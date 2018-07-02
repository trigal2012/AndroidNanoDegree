package com.example.dmac1.mymusicapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;


public class MediaAdapter extends ArrayAdapter {
    private Media myMedia;
    private Activity mContext;

    public MediaAdapter(Activity context, ArrayList<Media> media) {
        super(context, 0, media);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        Media my_media = (Media) getItem(position);
        TextView mediaTitleTextView = listItemView.findViewById(R.id.media_title);
        mediaTitleTextView.setText(my_media.getMediaTitle());
        TextView mediaStatusTextView = listItemView.findViewById(R.id.media_status);
        mediaStatusTextView.setText(my_media.getMediaStatus());
        return listItemView;
        //return super.getView(position, convertView, parent);
    }
}

