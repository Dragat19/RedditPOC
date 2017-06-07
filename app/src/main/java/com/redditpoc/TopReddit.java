package com.redditpoc;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by levaa on 6/6/2017.
 */

public class TopReddit {
    private String title;
    private String author;
    private int created_utc;
    private String num_comments;
    private String thumbnail;


    public TopReddit(JSONObject json) throws JSONException {
        this.title = json.getString("title");
        this.author = json.getString("author");
        this.thumbnail = json.getString("thumbnail");
        this.created_utc = json.getInt("created_utc");
        this.num_comments = json.getString("num_comments");
        Log.e("Title", getTitle());
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getCreated_utc() {
        return created_utc;
    }

    public String getNum_comments() {
        return num_comments;
    }

    public String getThumbnail() {
        return thumbnail;
    }
}
