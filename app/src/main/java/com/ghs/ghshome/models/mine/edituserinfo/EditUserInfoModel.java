package com.ghs.ghshome.models.mine.edituserinfo;

import com.ghs.ghshome.base.network.RequestStatus;
import com.ghs.ghshome.base.network.networkProxy.HttpProxy;
import com.ghs.ghshome.base.network.okgo.NetResponseCallBack;
import com.ghs.ghshome.bean.LoginBean;
import com.ghs.ghshome.tools.Contract;
import com.ghs.ghshome.tools.GsonManager;

import java.util.Map;

/**
 * Author:wang_sir
 * Time:2018/8/29 15:44
 * Description:This is EditUserInfoModel
 */
public class EditUserInfoModel implements EditUserInfoContract.IEditUserInfoModel {
    @Override
    public void updateUserInfo(int userId, Map<String, String> map, final RequestStatus requestStatus, final String tag) {
        HttpProxy.getInstance()
                .params("userId", userId )
                .params(map)
                .postToNetwork(Contract.MINE_UPDATE_USER_INFO,new NetResponseCallBack() {
                    @Override
                    public void onSuccess(String content) {
                        if (requestStatus != null) {
                            requestStatus.onSuccess(GsonManager.getInstance().parseJsonToBean(content, LoginBean.class),tag);
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
