package com.ghs.ghshome.base;

/**
 * Author:wang_sir
 * Time:2018/7/5 10:19
 * Description:This is BaseViewInterface
 */
public interface BaseViewInterface<T> {
    String REFRESH = "refresh";
    String UPDATE = "update";
    void startLoading(String tag);

    void stopLoading(String tag);

    void updateView(T t,String tag);

    void onError(String tag);
}
