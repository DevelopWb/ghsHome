package com.ghs.ghshome.bean;

/**
 * Author:wang_sir
 * Time:2019/5/24 14:29
 * Description:This is PropertyTelBean
 */
public class PropertyTelBean {
    /**
     * code : 1000
     * message : 成功
     * data : {"mobile":"400-8902443","serviceTime":"每天9:00-18:00"}
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

    public static class DataBean {
        /**
         * mobile : 400-8902443
         * serviceTime : 每天9:00-18:00
         */

        private String mobile;
        private String serviceTime;

        public String getMobile() {
            return mobile == null ? "" : mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getServiceTime() {
            return serviceTime == null ? "" : serviceTime;
        }

        public void setServiceTime(String serviceTime) {
            this.serviceTime = serviceTime;
        }
    }
}
