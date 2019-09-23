package com.ghs.ghshome.bean;

/**
 * Author:wang_sir
 * Time:2018/7/25 17:24
 * Description:This is IntegralLevelBean
 */
public class IntegralLevelBean {

    /**
     * code : 1000
     * message : 成功
     * data : {"currentIntegral":850,"currentName":"白银","nextIntegral":2187,"nextName":"黄金"}
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
         * currentIntegral : 850
         * currentName : 白银
         * nextIntegral : 2187
         * nextName : 黄金
         */

        private long currentIntegral;
        private String currentName;
        private long nextIntegral;
        private String nextName;

        public long getCurrentIntegral() {
            return currentIntegral;
        }

        public void setCurrentIntegral(long currentIntegral) {
            this.currentIntegral = currentIntegral;
        }

        public String getCurrentName() {
            return currentName;
        }

        public void setCurrentName(String currentName) {
            this.currentName = currentName;
        }

        public long getNextIntegral() {
            return nextIntegral;
        }

        public void setNextIntegral(long nextIntegral) {
            this.nextIntegral = nextIntegral;
        }

        public String getNextName() {
            return nextName;
        }

        public void setNextName(String nextName) {
            this.nextName = nextName;
        }
    }
}
