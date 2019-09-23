package com.ghs.ghshome.tools;

/**
 * Author:wang_sir
 * Time:2018/7/9 11:23
 * Description:This is ActivityResultManager
 */
public interface ActivityResultManager {

    public static int ADD_KEY_RESULT = 100;
    public static int LOGIN_MOBILE_SUCESSED = 99;//手机号码登录成功
//    public static int LOGIN_MOBILE_CANCEL = 199;//手机号码登录取消
    public static int SELECT_VILLAGE_SUCESSED = 98;//选择小区成功
    public static int TAKE_PICTURE = 97;//调用系统相机拍照  默认
    public static int SYSTEM_PICTURE = 96;//系统相册中选择图片  默认
    public static int EDIT_USER_MODIFY_MOBILE = 95;//修改手机号成功
    public static int MINE_BILL_SELECT_DATE = 94;//我的账单 选择时间
    public static int MINE_SET_QUIT_APP = 93;//设置 退出应用
    public static int VILLIAGE_NOTICE_DETAIL = 92;//小区公告详情类退出
    public static int VILLIAGE_NOTICE = 91;//小区公告类退出
    public static int LIFE_BILL = 90;//生活账单类退出
    public static int CONTACT_PROPERTY = 89;//联系物业类退出
    public static int GROWTH_TASK = 88;//成长任务类退出
    public static int EDIT_USER_INFO = 87;//编辑用户资料
    public static int OPEN_RECORD = 86;//开门记录
    public static int ALLOT_KEY = 85;//钥匙分配
    public static int QUIT_CURRENT_ACCOUNT_FOR_LOGIN_CANCEL = 83;//注销登录取消
//    public static int QUIT_CURRENT_ACCOUNT_FOR_LOGIN = 84;//注销登录
    public static int MODIFY_NICK_NAME = 83;//修改昵称
    public static int LOGIN_TOKEN_EXPIRED = 82;//token过期时login的操作 包括取消和登录成功
    /**
     * 车辆管理
     */
    public static int CAR_PARK_ADD_CAR = 81;//绑定车辆
    public static int ADD_VISITOR_ADD_CAR = 80;//添加访客中的绑定车辆
    public static int ADD_VISITOR_ADD_VISITOR = 79;//添加访客
    public static int ADD_VISITOR_DEAL_REQUEST = 78;//处理访客申请
    public static int BILL_HISTORY = 77;//历史账单
    public static int PARKING_PAYED = 76;//停车费支付成功
    public static int HOME_PAGE_REQUEST = 75;//首页跳转 返回的result
    public static int ENTER_ORDER_DETAIL = 74;//进入订单详情
    /**
     * 物品放行
     */
    public static int ADD_PROPERTY = 73;
    public static String ARTICLE_ID="article_id";//物品放行详情id

    /**
     * 发现
     */
    public static int COMMUNITY_APLY = 72;
    public static int FITMENT_SW = 71;//装修动火刷新
    public static int OFFICAL_MSG_DETAIL = 70;//跳转官方消息详情

    /**
     * 人脸采集
     */
    public static int TASKED_FACE_PHOTO = 69;
    public static int TASKED_FACE_LIST=68;//人脸列表
    public static int SEED_TASK_LIST_CLICK=67;//种子跳转
    public static int QUIT_SEED_ACTIVITY=66;//退出种子
    /***
     * 住户管理
     */
    public static int TENEMENT_DETAILS=65;//住户详情


    /**
     * 使用指南
     */
    public static String USER_GUID_TAG = "user_guid_tag";
    //人脸开门
    public static String OPEN_BY_FACE="openbyface";
    //手机开门
    public static String OPEN_BY_MOBILE="openbymobile";
    //添加新房屋
    public static String ADD_NEW_HOUSE="addnewhouse";
    //授权管理
    public static String  AUTHORIZATION_MANGER="AUTHORIZATIOMANGER";
    //房屋账单
    public static String HOUSE_BILL="houserbill";


    public static String HOME_RED_POINT_SHOWN = "home_red_point_shown";
    public static String MINE_HEAD_PIC_CLICKED = "mine_head_pic_clicked";
    public static String SET_LOGOUT = "set_logout";//注销
    public static String NICK_NAME = "nick_name";//昵称
    public static String EDIT_SERVICES_USUALLY = "edit_services";//编辑服务 常用服务


    /**
     * 车辆管理
     */
    public static String OUT_PARK_BEAN = "out_park_bean";//昵称
    public static String CAR_NUM = "car_num";//昵称


    public static final int REQUEST_OPEN_BLUETOOTH = 101;//请求打开蓝牙
    public static final int BOUND_MOBILE = 97;//绑定手机


    public static String virtureKey = "38628c516a8a4b4ab9226b9edd161074";


    public static String WEI_XIN_PAY_ERROR = "wei_xin_pay_error";
    public static String WEI_XIN_PAY_SUCCESS = "wei_xin_pay_success";


    public static String SAVED_MOBILE = "saved_mobile";//单用户登录时保存的手机号
    public static String SAVED_MOBILE_TOKEN_EXPIRED = "saved_mobile_token_expired";//token失效时重新登录前保存的手机号

    String DISPLAY_PHOTOS = "display_photos";//展示的图片
    String DISPLAY_PHOTO_POSITION = "display_photo_position";//展示的图片的起点


}
