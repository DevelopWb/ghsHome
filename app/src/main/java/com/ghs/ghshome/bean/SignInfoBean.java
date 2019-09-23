package com.ghs.ghshome.bean;

/**
 * Author:wang_sir
 * Time:2018/7/26 14:27
 * Description:This is SignInfoBean 签到信息
 */
public class SignInfoBean {


    /**
     * code : 1000
     * message : 成功
     * data : {"days":1,"checkGiftBag":false}
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
         * days : 1
         * checkGiftBag : false
         */

        private int days;
        private boolean checkGiftBag;

        public int getDays() {
            return days;
        }

        public void setDays(int days) {
            this.days = days;
        }

        public boolean isCheckGiftBag() {
            return checkGiftBag;
        }

        public void setCheckGiftBag(boolean checkGiftBag) {
            this.checkGiftBag = checkGiftBag;
        }
    }
}
