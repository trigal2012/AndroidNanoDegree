package com.example.dmac1.mymusicapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the content of the activity to use the activity_main.xml layout file
        setContentView(R.layout.activity_main);

        // Find the View that shows the math category
        TextView math = findViewById(R.id.math);

        // Set a click listener on that View
        math.setOnClickListener(new OnClickListener() {
            // The code in this method will be executed when the math category is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link MathActivity}
                Intent mathIntent = new Intent(MainActivity.this, MathActivity.class);

                // Start the new activity
                startActivity(mathIntent);
            }
        });

        // Find the View that shows the history category
        TextView history = findViewById(R.id.history);

        // Set a click listener on that View
        history.setOnClickListener(new OnClickListener() {
            // The code in this method will be executed when the history category is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link HistoryActivity}
                Intent historyIntent = new Intent(MainActivity.this, HistoryActivity.class);

                // Start the new activity
                startActivity(historyIntent);
            }
        });

        // Find the View that shows the literature category
        TextView literature = findViewById(R.id.literature);

        // Set a click listener on that View
        literature.setOnClickListener(new OnClickListener() {
            // The code in this method will be executed when the literature category is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link LiteratureActivity}
                Intent literatureIntent = new Intent(MainActivity.this, LiteratureActivity.class);

                // Start the new activity
                startActivity(literatureIntent);
            }
        });
    }
}