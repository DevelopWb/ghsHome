package com.ghs.ghshome.models.propertyHall.decoration;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.constraint.Group;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.ArrayMap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ghs.ghshome.R;
import com.ghs.ghshome.base.BaseActivity;
import com.ghs.ghshome.base.displayPhotos.ShowSelectedPicsAdapter;
import com.ghs.ghshome.base.network.RequestStatus;
import com.ghs.ghshome.base.oss.OssUploadManager;
import com.ghs.ghshome.base.promission.CheckPermListener;
import com.ghs.ghshome.bean.FitmentFireParticularsBean;
import com.ghs.ghshome.tools.ActivityResultManager;
import com.ghs.ghshome.tools.CalendarUtil;
import com.ghs.ghshome.tools.FileUtil;
import com.ghs.ghshome.tools.ImageUtil;
import com.ghs.ghshome.tools.PickerManager;
import com.ghs.ghshome.tools.PubUtil;
import com.ghs.ghshome.tools.RxScheduler;
import com.ghs.ghshome.tools.StrUtils;
import com.ghs.ghshome.tools.UserInfoUtil;
import com.ghs.ghshome.tools.glide.GlideManagerUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import cn.com.mark.multiimage.core.ImageMainActivity;

/**
 * 创建装修备案
 */
public class CreateDecorationActivity extends BaseActivity<DecorationContrat.DecorationView, DecorationPresenter> implements View.OnClickListener, DecorationContrat.DecorationView, RequestStatus {


    private Switch mAddVisitorDrivedSwitch;
    private Group isProxy;
    private Switch add_visitor_drived_switch_;
    private LinearLayout fitment_isDecorAtion;
    private CreateDecorationActivity selectPhotosFragment;
    /**
     * 一期一号楼一单元101
     */
    private TextView mFitmentRoomName;
    /**
     * 里小双
     */
    private TextView mFitmentProposer;
    /**
     * 18810891140
     */
    private TextView mFitmentProposerMobile;
    /**
     * 请输入
     */
    private EditText mFitmentAgentName;
    /**
     * 请输入
     */
    private EditText mFitmentAgentMobile;
    /**
     * 请输入
     */
    private EditText mFitmentAgentNumber;
    /**
     * 请选择
     */
    private TextView mFitmentStartTime;
    /**
     * 请选择
     */
    private TextView mFitmentEndTime;
    /**
     * 请输入
     */
    private EditText mFitmentCompanyName;
    /**
     * 请输入
     */
    private EditText mFitmentCompanyPeople;
    /**
     * 请输入
     */
    private EditText mFitmentCompanyMobile;
    /**
     * 请输入装修内容
     */
    private EditText mFitmentContent;
    /**
     * 物业公司清理（收费）
     */
    private TextView mFitmentClearType;
    /**
     * 确定
     */
    private TextView mEntryConfirm;
    private ImageView mFitmentStartTimeImage;
    private ImageView mFitmentEndTimeImage;
    private ImageView mIdCardFrontIv;
    private ImageView mIdCardBackIv;
    private ImageView mIdCardLicenceIv;
    private ImageView mIdCardQualificationIv;

    private String currentClickedImageKey = "";//当前点击imgage 主要针对四个对象，身份证正反面  营业执照 资质证书
    private final String idCardFrontKey = "id_card_front";//身份证正面key
    private final String idCardBackKey = "id_card_back";//身份证反面key
    private final String busnessLicenceKey = "busness_licence";//营业执照
    private final String qualificationKey = "qualification";//资质证书
    private final String drawingKey = "drawingKey";//装修图纸
    private ArrayMap<String, List<String>> uploadImagesMap = new ArrayMap<>(4);//存储选择的图片主要针对四个对象，身份证正反面  营业执照 资质证书
    private RecyclerView mSelectPhotosRv;
    private ShowSelectedPicsAdapter selectedPicsAdapter;

    private List<String> icons = new ArrayList<>();
    private int maxCount = 10;//最大个数，默认9个
    private int spanCount = 4;//一行的个数，默认3
    private BottomSheetDialog bottomSheetDialog;
    private LinearLayout fitment_fire_startTime_ly;
    private LinearLayout fitment_end_time_ly;
    private ImageView mIdCardFrontDeleteIcon;
    private ImageView mIdCardBackDeleteIcon;
    private ImageView mLicenceDeleteIcon;
    private ImageView mQualificationDeleteIcon;
    private OssUploadManager ossUploadManager;


    @Override

    public void setLayout() {
        setContentView(R.layout.activity_fitment_entry);

    }

    @Override
    public void initLayoutView() {
        initActionBar("装修备案", "");
        initView();
    }

    @Override
    public DecorationPresenter creatPresenter() {
        return new DecorationPresenter();
    }


    @Override
    public void getDate() {
        mFitmentRoomName.setText(UserInfoUtil.getInstance().getRoomName());
        mFitmentProposer.setText(UserInfoUtil.getInstance().getFullName());
        mFitmentProposerMobile.setText(UserInfoUtil.getInstance().getMobile());
        ossUploadManager = OssUploadManager.getInstance(this);
    }


    public void initView() {

        mAddVisitorDrivedSwitch = (Switch) findViewById(R.id.add_visitor_drived_switch);
        mAddVisitorDrivedSwitch.setOnClickListener(this);
        isProxy = (Group) findViewById(R.id.add_visitor_car_num_group);
        if (mAddVisitorDrivedSwitch.isChecked()) {
            isProxy.setVisibility(View.VISIBLE);
        } else {
            isProxy.setVisibility(View.GONE);

        }
        mAddVisitorDrivedSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    isProxy.setVisibility(View.VISIBLE);
                } else {
                    isProxy.setVisibility(View.GONE);
                }
            }
        });


        add_visitor_drived_switch_ = findViewById(R.id.add_visitor_drived_switch_);
        //设置默认开启
        add_visitor_drived_switch_.setChecked(true);

        fitment_isDecorAtion = findViewById(R.id.fitment_isDecorAtion);
        if (!add_visitor_drived_switch_.isChecked()) {
            fitment_isDecorAtion.setVisibility(View.VISIBLE);
        } else {
            fitment_isDecorAtion.setVisibility(View.GONE);

        }
        add_visitor_drived_switch_.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked) {
                    fitment_isDecorAtion.setVisibility(View.VISIBLE);
                } else {

                    fitment_isDecorAtion.setVisibility(View.GONE);

                }
            }
        });


        mFitmentRoomName = (TextView) findViewById(R.id.fitment_room_name);
        mFitmentProposer = (TextView) findViewById(R.id.fitment_proposer);
        mFitmentProposerMobile = (TextView) findViewById(R.id.fitment_proposer_mobile);
        mFitmentAgentName = (EditText) findViewById(R.id.fitment_agent_name);
        mFitmentAgentMobile = (EditText) findViewById(R.id.fitment_agent_mobile);
        mFitmentAgentNumber = (EditText) findViewById(R.id.fitment_agent_number);
        mFitmentStartTime = (TextView) findViewById(R.id.fitment_start_time);
        mFitmentEndTime = (TextView) findViewById(R.id.fitment_end_time);
        mFitmentCompanyName = (EditText) findViewById(R.id.fitment_company_name);
        mFitmentCompanyPeople = (EditText) findViewById(R.id.fitment_company_people);
        mFitmentCompanyMobile = (EditText) findViewById(R.id.fitment_company_mobile);
        mFitmentContent = (EditText) findViewById(R.id.fitment_content);
        mFitmentClearType = (TextView) findViewById(R.id.fitment_clear_type);
        mFitmentClearType.setOnClickListener(this);
        mEntryConfirm = (TextView) findViewById(R.id.entry_confirm);
        mEntryConfirm.setOnClickListener(this);
        mFitmentStartTimeImage = (ImageView) findViewById(R.id.fitment_start_time_image);
        mFitmentStartTimeImage.setVisibility(View.VISIBLE);
        mFitmentEndTimeImage = (ImageView) findViewById(R.id.fitment_end_time_image);
        mFitmentEndTimeImage.setVisibility(View.VISIBLE);
        mIdCardFrontIv = (ImageView) findViewById(R.id.id_card_front_iv);
        mIdCardFrontIv.setOnClickListener(this);
        mIdCardBackIv = (ImageView) findViewById(R.id.id_card_back_iv);
        mIdCardBackIv.setOnClickListener(this);
        mIdCardLicenceIv = (ImageView) findViewById(R.id.id_card_licence_iv);
        mIdCardLicenceIv.setOnClickListener(this);
        mIdCardQualificationIv = (ImageView) findViewById(R.id.id_card_qualification_iv);
        mIdCardQualificationIv.setOnClickListener(this);
        mSelectPhotosRv = (RecyclerView) findViewById(R.id.select_photos_rv);
        fitment_fire_startTime_ly = findViewById(R.id.fitment_start_time_ly);
        fitment_fire_startTime_ly.setOnClickListener(this);
        fitment_end_time_ly = findViewById(R.id.fitment_end_time_ly);
        fitment_end_time_ly.setOnClickListener(this);
        selectedPicsAdapter = new ShowSelectedPicsAdapter(R.layout.show_selected_pic_item);
        GridLayoutManager managere = new GridLayoutManager(this, spanCount);
        mSelectPhotosRv.setLayoutManager(managere);
        mSelectPhotosRv.setAdapter(selectedPicsAdapter);
        icons.add("-1");
        selectedPicsAdapter.setNewData(icons);
        selectedPicsAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//                imageView = (ImageView) selectedPicsAdapter.getViewByPosition(mPublishNoticeRv, position, R.id.mine_sugguest_icon_iv);
                maxCount = 21;
                spanCount = 5;
                List<String> arrays = reSortIconList(adapter.getData());
                String icon_path = arrays.get(position);
                switch (view.getId()) {
                    case R.id.mine_sugguest_icon_iv:
                        currentClickedImageKey = drawingKey;
                        if ("-1".equals(icon_path)) {
                            showSelectPhotoLayout();
                        }
                        break;
                    case R.id.mine_sugguest_delete_iv:
                        arrays.remove(position);
                        icons.clear();
                        if (arrays.size() < maxCount) {
                            if (!arrays.contains("-1")) {
                                arrays.add("-1");
                            }
                        }
                        icons = arrays;
                        adapter.setNewData(arrays);
                        break;
                    default:
                        break;
                }
            }
        });
        mIdCardFrontDeleteIcon = (ImageView) findViewById(R.id.id_card_front_delete_icon);
        mIdCardFrontDeleteIcon.setOnClickListener(this);
        mIdCardBackDeleteIcon = (ImageView) findViewById(R.id.id_card_back_delete_icon);
        mIdCardBackDeleteIcon.setOnClickListener(this);
        mLicenceDeleteIcon = (ImageView) findViewById(R.id.licence_delete_icon);
        mLicenceDeleteIcon.setOnClickListener(this);
        mQualificationDeleteIcon = (ImageView) findViewById(R.id.qualification_delete_icon);
        mQualificationDeleteIcon.setOnClickListener(this);
    }

    /**
     * 展示选择图片的布局  拍照或者从相册选择
     */
    private void showSelectPhotoLayout() {
        View bottomView = LayoutInflater.from(CreateDecorationActivity.this).inflate(R.layout.select_pic_menue, null);
        if (bottomSheetDialog == null) {
            bottomSheetDialog = new BottomSheetDialog(CreateDecorationActivity.this);
            bottomSheetDialog.setCanceledOnTouchOutside(true);
            bottomSheetDialog.setContentView(bottomView);
            bottomView.findViewById(R.id.mine_edit_cancel_pic_tv).setOnClickListener(CreateDecorationActivity.this);
            bottomView.findViewById(R.id.mine_edit_take_pic_tv).setOnClickListener(CreateDecorationActivity.this);
            bottomView.findViewById(R.id.mine_edit_select_pic_tv).setOnClickListener(CreateDecorationActivity.this);
            bottomView.findViewById(R.id.mine_edit_title_tv).setVisibility(View.GONE);
        }
        bottomSheetDialog.show();
    }

    /**
     * 关闭dialog
     */
    private void dismissBottomSheetDialog() {
        if (bottomSheetDialog != null) {
            if (bottomSheetDialog.isShowing()) {
                bottomSheetDialog.dismiss();
            }
        }

    }

    /**
     * 对icons集合处理
     *
     * @return
     */
    private List<String> reSortIconList(List<String> icons) {
        List<String> icons_new = new ArrayList<>();
        for (String icon : icons) {
            if (!"-1".equals(icon)) {
                icons_new.add(icon);
            }
        }
        if (icons.size() < maxCount) {
            icons_new.add("-1");
        }
        return icons_new;
    }

    /**
     * 获取适配器中 不是-1的数据
     *
     * @param arrays
     * @return
     */
    private List<String> getAdapterDateWithAllPhoto(List<String> arrays) {
        List<String> icons_new = new ArrayList<>();
        for (String icon : arrays) {
            if (!"-1".equals(icon)) {
                icons_new.add(icon);
            }
        }
        return icons_new;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;

            case R.id.fitment_start_time_ly:
                //开始时间
                setTime("start");
                break;

            case R.id.fitment_end_time_ly:

                //结束时间
                setTime("end");
                break;
            case R.id.fitment_clear_type:
                //清运类型
                List<String> arrlist = new ArrayList<>();
                arrlist.add("物业公司清理（收费）");
                arrlist.add("自己清理");
                PickerManager.getInstance().showOptionPicker(this, arrlist,new PickerManager.OnOptionPickerSelectedListener() {
                    @Override
                    public void onOptionsSelect(int options1, int option2, int options3, View v) {
                        mFitmentClearType.setText(arrlist.get(options1));
                    }
                });
                break;
            case R.id.entry_confirm:
                int u = mAddVisitorDrivedSwitch.isChecked() ? 1 : 0;
                Log.i("TAG", u + "------");
                //提交
                Map<String, String> map = new HashMap<>();
                if (mAddVisitorDrivedSwitch.isChecked()) {
                    //是否委托代理人
                    if (!StrUtils.isStringValueOk(mFitmentAgentName.getText().toString())) {
                        showToast("委托代理人不能为空！");
                        return;
                    }
                    //代理人姓名
                    map.put("proxyName", mFitmentAgentName.getText().toString());

                    if (!StrUtils.isStringValueOk(mFitmentAgentMobile.getText().toString())) {
                        showToast("委托代理人手机号不能为空！");
                        return;
                    }
                    if (!PubUtil.isMobileNO(mFitmentAgentMobile.getText().toString())) {
                        showToast("手机号格式不正确！");
                        return;
                    }
                    map.put("proxyMobile", mFitmentAgentMobile.getText().toString());

                    //代理人身份证
                    if (!StrUtils.isStringValueOk(mFitmentAgentNumber.getText().toString())) {

                        showToast("委托代理人身份证号不能为空！");
                        return;
                    }
                    if (!PubUtil.isIDCard(mFitmentAgentNumber.getText().toString())) {
                        showToast("身份证格式错误！");
                        return;
                    }
                    map.put("proxyNum", mFitmentAgentNumber.getText().toString());
                }
                //装修开始时间
                if ("请选择".equals(mFitmentStartTime.getText().toString())) {
                    showToast("请选择装修开始时间！");
                    return;
                }
                map.put("startTime", mFitmentStartTime.getText().toString());
                //装修结束时间
                if ("请选择".equals(mFitmentEndTime.getText().toString())) {
                    showToast("请选择装修结束时间！");
                    return;
                }
                map.put("endTime", mFitmentEndTime.getText().toString());
                //是否自装
                if (!add_visitor_drived_switch_.isChecked()) {

                    //装修公司
                    if (!StrUtils.isStringValueOk(mFitmentCompanyName.getText().toString())) {
                        showToast("装修公司不能为空！");
                        return;
                    }
                    map.put("companyName", mFitmentCompanyName.getText().toString());
                    //装修公司负责人
                    if (!StrUtils.isStringValueOk(mFitmentCompanyPeople.getText().toString())) {
                        showToast("装修负责人不能为空！");
                        return;
                    }
                    map.put("companyHandlerName", mFitmentCompanyPeople.getText().toString());
                    //装修人联系电话
                    if (!StrUtils.isStringValueOk(mFitmentCompanyMobile.getText().toString())) {
                        showToast("装修负责人联系电话不能为空！");
                        return;
                    }
                    if (!PubUtil.isMobileNO(mFitmentCompanyMobile.getText().toString())) {
                        showToast("手机号格式不正确！");
                        return;
                    }
                    map.put("companyHandlerMobile", mFitmentCompanyMobile.getText().toString());
                    if (uploadImagesMap.size() > 0) {
                        if (!uploadImagesMap.containsKey(idCardFrontKey)) {
                            showToast("请选择身份证正面");
                            return;
                        }
                        if (!uploadImagesMap.containsKey(idCardBackKey)) {
                            showToast("请选择身份证背面");
                            return;
                        }
                        if (!uploadImagesMap.containsKey(busnessLicenceKey)) {
                            showToast("请选择营业执照");
                            return;
                        }
                        if (!uploadImagesMap.containsKey(qualificationKey)) {
                            showToast("请选择资质证书");
                            return;
                        }
                    } else {
                        showToast("请选择身份证正面");
                        return;
                    }
                }

                //装修图纸
                if (selectedPicsAdapter.getData().size() < 2) {
                    showToast("请选择装修图纸");
                    return;

                }
                //装修内容
                if (!StrUtils.isStringValueOk(mFitmentContent.getText().toString())) {
                    showToast("装修内容必填！");
                    return;
                }
                map.put("content", mFitmentContent.getText().toString());

                //装修清运方式
                map.put("cleanType", mFitmentClearType.getText().toString());
                showProgressDialog();
                if (uploadImagesMap.size() > 0) {
                    RxScheduler.doOnIoThread(new RxScheduler.IOTask<Void>() {
                        @Override
                        public void doOnIOThread() {

                            List<String> idCardFrontLocal = null;
                            List<String> idCardBackLocal = null;
                            List<String> busnessLicence = null;
                            List<String> qualification = null;
                            List<String> drawingLocal = null;
                            Iterator<Map.Entry<String, List<String>>> iterator = uploadImagesMap.entrySet().iterator();
                            while (iterator.hasNext()) {
                                Map.Entry<String, List<String>> entry = iterator.next();
                                String key = entry.getKey();
                                List<String> value = entry.getValue();
                                switch (key) {
                                    case idCardFrontKey:
                                        idCardFrontLocal = value;
                                        break;
                                    case idCardBackKey:
                                        idCardBackLocal = value;
                                        break;
                                    case busnessLicenceKey:
                                        busnessLicence = value;
                                        break;
                                    case qualificationKey:
                                        qualification = value;
                                        break;
                                    case drawingKey:
                                        drawingLocal = value;
                                        break;
                                    default:
                                        break;
                                }
                            }
                            if (idCardFrontLocal != null && idCardBackLocal != null) {
                                idCardFrontLocal.addAll(idCardBackLocal);
                                String idCardServerPath = ossUploadManager.getPhotoPathOfUploadedToOssServer(CreateDecorationActivity.this, idCardFrontLocal);
                                map.put("companyHandlerNumImg", idCardServerPath);
                            }
                            if (busnessLicence != null) {
                                String busnessLicenceServerPath = ossUploadManager.getPhotoPathOfUploadedToOssServer(CreateDecorationActivity.this, busnessLicence);
                                map.put("companyLicenseImg", busnessLicenceServerPath);
                            }
                            if (qualification != null) {
                                String qualificationServerPath = ossUploadManager.getPhotoPathOfUploadedToOssServer(CreateDecorationActivity.this, qualification);
                                map.put("companyQualificationImg", qualificationServerPath);
                            }
                            if (drawingLocal != null) {
                                String drawingServerPath = ossUploadManager.getPhotoPathOfUploadedToOssServer(CreateDecorationActivity.this, drawingLocal);
                                map.put("drawingImg", drawingServerPath);
                            }
                            getPresenter().addFitment(map, UserInfoUtil.getInstance().getUserId(), UserInfoUtil.getInstance().getVillageId(), UserInfoUtil.getInstance().getRoomId(), u, !add_visitor_drived_switch_.isChecked() ? 1 : 0, DecorationContrat.ADDFITMENTENTRY);
                        }
                    });
                } else {
                    getPresenter().addFitment(map, UserInfoUtil.getInstance().getUserId(), UserInfoUtil.getInstance().getVillageId(), UserInfoUtil.getInstance().getRoomId(), u, !add_visitor_drived_switch_.isChecked() ? 1 : 0, DecorationContrat.ADDFITMENTENTRY);

                }


                break;
            case R.id.id_card_front_iv:
                //选择身份证图片 正面
                currentClickedImageKey = idCardFrontKey;
                showSelectPhotoLayout();
                break;
            case R.id.id_card_back_iv:
                //选择身份证图片 反面
                currentClickedImageKey = idCardBackKey;
                showSelectPhotoLayout();

                break;
            case R.id.id_card_licence_iv:
                //营业执照
                currentClickedImageKey = busnessLicenceKey;
                showSelectPhotoLayout();

                break;
            case R.id.id_card_qualification_iv:
                //资格证书

                currentClickedImageKey = qualificationKey;
                showSelectPhotoLayout();

                break;
            case R.id.mine_edit_cancel_pic_tv://替换头像中的取消按钮
                dismissBottomSheetDialog();
                break;
            case R.id.mine_edit_take_pic_tv://替换头像中的拍照按钮
               takePicturesFromActivity(bottomSheetDialog);

                break;
            case R.id.mine_edit_select_pic_tv:////替换头像中的从相册中选取按钮
                checkAppPermissions(new CheckPermListener() {
                    @Override
                    public void agreeAllPermission() {
                        dismissBottomSheetDialog();
                        Intent intent = new Intent(CreateDecorationActivity.this, ImageMainActivity.class);
                        if (drawingKey.equals(currentClickedImageKey)) {
                            icons.clear();
                            icons = reSortIconList(selectedPicsAdapter.getData());
                            intent.putExtra("MAX_SEND", maxCount - icons.size());
                        } else {
                            maxCount = 1;
                            spanCount = 1;
                            intent.putExtra("MAX_SEND", maxCount);
                        }
                        intent.putExtra("action-original", true);
                        startActivityForResult(intent, ActivityResultManager.SYSTEM_PICTURE);
                    }

                    @Override
                    public void selectedAllPermission() {

                    }
                }, R.string.perm_camera_store, PubUtil.promissions[1], PubUtil.promissions[3]);




                break;
            case R.id.id_card_front_delete_icon:
                uploadImagesMap.remove(idCardFrontKey);
                mIdCardFrontIv.setImageResource(R.mipmap.id_card_front_icon);
                mIdCardFrontDeleteIcon.setVisibility(View.GONE);
                break;
            case R.id.id_card_back_delete_icon:
                uploadImagesMap.remove(idCardBackKey);
                mIdCardBackIv.setImageResource(R.mipmap.id_card_back_icon);
                mIdCardBackDeleteIcon.setVisibility(View.GONE);
                break;
            case R.id.licence_delete_icon:
                uploadImagesMap.remove(busnessLicenceKey);
                mIdCardLicenceIv.setImageResource(R.mipmap.business_license);
                mLicenceDeleteIcon.setVisibility(View.GONE);
                break;
            case R.id.qualification_delete_icon:
                uploadImagesMap.remove(qualificationKey);
                mIdCardQualificationIv.setImageResource(R.mipmap.qualification_icon);
                mQualificationDeleteIcon.setVisibility(View.GONE);
                break;
        }
    }


    //时间选择器
    private void setTime(String type) {
        Calendar startDate = Calendar.getInstance();
        int currentYear = getCurrentYear();
        startDate.set(currentYear, 1, 0);
        Calendar endDate = Calendar.getInstance();
        endDate.set(currentYear + 1, 12, 0);

        //时间选择器
        PickerManager.getInstance().showTimePickerViewIncludeRangDate(this, null, "", new PickerManager.OnTimePickerTimeSelectedListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                if ("start".equals(type)) {
                    mFitmentStartTime.setText(CalendarUtil.getTimeFromDate("yyyy-MM-dd", date));
                    if (setImage(mFitmentStartTime.getText().toString())) {

                        mFitmentStartTimeImage.setVisibility(View.VISIBLE);
                    } else {

                        mFitmentStartTimeImage.setVisibility(View.GONE);
                    }
                } else {
                    mFitmentEndTime.setText(CalendarUtil.getTimeFromDate("yyyy-MM-dd", date));
                    if (setImage(mFitmentEndTime.getText().toString())) {

                        mFitmentEndTimeImage.setVisibility(View.VISIBLE);
                    } else {

                        mFitmentEndTimeImage.setVisibility(View.GONE);
                    }
                }
            }
        },startDate,endDate);

    }

    public boolean setImage(String content) {
        if ("请输入".equals(content)) {

            return true;
        }

        return false;

    }

    @Override
    public void startLoading(String tag) {

    }

    @Override
    public void stopLoading(String tag) {

    }

    @Override
    public void updateView(Object o, String tag) {
        FitmentFireParticularsBean particularsBean = (FitmentFireParticularsBean) o;
        if (particularsBean != null) {
            showToast(particularsBean.getMessage());
            setResult(ActivityResultManager.FITMENT_SW);
            finish();
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
        Log.i("TAG", tag);
        showToast(tag);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (-1 == resultCode) {
            if (requestCode == ActivityResultManager.SYSTEM_PICTURE) {
                String imagePath = "";
                ArrayList<Uri> images = data.getParcelableArrayListExtra("result");
                for (int i = 0; i < images.size(); i++) {
                    imagePath = images.get(i).getPath();
                    if (drawingKey.equals(currentClickedImageKey)) {
                        icons.add(imagePath);
                    }
                }
                switch (currentClickedImageKey) {
                    case idCardFrontKey:
                        mIdCardFrontDeleteIcon.setVisibility(View.VISIBLE);
                        saveTakedPhotosForUpload(mIdCardFrontIv, idCardFrontKey, imagePath);
                        break;
                    case idCardBackKey:
                        mIdCardBackDeleteIcon.setVisibility(View.VISIBLE);
                        saveTakedPhotosForUpload(mIdCardBackIv, idCardBackKey, imagePath);
                        break;
                    case busnessLicenceKey:
                        mLicenceDeleteIcon.setVisibility(View.VISIBLE);
                        saveTakedPhotosForUpload(mIdCardLicenceIv, busnessLicenceKey, imagePath);
                        break;
                    case qualificationKey:
                        mQualificationDeleteIcon.setVisibility(View.VISIBLE);
                        saveTakedPhotosForUpload(mIdCardQualificationIv, qualificationKey, imagePath);
                        break;
                    case drawingKey:
                        saveSelectedPhotosToMap(drawingKey, getAdapterDateWithAllPhoto(icons));
                        selectedPicsAdapter.setNewData(reSortIconList(icons));
                        break;
                    default:
                        break;
                }


            } else if (requestCode == ActivityResultManager.TAKE_PICTURE) {
                Bitmap bitmap = ImageUtil.readPictureDegreeToForwordBitmap(FileUtil.getHeadPicRootPath(this));
                String userHeadPic = ImageUtil.saveCroppedImage(FileUtil.getHeadPicDir(this), bitmap);
                switch (currentClickedImageKey) {
                    case idCardFrontKey:
                        saveTakedPhotosForUpload(mIdCardFrontIv, idCardFrontKey, userHeadPic);
                        break;
                    case idCardBackKey:
                        saveTakedPhotosForUpload(mIdCardBackIv, idCardBackKey, userHeadPic);
                        break;
                    case busnessLicenceKey:
                        saveTakedPhotosForUpload(mIdCardLicenceIv, busnessLicenceKey, userHeadPic);
                        break;
                    case qualificationKey:
                        saveTakedPhotosForUpload(mIdCardQualificationIv, qualificationKey, userHeadPic);
                        break;
                    case drawingKey:
                        icons.add(userHeadPic);
                        selectedPicsAdapter.setNewData(reSortIconList(icons));
                        saveSelectedPhotosToMap(drawingKey, getAdapterDateWithAllPhoto(icons));
                        break;
                    default:
                        break;
                }

            }
        }

    }

    /**
     * 保存拍照存储本地的路径
     *
     * @param userHeadPic
     */
    private void saveTakedPhotosForUpload(ImageView imageView, String key, String userHeadPic) {
        GlideManagerUtil.getInstance(this).loadNormalPic(userHeadPic,R.drawable.default_image,imageView);
        List<String> arrays = new ArrayList<>();
        arrays.add(userHeadPic);
        saveSelectedPhotosToMap(key, arrays);
    }

    /**
     * 将选择的图片放到map中
     *
     * @param key
     */
    private void saveSelectedPhotosToMap(String key, List<String> arrays) {
        if (uploadImagesMap.containsKey(key)) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                uploadImagesMap.replace(key, arrays);
            } else {
                uploadImagesMap.remove(key);
                uploadImagesMap.put(key, arrays);
            }
        } else {
            uploadImagesMap.put(key, arrays);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


}
