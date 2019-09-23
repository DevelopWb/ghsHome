package com.ghs.ghshome.models.discover.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.ghs.ghshome.R;
import com.ghs.ghshome.base.BaseActivity;
import com.ghs.ghshome.base.BasePresent;

import static android.view.KeyEvent.KEYCODE_BACK;

public class OfficaialWebActivity extends BaseActivity {

    private WebView mOfficaialPlWv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_officaial_web);
        initView();
        initActionBar("详情", "");
        Intent intent = getIntent();
        String web_url = intent.getStringExtra("web_url");
        WebSettings ws = mOfficaialPlWv.getSettings();
        ws.setJavaScriptEnabled(true);//开启JavaScript支持
        ws.setAppCacheEnabled(false);
        ws.setDomStorageEnabled(true);
        ws.setDefaultTextEncodingName("utf-8");
            showProgressDialog();
            mOfficaialPlWv.loadUrl(web_url);
            mOfficaialPlWv.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    view.loadUrl(url);
                    return super.shouldOverrideUrlLoading(view, url);
                }
            });

    }

    @Override
    protected void setLayout() {

    }

    @Override
    public BasePresent creatPresenter() {
        return null;
    }

    @Override
    public void getDate() {

    }

    private void initView() {
        mOfficaialPlWv = (WebView) findViewById(R.id.officaial_pl_wv);
        mOfficaialPlWv.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int progress) {
                //当进度走到100的时候做自己的操作，我这边是弹出dialog
                if (progress == 100) {
                    stopProgressDialog();
                }
            }
        });

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mOfficaialPlWv.destroy();
        mOfficaialPlWv = null;
    }



    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KEYCODE_BACK) && mOfficaialPlWv.canGoBack()) {
            //是否可以后退
                mOfficaialPlWv.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
