package com.ghs.ghshome.models.mine.appSuggestion;

import com.ghs.ghshome.base.BasePresent;
import com.ghs.ghshome.base.network.RequestStatus;
import com.ghs.ghshome.base.network.networkProxy.HttpProxy;
import com.ghs.ghshome.base.network.okgo.NetResponseCallBack;
import com.ghs.ghshome.tools.Contract;
import com.ghs.ghshome.tools.PubUtil;

/**
 * Author:wang_sir
 * Time:2018/7/19 15:30
 * Description:This is AppSuggestionPresent
 */
public class AppSuggestionPresent extends BasePresent<AppSuggestionContract.IMySuggestionView> implements AppSuggestionContract.IMySuggestionPresent , RequestStatus {


    @Override
    public void uploadSuggestion(int userId, int roomId, int villageId, String contnet, String imageUrl1, String imageUrl2, String imageUrl3, final String tag) {
        HttpProxy.getInstance()
                .params("userId", userId)
                .params("roomId", roomId)
                .params("villageId", villageId)
                .params("content", contnet)
                .params("imageUrl1", imageUrl1)
                .params("imageUrl2", imageUrl2)
                .params("imageUrl3", imageUrl3)
                .postToNetwork(Contract.MINE_APP_SUGGUEST,new NetResponseCallBack() {
                    @Override
                    public void onSuccess(String content) {
                        if (getView() != null) {
                            getView().updateView(PubUtil.getServerCode(content), tag);
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
