package com.ghs.ghshome.models.homePage.keymanager.allotkey;

import com.ghs.ghshome.base.BaseViewInterface;

/**
 * Author:wang_sir
 * Time:2018/6/29 18:48
 * Description:This is AllotKeyContract
 */
public interface AllotKeyContract {
    String RELEASE = "release";
    String MODIFY = "modify";

    interface AllotKeyView<T> extends BaseViewInterface {

    }

    interface AllotKeyPresent<T> {
        void getUserInfos(int userId,int roleType,int roomId,String tag);
        void modifyKeyAmount(int userRoomId,int updatedUserRoomId,int leftKeyNum);
        void releaseKeyAmount(int userRoomId,int deletedUserRoomId,int deletedRoleType,int roomId);
    }


}
