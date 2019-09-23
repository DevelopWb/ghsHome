package com.ghs.ghshome.bean;

/**
 * Author:wang_sir
 * Time:2018/7/25 17:58
 * Description:This is ReceiveIntegralBean
 */
public class ReceiveIntegralBean {

    /**
     * code : 0
     * data : {"addIntegral":0,"integral":0}
     * message :
     */

    private int code;
    private DataBean data;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class DataBean {
        /**
         * addIntegral : 0
         * integral : 0
         */

        private int addIntegral;
        private int integral;

        public int getAddIntegral() {
            return addIntegral;
        }

        public void setAddIntegral(int addIntegral) {
            this.addIntegral = addIntegral;
        }

        public int getIntegral() {
            return integral;
        }

        public void setIntegral(int integral) {
            this.integral = integral;
        }
    }
}
