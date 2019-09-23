package com.ghs.ghshome.bean;

import java.util.List;

/**
 * Author:wang_sir
 * Time:2019/5/28 11:10
 * Description:This is HouseKeeperBean   管家服务功能模块
 */
public class HouseKeeperBean {
    /**
     * code : 1000
     * message : 成功
     * data : [{"id":3,"villageId":27,"roomId":null,"ghsUserId":null,"msgDataId":113,"titile":"@明@斌 公告","content":"褐石公园小区，公告","msgType":"notice","createTime":"2019-05-22 11:18:46","createTimeStr":"05月22日 11:18"}]
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
         * id : 3
         * villageId : 27
         * roomId : null
         * ghsUserId : null
         * msgDataId : 113
         * titile : @明@斌 公告
         * content : 褐石公园小区，公告
         * msgType : notice
         * createTime : 2019-05-22 11:18:46
         * createTimeStr : 05月22日 11:18
         */

        private int id;
        private int villageId;
        private int roomId;
        private int ghsUserId;
        private int msgDataId;
        private String titile;
        private String content;
        private String msgType;
        private String createTime;
        private String createTimeStr;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getVillageId() {
            return villageId;
        }

        public void setVillageId(int villageId) {
            this.villageId = villageId;
        }

        public int getRoomId() {
            return roomId;
        }

        public void setRoomId(int roomId) {
            this.roomId = roomId;
        }

        public int getGhsUserId() {
            return ghsUserId;
        }

        public void setGhsUserId(int ghsUserId) {
            this.ghsUserId = ghsUserId;
        }

        public int getMsgDataId() {
            return msgDataId;
        }

        public void setMsgDataId(int msgDataId) {
            this.msgDataId = msgDataId;
        }

        public String getTitile() {
            return titile == null ? "" : titile;
        }

        public void setTitile(String titile) {
            this.titile = titile;
        }

        public String getContent() {
            return content == null ? "" : content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getMsgType() {
            return msgType == null ? "" : msgType;
        }

        public void setMsgType(String msgType) {
            this.msgType = msgType;
        }

        public String getCreateTime() {
            return createTime == null ? "" : createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getCreateTimeStr() {
            return createTimeStr == null ? "" : createTimeStr;
        }

        public void setCreateTimeStr(String createTimeStr) {
            this.createTimeStr = createTimeStr;
        }
    }
}
