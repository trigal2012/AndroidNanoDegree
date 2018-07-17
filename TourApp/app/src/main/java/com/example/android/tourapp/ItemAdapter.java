package com.example.android.tourapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ItemAdapter extends ArrayAdapter<Item> {

    private String mySourceFragment;
    private int text1, text2, imageLocation;
    private boolean useMapAutoLink;

    /**
     * Create a new object. pass in the arrayList of Items from the fragment
     *
     * @param context is the current context (i.e. Activity) that the adapter is being created in.
     * @param items   is the list of items to be displayed.
     */
    //create a new ItemAdapter object
    public ItemAdapter(Context context, ArrayList<Item> items, String sourceFragment) {
        super(context, 0, items);
        mySourceFragment = sourceFragment;
    }

    //this section dynamically creates the layout and list that will be displayed
    //in the fragment container
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the item object located at this position in the list
        Item currentItem = getItem(position);

        switch (mySourceFragment) {
            case "event":
                text1 = currentItem.getmDateId();
                text2 = currentItem.getmNameId();
                break;
            case "art":
                text1 = currentItem.getmNameId();
                text2 = currentItem.getmAddressId();
                imageLocation = R.id.imageLeft;
                useMapAutoLink = true;
                break;
            case "eat":
                text1 = currentItem.getmNameId();
                text2 = currentItem.getmAddressId();
                useMapAutoLink = true;
                break;
            case "walk":
                text1 = currentItem.getmNameId();
                text2 = currentItem.getmAddressId();
                imageLocation = R.id.imageRight;
                useMapAutoLink = true;
                break;
        }

        // Check if an existing view is being reused, otherwise inflate the view
        View listItemView = convertView;

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.fragment_item_row, parent, false);
        }

        // Find the TextView in the fragment_item_row.xml layout (text_1)
        // For the Event Fragment - this is the left column text and is the date
        TextView text_1 = (TextView) listItemView.findViewById(R.id.text_1);
        text_1.setText(text1);

        // Find the TextView in the ffragment_item_row.xml layout (text_2)
        // For the Event Fragment - this is the right column text and is the event name
        TextView text_2 = (TextView) listItemView.findViewById(R.id.text_2);
        text_2.setText(text2);

        // Find the ImageViews in the fragment_item_row.xml layout.

        //if item has image, set image view for left or right and make the other image view gone
        if (currentItem.hasImage() && imageLocation == R.id.imageLeft) {
            ImageView imageViewLeft = (ImageView) listItemView.findViewById(R.id.imageLeft);
            imageViewLeft.setImageResource(currentItem.getmImageResourceId());
            imageViewLeft.setVisibility(View.VISIBLE);

            ImageView imageViewRight = (ImageView) listItemView.findViewById(R.id.imageRight);
            imageViewRight.setVisibility(View.GONE);

        } else if (currentItem.hasImage() && imageLocation == R.id.imageRight) {
            ImageView imageViewLeft = (ImageView) listItemView.findViewById(R.id.imageLeft);
            imageViewLeft.setVisibility(View.GONE);

            ImageView imageViewRight = (ImageView) listItemView.findViewById(R.id.imageRight);
            imageViewRight.setImageResource(currentItem.getmImageResourceId());
            imageViewRight.setVisibility(View.VISIBLE);
        } else {
            // Otherwise hide the ImageViews (set visibility to GONE)
            ImageView imageViewLeft = (ImageView) listItemView.findViewById(R.id.imageLeft);
            imageViewLeft.setVisibility(View.GONE);

            ImageView imageViewRight = (ImageView) listItemView.findViewById(R.id.imageRight);
            imageViewRight.setVisibility(View.GONE);
        }

        // Return the fragment_*_item.xml layout (containing 2 TextViews) so that it can be shown in
        // the ListView.
        return listItemView;
    }
}
