package com.ghs.ghshome.models.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ghs.ghshome.R;
import com.ghs.ghshome.base.BaseActivity;
import com.ghs.ghshome.base.network.RequestStatus;
import com.ghs.ghshome.bean.LoginBean;
import com.ghs.ghshome.bean.RoomListBean;
import com.ghs.ghshome.bean.UserAndRoomBean;
import com.ghs.ghshome.models.homePage.seed.SeedTaskAdapter;
import com.ghs.ghshome.models.checkIdentity.SelectVillageActivity;
import com.ghs.ghshome.models.main.MainActivity;
import com.ghs.ghshome.models.mine.set.UserProtocalActivity;
import com.ghs.ghshome.tools.ActivityManagerTool;
import com.ghs.ghshome.tools.ActivityResultManager;
import com.ghs.ghshome.tools.HawkProperty;
import com.ghs.ghshome.tools.PubUtil;
import com.ghs.ghshome.tools.StrUtils;
import com.ghs.ghshome.tools.YouMengCustomEvent;
import com.ghs.ghshome.wxapi.weixinauth.IWeiXinAuthCallBack;
import com.ghs.ghshome.wxapi.weixinauth.IWeiXinAuthListener;
import com.orhanobut.hawk.Hawk;
import com.umeng.analytics.MobclickAgent;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.List;
import java.util.Map;

/**
 * created by tobato
 * created date 2019/5/20 17:30.
 * application   登录
 * 逻辑整理：登录成功后 调用获取小区的那个接口
 * 手机号登录：登录成功 调用小区接口
 * 微信登录：登录成功后，查看是否绑定手机号，如果绑定了，调用小区接口，如果没有绑定，待绑定完，调用小区接口
 * <p>
 * 注：如果调用小区接口 有小区，如果之前没有选中的小区，默认选中第一个小区
 */
public class LoginActivity extends BaseActivity<LoginContract.ILoginView, LoginPresent> implements LoginContract.ILoginView, IWeiXinAuthCallBack, View.OnClickListener, RequestStatus {

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
    private ImageView mLoginWeixinIv;
    /**
     * 点击登录即表示您已同意《光合家用户协议》
     */
    private TextView mUserProtocalTv;
    private int userId = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PubUtil.isQuiteCurrentAccount = false;
    }

    @Override
    public void getDate() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onEvent(this, YouMengCustomEvent.LOGIN);

    }

    @Override
    public void initLayoutView() {
        initView();

    }

    @Override
    public void setLayout() {
        setContentView(R.layout.activity_login);
    }


    @Override
    public LoginPresent creatPresenter() {
        return new LoginPresent();
    }


    @Override
    public void startLoading(String tag) {
        showProgressDialog();
    }

    @Override
    public void stopLoading(String tag) {

    }

    @Override
    public void updateView(Object o, String tag) {
        switch (tag) {
            case LoginContract.CHECK_CODE:
//                getPresenter().checkCodeReceived();
                break;
            case LoginContract.LOGIN_MOBILE:
                loginSuccess((LoginBean) o);
                break;
            case LoginContract.UN_BOUND_MOBILE://未绑定手机
//                startActivityForResult(new Intent(this, BoundMobileActivity.class), ActivityResultManager.BOUND_MOBILE);
                break;
            case LoginContract.LOGIN_WEIXIN:
                loginSuccess((LoginBean) o);

                break;
            default:
                break;
        }

    }

    /**
     * 登录成功
     *
     * @param o
     */
    private void loginSuccess(LoginBean o) {

        LoginBean user = o;
        if (user != null) {
            //如果已经绑定过,直接登录
            userId = user.getData().getId();
            Hawk.put(HawkProperty.LOGIN_BEAN, user);
            Hawk.put(HawkProperty.APP_TOKEN, user.getData().getToken());
            new LoginModel().getUserRoomList(this, userId, LoginContract.GET_ROOMS);
        }
    }

    @Override
    public void onStart(String tag) {

    }

    @Override
    public void onSuccess(Object o, String tag) {
        RoomListBean roomListBean = (RoomListBean) o;
        List<UserAndRoomBean.DataBean.GhsUserRoomDOBean> dataBeans = roomListBean.getData();
        if (dataBeans != null && dataBeans.size() > 0) {
            //查看之前有没有选中过当前小区
            if (!mUserInfoUtil.isSelectedCurrentVillage()) {
                //没有选过，默认选中第一个
                mUserInfoUtil.saveCurrentVillageBean(dataBeans.get(0));
            }
        }else{
            PubUtil.LOGIN_ENTER = 4;
        }


        execSeedTask(SeedTaskAdapter.SEED_TASK_LOGIN, -1);
        finishLoginActivity();


    }

    @Override
    public void onError(String tag) {
        if (StrUtils.isStringValueOk(tag)) {
            if (tag.contains("未检测到小区权限")) {
                if (Hawk.contains(mUserInfoUtil.getCurrentVillageKey())) {
                    Hawk.delete(mUserInfoUtil.getCurrentVillageKey());
                }
                execSeedTask(SeedTaskAdapter.SEED_TASK_LOGIN, -1);
                PubUtil.LOGIN_ENTER = 4;
                finishLoginActivity();
            }else{
                showToast(tag);
            }
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == ActivityResultManager.BOUND_MOBILE) {
            new LoginModel().getUserRoomList(this, userId, LoginContract.GET_ROOMS);

        }

    }

    /**
     * 关闭当前活动
     *
     */
    private void finishLoginActivity() {
        getPresenter().checkCodeReceived();
        switch (PubUtil.LOGIN_ENTER) {

            case 3:
                PubUtil.tokenExpiredFirstNotice = true;
                startActivity(new Intent(this, MainActivity.class));
                break;
            case 4:
                startActivity(new Intent(this, SelectVillageActivity.class));
                break;
            default:
                startActivity(new Intent(this, MainActivity.class));
                break;
        }

        finish();
    }


    private void initView() {
        mLoginMobileEt = (EditText) findViewById(R.id.login_mobile_et);
        mLoginSendCheckCodeTv = (TextView) findViewById(R.id.login_send_check_code_tv);
        mLoginSendCheckCodeTv.setOnClickListener(this);
        mLoginCheckCodeEt = (EditText) findViewById(R.id.login_check_code_et);
        mLoginConfirmTv = (TextView) findViewById(R.id.login_confirm_tv);
        mLoginConfirmTv.setOnClickListener(this);
        mLoginWeixinIv = (ImageView) findViewById(R.id.login_weixin_iv);
        mLoginWeixinIv.setOnClickListener(this);
        String mobile = getIntent().getStringExtra(ActivityResultManager.SAVED_MOBILE);
        String mobile_token_expired = getIntent().getStringExtra(ActivityResultManager.SAVED_MOBILE_TOKEN_EXPIRED);
        if (StrUtils.isStringValueOk(mobile)) {
            mLoginMobileEt.setText(mobile);
//            PubUtil.showSoftInputFromWindow(this, mLoginCheckCodeEt);
        } else {
            if (StrUtils.isStringValueOk(mobile_token_expired)) {
                showToast("登录已过期,请重新登录");
                mLoginMobileEt.setText(mobile_token_expired);
//                PubUtil.showSoftInputFromWindow(this, mLoginCheckCodeEt);
            } else {
//                PubUtil.showSoftInputFromWindow(this, mLoginMobileEt);

            }

        }

        mUserProtocalTv = (TextView) findViewById(R.id.user_protocal_tv);
        String content = mUserProtocalTv.getText().toString().trim();
        StrUtils.setTextPartColor(mUserProtocalTv, content, content.lastIndexOf("《"), content.length(), "#53C68B");
        mUserProtocalTv.setOnClickListener(this);
    }


    @Override
    public void updateSendCheckCodeViewStatus(int second) {
        if (second > 0) {
            mLoginSendCheckCodeTv.setText(second + "s" + "后重新获取 ");
            mLoginSendCheckCodeTv.setClickable(false);
            mLoginSendCheckCodeTv.setTextColor(ContextCompat.getColor(this, R.color.gray_deeper));
        } else {
            mLoginSendCheckCodeTv.setText("获取验证码");
            mLoginSendCheckCodeTv.setClickable(true);
            mLoginSendCheckCodeTv.setTextColor(ContextCompat.getColor(this, R.color.text_press));


        }
    }

    @Override
    public void checkFormatError(String error) {
        showToast(error);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void AuthFinished(Map<String, String> data) {
    }

    @Override
    public void AuthError(Throwable throwable) {
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_send_check_code_tv:
                if (isNetWorkConnected()) {
                    getPresenter().sendCheckCode(mLoginMobileEt.getText().toString().trim());
                }

                break;
            case R.id.login_confirm_tv:
                hideKeyboard(v);
                getPresenter().loginByTelNo(mLoginMobileEt.getText().toString().trim(), mLoginCheckCodeEt.getText().toString().trim());

                break;
            case R.id.login_weixin_iv:
                UMShareAPI.get(this).getPlatformInfo(this, SHARE_MEDIA.WEIXIN, new IWeiXinAuthListener(this));
                break;
            default:
                break;
            case R.id.user_protocal_tv:
                startActivity(new Intent(this, UserProtocalActivity.class));
                break;
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        hideKeyboard(mLoginConfirmTv);
        ActivityManagerTool.getInstance().finishApp();
    }
}

