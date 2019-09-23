package com.ghs.ghshome.models.propertyHall.openByFace;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ghs.ghshome.R;
import com.ghs.ghshome.base.BaseActivity;
import com.ghs.ghshome.bean.FaceUfaceUserInfoBean;
import com.ghs.ghshome.models.mine.useguide.UseGuideDetailActivity;
import com.ghs.ghshome.tools.ActivityResultManager;
import com.ghs.ghshome.tools.UserInfoUtil;

import java.util.List;

/**
 * created by tobato
 * created date 2019/6/17 18:34.
 * application   人脸开门
 */
public class FaceOpenActivity extends BaseActivity<FaceOpenContrat.IFaceOpenView, FaceOpenPresenter> implements FaceOpenContrat.IFaceOpenView, View.OnClickListener {

    private ImageView mHeaderLeftIv;
    /**
     * 设置
     */
    private TextView mHeaderTitleTv;
    private ImageView mKeyLockExplain;
    /**
     * 褐石小区
     */
    private TextView mFaceVillageName;
    /**
     * 里小双
     */
    private TextView mFaceUserName;
    /**
     * 一单元303
     */
    private TextView mFaceRoomName;
    /**
     * 已开启
     */
    private TextView mFaceState;
    private RelativeLayout mFaceCollect;
    private LinearLayout mFaceRefreshRange;
    /**
     * 获取新范围
     */
    private TextView mFaceGet1;
    private RecyclerView mFaceRv;
    private FaceRangeDoorAdapter faceRangeDoorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_face_open);
        initView();

    }

    @Override
    protected void setLayout() {

    }

    @Override
    public FaceOpenPresenter creatPresenter() {
        return new FaceOpenPresenter();
    }


    @Override
    public void getDate() {

            showProgressDialog();
            getPresenter().getUfcaceInfo(mUserInfoUtil.getUserId(), UserInfoUtil.getInstance().getVillageId(), UserInfoUtil.getInstance().getCellId(), FaceOpenContrat.GETUSERFACEINFO);


    }

    private void initView() {
        mHeaderLeftIv = (ImageView) findViewById(R.id.header_left_iv);
        mHeaderLeftIv.setOnClickListener(this);
        mHeaderTitleTv = (TextView) findViewById(R.id.header_title_tv);
        mHeaderTitleTv.setText("人脸开门");
        mKeyLockExplain = (ImageView) findViewById(R.id.key_lock_explain);
        mKeyLockExplain.setOnClickListener(this);
        mFaceVillageName = (TextView) findViewById(R.id.face_villageName);
        mFaceUserName = (TextView) findViewById(R.id.face_userName);
        mFaceRoomName = (TextView) findViewById(R.id.face_roomName);
        mFaceState = (TextView) findViewById(R.id.face_state);
        mFaceCollect = (RelativeLayout) findViewById(R.id.face_collect);
        mFaceCollect.setOnClickListener(this);
        mFaceRefreshRange = (LinearLayout) findViewById(R.id.face_refresh_range);
        mFaceRefreshRange.setOnClickListener(this);
        mFaceGet1 = (TextView) findViewById(R.id.face_get_1);
        mFaceRv = (RecyclerView) findViewById(R.id.face_rv);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        mFaceRv.setLayoutManager(staggeredGridLayoutManager);

        faceRangeDoorAdapter = new FaceRangeDoorAdapter(R.layout.face_door_item_layout, null);
//        faceRangeDoorAdapter.setEmptyView(getAdapterEmptyView("暂时没有授权设备"));
        mFaceRv.setAdapter(faceRangeDoorAdapter);

        mFaceVillageName.setText(mUserInfoUtil.getVillageName());
        mFaceUserName.setText(mUserInfoUtil.getFullName());
        mFaceRoomName.setText(mUserInfoUtil.getRoomName());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.header_left_iv:
                //返回
                onBackPressed();
                break;

            case R.id.key_lock_explain:
                //说明
                startActivity(new Intent(FaceOpenActivity.this, UseGuideDetailActivity.class).putExtra(ActivityResultManager.USER_GUID_TAG, ActivityResultManager.OPEN_BY_FACE));

                break;
            case R.id.face_collect:
                //采集人脸
                startActivityForResult(new Intent(this, PhotosOfTakedActivity.class), ActivityResultManager.TASKED_FACE_LIST);

                break;
            case R.id.face_refresh_range:
                //刷新范围
                    showProgressDialog();
                    getPresenter().refreshRole(UserInfoUtil.getInstance().getUserId(), UserInfoUtil.getInstance().getVillageId(), UserInfoUtil.getInstance().getCellId(), FaceOpenContrat.REFRESHROLE);

                break;
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
        FaceUfaceUserInfoBean faceUfaceUserInfoBean = (FaceUfaceUserInfoBean) o;
        FaceUfaceUserInfoBean.DataBean userInfoBeanData = faceUfaceUserInfoBean.getData();
        if (userInfoBeanData != null) {
            mFaceState.setText(userInfoBeanData.getState() == 0 ? "未开启" : "已开启");
            List<FaceUfaceUserInfoBean.DataBean.UfaceDeviceDOListBean> ufaceDeviceDOList = userInfoBeanData.getUfaceDeviceDOList();
            faceRangeDoorAdapter.setNewData(ufaceDeviceDOList);

        }


    }

    @Override
    public void onError(String tag) {
        showToast(tag);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ActivityResultManager.TASKED_FACE_LIST) {
                showProgressDialog();
                getPresenter().refreshRole(UserInfoUtil.getInstance().getUserId(), UserInfoUtil.getInstance().getVillageId(), UserInfoUtil.getInstance().getCellId(), FaceOpenContrat.REFRESHROLE);
        }

    }
}
