package com.ghs.ghshome.bean;

import java.util.List;

/**
 * Author:wang_sir
 * Time:2018/7/23 14:13
 * Description:This is VillageNoticeBean
 */
public class VillageNoticeBean {


    /**
     * code : 0
     * data : {"limit":0,"offset":0,"param":"","params":{},"rows":[{"content":"","id":0,"introduction":"","noticeUrl":"","propertyId":0,"propertyName":"","state":0,"sysUserId":0,"title":"","top":0,"updateTime":"","updateTimeOld":"","userName":"","userNoticeType":0,"villageId":0}],"total":0}
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
         * limit : 0
         * offset : 0
         * param :
         * params : {}
         * rows : [{"content":"","id":0,"introduction":"","noticeUrl":"","propertyId":0,"propertyName":"","state":0,"sysUserId":0,"title":"","top":0,"updateTime":"","updateTimeOld":"","userName":"","userNoticeType":0,"villageId":0}]
         * total : 0
         */

        private int limit;
        private int offset;
        private String param;
        private ParamsBean params;
        private int total;
        private List<RowsBean> rows;

        public int getLimit() {
            return limit;
        }

        public void setLimit(int limit) {
            this.limit = limit;
        }

        public int getOffset() {
            return offset;
        }

        public void setOffset(int offset) {
            this.offset = offset;
        }

        public String getParam() {
            return param;
        }

        public void setParam(String param) {
            this.param = param;
        }

        public ParamsBean getParams() {
            return params;
        }

        public void setParams(ParamsBean params) {
            this.params = params;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<RowsBean> getRows() {
            return rows;
        }

        public void setRows(List<RowsBean> rows) {
            this.rows = rows;
        }

        public static class ParamsBean {
        }

        public static class RowsBean {
            /**
             * content :
             * id : 0
             * introduction :
             * noticeUrl :
             * propertyId : 0
             * propertyName :
             * state : 0
             * sysUserId : 0
             * title :
             * top : 0
             * updateTime :
             * updateTimeOld :
             * userName :
             * userNoticeType : 0
             * villageId : 0
             */

            private String content;
            private int id;
            private String introduction;
            private String noticeUrl;
            private int propertyId;
            private String propertyName;
            private int state;
            private int sysUserId;
            private String title;
            private int top;
            private String updateTime;
            private String updateTimeOld;
            private String userName;
            private int userNoticeType;
            private int villageId;

            public String getContent() {
                return content == null ? "" : content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getIntroduction() {
                return introduction == null ? "" : introduction;
            }

            public void setIntroduction(String introduction) {
                this.introduction = introduction;
            }

            public String getNoticeUrl() {
                return noticeUrl == null ? "" : noticeUrl;
            }

            public void setNoticeUrl(String noticeUrl) {
                this.noticeUrl = noticeUrl;
            }

            public int getPropertyId() {
                return propertyId;
            }

            public void setPropertyId(int propertyId) {
                this.propertyId = propertyId;
            }

            public String getPropertyName() {
                return propertyName == null ? "" : propertyName;
            }

            public void setPropertyName(String propertyName) {
                this.propertyName = propertyName;
            }

            public int getState() {
                return state;
            }

            public void setState(int state) {
                this.state = state;
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

            public int getTop() {
                return top;
            }

            public void setTop(int top) {
                this.top = top;
            }

            public String getUpdateTime() {
                return updateTime == null ? "" : updateTime;
            }

            public void setUpdateTime(String updateTime) {
                this.updateTime = updateTime;
            }

            public String getUpdateTimeOld() {
                return updateTimeOld == null ? "" : updateTimeOld;
            }

            public void setUpdateTimeOld(String updateTimeOld) {
                this.updateTimeOld = updateTimeOld;
            }

            public String getUserName() {
                return userName == null ? "" : userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }

            public int getUserNoticeType() {
                return userNoticeType;
            }

            public void setUserNoticeType(int userNoticeType) {
                this.userNoticeType = userNoticeType;
            }

            public int getVillageId() {
                return villageId;
            }

            public void setVillageId(int villageId) {
                this.villageId = villageId;
            }
        }
    }
}
