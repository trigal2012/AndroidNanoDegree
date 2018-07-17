/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.quakereport;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import java.util.ArrayList;
import java.util.List;

public class EarthquakeActivity extends AppCompatActivity {


    private RecyclerView listingsView;
    private List<EarthquakeObject> earthquakes;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        this.context = this;

        //load array of earthquakes
        loadEarthquakes();

        //Initialize the Earthquake adapter
        EarthquakeObjectAdapter adapter = new EarthquakeObjectAdapter(this, earthquakes);

        //Initilaize the RecyclerView layout Manager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

        //inflate RecyclerView, tell OS size will not change, set the layoutManager
        listingsView = (RecyclerView) findViewById(R.id.list);
        listingsView.setLayoutManager(layoutManager);
        listingsView.setHasFixedSize(true);

        //attach adapter to the RecyclerView
        listingsView.setAdapter(adapter);
    }

    //method to load earthquake info
    private void loadEarthquakes(){
        earthquakes = new ArrayList<>();
        earthquakes.add(new EarthquakeObject("7.2", "San Francisco", "Feb 2, 2016"));
        earthquakes.add(new EarthquakeObject("6.1", "London", "July 20, 2016"));
        earthquakes.add(new EarthquakeObject("3.9", "Tokyo", "Nov 10, 2014"));
        earthquakes.add(new EarthquakeObject("5.4", "Mexico City","May 3, 2014"));
        earthquakes.add(new EarthquakeObject("2.8", "Moscow","Jan 31, 2013"));
        earthquakes.add(new EarthquakeObject("4.0", "Rio de Janeiro","Aug 19, 2012"));
        earthquakes.add(new EarthquakeObject("1.6", "Paris","Oct 30, 2011"));
    }
}
