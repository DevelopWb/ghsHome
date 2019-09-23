package com.ghs.ghshome.models.login;


import com.ghs.ghshome.base.BaseViewInterface;
import com.ghs.ghshome.base.network.RequestStatus;

/**
 * Author:wang_sir
 * Time:2018/7/9 17:18
 * Description:This is LoginContract
 */
public interface LoginContract {
    String CHECK_CODE = "check_code";//验证码
    String LOGIN_MOBILE = "login_mobile";//手机登录
    String LOGIN_WEIXIN = "login_weixin";//微信登录
    String UN_BOUND_MOBILE = "un_bound_mobile";//未绑定手机号
    String GET_ROOMS = "get_rooms";//获取所有的房间号

    interface ILoginView extends BaseViewInterface {
        void updateSendCheckCodeViewStatus(int second);

        void checkFormatError(String error);
    }

    interface ILoginPresent {
        void sendCheckCode(String mobile);

        void loginByTelNo(String mobile, String code);

        void checkCodeReceived();

        void loginByWeixin(String unionid, String tag);



    }

    interface ILoginModel {
        void getCheckCode(String mobile, RequestStatus requestStatus);

        void loginByTelNo(String mobile, String code, RequestStatus requestStatus);

        void loginByWeixin(String unionid, RequestStatus requestStatus, String tag);

        /**
         * 获取用户房间列表
         *
         * @param userId
         */
        void getUserRoomList(RequestStatus requestStatus, int userId, String tag);
    }


}
