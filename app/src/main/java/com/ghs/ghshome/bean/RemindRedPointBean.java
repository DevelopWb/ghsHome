package com.ghs.ghshome.bean;

/**
 * Author:wang_sir
 * Time:2018/8/7 11:09
 * Description:This is RemindRedPointBean
 */
public class RemindRedPointBean {


    /**
     * code : 1000
     * message : 成功
     * data : {"id":70,"villageId":27,"ghsUserId":86446,"roomId":86129,"notice":0,"noticeFloatId":86,"bill":0,"serviceWork":0,"integralTask":0,"visitor":0,"updateTime":"2018-11-28 14:46:27","villageIdList":null,"roomIdList":null,"ghsUserIdList":null}
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
         * id : 70
         * villageId : 27
         * ghsUserId : 86446
         * roomId : 86129
         * notice : 0
         * noticeFloatId : 86
         * bill : 0
         * serviceWork : 0
         * integralTask : 0
         * visitor : 0
         * updateTime : 2018-11-28 14:46:27
         * villageIdList : null
         * roomIdList : null
         * ghsUserIdList : null
         */

        private int id;
        private int villageId;
        private int ghsUserId;
        private int roomId;
        private int notice;
        private int noticeFloatId;
        private int bill;
        private int serviceWork;
        private int integralTask;
        private int visitor;
        private String updateTime;
        private Object villageIdList;
        private Object roomIdList;
        private Object ghsUserIdList;

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

        public int getGhsUserId() {
            return ghsUserId;
        }

        public void setGhsUserId(int ghsUserId) {
            this.ghsUserId = ghsUserId;
        }

        public int getRoomId() {
            return roomId;
        }

        public void setRoomId(int roomId) {
            this.roomId = roomId;
        }

        public int getNotice() {
            return notice;
        }

        public void setNotice(int notice) {
            this.notice = notice;
        }

        public int getNoticeFloatId() {
            return noticeFloatId;
        }

        public void setNoticeFloatId(int noticeFloatId) {
            this.noticeFloatId = noticeFloatId;
        }

        public int getBill() {
            return bill;
        }

        public void setBill(int bill) {
            this.bill = bill;
        }

        public int getServiceWork() {
            return serviceWork;
        }

        public void setServiceWork(int serviceWork) {
            this.serviceWork = serviceWork;
        }

        public int getIntegralTask() {
            return integralTask;
        }

        public void setIntegralTask(int integralTask) {
            this.integralTask = integralTask;
        }

        public int getVisitor() {
            return visitor;
        }

        public void setVisitor(int visitor) {
            this.visitor = visitor;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public Object getVillageIdList() {
            return villageIdList;
        }

        public void setVillageIdList(Object villageIdList) {
            this.villageIdList = villageIdList;
        }

        public Object getRoomIdList() {
            return roomIdList;
        }

        public void setRoomIdList(Object roomIdList) {
            this.roomIdList = roomIdList;
        }

        public Object getGhsUserIdList() {
            return ghsUserIdList;
        }

        public void setGhsUserIdList(Object ghsUserIdList) {
            this.ghsUserIdList = ghsUserIdList;
        }
    }
}
