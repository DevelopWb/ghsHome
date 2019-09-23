package com.ghs.ghshome.models.mine;

import com.ghs.ghshome.base.BasePresent;
import com.ghs.ghshome.base.network.networkProxy.HttpProxy;
import com.ghs.ghshome.base.network.okgo.NetResponseCallBack;
import com.ghs.ghshome.bean.WorkOrderBean;
import com.ghs.ghshome.tools.Contract;
import com.ghs.ghshome.tools.GsonManager;

/**
 * Author:wang_sir
 * Time:2019/5/31 11:11
 * Description:This is MinePresent
 */
public class MinePresent extends BasePresent<MineContract.IMineView> implements MineContract.IMinePresent {
    @Override
    public void getCustomerWorkOrders(int offset, int limit, int villageId, int ghsUserId, int state, String tag) {
        HttpProxy.getInstance()
                .params("offset", offset)
                .params("limit", limit)
                .params("villageId", villageId)
                .params("ghsUserId", ghsUserId)
                .params("state", state)
                .postToNetwork(Contract.CUSTOMER_WORK_ORDER_LIST,new NetResponseCallBack() {
                    @Override
                    public void onSuccess(String content) {
                        if (getView() != null) {
                            getView().updateView(GsonManager.getInstance().parseJsonToBean(content, WorkOrderBean.class),tag);
                        }
                    }

                    @Override
                    public void onError(String str) {
                        if (getView() != null) {
                            getView().onError(str);
                        }
                    }
                });
    }
}
