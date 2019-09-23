package com.ghs.ghshome.models.push.jcush;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.util.Log;

import com.huawei.hms.api.HuaweiApiClient;
import com.huawei.hms.support.api.client.PendingResult;
import com.huawei.hms.support.api.client.ResultCallback;
import com.huawei.hms.support.api.push.HuaweiPush;
import com.huawei.hms.support.api.push.TokenResult;

public class HwPushManager {
    private String TAG = "HWPUSH";
    private static volatile HwPushManager instance = null;
    private Context context;
    private HuaweiApiClient client;

    private HwPushManager() {

    }

    public static HwPushManager getInstance() {
        if (instance == null) {
            synchronized (HwPushManager.class) {
                if (instance == null) {
                    instance = new HwPushManager();
                }
            }
        }
        return instance;
    }

    /**
     * 初始化
     * @param context
     */
    public void init(final Activity context) {
        this.context = context.getApplicationContext();
        HuaweiApiClient.Builder hw = new HuaweiApiClient.Builder(context);
        client = hw.addApi(HuaweiPush.PUSH_API).addConnectionCallbacks(new HuaweiApiClient.ConnectionCallbacks() {
            @Override
            public void onConnected() {

                Log.i(TAG, "连接成功");
                PendingResult<TokenResult> tokenResult = HuaweiPush.HuaweiPushApi.getToken(client);

                tokenResult.setResultCallback(new ResultCallback<TokenResult>() {

                    @Override

                    public void onResult(TokenResult result) {
                        Log.i(TAG,"获取token"+result.getTokenRes());
                    }

                });



            }

            @Override
            public void onConnectionSuspended(int cause) {
                Log.i(TAG, "连接断开");
            }
        }).build();

        client.connect(context);
        new Thread(new Runnable() {
            @Override
            public void run() {
                HuaweiPush.HuaweiPushApi.getPushState(client);
            }
        }){}.start();




    }


    public  void stop() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) {
            return;
        }
        client = null;

    }

}
