package com.ghs.ghshome.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Author:wang_sir
 * Time:2018/11/22 13:44
 * Description:This is CarPayRecord
 */
public class CarPayRecord {

    /**
     * code : 1000
     * message : 成功
     * data : [{"month":"本月","carparkTempOrderDOList":[{"id":1043,"orderNum":"2018120615231230817526_69183","channelOrderNum":"4200000213201812063036421976","gmtOrderNum":"181206152311970010523000000276","payState":4,"payCreateTime":"2018-12-06 15:23:29","paySuccessTime":"2019-08-08 15:23:37","payType":null,"carId":85,"carNum":"京AFF236","serviceStartTime":"2018-12-06 15:20:50","serviceEndTime":"2018-12-06 15:23:11","serviceFeeTime":141,"serviceFee":0.01,"payMoney":0.01,"useSeedNum":0,"seedDiscountMoney":0,"exitChannelId":null,"exitChannelName":"南门","openId":null,"userType":1,"userId":86446,"userName":null,"mobile":null,"villageId":27,"createTime":"2018-12-06 15:23:12","propertyId":30,"propertyName":"长春大禹物业公司","villageName":"褐石公园小区（测试）","serviceFeeTimeFormat":"2分"},{"id":923,"orderNum":"2018120416405605426960","channelOrderNum":null,"gmtOrderNum":"181204164055690010523000000180","payState":4,"payCreateTime":null,"paySuccessTime":"2019-08-08 15:23:37","payType":null,"carId":83,"carNum":"京AD12345","serviceStartTime":"2018-12-04 15:40:14","serviceEndTime":"2018-12-04 16:40:55","serviceFeeTime":3641,"serviceFee":0.03,"payMoney":0.03,"useSeedNum":100,"seedDiscountMoney":10,"exitChannelId":null,"exitChannelName":"南门","openId":null,"userType":1,"userId":86446,"userName":null,"mobile":null,"villageId":27,"createTime":"2018-12-04 16:40:56","propertyId":30,"propertyName":"长春大禹物业公司","villageName":"褐石公园小区（测试）","serviceFeeTimeFormat":"1小时"}]},{"month":"07月","carparkTempOrderDOList":[{"id":912,"orderNum":"2018120416352857937757","channelOrderNum":null,"gmtOrderNum":"181204163528580010523000000180","payState":4,"payCreateTime":null,"paySuccessTime":"2019-07-07 15:23:37","payType":null,"carId":80,"carNum":"京AD12345","serviceStartTime":"2018-12-04 15:40:14","serviceEndTime":"2018-12-04 16:35:28","serviceFeeTime":3314,"serviceFee":0.03,"payMoney":0.03,"useSeedNum":100,"seedDiscountMoney":10,"exitChannelId":null,"exitChannelName":"东门","openId":null,"userType":1,"userId":86446,"userName":null,"mobile":null,"villageId":27,"createTime":"2018-12-04 16:35:28","propertyId":30,"propertyName":"长春大禹物业公司","villageName":"褐石公园小区（测试）","serviceFeeTimeFormat":"55分"},{"id":913,"orderNum":"2018120416360525917720","channelOrderNum":null,"gmtOrderNum":"181204163605590010523000000180","payState":4,"payCreateTime":null,"paySuccessTime":"2019-07-07 15:23:37","payType":null,"carId":80,"carNum":"京AD12345","serviceStartTime":"2018-12-04 15:40:14","serviceEndTime":"2018-12-04 16:36:05","serviceFeeTime":3351,"serviceFee":0.03,"payMoney":0.03,"useSeedNum":100,"seedDiscountMoney":10,"exitChannelId":null,"exitChannelName":"南门","openId":null,"userType":1,"userId":86446,"userName":null,"mobile":null,"villageId":27,"createTime":"2018-12-04 16:36:05","propertyId":30,"propertyName":"长春大禹物业公司","villageName":"褐石公园小区（测试）","serviceFeeTimeFormat":"55分"}]},{"month":"06月","carparkTempOrderDOList":[{"id":908,"orderNum":"2018120416344434860614","channelOrderNum":null,"gmtOrderNum":"181204163444540010523000000180","payState":4,"payCreateTime":null,"paySuccessTime":"2019-06-06 15:23:37","payType":null,"carId":80,"carNum":"京AD12345","serviceStartTime":"2018-12-04 15:40:14","serviceEndTime":"2018-12-04 16:34:44","serviceFeeTime":3270,"serviceFee":0.03,"payMoney":0.03,"useSeedNum":100,"seedDiscountMoney":10,"exitChannelId":null,"exitChannelName":"东门","openId":null,"userType":1,"userId":86446,"userName":null,"mobile":null,"villageId":27,"createTime":"2018-12-04 16:34:44","propertyId":30,"propertyName":"长春大禹物业公司","villageName":"褐石公园小区（测试）","serviceFeeTimeFormat":"54分"},{"id":911,"orderNum":"2018120416350169121712","channelOrderNum":null,"gmtOrderNum":"181204163501570010523000000180","payState":4,"payCreateTime":null,"paySuccessTime":"2019-06-06 15:23:37","payType":null,"carId":80,"carNum":"京AD12345","serviceStartTime":"2018-12-04 15:40:14","serviceEndTime":"2018-12-04 16:35:01","serviceFeeTime":3287,"serviceFee":0.03,"payMoney":0.03,"useSeedNum":100,"seedDiscountMoney":10,"exitChannelId":null,"exitChannelName":"东门","openId":null,"userType":1,"userId":86446,"userName":null,"mobile":null,"villageId":27,"createTime":"2018-12-04 16:35:01","propertyId":30,"propertyName":"长春大禹物业公司","villageName":"褐石公园小区（测试）","serviceFeeTimeFormat":"54分"}]}]
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

    public static class DataBean {
        /**
         * month : 本月
         * carparkTempOrderDOList : [{"id":1043,"orderNum":"2018120615231230817526_69183","channelOrderNum":"4200000213201812063036421976","gmtOrderNum":"181206152311970010523000000276","payState":4,"payCreateTime":"2018-12-06 15:23:29","paySuccessTime":"2019-08-08 15:23:37","payType":null,"carId":85,"carNum":"京AFF236","serviceStartTime":"2018-12-06 15:20:50","serviceEndTime":"2018-12-06 15:23:11","serviceFeeTime":141,"serviceFee":0.01,"payMoney":0.01,"useSeedNum":0,"seedDiscountMoney":0,"exitChannelId":null,"exitChannelName":"南门","openId":null,"userType":1,"userId":86446,"userName":null,"mobile":null,"villageId":27,"createTime":"2018-12-06 15:23:12","propertyId":30,"propertyName":"长春大禹物业公司","villageName":"褐石公园小区（测试）","serviceFeeTimeFormat":"2分"},{"id":923,"orderNum":"2018120416405605426960","channelOrderNum":null,"gmtOrderNum":"181204164055690010523000000180","payState":4,"payCreateTime":null,"paySuccessTime":"2019-08-08 15:23:37","payType":null,"carId":83,"carNum":"京AD12345","serviceStartTime":"2018-12-04 15:40:14","serviceEndTime":"2018-12-04 16:40:55","serviceFeeTime":3641,"serviceFee":0.03,"payMoney":0.03,"useSeedNum":100,"seedDiscountMoney":10,"exitChannelId":null,"exitChannelName":"南门","openId":null,"userType":1,"userId":86446,"userName":null,"mobile":null,"villageId":27,"createTime":"2018-12-04 16:40:56","propertyId":30,"propertyName":"长春大禹物业公司","villageName":"褐石公园小区（测试）","serviceFeeTimeFormat":"1小时"}]
         */

        private String month;
        private List<CarparkTempOrderDOListBean> carparkTempOrderDOList;

        public String getMonth() {
            return month;
        }

        public void setMonth(String month) {
            this.month = month;
        }

        public List<CarparkTempOrderDOListBean> getCarparkTempOrderDOList() {
            return carparkTempOrderDOList;
        }

        public void setCarparkTempOrderDOList(List<CarparkTempOrderDOListBean> carparkTempOrderDOList) {
            this.carparkTempOrderDOList = carparkTempOrderDOList;
        }

        public static class CarparkTempOrderDOListBean implements Parcelable {
            /**
             * id : 1043
             * orderNum : 2018120615231230817526_69183
             * channelOrderNum : 4200000213201812063036421976
             * gmtOrderNum : 181206152311970010523000000276
             * payState : 4
             * payCreateTime : 2018-12-06 15:23:29
             * paySuccessTime : 2019-08-08 15:23:37
             * payType : null
             * carId : 85
             * carNum : 京AFF236
             * serviceStartTime : 2018-12-06 15:20:50
             * serviceEndTime : 2018-12-06 15:23:11
             * serviceFeeTime : 141
             * serviceFee : 0.01
             * payMoney : 0.01
             * useSeedNum : 0
             * seedDiscountMoney : 0.0
             * exitChannelId : null
             * exitChannelName : 南门
             * openId : null
             * userType : 1
             * userId : 86446
             * userName : null
             * mobile : null
             * villageId : 27
             * createTime : 2018-12-06 15:23:12
             * propertyId : 30
             * propertyName : 长春大禹物业公司
             * villageName : 褐石公园小区（测试）
             * serviceFeeTimeFormat : 2分
             */

            private int id;
            private String orderNum;
            private String channelOrderNum;
            private String gmtOrderNum;
            private int payState;
            private String payCreateTime;
            private String paySuccessTime;
            private int payType;
            private int carId;
            private String carNum;
            private String serviceStartTime;
            private String serviceEndTime;
            private int serviceFeeTime;
            private double serviceFee;
            private double payMoney;
            private int useSeedNum;
            private double seedDiscountMoney;
            private int exitChannelId;
            private String exitChannelName;
            private String openId;
            private int userType;
            private int userId;
            private String userName;
            private String mobile;
            private int villageId;
            private String createTime;
            private int propertyId;
            private String propertyName;
            private String villageName;
            private String serviceFeeTimeFormat;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getOrderNum() {
                return orderNum == null ? "" : orderNum;
            }

            public void setOrderNum(String orderNum) {
                this.orderNum = orderNum;
            }

            public String getChannelOrderNum() {
                return channelOrderNum == null ? "" : channelOrderNum;
            }

            public void setChannelOrderNum(String channelOrderNum) {
                this.channelOrderNum = channelOrderNum;
            }

            public String getGmtOrderNum() {
                return gmtOrderNum == null ? "" : gmtOrderNum;
            }

            public void setGmtOrderNum(String gmtOrderNum) {
                this.gmtOrderNum = gmtOrderNum;
            }

            public int getPayState() {
                return payState;
            }

            public void setPayState(int payState) {
                this.payState = payState;
            }

            public String getPayCreateTime() {
                return payCreateTime == null ? "" : payCreateTime;
            }

            public void setPayCreateTime(String payCreateTime) {
                this.payCreateTime = payCreateTime;
            }

            public String getPaySuccessTime() {
                return paySuccessTime == null ? "" : paySuccessTime;
            }

            public void setPaySuccessTime(String paySuccessTime) {
                this.paySuccessTime = paySuccessTime;
            }

            public int getPayType() {
                return payType;
            }

            public void setPayType(int payType) {
                this.payType = payType;
            }

            public int getCarId() {
                return carId;
            }

            public void setCarId(int carId) {
                this.carId = carId;
            }

            public String getCarNum() {
                return carNum == null ? "" : carNum;
            }

            public void setCarNum(String carNum) {
                this.carNum = carNum;
            }

            public String getServiceStartTime() {
                return serviceStartTime == null ? "" : serviceStartTime;
            }

            public void setServiceStartTime(String serviceStartTime) {
                this.serviceStartTime = serviceStartTime;
            }

            public String getServiceEndTime() {
                return serviceEndTime == null ? "" : serviceEndTime;
            }

            public void setServiceEndTime(String serviceEndTime) {
                this.serviceEndTime = serviceEndTime;
            }

            public int getServiceFeeTime() {
                return serviceFeeTime;
            }

            public void setServiceFeeTime(int serviceFeeTime) {
                this.serviceFeeTime = serviceFeeTime;
            }

            public double getServiceFee() {
                return serviceFee;
            }

            public void setServiceFee(double serviceFee) {
                this.serviceFee = serviceFee;
            }

            public double getPayMoney() {
                return payMoney;
            }

            public void setPayMoney(double payMoney) {
                this.payMoney = payMoney;
            }

            public int getUseSeedNum() {
                return useSeedNum;
            }

            public void setUseSeedNum(int useSeedNum) {
                this.useSeedNum = useSeedNum;
            }

            public double getSeedDiscountMoney() {
                return seedDiscountMoney;
            }

            public void setSeedDiscountMoney(double seedDiscountMoney) {
                this.seedDiscountMoney = seedDiscountMoney;
            }

            public int getExitChannelId() {
                return exitChannelId;
            }

            public void setExitChannelId(int exitChannelId) {
                this.exitChannelId = exitChannelId;
            }

            public String getExitChannelName() {
                return exitChannelName == null ? "" : exitChannelName;
            }

            public void setExitChannelName(String exitChannelName) {
                this.exitChannelName = exitChannelName;
            }

            public String getOpenId() {
                return openId == null ? "" : openId;
            }

            public void setOpenId(String openId) {
                this.openId = openId;
            }

            public int getUserType() {
                return userType;
            }

            public void setUserType(int userType) {
                this.userType = userType;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
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

            public int getVillageId() {
                return villageId;
            }

            public void setVillageId(int villageId) {
                this.villageId = villageId;
            }

            public String getCreateTime() {
                return createTime == null ? "" : createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public int getPropertyId() {
                return propertyId;
            }

            public void setPropertyId(int propertyId) {
                this.propertyId = propertyId;
            }

            public String getPropertyName() {
                return propertyName == null ? "" : propertyName;
            }

            public void setPropertyName(String propertyName) {
                this.propertyName = propertyName;
            }

            public String getVillageName() {
                return villageName == null ? "" : villageName;
            }

            public void setVillageName(String villageName) {
                this.villageName = villageName;
            }

            public String getServiceFeeTimeFormat() {
                return serviceFeeTimeFormat == null ? "" : serviceFeeTimeFormat;
            }

            public void setServiceFeeTimeFormat(String serviceFeeTimeFormat) {
                this.serviceFeeTimeFormat = serviceFeeTimeFormat;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeInt(this.id);
                dest.writeString(this.orderNum);
                dest.writeString(this.channelOrderNum);
                dest.writeString(this.gmtOrderNum);
                dest.writeInt(this.payState);
                dest.writeString(this.payCreateTime);
                dest.writeString(this.paySuccessTime);
                dest.writeInt(this.payType);
                dest.writeInt(this.carId);
                dest.writeString(this.carNum);
                dest.writeString(this.serviceStartTime);
                dest.writeString(this.serviceEndTime);
                dest.writeInt(this.serviceFeeTime);
                dest.writeDouble(this.serviceFee);
                dest.writeDouble(this.payMoney);
                dest.writeInt(this.useSeedNum);
                dest.writeDouble(this.seedDiscountMoney);
                dest.writeInt(this.exitChannelId);
                dest.writeString(this.exitChannelName);
                dest.writeString(this.openId);
                dest.writeInt(this.userType);
                dest.writeInt(this.userId);
                dest.writeString(this.userName);
                dest.writeString(this.mobile);
                dest.writeInt(this.villageId);
                dest.writeString(this.createTime);
                dest.writeInt(this.propertyId);
                dest.writeString(this.propertyName);
                dest.writeString(this.villageName);
                dest.writeString(this.serviceFeeTimeFormat);
            }

            public CarparkTempOrderDOListBean() {
            }

            protected CarparkTempOrderDOListBean(Parcel in) {
                this.id = in.readInt();
                this.orderNum = in.readString();
                this.channelOrderNum = in.readString();
                this.gmtOrderNum = in.readString();
                this.payState = in.readInt();
                this.payCreateTime = in.readString();
                this.paySuccessTime = in.readString();
                this.payType = in.readInt();
                this.carId = in.readInt();
                this.carNum = in.readString();
                this.serviceStartTime = in.readString();
                this.serviceEndTime = in.readString();
                this.serviceFeeTime = in.readInt();
                this.serviceFee = in.readDouble();
                this.payMoney = in.readDouble();
                this.useSeedNum = in.readInt();
                this.seedDiscountMoney = in.readDouble();
                this.exitChannelId = in.readInt();
                this.exitChannelName = in.readString();
                this.openId = in.readString();
                this.userType = in.readInt();
                this.userId = in.readInt();
                this.userName = in.readString();
                this.mobile = in.readString();
                this.villageId = in.readInt();
                this.createTime = in.readString();
                this.propertyId = in.readInt();
                this.propertyName = in.readString();
                this.villageName = in.readString();
                this.serviceFeeTimeFormat = in.readString();
            }

            public static final Parcelable.Creator<CarparkTempOrderDOListBean> CREATOR = new Parcelable.Creator<CarparkTempOrderDOListBean>() {
                @Override
                public CarparkTempOrderDOListBean createFromParcel(Parcel source) {
                    return new CarparkTempOrderDOListBean(source);
                }

                @Override
                public CarparkTempOrderDOListBean[] newArray(int size) {
                    return new CarparkTempOrderDOListBean[size];
                }
            };
        }
    }
}
