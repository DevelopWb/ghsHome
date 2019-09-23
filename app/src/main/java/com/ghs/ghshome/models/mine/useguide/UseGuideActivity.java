package com.ghs.ghshome.models.mine.useguide;

import android.content.Intent;
import android.view.View;

import com.ghs.ghshome.R;
import com.ghs.ghshome.base.BaseActivity;
import com.ghs.ghshome.base.BasePresent;
import com.ghs.ghshome.custom.MineCustomView;
import com.ghs.ghshome.tools.ActivityResultManager;
/**
 * created by tobato
 * created date 2019/6/18 11:32.
 * application   用户指南
 */
public class UseGuideActivity extends BaseActivity implements View.OnClickListener {

    private MineCustomView mUseGuideFaceKey;
    private MineCustomView mUseGuidePhoneKey;
    private MineCustomView mUseGuideAddNewRoom;
    private MineCustomView mUseGuideAuthorization;
    private MineCustomView mUseGuideBill;



    @Override
    public void getDate() {

    }

    @Override
    public void initLayoutView() {
        initView();
        initActionBar("使用指南", "");

    }

    @Override
    public void setLayout() {
        setContentView(R.layout.activity_use_guide);
    }


    @Override
    public BasePresent creatPresenter() {
        return null;
    }


    private void initView() {
        mUseGuideFaceKey = (MineCustomView) findViewById(R.id.use_guide_face_key);
        mUseGuideFaceKey.setOnClickListener(this);
        mUseGuidePhoneKey = (MineCustomView) findViewById(R.id.use_guide_phone_key);
        mUseGuidePhoneKey.setOnClickListener(this);
        mUseGuideAddNewRoom = (MineCustomView) findViewById(R.id.use_guide_add_newRoom);
        mUseGuideAddNewRoom.setOnClickListener(this);
        mUseGuideAuthorization = (MineCustomView) findViewById(R.id.use_guide_authorization);
        mUseGuideAuthorization.setOnClickListener(this);
        mUseGuideBill = (MineCustomView) findViewById(R.id.use_guide_bill);
        mUseGuideBill.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.use_guide_face_key:
                startActivity(new Intent(UseGuideActivity.this, UseGuideDetailActivity.class).putExtra(ActivityResultManager.USER_GUID_TAG,ActivityResultManager.OPEN_BY_FACE));
                break;
            case R.id.use_guide_phone_key:
                startActivity(new Intent(UseGuideActivity.this, UseGuideDetailActivity.class).putExtra(ActivityResultManager.USER_GUID_TAG,ActivityResultManager.OPEN_BY_MOBILE));
                break;
            case R.id.use_guide_add_newRoom:
                startActivity(new Intent(UseGuideActivity.this, UseGuideDetailActivity.class).putExtra(ActivityResultManager.USER_GUID_TAG,ActivityResultManager.ADD_NEW_HOUSE));

                break;
            case R.id.use_guide_authorization:
                startActivity(new Intent(UseGuideActivity.this, UseGuideDetailActivity.class).putExtra(ActivityResultManager.USER_GUID_TAG,ActivityResultManager.AUTHORIZATION_MANGER));

                break;
            case R.id.use_guide_bill:
                startActivity(new Intent(UseGuideActivity.this, UseGuideDetailActivity.class).putExtra(ActivityResultManager.USER_GUID_TAG,ActivityResultManager.HOUSE_BILL));
                break;
        }
    }
}
