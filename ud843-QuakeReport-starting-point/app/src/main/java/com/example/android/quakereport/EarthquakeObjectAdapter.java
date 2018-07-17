package com.example.android.quakereport;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.support.v7.widget.RecyclerView;


import java.util.List;

public class EarthquakeObjectAdapter extends RecyclerView.Adapter<EarthquakeObjectAdapter.EarthquakeViewHolder> {

    private List<EarthquakeObject> earthquakes;
    private Context context;

    //create the adapter
    public EarthquakeObjectAdapter(Context context, List<EarthquakeObject> earthquakes){

        //initialize the adapter
        this.earthquakes = earthquakes;
        this.context = context;
    }

    //create the viewholder
    public EarthquakeViewHolder onCreateViewHolder(ViewGroup parent, int viewType){

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new EarthquakeViewHolder(this.context, view);
    }

    @Override
    public void onBindViewHolder(EarthquakeViewHolder holder, int position) {

        EarthquakeObject earthquakes = this.earthquakes.get(position);
        holder.magnitude.setText(earthquakes.getMagnitude());
        holder.location.setText(earthquakes.getLocation());
        holder.time.setText(earthquakes.getTime());

    }

    @Override
    public int getItemCount() {
        return earthquakes.size();
    }

    public class EarthquakeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private final TextView magnitude, location, time;
        private EarthquakeObject earthquakes;
        private Context context;

        public EarthquakeViewHolder(Context context, View itemView){
            super(itemView);

            //set the context
            this.context = context;

            //inflate the UI pieces of the view for the view holder widget
            this.magnitude = (TextView)itemView.findViewById(R.id.magnitude);
            this.location = (TextView)itemView.findViewById(R.id.location);
            this.time = (TextView)itemView.findViewById(R.id.time);


            //set onclick listener
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v){
            //handle the click by calling an intent to the MediaDetailsActivity
            int position = getAdapterPosition(); // gets item position
            Log.i("EarthquakeObjectAdapter", "a thing was clicked");
        }

    }
}

