package com.redditpoc.Utils;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

public class PermissionHelper {
    public static final int PERMISSIONS_REQUEST = 100;
    private static final String[] ALL = {
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_CONTACTS,
            Manifest.permission.CALL_PHONE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    public static boolean needsToRequestWriteExternalPermissions(Activity activity) {
        if (Build.VERSION.SDK_INT < 23) {
            return false;
        } else {
            return ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED;
        }
    }

    public static void requestWriteExternalPermission(Activity activity) {
        ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSIONS_REQUEST);
    }

    public static boolean needsToRequestCameraPermissions(Activity activity) {
        if (Build.VERSION.SDK_INT < 23) {
            return false;
        } else {
            return ContextCompat.checkSelfPermission(activity, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED;
        }
    }

    public static void requestCameraPermission(Activity activity) {
        ActivityCompat.requestPermissions(activity, new String[] {Manifest.permission.CAMERA}, PERMISSIONS_REQUEST);
    }
}
