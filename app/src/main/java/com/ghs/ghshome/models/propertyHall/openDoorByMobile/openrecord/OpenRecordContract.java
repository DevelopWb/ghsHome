package com.ghs.ghshome.models.propertyHall.openDoorByMobile.openrecord;


import com.ghs.ghshome.base.BaseViewInterface;

/**
 * Author:wang_sir
 * Time:2018/7/5 10:16
 * Description:This is OpenRecordContract
 */
public interface OpenRecordContract {
    String ALLOW_SHOW = "allow_show";
    String UN_ALLOW_SHOW = "un_allow_show";

    interface OpenRecordView<T> extends BaseViewInterface {

    }

    interface IOpenRecordPresent {
        void getOpenRecordList(int userId, int roomId, int roleType, String startDate, String endDate, String tag);

        void allowedToSee(int userRoomId, int showLockLog, String tag);//允许查看
    }

}
