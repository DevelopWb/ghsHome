package com.ghs.ghshome.bean;

import java.util.List;

/**
 * Author:wang_sir
 * Time:2018/7/30 15:28
 * Description:This is CouponsBean
 */
public class CouponsBean {

    /**
     * code : 1000
     * message : 成功
     * data : [{"id":14,"ghsUserId":3075,"couponId":1,"effectiveStartTime":"2018-07-26 18:20:07","effectiveEndTime":"2019-07-26 18:20:07","source":1,"state":1,"createTime":"2018-07-26 18:20:07","useTime":"2018-07-26 18:20:07","name":"电费代金券","description":"电费0.01元代金券","icon":null,"money":0.01,"useMoney":0,"type":3},{"id":7,"ghsUserId":3075,"couponId":4,"effectiveStartTime":"2018-06-23 16:02:35","effectiveEndTime":"2019-06-23 16:02:35","source":0,"state":1,"createTime":"2018-06-25 16:02:34","useTime":"2018-07-01 15:06:41","name":"全品类","description":"全品类0.01块钱","icon":null,"money":0.01,"useMoney":0,"type":1},{"id":6,"ghsUserId":3075,"couponId":3,"effectiveStartTime":"2018-06-23 16:02:27","effectiveEndTime":"2019-06-23 16:02:27","source":1,"state":1,"createTime":"2018-06-23 16:02:26","useTime":"2018-07-04 13:23:15","name":"物业费代金券","description":"物业费0.01元代金券","icon":null,"money":0.01,"useMoney":0,"type":4}]
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
         * id : 14
         * ghsUserId : 3075
         * couponId : 1
         * effectiveStartTime : 2018-07-26 18:20:07
         * effectiveEndTime : 2019-07-26 18:20:07
         * source : 1
         * state : 1
         * createTime : 2018-07-26 18:20:07
         * useTime : 2018-07-26 18:20:07
         * name : 电费代金券
         * description : 电费0.01元代金券
         * icon : null
         * money : 0.01
         * useMoney : 0
         * type : 3
         */

        private int id;
        private int ghsUserId;
        private int couponId;
        private String effectiveStartTime;
        private String effectiveEndTime;
        private int source;
        private int state;
        private String createTime;
        private String useTime;
        private String name;
        private String description;
        private Object icon;
        private double money;
        private int useMoney;
        private int type;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getGhsUserId() {
            return ghsUserId;
        }

        public void setGhsUserId(int ghsUserId) {
            this.ghsUserId = ghsUserId;
        }

        public int getCouponId() {
            return couponId;
        }

        public void setCouponId(int couponId) {
            this.couponId = couponId;
        }

        public String getEffectiveStartTime() {
            return effectiveStartTime;
        }

        public void setEffectiveStartTime(String effectiveStartTime) {
            this.effectiveStartTime = effectiveStartTime;
        }

        public String getEffectiveEndTime() {
            return effectiveEndTime;
        }

        public void setEffectiveEndTime(String effectiveEndTime) {
            this.effectiveEndTime = effectiveEndTime;
        }

        public int getSource() {
            return source;
        }

        public void setSource(int source) {
            this.source = source;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getUseTime() {
            return useTime;
        }

        public void setUseTime(String useTime) {
            this.useTime = useTime;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Object getIcon() {
            return icon;
        }

        public void setIcon(Object icon) {
            this.icon = icon;
        }

        public double getMoney() {
            return money;
        }

        public void setMoney(double money) {
            this.money = money;
        }

        public int getUseMoney() {
            return useMoney;
        }

        public void setUseMoney(int useMoney) {
            this.useMoney = useMoney;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }
}
