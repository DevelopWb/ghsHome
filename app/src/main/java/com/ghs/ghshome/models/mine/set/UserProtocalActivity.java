package com.ghs.ghshome.models.mine.set;

import android.os.Bundle;
import android.webkit.WebView;

import com.ghs.ghshome.R;
import com.ghs.ghshome.base.BaseActivity;
import com.ghs.ghshome.base.BasePresent;
import com.ghs.ghshome.tools.Contract;

public class UserProtocalActivity extends BaseActivity {

    private WebView mUserProtocalWv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void getDate() {
        mUserProtocalWv.loadUrl(Contract.USER_PROTOCAL_URL);
    }

    @Override
    public void initLayoutView() {
        initView();
        initActionBar("用户协议",null);

    }

    @Override
    public void setLayout() {
        setContentView(R.layout.activity_user_protocal);

    }


    @Override
    public BasePresent creatPresenter() {
        return null;
    }

    private void initView() {
        mUserProtocalWv = (WebView) findViewById(R.id.user_protocal_wv);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUserProtocalWv.destroy();
        mUserProtocalWv=null;
    }

}
