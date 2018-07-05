package com.example.dmac1.mymusicapp;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MathActivity extends AppCompatActivity {

    private RecyclerView listingsView;
    private List<Media> myMedia;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //inflate activity layout
        setContentView(R.layout.media_list);
        this.context = this;

        //load media array
        loadMedia();

        //initialize MediaAdapter
        MediaAdapter adapter = new MediaAdapter(this, myMedia);

        //initialize RecyclerView layoutManager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

        //inflate RecyclerView, tell OS size will not change, set the layoutManager
        listingsView = (RecyclerView)findViewById(R.id.list);
        listingsView.setLayoutManager(layoutManager);
        listingsView.setHasFixedSize(true);

        //attach adapter to the RecyclerView
        listingsView.setAdapter(adapter);
    }

    private void loadMedia(){
        myMedia = new ArrayList<>();
        myMedia.add(new Media("Grade 5: Addition: Lesson 2", "00:05:00", "Lorem ipsum dolor sit amet, " +
                "consectetur adipiscing elit. Vivamus ullamcorper magna at turpis consequat, " +
                "vitae ultrices ex commodo. Etiam ultrices tempus felis, sed pretium urna eleifend " +
                "pulvinar. Morbi viverra vestibulum fringilla. Donec lacinia, eros et varius gravida, " +
                "nisi nisi tincidunt elit, eget condimentum mi tortor id turpis. Maecenas ut fermentum dui. " +
                "Integer eget facilisis dolor. Class aptent taciti sociosqu ad litora torquent per " +
                "conubia nostra, per inceptos himenaeos.", "completed"));
        myMedia.add(new Media("Grade 5: Addition: Lesson 4", "00:05:50", "Lorem ipsum dolor sit amet, " +
                "consectetur adipiscing elit. Vivamus ullamcorper magna at turpis consequat, " +
                "vitae ultrices ex commodo. Etiam ultrices tempus felis, sed pretium urna eleifend " +
                "pulvinar. Morbi viverra vestibulum fringilla. Donec lacinia, eros et varius gravida, " +
                "nisi nisi tincidunt elit, eget condimentum mi tortor id turpis. Maecenas ut fermentum dui. " +
                "Integer eget facilisis dolor. Class aptent taciti sociosqu ad litora torquent per " +
                "conubia nostra, per inceptos himenaeos.", ""));
        myMedia.add(new Media("Grade 5: Addition: Lesson 6", "00:10:45", "Lorem ipsum dolor sit amet, " +
                "consectetur adipiscing elit. Vivamus ullamcorper magna at turpis consequat, " +
                "vitae ultrices ex commodo. Etiam ultrices tempus felis, sed pretium urna eleifend " +
                "pulvinar. Morbi viverra vestibulum fringilla. Donec lacinia, eros et varius gravida, " +
                "nisi nisi tincidunt elit, eget condimentum mi tortor id turpis. Maecenas ut fermentum dui. " +
                "Integer eget facilisis dolor. Class aptent taciti sociosqu ad litora torquent per " +
                "conubia nostra, per inceptos himenaeos.", "started"));
        myMedia.add(new Media("Grade 5: Addition: Lesson 8", "00:04:00", "Lorem ipsum dolor sit amet, " +
                "consectetur adipiscing elit. Vivamus ullamcorper magna at turpis consequat, " +
                "vitae ultrices ex commodo. Etiam ultrices tempus felis, sed pretium urna eleifend " +
                "pulvinar. Morbi viverra vestibulum fringilla. Donec lacinia, eros et varius gravida, " +
                "nisi nisi tincidunt elit, eget condimentum mi tortor id turpis. Maecenas ut fermentum dui. " +
                "Integer eget facilisis dolor. Class aptent taciti sociosqu ad litora torquent per " +
                "conubia nostra, per inceptos himenaeos.", "completed"));
        myMedia.add(new Media("Grade 6: Division: Lesson 1", "00:01:30", "Lorem ipsum dolor sit amet, " +
                "consectetur adipiscing elit. Vivamus ullamcorper magna at turpis consequat, " +
                "vitae ultrices ex commodo. Etiam ultrices tempus felis, sed pretium urna eleifend " +
                "pulvinar. Morbi viverra vestibulum fringilla. Donec lacinia, eros et varius gravida, " +
                "nisi nisi tincidunt elit, eget condimentum mi tortor id turpis. Maecenas ut fermentum dui. " +
                "Integer eget facilisis dolor. Class aptent taciti sociosqu ad litora torquent per " +
                "conubia nostra, per inceptos himenaeos.", "started"));
        myMedia.add(new Media("Grade 6: Division: Lesson 2", "00:03:10", "Lorem ipsum dolor sit amet, " +
                "consectetur adipiscing elit. Vivamus ullamcorper magna at turpis consequat, " +
                "vitae ultrices ex commodo. Etiam ultrices tempus felis, sed pretium urna eleifend " +
                "pulvinar. Morbi viverra vestibulum fringilla. Donec lacinia, eros et varius gravida, " +
                "nisi nisi tincidunt elit, eget condimentum mi tortor id turpis. Maecenas ut fermentum dui. " +
                "Integer eget facilisis dolor. Class aptent taciti sociosqu ad litora torquent per " +
                "conubia nostra, per inceptos himenaeos.", ""));
    }

}
