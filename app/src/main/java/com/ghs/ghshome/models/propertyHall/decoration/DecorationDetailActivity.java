package com.ghs.ghshome.models.propertyHall.decoration;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ghs.ghshome.R;
import com.ghs.ghshome.base.BaseActivity;
import com.ghs.ghshome.base.displayPhotos.DisplayPhotosActivity;
import com.ghs.ghshome.bean.FitmentFireParticularsBean;
import com.ghs.ghshome.bean.FitmentParticularsBean;

/**
 * 装修备案详情
 */
public class DecorationDetailActivity extends BaseActivity<DecorationContrat.DecorationView, DecorationPresenter> implements DecorationContrat.DecorationView, View.OnClickListener {

    private LinearLayout mHeadLayoutLl;
    private FrameLayout mFragmentContent;
    /**
     * 一单元105
     */
    private TextView mFitmentParticularsRoomName;
    /**
     * 李小双
     */
    private TextView mFitmentParticularsName;
    /**
     * 18810891140
     */
    private TextView mFitmentParticularsMobile;
    /**
     * 代理人姓名：
     */
    private TextView mFitmentParticularsProxyName;
    /**
     * 18810891140
     */
    private TextView mFitmentParticularsProxyMobile;
    /**
     * 1115522688889965555555556666666
     */
    private TextView mFitmentParticularsProxyIdcard;
    /**
     * 2019年5月30日
     */
    private TextView mFitmentParticularsStarttime;
    /**
     * 2019年5月31日
     */
    private TextView mFitmentParticularsEndtime;
    /**
     * 红星美凯龙
     */
    private TextView mFitmentParticularsCompanyName;
    /**
     * 王二
     */
    private TextView mFitmentParticularsCompanyPeople;
    /**
     * 18810891140
     */
    private TextView mFitmentParticularsCompanyMobile;
    /**
     * 查看
     */
    private TextView mFitmentParticularsCompanyIdcard;
    /**
     * 查看
     */
    private TextView mFitmentParticularsCompanyLicense;
    /**
     * 查看
     */
    private TextView mFitmentParticularsCompanyCertificate;
    /**
     * 查看
     */
    private TextView mFitmentParticularsDrawing;
    /**
     * 装修全部设施
     */
    private TextView mFitmentParticularsContent;
    /**
     * 是
     */
    private TextView mFitmentParticularsClearType;
    /**
     * 2019年5月5日
     */
    private TextView mFitmentFireStartTime;
    /**
     * 2019年5月30日
     */
    private TextView mFitmentFireEndTime;
    /**
     * 厨房燃气灶
     */
    private TextView mFitmentFireLocation;
    /**
     * 厨房燃气灶
     */
    private TextView mFitmentFireFacility;
    /**
     * 装修首次点火
     */
    private TextView mFitmentFireCause;
    /**
     * 燃气防漏检测
     */
    private TextView mFitmentFireMeasure;
    /**
     * 里差德
     */
    private TextView mFitmentFireOperator;
    /**
     * 123456789
     */
    private TextView mFitmentFireNumber;
    /**
     * 查看
     */
    private TextView mFitmentFirePhoto;
    private LinearLayout fitment_proxy_state;
    private LinearLayout fitment_self_state;
    private LinearLayout fitment_fire;
    private LinearLayout fitment_;
    private FitmentFireParticularsBean.DataBean particularsFireBeanData;
    private FitmentParticularsBean.DataBean particularsBeanData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decoration_particulars);
        initView();
        initActionBar("备案详情", "");
    }

    @Override
    protected void setLayout() {

    }

    @Override
    public DecorationPresenter creatPresenter() {
        return new DecorationPresenter();
    }


    @Override
    public void getDate() {
        Intent intent = getIntent();
        int type = intent.getIntExtra("decoration_type",0);
        int id = intent.getIntExtra("decoration_id",0);
        if (1 == type) {
            //装修备案
            getPresenter().getFitmentParticulars(id, DecorationContrat.FITMENTPARTICULARS);
        } else {
            //动火备案
            getPresenter().getFitmentFireParticulars(id, DecorationContrat.FITMENTFIREPARTICULARS);

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

        switch (tag) {

            //装修备案
            case DecorationContrat.FITMENTPARTICULARS:
                fitment_fire.setVisibility(View.GONE);
                FitmentParticularsBean fitmentParticularsBean = (FitmentParticularsBean) o;
                particularsBeanData = fitmentParticularsBean.getData();
                if (particularsBeanData != null) {
                    //房屋名称
                    mFitmentParticularsRoomName.setText(particularsBeanData.getRoomFullName());
                    //申请人
                    mFitmentParticularsName.setText(particularsBeanData.getFullName());
                    //联系电话
                    mFitmentParticularsMobile.setText(particularsBeanData.getMobile());
                    if (1 == particularsBeanData.getProxyFlag()) {
                        //代理人姓名
                        mFitmentParticularsProxyName.setText(particularsBeanData.getProxyName());
                        //代理人电话
                        mFitmentParticularsProxyMobile.setText(particularsBeanData.getProxyMobile());
                        //代理人身份证号
                        mFitmentParticularsProxyIdcard.setText(particularsBeanData.getProxyNum());
                    } else {
                        //隐藏
                        fitment_proxy_state.setVisibility(View.GONE);
                    }
                    //装修开始时间
                    mFitmentParticularsStarttime.setText(particularsBeanData.getStartTime());
                    //装修结束时间
                    mFitmentParticularsEndtime.setText(particularsBeanData.getEndTime());

                    if (1 == particularsBeanData.getSelfFlag()) {
                        //装修公司名称
                        mFitmentParticularsCompanyName.setText(particularsBeanData.getCompanyName());
                        //装修负责人
                        mFitmentParticularsCompanyPeople.setText(particularsBeanData.getCompanyHandlerName());
                        //装修联系电话
                        mFitmentParticularsCompanyMobile.setText(particularsBeanData.getCompanyHandlerMobile());
                    } else {
                        //隐藏
                        fitment_self_state.setVisibility(View.GONE);
                    }

                    //装修内容
                    mFitmentParticularsContent.setText(particularsBeanData.getContent());
                    //装修公司清运方式
                    mFitmentParticularsClearType.setText(particularsBeanData.getCleanType());
                }

                break;
            //动火备案
            case DecorationContrat.FITMENTFIREPARTICULARS:
                fitment_.setVisibility(View.GONE);
                FitmentFireParticularsBean particularsBean = (FitmentFireParticularsBean) o;
                particularsFireBeanData = particularsBean.getData();
                if (particularsFireBeanData != null) {
                    //房屋名称
                    mFitmentParticularsRoomName.setText(particularsFireBeanData.getRoomFullName());
                    //申请人
                    mFitmentParticularsName.setText(particularsFireBeanData.getFullName());
                    //联系电话
                    mFitmentParticularsMobile.setText(particularsFireBeanData.getMobile());
                    //动火开始时间
                    mFitmentFireStartTime.setText(particularsFireBeanData.getStartTime());
                    //动火结束时间
                    mFitmentFireEndTime.setText(particularsFireBeanData.getEndTime());
                    //动火位置
                    mFitmentFireLocation.setText(particularsFireBeanData.getPosition());
                    //动火方式与设备
                    mFitmentFireFacility.setText(particularsFireBeanData.getMethod());
                    //动火原因
                    mFitmentFireCause.setText(particularsFireBeanData.getReason());
                    //动火操作人
                    mFitmentFireOperator.setText(particularsFireBeanData.getHandlerName());
                    //操作证号
                    mFitmentFireNumber.setText(particularsFireBeanData.getPapersNum());

                }
                break;


        }


    }

    @Override
    public void onError(String tag) {

        showToast(tag);

    }


    private void initView() {
        mFitmentParticularsRoomName = (TextView) findViewById(R.id.fitment_particulars_roomName);
        mFitmentParticularsName = (TextView) findViewById(R.id.fitment_particulars_name);
        mFitmentParticularsMobile = (TextView) findViewById(R.id.fitment_particulars_mobile);
        mFitmentParticularsProxyName = (TextView) findViewById(R.id.fitment_particulars_proxy_name);
        mFitmentParticularsProxyMobile = (TextView) findViewById(R.id.fitment_particulars_proxy_mobile);
        mFitmentParticularsProxyIdcard = (TextView) findViewById(R.id.fitment_particulars_proxy_idcard);
        mFitmentParticularsStarttime = (TextView) findViewById(R.id.fitment_particulars_starttime);
        mFitmentParticularsEndtime = (TextView) findViewById(R.id.fitment_particulars_endtime);
        mFitmentParticularsCompanyName = (TextView) findViewById(R.id.fitment_particulars_company_name);
        mFitmentParticularsCompanyPeople = (TextView) findViewById(R.id.fitment_particulars_company_people);
        mFitmentParticularsCompanyMobile = (TextView) findViewById(R.id.fitment_particulars_company_mobile);
        mFitmentParticularsCompanyIdcard = (TextView) findViewById(R.id.fitment_particulars_company_idcard);
        mFitmentParticularsCompanyLicense = (TextView) findViewById(R.id.fitment_particulars_company_license);
        mFitmentParticularsCompanyCertificate = (TextView) findViewById(R.id.fitment_particulars_company_certificate);
        mFitmentParticularsDrawing = (TextView) findViewById(R.id.fitment_particulars_drawing);
        mFitmentParticularsContent = (TextView) findViewById(R.id.fitment_particulars_content);
        mFitmentParticularsClearType = (TextView) findViewById(R.id.fitment_particulars_clearType);
        mFitmentFireStartTime = (TextView) findViewById(R.id.fitment_fire_startTime);
        mFitmentFireEndTime = (TextView) findViewById(R.id.fitment_fire_endTime);
        mFitmentFireLocation = (TextView) findViewById(R.id.fitment_fire_location);
        mFitmentFireFacility = (TextView) findViewById(R.id.fitment_fire_facility);
        mFitmentFireCause = (TextView) findViewById(R.id.fitment_fire_cause);
        mFitmentFireMeasure = (TextView) findViewById(R.id.fitment_fire_measure);
        mFitmentFireOperator = (TextView) findViewById(R.id.fitment_fire_operator);
        mFitmentFireNumber = (TextView) findViewById(R.id.fitment_fire_number);
        mFitmentFirePhoto = (TextView) findViewById(R.id.fitment_fire_photo);
        //装修人身份证
        mFitmentParticularsCompanyIdcard.setOnClickListener(this);
        //装修公司营业执照
        mFitmentParticularsCompanyLicense.setOnClickListener(this);
        //装修公司资质证明
        mFitmentParticularsCompanyCertificate.setOnClickListener(this);
        //装修图纸
        mFitmentParticularsDrawing.setOnClickListener(this);
        //是否有代理人
        fitment_proxy_state = findViewById(R.id.fitment_proxy_state);
        //是否自装
        fitment_self_state = findViewById(R.id.fitment_self_state);
        //动火备案
        fitment_fire = findViewById(R.id.fitment_fire);
        //装修备案
        fitment_ = findViewById(R.id.fitment_);
        //操作证照片
        mFitmentFirePhoto.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            //装修人身份证
            case R.id.fitment_particulars_company_idcard:
                if (particularsBeanData != null) {

                    new DisplayPhotosActivity().startDisplayPhotosActivity(DecorationDetailActivity.this, particularsBeanData.getCompanyHandlerNumImg(), 0);
                }

                break;
            //装修公司营业执照
            case R.id.fitment_particulars_company_license:

                if (particularsBeanData != null) {
                    new DisplayPhotosActivity().startDisplayPhotosActivity(DecorationDetailActivity.this, particularsBeanData.getCompanyLicenseImg(), 0);
                }
                break;
            //装修公司资质证书
            case R.id.fitment_particulars_company_certificate:

                if (particularsBeanData != null) {
                    new DisplayPhotosActivity().startDisplayPhotosActivity(DecorationDetailActivity.this, particularsBeanData.getCompanyQualificationImg(), 0);
                }

                break;
            //装修图纸
            case R.id.fitment_particulars_drawing:

                if (particularsBeanData != null) {

                    new DisplayPhotosActivity().startDisplayPhotosActivity(DecorationDetailActivity.this, particularsBeanData.getDrawingImg(), 0);

                }


                break;
            //操作照片
            case R.id.fitment_fire_photo:

                if (particularsFireBeanData != null) {
                    new DisplayPhotosActivity().startDisplayPhotosActivity(DecorationDetailActivity.this, particularsFireBeanData.getPapersImg(), 0);
                }
                break;
        }


    }
}
