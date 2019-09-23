package com.ghs.ghshome.models.mine.edituserinfo;

import com.ghs.ghshome.base.BaseViewInterface;
import com.ghs.ghshome.base.network.RequestStatus;

import java.util.Map;

/**
 * Author:wang_sir
 * Time:2018/7/20 13:49
 * Description:This is EditUserInfoContract
 */
public interface EditUserInfoContract {
    String MODIFY_UNIONID = "modify_unionid";
    String MODIFY_NICK_NAME = "modify_nick_name";
    String MODIFY_HEAD_IMAGE = "modify_head_image";

    interface IEditUserInfoView extends BaseViewInterface {
    }

    interface IEditUserInfoPresent {
        void updateUserInfo(int userId, Map<String, String> map,String tag);
    }
    interface IEditUserInfoModel {
        void updateUserInfo(int userId, Map<String, String> map, RequestStatus requestStatus, String tag);
    }
}
