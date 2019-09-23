package com.ghs.ghshome.models.homePage.keymanager.tenementmanager;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ghs.ghshome.R;
import com.ghs.ghshome.base.BaseActivity;
import com.ghs.ghshome.bean.MobileBean;
import com.ghs.ghshome.tools.ActivityResultManager;
import com.ghs.ghshome.tools.PickerManager;
import com.ghs.ghshome.tools.PubUtil;
import com.ghs.ghshome.tools.StrUtils;
import com.ghs.ghshome.tools.UserInfoUtil;

import java.util.Arrays;
import java.util.List;

public class AddTenementActivity extends BaseActivity<TenementContract.TenementView, TenementPresent> implements TenementContract.TenementView, View.OnClickListener {

    /**
     * 万通中心3003
     */
    private TextView mAddTenementMessage;
    private EditText mAddTenementMobile;
    private EditText mAddTenementName;

    private String[] strings = {"业主", "业主家人", "租客", "租客家人", "其他"};
    /**
     * 请选择
     */
    private TextView mAddTenementWay;
    /**
     * 确定添加授权
     */
    private TextView mAddKeyFab;
    private LinearLayout add_tenement_name_ly;
    private LinearLayout add_tenement_way_ly;

    @Override
    protected void setLayout() {
        setContentView(R.layout.activity_add_tenement);
        initView();
        initActionBar("添加认证住户", "");
    }

    @Override
    public TenementPresent creatPresenter() {
        return new TenementPresent();
    }

    @Override
    public void getDate() {

    }


    public void initView() {
        mAddTenementMessage = (TextView) findViewById(R.id.add_tenement_message);
        mAddTenementMessage.setText(PubUtil.getRoomMsg(UserInfoUtil.getInstance().getCurrentVillageBean()));
        mAddTenementMobile = (EditText) findViewById(R.id.add_tenement_mobile);
        mAddTenementMobile.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Log.i("TAG", i2 + "******" + i1 + "******" + i);
                String s = mAddTenementMobile.getText().toString();
                Log.i("TAG", s + "******");
                int length = s.length();
                Log.i("TAG", length + "******");

                if (length == 11) {
                    if (!PubUtil.isMobileNO(mAddTenementMobile.getText().toString())) {
                        showToast("手机号格式错误！");
                        return;
                    }
                    getPresenter().getFullNameByMobile(mAddTenementMobile.getText().toString(), "MOBILE");
                } else {
                    add_tenement_name_ly.setVisibility(View.GONE);
                    mAddTenementName.setText("");
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {


            }
        });
        mAddTenementName = (EditText) findViewById(R.id.add_tenement_name);
        mAddTenementWay = (TextView) findViewById(R.id.add_tenement_way);
        mAddKeyFab = (TextView) findViewById(R.id.add_key_fab);
        mAddKeyFab.setOnClickListener(this);
        add_tenement_name_ly = findViewById(R.id.add_tenement_name_ly);
        add_tenement_way_ly = findViewById(R.id.add_tenement_way_ly);
        add_tenement_way_ly.setOnClickListener(this);
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
            case "MOBILE":
                MobileBean mobileBean = (MobileBean) o;
                add_tenement_name_ly.setVisibility(View.VISIBLE);
                mAddTenementName.setText(mobileBean.getData());
                mAddTenementName.setFocusable(false);
                mAddTenementName.setFocusableInTouchMode(false);
                break;
            case "ADDTENNEMENT":
                stopProgressDialog();
                showToast("认证成功");
                setResult(ActivityResultManager.TENEMENT_DETAILS);
                finish();
                break;
            default:

                break;


        }

    }

    public int getWay(String str) {
        int way = 0;
        switch (str) {
            case "业主":
                way = 1;
                break;
            case "业主家人":
                way = 2;
                break;

            case "租客":
                way = 3;

                break;

            case "租客家人":
                way = 4;

                break;

            case "其他":
                way = 5;
                break;

        }

        return way;

    }

    //标签展示
    public void selectCreateLabel(final List<String> dataList) {

        PickerManager.getInstance().showOptionPicker(AddTenementActivity.this, dataList, new PickerManager.OnOptionPickerSelectedListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                mAddTenementWay.setText(dataList.get(options1));
            }
        });

    }

    @Override
    public void onError(String tag) {
        stopProgressDialog();
        if ("此电话号对应的用户没有录入姓名".equals(tag)) {
            add_tenement_name_ly.setVisibility(View.VISIBLE);
            mAddTenementName.setText("");
            mAddTenementName.setFocusable(true);
            mAddTenementName.setFocusableInTouchMode(true);
            mAddTenementName.requestFocus();

        } else {
            showToast(tag);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add_tenement_way_ly:
                selectCreateLabel(Arrays.asList(strings));
                break;
            case R.id.add_key_fab:
                if (!StrUtils.isStringValueOk(mAddTenementMobile.getText().toString())) {
                    showToast("请填写手机号码！");
                    return;
                }
                if (!StrUtils.isStringValueOk(mAddTenementName.getText().toString())) {
                    showToast("请填写住户姓名！");
                    return;
                }

                if (!StrUtils.isStringValueOk(mAddTenementWay.getText().toString()) || mAddTenementWay.getText().toString().equals("请选择")) {
                    showToast("请选择住户身份！");
                    return;
                }
                showProgressDialog();
                getPresenter().addTenement(UserInfoUtil.getInstance().getUserId(), UserInfoUtil.getInstance().getRoomId(), getWay(mAddTenementWay.getText().toString()), mAddTenementMobile.getText().toString(), mAddTenementName.getText().toString(), "ADDTENNEMENT");

                break;

        }
    }
}
