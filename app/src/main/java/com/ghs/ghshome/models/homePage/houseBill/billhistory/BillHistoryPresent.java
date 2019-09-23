package com.ghs.ghshome.models.homePage.houseBill.billhistory;

import com.ghs.ghshome.base.BasePresent;
import com.ghs.ghshome.base.network.RequestStatus;
import com.ghs.ghshome.base.network.networkProxy.HttpProxy;
import com.ghs.ghshome.base.network.okgo.NetResponseCallBack;
import com.ghs.ghshome.bean.CouponsBean;
import com.ghs.ghshome.bean.MyBillInfolBean;
import com.ghs.ghshome.tools.Contract;
import com.ghs.ghshome.tools.GsonManager;
import com.ghs.ghshome.tools.PubUtil;
import com.ghs.ghshome.wxapi.WeiXinPayModel;

/**
 * Author:wang_sir
 * Time:2018/7/18 15:41
 * Description:This is BillHistoryPresent
 */
public class BillHistoryPresent extends BasePresent<BillHistoryContract.IBaseBillInfoView> implements BillHistoryContract.IBaseBillInfoPresent, RequestStatus {


    @Override
    public void getBillInfo(int roomId, int payState, String feeType, int payUser, String startPayMonth, String endPayMonth, String startMonth, String endMonth,String startCreateYear,String endCreateYear) {

        if (getView() != null) {
            getView().startLoading(RequestStatus.UPDATE);
        }
        HttpProxy.getInstance()
                .params("roomId", roomId)
                .params("payState", payState)
                .params("type", feeType)
                .params("payUser", payUser)
                .params("startPayMonth", startPayMonth)
                .params("endPayMonth", endPayMonth)
                .params("startCreateMonth", startMonth)
                .params("endCreateMonth", endMonth)
                .params("startCreateYear", startCreateYear)
                .params("endCreateYear", endCreateYear)
                .postToNetwork(Contract.LIFE_BILL_LIST,new NetResponseCallBack() {
                    @Override
                    public void onSuccess(String content) {
                        if (getView() != null) {
                            getView().stopLoading(RequestStatus.UPDATE);
                            getView().updateView(GsonManager.getInstance().parseJsonToBean(content, MyBillInfolBean.class), RequestStatus.UPDATE);

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


    @Override
    public void getCouponListNoPage(int userId, double payMoney, int couponType, int state) {
        HttpProxy.getInstance()
                .params("userId", userId)
                .params("payMoney", payMoney)
                .params("couponType", couponType)
                .params("state", state)
                .postToNetwork(Contract.MINE_COUPONS_NO_PAGE,new NetResponseCallBack() {
                    @Override
                    public void onSuccess(String content) {
                        if (getView() != null) {
                            getView().updateView(GsonManager.getInstance().parseJsonToBean(content, CouponsBean.class), RequestStatus.SELECT_COUPON);

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

    @Override
    public void getWeiXinPayInfo(int orderId, String ip, int userId, String feeType, int couponUserId) {
        new WeiXinPayModel().getWeiXinPayInfo(orderId,ip,userId,feeType,couponUserId,this);
    }

    @Override
    public void getBillDetail(int orderId, String tag) {
        HttpProxy.getInstance()
                .params("orderId", orderId)
                .postToNetwork(Contract.LIFE_BILL_DETAIL,new NetResponseCallBack() {
                    @Override
                    public void onSuccess(String content) {
                        if (getView() != null) {
                            getView().updateView(GsonManager.getInstance().parseJsonToBean(PubUtil.getServerData(content), MyBillInfolBean.DataBean.class), RequestStatus.SELECT_COUPON);

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

    @Override
    public void onStart(String tag) {

    }

    @Override
    public void onSuccess(Object o, String tag) {
        if (getView() != null) {
            getView().updateView(o,tag);
        }
    }

    @Override
    public void onError(String tag) {
        if (getView() != null) {
            getView().onError(tag);
        }
    }
}
