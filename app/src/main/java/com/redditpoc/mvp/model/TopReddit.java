package com.redditpoc.mvp.model;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by levaa on 6/6/2017.
 */

public class TopReddit {
    private static final String JSON_TITLE = "title";
    private static final String JSON_AUTHOR = "author";
    private static final String JSON_THUMBNAIL = "thumbnail";
    private static final String JSON_DATE_CREATED = "created_utc";
    private static final String JSON_COMMENTS = "num_comments";
    private static final String JSON_IMAGE = "url";

    private String title;
    private String author;
    private int created_utc;
    private String num_comments;
    private String thumbnail;
    private String image;



    public TopReddit(JSONObject json) throws JSONException {
        this.title = json.getString(JSON_TITLE);
        this.author = json.getString(JSON_AUTHOR);
        this.thumbnail = json.getString(JSON_THUMBNAIL);
        this.created_utc = json.getInt(JSON_DATE_CREATED);
        this.num_comments = json.getString(JSON_COMMENTS);


       /* if (json.has("preview")){
            JSONArray imagenReddit = json.getJSONObject("preview").getJSONArray("images");
            for (int j = 0 ; j<imagenReddit.length() ; j++){

                String string = imagenReddit.getJSONObject(j).getJSONObject("source").getString(JSON_IMAGE);
                String[] parts = string.split("/");
                this.image=parts[3];

            }
        }*/

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


    public JSONObject toJSON() throws JSONException {
        JSONObject obj = new JSONObject();
        obj.put(JSON_TITLE, getTitle());
        obj.put(JSON_AUTHOR, getAuthor());
        obj.put(JSON_THUMBNAIL, getThumbnail());
        obj.put(JSON_DATE_CREATED, getCreated_utc());
        obj.put(JSON_COMMENTS, getNum_comments());
        return obj;
    }
}
