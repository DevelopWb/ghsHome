package com.ghs.ghshome.models.login;


import com.ghs.ghshome.base.network.RequestStatus;
import com.ghs.ghshome.base.network.networkProxy.HttpProxy;
import com.ghs.ghshome.base.network.okgo.NetResponseCallBack;
import com.ghs.ghshome.bean.LoginBean;
import com.ghs.ghshome.bean.RoomListBean;
import com.ghs.ghshome.tools.Contract;
import com.ghs.ghshome.tools.GsonManager;
import com.ghs.ghshome.tools.PubUtil;

/**
 * Author:wang_sir
 * Time:2018/7/9 17:19
 * Description:This is LoginModel
 */
public class LoginModel implements LoginContract.ILoginModel {
    @Override
    public void getCheckCode(String mobile, final RequestStatus requestStatus) {
        HttpProxy.getInstance()
                .params("mobile", mobile)
                .params("codeType", Contract.SMS_CODE_TYPE_LOGIN)
                .postToNetwork(Contract.LOGIN_SMS_CODE, new NetResponseCallBack() {
                    @Override
                    public void onSuccess(String content) {
                        if (requestStatus != null) {
                            requestStatus.onSuccess("111", LoginContract.CHECK_CODE);
                        }
                    }

                    @Override
                    public void onError(String str) {
                        if (requestStatus != null) {
                            requestStatus.onError(str);
                        }
                    }
                });


    }

    @Override
    public void loginByTelNo(String mobile, String code, final RequestStatus requestStatus) {
        if (requestStatus != null) {
            requestStatus.onStart(LoginContract.CHECK_CODE);
        }
        HttpProxy.getInstance()
                .params("mobile", mobile)
                .params("smsCode", code)
                .postToNetwork(Contract.LOGIN_BY_TEL_NO, new NetResponseCallBack() {
                    @Override
                    public void onSuccess(String content) {
                        if (requestStatus != null) {
                            requestStatus.onSuccess(GsonManager.getInstance().parseJsonToBean(content, LoginBean.class), LoginContract.LOGIN_MOBILE);

                        }
                    }

                    @Override
                    public void onError(String str) {
                        if (requestStatus != null) {
                            requestStatus.onError(str);
                        }
                    }
                });


    }

    @Override
    public void loginByWeixin(String unionid, final RequestStatus requestStatus, final String tag) {
        HttpProxy.getInstance()
                .params("unionId", unionid)
                .postToNetwork(Contract.LOGIN_BY_WEIXIN, new NetResponseCallBack() {
                    @Override
                    public void onSuccess(String content) {
                        if (requestStatus != null) {
                            requestStatus.onSuccess(GsonManager.getInstance().parseJsonToBean(content, LoginBean.class), tag);
                        }
                    }

                    @Override
                    public void onError(String str) {
                        if (1001 == PubUtil.getServerCode(str)) {
                            requestStatus.onSuccess("", LoginContract.UN_BOUND_MOBILE);
                        } else {
                            requestStatus.onError(str);
                        }
                    }
                });
    }

    @Override
    public void getUserRoomList(RequestStatus requestStatus, int userId, String tag) {
        HttpProxy.getInstance()
                .params("userId", userId)
                .getToNetwork(Contract.LOGIN_USER_ROOMS, new NetResponseCallBack() {
                    @Override
                    public void onSuccess(String content) {
                        if (requestStatus != null) {
                            RoomListBean roomListBean = GsonManager.getInstance().parseJsonToBean(content, RoomListBean.class);
                            if (requestStatus != null) {
                                requestStatus.onSuccess(roomListBean, tag);
                            }
                        }
                    }

                    @Override
                    public void onError(String str) {
                        if (requestStatus != null) {
                            requestStatus.onError(str);
                        }
                    }
                });
    }
}
