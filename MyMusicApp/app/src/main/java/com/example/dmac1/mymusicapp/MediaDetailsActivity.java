package com.example.dmac1.mymusicapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MediaDetailsActivity extends AppCompatActivity {

    int imageViewId_play = R.id.media_play;
    int imageViewId_pause = R.id.media_pause;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_view);

        Intent intent = getIntent();

        TextView mediaTitle = findViewById(R.id.media_title);
        mediaTitle.setText(intent.getStringExtra("mediaTitle"));

        TextView mediaLength = findViewById(R.id.media_length);
        mediaLength.setText(intent.getStringExtra("mediaLength"));

        TextView mediaStatus = findViewById(R.id.media_status);
        mediaStatus.setText(intent.getStringExtra("mediaStatus"));

        TextView mediaDescription = findViewById(R.id.media_description);
        mediaDescription.setText(intent.getStringExtra("mediaDescription"));

        final ImageView play_pause = findViewById(imageViewId_play);
        final ImageView pause_play = findViewById(imageViewId_pause);

        play_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (play_pause.getVisibility() == View.VISIBLE) {
                    play_pause.setVisibility(View.INVISIBLE);
                    pause_play.setVisibility(View.VISIBLE);
                }
            }
        });

        pause_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pause_play.getVisibility() == View.VISIBLE) {
                    pause_play.setVisibility(View.INVISIBLE);
                    play_pause.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}