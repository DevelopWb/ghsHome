package com.ghs.ghshome.models.homePage.houseBill.billhistory;

import com.ghs.ghshome.base.BaseViewInterface;

/**
 * Author:wang_sir
 * Time:2018/7/18 15:32
 * Description:This is BillHistoryContract
 */
public interface BillHistoryContract {
    String LIFE_BILL_DETAIL = "/bill/orderDetail";
    String BILL_HIS_ORDERID = "bill_his_orderId";

    interface IBaseBillInfoView extends BaseViewInterface {

    }

    interface IBaseBillInfoPresent {
        void getBillInfo(int roomId, int payState, String feeType, int payUser, String startPayMonth, String endPayMonth, String startMonth, String endMonth,String startCreateYear,String endCreateYear);


        void getCouponListNoPage(int userId, double payMoney, int couponType, int state);

        void getWeiXinPayInfo(int orderId, String ip, int userId, String feeType, int couponUserId);


        /**
         * 获取账单详情
         *
         * @param orderId
         * @param tag
         */
        void getBillDetail(int orderId, String tag);
    }
}
