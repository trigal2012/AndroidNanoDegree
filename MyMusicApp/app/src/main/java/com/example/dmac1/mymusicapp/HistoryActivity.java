package com.example.dmac1.mymusicapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.media_list);

        //*** This next section creates the list of media files that are part of the History category ***
        final ArrayList<Media> myMedia = new ArrayList<Media>();
        myMedia.add(new Media("Grade 10: American Civil War", "00:05:00", "Lorem ipsum dolor sit amet, " +
                "consectetur adipiscing elit. Vivamus ullamcorper magna at turpis consequat, " +
                "vitae ultrices ex commodo. Etiam ultrices tempus felis, sed pretium urna eleifend " +
                "pulvinar. Morbi viverra vestibulum fringilla. Donec lacinia, eros et varius gravida, " +
                "nisi nisi tincidunt elit, eget condimentum mi tortor id turpis. Maecenas ut fermentum dui. " +
                "Integer eget facilisis dolor. Class aptent taciti sociosqu ad litora torquent per " +
                "conubia nostra, per inceptos himenaeos.", "completed"));
        myMedia.add(new Media("Grade 6: Paris Climate Summit", "00:05:50", "Lorem ipsum dolor sit amet, " +
                "consectetur adipiscing elit. Vivamus ullamcorper magna at turpis consequat, " +
                "vitae ultrices ex commodo. Etiam ultrices tempus felis, sed pretium urna eleifend " +
                "pulvinar. Morbi viverra vestibulum fringilla. Donec lacinia, eros et varius gravida, " +
                "nisi nisi tincidunt elit, eget condimentum mi tortor id turpis. Maecenas ut fermentum dui. " +
                "Integer eget facilisis dolor. Class aptent taciti sociosqu ad litora torquent per " +
                "conubia nostra, per inceptos himenaeos.", ""));
        myMedia.add(new Media("Grade 9: Assasinations", "00:10:45", "Lorem ipsum dolor sit amet, " +
                "consectetur adipiscing elit. Vivamus ullamcorper magna at turpis consequat, " +
                "vitae ultrices ex commodo. Etiam ultrices tempus felis, sed pretium urna eleifend " +
                "pulvinar. Morbi viverra vestibulum fringilla. Donec lacinia, eros et varius gravida, " +
                "nisi nisi tincidunt elit, eget condimentum mi tortor id turpis. Maecenas ut fermentum dui. " +
                "Integer eget facilisis dolor. Class aptent taciti sociosqu ad litora torquent per " +
                "conubia nostra, per inceptos himenaeos.", "started"));
        myMedia.add(new Media("Grade 4: Kings and Queens", "00:04:00", "Lorem ipsum dolor sit amet, " +
                "consectetur adipiscing elit. Vivamus ullamcorper magna at turpis consequat, " +
                "vitae ultrices ex commodo. Etiam ultrices tempus felis, sed pretium urna eleifend " +
                "pulvinar. Morbi viverra vestibulum fringilla. Donec lacinia, eros et varius gravida, " +
                "nisi nisi tincidunt elit, eget condimentum mi tortor id turpis. Maecenas ut fermentum dui. " +
                "Integer eget facilisis dolor. Class aptent taciti sociosqu ad litora torquent per " +
                "conubia nostra, per inceptos himenaeos.", "completed"));
        myMedia.add(new Media("Grade 8: Industrial Revolution", "00:01:30", "Lorem ipsum dolor sit amet, " +
                "consectetur adipiscing elit. Vivamus ullamcorper magna at turpis consequat, " +
                "vitae ultrices ex commodo. Etiam ultrices tempus felis, sed pretium urna eleifend " +
                "pulvinar. Morbi viverra vestibulum fringilla. Donec lacinia, eros et varius gravida, " +
                "nisi nisi tincidunt elit, eget condimentum mi tortor id turpis. Maecenas ut fermentum dui. " +
                "Integer eget facilisis dolor. Class aptent taciti sociosqu ad litora torquent per " +
                "conubia nostra, per inceptos himenaeos.", "started"));
        myMedia.add(new Media("Grade 12: Communism", "00:03:10", "Lorem ipsum dolor sit amet, " +
                "consectetur adipiscing elit. Vivamus ullamcorper magna at turpis consequat, " +
                "vitae ultrices ex commodo. Etiam ultrices tempus felis, sed pretium urna eleifend " +
                "pulvinar. Morbi viverra vestibulum fringilla. Donec lacinia, eros et varius gravida, " +
                "nisi nisi tincidunt elit, eget condimentum mi tortor id turpis. Maecenas ut fermentum dui. " +
                "Integer eget facilisis dolor. Class aptent taciti sociosqu ad litora torquent per " +
                "conubia nostra, per inceptos himenaeos.", ""));


        // Create an {@link MediaAdapter}, whose data source is a list of {@link Media}. The
        // adapter knows how to create list items for each item in the list.
        MediaAdapter adapter = new MediaAdapter(this, myMedia);

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // list_items.xml layout file.
        ListView listView = findViewById(R.id.list);

        // Make the {@link ListView} use the {@link MediaAdapter} we created above, so that the
        // {@link ListView} will display list items for each {@link Media} in the list.
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // get media specific details then start the media details activity
                Intent mediaDetailsIntent = new Intent(HistoryActivity.this, MediaDetailsActivity.class);
                mediaDetailsIntent.putExtra("mediaTitle", myMedia.get(position).getMediaTitle());
                mediaDetailsIntent.putExtra("mediaLength", myMedia.get(position).getMediaLength());
                mediaDetailsIntent.putExtra("mediaStatus", myMedia.get(position).getMediaStatus());
                mediaDetailsIntent.putExtra("mediaDescription", myMedia.get(position).getMediaDescription());
                startActivity(mediaDetailsIntent);
            }
        });
        //******** End of media files list creation ****
    }
}
