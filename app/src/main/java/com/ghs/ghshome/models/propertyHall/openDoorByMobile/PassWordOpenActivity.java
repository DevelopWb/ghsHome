package com.ghs.ghshome.models.propertyHall.openDoorByMobile;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ghs.ghshome.R;
import com.ghs.ghshome.base.BaseActivity;
import com.ghs.ghshome.base.network.RequestStatus;
import com.ghs.ghshome.models.propertyHall.openDoorByMobile.remoteopen.RemoteOpenContract;
import com.ghs.ghshome.models.propertyHall.openDoorByMobile.remoteopen.RemoteOpenPresent;
import com.ghs.ghshome.tools.StrUtils;
import com.ghs.ghshome.tools.UserInfoUtil;

public class PassWordOpenActivity extends BaseActivity<RemoteOpenContract.IRemoteOpenView, RemoteOpenPresent> implements RemoteOpenContract.IRemoteOpenView, RequestStatus {


    private TextView password;
    /**
     * 褐石公园小区
     */
    private TextView mBlueKeyVillageName;
    private ImageView mHeaderLeftIv;
    /**
     * 设置
     */
    private TextView mHeaderTitleTv;
    private ImageView mKeyLockExplain;

    @Override
    public void setLayout() {
        setContentView(R.layout.activity_pass_word_key);
    }

    @Override
    public void initLayoutView() {
//        initActionBar("密码开门", "");

        password = findViewById(R.id.password);
        initView();

    }

    @Override
    public RemoteOpenPresent creatPresenter() {
        return new RemoteOpenPresent();
    }

    @Override
    public void getDate() {
            getPresenter().getPassword(UserInfoUtil.getInstance().getRoomUserId(), RemoteOpenContract.GET_PWD);
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
            case RemoteOpenContract.GET_PWD:
                String pwd = (String) o;
                if (StrUtils.isStringValueOk(pwd)) {
                    password.setText(pwd);
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onStart(String tag) {

    }

    @Override
    public void onSuccess(Object o, String tag) {

    }

    @Override
    public void onError(String tag) {
        showToast(tag);
    }



    public void initView() {
        mBlueKeyVillageName = (TextView) findViewById(R.id.blue_key_village_name);
        mBlueKeyVillageName.setText(UserInfoUtil.getInstance().getVillageName());
        mHeaderLeftIv = (ImageView) findViewById(R.id.header_left_iv);
        mHeaderLeftIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        mHeaderTitleTv = (TextView) findViewById(R.id.header_title_tv);
        mHeaderTitleTv.setText("密码开门");
        mKeyLockExplain = (ImageView) findViewById(R.id.key_lock_explain);
        mKeyLockExplain.setVisibility(View.GONE);
    }

}
