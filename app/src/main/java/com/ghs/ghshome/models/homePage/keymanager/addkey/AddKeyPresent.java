package com.ghs.ghshome.models.homePage.keymanager.addkey;

import android.text.TextUtils;

import com.ghs.ghshome.base.BasePresent;
import com.ghs.ghshome.base.network.RequestStatus;
import com.ghs.ghshome.base.network.networkProxy.HttpProxy;
import com.ghs.ghshome.base.network.okgo.NetResponseCallBack;
import com.ghs.ghshome.tools.Contract;
import com.ghs.ghshome.tools.PubUtil;

/**
 * Author:wang_sir
 * Time:2018/7/3 7:51
 * Description:This is AddKeyPresent
 */
public class AddKeyPresent extends BasePresent<AddKeyContract.AddKey_if_View> implements AddKeyContract.IAddKeyPresent{







    @Override
    public void checkFormat(int userId, int roomId, int roleType, int leftKeyNum, String mobile, String fullName) {

        if (fullName == null || TextUtils.isEmpty(fullName)) {
            if (getView() != null) {
                getView().checkFormatError("用户姓名没有填写");
            }

            return;
        }
        if (mobile == null || TextUtils.isEmpty(mobile)) {
            if (getView() != null) {
                getView().checkFormatError("手机号没有填写");

            }
            return;
        }
        if (!PubUtil.isMobileNO(mobile)) {
            if (getView() != null) {
                getView().checkFormatError("手机号格式错误");

            }
            return;
        }
        addkey(userId,roomId,roleType,leftKeyNum,mobile,fullName);

    }

    @Override
    public void addkey(int userId, int roomId, int roleType, int leftKeyNum, String mobile, String fullName) {
        HttpProxy.getInstance()
                .params("userId",userId)
                .params("roleType",roleType)
                .params("roomId",roomId)
                .params("leftKeyNum",leftKeyNum)
                .params("mobile",mobile)
                .params("fullName",fullName)
                .postToNetwork(Contract.ALLOT_KET_ADD,new NetResponseCallBack() {
                    @Override
                    public void onSuccess(String content) {
                        if (getView() != null) {
                            getView().updateView(PubUtil.getServerMessage(content), RequestStatus.UPDATE);

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
}
