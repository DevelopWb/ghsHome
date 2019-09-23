package com.ghs.ghshome.models.mine.useguide;

import android.content.Intent;
import android.os.Bundle;

import com.ghs.ghshome.R;
import com.ghs.ghshome.base.BaseActivity;
import com.ghs.ghshome.base.BasePresent;
import com.ghs.ghshome.custom.photoview.PhotoView;
import com.ghs.ghshome.tools.ActivityResultManager;

public class UseGuideDetailActivity extends BaseActivity {

    private PhotoView mPhotoview;
    private PhotoView mPhotoview2;
    private PhotoView mPhotoview3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_use_guide_detail);
        initView();
        Intent intent = getIntent();
        String tag = intent.getStringExtra(ActivityResultManager.USER_GUID_TAG);
        switch (tag) {
            case ActivityResultManager.OPEN_BY_FACE:
                initActionBar("人脸开门","");
                mPhotoview.setImageResource(R.mipmap.face_1);
                mPhotoview2.setImageResource(R.mipmap.face_2);
                mPhotoview3.setImageResource(R.mipmap.face_3);
                break;

            case ActivityResultManager.OPEN_BY_MOBILE:
                initActionBar("手机开门","");
                mPhotoview.setImageResource(R.mipmap.mobile_1);
                mPhotoview2.setImageResource(R.mipmap.mobile_2);

                break;
            case ActivityResultManager.ADD_NEW_HOUSE:
                initActionBar("添加新房屋","");
                mPhotoview.setImageResource(R.mipmap.add_new_house);

                break;
            case ActivityResultManager.AUTHORIZATION_MANGER:
                initActionBar("授权管理","");

                mPhotoview.setImageResource(R.mipmap.auth_ma1);
                break;

            case ActivityResultManager.HOUSE_BILL:
                initActionBar("房屋账单","");
                mPhotoview.setImageResource(R.mipmap.house_bill);
                break;

        }
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
        mPhotoview = (PhotoView) findViewById(R.id.photoview);
        mPhotoview2 = (PhotoView) findViewById(R.id.photoview2);
        mPhotoview3 = (PhotoView) findViewById(R.id.photoview3);
        mPhotoview.enable();
        mPhotoview2.enable();
        mPhotoview3.enable();
    }
}
