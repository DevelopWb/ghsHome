package com.ghs.ghshome.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Author:wang_sir
 * Time:2018/11/21 11:08
 * Description:This is BoundCarsBean
 */
public class BoundCarsBean {
    /**
     * code : 1000
     * message : 成功
     * data : [{"id":203,"carNum":"沪JKTRSTT","ghsUserId":86446,"villageId":27,"roomId":86129,"source":2,"remark":null,"createTime":"2019-08-20 19:00:56","createUserId":null,"updateTime":"2019-08-20 19:00:56","updateUserId":null,"qrCode":"200000000F000004565D5C4367E0F058670047CEE51C","userName":"王斌","mobile":"17568086930","roomFullName":null,"type":null}]
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
         * id : 203
         * carNum : 沪JKTRSTT
         * ghsUserId : 86446
         * villageId : 27
         * roomId : 86129
         * source : 2
         * remark : null
         * createTime : 2019-08-20 19:00:56
         * createUserId : null
         * updateTime : 2019-08-20 19:00:56
         * updateUserId : null
         * qrCode : 200000000F000004565D5C4367E0F058670047CEE51C
         * userName : 王斌
         * mobile : 17568086930
         * roomFullName : null
         * type : null
         */

        private int id;
        private String carNum;
        private int ghsUserId;
        private int villageId;
        private int roomId;
        private int source;
        private String remark;
        private String createTime;
        private int createUserId;
        private String updateTime;
        private int updateUserId;
        private String qrCode;
        private String userName;
        private String mobile;
        private String roomFullName;
        private int type;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCarNum() {
            return carNum == null ? "" : carNum;
        }

        public void setCarNum(String carNum) {
            this.carNum = carNum;
        }

        public int getGhsUserId() {
            return ghsUserId;
        }

        public void setGhsUserId(int ghsUserId) {
            this.ghsUserId = ghsUserId;
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

        public int getSource() {
            return source;
        }

        public void setSource(int source) {
            this.source = source;
        }

        public String getRemark() {
            return remark == null ? "" : remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getCreateTime() {
            return createTime == null ? "" : createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public int getCreateUserId() {
            return createUserId;
        }

        public void setCreateUserId(int createUserId) {
            this.createUserId = createUserId;
        }

        public String getUpdateTime() {
            return updateTime == null ? "" : updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public int getUpdateUserId() {
            return updateUserId;
        }

        public void setUpdateUserId(int updateUserId) {
            this.updateUserId = updateUserId;
        }

        public String getQrCode() {
            return qrCode == null ? "" : qrCode;
        }

        public void setQrCode(String qrCode) {
            this.qrCode = qrCode;
        }

        public String getUserName() {
            return userName == null ? "" : userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getMobile() {
            return mobile == null ? "" : mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getRoomFullName() {
            return roomFullName == null ? "" : roomFullName;
        }

        public void setRoomFullName(String roomFullName) {
            this.roomFullName = roomFullName;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.id);
            dest.writeString(this.carNum);
            dest.writeInt(this.ghsUserId);
            dest.writeInt(this.villageId);
            dest.writeInt(this.roomId);
            dest.writeInt(this.source);
            dest.writeString(this.remark);
            dest.writeString(this.createTime);
            dest.writeInt(this.createUserId);
            dest.writeString(this.updateTime);
            dest.writeInt(this.updateUserId);
            dest.writeString(this.qrCode);
            dest.writeString(this.userName);
            dest.writeString(this.mobile);
            dest.writeString(this.roomFullName);
            dest.writeInt(this.type);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.id = in.readInt();
            this.carNum = in.readString();
            this.ghsUserId = in.readInt();
            this.villageId = in.readInt();
            this.roomId = in.readInt();
            this.source = in.readInt();
            this.remark = in.readString();
            this.createTime = in.readString();
            this.createUserId = in.readInt();
            this.updateTime = in.readString();
            this.updateUserId = in.readInt();
            this.qrCode = in.readString();
            this.userName = in.readString();
            this.mobile = in.readString();
            this.roomFullName = in.readString();
            this.type = in.readInt();
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
