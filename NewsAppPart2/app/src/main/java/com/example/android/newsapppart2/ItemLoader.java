package com.example.android.newsapppart2;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.util.List;


class ItemLoader extends AsyncTaskLoader<List<Item>> {

    /**
     * Query URL
     */
    private final String webUrl;

    /**
     * Constructs a new {@link ItemLoader}.
     *
     * @param context of the activity
     * @param swebUrl to load data from
     */
    public ItemLoader(Context context, String swebUrl) {
        super(context);
        webUrl = swebUrl;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    /**
     * This is on a background thread.
     */
    @Override
    public List<Item> loadInBackground() {
        if (webUrl == null) {
            return null;
        }

        // Perform the network request, parse the response, and extract a list of items.

        List<Item> items = HttpUtils.fetchItemData(webUrl);


        return items;
    }
}
