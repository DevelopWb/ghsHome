package com.ghs.ghshome.models.homePage.visitors;

import com.ghs.ghshome.base.BaseViewInterface;

/**
 * Author:wang_sir
 * Time:2018/11/23 11:05
 * Description:This is VisitorContract
 */
public interface VisitorContract {

    String SEARCH_VISITOR = "searchVisitors";
    String SEARCH_VISITOR_REQUEST = "searchVisitorsRequest";
    String ADD_VISITOR = "addVisitors";
    String ACCESS_VISITOR = "accessVisitors";
    String REJECT_VISITOR = "rejectVisitors";
    String PARTICULARS_VISTOR ="particularsVistor";
    String GET_VISITOR_MSG = "/visitor/detail";

    interface IVisitorView extends BaseViewInterface {
    }

    interface IVisitorPresent {
        void searchVisitors(int villageId, int roomId, int ghsUserId, int state, int offset, int limit, String tag);
        void addVisitors(int villageId, int roomId, int ghsUserId,String visitorName,String visitorMobile
                ,String visitDay,String carNum,String tag);

        void accessVisitors(int visitorId, int ghsUserId ,String tag);
        void rejectVisitors(int visitorId, int ghsUserId ,String tag);
        void getVisitorMsg(int visitorId,String tag);

    }



}
