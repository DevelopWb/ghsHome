package com.ghs.ghshome.models.discover;

import com.ghs.ghshome.base.BaseViewInterface;

public interface DiscoverContract {
    String BANNER_LIST = "banner_list";//banner
    String AC_LIST_COMMUNITY = "ac_list_community";//活动列表
    String AC_PARTICULARS = "ac_particulars";//活动详情
    String AC_ENTRY = "ac_entry";//报名
    String AC_CHICK = "ac_chick";//点击记录
    String AC_LIST_OFFICAIAL = "ac_list_officaial";//官方资讯
    String AC_LIST_OFFICAIAL_OL = "ac_list_official_pl";//官方资讯详情
    String MY_EVENT_LIST = "my_event_list";//活动列表

    public interface IDiscoverView extends BaseViewInterface {

    }

    interface IDiscoverPresnerter {

        void getBannerList(int villageId ,String tag);

        void getClickBanner(int bannerid, String tag);

        void getCommunityList(int userId, int villageId, String tag);

        void getCommUnityParticulars(int id, int userId, String tag);

        void getCommUnityApply(int id, int userId, int roomid,String leaveMessage, String tag);

        void getOfficialList(String tag);

        void getOfficialParticulars(int id, String tag);

        void getMyEventList(int userId,int villageId,String tag);


    }


}
