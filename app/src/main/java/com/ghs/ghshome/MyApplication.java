package com.ghs.ghshome;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Build;
import android.support.multidex.MultiDex;

import com.alibaba.sdk.android.push.register.HuaWeiRegister;
import com.alibaba.sdk.android.push.register.MiPushRegister;
import com.ghs.ghshome.base.network.networkProxy.HttpProxy;
import com.ghs.ghshome.base.network.okgo.OkGoInterceptor;
import com.ghs.ghshome.base.network.okgo.OkgoTool;
import com.ghs.ghshome.models.justtalk.JCManager;
import com.ghs.ghshome.models.push.AliPushManager;
import com.ghs.ghshome.tools.ActivityLifecycleListener;
import com.ghs.ghshome.tools.BuglyConfig;
import com.ghs.ghshome.tools.ConfigUtil;
import com.ghs.ghshome.tools.Contract;
import com.ghs.ghshome.tools.CrashHandler;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cache.CacheMode;
import com.orhanobut.hawk.Hawk;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;
import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;

import java.util.concurrent.TimeUnit;

import cn.com.mark.multiimage.core.PreferencesUtils;
import io.javac.ManyBlue.ManyBlue;
import okhttp3.OkHttpClient;

/**
 * Author:wang_sir
 * Time:2018/7/7 18:34
 * Description:This is MyApplication
 */
public class MyApplication extends Application {
    private static final String MIPUSH_APP_ID = "2882303761517848530";
    private static final String MIPUSH_APP_KEY = "5641784871530";
    public   static final boolean IsDeBugMoDe = false;//是否是调试模式,上线的时候改成false就可以了
    public   static final boolean OpenRefWatcher = false;//开启leakCanary
    private RefWatcher refWatcher;
    private static Context mContext;
    @Override

    protected void attachBaseContext(Context base) {

        super.attachBaseContext(base);

        MultiDex.install(base);

    }

    /**
     * 获取App上下文
     * @return
     */
    public static Context getAppContext() {
        return MyApplication.mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
//        resetDensity();//这个方法重写也是很有必要的

        //异常捕获  在bugly之前初始化  可能影响bugly异常上报
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(getApplicationContext());
        if (!IsDeBugMoDe) {
            BuglyConfig.init(this);
            Contract.BASE_URL = Contract.BASE_URL_RELEASE;
        }else{
            Contract.BASE_URL = Contract.BASE_URL_DEBUG;
        }
//低功耗蓝牙
        ManyBlue.blueStartService(this);
        setOkGoConfig();
        Hawk.init(this).build();
        //监听app状态
        registerActivityLifecycleCallbacks(new ActivityLifecycleListener());
        JCManager.getInstance().initialize(this);

        //初始化阿里推送
        AliPushManager.getInstance().initPush(getApplicationContext());
        //华为辅助推送通道
        HuaWeiRegister.register(MyApplication.this);
        // 小米辅助推送通道
        MiPushRegister.register(MyApplication.this, MIPUSH_APP_ID, MIPUSH_APP_KEY);
        initUMConfig();

        //图片上传工具类
        PreferencesUtils.init(this, "multiimage");
        initNotification();

        if (OpenRefWatcher) {
            if (LeakCanary.isInAnalyzerProcess(this)) {
                //此过程专用于LeakCanary进行堆分析。在此过程中不应初始化应用程序。
                return;
            }
            refWatcher = LeakCanary.install(this);
        }

    }
    public static RefWatcher getRefWatcher(Context context){
        MyApplication application = (MyApplication)context.getApplicationContext();
        return application.refWatcher;
    }
    /**
     * 定义notification渠道
     */
    private void initNotification() {
        createNotificationChannel(AliPushManager.PUSH_HOUSE_KEEPER,"管家消息","管家服务相关的通知", NotificationManager.IMPORTANCE_HIGH);
        createNotificationChannel(AliPushManager.PUSH_OFFICAL_MSG,"官方消息","系统通知",NotificationManager.IMPORTANCE_HIGH);
        createNotificationChannel(AliPushManager.PUSH_VIDEO_CALL,"视频通话","视频来电提醒",NotificationManager.IMPORTANCE_HIGH);
        createNotificationChannel(AliPushManager.OTHER,"其他","其他",NotificationManager.IMPORTANCE_LOW);

    }


    /**
     * 初始化友盟
     */
    private void initUMConfig() {
        MobclickAgent.openActivityDurationTrack(false);
        MobclickAgent.setCatchUncaughtExceptions(false);
        //友盟第三方登录 微信
//        //初始化组件化基础库, 统计SDK/推送SDK/分享SDK都必须调用此初始化接口
        UMConfigure.init(this, ConfigUtil.YOU_MENG_APPID,
                "", UMConfigure.DEVICE_TYPE_PHONE, "");
        MobclickAgent.setScenarioType(this, MobclickAgent.EScenarioType.E_UM_NORMAL);
    }

    /**
     * 配置okgo
     */
    private void setOkGoConfig() {
        //OKGO
        /**
         * 配置OkHttpClient
         */
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(3000, TimeUnit.SECONDS)
                .writeTimeout(3000, TimeUnit.SECONDS)
                .addInterceptor(new OkGoInterceptor("TokenInterceptor"))//添加获取token的拦截器
                .build();
        OkGo.getInstance().init(this)                       //必须调用初始化
                .setOkHttpClient(client)               //建议设置OkHttpClient，不设置将使用默认的
                .setCacheMode(CacheMode.NO_CACHE)               //全局统一缓存模式，默认不使用缓存，可以不传
                .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE);   //全局统一缓存时间，默认永不过期，可以不传
//                .setRetryCount(3);                           //全局统一超时重连次数，默认为三次，那么最差的情况会请求4次(一次原始请求，三次重连请求)，不需要可以设置为0
//                .addCommonHeaders(headers)                      //全局公共头
//                .addCommonParams(params);

        HttpProxy.getInstance().setNetProxyType(new OkgoTool());
    }

    {
        PlatformConfig.setWeixin(ConfigUtil.WEI_XIN_APPID, ConfigUtil.WEI_XIN_SECRET);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
//        resetDensity();//这个方法重写也是很有必要的
    }


    /**
     * 针对8.0及以上系统消息通知收不到时的处理
     * @param channelId
     * @param channelName
     * @param channelDes
     * @param ImportantLevel
     */
    private void createNotificationChannel(String  channelId,String  channelName,String  channelDes,int ImportantLevel) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            // 通知渠道的id
            String id = channelId;
            // 用户可以看到的通知渠道的名字.
            CharSequence name = channelName;
            // 用户可以看到的通知渠道的描述
            String description = channelDes;
            int importance = ImportantLevel;
            NotificationChannel mChannel = new NotificationChannel(id, name, importance);
            // 配置通知渠道的属性
            mChannel.setDescription(description);
            if (AliPushManager.OTHER.equals(channelId)) {
                // 设置通知出现时的闪灯（如果 android 设备支持的话）
                mChannel.enableLights(false);
                // 设置通知出现时的震动（如果 android 设备支持的话）
                mChannel.enableVibration(false);
                mChannel.setSound(null, null);
            }else{
                // 设置通知出现时的闪灯（如果 android 设备支持的话）
                mChannel.enableLights(true);
                mChannel.setLightColor(Color.RED);
                // 设置通知出现时的震动（如果 android 设备支持的话）
                mChannel.enableVibration(true);
                mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            }
            //最后在notificationmanager中创建该通知渠道
            mNotificationManager.createNotificationChannel(mChannel);
        }
    }


}
