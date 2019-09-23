package com.ghs.ghshome.bean;

public class PushBean {


    /**
     * _ALIYUN_NOTIFICATION_ID_ : 25119
     * noticeType : notice
     * _ALIYUN_NOTIFICATION_PRIORITY_ : 1
     */

    private String _ALIYUN_NOTIFICATION_ID_;
    private String noticeType;
    //1.认证通过、2.认证不通过、3.待认证, -1.删除
    private String userState;
    private String roomId;
    private String _ALIYUN_NOTIFICATION_PRIORITY_;

    public String get_ALIYUN_NOTIFICATION_ID_() {
        return _ALIYUN_NOTIFICATION_ID_;
    }

    public void set_ALIYUN_NOTIFICATION_ID_(String _ALIYUN_NOTIFICATION_ID_) {
        this._ALIYUN_NOTIFICATION_ID_ = _ALIYUN_NOTIFICATION_ID_;
    }

    public String getUserState() {
        return userState == null ? "" : userState;
    }

    public void setUserState(String userState) {
        this.userState = userState;
    }

    public String getRoomId() {
        return roomId == null ? "" : roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getNoticeType() {
        return noticeType;
    }

    public void setNoticeType(String noticeType) {
        this.noticeType = noticeType;
    }

    public String get_ALIYUN_NOTIFICATION_PRIORITY_() {
        return _ALIYUN_NOTIFICATION_PRIORITY_;
    }

    public void set_ALIYUN_NOTIFICATION_PRIORITY_(String _ALIYUN_NOTIFICATION_PRIORITY_) {
        this._ALIYUN_NOTIFICATION_PRIORITY_ = _ALIYUN_NOTIFICATION_PRIORITY_;
    }
}
