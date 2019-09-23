package com.ghs.ghshome.bean;

import java.io.Serializable;
import java.util.List;

public class DecorationBean {


    /**
     * code : 0
     * data : {"limit":0,"offset":0,"param":"","params":{},"rows":[{"createTime":"","endTime":"","ghsUserId":0,"id":0,"roleType":0,"roomFullName":"","roomId":0,"startTime":"","state":0,"type":0,"userName":"","villageId":0}],"total":0}
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
         * rows : [{"createTime":"","endTime":"","ghsUserId":0,"id":0,"roleType":0,"roomFullName":"","roomId":0,"startTime":"","state":0,"type":0,"userName":"","villageId":0}]
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

        public static class RowsBean implements Serializable {
            /**
             * createTime :
             * endTime :
             * ghsUserId : 0
             * id : 0
             * roleType : 0
             * roomFullName :
             * roomId : 0
             * startTime :
             * state : 0
             * type : 0
             * userName :
             * villageId : 0
             */

            private String createTime;
            private String endTime;
            private int ghsUserId;
            private int id;
            private int roleType;
            private String roomFullName;
            private int roomId;
            private String startTime;
            private int state;
            private int type;
            private String userName;
            private int villageId;

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

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
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
