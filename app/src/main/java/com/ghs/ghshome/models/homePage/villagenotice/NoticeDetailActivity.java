package com.ghs.ghshome.models.homePage.villagenotice;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.ghs.ghshome.R;
import com.ghs.ghshome.base.BaseActivity;
import com.ghs.ghshome.base.network.RequestStatus;
import com.ghs.ghshome.bean.VillageNoticeDetailBean;
import com.ghs.ghshome.models.homePage.HomePageContract;
import com.ghs.ghshome.tools.ActivityResultManager;
import com.ghs.ghshome.tools.Contract;
import com.ghs.ghshome.tools.StrUtils;

/**
 * created by tobato
 * created date 2019/6/10 17:39.
 * application   消息详情
 */
public class NoticeDetailActivity extends BaseActivity<VillageNoticeContract.IVillageNoticeView, VillageNoticePresent> implements VillageNoticeContract.IVillageNoticeView {

    private WebView mVillageNoticeDetailWv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public void getDate() {
        Intent intent = getIntent();
        if (intent != null) {
            String noticeType = intent.getStringExtra(HomePageContract.NOTICI_TYPE);
            if (StrUtils.isStringValueOk(noticeType)) {
                int noticeId = intent.getIntExtra(HomePageContract.NOTICI_ID, 0);
                switch (noticeType) {
                    case HomePageContract.NOTICI_VILLAGE:
                        getPresenter().readVillageNotice(noticeId);
                        break;
                    case HomePageContract.NOTICI_REPAIR:
                        break;
                    case HomePageContract.NOTICI_SUGGESTION:
                        break;
                    case HomePageContract.NOTICI_BILL:
                        break;
                    case HomePageContract.NOTICI_VISITOR:
                        break;
                    case HomePageContract.NOTICI_GOODS_INTO:
                        break;
                    case HomePageContract.NOTICI_DECORATION:
                        break;
                    case HomePageContract.NOTICI_SERVICE_WORK:
                        break;
                    case HomePageContract.OFFICICAL_NOTICE:
                        getPresenter().readOfficialNotice(mUserInfoUtil.getUserId(), noticeId, HomePageContract.READ_OFFICIAL_NOTICE);
                        break;
                    case HomePageContract.NOTICI_VILLAGRE_ACTIVITY:
                        break;
                    default:
                        break;
                }
            }

        }
    }

    @Override
    public void initLayoutView() {

    }

    @Override
    public void setLayout() {
        setContentView(R.layout.activity_village_notice_detail);
        initView();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        setResult(ActivityResultManager.VILLIAGE_NOTICE_DETAIL);
    }


    @Override
    public VillageNoticePresent creatPresenter() {
        return new VillageNoticePresent();
    }

    private void initView() {
        mVillageNoticeDetailWv = (WebView) findViewById(R.id.webview);
        mVillageNoticeDetailWv.setWebChromeClient(new WebChromeClient() {
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
    public void startLoading(String tag) {

    }

    @Override
    public void stopLoading(String tag) {

    }

    @Override
    public void updateView(Object o, String tag) {
        switch (tag) {
            case RequestStatus.UPDATE:
                getNoticeInfo((VillageNoticeDetailBean) o);
                break;
            case HomePageContract.READ_OFFICIAL_NOTICE:
                //小区公告消息
                getNoticeInfo((VillageNoticeDetailBean) o);
                break;
            default:
                break;
        }

    }

    /**
     * 获取公告信息
     *
     * @param o
     */
    private void getNoticeInfo(VillageNoticeDetailBean o) {
        //小区公告消息
        VillageNoticeDetailBean bean = o;
        if (bean != null) {
            VillageNoticeDetailBean.DataBean dataBean = bean.getData();
            if (dataBean != null) {
                initActionBar(dataBean.getTitle(), null);
                WebSettings ws = mVillageNoticeDetailWv.getSettings();
                ws.setJavaScriptEnabled(true);//开启JavaScript支持
                ws.setAppCacheEnabled(false);
                ws.setDomStorageEnabled(true);
                String url = dataBean.getNoticeUrl();
                showProgressDialog();
                mVillageNoticeDetailWv.loadUrl(Contract.ImageBaseURL + url);

            }
        }
    }

    @Override
    public void onError(String tag) {
        initActionBar("", null);
        showToast(tag);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mVillageNoticeDetailWv.destroy();
        mVillageNoticeDetailWv = null;
    }
}
