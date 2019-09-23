package com.ghs.ghshome.wxapi.weixinauth;

import java.util.Map;

/**
 * Author:wang_sir
 * Time:2018/7/20 14:21
 * Description:This is IWeiXinAuthCallBack
 */
public interface IWeiXinAuthCallBack {

    void AuthFinished( Map<String, String> data);
    void AuthError(  Throwable throwable);
}
