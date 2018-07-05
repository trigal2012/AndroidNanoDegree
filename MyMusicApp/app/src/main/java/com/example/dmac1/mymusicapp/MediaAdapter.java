package com.example.dmac1.mymusicapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


public class MediaAdapter extends RecyclerView.Adapter<MediaAdapter.MediaViewHolder> {

    private List<Media> myMedia;
    private Context context;

    //create the adapter
    public MediaAdapter(Context context, List<Media> myMedia) {

        //initialize adapter
        this.myMedia = myMedia;
        this.context = context;
    }

    //create the viewholder
    @Override
    public MediaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new MediaViewHolder(this.context, view);
    }

    @Override
    public void onBindViewHolder(MediaViewHolder holder, int position) {

        Media myMedia = this.myMedia.get(position);
        holder.myMediaTitle.setText(myMedia.getMediaTitle());
        holder.myMediaStatus.setText(myMedia.getMediaStatus());
    }

    @Override
    public int getItemCount() {
        return myMedia.size();
    }

    public class MediaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private final TextView myMediaTitle, myMediaStatus;
        private Media media;
        private Context context;

        public MediaViewHolder(Context context, View itemView){
            super(itemView);

            //set the context
            this.context = context;

            //inflate the UI pieces of the view for the view holder widget
            this.myMediaTitle = (TextView)itemView.findViewById(R.id.media_title);
            this.myMediaStatus = (TextView)itemView.findViewById(R.id.media_status);

            //set onclick listener
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v){
            //handle the click by calling an intent to the MediaDetailsActivity
            int position = getAdapterPosition(); // gets item position
            if (position != RecyclerView.NO_POSITION) { // Check if an item was deleted, but the user clicked it before the UI removed it
                Media media = myMedia.get(position);

                // get media specific details then start the media details activity
                Intent mediaDetailsIntent = new Intent(context, MediaDetailsActivity.class);
                mediaDetailsIntent.putExtra("mediaTitle", myMedia.get(position).getMediaTitle());
                mediaDetailsIntent.putExtra("mediaLength", myMedia.get(position).getMediaLength());
                mediaDetailsIntent.putExtra("mediaStatus", myMedia.get(position).getMediaStatus());
                mediaDetailsIntent.putExtra("mediaDescription", myMedia.get(position).getMediaDescription());
                context.startActivity(mediaDetailsIntent);
            }
        }

    }
}

