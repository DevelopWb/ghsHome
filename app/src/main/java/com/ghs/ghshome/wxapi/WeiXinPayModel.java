package com.ghs.ghshome.wxapi;

import com.ghs.ghshome.base.network.RequestStatus;
import com.ghs.ghshome.base.network.networkProxy.HttpProxy;
import com.ghs.ghshome.base.network.okgo.NetResponseCallBack;
import com.ghs.ghshome.bean.WXPayResultBean;
import com.ghs.ghshome.tools.Contract;
import com.ghs.ghshome.tools.GsonManager;

/**
 * Author:wang_sir
 * Time:2018/11/21 17:53
 * Description:This is WeiXinPayModel
 */
public class WeiXinPayModel implements IWeiXinPayInfo {



    @Override
    public void getWeiXinPayInfo(int orderId, String ip, int userId, String feeType, int couponUserId, RequestStatus requestStatus) {
        HttpProxy.getInstance()
                .params("orderId", orderId)
                .params("ip", ip)
                .params("userId", userId)
                .params("feeType", feeType)
                .params("couponUserId", couponUserId)
                .postToNetwork(Contract.WEI_XIN_PAY,new NetResponseCallBack() {
                    @Override
                    public void onSuccess(String content) {
                        if (requestStatus != null) {
                            requestStatus.onSuccess(GsonManager.getInstance().parseJsonToBean(content, WXPayResultBean.class), RequestStatus.WEI_XIN_PAY);
                        }
                    }

                    @Override
                    public void onError(String str) {
                        if (requestStatus != null) {
                            requestStatus.onError(str);
                        }
                    }
                });
       
    }
}
