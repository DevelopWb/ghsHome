package com.ghs.ghshome.models.mine.edituserinfo;

import com.ghs.ghshome.base.BasePresent;
import com.ghs.ghshome.base.network.RequestStatus;

import java.util.Map;

/**
 * Author:wang_sir
 * Time:2018/7/20 13:47
 * Description:This is EditUserInfoPresent
 */
public class EditUserInfoPresent extends BasePresent<EditUserInfoContract.IEditUserInfoView> implements EditUserInfoContract.IEditUserInfoPresent, RequestStatus {

    private final EditUserInfoModel editUserInfoModel;

    public EditUserInfoPresent() {
        editUserInfoModel = new EditUserInfoModel();
    }

    @Override
    public void updateUserInfo(int userId, Map<String, String> map,final String tag) {
        editUserInfoModel.updateUserInfo(userId,map,this,tag);
    }

    @Override
    public void onStart(String tag) {

    }

    @Override
    public void onSuccess(Object o, String tag) {
        if (getView() != null) {
            getView().updateView(o,tag);
        }
    }

    @Override
    public void onError(String tag) {
        if (getView() != null) {
            getView().onError(tag);
        }
    }
}
