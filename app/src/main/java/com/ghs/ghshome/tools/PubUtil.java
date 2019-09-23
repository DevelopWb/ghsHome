package com.ghs.ghshome.tools;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.hardware.Camera;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.support.design.widget.TabLayout;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ghs.ghshome.R;
import com.ghs.ghshome.bean.UserAndRoomBean;
import com.ghs.ghshome.models.homePage.HomePageContract;
import com.ghs.ghshome.tools.glide.GlideManagerUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * Author:wang_sir
 * Time:2018/7/3 10:28
 * Description:This is PubUtil
 */
public class PubUtil {
    public static int MINE_BILL_TAG = -1;//1代表水费，2代表电费，3代表物业费
    public static int SEED_RECORD_TYPE= 1;//类型：1:增加 2：消耗
    public static String[] promissions = {Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.WRITE_EXTERNAL_STORAGE
            , Manifest.permission.CALL_PHONE, Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO};
    public static int MINE_BILL_PAY_STATUS_FINISHED = 98;//98代表已成功缴费，按钮状态为完成，
    public static int LOGIN_ENTER = 100;//进入登录界面的入口。1为homefragment 2为设置中退出||在minefragment中登录,3为token过期时的跳转,4 无小区列表 需要认证
    public static String MINE_BILL_TAG_STR = "";//water代表水费，ammeter代表电费，property代表物业费
    public static boolean LOGOUT_LOADED = false;//注销登录成功。
    public static boolean ADDED_GUESTER = false;//已经添加租户了
    public static int LEFT_KEY_NUM = -1;//剩余钥匙数量
    public static int SUBMIT_CHECK_ENTRY = 0;//提交认证的入口 0代表第一次登录之后跳转的，1代表房屋管理跳转的
    public static int ScreenHeight;//屏幕高度
    public static int ScreenWidth;//屏幕宽度
    public static String WEI_XIN_PAY_TAG = "";
    public static String CAR_WEI_XIN_PAY_TAG = "";//车场管理微信支付结果
    public static boolean isQuiteCurrentAccount = false;//退出当前账户

    //EventBusMsg
    public static final String EVENTBUS_TOCKEN_EXPIRED = "eventbus_tocken_expired";//token过期
    public static final String EVENTBUS_TOCKEN_NO_ACTION= "eventbus_tocken_no_action";//切换到登录界面，无提醒
    public static  final String EVENTBUS_TOCKEN_NOT_EXIST = "eventbus_tocken_not_exist";//token不存在
    public static final String EVENTBUS_TOCKEN_TEST_FAILED = "eventbus_tocken_test_failed";//token验证错误
    public static boolean tokenExpiredFirstNotice = true;//token过期第一次提醒


    public static int WEI_XIN_PAY_ENTER = 0;//支付入口，0代表账单管理 1代表 支付车费

    public static int ADD_CAR_NUM_ENTER = 0;//跳转到填写车牌的类  0代表绑定车牌 1代表 添加访客里面的添加车牌
    public static List<String> usuallyServers = new ArrayList<>();//常用服务的名称


    /**
     * 获取随机4位数
     */
    public static String getRandomData() {
        String strRand = "";
        for (int i = 0; i < 5; i++) {
            strRand += String.valueOf((int) (Math.random() * 10));
        }
        return strRand;
    }

    /**
     * 验证手机格式
     */
    public static boolean isMobileNO(String mobiles) {
        /*
         * 移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
         * 联通：130、131、132、152、155、156、185、186 电信：133、153、180、189、（1349卫通）
         * 总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
         */
        String telRegex = "[1][23456789]\\d{9}";// "[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(mobiles)) {
            return false;
        } else {
            return mobiles.matches(telRegex);
        }
    }

    /**
     * 设置控件的margin
     *
     * @param v
     * @param l
     * @param t
     * @param r
     * @param b
     */
    public static void margin(View v, int l, int t, int r, int b) {
        if (v.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
            p.setMargins(l, t, r, b);
            v.requestLayout();
        }
    }

    /**
     * 拨打电话（直接拨打电话）
     *
     * @param phoneNum 电话号码
     */
    public static void callPhone(Context context, String phoneNum) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:" + phoneNum);
        intent.setData(data);
        context.startActivity(intent);
    }

    /**
     * inputStream转String
     *
     * @param in
     * @return
     * @throws IOException
     */
    public static String inputStreamString(InputStream in) throws IOException {

        StringBuffer out = new StringBuffer();
        byte[] b = new byte[4096];
        int n;
        while ((n = in.read(b)) != -1) {
            out.append(new String(b, 0, n));
        }
        Log.i("String的长度", new Integer(out.length()).toString());
        return out.toString();
    }

    /**
     * 获取用户的类型
     *
     * @param type
     * @return
     */
    public static String getUserType(int type) {
        String userType = "";
        switch (type) {
            case 1:
                userType = "业主";
                break;
            case 2:
                userType = "业主家人";
                break;
            case 3:
                userType = "租客";
                break;
            case 4:
                userType = "租客家人";
                break;
            default:
                break;
        }
        return userType;
    }


    /**
     * 检测服务端返回数据的状态
     *
     * @param content
     * @return
     */
    public static boolean initContent(String content) {
        boolean status = false;
        if (StrUtils.isStringValueOk(content)) {
            try {
                JSONObject object = new JSONObject(content);
                int code = object.getInt("code");
                if (code == 1000) {
                    status = true;
                } else {
                    status = false;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        return status;
    }

    /**
     * 获取服务端返回的message信息
     *
     * @param content
     * @return
     */
    public static String getServerMessage(String content) {
        String message = "";
        if (StrUtils.isStringValueOk(content)) {
            try {
                JSONObject object = new JSONObject(content);
                message = object.getString("message");
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        return message;
    }

    /**
     * 获取服务端返回的data信息
     *
     * @param content
     * @return
     */
    public static String getServerData(String content) {
        String data = "";
        if (StrUtils.isStringValueOk(content)) {
            try {
                JSONObject object = new JSONObject(content);
                data = object.getString("data");
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        return data;
    }

    /**
     * code
     *
     * @param content
     * @return
     */
    public static int getServerCode(String content) {
        int code = -1;
        if (StrUtils.isStringValueOk(content)) {
            try {
                JSONObject object = new JSONObject(content);
                code = object.getInt("code");
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        return code;
    }

    /**
     * 判定微信是否安装
     *
     * @param context
     * @return
     */
    public static boolean isWeixinAvilible(Context context) {
        final PackageManager packageManager = context.getPackageManager();// 获取packagemanager
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);// 获取所有已安装程序的包信息
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                if (pn.equals("com.tencent.mm")) {
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * 获取软件版本号
     */
    public static String getAPPVersion(Context context) {
        PackageManager pm = context.getPackageManager();//得到PackageManager对象
        String version_app = "";
        try {
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);//得到PackageInfo对象，封装了一些软件包的信息在里面
            version_app = pi.versionName;//获取清单文件中versionCode节点的值
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return version_app;
    }

    /**
     * 将像素转换成dp
     *
     * @param context
     * @param pxValue
     * @return
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }


    /**
     * 判断摄像头是否可用
     * 主要针对6.0 之前的版本，现在主要是依靠try...catch... 报错信息，感觉不太好，
     * 以后有更好的方法的话可适当替换
     * <p>
     * https://blog.csdn.net/jm_beizi/article/details/51728495
     *
     * @return
     */
    public static boolean isCameraCanUse() {
        boolean canUse = true;
        Camera mCamera = null;
        try {
            mCamera = Camera.open();
            // setParameters 是针对魅族MX5 做的。MX5 通过Camera.open() 拿到的Camera
            // 对象不为null
            Camera.Parameters mParameters = mCamera.getParameters();
            mCamera.setParameters(mParameters);
        } catch (Exception e) {
            canUse = false;
        }
        if (mCamera != null) {
            mCamera.release();
        }
        return canUse;
    }


    /**
     * EditText获取焦点并显示软键盘
     */
    public static void showSoftInputFromWindow(Activity activity, EditText editText) {
        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();
        activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
    }

    /**
     * 获取IP地址
     *
     * @param context
     * @return
     */
    public static String getIPAddress(Context context) {
        NetworkInfo info = ((ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        if (info != null && info.isConnected()) {
            if (info.getType() == ConnectivityManager.TYPE_MOBILE) {//当前使用2G/3G/4G网络
                try {
                    //Enumeration<NetworkInterface> en=NetworkInterface.getNetworkInterfaces();
                    for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                        NetworkInterface intf = en.nextElement();
                        for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                            InetAddress inetAddress = enumIpAddr.nextElement();
                            if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {
                                return inetAddress.getHostAddress();
                            }
                        }
                    }
                } catch (SocketException e) {
                    e.printStackTrace();
                }

            } else if (info.getType() == ConnectivityManager.TYPE_WIFI) {//当前使用无线网络
                WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
                WifiInfo wifiInfo = wifiManager.getConnectionInfo();
                String ipAddress = intIP2StringIP(wifiInfo.getIpAddress());//得到IPV4地址
                return ipAddress;
            }
        } else {
            //当前无网络连接,请在设置中打开网络
        }
        return null;
    }

    /**
     * 将得到的int类型的IP转换为String类型
     *
     * @param ip
     * @return
     */
    public static String intIP2StringIP(int ip) {
        return (ip & 0xFF) + "." +
                ((ip >> 8) & 0xFF) + "." +
                ((ip >> 16) & 0xFF) + "." +
                (ip >> 24 & 0xFF);
    }

    /**
     * 获取工单类型名
     *
     * @param orderType 1:家庭报修、2:投诉工单、3:建议工单、4.公共报修5:报事工单、6:其它工单
     */
    public static String getOrderTypeName(int orderType) {
        String name = "";
        switch (orderType) {
            case 1:
                name = "家庭报修";
                break;
            case 2:
                name = "投诉工单";
                break;
            case 3:
                name = "建议工单";
                break;
            case 4:
                name = "公共报修";
                break;
            case 5:
                name = "报事工单";
                break;
            case 6:
                name = "其他工单";
                break;
            default:
                name = "工单";
                break;
        }
        return name;
    }


    /**
     * 设置tablayout底部导航条的宽度
     *
     * @param tabs
     * @param leftDip  距离左边的边距
     * @param rightDip 距离右边的边距
     */
    public static void setIndicator(TabLayout tabs, int leftDip, int rightDip) {
        try {
            Field tabStrip = tabs.getClass().getDeclaredField("mTabStrip");
            tabStrip.setAccessible(true);
            LinearLayout llTab = null;
            llTab = (LinearLayout) tabStrip.get(tabs);
            int left = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, leftDip, Resources.getSystem().getDisplayMetrics());
            int right = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, rightDip, Resources.getSystem().getDisplayMetrics());

            for (int i = 0; i < llTab.getChildCount(); i++) {
                View child = llTab.getChildAt(i);
                child.setPadding(0, 0, 0, 0);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
                params.leftMargin = left;
                params.rightMargin = right;
                child.setLayoutParams(params);
                child.invalidate();
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    //     验证身份证号
    public static boolean isIDCard(String idcard) {
        return IDCardValidate.validate_effective(idcard);
    }
    /**
     * 获取activity
     * @param context
     * @return
     */
    public static  Activity getActivity(Context context) {
        while (!(context instanceof Activity) && context instanceof ContextWrapper) {
            context = ((ContextWrapper) context).getBaseContext();
        }
        if (context instanceof Activity) {
            return (Activity) context;
        }else {
            return null;
        }
    }
    /**
     * 获取房间信息
     * @param bean
     * @return
     */
    public static String getRoomMsg(UserAndRoomBean.DataBean.GhsUserRoomDOBean bean) {
        String villageMsg = bean.getVillageMsg();
        if (StrUtils.isStringValueOk(villageMsg)) {
            if (villageMsg.contains("-")) {
                villageMsg = villageMsg.substring(villageMsg.indexOf("-")+1,villageMsg.length());
            }
        }
        return villageMsg;
    }

    /**
     * 获取消息类型
     *
     * @param msgType
     * @return
     */
    public static  String getMsgTypeName(String msgType) {
        String msgName = "";
        switch (msgType) {
            case HomePageContract.NOTICI_VILLAGE:
                msgName = "小区公告";
                break;
            case HomePageContract.NOTICI_REPAIR:
                msgName = "报修";
                break;
            case HomePageContract.NOTICI_SUGGESTION:
                msgName = "投诉建议";
                break;
            case HomePageContract.NOTICI_BILL:
                msgName = "房屋账单";
                break;
            case HomePageContract.NOTICI_VISITOR:
                msgName = "邀请访客";
                break;
            case HomePageContract.NOTICI_GOODS_INTO:
                msgName = "物品放行";
                break;
            case HomePageContract.NOTICI_DECORATION:
                msgName = "装修备案";
                break;
            case HomePageContract.NOTICI_FIRE:
                msgName = "动火备案";
                break;
            case HomePageContract.NOTICI_SERVICE_WORK:
                msgName = "一键呼叫";
                break;
            case HomePageContract.OFFICICAL_ACTIVITY:
                msgName = "官方活动";
                break;
            case HomePageContract.NOTICI_VILLAGRE_ACTIVITY:
                msgName = "社区活动";
                break;
            case HomePageContract.VISITOR_CHECK:
                msgName = "访客审核";
                break;
            case HomePageContract.USER_AUTH:
                msgName = "用户认证";
                break;
            default:
                break;
        }
        return msgName;
    }

    /**
     * 获取物业大厅菜单
     * @return
     */
    public  static  List<String>  getPropertyHallMenus(){
        List<String>  menus = new ArrayList<>();
        menus.add("小区公告");
        menus.add("报修");
        menus.add("投诉建议");
        menus.add("房屋账单");
        menus.add("手机开门");
        menus.add("邀请访客");
        menus.add("物品放行");
        menus.add("装修备案");
        menus.add("人脸开门");
        menus.add("授权管理");
        menus.add("车辆车位");
        return menus;
    }
}
