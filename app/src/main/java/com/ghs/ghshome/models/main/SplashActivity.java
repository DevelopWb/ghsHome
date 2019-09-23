package com.ghs.ghshome.models.main;

import android.content.Intent;
import android.os.Bundle;

import com.ghs.ghshome.base.BaseActivity;
import com.ghs.ghshome.base.BasePresent;
import com.ghs.ghshome.models.login.LoginActivity;
import com.ghs.ghshome.models.checkIdentity.SelectVillageActivity;
import com.ghs.ghshome.tools.HawkProperty;
import com.ghs.ghshome.tools.PubUtil;
import com.orhanobut.hawk.Hawk;

/**
 * created by tobato
 * created date 2019/7/23 11:43.
 * application   启动页
 */
public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initSplashActivity();
    }

    @Override
    protected void setLayout() {

    }

    /**
     * 查看身份有没有认证，
     * 认证状态：
     * 无认证关系：0
     * 待认证：1
     * 认证通过 2
     * 认证不通过 3
     */
    private void initSplashActivity() {
        Intent intent = null;
        if (Hawk.contains(HawkProperty.LOGIN_BEAN)) {
            //页面的跳转
            if (Hawk.contains(mUserInfoUtil.getCurrentVillageKey())) {
                //已经登录  认证过 认证
                intent = new Intent(SplashActivity.this, MainActivity.class);
            } else {
                //已经登录 没有认证  跳转到选择小区的界面
                intent = new Intent(SplashActivity.this, SelectVillageActivity.class);
            }


        } else {
            PubUtil.LOGIN_ENTER = 3;
            intent = new Intent(SplashActivity.this, LoginActivity.class);

        }
        startActivity(intent);
        finish();
    }

    @Override
    public BasePresent creatPresenter() {
        return null;
    }

    @Override
    public void getDate() {

    }

    @Override
    public void onRefuseGivePromission() {
//        initSplashActivity();
        super.onRefuseGivePromission();
    }
}