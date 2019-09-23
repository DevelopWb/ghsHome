package com.ghs.ghshome.models.homePage.keymanager.addkey;

import com.ghs.ghshome.base.BaseViewInterface;

/**
 * Author:wang_sir
 * Time:2018/7/3 7:52
 * Description:This is AddKeyContract
 */
public interface AddKeyContract {

    interface AddKey_if_View extends BaseViewInterface{
        void checkFormatSuccess();
        void checkFormatError(String error);
    }

    interface IAddKeyPresent{
        void checkFormat(int userId,int roomId,int roleType,int leftKeyNum,String mobile,String fullName);
        void addkey(int userId,int roomId,int roleType,int leftKeyNum,String mobile,String fullName);
    }


}
