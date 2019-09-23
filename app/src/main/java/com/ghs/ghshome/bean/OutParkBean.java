package com.ghs.ghshome.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Author:wang_sir
 * Time:2018/11/21 15:26
 * Description:This is OutParkBean
 */
public class OutParkBean {
    /**
     * code : 1000
     * message : 成功
     * data : {"orderId":83,"carNum":"京AF0236","serviceDay":"2018-11-21","serviceTime":"14:56:43-15:26:10","serviceHoursAndMinute":"0小时29分","totalFee":0.02}
     */

    private int code;
    private String message;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Parcelable {


        /**
         * orderId : 83
         * carNum : 京AF0236
         * serviceDay : 2018-11-21
         * serviceTime : 14:56:43-15:26:10
         * serviceHoursAndMinute : 0小时29分
         * totalFee : 0.02
         */


        private int orderId;
        private String carNum;
        private String serviceDay;
        private String serviceTime;
        private String serviceHoursAndMinute;
        private double totalFee;


        public int getOrderId() {
            return orderId;
        }

        public void setOrderId(int orderId) {
            this.orderId = orderId;
        }

        public String getCarNum() {
            return carNum;
        }

        public void setCarNum(String carNum) {
            this.carNum = carNum;
        }

        public String getServiceDay() {
            return serviceDay;
        }

        public void setServiceDay(String serviceDay) {
            this.serviceDay = serviceDay;
        }

        public String getServiceTime() {
            return serviceTime;
        }

        public void setServiceTime(String serviceTime) {
            this.serviceTime = serviceTime;
        }

        public String getServiceHoursAndMinute() {
            return serviceHoursAndMinute;
        }

        public void setServiceHoursAndMinute(String serviceHoursAndMinute) {
            this.serviceHoursAndMinute = serviceHoursAndMinute;
        }

        public double getTotalFee() {
            return totalFee;
        }

        public void setTotalFee(double totalFee) {
            this.totalFee = totalFee;
        }


        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.orderId);
            dest.writeString(this.carNum);
            dest.writeString(this.serviceDay);
            dest.writeString(this.serviceTime);
            dest.writeString(this.serviceHoursAndMinute);
            dest.writeDouble(this.totalFee);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.orderId = in.readInt();
            this.carNum = in.readString();
            this.serviceDay = in.readString();
            this.serviceTime = in.readString();
            this.serviceHoursAndMinute = in.readString();
            this.totalFee = in.readDouble();
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
