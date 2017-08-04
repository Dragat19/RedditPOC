package com.redditpoc.Utils;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;


/**
 * Created by levaa on 6/6/2017.
 */

public class ApiReddit {
    private static final String BASE_URL =  "https://www.reddit.com/top.json?limit=50";
    private final String API_BASE_URL = "http://www.reddit.com/";
    private static AsyncHttpClient client;

    public ApiReddit() {
        this.client = new AsyncHttpClient();
    }

    private String getApiUrl(String subreddit) {
        String url = API_BASE_URL;

        if(!subreddit.equals("")){
            url += "top.json?after=" + subreddit;
        }

        return url;
    }

    public void getPosts(String after,AsyncHttpResponseHandler  handler) {
        String url = getApiUrl(after);
        client.get(url, handler);
    }



    public static void getReddit(RequestParams params, AsyncHttpResponseHandler asyncHttpResponseHandler){

        client.get(BASE_URL,params,asyncHttpResponseHandler);
    }

}
