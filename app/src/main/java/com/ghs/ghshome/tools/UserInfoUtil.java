package com.ghs.ghshome.tools;

import com.ghs.ghshome.bean.LoginBean;
import com.ghs.ghshome.bean.UserAndRoomBean;
import com.ghs.ghshome.models.push.AliPushManager;
import com.orhanobut.hawk.Hawk;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Author:wang_sir
 * Time:2018/8/10 15:33
 * Description:This is UserInfoUtil
 * 和房屋相关的  从当前选中的小区的实体中获取数据   和用户个人信息相关的  从登陆返回的实体中获取数据
 */
public class UserInfoUtil {


    public UserInfoUtil() {
    }

    public static UserInfoUtil getInstance() {
        return UserInfoUtilHolder.instance;
    }

    private static class UserInfoUtilHolder {
        private static UserInfoUtil instance = new UserInfoUtil();
    }


    /**
     * 获取登录返回的实体类
     *
     * @return
     */
    private UserAndRoomBean.DataBean.GhsUserDOBean getLoginDataBean() {
        UserAndRoomBean.DataBean.GhsUserDOBean dataBean = null;
        LoginBean loginBean = Hawk.get(HawkProperty.LOGIN_BEAN);
        if (loginBean != null) {
            dataBean = loginBean.getData();
        }
        return dataBean;
    }

    /**
     * 获取用户房间关系的key
     *
     * @return
     */
    public String getUserAndRoomKey() {
        return getUserId() + HawkProperty.USER_AND_ROOM;
    }


    /**
     * 获取用户房间关系表中的用户基本信息实体
     *
     * @return
     */
    public UserAndRoomBean.DataBean.GhsUserDOBean getUserAndRoomUserBean() {
        UserAndRoomBean.DataBean dataBean = null;
        UserAndRoomBean bean = Hawk.get(getUserAndRoomKey());
        if (bean != null) {
            dataBean = bean.getData();
            if (dataBean != null) {
                return dataBean.getGhsUserDO();
            }
        }
        return null;
    }

    /**
     * 获取用户房间关系表中的用户房间信息实体
     *
     * @return
     */
    public UserAndRoomBean.DataBean.GhsUserRoomDOBean getUserAndRoomUserRoomBean() {
        UserAndRoomBean.DataBean dataBean = null;
        UserAndRoomBean bean = Hawk.get(getUserAndRoomKey());
        if (bean != null) {
            dataBean = bean.getData();
            if (dataBean != null) {
                return dataBean.getGhsUserRoomDO();
            }
        }
        return null;
    }

    /**
     * 获取登录返回的实体类
     *
     * @return
     */
    public UserAndRoomBean.DataBean getUserInfosBean() {
        UserAndRoomBean.DataBean dataBean = null;
        UserAndRoomBean bean = Hawk.get(getUserAndRoomKey());
        if (bean != null) {
            dataBean = bean.getData();
        }
        return dataBean;
    }


    /**
     * 获取账号状态
     *
     * @return//2停用 3删除
     */
    public int getUserStatus() {
        int status = -1;
        if (getUserInfosBean() != null) {
            if (getUserInfosBean().getGhsUserRoomDO() != null) {
                status = getUserInfosBean().getGhsUserRoomDO().getUserState();
            } else {
                if (getCurrentVillageBean() == null) {
                    status = 3;
                } else {
                    status = getCurrentVillageBean().getUserState();
                }
            }
        } else {
            if (getCurrentVillageBean() == null) {
                status = 3;
            } else {
                status = getCurrentVillageBean().getUserState();
            }

        }
        return status;
    }

    /**
     * 获取j角色类型
     * 角色 1业主 2业主家人 3租客 4租客家人
     *
     * @return
     */
    public int getRoleType() {
        if (getCurrentVillageBean() == null) {
            return -1;
        }
        return getCurrentVillageBean().getRoleType();
    }

    /**
     * 是否展示开门记录
     *
     * @return
     */
    public int getShowLockLog() {
        if (getCurrentVillageBean() == null) {
            return -1;
        }
        return getCurrentVillageBean().getShowLockLog();
    }

    /**
     * 本地保存的开门密钥
     *
     * @return
     */
    public String getLockPassword() {
        if (getCurrentVillageBean() == null) {
            return "";
        }
        return getCurrentVillageBean().getLockPassword();
    }


    /**
     * 判断是否已经登录和选择小区
     *
     * @return
     */
    public boolean isLoadAndSelectVillage() {
        if (Hawk.contains(HawkProperty.LOGIN_BEAN) && isSelectedCurrentVillage()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断是否已经登录
     *
     * @return
     */
    public boolean isLogin() {
        return Hawk.contains(HawkProperty.LOGIN_BEAN);
    }


    /**
     * 当前选中小区里面的信息-------###########################################################-----------------以下
     */

    /**
     * 获取当前小区的key
     *
     * @return
     */
    public String getCurrentVillageKey() {
        return getUserId() + HawkProperty.CURRENT_VILLAGE;
    }

    /**
     * 检查是否已经选择了当前小区
     *
     * @return
     */
    public boolean isSelectedCurrentVillage() {
        if (Hawk.contains(getCurrentVillageKey())) {
            UserAndRoomBean.DataBean.GhsUserRoomDOBean bean = Hawk.get(getCurrentVillageKey());
            if (bean == null) {
                Hawk.delete(getCurrentVillageKey());
                return false;
            }
            return true;
        }
        return false;
    }

    /**
     * 获取房间号
     *
     * @return
     */
    public String getRoomName() {
        if (getCurrentVillageBean() == null) {
            return "-1";
        }
        return getCurrentVillageBean().getRoomNumber();


    }

    /**
     * cellId  单元ID
     *
     * @return
     */
    public int getCellId() {
        if (getCurrentVillageBean() == null) {
            return -1;
        }
        return getCurrentVillageBean().getCellId();


    }

    /**
     * 获取认证状态
     * 用户状态 1.认证通过、2.认证不通过、3.待认证
     *
     * @return
     */
    public int getCheckStatus() {
        if (getCurrentVillageBean() == null) {
            return -1;
        }
        return getCurrentVillageBean().getUserState();


    }

    /**
     * cellId  单元名
     *
     * @return
     */
    public String getCellname() {
        if (getCurrentVillageBean() == null) {
            return "";
        }
        return getCurrentVillageBean().getCellName();


    }

    /* cellId  楼号
     *
     * @return
     */
    public String getPowerName() {
        if (getCurrentVillageBean() == null) {
            return "";
        }
        return getCurrentVillageBean().getTowerNumber();


    }

    /* cellId  期数名
     *
     * @return
     */
    public String getPortionName() {
        if (getCurrentVillageBean() == null) {
            return "";
        }
        return getCurrentVillageBean().getPortionName();


    }

    /**
     * 小区名称
     *
     * @return
     */
    public String getVillageName() {
        if (getCurrentVillageBean() != null) {
            return getCurrentVillageBean().getVillageName() + getCurrentVillageBean().getPortionName();
        }
        return "";

    }

    /**
     * 小区描述信息
     *
     * @return
     */
    public String getVillageMsg() {
        if (getCurrentVillageBean() != null) {
            return getCurrentVillageBean().getVillageMsg();
        }
        return "";

    }

    /**
     * 获取villiageId
     *
     * @return
     */
    public int getVillageId() {
        if (getCurrentVillageBean() == null) {
            return -1;
        }
        return getCurrentVillageBean().getVillageId();


    }

    /**
     * 获取roomUserId  用户房间关系表id
     *
     * @return
     */
    public int getRoomUserId() {
        if (getCurrentVillageBean() == null) {
            return -1;
        }
        return getCurrentVillageBean().getId();


    }

    /**
     * 获取roomID
     *
     * @return
     */
    public int getRoomId() {
        if (getCurrentVillageBean() == null) {
            return -1;
        }
        return getCurrentVillageBean().getRoomId();

    }

    /**
     * 本地保存小区的实体类
     *
     * @return
     */
    public void saveCurrentVillageBean(UserAndRoomBean.DataBean.GhsUserRoomDOBean dataBean) {

        Hawk.put(getCurrentVillageKey(), dataBean);
        AliPushManager.getInstance().bindAll(dataBean.getVillageId(), dataBean.getRoomId(),getUserId());

    }

    /**
     * 获取本地保存小区的实体类
     *
     * @return
     */
    public UserAndRoomBean.DataBean.GhsUserRoomDOBean getCurrentVillageBean() {
        return Hawk.get(getCurrentVillageKey());
    }

    /**
     * 当前选中小区里面的信息----------###################################################################------------以上
     */

    /**
     * 从登录接口返回的信息里面的数据----------###################################################################------------以下
     */
    /**
     * 获取userId
     *
     * @return
     */
    public int getUserId() {
        if (getLoginDataBean() != null) {
            return getLoginDataBean().getId();
        }
        return -1;
    }

    /**
     * 获取token
     *
     * @return
     */
    public String getUserToken() {
        return Hawk.get(HawkProperty.APP_TOKEN);

    }


    /**
     * 获取联系人姓名
     *
     * @return
     */
    public String getFullName() {
        if (getLoginDataBean() == null) {
            return "";
        }
        return getLoginDataBean().getFullName();
    }


    /**
     * 获取登录标识flog
     *
     * @return
     */
    public String getLoginFlog() {
        if (getLoginDataBean() != null) {
            return getLoginDataBean().getLoginFlag();
        }
        return "";

    }

    /**
     * 获取注册时间
     *
     * @return
     * @format 格式 “yyyy-MM”
     */
    public String getRegistTime(String format) {

        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String time = getLoginDataBean().getRegisterTime();
        if (!StrUtils.isStringValueOk(time)) {
            return null;
        }
        try {
            date = sdf.parse(time);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return sdf.format(date);
    }

    /**
     * 头像图片
     *
     * @return
     */
    public String getHeadImage() {
        if (getLoginDataBean() != null) {
            return getLoginDataBean().getHeadImage();
        }
        return "";
    }
    /**
     * 头像图片
     *
     * @return
     */
    public String getHeadBgColor() {
        if (getLoginDataBean() != null) {
            return getLoginDataBean().getHeadImageBackGroudColor();
        }
        return "";
    }

    /**
     * 昵称
     *
     * @return
     */
    public String getNickName() {
        if (getLoginDataBean() != null) {
            return getLoginDataBean().getNickName();
        }
        return "";
    }

    /**
     * getUnionId
     *
     * @return
     */
    public String getUnionId() {
        if (getLoginDataBean() != null) {
            return getLoginDataBean().getUnionId();
        }
        return "";
    }

    /**
     * getMobile
     *
     * @return
     */
    public String getMobile() {
        if (getLoginDataBean() != null) {
            return getLoginDataBean().getMobile();
        }
        return "";
    }
    /**
     * 从登录接口返回的信息里面的数据----------###################################################################------------以上
     */


}
