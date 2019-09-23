package com.ghs.ghshome.models.propertyHall.openDoorByMobile.remoteopen;

import com.ghs.ghshome.base.BaseViewInterface;
import com.ghs.ghshome.base.network.RequestStatus;

/**
 * Author:wang_sir
 * Time:2018/8/13 13:46
 * Description:This is RemoteOpenContract
 */
public interface RemoteOpenContract {
    String GET_PWD = "get_pwd";//获取密码标识
    String GET_DEV_LIST = "get_dev_list";//获取设备列表标识

    interface IRemoteOpenView extends BaseViewInterface {
    }

    interface IRemoteOpenPresent {
        void getLockDeviceList(int villageId,int cellId,String tag);//获取门禁列表
        void getPassword(int userRoomId,String tag);//获取密码
    }

    interface IRemoteOpenModel {
        void getLockDeviceList(int villageId, int cellId, RequestStatus requestStatus, String tag);//获取门禁列表
        void getPassword(int userRoomId,RequestStatus requestStatus,String tag);//获取密码
    }
}
