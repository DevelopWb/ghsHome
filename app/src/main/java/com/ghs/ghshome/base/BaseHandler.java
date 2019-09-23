package com.ghs.ghshome.base;

import android.os.Handler;
import android.os.Message;

import java.lang.ref.WeakReference;

/**
 * Author:wang_sir
 * Time:2018/12/6 16:32
 * Description:This is BaseHandler
 */
public class BaseHandler<T extends BaseHandler.BaseHandlerCallBack> extends Handler {

    WeakReference<T> wr;

    public BaseHandler(T t) {
        wr = new WeakReference<T>(t);
    }

    public BaseHandler() {

    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        T t = wr.get();
        if (t != null) {
            t.callBack(msg);
        }
    }

    public interface BaseHandlerCallBack {
        public void callBack(Message msg);
    }
}