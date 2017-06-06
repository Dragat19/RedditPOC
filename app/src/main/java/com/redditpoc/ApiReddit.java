package com.redditpoc;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

/**
 * Created by levaa on 6/6/2017.
 */

public class ApiReddit {
    private static final String BASE_URL =  "www.reddit.com/top.json?limit=10";
    private static AsyncHttpClient client = new AsyncHttpClient();
    private static RequestParams params = new RequestParams();

    public static void GetTopReddit(){

        client.get(BASE_URL, params, new TextHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Gson gson = new GsonBuilder().create();
                InfoReddit infoReddit = gson.fromJson(responseString, InfoReddit.class);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

            }


        });

    }


}
