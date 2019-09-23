package com.ghs.ghshome.models.homePage.villagenotice;

import com.ghs.ghshome.base.BaseViewInterface;

/**
 * Author:wang_sir
 * Time:2018/7/23 14:01
 * Description:This is VillageNoticeContract
 */
public interface VillageNoticeContract {
    interface  IVillageNoticeView extends BaseViewInterface{

    }

    interface  IVillageNoticePresent {

        void getNoticeList(int offset,int limit,int userId,int villageId,String tag);

        /**
         * 读取小区消息
         * @param noticeId
         */
        void readVillageNotice( int noticeId);
        /**
         * 读消息
         *
         * @param userId
         * @param noticeId
         * @param tag
         */
        void readOfficialNotice(int userId, int noticeId, String tag);
    }
}
