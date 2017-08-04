package com.redditpoc.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.UrlQuerySanitizer;

import com.redditpoc.R;

public class Utils {


    public static String getParam(String param, String url) {
        UrlQuerySanitizer sanitzer = new UrlQuerySanitizer(url);
        return sanitzer.getValue(param);
    }

    public static boolean hasInternet(Context context) {
        ConnectivityManager connMgr = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

    public static void shareIntent(Activity activity, String text) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, text + "\n" + activity.getString(R.string.app_name));
        sendIntent.setType(Constants.TEXT_PLAIN);
        activity.startActivity(sendIntent);
    }
}