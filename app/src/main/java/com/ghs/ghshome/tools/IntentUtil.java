package com.ghs.ghshome.tools;

import android.app.Activity;
import android.content.Intent;
import android.os.Parcelable;

/**
 * Author:wang_sir
 * Time:2019/8/15 11:28
 * Description:This is IntentUtil
 */
public class IntentUtil {
    public  String INTENT_PARCELABLE_KEY = "intent_key";//intent跳转的key
    public  String INTENT_INT_KEY = "intent_int_key";//intent跳转的key
    public   int REQUEST_CODE = 888;//intent跳转的key
    public  int RESULT_CODE = 889;//intent跳转的key
    public String INTENT_KEY = "intent_string_key";//intent跳转的key

    public static IntentUtil getInstance() {
        return IntentUtilHolder.instatnce;
    }

    private static class IntentUtilHolder {
        private static IntentUtil instatnce = new IntentUtil();
    }

    /**
     * 无参数启动activity
     *
     * @param cls
     */
    public  void startActivityWithoutData( Activity activity, Class<?> cls) {
        Intent intent = new Intent(activity, cls);
        activity.startActivityForResult(intent, REQUEST_CODE);
    }
    /**
     * 启动activity
     *
     * @param value
     * @param cls
     */
    public  void startActivityWithParcelableData(Parcelable value, Activity activity, Class<?> cls) {

        Intent intent = new Intent(activity, cls);
        intent.putExtra(INTENT_PARCELABLE_KEY, value);
        activity.startActivityForResult(intent, REQUEST_CODE);

    }

    /**
     * 获取传过来的Parcelable实体类
     *
     * @return
     */
    public  Parcelable getIntentParcelableData(Activity activity) {
        Parcelable value = null;
        Intent intent = activity.getIntent();
        if (intent != null) {
            value = intent.getParcelableExtra(INTENT_PARCELABLE_KEY);
        }
        return value;
    }

    /**
     * 启动activity
     *
     * @param value
     * @param cls
     */
    public  void startActivityWithIntData(int value, Activity activity, Class<?> cls) {
        Intent intent = new Intent(activity, cls);
        intent.putExtra(INTENT_INT_KEY, value);
        activity.startActivityForResult(intent, REQUEST_CODE);

    }

    /**
     * 获取传过来的Parcelable实体类
     *
     * @return
     */
    public  int getIntentIntData(Activity activity) {
        int value = -1;
        Intent intent = activity.getIntent();
        if (intent != null) {
            value = intent.getIntExtra(INTENT_INT_KEY, -1);
        }
        return value;
    }

    /**
     * setResult
     */
    public  void  setIntentResult(Activity activity,String content){
        Intent  intent = new Intent();
        intent.putExtra(INTENT_KEY,content);
        activity.setResult(RESULT_CODE,intent);
        activity.finish();
    }
    /**
     * setResult
     */
    public  void  setIntentResultWithParcelableData(Activity activity,Parcelable value){
        Intent  intent = new Intent();
        intent.putExtra(INTENT_KEY,value);
        activity.setResult(RESULT_CODE,intent);
        activity.finish();
    }
}
