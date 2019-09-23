package com.ghs.ghshome.bean;

/**
 * Author:wang_sir
 * Time:2018/7/23 17:09
 * Description:This is VillageNoticeDetailBean
 */
public class VillageNoticeDetailBean {

    /**
     * code : 1000
     * message : 成功
     * data : {"id":5,"sysUserId":183,"title":"催缴公告333","introduction":"催缴公告333催缴公告333","content":"<p>催缴公告333催缴公告333催缴公告333<br><\/p>","state":2,"updateTime":"2019-05-29 11:12:59","top":0,"noticeUrl":"notice/20190529111252713.html","auditUserId":183,"auditTime":"2019-05-29 11:12:59","userName":null,"auditTimeStr":"2019-05-29 11:12"}
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
         * id : 5
         * sysUserId : 183
         * title : 催缴公告333
         * introduction : 催缴公告333催缴公告333
         * content : <p>催缴公告333催缴公告333催缴公告333<br></p>
         * state : 2
         * updateTime : 2019-05-29 11:12:59
         * top : 0
         * noticeUrl : notice/20190529111252713.html
         * auditUserId : 183
         * auditTime : 2019-05-29 11:12:59
         * userName : null
         * auditTimeStr : 2019-05-29 11:12
         */

        private int id;
        private int sysUserId;
        private String title;
        private String introduction;
        private String content;
        private int state;
        private String updateTime;
        private int top;
        private String noticeUrl;
        private int auditUserId;
        private String auditTime;
        private String  userName;
        private String auditTimeStr;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getSysUserId() {
            return sysUserId;
        }

        public void setSysUserId(int sysUserId) {
            this.sysUserId = sysUserId;
        }

        public String getTitle() {
            return title == null ? "" : title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getIntroduction() {
            return introduction == null ? "" : introduction;
        }

        public void setIntroduction(String introduction) {
            this.introduction = introduction;
        }

        public String getContent() {
            return content == null ? "" : content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public String getUpdateTime() {
            return updateTime == null ? "" : updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public int getTop() {
            return top;
        }

        public void setTop(int top) {
            this.top = top;
        }

        public String getNoticeUrl() {
            return noticeUrl == null ? "" : noticeUrl;
        }

        public void setNoticeUrl(String noticeUrl) {
            this.noticeUrl = noticeUrl;
        }

        public int getAuditUserId() {
            return auditUserId;
        }

        public void setAuditUserId(int auditUserId) {
            this.auditUserId = auditUserId;
        }

        public String getAuditTime() {
            return auditTime == null ? "" : auditTime;
        }

        public void setAuditTime(String auditTime) {
            this.auditTime = auditTime;
        }

        public String getUserName() {
            return userName == null ? "" : userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getAuditTimeStr() {
            return auditTimeStr == null ? "" : auditTimeStr;
        }

        public void setAuditTimeStr(String auditTimeStr) {
            this.auditTimeStr = auditTimeStr;
        }
    }
}
