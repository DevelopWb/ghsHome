package com.ghs.ghshome.tools;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import java.util.List;


public class ActivityLifecycleListener implements Application.ActivityLifecycleCallbacks {

    private int refCount = 0;

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

    }

    @Override
    public void onActivityStarted(Activity activity) {
        SpManager.getInstance(activity, "app_state").put("state", true, "BOOLEAN");
        boolean s = (boolean) SpManager.getInstance(activity, "app_state").get("state", "BOOLEAN");
        Log.d("TAG", "当前程序处在前台" + s);

        refCount++;
    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {
        refCount--;
        if (refCount == 0) {
            SpManager.getInstance(activity, "app_state").put("state", false, "BOOLEAN");
            boolean s = (boolean) SpManager.getInstance(activity, "app_state").get("state", "BOOLEAN");
            Log.d("TAG", "当前程序处在后台" + s);

        }
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }

    public boolean isProessRunning(Context context, String proessName) {

        android.app.ActivityManager am = (android.app.ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<android.app.ActivityManager.RunningAppProcessInfo> lists = am.getRunningAppProcesses();

        for (ActivityManager.RunningAppProcessInfo info : lists) {
            if (info.processName.equals(proessName)) {
                return true;
            }
        }
        return false;
    }
}
