package com.ghs.ghshome.models.homePage;

import com.ghs.ghshome.base.network.RequestStatus;
import com.ghs.ghshome.base.network.networkProxy.HttpProxy;
import com.ghs.ghshome.base.network.okgo.NetResponseCallBack;
import com.ghs.ghshome.bean.UserAndRoomBean;
import com.ghs.ghshome.tools.Contract;
import com.ghs.ghshome.tools.GsonManager;
import com.ghs.ghshome.tools.PubUtil;

/**
 * Author:wang_sir
 * Time:2018/8/7 14:17
 * Description:This is HomePageModel
 */
public class HomePageModel implements HomePageContract.IHomeModel {


    @Override
    public void getUserAndRoomInfo(int userId, int userRoomId, final RequestStatus requestStatus, final String tag) {
        HttpProxy.getInstance()
                .params("userId", userId)
                .params("userRoomId", userRoomId)
                .getToNetwork(Contract.USER_ROOM_INFO,new NetResponseCallBack() {
                    @Override
                    public void onSuccess(String content) {
                        if (requestStatus != null) {
                            requestStatus.onSuccess(GsonManager.getInstance().parseJsonToBean(content, UserAndRoomBean.class),tag);

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
    public void getSeedAmount(int userId, RequestStatus requestStatus, String tag) {
        HttpProxy.getInstance()
                .params("userId", userId)
                .postToNetwork(Contract.GET_SEED_AMOUNT,new NetResponseCallBack() {
                    @Override
                    public void onSuccess(String content) {
                        if (requestStatus != null) {
                            requestStatus.onSuccess(PubUtil.getServerData(content), tag);

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
