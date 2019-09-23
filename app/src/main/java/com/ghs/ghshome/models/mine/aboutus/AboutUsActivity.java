package com.ghs.ghshome.models.mine.aboutus;

import android.os.Bundle;

import com.ghs.ghshome.R;
import com.ghs.ghshome.base.BaseActivity;
import com.ghs.ghshome.base.BasePresent;
import com.ghs.ghshome.custom.MineEditCustomView;
import com.ghs.ghshome.tools.PubUtil;

public class AboutUsActivity extends BaseActivity {

    private MineEditCustomView mAboutUsCurrentVersionMcv;
    private MineEditCustomView mAboutUsEmailMcv;
    private MineEditCustomView mAboutUsNetAddressMcv;
    private MineEditCustomView mAboutUsPublicNoMcv;
    private MineEditCustomView mAboutUsCustomerServiceMcv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public void getDate() {

    }

    @Override
    public void initLayoutView() {
        initView();
        initActionBar("关于我们","");

    }

    @Override
    public void setLayout() {
        setContentView(R.layout.activity_about_us);
    }


    @Override
    public BasePresent creatPresenter() {
        return null;
    }


    private void initView() {
        mAboutUsCurrentVersionMcv = (MineEditCustomView) findViewById(R.id.aboutUs_current_version_mcv);
        mAboutUsCurrentVersionMcv.gettitleBarRightTv().setText(PubUtil.getAPPVersion(this));
        mAboutUsEmailMcv = (MineEditCustomView) findViewById(R.id.aboutUs_email_mcv);
        mAboutUsNetAddressMcv = (MineEditCustomView) findViewById(R.id.aboutUs_net_address_mcv);
        mAboutUsPublicNoMcv = (MineEditCustomView) findViewById(R.id.aboutUs_public_no_mcv);
        mAboutUsCustomerServiceMcv = (MineEditCustomView) findViewById(R.id.aboutUs_customer_service_mcv);
    }

}
