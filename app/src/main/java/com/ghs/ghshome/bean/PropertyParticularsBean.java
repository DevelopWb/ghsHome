package com.ghs.ghshome.bean;

public class PropertyParticularsBean {


    /**
     * code : 0
     * data : {"auditUserId":0,"content":"","createTime":"","fullName":"","ghsUserId":0,"id":0,"imageUrl":"","mobile":"","roleType":0,"roomFullName":"","roomId":0,"state":0,"updateTime":"","villageId":0}
     * message :
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

    public static class DataBean {
        /**
         * auditUserId : 0
         * content :
         * createTime :
         * fullName :
         * ghsUserId : 0
         * id : 0
         * imageUrl :
         * mobile :
         * roleType : 0
         * roomFullName :
         * roomId : 0
         * state : 0
         * updateTime :
         * villageId : 0
         */

        private int auditUserId;
        private String content;
        private String createTime;
        private String fullName;
        private int ghsUserId;
        private int id;
        private String imageUrl;
        private String mobile;
        private int roleType;
        private String roomFullName;
        private int roomId;
        private int state;
        private String updateTime;
        private int villageId;

        public int getAuditUserId() {
            return auditUserId;
        }

        public void setAuditUserId(int auditUserId) {
            this.auditUserId = auditUserId;
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

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        public int getGhsUserId() {
            return ghsUserId;
        }

        public void setGhsUserId(int ghsUserId) {
            this.ghsUserId = ghsUserId;
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

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public int getRoleType() {
            return roleType;
        }

        public void setRoleType(int roleType) {
            this.roleType = roleType;
        }

        public String getRoomFullName() {
            return roomFullName;
        }

        public void setRoomFullName(String roomFullName) {
            this.roomFullName = roomFullName;
        }

        public int getRoomId() {
            return roomId;
        }

        public void setRoomId(int roomId) {
            this.roomId = roomId;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public int getVillageId() {
            return villageId;
        }

        public void setVillageId(int villageId) {
            this.villageId = villageId;
        }
    }
}
