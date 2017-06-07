package com.redditpoc.Model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by levaa on 6/5/2017.
 */

public class InfoReddit {

    private String after_page;
    private String before_page;
    private List<TopReddit> topReddit;

    public InfoReddit() {
        this.topReddit = new ArrayList<TopReddit>();
    }

    public InfoReddit(JSONObject json) throws JSONException {

        this.topReddit = new ArrayList<TopReddit>();
        JSONArray childrenReddit =  json.getJSONArray("children");
        for (int i = 0; i < childrenReddit.length(); i++) {
            this.topReddit.add(new TopReddit(childrenReddit.getJSONObject(i).getJSONObject("data")));
        }

        this.after_page = json.getString("after");

        this.before_page = json.getString("before");

    }

    public List<TopReddit> getTopReddit() {
        return topReddit;
    }

    public String getAfter_page() {
        return after_page;
    }

    public String getBefore_page() {
        return before_page;
    }
}
