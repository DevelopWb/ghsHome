package com.ghs.ghshome.wxapi.weixinauth;

import android.util.Log;

import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

/**
 * Author:wang_sir
 * Time:2018/7/20 14:19
 * Description:This is IWeiXinAuthListener
 */
public class IWeiXinAuthListener implements UMAuthListener {
private String TAG  = "IWeiXinAuthListener";
    private IWeiXinAuthCallBack iAuthCallBack;

    public IWeiXinAuthListener(IWeiXinAuthCallBack iAuthCallBack) {
        this.iAuthCallBack = iAuthCallBack;
    }

    @Override
    public void onStart(SHARE_MEDIA share_media) {
        Log.d(TAG,"开始");
    }

    @Override
    public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
        if (iAuthCallBack != null) {
            iAuthCallBack.AuthFinished(map);
        }
    }

    @Override
    public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
        if (iAuthCallBack != null) {
            iAuthCallBack.AuthError(throwable);
        }
    }

    @Override
    public void onCancel(SHARE_MEDIA share_media, int i) {
        Log.d(TAG,"取消");

    }
}
