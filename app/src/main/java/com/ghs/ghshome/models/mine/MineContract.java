package com.ghs.ghshome.models.mine;

import com.ghs.ghshome.base.BaseViewInterface;

/**
 * Author:wang_sir
 * Time:2019/5/31 11:11
 * Description:This is MineContract
 */
public interface MineContract {
    String CUSTOMER_WORK_ORDER_LIST = "/serviceWork/serviceWorkList";//客服工单列表

    interface IMineView extends BaseViewInterface {
    }

    interface IMinePresent {
        /**
         * 获取客服工单列表
         * @param offset
         * @param limit
         * @param villageId
         * @param ghsUserId
         * @param state   1:跟进中 2：完成 3:待跟进
         * @param tag
         */
        void getCustomerWorkOrders(int offset, int limit, int villageId, int ghsUserId, int state, String tag);
    }
}
