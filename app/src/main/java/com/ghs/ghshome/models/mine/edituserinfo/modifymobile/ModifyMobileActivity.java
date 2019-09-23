package com.ghs.ghshome.models.mine.edituserinfo.modifymobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ghs.ghshome.R;
import com.ghs.ghshome.base.BaseActivity;
import com.ghs.ghshome.bean.LoginBean;
import com.ghs.ghshome.models.justtalk.JCManager;
import com.ghs.ghshome.models.login.LoginContract;
import com.ghs.ghshome.tools.ActivityResultManager;
import com.ghs.ghshome.tools.HawkProperty;
import com.ghs.ghshome.tools.UserInfoUtil;
import com.orhanobut.hawk.Hawk;

/**
 * created by tobato
 * created date 2019/6/5 18:59.
 * application   修改手机号
 */
public class ModifyMobileActivity extends BaseActivity<ModifyMobileContract.IModifyMobileView, ModifyMobilePresent> implements ModifyMobileContract.IModifyMobileView, View.OnClickListener {

    /**
     * 13578790511
     */
    private EditText mLoginMobileEt;
    /**
     * 发送验证码
     */
    private TextView mLoginSendCheckCodeTv;
    private EditText mLoginCheckCodeEt;
    /**
     * 登 录
     */
    private TextView mLoginConfirmTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void getDate() {

    }

    @Override
    public void initLayoutView() {
        initView();
        initActionBar("修改手机号", "", R.color.app_white);

    }

    @Override
    public void setLayout() {
        setContentView(R.layout.activity_modify_mobile);

    }


    @Override
    public ModifyMobilePresent creatPresenter() {
        return new ModifyMobilePresent();
    }


    private void initView() {
        mLoginMobileEt = (EditText) findViewById(R.id.login_mobile_et);
        mLoginSendCheckCodeTv = (TextView) findViewById(R.id.login_send_check_code_tv);
        mLoginSendCheckCodeTv.setOnClickListener(this);
        mLoginCheckCodeEt = (EditText) findViewById(R.id.login_check_code_et);
        mLoginConfirmTv = (TextView) findViewById(R.id.login_confirm_tv);
        mLoginConfirmTv.setOnClickListener(this);
        mLoginConfirmTv.setText("提交");
    }

    @Override
    public void updateSendCheckCodeViewStatus(int second) {
        if (second > 0) {
            mLoginSendCheckCodeTv.setText("重新发送 " + second + "s");
            mLoginSendCheckCodeTv.setClickable(false);
            mLoginSendCheckCodeTv.setTextColor(getResources().getColor(R.color.unclick_gray));
        } else {
            mLoginSendCheckCodeTv.setText("发送验证码");
            mLoginSendCheckCodeTv.setClickable(true);
            mLoginSendCheckCodeTv.setTextColor(getResources().getColor(R.color.app_black));

        }
    }

    @Override
    public void checkFormatError(String error) {
        showToast(error);
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
            case LoginContract.CHECK_CODE:
//                getPresenter().checkCodeReceived();
//                mLoginCheckCodeEt.setText((String) o);
                break;
            case ModifyMobileContract.MODIFY_MOBILE:
                //登出当前设备
                JCManager.getInstance().client.logout();
                showToast("修改成功");
                String mobile = mLoginMobileEt.getText().toString().trim();
                LoginBean loginBean = Hawk.get(HawkProperty.LOGIN_BEAN);
                if (loginBean != null) {
                    if (loginBean.getData() != null) {
                        loginBean.getData().setMobile(mobile);
                        Hawk.put(HawkProperty.LOGIN_BEAN,loginBean);
                    }
                }
                Intent intent = new Intent();
                intent.putExtra("MODIFYED_MOBILE", mobile);
                setResult(ActivityResultManager.EDIT_USER_MODIFY_MOBILE, intent);
                finish();
                break;
            case ModifyMobileContract.MODIFY_MOBILE_FAILED:
                showToast((String) o);
                break;
            default:
                break;
        }
    }

    @Override
    public void onError(String tag) {
        showToast(tag);
    }

    @Override
    public void onBackPressed() {
        getPresenter().checkCodeReceived();
        super.onBackPressed();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.login_send_check_code_tv:
                if (isNetWorkConnected()) {
                    getPresenter().sendCheckCode(mLoginMobileEt.getText().toString().trim());
                }
                break;
            case R.id.login_confirm_tv:
                    showProgressDialog();
                    getPresenter().commitModify(UserInfoUtil.getInstance().getUserId(), mLoginMobileEt.getText().toString().trim(), mLoginCheckCodeEt.getText().toString().trim());
                break;
        }
    }
}
