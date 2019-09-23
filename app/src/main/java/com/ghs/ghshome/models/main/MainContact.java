package com.ghs.ghshome.models.main;

import com.ghs.ghshome.base.BaseViewInterface;
import com.ghs.ghshome.base.network.RequestStatus;

/**
 * Author:wang_sir
 * Time:2018/7/10 18:54
 * Description:This is MainContact
 */
public interface MainContact {

    String BLUE_MAC_ADDR = "blue_mac_addr";//蓝牙地址列表
    String OPEN_BY_NET = "open_by_net";//网络开门
    String GET_ROOMS = "get_rooms";//获取所有的房间
    //退出房间
    String QUIT_ROOM = "/userRoom/deleteUserRoom";

    interface IMainView extends BaseViewInterface {

    }

    /**
     * main和fragments之间的回调
     */
    interface OnBetweenMainAndFragmentsCallBack {
        void hindDrawerLayout();

        void unHindDrawerLayout();

        void openDrawerLayout();
    }

    interface IMainPresent {

        /**
         * 获取蓝牙列表
         *
         * @param villageId
         * @param cellId
         */
        void getBlueMacList(int villageId, int cellId, String tag);

        /**
         * 获取用户房间列表
         *
         * @param userId
         */
        void getUserRoomList(int userId);

        /**
         * 网络开门
         *
         * @param deviceId
         * @param userRoomId
         */
        void openDoorByNet(int deviceId, int userRoomId);

        /**
         * 退出房间
         * @param userRoomId
         * @param tag
         */
        void quitHouse(int userRoomId, String tag);

    }

    interface IMainModel {
        /**
         * 获取蓝牙列表
         *
         * @param villageId
         * @param cellId
         */
        void getBlueMacList(int villageId, int cellId, RequestStatus requestStatus, String tag);


        /**
         * 网络开门
         *
         * @param deviceId
         * @param userRoomId
         */
        void openDoorByNet(int deviceId, int userRoomId, RequestStatus requestStatus);

        /**
         * 获取客服电话
         *
         * @param tag
         */
        void customerServerTel(RequestStatus requestStatus, String tag);
    }
}
