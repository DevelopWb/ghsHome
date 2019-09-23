package com.ghs.ghshome.models.checkIdentity;

import com.ghs.ghshome.base.BaseViewInterface;

/**
 * Author:wang_sir
 * Time:2019/9/20 14:14
 * Description:This is CheckIdentityContract
 */
public interface CheckIdentityContract {
    //获取小区列表
    String GET_VILLAGE_LIST = "/userRoom/getVillageWithCity";
    //获取城市列表
    String GET_CITY_LIST = "/userRoom/getPropertyHallAppMenu";
    //获取单元列表
    String GET_CELL_LIST = "/userRoom/getCell";
    //获取房间列表
    String GET_ROOM_LIST = "/userRoom/getRoom";
    //提交认证
    String SUBMIT_CHECK = "/userRoom/addUserRoom";
    //提交认证
    String RESUBMIT_CHECK = "/userRoom/resubmit";

    String GET_USER_NAME = "/key/getUserNameByMobile";
    //搜索小区
    String SEARCH_VILLAGE = "/userRoom/getVillageWithKey";

    interface ICheckIdentityView extends BaseViewInterface {
    }

    interface ICheckIdentityPresent {
        void getVillageList(String cityName, String tag);

        void getUserName(String mobile, String tag);
        void searchVillage(String searchKey, String tag);

        void getCellList(int villageId, String tag);

        void getRoomList(int cellId, String tag);

        void getCityList(String tag);

        void submitCheck(int userId, int roomId, int roleType, String mobile, String fullName, String tag);

        void reSubmitCheck(int userRoomId, int roomId, int roleType, String tag);

    }

}
