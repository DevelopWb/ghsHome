package com.ghs.ghshome.bean;

import java.io.Serializable;

public class CommunityParticularsBean {


    /**
     * code : 1000
     * data : {"address":"物业大厅","applyEndTime":"2019-06-11","applyFlag":1,"applyMaxNum":1,"applyStartTime":"2019-06-11","applyState":0,"clickNum":24,"contactMobile":"18630686124","contactUser":"刘","content":"大家都来物业吹空调嗑瓜子","createTime":"2019-06-11 15:51:09","endTime":"2019-06-11","freeFlag":0,"id":14,"image":"2019061115493361470618.png","price":0.01,"startTime":"2019-06-11","state":2,"title":"天太热了，避暑","type":2,"updateTime":"2019-06-11 18:27:36"}
     * message : 成功
     */

    private int code;
    private DataBean data;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class DataBean implements Serializable{
        /**
         * address : 物业大厅
         * applyEndTime : 2019-06-11
         * applyFlag : 1
         * applyMaxNum : 1
         * applyStartTime : 2019-06-11
         * applyState : 0
         * clickNum : 24
         * contactMobile : 18630686124
         * contactUser : 刘
         * content : 大家都来物业吹空调嗑瓜子
         * createTime : 2019-06-11 15:51:09
         * endTime : 2019-06-11
         * freeFlag : 0
         * id : 14
         * image : 2019061115493361470618.png
         * price : 0.01
         * startTime : 2019-06-11
         * state : 2
         * title : 天太热了，避暑
         * type : 2
         * updateTime : 2019-06-11 18:27:36
         */

        private String address;
        private String applyEndTime;
        private int applyFlag;
        private int applyMaxNum;
        private String applyStartTime;
        private int applyState;
        private int clickNum;
        private String contactMobile;
        private String contactUser;
        private String content;
        private String createTime;
        private String endTime;
        private int freeFlag;
        private int id;
        private String image;
        private double price;
        private String startTime;
        private int state;
        private String title;
        private int type;
        private String updateTime;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getApplyEndTime() {
            return applyEndTime;
        }

        public void setApplyEndTime(String applyEndTime) {
            this.applyEndTime = applyEndTime;
        }

        public int getApplyFlag() {
            return applyFlag;
        }

        public void setApplyFlag(int applyFlag) {
            this.applyFlag = applyFlag;
        }

        public int getApplyMaxNum() {
            return applyMaxNum;
        }

        public void setApplyMaxNum(int applyMaxNum) {
            this.applyMaxNum = applyMaxNum;
        }

        public String getApplyStartTime() {
            return applyStartTime;
        }

        public void setApplyStartTime(String applyStartTime) {
            this.applyStartTime = applyStartTime;
        }

        public int getApplyState() {
            return applyState;
        }

        public void setApplyState(int applyState) {
            this.applyState = applyState;
        }

        public int getClickNum() {
            return clickNum;
        }

        public void setClickNum(int clickNum) {
            this.clickNum = clickNum;
        }

        public String getContactMobile() {
            return contactMobile;
        }

        public void setContactMobile(String contactMobile) {
            this.contactMobile = contactMobile;
        }

        public String getContactUser() {
            return contactUser;
        }

        public void setContactUser(String contactUser) {
            this.contactUser = contactUser;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public int getFreeFlag() {
            return freeFlag;
        }

        public void setFreeFlag(int freeFlag) {
            this.freeFlag = freeFlag;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
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
    }
}
