package com.ghs.ghshome.bean;

import java.util.List;

/**
 * Author:wang_sir
 * Time:2019/7/30 16:16
 * Description:This is SeedRecordBean
 */
public class SeedRecordBean {
    /**
     * code : 1000
     * message : 成功
     * data : [{"month":"本月","seedDetailDOList":[{"id":44,"seedTaskId":9,"userId":86446,"seed":5,"remark":"上传头像","type":1,"createTime":"2019-07-29 18:52:42","expireTime":"2019-10-29 18:52:42"},{"id":42,"seedTaskId":3,"userId":86446,"seed":10,"remark":"首次登陆","type":1,"createTime":"2019-07-29 17:44:07","expireTime":"2019-10-29 17:44:07"},{"id":40,"seedTaskId":2,"userId":86446,"seed":5000,"remark":"绑定车辆","type":1,"createTime":"2019-07-29 17:02:12","expireTime":"2019-10-29 17:02:12"}]}]
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
         * month : 本月
         * seedDetailDOList : [{"id":44,"seedTaskId":9,"userId":86446,"seed":5,"remark":"上传头像","type":1,"createTime":"2019-07-29 18:52:42","expireTime":"2019-10-29 18:52:42"},{"id":42,"seedTaskId":3,"userId":86446,"seed":10,"remark":"首次登陆","type":1,"createTime":"2019-07-29 17:44:07","expireTime":"2019-10-29 17:44:07"},{"id":40,"seedTaskId":2,"userId":86446,"seed":5000,"remark":"绑定车辆","type":1,"createTime":"2019-07-29 17:02:12","expireTime":"2019-10-29 17:02:12"}]
         */

        private String month;
        private List<SeedDetailDOListBean> seedDetailDOList;

        public String getMonth() {
            return month;
        }

        public void setMonth(String month) {
            this.month = month;
        }

        public List<SeedDetailDOListBean> getSeedDetailDOList() {
            return seedDetailDOList;
        }

        public void setSeedDetailDOList(List<SeedDetailDOListBean> seedDetailDOList) {
            this.seedDetailDOList = seedDetailDOList;
        }

        public static class SeedDetailDOListBean {
            /**
             * id : 44
             * seedTaskId : 9
             * userId : 86446
             * seed : 5
             * remark : 上传头像
             * type : 1
             * createTime : 2019-07-29 18:52:42
             * expireTime : 2019-10-29 18:52:42
             */

            private int id;
            private int seedTaskId;
            private int userId;
            private int seed;
            private String remark;
            private int type;
            private String createTime;
            private String expireTime;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getSeedTaskId() {
                return seedTaskId;
            }

            public void setSeedTaskId(int seedTaskId) {
                this.seedTaskId = seedTaskId;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public int getSeed() {
                return seed;
            }

            public void setSeed(int seed) {
                this.seed = seed;
            }

            public String getRemark() {
                return remark == null ? "" : remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getCreateTime() {
                return createTime == null ? "" : createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getExpireTime() {
                return expireTime == null ? "" : expireTime;
            }

            public void setExpireTime(String expireTime) {
                this.expireTime = expireTime;
            }
        }
    }
}
