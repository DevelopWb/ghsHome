package com.ghs.ghshome.bean;

/**
 * Author:wang_sir
 * Time:2018/7/31 18:28
 * Description:This is SectorChartBean
 */
public class SectorChartBean {

    /**
     * code : 1000
     * message : 成功
     * data : {"allFee":0.09,"propertyFee":0,"waterFee":0.04,"ammeterFee":0.05,"noAllNum":5,"noPropertyNum":1,"noWaterNum":4,"noAmmeterNum":0}
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
         * allFee : 0.09
         * propertyFee : 0
         * waterFee : 0.04
         * ammeterFee : 0.05
         * noAllNum : 5
         * noPropertyNum : 1
         * noWaterNum : 4
         * noAmmeterNum : 0
         */

        private double allFee;
        private double propertyFee;
        private double waterFee;
        private double ammeterFee;
        private int noAllNum;
        private int noPropertyNum;
        private int noWaterNum;
        private int noAmmeterNum;

        public double getAllFee() {
            return allFee;
        }

        public void setAllFee(double allFee) {
            this.allFee = allFee;
        }

        public double getPropertyFee() {
            return propertyFee;
        }

        public void setPropertyFee(double propertyFee) {
            this.propertyFee = propertyFee;
        }

        public double getWaterFee() {
            return waterFee;
        }

        public void setWaterFee(double waterFee) {
            this.waterFee = waterFee;
        }

        public double getAmmeterFee() {
            return ammeterFee;
        }

        public void setAmmeterFee(double ammeterFee) {
            this.ammeterFee = ammeterFee;
        }

        public int getNoAllNum() {
            return noAllNum;
        }

        public void setNoAllNum(int noAllNum) {
            this.noAllNum = noAllNum;
        }

        public int getNoPropertyNum() {
            return noPropertyNum;
        }

        public void setNoPropertyNum(int noPropertyNum) {
            this.noPropertyNum = noPropertyNum;
        }

        public int getNoWaterNum() {
            return noWaterNum;
        }

        public void setNoWaterNum(int noWaterNum) {
            this.noWaterNum = noWaterNum;
        }

        public int getNoAmmeterNum() {
            return noAmmeterNum;
        }

        public void setNoAmmeterNum(int noAmmeterNum) {
            this.noAmmeterNum = noAmmeterNum;
        }
    }
}
