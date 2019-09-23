package com.ghs.ghshome.models.propertyHall.decoration;

import android.os.Bundle;
import android.util.ArrayMap;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ghs.ghshome.R;
import com.ghs.ghshome.base.BaseActivity;
import com.ghs.ghshome.base.displayPhotos.SelectPhotosToUploadFragment;
import com.ghs.ghshome.bean.FitmentFireParticularsBean;
import com.ghs.ghshome.tools.ActivityResultManager;
import com.ghs.ghshome.tools.RxScheduler;
import com.ghs.ghshome.tools.StrUtils;
import com.ghs.ghshome.tools.UserInfoUtil;

import java.util.Map;

/**
 * 创建动火备案
 */
public class CreateFireEntryActivity extends BaseActivity<DecorationContrat.DecorationView, DecorationPresenter> implements View.OnClickListener, DecorationContrat.DecorationView {

    private SelectPhotosToUploadFragment selectPhotosFragment;
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
     * 请选择
     */
    private TextView mFitmentFireStartTime;
    private ImageView mFitmentStartTimeImage;
    /**
     * 请选择
     */
    private TextView mFitmentFireEndTime;
    private ImageView mFitmentEndTimeImage;
    /**
     * 如：厨房燃气灶
     */
    private EditText mFitmentFireLocation;
    /**
     * 天然气点火
     */
    private EditText mFitmentFireFacility;
    /**
     * 装修首次点火
     */
    private EditText mFitmentFireCause;
    /**
     * 煤气防泄漏检测
     */
    private EditText mFitmentFireMeasure;
    /**
     * 请输入
     */
    private EditText mFitmentFireOperator;
    /**
     * 请输入
     */
    private EditText mFitmentFireNumber;
    /**
     * 确定
     */
    private TextView mEntryConfirm;
    private LinearLayout mFitmentFireStartTimeLy;

    private LinearLayout mFitmentFireEndTimeLy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fitment_fire_entry);
        initView();

        initActionBar("动火备案", "");


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


    }


    private void initView() {


        selectPhotosFragment = (SelectPhotosToUploadFragment) getSupportFragmentManager().findFragmentById(R.id.select_photos_fg);
        selectPhotosFragment.setSpanCount(5, 9, 20,false).setEtMinHeightAndHint(35, "操作证照片");
        mFitmentRoomName = (TextView) findViewById(R.id.fitment_room_name);
        mFitmentProposer = (TextView) findViewById(R.id.fitment_proposer);
        mFitmentProposerMobile = (TextView) findViewById(R.id.fitment_proposer_mobile);
        mFitmentFireStartTime = (TextView) findViewById(R.id.fitment_fire_startTime);
        mFitmentStartTimeImage = (ImageView) findViewById(R.id.fitment_start_time_image);
        mFitmentFireEndTime = (TextView) findViewById(R.id.fitment_fire_endTime);
        mFitmentEndTimeImage = (ImageView) findViewById(R.id.fitment_end_time_image);
        mFitmentFireLocation = (EditText) findViewById(R.id.fitment_fire_location);
        mFitmentFireFacility = (EditText) findViewById(R.id.fitment_fire_facility);
        mFitmentFireCause = (EditText) findViewById(R.id.fitment_fire_cause);
        mFitmentFireMeasure = (EditText) findViewById(R.id.fitment_fire_measure);
        mFitmentFireOperator = (EditText) findViewById(R.id.fitment_fire_operator);
        mFitmentFireNumber = (EditText) findViewById(R.id.fitment_fire_number);
        mEntryConfirm = (TextView) findViewById(R.id.entry_confirm);
        mEntryConfirm.setOnClickListener(this);

        mFitmentRoomName.setText(UserInfoUtil.getInstance().getRoomName());
        mFitmentProposer.setText(UserInfoUtil.getInstance().getFullName());
        mFitmentProposerMobile.setText(UserInfoUtil.getInstance().getMobile());
        mFitmentFireStartTimeLy = (LinearLayout) findViewById(R.id.fitment_fire_startTime_ly);
        mFitmentFireStartTimeLy.setOnClickListener(this);
        mFitmentFireEndTimeLy = (LinearLayout) findViewById(R.id.fitment_fire_endTime_ly);
        mFitmentFireEndTimeLy.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.entry_confirm:
                Map<String, String> parameMap = new ArrayMap<>();
                if ("请选择".equals(mFitmentFireStartTime.getText().toString())) {
                    showToast("请选择开始时间");
                    return;
                }
                parameMap.put("startTime", mFitmentFireStartTime.getText().toString());

                if ("请选择".equals(mFitmentFireEndTime.getText().toString())) {
                    showToast("请选择结束时间");
                    return;
                }
                parameMap.put("endTime", mFitmentFireEndTime.getText().toString());

                //动火位置
                if (!StrUtils.isStringValueOk(mFitmentFireLocation.getText().toString())) {
                    showToast("请输入动火位置");
                    return;
                }
                parameMap.put("position", mFitmentFireLocation.getText().toString());

                //动火原因
                if (!StrUtils.isStringValueOk(mFitmentFireFacility.getText().toString())) {
                    showToast("请输入动火方式和设备");
                    return;
                }
                parameMap.put("method", mFitmentFireFacility.getText().toString());
                //动火原因
                if (!StrUtils.isStringValueOk(mFitmentFireCause.getText().toString())) {
                    showToast("请输入动火原因");
                    return;
                }
                parameMap.put("reason", mFitmentFireCause.getText().toString());

                //动火作业安全措施
                if (!StrUtils.isStringValueOk(mFitmentFireMeasure.getText().toString())) {
                    showToast("请输入动火作业安全措施");
                    return;
                }
                parameMap.put("security", mFitmentFireMeasure.getText().toString());

                //动火作业人
                if (!StrUtils.isStringValueOk(mFitmentFireOperator.getText().toString())) {
                    showToast("请输入动火作业人");
                    return;
                }
                parameMap.put("handlerName", mFitmentFireOperator.getText().toString());

                //操作证号
                if (!StrUtils.isStringValueOk(mFitmentFireNumber.getText().toString())) {
                    showToast("请输入操作证号");
                    return;
                }
                parameMap.put("papersNum", mFitmentFireNumber.getText().toString());


//                Log.i("TAG",UserInfoUtil.getInstance().getUserId()+"--"+ UserInfoUtil.getInstance().getVillageId()+ "--"+UserInfoUtil.getInstance().getRoomId()+"");
                showProgressDialog();
                RxScheduler.doOnIoThread(new RxScheduler.IOTask<Void>() {
                    @Override
                    public void doOnIOThread() {
                        //操作证照片
                        if (!StrUtils.isStringValueOk(selectPhotosFragment.uploadPhotosToOssForPath())) {
                            showToast("请上传操作证照片");
                            return;
                        }
                        parameMap.put("papersImg", selectPhotosFragment.uploadPhotosToOssForPath());
                        getPresenter().addFitmentFire(parameMap, UserInfoUtil.getInstance().getUserId(), UserInfoUtil.getInstance().getVillageId(), UserInfoUtil.getInstance().getRoomId(), DecorationContrat.ADDFITMENTFIREENTRY);

                    }});

                break;
            case R.id.fitment_fire_startTime_ly:
                showTimeView(mFitmentFireStartTime, "yyyy-MM-dd");
                break;
            case R.id.fitment_fire_endTime_ly:
                showTimeView(mFitmentFireEndTime, "yyyy-MM-dd");

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
        FitmentFireParticularsBean fitmentFireParticularsBean = (FitmentFireParticularsBean) o;
        if (fitmentFireParticularsBean != null) {
            showToast(fitmentFireParticularsBean.getMessage());
            setResult(ActivityResultManager.FITMENT_SW);
            finish();
        }

    }

    @Override
    public void onError(String tag) {
        showToast(tag);
    }
}
