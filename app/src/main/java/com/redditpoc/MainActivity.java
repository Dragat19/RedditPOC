package com.redditpoc;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.util.Log;


import com.loopj.android.http.JsonHttpResponseHandler;
import com.redditpoc.Model.InfoReddit;
import com.redditpoc.Model.TopReddit;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {
    private long UTC_TIMEZONE=1496636570;
    private String OUTPUT_DATE_FORMATE="dd-MM-yyyy - hh:mm a";
    private List<InfoReddit> infoRedditList;
    private RecyclerView mRecycler;
    private RedditRecyclerAdapter adapter;
    private ApiReddit apiReddit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecycler = (RecyclerView)findViewById(R.id.reddit_recycler);
        infoRedditList = new ArrayList<InfoReddit>();
        apiReddit = new ApiReddit();

        apiReddit.getReddit(null ,new JsonHttpResponseHandler(){

            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, JSONObject response) {
                try {
                    JSONObject dataReddit = response.getJSONObject("data");
                    infoRedditList.add(new InfoReddit(dataReddit));
                    if (infoRedditList.size() != 0){
                        infoRedditList.get(0).getAfter_page();
                        Log.e("After ", infoRedditList.get(0).getAfter_page());
                        for (int i = 0 ; i<infoRedditList.size(); i++){
                            List<TopReddit> exp = infoRedditList.get(i).getTopReddit();
                            mRecycler.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                            mRecycler.setItemAnimator(new DefaultItemAnimator());
                            adapter = new RedditRecyclerAdapter(MainActivity.this,exp);
                            mRecycler.setAdapter(adapter);
                        }
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    public String getDateFromUTCTimestamp(long mTimestamp, String mDateFormate) {
        String date = null;
        try {
            Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
            cal.setTimeInMillis(mTimestamp * 1000L);
            date = DateFormat.format(mDateFormate, cal.getTimeInMillis()).toString();

            SimpleDateFormat formatter = new SimpleDateFormat(mDateFormate);
            formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date value = formatter.parse(date);

            SimpleDateFormat dateFormatter = new SimpleDateFormat(mDateFormate);
            dateFormatter.setTimeZone(TimeZone.getDefault());
            date = dateFormatter.format(value);
            return date;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }
}
