package com.ghs.ghshome.tools;

/**
 * Author:wang_sir
 * Time:2018/7/9 9:43
 * Description:This is Contract
 */
public class Contract {


    /**
     * 图片的绝对路径
     */
    public static String ImageBasePath = "http://image.guangheplus.com/images/";
    /**
     * 图片baseUrl
     */
    public static String ImageBaseURL = "http://image.guangheplus.com/";
    //用户协议地址
    public static String USER_PROTOCAL_URL = "http://image.guangheplus.com/html/protocol.html";//用户协议地址
//    /*BaseUrl*/
    public static String BASE_URL_DEBUG = "https://rd.ghsapi.guangheplus.com";
    /*BaseUrl*/
//    public static String BASE_URL_DEBUG = "http://192.168.1.184:8090";
    //    /*BaseUrl*/
    public static String BASE_URL_RELEASE = "https://ghsapi.guangheplus.com";
    public static String BASE_URL = "";

    /*  登录模块*/

    public static String SMS_CODE_TYPE_LOGIN = "LOGIN";
    public static String SMS_CODE_TYPE_BINDMOBILE = "BINDMOBILE";
    public static String SMS_CODE_TYPE_UPDATEMOBILE = "UPDATEMOBILE";

    //获取验证码
    public static String LOGIN_SMS_CODE = "/appLogin/getSmsCode";
    //手机号登录
    public static String LOGIN_BY_TEL_NO = "/appLogin/mobileLogin";
    //微信登录
    public static String LOGIN_BY_WEIXIN = "/appLogin/wxLogin";
    //绑定手机号
    public static String BOUND_MOBILE = "/appLogin/mobileBind";
    //用户信息和房间信息
    public static String USER_ROOM_INFO = "/appLogin/getUserAndUserRoom";

    //用户房间列表
    public static String LOGIN_USER_ROOMS = "/appLogin/getUserRoomList";

    /* 个人中心*/
//获取优惠卷（分页）
    public static String MINE_COUPONS = "/userCenter/couponList";
    //获取优惠卷（不带分页）
    public static String MINE_COUPONS_NO_PAGE = "/userCenter/couponListNoPage";
    //获取OSS上传图片token
    public static String MINE_OSS_TOKEN = "/userCenter/getOssToken";
    //	获得用户信息
    public static String MINE_USER_INFO = "/userCenter/getUser";
    //投诉建议（最多3张图）
    public static String MINE_APP_SUGGUEST = "/userCenter/suggest";
    //修改手机号
    public static String MINE_UPDATE_MOBILE = "/userCenter/updateMobile";
    //修改用户信息
    public static String MINE_UPDATE_USER_INFO = "/userCenter/updateUser";


    /*住户管理*/
    //钥匙分配列表
    public static String ALLOT_KET_LIST = "/key/getUserRoomList";
    //添加钥匙
    public static String ALLOT_KET_ADD = "/key/addKey";
    //获取用户姓名
    public static String GET_USER_NAME = "/key/getUserNameByMobile";
    //删除钥匙
    public static String ALLOT_KET_DELETE = "/key/deleteKey";
    //修改钥匙数量
    public static String ALLOT_KET_MODIFY = "/key/updateLeftKeyNum";

    /*  开门记录模块*/
    //获取开门记录列表
    public static String OPEN_RECORD_LIST = "/lockLog/getLockLogList";
    //设置是否允许查看
    public static String OPEN_RECORD_SHOWABLE = "/lockLog/updateShowLockLog";

    /*联系物业模块*/
    //客户评价
    public static String CONTACT_PROPERTY_EVALUATE = "/serviceWork/evaulate";
    //获取工单列表
    public static String CONTACT_PROPERTY_ORDER_LIST = "/serviceWork/list";
    //物业电话
    public static String CONTACT_PROPERTY_MOBILE = "/serviceWork/serviceMobile";
    //修改工单状态
    public static String CONTACT_PROPERTY_EDIT_OEDER_STATUS = "/serviceWork/updateState";


    /*成长任务模块*/
    //获取积分等级情况
    public static String GROWTH_INTEGRAL_LEVEL = "/integralTask/integralLevelMsg";
    //玩家任务列表
    public static String GROWTH_TASK_LIST = "/integralTask/integralTaskList";
    //玩家任务列表
    public static String GROWTH_TASK_LIST_ALL = "/integralTask/integralTaskListAll";
    //获取任务积分
    public static String GROWTH_TASK_INTEGRAL = "/integralTask/receiveIntegral";
    //获取签到礼包
    public static String GROWTH_SIGN_GIFTBAG = "/integralTask/receiveSignGiftBag";
    //签到获取积分（限时任务）
    public static String GROWTH_SIGNIN_INTEGRAL = "/integralTask/signIn";
    //累计签到天数
    public static String GROWTH_SIGNIN_DAYS = "/integralTask/signInDays";

    /*远程开门模块*/
    //蓝牙mac地址列表
    public static String REMOTE_OPEN_MACS = "/openLockDevice/getBlueMacList";
    //门禁列表
    public static String REMOTE_OPEN_LOCKS = "/openLockDevice/getLockDeviceList";
    //门禁密码
    public static String REMOTE_OPEN_LOCK_PWD = "/openLockDevice/getPassword";
    //网络开门
    public static String REMOTE_OPEN_BY_NET = "/openLockDevice/openLockDevice";


    /*  生活账单*/
    //获取账单
    public static String LIFE_BILL_LIST = "/bill/orderDetailList";
    //获取账单详情
    public static String LIFE_BILL_DETAIL = "/bill/orderDetail";
    //获取饼型图表数据
    public static String SECTOR_CHART_DATE = "/bill/circleFee";
    //获取线型图表数据
    public static String LINEAR_CHART_DATE = "/bill/lineFee";

    /*小区公告*/
    //公告列表
    public static String VILLAGE_NOTICE_LIST = "/notice/noticeList";
    //公告状态
    public static String READ_VILLAGR_NOTICE = "/notice/noticeDetail";

    /*微信支付*/
//微信支付
    public static String WEI_XIN_PAY = "/pay/payFee";

    /**
     * 小红点
     */
//查看提醒状态
    public static String NOTICE_STATUS = "/remindRedDot/redDot";//查看提醒状态
    //点击红点
    public static String CLICK_RED_POINT = "/remindRedDot/clickRedDot";//点击红点


    /**
     * 车场管理
     */

    //获取停车规则
    public static String GET_PARK_RULE = "/carpark/getCarPortRuleMsg";

    //获取绑定的车辆信息
    public static String GET_BOUND_CARS = "/carpark/carList";

    //获取车费记录
    public static String GET_CARS_FEE_RECORD = "/carpark/carFeeList";

    //出场
    public static String OUT_OF_PARK = "/carpark/ownerDriveOff";

    //解除绑定
    public static String UNBIND_CAR = "/carpark/removeCar";

    //绑定
    public static String BIND_CAR = "/carpark/saveCar";
    //车位列表
    public static String PARKING_SPACE_LIST = "/carpark/carportList";


    /**
     * 访客
     */
    //查找添加的访客
    public static String SEARCH_VISITOR = "/visitor/ownerVisitorList";

    //添加访客
    public static String ADD_VISITOR = "/visitor/ownerAddVisitor";

    //接受访客
    public static String ACCESS_VISITOR = "/visitor/checkAgree";

    //拒绝访客
    public static String REJECT_VISITOR = "/visitor/checkReject";
    //访客个人信息
    public static String GET_VISITOR_MSG = "/visitor/detail";


    /**
     * 首页（新）
     */
    //获取常用服务
    public static String GET_USUALLY_USED_SERVICES = "/appLogin/getUserUseAppMenu";
    //保存常用服务
    public static String SAVE_USUALLY_USED_SERVICES = "/appLogin/saveUserUseAppMenu";
    //获取所有的服务
    public static String GET_ALL_SERVICES = "/appLogin/getPropertyHallAppMenu";

    /**
     * 客服工单
     */

    //一键呼叫工单列表
    public static String WORK_ORDER_LIST = "/serviceWork/oneTouchCallList";

    //一键呼叫工单详情
    public static String WORK_ORDER_DES = "/serviceWork/detail";
    //400电话
    public static String CUSTOMER_SERVER_TEL = "/serviceWork/serviceMobile";
    //客服工单列表
    public static String CUSTOMER_WORK_ORDER_LIST = "/serviceWork/serviceWorkList";

    /**
     * 物品放行
     */
    //物品放行列表
    public static String PROPERTYPASS_LIST = "/goodsPermitThrough/list";
    //物品详情
    public static String PROPERTY_PARTICULARS = "/goodsPermitThrough/detail";
    //添加物品放行
    public static String ADDPROPERTY = "/goodsPermitThrough/save";
    //报修
    public static String ADDREPAIRS = "/serviceWork/saveRepair";
    //投诉建议
    public static String COMPALAINT_SUGGEST = "/serviceWork/saveComplainOrSuggest";

    /**
     * 管家服务
     */

    //首页的客服管家数据
    public static String GET_HOME_HOUSE_KEEPER_DATA = "/housekeeper/housekeeperIndex";
    //客服管家分页数据
    public static String GET_HOUSE_KEEPER_ALL_DATA = "/housekeeper/housekeeperList";

    /**
     * 官方公告
     */
    //未读官方通告数
    public static String UNREAD_OFFICIAL_NOTICE = "/officialNotice/getUnreadNum";
    //官方通告列表
    public static String OFFICIAL_NOTICE_LIST = "/officialNotice/noticeList";
    //读取官方通告
    public static String READ_OFFICIAL_NOTICE = "/officialNotice/readNotice";

    /**
     * 装修备案
     */
    //装修备案列表
    public static String DECORATION_LIST = "/recordDecoration/list";
    //新建装修备案
    public static String DECORATION_FITMENT_SAVE = "/recordDecoration/saveDecoration";
    //新建动火备案
    public static String DECORATION_FITMENT_FIRE_SAVE = "/recordDecoration/saveFire";
    //装修备案详情
    public static String FITMENT_PARTICULARS = "/recordDecoration/detailDecoration";
    //动火备案详情
    public static String FITMENT_FIRE_PARTICULARS = "/recordDecoration/detailFire";

    /**
     * 发现活动
     */
    //轮播图地址
    public static String BANNER_LIST = "/discover/bannerList";
    //点击轮播图增量
    public static String CLICKE_BANNER = "/discover/clickBanner";
    //社区活动列表
    public static String COMMUNITY_LIST = "/activity/list";
    //社区活动详情
    public static String COMMUNITY_PARTICULAR = "/activity/detail";
    //报名
    public static String COMMUNITY_APPLY = "/activity/apply";
    //官方资讯列表
    public static String OFFICAIAL_LIST = "/officialInformation/list";
    //官方资讯详情
    public static String OFFICAIAL_PATTICULARS = "/officialInformation/detail";
    //我报名活动列表
    public static String MY_EVENT_LIST = "/activity/ownerList";

    /**
     * 人脸开门
     */
    //获取人脸开门信息
    public static String GETUSERFACEINFO = "/ufaceDevice/getUfaceUserInfo";
    //刷新权限
    public static String REFRESHROLE = "/ufaceDevice/refreshRole";
    //获取照片列表
    public static String GETPHOTOLIST = "/ufaceDevice/getPhotoList";
    //删除人脸照片
    public static String DELETEPHOTO = "/ufaceDevice/deletePhoto";
    //采集人脸照片
    public static String ADD_PHOTO = "/ufaceDevice/addPhoto";


    /**
     * 种子
     */
    //种子任务列表
    public static String SEED_TASK_LIST = "/seedTask/list";
    //种子记录列表
    public static String SEED_RECORD_LIST = "/seedTask/seedDetailList";
    //获取种子数量
    public static String GET_SEED_AMOUNT = "/seedTask/getSeedNum";
    //执行种子任务
    public static String EXEC_SEED_TASK = "/seedTask/executeSeedTask";


    /**
     * 房屋管理
     *
     */
    //获取小区列表
    public static String GET_VILLAGE_LIST = "/userRoom/getVillageWithCity";
    //获取城市列表
    public static String GET_CITY_LIST = "/userRoom/getPropertyHallAppMenu";
    //获取单元列表
    public static String GET_CELL_LIST = "/userRoom/getCell";
    //获取房间列表
    public static String GET_ROOM_LIST = "/userRoom/getRoom";
    //提交认证
    public static String SUBMIT_CHECK = "/userRoom/addUserRoom";
    //提交认证
    public static String RESUBMIT_CHECK = "/userRoom/resubmit";
    //退出房间
    public static String QUIT_ROOM = "/userRoom/deleteUserRoom";
    //搜索小区
    public static String SEARCH_VILLAGE = "/userRoom/getVillageWithKey";

    /**
     * 住户管理
     */
    //添加认证用户
    public static String ADD_KEY = "/key/addKey";
    //移除用户
    public static String DELETE_KEY = "/key/deleteKey";
    //获取用户姓名通过手机号获取
    public static String GET_USERNAME_MOBILE = "/key/getUserNameByMobile";
    //获取住户列表
    public static String GET_USERROMM_LIST = "/key/getUserRoomList";



}
