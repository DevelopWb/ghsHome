package com.ghs.ghshome.bean;

import java.util.List;

/**
 * Author:wang_sir
 * Time:2019/5/24 11:38
 * Description:This is WorkOrderDesBean
 */
public class WorkOrderDesBean {
    /**
     * code : 1000
     * message : 成功
     * data : {"serviceWorkDO":{"id":226,"source":2,"propertyId":30,"villageId":27,"portionId":215,"towerId":521,"cellId":683,"roomId":null,"sysUserId":42,"repairUserId":42,"ownerName":"明申","mobile":"15890086993","ghsUserId":86445,"serviceType":5,"state":1,"content":"明申测试","imageUrl":null,"hopeGotoTime":null,"temporaryTaskId":585,"createTime":"2019-05-23 13:49:59","updateTime":"2019-05-23 13:49:59","startCreateTime":null,"endCreateTime":null,"sysUserName":"龙洋","repairUserName":"龙洋","repairUserMobile":"18500975534","villageMsg":"褐石公园小区（测试） -一期 -2号楼 -7单元","roomNumber":null,"villageName":"褐石公园小区（测试）","portionName":"一期","towerNumber":"2号楼","cellName":"7单元","distributeTime":null,"receiveTime":null,"arriveTime":null,"repairMsg":null,"useMaterial":null,"remark":null,"finishJobTime":null,"repairHour":null,"repairMinute":null,"engineeringManager":null,"evaluateArrive":null,"evaluateAttitude":null,"evaluateQuality":null,"paid":null,"repairMoney":null,"suggest":null},"statusRecordList":[{"id":4,"serviceWorkId":226,"content":"物业跟进中","remark":null,"imageUrl":null,"createTime":"2019-05-23 13:49:59"},{"id":3,"serviceWorkId":226,"content":"物业待跟进","remark":null,"imageUrl":null,"createTime":"2019-05-23 13:49:59"},{"id":2,"serviceWorkId":226,"content":"已下单","remark":null,"imageUrl":null,"createTime":"2019-05-23 13:49:59"}]}
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
         * serviceWorkDO : {"id":226,"source":2,"propertyId":30,"villageId":27,"portionId":215,"towerId":521,"cellId":683,"roomId":null,"sysUserId":42,"repairUserId":42,"ownerName":"明申","mobile":"15890086993","ghsUserId":86445,"serviceType":5,"state":1,"content":"明申测试","imageUrl":null,"hopeGotoTime":null,"temporaryTaskId":585,"createTime":"2019-05-23 13:49:59","updateTime":"2019-05-23 13:49:59","startCreateTime":null,"endCreateTime":null,"sysUserName":"龙洋","repairUserName":"龙洋","repairUserMobile":"18500975534","villageMsg":"褐石公园小区（测试） -一期 -2号楼 -7单元","roomNumber":null,"villageName":"褐石公园小区（测试）","portionName":"一期","towerNumber":"2号楼","cellName":"7单元","distributeTime":null,"receiveTime":null,"arriveTime":null,"repairMsg":null,"useMaterial":null,"remark":null,"finishJobTime":null,"repairHour":null,"repairMinute":null,"engineeringManager":null,"evaluateArrive":null,"evaluateAttitude":null,"evaluateQuality":null,"paid":null,"repairMoney":null,"suggest":null}
         * statusRecordList : [{"id":4,"serviceWorkId":226,"content":"物业跟进中","remark":null,"imageUrl":null,"createTime":"2019-05-23 13:49:59"},{"id":3,"serviceWorkId":226,"content":"物业待跟进","remark":null,"imageUrl":null,"createTime":"2019-05-23 13:49:59"},{"id":2,"serviceWorkId":226,"content":"已下单","remark":null,"imageUrl":null,"createTime":"2019-05-23 13:49:59"}]
         */

        private ServiceWorkDOBean serviceWorkDO;
        private List<StatusRecordListBean> statusRecordList;

        public ServiceWorkDOBean getServiceWorkDO() {
            return serviceWorkDO;
        }

        public void setServiceWorkDO(ServiceWorkDOBean serviceWorkDO) {
            this.serviceWorkDO = serviceWorkDO;
        }

        public List<StatusRecordListBean> getStatusRecordList() {
            return statusRecordList;
        }

        public void setStatusRecordList(List<StatusRecordListBean> statusRecordList) {
            this.statusRecordList = statusRecordList;
        }

        public static class ServiceWorkDOBean {
            private int id;
            private int source;
            private int propertyId;
            private int villageId;
            private int portionId;
            private int towerId;
            private int cellId;
            private int roomId;
            private int sysUserId;
            private int repairUserId;
            private String ownerName;
            private String mobile;
            private int ghsUserId;
            private int serviceType;
            private int state;
            private String content;
            private String imageUrl;
            private String hopeGotoTime;
            private int temporaryTaskId;
            private String createTime;
            private String updateTime;
            private String startCreateTime;
            private String endCreateTime;
            private String sysUserName;
            private String repairUserName;
            private String repairUserMobile;
            private String villageMsg;
            private String roomNumber;
            private String villageName;
            private String portionName;
            private String towerNumber;
            private String cellName;
            private String distributeTime;
            private String receiveTime;
            private String arriveTime;
            private String repairMsg;
            private String useMaterial;
            private String remark;
            private String finishJobTime;
            private int  repairHour;
            private int  repairMinute;
            private String engineeringManager;
            private int evaluateArrive;
            private int evaluateAttitude;
            private int evaluateQuality;
            private int paid;
            private double repairMoney;
            private String suggest;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getSource() {
                return source;
            }

            public void setSource(int source) {
                this.source = source;
            }

            public int getPropertyId() {
                return propertyId;
            }

            public void setPropertyId(int propertyId) {
                this.propertyId = propertyId;
            }

            public int getVillageId() {
                return villageId;
            }

            public void setVillageId(int villageId) {
                this.villageId = villageId;
            }

            public int getPortionId() {
                return portionId;
            }

            public void setPortionId(int portionId) {
                this.portionId = portionId;
            }

            public int getTowerId() {
                return towerId;
            }

            public void setTowerId(int towerId) {
                this.towerId = towerId;
            }

            public int getCellId() {
                return cellId;
            }

            public void setCellId(int cellId) {
                this.cellId = cellId;
            }

            public int getRoomId() {
                return roomId;
            }

            public void setRoomId(int roomId) {
                this.roomId = roomId;
            }

            public int getSysUserId() {
                return sysUserId;
            }

            public void setSysUserId(int sysUserId) {
                this.sysUserId = sysUserId;
            }

            public int getRepairUserId() {
                return repairUserId;
            }

            public void setRepairUserId(int repairUserId) {
                this.repairUserId = repairUserId;
            }

            public String getOwnerName() {
                return ownerName == null ? "" : ownerName;
            }

            public void setOwnerName(String ownerName) {
                this.ownerName = ownerName;
            }

            public String getMobile() {
                return mobile == null ? "" : mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public int getGhsUserId() {
                return ghsUserId;
            }

            public void setGhsUserId(int ghsUserId) {
                this.ghsUserId = ghsUserId;
            }

            public int getServiceType() {
                return serviceType;
            }

            public void setServiceType(int serviceType) {
                this.serviceType = serviceType;
            }

            public int getState() {
                return state;
            }

            public void setState(int state) {
                this.state = state;
            }

            public String getContent() {
                return content == null ? "" : content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getImageUrl() {
                return imageUrl == null ? "" : imageUrl;
            }

            public void setImageUrl(String imageUrl) {
                this.imageUrl = imageUrl;
            }

            public String getHopeGotoTime() {
                return hopeGotoTime == null ? "" : hopeGotoTime;
            }

            public void setHopeGotoTime(String hopeGotoTime) {
                this.hopeGotoTime = hopeGotoTime;
            }

            public int getTemporaryTaskId() {
                return temporaryTaskId;
            }

            public void setTemporaryTaskId(int temporaryTaskId) {
                this.temporaryTaskId = temporaryTaskId;
            }

            public String getCreateTime() {
                return createTime == null ? "" : createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getUpdateTime() {
                return updateTime == null ? "" : updateTime;
            }

            public void setUpdateTime(String updateTime) {
                this.updateTime = updateTime;
            }

            public String getStartCreateTime() {
                return startCreateTime == null ? "" : startCreateTime;
            }

            public void setStartCreateTime(String startCreateTime) {
                this.startCreateTime = startCreateTime;
            }

            public String getEndCreateTime() {
                return endCreateTime == null ? "" : endCreateTime;
            }

            public void setEndCreateTime(String endCreateTime) {
                this.endCreateTime = endCreateTime;
            }

            public String getSysUserName() {
                return sysUserName == null ? "" : sysUserName;
            }

            public void setSysUserName(String sysUserName) {
                this.sysUserName = sysUserName;
            }

            public String getRepairUserName() {
                return repairUserName == null ? "" : repairUserName;
            }

            public void setRepairUserName(String repairUserName) {
                this.repairUserName = repairUserName;
            }

            public String getRepairUserMobile() {
                return repairUserMobile == null ? "" : repairUserMobile;
            }

            public void setRepairUserMobile(String repairUserMobile) {
                this.repairUserMobile = repairUserMobile;
            }

            public String getVillageMsg() {
                return villageMsg == null ? "" : villageMsg;
            }

            public void setVillageMsg(String villageMsg) {
                this.villageMsg = villageMsg;
            }

            public String getRoomNumber() {
                return roomNumber == null ? "" : roomNumber;
            }

            public void setRoomNumber(String roomNumber) {
                this.roomNumber = roomNumber;
            }

            public String getVillageName() {
                return villageName == null ? "" : villageName;
            }

            public void setVillageName(String villageName) {
                this.villageName = villageName;
            }

            public String getPortionName() {
                return portionName == null ? "" : portionName;
            }

            public void setPortionName(String portionName) {
                this.portionName = portionName;
            }

            public String getTowerNumber() {
                return towerNumber == null ? "" : towerNumber;
            }

            public void setTowerNumber(String towerNumber) {
                this.towerNumber = towerNumber;
            }

            public String getCellName() {
                return cellName == null ? "" : cellName;
            }

            public void setCellName(String cellName) {
                this.cellName = cellName;
            }

            public String getDistributeTime() {
                return distributeTime == null ? "" : distributeTime;
            }

            public void setDistributeTime(String distributeTime) {
                this.distributeTime = distributeTime;
            }

            public String getReceiveTime() {
                return receiveTime == null ? "" : receiveTime;
            }

            public void setReceiveTime(String receiveTime) {
                this.receiveTime = receiveTime;
            }

            public String getArriveTime() {
                return arriveTime == null ? "" : arriveTime;
            }

            public void setArriveTime(String arriveTime) {
                this.arriveTime = arriveTime;
            }

            public String getRepairMsg() {
                return repairMsg == null ? "" : repairMsg;
            }

            public void setRepairMsg(String repairMsg) {
                this.repairMsg = repairMsg;
            }

            public String getUseMaterial() {
                return useMaterial == null ? "" : useMaterial;
            }

            public void setUseMaterial(String useMaterial) {
                this.useMaterial = useMaterial;
            }

            public String getRemark() {
                return remark == null ? "" : remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public String getFinishJobTime() {
                return finishJobTime == null ? "" : finishJobTime;
            }

            public void setFinishJobTime(String finishJobTime) {
                this.finishJobTime = finishJobTime;
            }

            public int getRepairHour() {
                return repairHour;
            }

            public void setRepairHour(int repairHour) {
                this.repairHour = repairHour;
            }

            public int getRepairMinute() {
                return repairMinute;
            }

            public void setRepairMinute(int repairMinute) {
                this.repairMinute = repairMinute;
            }

            public String getEngineeringManager() {
                return engineeringManager == null ? "" : engineeringManager;
            }

            public void setEngineeringManager(String engineeringManager) {
                this.engineeringManager = engineeringManager;
            }

            public int getEvaluateArrive() {
                return evaluateArrive;
            }

            public void setEvaluateArrive(int evaluateArrive) {
                this.evaluateArrive = evaluateArrive;
            }

            public int getEvaluateAttitude() {
                return evaluateAttitude;
            }

            public void setEvaluateAttitude(int evaluateAttitude) {
                this.evaluateAttitude = evaluateAttitude;
            }

            public int getEvaluateQuality() {
                return evaluateQuality;
            }

            public void setEvaluateQuality(int evaluateQuality) {
                this.evaluateQuality = evaluateQuality;
            }

            public int getPaid() {
                return paid;
            }

            public void setPaid(int paid) {
                this.paid = paid;
            }

            public double getRepairMoney() {
                return repairMoney;
            }

            public void setRepairMoney(double repairMoney) {
                this.repairMoney = repairMoney;
            }

            public String getSuggest() {
                return suggest == null ? "" : suggest;
            }

            public void setSuggest(String suggest) {
                this.suggest = suggest;
            }
        }

        public static class StatusRecordListBean {
            /**
             * id : 4
             * serviceWorkId : 226
             * content : 物业跟进中
             * remark : null
             * imageUrl : null
             * createTime : 2019-05-23 13:49:59
             */

            private int id;
            private int serviceWorkId;
            private String content;
            private String remark;
            private String imageUrl;
            private String createTime;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getServiceWorkId() {
                return serviceWorkId;
            }

            public void setServiceWorkId(int serviceWorkId) {
                this.serviceWorkId = serviceWorkId;
            }

            public String getContent() {
                return content == null ? "" : content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getRemark() {
                return remark == null ? "" : remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public String getImageUrl() {
                return imageUrl == null ? "" : imageUrl;
            }

            public void setImageUrl(String imageUrl) {
                this.imageUrl = imageUrl;
            }

            public String getCreateTime() {
                return createTime == null ? "" : createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }
        }
    }
}
