package com.ghs.ghshome.models.login;

import android.text.TextUtils;

import com.ghs.ghshome.base.BasePresent;
import com.ghs.ghshome.base.network.RequestStatus;
import com.ghs.ghshome.base.network.networkProxy.HttpProxy;
import com.ghs.ghshome.base.network.okgo.NetResponseCallBack;
import com.ghs.ghshome.bean.PropertyTelBean;
import com.ghs.ghshome.models.login.sendcode.ISendCode;
import com.ghs.ghshome.models.login.sendcode.SendCodeModel;
import com.ghs.ghshome.tools.Contract;
import com.ghs.ghshome.tools.GsonManager;
import com.ghs.ghshome.tools.PubUtil;

/**
 * Author:wang_sir
 * Time:2018/7/9 17:18
 * Description:This is LoginPresent
 */
public class LoginPresent extends BasePresent<LoginContract.ILoginView> implements LoginContract.ILoginPresent, RequestStatus, ISendCode.IUpdateView {

    private final LoginModel model;
    private boolean run = false;
    private final SendCodeModel sendCodeModel;

    public LoginPresent() {
        model = new LoginModel();
        sendCodeModel = new SendCodeModel(this);

    }

    @Override
    public void onStart(String tag) {
        if (getView() != null) {
            getView().startLoading(tag);
        }
    }

    @Override
    public void onSuccess(Object o, String tag) {
        if (getView() != null) {
            getView().updateView(o, tag);
        }

    }

    @Override
    public void onError(String tag) {
        if (getView() != null) {
            getView().onError(tag);
        }

    }

    @Override
    public void sendCheckCode(String mobile) {
        if (checkMobile(mobile)) {
            sendCodeModel.initGetTestCodeButtonStatus();
            model.getCheckCode(mobile, this);
        }
        ;
    }

    @Override
    public void loginByTelNo(String mobile, String code) {
        if (checkMobile(mobile)) {
            if (code != null && !TextUtils.isEmpty(code)) {
                model.loginByTelNo(mobile, code, this);
            } else {
                if (getView() != null) {
                    getView().checkFormatError("验证码为空");
                }
            }
        }

    }

    @Override
    public void checkCodeReceived() {
        sendCodeModel.checkCodeReceived();
    }


    /**
     * 检查手机号的格式
     */
    private boolean checkMobile(String mobile) {
        if (mobile == null || TextUtils.isEmpty(mobile)) {
            if (getView() != null) {
                getView().checkFormatError("手机号没有填写");
            }

            return false;
        }
        if (!PubUtil.isMobileNO(mobile)) {
            if (getView() != null) {
                getView().checkFormatError("手机号格式错误");

            }
            return false;
        }
        return true;

    }

    @Override
    public void loginByWeixin(String unionid, String tag) {
        model.loginByWeixin(unionid, this, tag);
    }


    @Override
    public void startTiming(int value) {
        if (getView() != null) {
            getView().updateSendCheckCodeViewStatus(value);
        }

    }

    @Override
    public void endTiming(int value) {
        if (getView() != null) {
            getView().updateSendCheckCodeViewStatus(value);

        }
    }


}
