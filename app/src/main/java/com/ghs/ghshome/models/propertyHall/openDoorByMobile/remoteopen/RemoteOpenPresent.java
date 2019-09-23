package com.ghs.ghshome.models.propertyHall.openDoorByMobile.remoteopen;

import com.ghs.ghshome.base.BasePresent;
import com.ghs.ghshome.base.network.RequestStatus;

/**
 * Author:wang_sir
 * Time:2018/8/13 13:46
 * Description:This is RemoteOpenPresent
 */
public class RemoteOpenPresent extends BasePresent<RemoteOpenContract.IRemoteOpenView> implements RemoteOpenContract.IRemoteOpenPresent, RequestStatus {

    private RemoteOpenModel remoteOpenModel;

    public RemoteOpenPresent() {
        if (remoteOpenModel == null) {
            remoteOpenModel = new RemoteOpenModel();
        }

    }

    @Override
    public void getLockDeviceList(int villageId, int cellId, String tag) {
        remoteOpenModel.getLockDeviceList(villageId, cellId, this, tag);
    }

    @Override
    public void getPassword(int userRoomId, String tag) {
        remoteOpenModel.getPassword(userRoomId, this, tag);
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
