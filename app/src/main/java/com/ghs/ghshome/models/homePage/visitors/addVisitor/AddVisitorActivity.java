package com.ghs.ghshome.models.homePage.visitors.addVisitor;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.Group;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.ghs.ghshome.R;
import com.ghs.ghshome.base.BaseActivity;
import com.ghs.ghshome.bean.VisitorsBean;
import com.ghs.ghshome.models.homePage.carPark.bindcarnum.BindCarNumActivity;
import com.ghs.ghshome.models.homePage.visitors.VisitorContract;
import com.ghs.ghshome.models.homePage.visitors.VisitorPresent;
import com.ghs.ghshome.tools.ActivityResultManager;
import com.ghs.ghshome.tools.CalendarUtil;
import com.ghs.ghshome.tools.IntentUtil;
import com.ghs.ghshome.tools.PickerManager;
import com.ghs.ghshome.tools.PubUtil;
import com.ghs.ghshome.tools.StrUtils;
import com.ghs.ghshome.tools.UserInfoUtil;

import java.util.Calendar;
import java.util.Date;

/**
 * created by tobato
 * created date 2018/11/19 14:43.
 * application   添加访客
 */

public class AddVisitorActivity extends BaseActivity<VisitorContract.IVisitorView, VisitorPresent> implements View.OnClickListener, VisitorContract.IVisitorView {

    private Group mAddVisitorCarNumGroup;
    /**
     * 请输入
     */
    private EditText mAddVisitorNameEt;
    /**
     * 2018.11.22
     */
    private TextView mAddVisitorVisitedDateTv;
    private ConstraintLayout mAddVisitorVisitedDateCl;
    private Switch mAddVisitorDrivedSwitch;
    /**
     * 添加车牌号
     */
    private TextView mAddVisitorCarNumTv;
    /**
     * 确定
     */
    private TextView mAddVisitorConfirmTv;
    private String carNum = "";
    private Date mDate;//访问日期
    private LinearLayout rootView;
    private EditText allot_key_phone_et;
    private TextView fitment_start_time;
    /**
     *
     */
    private TextView mAddVisitorCarNumTagTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setLayout() {
        setContentView(R.layout.activity_add_visitor);

    }

    @Override
    public void initLayoutView() {
        initActionBar("添加访客", null);
        initView();
        setOnTouchListenerOfViews(this, rootView);
    }

    @Override
    public VisitorPresent creatPresenter() {
        return new VisitorPresent();
    }

    @Override
    public void getDate() {

    }

    private void initView() {
        rootView = findViewById(R.id.add_visitor_root_ll);
        mAddVisitorCarNumGroup = (Group) findViewById(R.id.add_visitor_car_num_group);
        mAddVisitorNameEt = (EditText) findViewById(R.id.add_visitor_name_et);
        mAddVisitorVisitedDateTv = (TextView) findViewById(R.id.add_visitor_visited_date_tv);
//        mAddVisitorVisitedDateTv.setText(CalendarUtil.getTimeFromDate("yyyy年MM月dd日", new Date()));
        mAddVisitorVisitedDateCl = (ConstraintLayout) findViewById(R.id.add_visitor_visited_date_cl);
        mAddVisitorVisitedDateCl.setOnClickListener(this);
        mAddVisitorDrivedSwitch = (Switch) findViewById(R.id.add_visitor_drived_switch);
        if (mAddVisitorDrivedSwitch.isChecked()) {
            mAddVisitorCarNumGroup.setVisibility(View.VISIBLE);
        } else {
            mAddVisitorCarNumGroup.setVisibility(View.GONE);

        }
        mAddVisitorDrivedSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mAddVisitorCarNumGroup.setVisibility(View.VISIBLE);
                    mAddVisitorCarNumTagTv.setVisibility(View.VISIBLE);
                } else {
                    carNum = "";
                    mAddVisitorCarNumGroup.setVisibility(View.GONE);
                    mAddVisitorCarNumTagTv.setVisibility(View.GONE);
                }
            }
        });
        mAddVisitorCarNumTv = (TextView) findViewById(R.id.add_visitor_car_num_tv);
        mAddVisitorCarNumTv.setOnClickListener(this);
        mAddVisitorConfirmTv = (TextView) findViewById(R.id.add_visitor_confirm_tv);
        mAddVisitorConfirmTv.setOnClickListener(this);

        allot_key_phone_et = findViewById(R.id.allot_key_phone_et);
        fitment_start_time = findViewById(R.id.fitment_start_time);

        mAddVisitorCarNumTagTv = (TextView) findViewById(R.id.add_visitor_car_num_tag_tv);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.add_visitor_visited_date_cl:
                selectDate(mAddVisitorVisitedDateTv);
                break;
            case R.id.add_visitor_car_num_tv:
                PubUtil.ADD_CAR_NUM_ENTER = 1;
                startActivityForResult(new Intent(this, BindCarNumActivity.class), ActivityResultManager.ADD_VISITOR_ADD_CAR);

                break;
            case R.id.add_visitor_confirm_tv:
                String visitorName = mAddVisitorNameEt.getText().toString().trim();
                if (!StrUtils.isStringValueOk(visitorName)) {
                    Toast.makeText(getApplicationContext(), "请输入访客的姓名", Toast.LENGTH_LONG).show();
                    return;
                }
                if (!checkUserNameFormat(visitorName)) {
                    return;
                }
                String visitDate = mAddVisitorVisitedDateTv.getText().toString().trim();
                if (!StrUtils.isStringValueOk(visitDate)) {
                    Toast.makeText(getApplicationContext(), "请选择到访日期", Toast.LENGTH_LONG).show();
                    return;
                }
                showProgressDialog();
                visitDate = mDate == null ? CalendarUtil.getTimeFromDate("yyyy-MM-dd", new Date()) : CalendarUtil.getTimeFromDate("yyyy-MM-dd", mDate);

                UserInfoUtil userInfoUtil = UserInfoUtil.getInstance();

                getPresenter().addVisitors(userInfoUtil.getVillageId(), userInfoUtil.getRoomId(), userInfoUtil.getUserId(), visitorName, allot_key_phone_et.getText().toString(),
                        visitDate, carNum, VisitorContract.ADD_VISITOR);
                break;

        }
    }

    /**
     * 选择时间
     *
     * @param textView
     */
    private void selectDate(final TextView textView) {
        Calendar startDate = Calendar.getInstance();
        Calendar endDate = Calendar.getInstance();
        String startYear = CalendarUtil.getLastDateOfMonth(-1, "yyyy");
        String startMonth = CalendarUtil.getLastDateOfMonth(-1, "MM");
        String endYear = CalendarUtil.getLastDateOfMonth(10, "yyyy");
        String endMonth = CalendarUtil.getLastDateOfMonth(10, "MM");
        //正确设置方式 原因：注意事项有说明
        startDate.set(Integer.parseInt(startYear), Integer.parseInt(startMonth), 1);
        endDate.set(Integer.parseInt(endYear), Integer.parseInt(endMonth), 1);
        //时间选择器
        PickerManager.getInstance().showTimePickerViewIncludeRangDate(this, null, "", new PickerManager.OnTimePickerTimeSelectedListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                mDate = date;
                textView.setText(CalendarUtil.getTimeFromDate("yyyy年MM月dd日", date));
            }
        }, startDate, endDate);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (ActivityResultManager.ADD_VISITOR_ADD_CAR == resultCode) {
            if (data != null) {
                carNum = data.getStringExtra(ActivityResultManager.CAR_NUM);
                mAddVisitorCarNumTv.setText(StrUtils.initCarNumStatus(carNum));
            } else {
                mAddVisitorCarNumTv.setHint("添加车牌号");

            }
        }
    }


    @Override
    public void startLoading(String tag) {

    }

    @Override
    public void stopLoading(String tag) {

    }

    /**
     * @param o
     * @param tag
     */
    @Override
    public void updateView(Object o, String tag) {
        VisitorsBean.DataBean addVistorbeanDataBean = (VisitorsBean.DataBean) o;
        IntentUtil.getInstance().startActivityWithParcelableData(addVistorbeanDataBean, AddVisitorActivity.this, VistorDetailActivity.class);
        finish();
//        setResult(ActivityResultManager.ADD_VISITOR_ADD_VISITOR);
//        Toast.makeText(getApplicationContext(), "添加成功", Toast.LENGTH_LONG).show();
//        finish();
    }

    @Override
    public void onError(String tag) {
        showToast(tag);
    }
}
