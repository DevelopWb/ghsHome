package com.ghs.ghshome.models.propertyHall.openDoorByMobile.remoteopen;

import com.ghs.ghshome.base.network.RequestStatus;
import com.ghs.ghshome.base.network.networkProxy.HttpProxy;
import com.ghs.ghshome.base.network.okgo.NetResponseCallBack;
import com.ghs.ghshome.bean.RemoteOpenLockDevBean;
import com.ghs.ghshome.tools.Contract;
import com.ghs.ghshome.tools.GsonManager;
import com.ghs.ghshome.tools.PubUtil;

/**
 * Author:wang_sir
 * Time:2018/8/13 13:46
 * Description:This is RemoteOpenModel
 */
public class RemoteOpenModel  implements RemoteOpenContract.IRemoteOpenModel{
    @Override
    public void getLockDeviceList(int villageId, int cellId, final RequestStatus requestStatus, final String tag) {

        HttpProxy.getInstance()
                .params("villageId",villageId)
                .params("cellId",cellId)
                .getToNetwork(Contract.REMOTE_OPEN_LOCKS,new NetResponseCallBack() {
                    @Override
                    public void onSuccess(String content) {
                        if (requestStatus != null) {
                            RemoteOpenLockDevBean remoteOpenLockDevBean = GsonManager.getInstance().parseJsonToBean(content,RemoteOpenLockDevBean.class);
                            requestStatus.onSuccess(remoteOpenLockDevBean,tag);
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
    public void getPassword(int userRoomId, final RequestStatus requestStatus, final String tag) {

        HttpProxy.getInstance()
                .params("userRoomId",userRoomId)
                .getToNetwork(Contract.REMOTE_OPEN_LOCK_PWD,new NetResponseCallBack() {
                    @Override
                    public void onSuccess(String content) {
                        if (requestStatus != null) {
                            requestStatus.onSuccess(PubUtil.getServerData(content),tag);
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
