package com.ghs.ghshome.wxapi;


import com.ghs.ghshome.base.network.RequestStatus;

/**
 * Author:wang_sir
 * Time:2018/11/21 17:55
 * Description:This is IWeiXinPayInfo
 */
public interface IWeiXinPayInfo {


    void getWeiXinPayInfo(int orderId, String ip, int userId, String feeType, int couponUserId, RequestStatus requestStatus);

}
