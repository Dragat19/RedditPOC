package com.redditpoc.Activities;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.redditpoc.ApiReddit;
import com.redditpoc.Model.InfoReddit;
import com.redditpoc.Model.TopReddit;
import com.redditpoc.R;
import com.redditpoc.RedditRecyclerAdapter;

import org.json.JSONException;
import org.json.JSONObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {
    private long UTC_TIMEZONE=1496636570;
    private String OUTPUT_DATE_FORMATE="dd-MM-yyyy - hh:mm a";
    private List<InfoReddit> infoRedditList;
    private RecyclerView mRecycler;
    private RedditRecyclerAdapter adapter;
    private ApiReddit apiReddit;
    private Button btnNext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecycler = (RecyclerView)findViewById(R.id.reddit_recycler);
        btnNext = (Button) findViewById(R.id.Next);
        infoRedditList = new ArrayList<InfoReddit>();
        apiReddit = new ApiReddit();

        mRecycler.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        mRecycler.setItemAnimator(new DefaultItemAnimator());


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
                            adapter = new RedditRecyclerAdapter(MainActivity.this,exp);
                            mRecycler.setAdapter(adapter);
                        }
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


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
