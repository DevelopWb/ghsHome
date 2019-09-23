package com.ghs.ghshome.bean;

/**
 * Author:wang_sir
 * Time:2018/11/20 15:51
 * Description:This is CarParkRule
 */
public class CarParkRule {
    /**
     * code : 1000
     * message : 成功
     * data : {"freeTime":"","firstTime":"","firstPrice":null,"intervalTime":"01:00","intervalPrice":0.01,"carType":1,"daysLimit":0,"ruleDescription":"小车:2元/小时 大车:3元/小时","tempCarportNumTotal":200,"tempCarportNumRemain":190}
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
         * freeTime :
         * firstTime :
         * firstPrice : null
         * intervalTime : 01:00
         * intervalPrice : 0.01
         * carType : 1
         * daysLimit : 0.0
         * ruleDescription : 小车:2元/小时 大车:3元/小时
         * tempCarportNumTotal : 200
         * tempCarportNumRemain : 190
         */

        private String freeTime;
        private String firstTime;
        private Object firstPrice;
        private String intervalTime;
        private double intervalPrice;
        private int carType;
        private double daysLimit;
        private String ruleDescription;
        private int tempCarportNumTotal;
        private int tempCarportNumRemain;

        public String getFreeTime() {
            return freeTime;
        }

        public void setFreeTime(String freeTime) {
            this.freeTime = freeTime;
        }

        public String getFirstTime() {
            return firstTime;
        }

        public void setFirstTime(String firstTime) {
            this.firstTime = firstTime;
        }

        public Object getFirstPrice() {
            return firstPrice;
        }

        public void setFirstPrice(Object firstPrice) {
            this.firstPrice = firstPrice;
        }

        public String getIntervalTime() {
            return intervalTime;
        }

        public void setIntervalTime(String intervalTime) {
            this.intervalTime = intervalTime;
        }

        public double getIntervalPrice() {
            return intervalPrice;
        }

        public void setIntervalPrice(double intervalPrice) {
            this.intervalPrice = intervalPrice;
        }

        public int getCarType() {
            return carType;
        }

        public void setCarType(int carType) {
            this.carType = carType;
        }

        public double getDaysLimit() {
            return daysLimit;
        }

        public void setDaysLimit(double daysLimit) {
            this.daysLimit = daysLimit;
        }

        public String getRuleDescription() {
            return ruleDescription;
        }

        public void setRuleDescription(String ruleDescription) {
            this.ruleDescription = ruleDescription;
        }

        public int getTempCarportNumTotal() {
            return tempCarportNumTotal;
        }

        public void setTempCarportNumTotal(int tempCarportNumTotal) {
            this.tempCarportNumTotal = tempCarportNumTotal;
        }

        public int getTempCarportNumRemain() {
            return tempCarportNumRemain;
        }

        public void setTempCarportNumRemain(int tempCarportNumRemain) {
            this.tempCarportNumRemain = tempCarportNumRemain;
        }
    }
}
