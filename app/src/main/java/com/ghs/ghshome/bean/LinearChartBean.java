package com.ghs.ghshome.bean;

import java.util.List;

/**
 * Author:wang_sir
 * Time:2018/8/1 19:26
 * Description:This is LinearChartBean
 */
public class LinearChartBean {

    /**
     * code : 1000
     * message : 成功
     * data : {"payList":[{"month":"2018-04","fee":0,"num":0},{"month":"2018-05","fee":1,"num":1},{"month":"2018-06","fee":2,"num":1},{"month":"2018-07","fee":0,"num":0},{"month":"2018-08","fee":0,"num":0}],"noPayList":[{"month":"2018-04","fee":0,"num":0},{"month":"2018-05","fee":0,"num":0},{"month":"2018-06","fee":0,"num":0},{"month":"2018-07","fee":0.01,"num":1},{"month":"2018-08","fee":0.1,"num":1}],"prevPayFee":0,"noPayFee":0.11,"payFee":3,"maxFee":2,"minFee":0,"averageFee":0.6,"yearOnYearMsg":"电费缴纳同比上涨","yearOnYear":"0%","noPayRate":"3.54%","payRate":"96.46%"}
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
         * payList : [{"month":"2018-04","fee":0,"num":0},{"month":"2018-05","fee":1,"num":1},{"month":"2018-06","fee":2,"num":1},{"month":"2018-07","fee":0,"num":0},{"month":"2018-08","fee":0,"num":0}]
         * noPayList : [{"month":"2018-04","fee":0,"num":0},{"month":"2018-05","fee":0,"num":0},{"month":"2018-06","fee":0,"num":0},{"month":"2018-07","fee":0.01,"num":1},{"month":"2018-08","fee":0.1,"num":1}]
         * prevPayFee : 0
         * noPayFee : 0.11
         * payFee : 3
         * maxFee : 2
         * minFee : 0
         * averageFee : 0.6
         * yearOnYearMsg : 电费缴纳同比上涨
         * yearOnYear : 0%
         * noPayRate : 3.54%
         * payRate : 96.46%
         */

        private double prevPayFee;
        private double noPayFee;
        private double payFee;
        private double maxFee;
        private double minFee;
        private double averageFee;
        private String yearOnYearMsg;
        private String yearOnYear;
        private String noPayRate;
        private String payRate;
        private List<ListBean> payList;
        private List<ListBean> noPayList;


        public double getNoPayFee() {
            return noPayFee;
        }

        public void setNoPayFee(double noPayFee) {
            this.noPayFee = noPayFee;
        }

        public double getPrevPayFee() {
            return prevPayFee;
        }

        public void setPrevPayFee(double prevPayFee) {
            this.prevPayFee = prevPayFee;
        }

        public double getPayFee() {
            return payFee;
        }

        public void setPayFee(double payFee) {
            this.payFee = payFee;
        }

        public double getMaxFee() {
            return maxFee;
        }

        public void setMaxFee(double maxFee) {
            this.maxFee = maxFee;
        }

        public double getMinFee() {
            return minFee;
        }

        public void setMinFee(double minFee) {
            this.minFee = minFee;
        }

        public double getAverageFee() {
            return averageFee;
        }

        public void setAverageFee(double averageFee) {
            this.averageFee = averageFee;
        }

        public String getYearOnYearMsg() {
            return yearOnYearMsg;
        }

        public void setYearOnYearMsg(String yearOnYearMsg) {
            this.yearOnYearMsg = yearOnYearMsg;
        }

        public String getYearOnYear() {
            return yearOnYear;
        }

        public void setYearOnYear(String yearOnYear) {
            this.yearOnYear = yearOnYear;
        }

        public String getNoPayRate() {
            return noPayRate;
        }

        public void setNoPayRate(String noPayRate) {
            this.noPayRate = noPayRate;
        }

        public String getPayRate() {
            return payRate;
        }

        public void setPayRate(String payRate) {
            this.payRate = payRate;
        }

        public List<ListBean> getPayList() {
            return payList;
        }

        public void setPayList(List<ListBean> payList) {
            this.payList = payList;
        }

        public List<ListBean> getNoPayList() {
            return noPayList;
        }

        public void setNoPayList(List<ListBean> noPayList) {
            this.noPayList = noPayList;
        }

        public static class ListBean {
            /**
             * month : 2018-04
             * fee : 0
             * num : 0
             */

            private String month;
            private float fee;
            private int num;

            public String getMonth() {
                return month;
            }

            public void setMonth(String month) {
                this.month = month;
            }

            public float getFee() {
                return fee;
            }

            public void setFee(float fee) {
                this.fee = fee;
            }

            public int getNum() {
                return num;
            }

            public void setNum(int num) {
                this.num = num;
            }
        }

    }
}
