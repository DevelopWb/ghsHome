package com.ghs.ghshome.models.discover.adapter;

import android.os.Bundle;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.ghs.ghshome.R;
import com.ghs.ghshome.base.BaseActivity;
import com.ghs.ghshome.bean.OfficialPlBean;
import com.ghs.ghshome.models.discover.DiscoverContract;
import com.ghs.ghshome.models.discover.DiscoverPresenter;
import com.ghs.ghshome.tools.Contract;

/**
 * 官方资讯详情
 */

public class OfficialPaticularsActivity extends BaseActivity<DiscoverContract.IDiscoverView, DiscoverPresenter> implements DiscoverContract.IDiscoverView {

    /**
     * 标题
     */
    private WebView mOfficaialPlWv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    protected void setLayout() {
        setContentView(R.layout.activity_official_paticulars);
        initView();
    }

    @Override
    public DiscoverPresenter creatPresenter() {
        return new DiscoverPresenter();
    }


    @Override
    public void getDate() {
        int official_pl_id = getIntent().getIntExtra("OFFICIAL_PL_ID", -1);
        if(official_pl_id!= -1){

            getPresenter().getOfficialParticulars(official_pl_id,DiscoverContract.AC_LIST_OFFICAIAL_OL);

        }

    }

    @Override
    public void startLoading(String tag) {

    }

    @Override
    public void stopLoading(String tag) {

    }

    @Override
    public void updateView(Object o, String tag) {

        OfficialPlBean officialPlBean = (OfficialPlBean) o;
        OfficialPlBean.DataBean plBeanData = officialPlBean.getData();
        if(plBeanData!= null){
            initActionBar(plBeanData.getTitle(),"  ");
            WebSettings ws = mOfficaialPlWv.getSettings();
            ws.setJavaScriptEnabled(true);//开启JavaScript支持
            ws.setAppCacheEnabled(false);
            ws.setDomStorageEnabled(true);
            ws.setDefaultTextEncodingName("utf-8");
            String url = plBeanData.getInfoUrl();
            Log.i("TAG",url);
                showProgressDialog();
                mOfficaialPlWv.loadUrl(Contract.ImageBaseURL+ url);

        }

    }

    @Override
    public void onError(String tag) {
     showToast(tag);
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
}
