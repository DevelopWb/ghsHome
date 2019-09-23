package com.ghs.ghshome.models.main;

import com.ghs.ghshome.base.BasePresent;
import com.ghs.ghshome.base.network.RequestStatus;
import com.ghs.ghshome.base.network.networkProxy.HttpProxy;
import com.ghs.ghshome.base.network.okgo.NetResponseCallBack;
import com.ghs.ghshome.bean.RoomListBean;
import com.ghs.ghshome.tools.Contract;
import com.ghs.ghshome.tools.GsonManager;

/**
 * Author:wang_sir
 * Time:2018/7/10 19:40
 * Description:This is MainPresent
 */
public  class MainPresent extends BasePresent<MainContact.IMainView> implements RequestStatus, MainContact.IMainPresent {

    private final MainModel mainModel;

    public MainPresent() {
        mainModel = new MainModel();
    }


    @Override
    public void getBlueMacList(int villageId, int cellId, String tag) {
        mainModel.getBlueMacList(villageId,cellId,this,tag);
    }

    @Override
    public void getUserRoomList(int userId) {
        if (getView() != null) {
            getView().startLoading(MainContact.GET_ROOMS);
        }
        HttpProxy.getInstance()
                .params("userId", userId)
                .getToNetwork(Contract.LOGIN_USER_ROOMS,new NetResponseCallBack() {
                    @Override
                    public void onSuccess(String content) {
                        if (getView() != null) {
                            RoomListBean roomListBean = GsonManager.getInstance().parseJsonToBean(content, RoomListBean.class);
                            if (getView() != null) {
                                getView().stopLoading(MainContact.GET_ROOMS);
                                getView().updateView(roomListBean, MainContact.GET_ROOMS);
                            }
                        }
                    }

                    @Override
                    public void onError(String str) {
                        if (getView() != null) {
                            getView().stopLoading(MainContact.GET_ROOMS);
                            getView().onError(str);
                        }
                    }
                });
    }


    @Override
    public void openDoorByNet(int deviceId, int userRoomId) {
        mainModel.openDoorByNet(deviceId, userRoomId, this);
    }

    @Override
    public void quitHouse(int userRoomId, String tag) {
        HttpProxy.getInstance()
                .params("userRoomId", userRoomId)
                .postToNetwork(Contract.QUIT_ROOM,new NetResponseCallBack() {
                    @Override
                    public void onSuccess(String content) {
                        if (getView() != null) {
                            getView().updateView(userRoomId,tag);
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
            getView().updateView(o, tag);

        }
    }

    @Override
    public void onError(String tag) {
        if (getView() != null) {
            getView().onError(tag);

        }
    }
}
