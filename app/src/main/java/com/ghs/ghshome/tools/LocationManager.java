package com.ghs.ghshome.tools;

import android.content.Context;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;

/**
 * Author:wang_sir
 * Time:2019/9/9 15:23
 * Description:This is LocationManager
 */
public class LocationManager {
    OnLocateCallBack onLocateCallBack;
    private AMapLocationClient locationClient;
    private AMapLocationClientOption mapLocationClientOption;
    private Context mContext;

    public static LocationManager getInstance() {
        return LocationManagerHolder.Instance;
    }

    public LocationManager setContext(Context context) {
        this.mContext = context.getApplicationContext();
        return this;
    }

    private static class LocationManagerHolder {
        private static LocationManager Instance = new LocationManager();
    }

    public void setOnLocateCallBack(OnLocateCallBack onLocateCallBack) {
        this.onLocateCallBack = onLocateCallBack;
    }

    /**
     * 定位监听
     */
    AMapLocationListener locationListener = new AMapLocationListener() {

        @Override
        public void onLocationChanged(AMapLocation loc) {
            if (onLocateCallBack != null) {
                onLocateCallBack.onLocationChanged(loc);
            }
        }
    };

    /**
     * 初始化定位
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    public void initLocation() {
        //初始化client
        locationClient = new AMapLocationClient(mContext);
        // 设置定位监听
        locationClient.setLocationListener(locationListener);
        startLocation();
    }

    /**
     * 默认的定位参数
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    private AMapLocationClientOption getDefaultOption() {
        AMapLocationClientOption mOption = new AMapLocationClientOption();
        mOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);//可选，设置定位模式，可选的模式有高精度、仅设备、仅网络。默认为高精度模式
        mOption.setGpsFirst(false);//可选，设置是否gps优先，只在高精度模式下有效。默认关闭
        mOption.setHttpTimeOut(30000);//可选，设置网络请求超时时间。默认为30秒。在仅设备模式下无效
        mOption.setInterval(2000);//可选，设置定位间隔。默认为2秒
        mOption.setNeedAddress(true);//可选，设置是否返回逆地理地址信息。默认是true
        mOption.setOnceLocation(false);//可选，设置是否单次定位。默认是false
//		mOption.setOnceLocationLatest(false);//可选，设置是否等待wifi刷新，默认为false.如果设置为true,会自动变为单次定位，持续定位时不要使用
//		AMapLocationClientOption.setLocationProtocol(AMapLocationProtocol.HTTP);//可选， 设置网络请求的协议。可选HTTP或者HTTPS。默认为HTTP
//		mOption.setSensorEnable(false);//可选，设置是否使用传感器。默认是false
        return mOption;
    }

    /**
     * 开始定位
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    private void startLocation() {
//		//根据控件的选择，重新设置定位参数
//		resetOption();
        // 设置定位参数
        mapLocationClientOption = getDefaultOption();
        locationClient.setLocationOption(mapLocationClientOption);
        // 启动定位
        locationClient.startLocation();
    }

    /**
     * 停止定位
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    public void stopLocation() {
        if (null != locationClient) {
            /**
             * 如果AMapLocationClient是在当前Activity实例化的，
             * 在Activity的onDestroy中一定要执行AMapLocationClient的onDestroy
             */
            locationClient.onDestroy();
            locationClient = null;
            mapLocationClientOption = null;
        }

    }

    public interface OnLocateCallBack {
        void onLocationChanged(AMapLocation loc);
    }
}
