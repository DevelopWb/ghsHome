package com.ghs.ghshome.models.homePage;

import com.ghs.ghshome.base.BaseViewInterface;
import com.ghs.ghshome.base.network.RequestStatus;

/**
 * Author:wang_sir
 * Time:2019/5/21 13:58
 * Description:This is HomePageContract
 */
public interface HomePageContract {

    String GET_USUALLY_USED_SERVICES = "/appLogin/getUserUseAppMenu";
    String SAVE_USUALLY_USED_SERVICES = "/appLogin/saveUserUseAppMenu";
    String GET_ALL_SERVICES = "/appLogin/getPropertyHallAppMenu";
    String WORK_ORDER_LIST = "/serviceWork/oneTouchCallList";
    String WORK_ORDER_LIST_REFRESH = "/serviceWork/oneTouchCallList/refresh";
    String WORK_ORDER_DES = "/serviceWork/detail";
    String CUSTOMER_SERVER_TEL = "/serviceWork/serviceMobile";
    String HOME_HOUSE_KEEPER_DATA = "/housekeeper/housekeeperIndex";
    String HOUSE_KEEPER_ALL_DATA = "/housekeeper/housekeeperList";
    //未读官方通告数
    String UNREAD_OFFICIAL_NOTICE = "/officialNotice/getUnreadNum";
    //官方通告列表
    String OFFICIAL_NOTICE_LIST = "/officialNotice/noticeList";
    //读取官方通告
    String READ_OFFICIAL_NOTICE = "/officialNotice/readNotice";

    /**
     * 消息类型 notice:小区公告、repair:报修、suggestion:投诉建议、bill:房屋账单、visitor:邀请访客
     * 、goodsInto:物品放行、decoration:装修申请、serviceWork:一键呼叫、officicalActivity:官方活动、
     * villageActivity:社区活动
     * <p>
     * 报修 一键呼叫 投诉建议 这几个返回的自断都是一样的 共用一个详情页
     */
    String NOTICI_TYPE = "";//消息的类型
    String NOTICI_ID = "notice_id";//消息的id
    String NOTICI_VILLAGE = "notice";//小区消息
    String NOTICI_REPAIR = "repair";//报修
    String NOTICI_SUGGESTION = "suggestion";//投诉建议
    String NOTICI_BILL = "bill";//账单消息
    String NOTICI_VISITOR = "visitor";//访客消息
    String NOTICI_GOODS_INTO = "goodsInto";//放行消息
    String NOTICI_DECORATION = "decoration";//装修备案
    String NOTICI_FIRE = "fire";//动火备案
    String NOTICI_SERVICE_WORK = "serviceWork";//一键呼叫
    String OFFICICAL_ACTIVITY = "officicalActivity";//官方活动
    String OFFICICAL_NOTICE = "officicalNotice";//官方消息
    String VISITOR_CHECK = "visitorCheck";//访客审核
    String USER_AUTH = "userAuth";//访客审核
    String NOTICI_VILLAGRE_ACTIVITY = "villageActivity";//社区活动
    String LIFE_BILL_DETAIL = "/bill/orderDetail";

    /**
     * 种子
     */
    //种子任务列表
    String SEED_TASK_LIST = "/seedTask/list";
    //种子记录列表
    String SEED_RECORD_LIST = "/seedTask/seedDetailList";
    //种子数量
    String SEED_AMOUNT = "/seedTask/sign";

    interface IHomePageView extends BaseViewInterface {
    }

    interface IHomePagePresent {
        /**
         * 获取常用的服务
         */
        void getUsuallyUsedService(int villageId, int userId, String tag);


        void saveUsuallyUsedService(int userId, String menuIds, String tag);

        void getAllServices(int villageId, String tag);

        void workOrderList(int offset, int limit, int villageId, int ghsUserId, String tag);

        void workOrderdes(int serviceWorkId, String tag);

        void customerServerTel(String tag);

        void getHomePageHouseKeeperData(int villageId, int userId, int roomId, String tag);

        void getAllHouseKeeperData(int offset, int limit, int villageId, int userId, int roomId, String tag);

        /**
         * 获取未读官方消息数量
         *
         * @param userId
         * @param tag
         */
        void getUnReadOfficialNoticeNum(int userId, String tag);

        /**
         * 获取官方消息列表
         *
         * @param offset
         * @param limit
         * @param userId
         * @param tag
         */
        void getOfficialNoticeList(int offset, int limit, int userId, String tag);

        /**
         * 获取账单详情
         *
         * @param orderId
         * @param tag
         */
        void getBillDetail(int orderId, String tag);

        /**
         * 获取种子任务的列表
         *
         * @param userId
         * @param tag
         */
        void getSeedTaskList(int userId, String tag);
        /**
         * 获取种子记录
         *
         * @param userId
         * @param tag
         */
        void getSeedRecordList(int userId,int type, String tag);
        /**
         * 获取种子数量
         *
         * @param userId
         * @param tag
         */
        void getSeedAmount(int userId, String tag);



    }

    interface IHomeModel {
        void getUserAndRoomInfo(int userId, int userRoomId, RequestStatus requestStatus, String tag);
        void getSeedAmount(int userId, RequestStatus requestStatus, String tag);
    }

}
