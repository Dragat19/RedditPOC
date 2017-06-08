package com.redditpoc.Model;

import android.util.Log;

import org.json.JSONArray;
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
    private String image;

    public TopReddit(JSONObject json) throws JSONException {
        this.title = json.getString("title");
        this.author = json.getString("author");
        this.thumbnail = json.getString("thumbnail");
        this.created_utc = json.getInt("created_utc");
        this.num_comments = json.getString("num_comments");

        if (json.has("preview")){
            JSONArray imagenReddit = json.getJSONObject("preview").getJSONArray("images");
            for (int j = 0 ; j<imagenReddit.length() ; j++){
                this.image = imagenReddit.getJSONObject(j).getJSONObject("source").getString("url");
            }
        }

    }

    public String getImage() {
        return image;
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
