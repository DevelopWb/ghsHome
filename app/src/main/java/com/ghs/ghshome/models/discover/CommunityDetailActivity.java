package com.ghs.ghshome.models.discover;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ghs.ghshome.R;
import com.ghs.ghshome.base.BaseActivity;
import com.ghs.ghshome.bean.CommunityParticularsBean;
import com.ghs.ghshome.tools.ActivityResultManager;
import com.ghs.ghshome.tools.CalendarUtil;
import com.ghs.ghshome.tools.Contract;
import com.ghs.ghshome.tools.StrUtils;
import com.ghs.ghshome.tools.glide.GlideManagerUtil;

/**
 * 社区活动详情
 */

public class CommunityDetailActivity extends BaseActivity<DiscoverContract.IDiscoverView, DiscoverPresenter> implements View.OnClickListener, DiscoverContract.IDiscoverView {

    private TextView button_particulars;
    private ImageView mCommunPlImage;
    /**
     * 20190520
     */
    private TextView mCommunPlTime;
    /**
     * 20190520
     */
    private TextView mCommunPlApplyTime;
    /**
     * 北京市万通中心
     */
    private TextView mCommunPlAddress;
    /**
     * 免费
     */
    private TextView mCommunPlMoneyState;
    /**
     * 李小双
     */
    private TextView mCommunPlPeople;
    /**
     * 17310809523:
     */
    private TextView mCommunPlMobile;
    /**
     * 活动内容
     */
    private TextView mCommunPlContent;
    /**
     * 我要报名
     */
    private TextView mButtonParticulars;
    private int dis_commun_pl_id;
    private CommunityParticularsBean.DataBean dataBean;
    private LinearLayout mCommunPlApplyTimeLy;
    private LinearLayout mCommunPlPeopleLy;
    private LinearLayout mCommunPlMobileLy;
    private SwipeRefreshLayout discover_datial_refesh;
    private RelativeLayout button_particulars_ly;

    @Override
    public void setLayout() {
        setContentView(R.layout.activity_community_particulars);

    }

    @Override
    public void initLayoutView() {
        initView();

    }

    @Override
    public DiscoverPresenter creatPresenter() {
        return new DiscoverPresenter();
    }


    @Override
    public void getDate() {

        Intent intent = getIntent();
        dis_commun_pl_id = intent.getIntExtra("DIS_COMMUN_PL_ID", -1);
        if (dis_commun_pl_id != -1) {
            showProgressDialog();
            getPresenter().getCommUnityParticulars(dis_commun_pl_id, mUserInfoUtil.getUserId(), DiscoverContract.AC_PARTICULARS);

        }


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_particulars:

                if (dataBean != null) {
                    Intent intent = new Intent(CommunityDetailActivity.this, ApplyActivity.class);
                    intent.putExtra("COMMUNITY_APPLY", dataBean);
                    startActivityForResult(intent, ActivityResultManager.COMMUNITY_APLY);
                }

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
        discover_datial_refesh.setRefreshing(false);
        CommunityParticularsBean particularsBean = (CommunityParticularsBean) o;
        dataBean = particularsBean.getData();
        if (dataBean != null) {

            initActionBar(dataBean.getTitle(), "");
            GlideManagerUtil.getInstance(this).loadNormalPic(Contract.ImageBasePath + dataBean.getImage(),R.drawable.default_image,mCommunPlImage);
            //活动时间
            mCommunPlTime.setText(dataBean.getStartTime() + "至" + dataBean.getEndTime());
            //活动报名时间
            if (StrUtils.isStringValueOk(dataBean.getApplyStartTime())) {
                mCommunPlApplyTime.setText(dataBean.getApplyStartTime() + "至" + dataBean.getApplyEndTime());
            } else {
                mCommunPlApplyTimeLy.setVisibility(View.GONE);
            }
            //活动地点
            mCommunPlAddress.setText(StrUtils.isStringValueOk(dataBean.getAddress()) ? dataBean.getAddress() : "暂无地址");
            //活动费用
            mCommunPlMoneyState.setText(dataBean.getFreeFlag() == 0 ? "收费"+"("+dataBean.getPrice()+"元)" : "免费");
            if (StrUtils.isStringValueOk(dataBean.getContactUser())) {
                //活动联系人
                mCommunPlPeople.setText(dataBean.getContactUser());
            } else {
                mCommunPlPeopleLy.setVisibility(View.GONE);
            }

            if (StrUtils.isStringValueOk(dataBean.getContactMobile())) {
                //活动电话
                mCommunPlMobile.setText(dataBean.getContactMobile());
            } else {
                mCommunPlMobileLy.setVisibility(View.GONE);
            }
            //活动内容
            mCommunPlContent.setText(StrUtils.isStringValueOk(dataBean.getContent()) ? dataBean.getContent() : "暂无活动内容");
            //报名按钮
            if (dataBean.getApplyFlag() == 0) {
                //不需要报名
                mButtonParticulars.setVisibility(View.GONE);
                button_particulars_ly.setVisibility(View.GONE);
            } else {
                mButtonParticulars.setVisibility(View.VISIBLE);
                button_particulars_ly.setVisibility(View.VISIBLE);
                if (dataBean.getApplyState() == 0) {
                    //当前时间是大于报名结束时间
                    if (CalendarUtil.compareDateSizediscover("yyyy-MM-dd", CalendarUtil.getCurrentTime("yyyy-MM-dd"), dataBean.getApplyEndTime())) {
                        mButtonParticulars.setText("报名已结束");
                        mButtonParticulars.setEnabled(false);
                        mButtonParticulars.setBackground(getResources().getDrawable(R.drawable.rv_gray_shadow_shape));
                    } else {
                        mButtonParticulars.setEnabled(true);
                        mButtonParticulars.setBackground(getResources().getDrawable(R.drawable.bt_selecter_default_green));
                        mButtonParticulars.setText("我要报名");
                    }

                } else {

                    //当前时间是大于报名结束时间
                    if (CalendarUtil.compareDateSizediscover("yyyy-MM-dd", CalendarUtil.getCurrentTime("yyyy-MM-dd"), dataBean.getApplyEndTime())) {
                        mButtonParticulars.setText("报名已结束");
                        mButtonParticulars.setEnabled(false);
                        mButtonParticulars.setBackground(getResources().getDrawable(R.drawable.rv_gray_shadow_shape));
                    } else {
                        mButtonParticulars.setText("已报名");
                        mButtonParticulars.setEnabled(false);
                        mButtonParticulars.setBackground(getResources().getDrawable(R.drawable.rv_gray_shadow_shape));

                    }

                }

            }


            //判断活动是否下线
            if (2 != dataBean.getState()) {
                showhintDialog();
            }

        }


    }


    public void showhintDialog() {

        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setTitle("通知")
                .setCancelable(false)
                .setMessage("该活动已下线！")
                .setNegativeButton("知道了", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        finish();
                    }
                }).show();
        setAlertDialogHeightWidth(alertDialog, -1, 0);


    }

    @Override
    public void onError(String tag) {
        initActionBar("活动详情", "");
        showToast(tag);
    }


    public void initView() {
        mCommunPlImage = (ImageView) findViewById(R.id.commun_pl_image);
        mCommunPlTime = (TextView) findViewById(R.id.commun_pl_time);
        mCommunPlApplyTime = (TextView) findViewById(R.id.commun_pl_apply_time);
        mCommunPlAddress = (TextView) findViewById(R.id.commun_pl_address);
        mCommunPlMoneyState = (TextView) findViewById(R.id.commun_pl_money_state);
        mCommunPlPeople = (TextView) findViewById(R.id.commun_pl_people);
        mCommunPlMobile = (TextView) findViewById(R.id.commun_pl_mobile);
        mCommunPlContent = (TextView) findViewById(R.id.commun_pl_content);
        mButtonParticulars = (TextView) findViewById(R.id.button_particulars);
        mButtonParticulars.setOnClickListener(this);
        button_particulars_ly = findViewById(R.id.button_particulars_ly);
        mCommunPlApplyTimeLy = (LinearLayout) findViewById(R.id.commun_pl_apply_time_ly);
        mCommunPlPeopleLy = (LinearLayout) findViewById(R.id.commun_pl_people_ly);
        mCommunPlMobileLy = (LinearLayout) findViewById(R.id.commun_pl_mobile_ly);
        discover_datial_refesh = findViewById(R.id.discover_datial_refesh);
        discover_datial_refesh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (dis_commun_pl_id != -1) {
                        getPresenter().getCommUnityParticulars(dis_commun_pl_id, mUserInfoUtil.getUserId(), DiscoverContract.AC_PARTICULARS);
                }


            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == ActivityResultManager.COMMUNITY_APLY) {
                showProgressDialog();
                getPresenter().getCommUnityParticulars(dis_commun_pl_id, mUserInfoUtil.getUserId(), DiscoverContract.AC_PARTICULARS);

        }

    }


}
