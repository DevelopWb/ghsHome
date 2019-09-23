package com.ghs.ghshome.bean;

/**
 * Author:wang_sir
 * Time:2018/7/26 18:04
 * Description:This is SignPrizeBean
 */
public class SignPrizeBean {

    /**
     * code : 1000
     * message : 成功
     * data : {"couponNum":1,"couponDo":{"id":1,"name":"电费代金券","description":"电费0.01元代金券","icon":null,"money":0.01,"useMoney":0,"type":3,"effectiveDays":365,"updateTime":"2018-06-19 18:42:00"},"goodsNum":1,"goodsDO":{"id":1,"goodsName":"小玩具","updateTime":"2018-06-20 11:13:54"}}
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
         * couponNum : 1
         * couponDo : {"id":1,"name":"电费代金券","description":"电费0.01元代金券","icon":null,"money":0.01,"useMoney":0,"type":3,"effectiveDays":365,"updateTime":"2018-06-19 18:42:00"}
         * goodsNum : 1
         * goodsDO : {"id":1,"goodsName":"小玩具","updateTime":"2018-06-20 11:13:54"}
         */

        private int couponNum;
        private CouponDoBean couponDo;
        private int goodsNum;
        private GoodsDOBean goodsDO;

        public int getCouponNum() {
            return couponNum;
        }

        public void setCouponNum(int couponNum) {
            this.couponNum = couponNum;
        }

        public CouponDoBean getCouponDo() {
            return couponDo;
        }

        public void setCouponDo(CouponDoBean couponDo) {
            this.couponDo = couponDo;
        }

        public int getGoodsNum() {
            return goodsNum;
        }

        public void setGoodsNum(int goodsNum) {
            this.goodsNum = goodsNum;
        }

        public GoodsDOBean getGoodsDO() {
            return goodsDO;
        }

        public void setGoodsDO(GoodsDOBean goodsDO) {
            this.goodsDO = goodsDO;
        }

        public static class CouponDoBean {
            /**
             * id : 1
             * name : 电费代金券
             * description : 电费0.01元代金券
             * icon : null
             * money : 0.01
             * useMoney : 0
             * type : 3
             * effectiveDays : 365
             * updateTime : 2018-06-19 18:42:00
             */

            private int id;
            private String name;
            private String description;
            private Object icon;
            private double money;
            private int useMoney;
            private int type;
            private int effectiveDays;
            private String updateTime;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
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

            public int getEffectiveDays() {
                return effectiveDays;
            }

            public void setEffectiveDays(int effectiveDays) {
                this.effectiveDays = effectiveDays;
            }

            public String getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(String updateTime) {
                this.updateTime = updateTime;
            }
        }

        public static class GoodsDOBean {
            /**
             * id : 1
             * goodsName : 小玩具
             * updateTime : 2018-06-20 11:13:54
             */

            private int id;
            private String goodsName;
            private String updateTime;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getGoodsName() {
                return goodsName;
            }

            public void setGoodsName(String goodsName) {
                this.goodsName = goodsName;
            }

            public String getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(String updateTime) {
                this.updateTime = updateTime;
            }
        }
    }
}
