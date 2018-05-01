package com.example.dmac1.userprofile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView userPic = (ImageView)findViewById(R.id.profile_picture);
        userPic.setImageResource(R.drawable.kona_cliff);

        TextView name = (TextView)findViewById(R.id.name);
        name.setText("user name");

        TextView birthday = (TextView)findViewById(R.id.birthday);
        birthday.setText("1/1/1900");

        TextView country = (TextView)findViewById(R.id.country);
        country.setText("germany");
    }
}
