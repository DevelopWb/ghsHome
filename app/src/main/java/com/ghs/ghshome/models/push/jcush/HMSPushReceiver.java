package com.ghs.ghshome.models.push.jcush;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.ghs.ghshome.R;
import com.ghs.ghshome.models.justtalk.JCManager;
import com.ghs.ghshome.models.main.SplashActivity;
import com.ghs.ghshome.models.push.ScreensUtils;
import com.ghs.ghshome.models.push.hwpush.NotificationClickReceiver;
import com.huawei.hms.support.api.push.PushReceiver;
import com.juphoon.cloud.JCPushTemplate;


/**
 * Created by juphoon on 2017/11/9.
 */

public class HMSPushReceiver extends PushReceiver {
    @Override
    public void onEvent(Context context, Event event, Bundle bundle) {
        super.onEvent(context, event, bundle);

    }

    @Override
    public void onToken(Context context, String s, Bundle bundle) {
        super.onToken(context, s, bundle);
        Log.d("HMWA", s);
        JCPushTemplate pushInfo = new JCPushTemplate();
        pushInfo.initWithHmsPush("100374089", s);
        boolean b = JCManager.getInstance().push.addPushInfo(pushInfo);
        pushInfo.initWithCall(JCPushTemplate.HUAWEI, JCManager.getInstance().client.getUserId(), "呼叫", "1");
        boolean b1 = JCManager.getInstance().push.addPushInfo(pushInfo);
        pushInfo.initWithText(JCPushTemplate.HUAWEI, JCManager.getInstance().client.getUserId(), "Text", "消息", "1");
        boolean b2 = JCManager.getInstance().push.addPushInfo(pushInfo);
        Log.d("HMWA", b + "----" + b1 + "----" + b2 + "");

    }

    @Override
    public boolean onPushMsg(Context context, byte[] bytes, Bundle bundle) {
        return super.onPushMsg(context, bytes, bundle);
    }

    @Override
    public void onPushMsg(Context context, byte[] bytes, String s) {
        //来消息亮屏
        ScreensUtils.isScree(context);
        Log.d("PUSH", "接收到华为的透传消息");
        showNotifictionIcon(context);

        super.onPushMsg(context, bytes, s);
    }

    @Override
    public void onPushState(Context context, boolean b) {
        super.onPushState(context, b);
    }

    @Override
    public void onToken(Context context, String s) {
        super.onToken(context, s);
    }


    public static void showNotifictionIcon(Context context) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        Intent intent = new Intent(context, SplashActivity.class);//将要跳转的界面
        //Intent intent = new Intent();//只显示通知，无页面跳转
        builder.setAutoCancel(true);//点击后消失
        builder.setSmallIcon(R.mipmap.ghshome_icon);//设置通知栏消息标题的头像
        builder.setDefaults(NotificationCompat.DEFAULT_SOUND);//设置通知铃声
        builder.setTicker("状态栏显示的文字");
        builder.setContentTitle("通知");
        builder.setContentText("您有一则新通话，赶快接听吧");
        //利用PendingIntent来包装我们的intent对象,使其延迟跳转
        int id = (int) System.currentTimeMillis() / 10000;
        Intent intent1 = new Intent(context, NotificationClickReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent1, 0);
        builder.setContentIntent(pendingIntent);
        NotificationManager manager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);

        manager.notify(0, builder.build());

    }


}
