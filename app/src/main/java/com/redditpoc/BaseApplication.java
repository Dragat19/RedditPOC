package com.redditpoc;

import android.app.Application;

import com.redditpoc.controllers.RequestController;


public class BaseApplication extends Application {

    private static BaseApplication instance;
    public static RequestController requestController;

    public static BaseApplication getInstance() {
        if (instance == null) {
            instance = new BaseApplication();
        }
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        requestController = RequestController.getInstance(this);
    }

}
