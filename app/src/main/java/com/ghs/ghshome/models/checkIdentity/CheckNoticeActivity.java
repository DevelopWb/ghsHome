package com.ghs.ghshome.models.checkIdentity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ghs.ghshome.R;
import com.ghs.ghshome.base.BaseActivity;
import com.ghs.ghshome.bean.PropertyTelBean;
import com.ghs.ghshome.models.homePage.HomePageContract;
import com.ghs.ghshome.tools.IntentUtil;
import com.ghs.ghshome.tools.StrUtils;

/**
 * created by tobato
 * created date 2019/9/18 15:42.
 * application   认证提示
 */
public class CheckNoticeActivity extends BaseActivity<CheckIdentityContract.ICheckIdentityView, CheckIdentityPresent> implements CheckIdentityContract.ICheckIdentityView,View.OnClickListener {

    private TextView mCheckNoticeContentTv;
    /**
     * 联系物业
     */
    private TextView mCheckNoticeBtTv;
    private String telNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void setLayout() {
        setContentView(R.layout.activity_check_notice);
        initView();
    }

    @Override
    public CheckIdentityPresent creatPresenter() {
        return new CheckIdentityPresent();
    }
    @Override
    public void getDate() {
        getPresenter().customerServerTel(HomePageContract.CUSTOMER_SERVER_TEL);
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
            case HomePageContract.CUSTOMER_SERVER_TEL:
                PropertyTelBean telBean = (PropertyTelBean) o;
                if (telBean != null) {
                    PropertyTelBean.DataBean dataBean = telBean.getData();
                    telNum = dataBean.getMobile();
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onError(String tag) {

    }

    private void initView() {
        mCheckNoticeContentTv = (TextView) findViewById(R.id.check_notice_content_tv);
        mCheckNoticeBtTv = (TextView) findViewById(R.id.check_notice_bt_tv);
        mCheckNoticeBtTv.setOnClickListener(this);
        //2.认证不通过、3.待认证
        if (2 == mUserInfoUtil.getCheckStatus()) {
            mCheckNoticeBtTv.setText("重新认证");
            String content = getString(R.string.check_wait_notice);
            StrUtils.setTextPartColor(mCheckNoticeContentTv, content, content.indexOf("“"), content.lastIndexOf("”") + 1, "#D0021B");
        } else {
            mCheckNoticeBtTv.setText("联系物业");
            String content = getString(R.string.check_reject_notice);
            StrUtils.setTextPartColor(mCheckNoticeContentTv, content, content.indexOf("“"), content.lastIndexOf("”") + 1, "#D0021B");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.check_notice_bt_tv:
                //2.认证不通过、3.待认证
                if (2 == mUserInfoUtil.getCheckStatus()) {
                    IntentUtil.getInstance().startActivityWithParcelableData(null,this,CheckIdentityActivity.class);
                } else {
                    makeAPhoneCall(telNum);
                }
                break;
        }
    }
}
