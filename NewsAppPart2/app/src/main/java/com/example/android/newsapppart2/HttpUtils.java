package com.example.android.newsapppart2;

import android.os.Build;
import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


final class HttpUtils {

    //Create a private constructor
    private HttpUtils() {
    }

    /**
     * Query the guardian api and return a list of {@link Item} objects.
     */
    public static List<Item> fetchItemData(String requestUrl) {

        // Create URL object
        URL url = createUrl(requestUrl);

        // Perform HTTP request to the URL and receive a JSON response back
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e("my tag", "Problem making the HTTP request.", e);
        }

        // Extract relevant fields from the JSON response and create a list of {@link Item}s

        // Return the list of {@link Item}s
        return extractResultsFromJson(jsonResponse);
    }

    /**
     * Returns new URL object from the given string URL.
     */
    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e("my tag", "Problem building the URL ", e);
        }
        return url;
    }

    /**
     * Make an HTTP request to the given URL and return a String as the response.
     */
    private static String makeHttpRequest(URL url) throws IOException {

        String jsonResponse = "";

        // If the URL is null, then return early.
        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // If the request was successful (response code 200),
            // then read the input stream and parse the response.
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);

            } else {
                Log.e("my tag", "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e("my tag", "Problem retrieving the item JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                // Closing the input stream could throw an IOException, which is why
                // the makeHttpRequest(URL url) method signature specifies than an IOException
                // could be thrown.
                inputStream.close();
            }
        }

        return jsonResponse;
    }

    /**
     * Convert the {@link InputStream} into a String which contains the
     * whole JSON response from the server.
     */
    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }

        return output.toString();
    }

    /**
     * Return a list of {@link Item} objects that has been built up from
     * parsing the given JSON response.
     */
    private static List<Item> extractResultsFromJson(String itemJSON) {

        // If the JSON string is empty or null, then return early.
        if (TextUtils.isEmpty(itemJSON)) {
            return null;
        }

        // Create an empty ArrayList that we can start adding items to
        List<Item> items = new ArrayList<>();

        // Try to parse the JSON response string. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try {

            //***these next steps deal with drilling down to the results array***

            // 1. Create a JSONObject from the JSON response string
            JSONObject baseJson = new JSONObject(itemJSON);

            // 2. Extract the json object associated with the key called "response",
            JSONObject baseJsonResponse = null;
            try {
                baseJsonResponse = baseJson.getJSONObject("response");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            // 3. Extract the json array associated with the key called "results",
            JSONArray baseJsonResponseResults = null;
            try {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    baseJsonResponseResults = Objects.requireNonNull(baseJsonResponse).getJSONArray("results");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            // 4. iterate thru each result to extract the desired data and create an Item object with that data
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                for (int i = 0; i < Objects.requireNonNull(baseJsonResponseResults).length(); i++) {

                    // 5. get the sectionName, webTitle, contributor, webUrl, publicationDate;
                    // Get a single item at position i within the list of items
                    JSONObject currentItem = baseJsonResponseResults.getJSONObject(i);
                    String contributor = "";
                    String publicationDate = "";

                    //check for a tags array within results
                    if(currentItem.getJSONArray("tags") != null && currentItem.getJSONArray("tags").length() > 0) {

                        JSONArray tags = currentItem.getJSONArray("tags");

                        //5.0 get the tags object
                        JSONObject tagsObject = tags.getJSONObject(0);

                        // 5.1 Extract the value for the key called "webTitle" and set that to the contributor
                        if(tagsObject.getString("webTitle") != null) {
                            contributor = tagsObject.getString("webTitle");
                        }
                    }
                    // 5.2 Extract the value for the key called "sectionName"
                    String sectionName = currentItem.getString("sectionName");

                    // 5.3 Extract the value for the key called "webTitle"
                    String webTitle = currentItem.getString("webTitle");

                    // 5.4 Extract the value for the key called "webTitle"
                    String webUrl = currentItem.getString("webUrl");

                    // 5.5 Extract the value for the key called "webPublicationDate"
                    if(currentItem.getString("webPublicationDate") != null) {
                        publicationDate = currentItem.getString("webPublicationDate");
                    }
                    // Create a new {@link Item} object with the contributor,
                    // section name, item title, item publication date
                    // and url from the JSON response.
                    Item item = new Item(sectionName, webTitle, contributor, webUrl, publicationDate);

                    // Add the new {@link Item} to the list of items.
                    items.add(item);
                }
            }

        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("http utils", "Problem parsing the item JSON results", e);
        }

        // Return the list of items
        return items;
    }
}

