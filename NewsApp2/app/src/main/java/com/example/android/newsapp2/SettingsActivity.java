package com.example.android.newsapp2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SettingsActivity extends AppCompatActivity {

    public static final String KEY_PREF_EDITION = "edition_key";
    public static final String KEY_PREF_ENVIRONMENT = "environment_key";
    public static final String KEY_PREF_SPORTS = "sport_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SettingsFragment())
                .commit();
    }
}
