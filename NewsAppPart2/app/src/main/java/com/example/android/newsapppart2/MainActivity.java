package com.example.android.newsapppart2;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<List<Item>>{


    /**
     * URL for item data from the guardian data-set
     */
    private static final String GUARDIAN_REQUEST_URL = "https://content.guardianapis.com/";

    /**
     * Constant value for the item loader ID. We can choose any integer.
     * This really only comes into play if you're using multiple loaders.
     */
    private static final int ITEM_LOADER_ID = 1;

    /**
     * Adapter for the list of items
     */
    private ItemAdapter mAdapter;

    /**
     * TextView that is displayed when the list is empty
     */
    private TextView mEmptyStateTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (mAdapter != null) mAdapter.clear();
        setContentView(R.layout.activity_main);

        // Find a reference to the {@link ListView} in the layout
        ListView itemListView = findViewById(R.id.list);

        mEmptyStateTextView = findViewById(R.id.empty_view);
        itemListView.setEmptyView(mEmptyStateTextView);

        // Create a new adapter that takes an empty list of items as input
        mAdapter = new ItemAdapter(this, new ArrayList<Item>());

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        itemListView.setAdapter(mAdapter);

        // Obtain a reference to the SharedPreferences file for this app
        // SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        // And register to be notified of preference changes
        // So we know when the user has adjusted the query settings
        //prefs.registerOnSharedPreferenceChangeListener(this);

        // Set an item click listener on the ListView, which sends an intent to a web browser
        // to open a website with more information about the selected item.
        itemListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Find the current item that was clicked on
                Item currentItem = mAdapter.getItem(position);

                // Convert the String URL into a URI object (to pass into the Intent constructor)
                Uri itemUri = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                    itemUri = Uri.parse(Objects.requireNonNull(currentItem).getWebUrl());
                }

                // Create a new intent to view the item URI
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, itemUri);

                // Send the intent to launch a new activity
                startActivity(websiteIntent);
            }
        });

        // Get a reference to the ConnectivityManager to check state of network connectivity
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        // Get details on the currently active default data network
        NetworkInfo networkInfo = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            networkInfo = Objects.requireNonNull(connMgr).getActiveNetworkInfo();
        }

        // If there is a network connection, fetch data
        if (networkInfo != null && networkInfo.isConnected()) {
            // Get a reference to the LoaderManager, in order to interact with loaders.
            LoaderManager loaderManager = getLoaderManager();

            // Initialize the loader. Pass in the int ID constant defined above and pass in null for
            // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
            // because this activity implements the LoaderCallbacks interface).
            loaderManager.initLoader(ITEM_LOADER_ID, null, this);
        } else {
            // Otherwise, display error
            // First, hide loading indicator so error message will be visible
            View loadingIndicator = findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(View.GONE);


            // Update empty state with no connection error message
            mEmptyStateTextView.setText(R.string.no_internet_connection);
        }
    }

    @Override
    public Loader<List<Item>> onCreateLoader(int i, Bundle bundle) {
        // Create a new loader for the given URL
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        String numResults = sharedPrefs.getString(
                getString(R.string.number_results_key),
                getString(R.string.number_results_key_default));
        String newsType = sharedPrefs.getString(
                getString(R.string.newsType_key),
                getString(R.string.newsType_key_default));

        String apikey = "84dedd1d-bf23-46ca-ab4d-ef062a844f5f";
        String pageSize = numResults;
        String showTags = "contributor";

        // parse breaks apart the URI string that's passed into its parameter
        Uri baseUri = Uri.parse(GUARDIAN_REQUEST_URL);

        // buildUpon prepares the baseUri that we just parsed so we can add query parameters to it
        Uri.Builder uriBuilder = baseUri.buildUpon();

        // Append query parameter and its value. For example, the `format=geojson`
        if(newsType.equals("cycling")){
            uriBuilder.appendPath("sport");
        } if(newsType.equals("europe")){
            uriBuilder.appendPath("travel");
        }
        uriBuilder.appendPath(newsType);
        uriBuilder.appendQueryParameter("api-key", apikey);
        uriBuilder.appendQueryParameter("page-size", pageSize);
        uriBuilder.appendQueryParameter("show-tags", showTags);

        Log.i("loader 155", "url: " + uriBuilder.toString());
        // Return the completed uri
        return new ItemLoader(this, uriBuilder.toString());

    }

    @Override
    public void onLoadFinished(Loader<List<Item>> loader, List<Item> items) {
        // Hide loading indicator because the data has been loaded
        View loadingIndicator = findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.GONE);


        // Set empty state text to display "No items found."
        mEmptyStateTextView.setText(R.string.no_items);

        // Clear the adapter of previous item data
        mAdapter.clear();

        // If there is a valid list of {@link Item}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if (items != null && !items.isEmpty()) {
            mAdapter.addAll(items);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Item>> loader) {
        // Loader reset, so we can clear out our existing data.
        mAdapter.clear();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent settingsIntent = new Intent(this, SettingsActivity.class);
            startActivity(settingsIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}