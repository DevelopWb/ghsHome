package com.ghs.ghshome.bean;

import java.util.List;

/**
 * Author:wang_sir
 * Time:2019/8/14 17:34
 * Description:This is ParkSpaceBean
 * 车位
 */
public class ParkSpaceBean {
    /**
     * code : 1000
     * message : 成功
     * data : [{"id":3,"carportId":103,"ghsUserId":86446,"bindType":1,"startTime":"2019-07-14 16:05:59","endTime":"2019-12-14 16:06:09","syncFlag":1,"remark":null,"createTime":null,"createUserId":null,"updateTime":null,"updateUserId":null,"villageId":27,"areaName":"wjf长租区","carportNum":"wjf001","carportType":2},{"id":2,"carportId":81,"ghsUserId":86446,"bindType":1,"startTime":"2019-07-14 16:05:59","endTime":"2019-12-14 16:06:09","syncFlag":1,"remark":null,"createTime":null,"createUserId":null,"updateTime":null,"updateUserId":null,"villageId":27,"areaName":"乔布斯自有1","carportNum":"自由001","carportType":2},{"id":1,"carportId":80,"ghsUserId":86446,"bindType":1,"startTime":"2019-07-14 16:05:59","endTime":"2019-12-14 16:06:09","syncFlag":1,"remark":null,"createTime":"2019-08-14 16:06:38","createUserId":null,"updateTime":"2019-08-14 16:06:35","updateUserId":null,"villageId":27,"areaName":"w_bin自有","carportNum":"w_bin自有001","carportType":1}]
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
         * carportId : 103
         * ghsUserId : 86446
         * bindType : 1
         * startTime : 2019-07-14 16:05:59
         * endTime : 2019-12-14 16:06:09
         * syncFlag : 1
         * remark : null
         * createTime : null
         * createUserId : null
         * updateTime : null
         * updateUserId : null
         * villageId : 27
         * areaName : wjf长租区
         * carportNum : wjf001
         * carportType : 2
         */

        private int id;
        private int carportId;
        private int ghsUserId;
        private int bindType;
        private String startTime;
        private String endTime;
        private int syncFlag;
        private String remark;
        private String createTime;
        private int  createUserId;
        private String updateTime;
        private int updateUserId;
        private int villageId;
        private String areaName;
        private String carportNum;
        private int carportType;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getCarportId() {
            return carportId;
        }

        public void setCarportId(int carportId) {
            this.carportId = carportId;
        }

        public int getGhsUserId() {
            return ghsUserId;
        }

        public void setGhsUserId(int ghsUserId) {
            this.ghsUserId = ghsUserId;
        }

        public int getBindType() {
            return bindType;
        }

        public void setBindType(int bindType) {
            this.bindType = bindType;
        }

        public String getStartTime() {
            return startTime == null ? "" : startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getEndTime() {
            return endTime == null ? "" : endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public int getSyncFlag() {
            return syncFlag;
        }

        public void setSyncFlag(int syncFlag) {
            this.syncFlag = syncFlag;
        }

        public String getRemark() {
            return remark == null ? "" : remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getCreateTime() {
            return createTime == null ? "" : createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public int getCreateUserId() {
            return createUserId;
        }

        public void setCreateUserId(int createUserId) {
            this.createUserId = createUserId;
        }

        public String getUpdateTime() {
            return updateTime == null ? "" : updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public int getUpdateUserId() {
            return updateUserId;
        }

        public void setUpdateUserId(int updateUserId) {
            this.updateUserId = updateUserId;
        }

        public int getVillageId() {
            return villageId;
        }

        public void setVillageId(int villageId) {
            this.villageId = villageId;
        }

        public String getAreaName() {
            return areaName == null ? "" : areaName;
        }

        public void setAreaName(String areaName) {
            this.areaName = areaName;
        }

        public String getCarportNum() {
            return carportNum == null ? "" : carportNum;
        }

        public void setCarportNum(String carportNum) {
            this.carportNum = carportNum;
        }

        public int getCarportType() {
            return carportType;
        }

        public void setCarportType(int carportType) {
            this.carportType = carportType;
        }
    }
}
