package com.ghs.ghshome.bean;

/**
 * Author:wang_sir
 * Time:2018/7/30 18:59
 * Description:This is WXPayResultBean
 */
public class WXPayResultBean {

    /**
     * code : 1000
     * message : 成功
     * data : {"payState":1,"appid":"wxe11e88450f219a70","partnerid":"1504787951","prepayid":"wx29154338231875f16bc457022692980085","packAge":"Sign=WXPay","noncestr":"e2ffbb9dadf74bf6834e5016b0fef0e8","timestamp":"1543477418","sign":"E88103C7527E7D044928B3FB54450ABE","out_trade_no":"2018112915433376504891_75410"}
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
         * payState : 1
         * appid : wxe11e88450f219a70
         * partnerid : 1504787951
         * prepayid : wx29154338231875f16bc457022692980085
         * packAge : Sign=WXPay
         * noncestr : e2ffbb9dadf74bf6834e5016b0fef0e8
         * timestamp : 1543477418
         * sign : E88103C7527E7D044928B3FB54450ABE
         * out_trade_no : 2018112915433376504891_75410
         */

        private int payState;
        private String appid;
        private String partnerid;
        private String prepayid;
        private String packAge;
        private String noncestr;
        private String timestamp;
        private String sign;
        private String out_trade_no;

        public int getPayState() {
            return payState;
        }

        public void setPayState(int payState) {
            this.payState = payState;
        }

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public String getPartnerid() {
            return partnerid;
        }

        public void setPartnerid(String partnerid) {
            this.partnerid = partnerid;
        }

        public String getPrepayid() {
            return prepayid;
        }

        public void setPrepayid(String prepayid) {
            this.prepayid = prepayid;
        }

        public String getPackAge() {
            return packAge;
        }

        public void setPackAge(String packAge) {
            this.packAge = packAge;
        }

        public String getNoncestr() {
            return noncestr;
        }

        public void setNoncestr(String noncestr) {
            this.noncestr = noncestr;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public String getOut_trade_no() {
            return out_trade_no;
        }

        public void setOut_trade_no(String out_trade_no) {
            this.out_trade_no = out_trade_no;
        }
    }
}
