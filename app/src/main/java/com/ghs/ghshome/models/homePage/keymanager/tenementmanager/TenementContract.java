package com.ghs.ghshome.models.homePage.keymanager.tenementmanager;

import com.ghs.ghshome.base.BaseViewInterface;

/**
 * Author:wang_sir
 * Time:2018/6/29 18:48
 * Description:This is AllotKeyContract
 */
public interface TenementContract {
    String TENEMENT_ROOMLIST = "tenement_roomList";
    String TENEMENT_GETMOBILE = "tenement_getMobile";
    String TENEMENT_DETAILS="tenement_details";

    interface TenementView extends BaseViewInterface {

    }

    interface ITenementPresent {
        void getUserRoomList( int roomId, String tag);
        void addTenement(int userId, int roomId, int roleType,String mobile,String fullName,String tag );
        void removeTenment(int userRoomId, int deletedUserRoomId,String tag);
        void getFullNameByMobile(String  mobile,String tag);
    }


}
