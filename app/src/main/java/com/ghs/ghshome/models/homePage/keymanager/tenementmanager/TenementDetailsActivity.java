package com.ghs.ghshome.models.homePage.keymanager.tenementmanager;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ghs.ghshome.R;
import com.ghs.ghshome.base.BaseActivity;
import com.ghs.ghshome.base.BasePresent;
import com.ghs.ghshome.bean.TenementRoomBean;
import com.ghs.ghshome.tools.ActivityResultManager;
import com.ghs.ghshome.tools.Contract;
import com.ghs.ghshome.tools.UserInfoUtil;
import com.ghs.ghshome.tools.glide.GlideManagerUtil;

public class TenementDetailsActivity extends BaseActivity<TenementContract.TenementView, TenementPresent> implements View.OnClickListener, TenementContract.TenementView {


    private ImageView mTenementUserPhotoIv;
    /**
     * 王二
     */
    private TextView mTenementUserNameTv;
    /**
     * 认证通过
     */
    private TextView mUserStatusTv;
    /**
     * 住户姓名：
     */
    private TextView mTenementUserNameTv1;
    /**
     * 手机号：
     */
    private TextView mTenementUserMobileTv;
    /**
     * 身份：
     */
    private TextView mTenementUserStateTv;
    /**
     * 认证方式：
     */
    private TextView mTenementUserWayTv;
    /**
     * 移除房间
     */
    private TextView mRemoveRoomTv;
    private TenementRoomBean.DataBean dataBean;

    @Override
    protected void setLayout() {
        setContentView(R.layout.activity_tenement_details);
        initActionBar("住户详情", "");
        initView();

    }

    @Override
    public TenementPresent creatPresenter() {
        return new TenementPresent();
    }

    @Override
    public void getDate() {
        Intent intent = getIntent();
        dataBean = (TenementRoomBean.DataBean) intent.getSerializableExtra(TenementContract.TENEMENT_DETAILS);
        if (dataBean != null) {
            //头像
            GlideManagerUtil.getInstance(this)
                    .loadCirclePic(Contract.ImageBasePath + dataBean.getHeadImage(), R.mipmap.default_user_head_icon, mTenementUserPhotoIv);
            //姓名
            mTenementUserNameTv.setText(dataBean.getFullName());
            //住户姓名
            mTenementUserNameTv1.setText("住户姓名：" + dataBean.getFullName());
            //手机号
            mTenementUserMobileTv.setText("手机号码：" + dataBean.getMobile());
            //身份
            mTenementUserStateTv.setText("住户身份：" + getStatus(dataBean.getRoleType()));
            //认证方式
            mTenementUserWayTv.setText("认证方式：" + getStatus1(dataBean.getCheckWay()));
        }

    }

    /**
     * 认证方式：1.业主认证，2.物业认证
     */
    public String getStatus1(int state) {
        String str = "";
        switch (state) {
            case 1:

                str = "业主认证";
                break;
            case 2:
                str = "物业认证";
                break;

        }
        return str;
    }

    /**
     * 角色 1业主 2业主家人 3租客 4租客家人 5其他
     */
    public String getStatus(int state) {
        String str = "";
        switch (state) {
            case 1:

                str = "业主";
                break;
            case 2:
                str = "业主家人";
                break;
            case 3:
                str = "租客";
                break;

            case 4:
                str = "租客家人";
                break;

            case 5:
                str = "其他";
                break;
        }
        return str;
    }

    public void initView() {
        mTenementUserPhotoIv = (ImageView) findViewById(R.id.tenement_user_photo_iv);
        mTenementUserNameTv = (TextView) findViewById(R.id.tenement_user_name_tv);
        mUserStatusTv = (TextView) findViewById(R.id.user_status_tv);
        mTenementUserNameTv1 = (TextView) findViewById(R.id.tenement_user_name_tv1);
        mTenementUserMobileTv = (TextView) findViewById(R.id.tenement_user_mobile_tv);
        mTenementUserStateTv = (TextView) findViewById(R.id.tenement_user_state_tv);
        mTenementUserWayTv = (TextView) findViewById(R.id.tenement_user_way_tv);
        mRemoveRoomTv = (TextView) findViewById(R.id.remove_room_tv);
        if(UserInfoUtil.getInstance().getCurrentVillageBean().getRoleType()!= 1){
            mRemoveRoomTv.setVisibility(View.GONE);
        }
        mRemoveRoomTv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.remove_room_tv:
                showButton("移除房间后，将不可使用该房间所有功能，确定要移除吗？？");
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
        stopProgressDialog();
        showToast("移除成功");
        setResult(ActivityResultManager.TENEMENT_DETAILS);
        onBackPressed();

    }

    @Override
    public void onError(String tag) {
        stopProgressDialog();
        showToast(tag);
    }

    public void showButton(String msg) {


        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setMessage(msg)
                .setNegativeButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        showProgressDialog();
                        getPresenter().removeTenment(UserInfoUtil.getInstance().getRoomUserId(), dataBean.getId(), "");

                    }

                }).setPositiveButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {


                    }
                }).show();

    }
}
