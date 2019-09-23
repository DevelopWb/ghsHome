package com.ghs.ghshome.bean;

import com.stx.xhb.xbanner.entity.SimpleBannerInfo;

import java.util.List;

public class BannerListBean {


    /**
     * code : 0
     * data : [{"activityId":0,"activityTitle":"","clickNum":0,"createTime":"","id":0,"imageUrl":"","sortIndex":0,"state":0,"type":0,"updateTime":"","url":""}]
     * message :
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

    public static class DataBean extends SimpleBannerInfo {
        /**
         * activityId : 0
         * activityTitle :
         * clickNum : 0
         * createTime :
         * id : 0
         * imageUrl :
         * sortIndex : 0
         * state : 0
         * type : 0
         * updateTime :
         * url :
         */

        private int activityId;
        private String activityTitle;
        private int clickNum;
        private String createTime;
        private int id;
        private String imageUrl;
        private int sortIndex;
        private int state;
        private int type;
        private String updateTime;
        private String url;

        public int getActivityId() {
            return activityId;
        }

        public void setActivityId(int activityId) {
            this.activityId = activityId;
        }

        public String getActivityTitle() {
            return activityTitle;
        }

        public void setActivityTitle(String activityTitle) {
            this.activityTitle = activityTitle;
        }

        public int getClickNum() {
            return clickNum;
        }

        public void setClickNum(int clickNum) {
            this.clickNum = clickNum;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public int getSortIndex() {
            return sortIndex;
        }

        public void setSortIndex(int sortIndex) {
            this.sortIndex = sortIndex;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        @Override
        public Object getXBannerUrl() {
            return getImageUrl();
        }
    }
}
