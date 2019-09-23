package com.ghs.ghshome.models.mine.edituserinfo.modifymobile;

import android.text.TextUtils;

import com.ghs.ghshome.base.BasePresent;
import com.ghs.ghshome.base.network.networkProxy.HttpProxy;
import com.ghs.ghshome.base.network.okgo.NetResponseCallBack;
import com.ghs.ghshome.models.login.sendcode.ISendCode;
import com.ghs.ghshome.models.login.sendcode.SendCodeModel;
import com.ghs.ghshome.tools.Contract;
import com.ghs.ghshome.tools.PubUtil;

/**
 * Author:wang_sir
 * Time:2018/7/16 18:54
 * Description:This is ModifyMobilePresent
 */
public class ModifyMobilePresent extends BasePresent<ModifyMobileContract.IModifyMobileView> implements ModifyMobileContract.IModifyMobilePresent, ISendCode.IUpdateView {

    private final SendCodeModel sendCodeModel;

    public ModifyMobilePresent() {
        sendCodeModel = new SendCodeModel(this);
    }

    @Override
    public void startTiming(int value) {
        getView().updateSendCheckCodeViewStatus(value);
    }

    @Override
    public void endTiming(int value) {
        if (getView() != null) {
            getView().updateSendCheckCodeViewStatus(value);

        }

    }
    /**
     * 检查手机号的格式
     */
    private boolean checkMobile(String mobile) {
        if (mobile == null || TextUtils.isEmpty(mobile)) {
            if (getView() == null) {
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
    public void sendCheckCode(String mobile) {
        if (checkMobile(mobile)) {
            sendCodeModel.initGetTestCodeButtonStatus();
            getCode(mobile);
        }

    }

    /**
     * 获取短信验证码
     * @param mobile
     */
    private void getCode(String mobile) {
        HttpProxy.getInstance()
                .params("mobile", mobile)
                .params("codeType", Contract.SMS_CODE_TYPE_UPDATEMOBILE)
                .postToNetwork(Contract.LOGIN_SMS_CODE,new NetResponseCallBack() {
                    @Override
                    public void onSuccess(String content) {
                        if (getView() != null) {
                        }
                    }

                    @Override
                    public void onError(String str) {
                        if (getView() != null) {
                            getView().onError(str);
                        }
                    }
                });


    }

    @Override
    public void checkCodeReceived() {
        sendCodeModel.checkCodeReceived();
    }

    @Override
    public void commitModify(int userId, String mobile, String smsCode) {
        if (checkMobile(mobile)) {
            if (smsCode != null && !TextUtils.isEmpty(smsCode)) {
                HttpProxy.getInstance()
                        .params("userId", userId)
                        .params("mobile", mobile)
                        .params("smsCode", smsCode)
                        .postToNetwork(Contract.MINE_UPDATE_MOBILE,new NetResponseCallBack() {
                            @Override
                            public void onSuccess(String content) {
                                if (getView() != null) {
                                    getView().updateView(PubUtil.getServerData(content), ModifyMobileContract.MODIFY_MOBILE);
                                }
                            }

                            @Override
                            public void onError(String str) {
                                if (getView() != null) {
                                    getView().onError(str);
                                }
                            }
                        });
            } else {
                if (getView() != null) {
                    getView().checkFormatError("验证码为空");
                }

            }

        }else{
            if (getView() != null) {
                getView().checkFormatError("请输入手机号");
            }

        }
    }

}
