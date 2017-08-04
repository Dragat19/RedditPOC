package com.redditpoc.mvp.model;
import android.content.Context;
import android.content.SharedPreferences;
import org.json.JSONArray;
import org.json.JSONException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by levaa on 6/8/2017.
 */

public class ManagerReddit {

    private static final String PREFERENCES = "MyPreferences";
    private static final String PREFERENCES_GROUPS = "com.reddit.inforeddit";
    private static ManagerReddit instance = null;
    private Context context;

    public ManagerReddit(Context context) {
        this.context = context;
    }

    private List<TopReddit> groups = new ArrayList<>();

    public void setGroups(List<TopReddit> groups) {
        this.groups = groups;
    }

    public List<TopReddit> getGroups() {
        return this.groups;
    }

    public void saveAll() {
        saveGroups();
    }


    public void saveGroups() {
        try {
            JSONArray es = new JSONArray();
            for (TopReddit g : getGroups()) {
                es.put(g.toJSON());
            }
            if (es.length() > 0) {
                SharedPreferences preferences = context.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString(PREFERENCES_GROUPS, es.toString());
                editor.commit();
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public List<TopReddit> loadAll() {
        return loadGroups();
    }

    private List<TopReddit> loadGroups() {

        SharedPreferences preferences = context.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
        String strExperiences = preferences.getString(PREFERENCES_GROUPS, "");

        try {
            JSONArray es = new JSONArray(strExperiences);
            List<TopReddit> groups = new ArrayList<>();
            for (int i = 0; i < es.length(); i++) {
                TopReddit g = new TopReddit(es.getJSONObject(i));
                groups.add(g);
            }
            setGroups(groups);
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        return getGroups();
    }

}
