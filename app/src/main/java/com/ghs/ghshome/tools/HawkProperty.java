package com.ghs.ghshome.tools;

/**
 * Author:wang_sir
 * Time:2018/7/13 14:40
 * Description:This is HawkProperty
 */
public class HawkProperty {

  public static String CURRENT_VILLAGE = "current_village";//当前所选小区//注意 调用的时候 需要绑定用户id一起使用
  public static String LOGIN_BEAN = "login_bean";//已登录
  public static String APP_TOKEN = "app_token";//接口的token
  public static String WEI_XIN_NAME = "wei_xin_name";//微信昵称
  public static String WEI_XIN_UNIONID = "wei_xin_unionId";//微信id
  public static String USER_AND_ROOM = "user_and_room";//用户房间关系表
  public static String PROPERTY_PHONE_NO = "property_phone_no";//物业电话
  public static String ALLOT_KEY_BEAN = "allot_key_bean";//钥匙分配实体类
  public static String BLUE_ADDRS = "blue_addr";//蓝牙地址列表
  public static String BLUE_ADDRS_LIST = "blue_addr_list";//蓝牙mac地址列表<String>
  public static String NOTICE_ALLOWED = "notice_allowed";//消息提醒
  public static String CARPART_STANDED = "carpark_standed";//停车标准
  public static String AUTO_RUN_NOTICE_DATE = "notice_auto_run_date";//自启动提醒时间
  public static String AUTORUN_NOTICE = "auto_run_notice";//自启动权限提醒
  public static String SEARCH_HIS_LIST = "search_his_list"+UserInfoUtil.getInstance().getUserId();//搜索历史记录
//  public static String ID_CHECK_STATUS = "id_check_status"+UserInfoUtil.getInstance().getUserId();//身份验证状态  * 无认证关系：0* 待认证：1* 认证通过 2* 认证不通过 3



}
