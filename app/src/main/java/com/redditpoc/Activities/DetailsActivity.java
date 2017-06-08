package com.redditpoc.Activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.os.EnvironmentCompat;
import android.support.v7.app.ActionBar;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.redditpoc.R;
import com.redditpoc.SaveImageGallery;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.redditpoc.RedditRecyclerAdapter.ADAPTER_IMAGEN;

/**
 * Created by levaa on 6/7/2017.
 */

public class DetailsActivity extends AppCompatActivity {
    private ImageView imageSave;
    private TextView btnCancel;
    private Button btnSave;
    private SaveImageGallery saveImageGallery;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        imageSave = (ImageView) findViewById(R.id.saveImage);
        btnCancel = (TextView) findViewById(R.id.saveBtnCancel);
        btnSave = (Button) findViewById(R.id.saveBtn);
        saveImageGallery = new SaveImageGallery(this);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);

        String Image = getIntent().getStringExtra(ADAPTER_IMAGEN);
        Picasso.with(DetailsActivity.this)
                .load(Image)
                .resize(800,950)
                .error(R.drawable.nohay)
                .into(imageSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder dialog = new AlertDialog.Builder(DetailsActivity.this);
                dialog.setTitle("Save Image");
                dialog.setMessage("You sure to save your image?");
                dialog.setCancelable(false);
                dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        saveImageGallery.saveImage(imageSave);
                    }
                });

                dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                dialog.create().show();
            }
        });

        btnCancel.setPaintFlags( btnCancel.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }



}
