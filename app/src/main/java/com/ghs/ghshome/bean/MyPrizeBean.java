package com.ghs.ghshome.bean;

import java.util.List;

/**
 * Author:wang_sir
 * Time:2018/7/23 9:55
 * Description:This is MyPrizeBean
 */


    public  class MyPrizeBean {
        /**
         * offset : 0
         * limit : 20
         * total : 4
         * params : {}
         * param :
         * rows : [{"id":7,"ghsUserId":3075,"couponId":4,"effectiveStartTime":"2018-06-23 16:02:35","effectiveEndTime":"2019-06-23 16:02:35","source":0,"state":1,"createTime":"2018-06-25 16:02:34","useTime":"2018-07-01 15:06:41","name":"全品类","description":"全品类0.01块钱","icon":null,"money":0.01,"useMoney":0,"type":1},{"id":6,"ghsUserId":3075,"couponId":3,"effectiveStartTime":"2018-06-23 16:02:27","effectiveEndTime":"2019-06-23 16:02:27","source":1,"state":1,"createTime":"2018-06-23 16:02:26","useTime":"2018-07-04 13:23:15","name":"物业费代金券","description":"物业费0.01元代金券","icon":null,"money":0.01,"useMoney":0,"type":4},{"id":5,"ghsUserId":3075,"couponId":2,"effectiveStartTime":"2018-06-23 16:02:17","effectiveEndTime":"2019-06-23 16:02:17","source":1,"state":1,"createTime":"2018-06-23 16:02:17","useTime":"2018-06-23 16:02:17","name":"水费代金券","description":"水费0.01元代金券","icon":null,"money":0.01,"useMoney":0,"type":2},{"id":4,"ghsUserId":3075,"couponId":1,"effectiveStartTime":"2018-06-20 15:13:29","effectiveEndTime":"2019-06-20 15:13:29","source":0,"state":1,"createTime":"2018-06-20 15:13:28","useTime":"2018-07-04 13:19:17","name":"电费代金券","description":"电费0.01元代金券","icon":null,"money":0.01,"useMoney":0,"type":3}]
         */

        private int offset;
        private int limit;
        private int total;
        private ParamsEntity params;
        private String param;
        private List<RowsEntity> rows;

        public int getOffset() {
            return offset;
        }

        public void setOffset(int offset) {
            this.offset = offset;
        }

        public int getLimit() {
            return limit;
        }

        public void setLimit(int limit) {
            this.limit = limit;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public ParamsEntity getParams() {
            return params;
        }

        public void setParams(ParamsEntity params) {
            this.params = params;
        }

        public String getParam() {
            return param;
        }

        public void setParam(String param) {
            this.param = param;
        }

        public List<RowsEntity> getRows() {
            return rows;
        }

        public void setRows(List<RowsEntity> rows) {
            this.rows = rows;
        }

        public static class ParamsEntity {
        }

        public static class RowsEntity {
            /**
             * id : 7
             * ghsUserId : 3075
             * couponId : 4
             * effectiveStartTime : 2018-06-23 16:02:35
             * effectiveEndTime : 2019-06-23 16:02:35
             * source : 0
             * state : 1
             * createTime : 2018-06-25 16:02:34
             * useTime : 2018-07-01 15:06:41
             * name : 全品类
             * description : 全品类0.01块钱
             * icon : null
             * money : 0.01
             * useMoney : 0
             * type : 1
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
