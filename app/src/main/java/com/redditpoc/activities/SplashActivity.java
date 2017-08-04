package com.redditpoc.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.redditpoc.R;
import com.redditpoc.Utils.ApiReddit;
import com.redditpoc.Model.InfoReddit;
import com.redditpoc.Model.ManagerReddit;
import com.redditpoc.Model.TopReddit;

import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by levaa on 6/8/2017.
 */

public class SplashActivity extends AppCompatActivity {
    private ApiReddit apiReddit;
    private List<InfoReddit> infoRedditList;
    private ManagerReddit managerReddit;
    private List<TopReddit> exp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acivity_splash);
        apiReddit = new ApiReddit();
        infoRedditList = new ArrayList<InfoReddit>();
        exp = new ArrayList<TopReddit>();
        managerReddit = new ManagerReddit(this);

        getSupportActionBar().hide();

        apiReddit.getReddit(null ,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, JSONObject response) {
                try {
                    JSONObject dataReddit = response.getJSONObject("data");
                    infoRedditList.add(new InfoReddit(dataReddit));
                    if (infoRedditList.size() != 0){
                        for (int i = 0 ; i<infoRedditList.size(); i++){
                            exp = infoRedditList.get(i).getTopReddit();
                            managerReddit.setGroups(exp);
                            managerReddit.saveAll();
                            if (exp.size() !=0) {
                                gotoHome();
                            }
                        }
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

    }

    private void gotoHome(){
        Intent intent = new Intent(SplashActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
