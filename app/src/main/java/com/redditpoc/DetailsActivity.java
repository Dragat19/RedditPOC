package com.redditpoc;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.redditpoc.Utils.SaveImageGallery;
import com.squareup.picasso.Picasso;

import static com.redditpoc.Adapter.RedditRecyclerAdapter.ADAPTER_IMAGEN;

/**
 * Created by levaa on 6/7/2017.
 */

public class DetailsActivity extends AppCompatActivity {

    private ImageView imageSave;
    private TextView btnCancel;
    private Button btnSave;
    private SaveImageGallery saveImageGallery;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        imageSave = (ImageView) findViewById(R.id.saveImage);
        btnCancel = (TextView) findViewById(R.id.saveBtnCancel);
        btnSave = (Button) findViewById(R.id.saveBtn);
        saveImageGallery = new SaveImageGallery(this);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_reddit_white);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        String Image = getIntent().getStringExtra(ADAPTER_IMAGEN);
        Picasso.with(DetailsActivity.this)
                .load(Image)
                .placeholder(R.drawable.icon_reddit)
                .resize(800,950)
                .error(R.drawable.icon_reddit)
                .into(imageSave);



        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder dialog = new AlertDialog.Builder(DetailsActivity.this);
                dialog.setTitle("Guardar Imagen");
                dialog.setMessage("¿Estas seguro que quieres guardar la imagen?");
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
