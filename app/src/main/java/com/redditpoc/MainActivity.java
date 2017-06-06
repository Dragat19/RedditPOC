package com.redditpoc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.items_info_reddit);
        getSupportActionBar().setIcon(R.drawable.icon_reddit);
    }
}
