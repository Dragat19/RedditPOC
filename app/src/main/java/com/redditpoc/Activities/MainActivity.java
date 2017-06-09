package com.redditpoc.Activities;
import android.os.Parcelable;
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
import com.redditpoc.Adapter.AdapterPaginator;
import com.redditpoc.ApiReddit;
import com.redditpoc.Model.InfoReddit;
import com.redditpoc.Model.ManagerReddit;
import com.redditpoc.Model.TopReddit;
import com.redditpoc.R;
import com.redditpoc.Adapter.RedditRecyclerAdapter;

import org.json.JSONObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecycler;
    private RedditRecyclerAdapter adapter;
    private Button btnNext,btnPrev;
    private AdapterPaginator paginator = new AdapterPaginator();
    private int totalPages = AdapterPaginator.TOTAL_ITEMS / AdapterPaginator.ITEMS_PAGE;
    private int currentPage = 0;
    private ManagerReddit managerReddit = new ManagerReddit(this);
    private List<TopReddit> dataReddit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecycler = (RecyclerView)findViewById(R.id.reddit_recycler);
        btnNext = (Button) findViewById(R.id.Next);
        btnPrev = (Button) findViewById(R.id.Prev);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_reddit_white);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        dataReddit = managerReddit.loadAll();
        if (dataReddit.size() != 0) {
            mRecycler.setLayoutManager(new LinearLayoutManager(MainActivity.this));
            mRecycler.setItemAnimator(new DefaultItemAnimator());
            adapter = new RedditRecyclerAdapter(MainActivity.this,dataReddit,paginator.generatePage(currentPage));
            mRecycler.setAdapter(adapter);
        }

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentPage += 1;
                adapter = new RedditRecyclerAdapter(MainActivity.this, dataReddit,paginator.generatePage(currentPage));
                mRecycler.setAdapter(adapter);
                ToogleButton();
            }
        });

        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentPage -= 1;
                adapter = new RedditRecyclerAdapter(MainActivity.this, dataReddit,paginator.generatePage(currentPage));
                mRecycler.setAdapter(adapter);
                ToogleButton();
            }
        });
    }

    private void ToogleButton(){
        if (currentPage == totalPages) {
            btnNext.setEnabled(false);
            btnPrev.setEnabled(true);
            btnNext.setTextColor(R.color.black);
            btnPrev.setTextColor(R.color.colorAccent);
        }else {
            if (currentPage == 0) {
                btnNext.setEnabled(true);
                btnPrev.setEnabled(false);
                btnNext.setTextColor(R.color.colorAccent);
                btnPrev.setTextColor(R.color.black);
            }else {
                if (currentPage >=1 && currentPage <=totalPages) {
                    btnNext.setEnabled(true);
                    btnPrev.setEnabled(true);
                }
            }
        }
    }







}
