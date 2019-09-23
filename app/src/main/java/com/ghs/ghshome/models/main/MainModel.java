package com.ghs.ghshome.models.main;

import com.ghs.ghshome.base.network.RequestStatus;
import com.ghs.ghshome.base.network.networkProxy.HttpProxy;
import com.ghs.ghshome.base.network.okgo.NetResponseCallBack;
import com.ghs.ghshome.bean.BluetoothsBean;
import com.ghs.ghshome.bean.PropertyTelBean;
import com.ghs.ghshome.tools.Contract;
import com.ghs.ghshome.tools.GsonManager;
import com.ghs.ghshome.tools.PubUtil;

/**
 * Author:wang_sir
 * Time:2018/7/10 19:39
 * Description:This is MainModel
 */
public class MainModel implements MainContact.IMainModel {
    @Override
    public void getBlueMacList(int villageId, int cellId, final RequestStatus requestStatus, final String tag) {
        HttpProxy.getInstance()
                .params("villageId",villageId)
                .params("cellId",cellId)
                .getToNetwork(Contract.REMOTE_OPEN_MACS,new NetResponseCallBack() {
                    @Override
                    public void onSuccess(String content) {
                        if (requestStatus != null) {
                            requestStatus.onSuccess(GsonManager.getInstance().parseJsonToBean(content,BluetoothsBean.class),tag);
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
    public void openDoorByNet(int deviceId, int userRoomId, final RequestStatus requestStatus) {
        HttpProxy.getInstance()
                .params("deviceId",deviceId)
                .params("userRoomId",userRoomId)
                .getToNetwork(Contract.REMOTE_OPEN_BY_NET,new NetResponseCallBack() {
                    @Override
                    public void onSuccess(String content) {
                        if (requestStatus != null) {
                            requestStatus.onSuccess(PubUtil.getServerData(content),MainContact.OPEN_BY_NET);
                        }
                    }

                    @Override
                    public void onError(String str) {
                        if (requestStatus != null) {
                            requestStatus.onError(MainContact.OPEN_BY_NET);
                        }
                    }
                });

    }

    @Override
    public void customerServerTel(RequestStatus requestStatus,String tag) {
        HttpProxy.getInstance()
                .postToNetwork(Contract.CUSTOMER_SERVER_TEL,new NetResponseCallBack() {
                    @Override
                    public void onSuccess(String content) {
                        if (requestStatus != null) {
                            requestStatus.onSuccess(GsonManager.getInstance().parseJsonToBean(content, PropertyTelBean.class),tag);
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
