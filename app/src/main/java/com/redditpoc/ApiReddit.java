package com.redditpoc;


import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;


import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * Created by levaa on 6/6/2017.
 */

public class ApiReddit {
    private static final String BASE_URL =  "https://www.reddit.com/top.json?limit=10";
    private final String API_BASE_URL = "http://www.reddit.com/";
    private static AsyncHttpClient client;

    public ApiReddit() {
        this.client = new AsyncHttpClient();
    }


    private String getApiUrl(String subreddit) {
        String url = API_BASE_URL;

        if(!subreddit.equals("")){
            url += "r/" + subreddit;
        }

        return url + ".json";
    }

    public void getPosts(JsonHttpResponseHandler handler) {
        String url = getApiUrl("");
        client.get(url, handler);
    }



    public static void getReddit(RequestParams params, AsyncHttpResponseHandler asyncHttpResponseHandler){

        client.get(BASE_URL,params,asyncHttpResponseHandler);
    }

}
