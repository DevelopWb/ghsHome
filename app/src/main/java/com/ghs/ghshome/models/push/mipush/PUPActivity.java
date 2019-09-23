package com.ghs.ghshome.models.push.mipush;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.alibaba.sdk.android.push.AndroidPopupActivity;
import com.ghs.ghshome.R;
import com.ghs.ghshome.models.main.SplashActivity;

import java.util.Map;

public class PUPActivity extends AndroidPopupActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pup);
    }

    @Override
    protected void onSysNoticeOpened(String s, String s1, Map<String, String> map) {
        Log.d("MiPushRegister", "辅助弹框" + s + "-----" + s1 + "----" + map.get("noticeType"));
        String noticeType = map.get("noticeType");
        Intent intent = new Intent(this, SplashActivity.class);
        intent.putExtra("noticeType",noticeType);
        startActivity(intent);
        finish();


    }
}
