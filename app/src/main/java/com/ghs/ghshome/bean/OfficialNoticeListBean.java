package com.ghs.ghshome.bean;

import java.util.List;

/**
 * Author:wang_sir
 * Time:2019/5/29 11:14
 * Description:This is OfficialNoticeListBean
 */
public class OfficialNoticeListBean {
    /**
     * code : 1000
     * message : 成功
     * data : {"offset":0,"limit":5,"total":4,"params":{},"param":"","rows":[{"id":5,"title":"催缴公告333","introduction":"催缴公告333催缴公告333","content":"<p>催缴公告333催缴公告333催缴公告333<br><\/p>","state":2,"top":0,"noticeUrl":"notice/20190529111252713.html","auditTime":"2019-05-29 11:12:59","auditTimeStr":"2019-05-29 11:12","userNoticeType":2}]}
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
         * offset : 0
         * limit : 5
         * total : 4
         * params : {}
         * param :
         * rows : [{"id":5,"title":"催缴公告333","introduction":"催缴公告333催缴公告333","content":"<p>催缴公告333催缴公告333催缴公告333<br><\/p>","state":2,"top":0,"noticeUrl":"notice/20190529111252713.html","auditTime":"2019-05-29 11:12:59","auditTimeStr":"2019-05-29 11:12","userNoticeType":2}]
         */

        private int offset;
        private int limit;
        private int total;
        private List<RowsBean> rows;

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

        public List<RowsBean> getRows() {
            return rows;
        }

        public void setRows(List<RowsBean> rows) {
            this.rows = rows;
        }

        public static class RowsBean {
            /**
             * id : 5
             * title : 催缴公告333
             * introduction : 催缴公告333催缴公告333
             * content : <p>催缴公告333催缴公告333催缴公告333<br></p>
             * state : 2
             * top : 0
             * noticeUrl : notice/20190529111252713.html
             * auditTime : 2019-05-29 11:12:59
             * auditTimeStr : 2019-05-29 11:12
             * userNoticeType : 2
             */

            private int id;
            private String title;
            private String introduction;
            private String content;
            private int state;
            private int top;
            private String noticeUrl;
            private String auditTime;
            private String auditTimeStr;
            private int userNoticeType;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getIntroduction() {
                return introduction;
            }

            public void setIntroduction(String introduction) {
                this.introduction = introduction;
            }

            public String getContent() {
                return content;
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

            public int getTop() {
                return top;
            }

            public void setTop(int top) {
                this.top = top;
            }

            public String getNoticeUrl() {
                return noticeUrl;
            }

            public void setNoticeUrl(String noticeUrl) {
                this.noticeUrl = noticeUrl;
            }

            public String getAuditTime() {
                return auditTime;
            }

            public void setAuditTime(String auditTime) {
                this.auditTime = auditTime;
            }

            public String getAuditTimeStr() {
                return auditTimeStr;
            }

            public void setAuditTimeStr(String auditTimeStr) {
                this.auditTimeStr = auditTimeStr;
            }

            public int getUserNoticeType() {
                return userNoticeType;
            }

            public void setUserNoticeType(int userNoticeType) {
                this.userNoticeType = userNoticeType;
            }
        }
    }
}
