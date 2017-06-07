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
    private static AsyncHttpClient client = new AsyncHttpClient();
    private static RequestParams params = new RequestParams();

    /*public static List<InfoReddit> GetTopReddit(){
        final List<InfoReddit> info = new ArrayList<InfoReddit>();
        client.get(BASE_URL, params, new TextHttpResponseHandler() {

           @Override
           public void onSuccess(int statusCode, Header[] headers, String responseString) {

               client.get(BASE_URL,params,new JsonHttpResponseHandler() {
                   @Override
                   public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                       super.onSuccess(statusCode, headers, response);
                       try {

                           JSONObject dataReddit = response.getJSONObject("data");
                           info.add(new InfoReddit(dataReddit));

                       }catch (Exception e){
                           e.printStackTrace();
                       }
                   }

                   @Override
                   public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                       super.onFailure(statusCode, headers, throwable, errorResponse);
                   }
               });

           }


           @Override
           public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

           }


       });

        return info;
    }*/

    public static void getReddit(RequestParams params, AsyncHttpResponseHandler asyncHttpResponseHandler){

        client.get(BASE_URL,params,asyncHttpResponseHandler);
    }




}
