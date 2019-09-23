package com.ghs.ghshome.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Author:wang_sir
 * Time:2018/11/23 13:44
 * Description:This is VisitorsBean
 */
public class VisitorsBean {
    /**
     * code : 1000
     * message : 成功
     * data : [{"id":285,"visitorUnionId":null,"visitorName":"测试","visitorMobile":"15311810032","carNum":"京ABCLLSS","type":1,"reason":null,"ghsUserId":86446,"ghsUserMobile":"17568086930","villageId":27,"roomId":86129,"roomFullName":"2号楼2单元102","visitDay":"2019-08-20 00:00:00","lockPassword":"834000","passworkStartTime":"2019-08-20 00:00:00","passworkEndTime":"2019-08-27 00:00:00","disabled":1,"source":null,"createTime":"2019-08-20 14:01:44","updateTime":"2019-08-20 14:01:44","state":2,"auditGhsUserId":null,"auditPmUserId":null,"auditTime":null,"qrCode":"200000000F0000044E5D5B38005D647280005EB3D806","villageName":"褐石公园小区（测试）"}]
     */

    private int code;
    private String message;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Parcelable {
        /**
         * id : 285
         * visitorUnionId : null
         * visitorName : 测试
         * visitorMobile : 15311810032
         * carNum : 京ABCLLSS
         * type : 1
         * reason : null
         * ghsUserId : 86446
         * ghsUserMobile : 17568086930
         * villageId : 27
         * roomId : 86129
         * roomFullName : 2号楼2单元102
         * visitDay : 2019-08-20 00:00:00
         * lockPassword : 834000
         * passworkStartTime : 2019-08-20 00:00:00
         * passworkEndTime : 2019-08-27 00:00:00
         * disabled : 1
         * source : null
         * createTime : 2019-08-20 14:01:44
         * updateTime : 2019-08-20 14:01:44
         * state : 2
         * auditGhsUserId : null
         * auditPmUserId : null
         * auditTime : null
         * qrCode : 200000000F0000044E5D5B38005D647280005EB3D806
         * villageName : 褐石公园小区（测试）
         */

        private int id;
        private String visitorUnionId;
        private String visitorName;
        private String visitorMobile;
        private String carNum;
        private int type;
        private String reason;
        private int ghsUserId;
        private String ghsUserMobile;
        private int villageId;
        private int roomId;
        private String roomFullName;
        private String visitDay;
        private String lockPassword;
        private String passworkStartTime;
        private String passworkEndTime;
        private int disabled;
        private int source;
        private String createTime;
        private String updateTime;
        private int state;
        private int auditGhsUserId;
        private int auditPmUserId;
        private String auditTime;
        private String qrCode;
        private String villageName;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getVisitorUnionId() {
            return visitorUnionId == null ? "" : visitorUnionId;
        }

        public void setVisitorUnionId(String visitorUnionId) {
            this.visitorUnionId = visitorUnionId;
        }

        public String getVisitorName() {
            return visitorName == null ? "" : visitorName;
        }

        public void setVisitorName(String visitorName) {
            this.visitorName = visitorName;
        }

        public String getVisitorMobile() {
            return visitorMobile == null ? "" : visitorMobile;
        }

        public void setVisitorMobile(String visitorMobile) {
            this.visitorMobile = visitorMobile;
        }

        public String getCarNum() {
            return carNum == null ? "" : carNum;
        }

        public void setCarNum(String carNum) {
            this.carNum = carNum;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getReason() {
            return reason == null ? "" : reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

        public int getGhsUserId() {
            return ghsUserId;
        }

        public void setGhsUserId(int ghsUserId) {
            this.ghsUserId = ghsUserId;
        }

        public String getGhsUserMobile() {
            return ghsUserMobile == null ? "" : ghsUserMobile;
        }

        public void setGhsUserMobile(String ghsUserMobile) {
            this.ghsUserMobile = ghsUserMobile;
        }

        public int getVillageId() {
            return villageId;
        }

        public void setVillageId(int villageId) {
            this.villageId = villageId;
        }

        public int getRoomId() {
            return roomId;
        }

        public void setRoomId(int roomId) {
            this.roomId = roomId;
        }

        public String getRoomFullName() {
            return roomFullName == null ? "" : roomFullName;
        }

        public void setRoomFullName(String roomFullName) {
            this.roomFullName = roomFullName;
        }

        public String getVisitDay() {
            return visitDay == null ? "" : visitDay;
        }

        public void setVisitDay(String visitDay) {
            this.visitDay = visitDay;
        }

        public String getLockPassword() {
            return lockPassword == null ? "" : lockPassword;
        }

        public void setLockPassword(String lockPassword) {
            this.lockPassword = lockPassword;
        }

        public String getPassworkStartTime() {
            return passworkStartTime == null ? "" : passworkStartTime;
        }

        public void setPassworkStartTime(String passworkStartTime) {
            this.passworkStartTime = passworkStartTime;
        }

        public String getPassworkEndTime() {
            return passworkEndTime == null ? "" : passworkEndTime;
        }

        public void setPassworkEndTime(String passworkEndTime) {
            this.passworkEndTime = passworkEndTime;
        }

        public int getDisabled() {
            return disabled;
        }

        public void setDisabled(int disabled) {
            this.disabled = disabled;
        }

        public int getSource() {
            return source;
        }

        public void setSource(int source) {
            this.source = source;
        }

        public String getCreateTime() {
            return createTime == null ? "" : createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getUpdateTime() {
            return updateTime == null ? "" : updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public int getAuditGhsUserId() {
            return auditGhsUserId;
        }

        public void setAuditGhsUserId(int auditGhsUserId) {
            this.auditGhsUserId = auditGhsUserId;
        }

        public int getAuditPmUserId() {
            return auditPmUserId;
        }

        public void setAuditPmUserId(int auditPmUserId) {
            this.auditPmUserId = auditPmUserId;
        }

        public String getAuditTime() {
            return auditTime == null ? "" : auditTime;
        }

        public void setAuditTime(String auditTime) {
            this.auditTime = auditTime;
        }

        public String getQrCode() {
            return qrCode == null ? "" : qrCode;
        }

        public void setQrCode(String qrCode) {
            this.qrCode = qrCode;
        }

        public String getVillageName() {
            return villageName == null ? "" : villageName;
        }

        public void setVillageName(String villageName) {
            this.villageName = villageName;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.id);
            dest.writeString(this.visitorUnionId);
            dest.writeString(this.visitorName);
            dest.writeString(this.visitorMobile);
            dest.writeString(this.carNum);
            dest.writeInt(this.type);
            dest.writeString(this.reason);
            dest.writeInt(this.ghsUserId);
            dest.writeString(this.ghsUserMobile);
            dest.writeInt(this.villageId);
            dest.writeInt(this.roomId);
            dest.writeString(this.roomFullName);
            dest.writeString(this.visitDay);
            dest.writeString(this.lockPassword);
            dest.writeString(this.passworkStartTime);
            dest.writeString(this.passworkEndTime);
            dest.writeInt(this.disabled);
            dest.writeInt(this.source);
            dest.writeString(this.createTime);
            dest.writeString(this.updateTime);
            dest.writeInt(this.state);
            dest.writeInt(this.auditGhsUserId);
            dest.writeInt(this.auditPmUserId);
            dest.writeString(this.auditTime);
            dest.writeString(this.qrCode);
            dest.writeString(this.villageName);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.id = in.readInt();
            this.visitorUnionId = in.readString();
            this.visitorName = in.readString();
            this.visitorMobile = in.readString();
            this.carNum = in.readString();
            this.type = in.readInt();
            this.reason = in.readString();
            this.ghsUserId = in.readInt();
            this.ghsUserMobile = in.readString();
            this.villageId = in.readInt();
            this.roomId = in.readInt();
            this.roomFullName = in.readString();
            this.visitDay = in.readString();
            this.lockPassword = in.readString();
            this.passworkStartTime = in.readString();
            this.passworkEndTime = in.readString();
            this.disabled = in.readInt();
            this.source = in.readInt();
            this.createTime = in.readString();
            this.updateTime = in.readString();
            this.state = in.readInt();
            this.auditGhsUserId = in.readInt();
            this.auditPmUserId = in.readInt();
            this.auditTime = in.readString();
            this.qrCode = in.readString();
            this.villageName = in.readString();
        }

        public static final Parcelable.Creator<DataBean> CREATOR = new Parcelable.Creator<DataBean>() {
            @Override
            public DataBean createFromParcel(Parcel source) {
                return new DataBean(source);
            }

            @Override
            public DataBean[] newArray(int size) {
                return new DataBean[size];
            }
        };
    }
}
