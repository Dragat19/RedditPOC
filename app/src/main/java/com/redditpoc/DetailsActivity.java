package com.redditpoc;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import static com.redditpoc.RedditRecyclerAdapter.ADAPTER_THUMBNAILS;

/**
 * Created by levaa on 6/7/2017.
 */

public class DetailsActivity extends AppCompatActivity {
    private ImageView imageSave;
    private TextView btnCancel;
    private Button btnSave;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        imageSave = (ImageView) findViewById(R.id.saveImage);
        btnCancel = (TextView) findViewById(R.id.saveBtnCancel);
        btnSave = (Button) findViewById(R.id.saveBtn);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);

        String Image = getIntent().getStringExtra(ADAPTER_THUMBNAILS);
        Picasso.with(DetailsActivity.this)
                .load(Image)
                .resize(200, 200)
                .error(R.mipmap.ic_launcher)
                .into(imageSave);
    }
}
