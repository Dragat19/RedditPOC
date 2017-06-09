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

    private static final String JSON_REDDIT_TOP_REDDIT = "data";
    private static final String JSON_REDDIT_AFTER  = "after";
    private static final String JSON_REDDIT_BEFORE  = "before";
        private static final String JSON_REDDIT_CHILDREN  = "children";

    private String after_page;
    private String before_page;
    private List<TopReddit> topReddit;

    public InfoReddit() {
        this.topReddit = new ArrayList<TopReddit>();
    }

    public InfoReddit(JSONObject json) throws JSONException {

        this.topReddit = new ArrayList<TopReddit>();

        if (json.has(JSON_REDDIT_CHILDREN)) {
            JSONArray childrenReddit = json.getJSONArray(JSON_REDDIT_CHILDREN);
            for (int i = 0; i < childrenReddit.length(); i++) {
                this.topReddit.add(new TopReddit(childrenReddit.getJSONObject(i).getJSONObject(JSON_REDDIT_TOP_REDDIT)));
            }
        }

        this.after_page = json.getString(JSON_REDDIT_AFTER);
        this.before_page = json.getString(JSON_REDDIT_BEFORE);
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
