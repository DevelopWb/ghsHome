package com.ghs.ghshome.bean;

import java.util.List;

/**
 * Author:wang_sir
 * Time:2019/5/23 15:42
 * Description:This is WorkOrderBean
 */
public class WorkOrderBean {
    /**
     * code : 1000
     * message : 成功
     * data : [{"id":226,"source":2,"propertyId":30,"villageId":27,"portionId":215,"towerId":521,"cellId":683,"roomId":null,"sysUserId":42,"repairUserId":42,"ownerName":"明申","mobile":"15890086993","ghsUserId":86445,"serviceType":5,"state":1,"content":"明申测试","imageUrl":null,"hopeGotoTime":null,"temporaryTaskId":585,"createTime":"2019-05-23 13:49:59","updateTime":"2019-05-23 13:49:59","startCreateTime":null,"endCreateTime":null,"sysUserName":"龙洋","repairUserName":"龙洋","repairUserMobile":"18500975534","villageMsg":"褐石公园小区（测试） -一期 -2号楼 -7单元","roomNumber":null,"villageName":"褐石公园小区（测试）","portionName":"一期","towerNumber":"2号楼","cellName":"7单元","distributeTime":null,"receiveTime":null,"arriveTime":null,"repairMsg":null,"useMaterial":null,"remark":null,"finishJobTime":null,"repairHour":null,"repairMinute":null,"engineeringManager":null,"evaluateArrive":null,"evaluateAttitude":null,"evaluateQuality":null,"paid":null,"repairMoney":null,"suggest":null},{"id":88,"source":2,"propertyId":30,"villageId":27,"portionId":215,"towerId":521,"cellId":null,"roomId":null,"sysUserId":null,"repairUserId":40,"ownerName":"tly","mobile":"18500975534","ghsUserId":86445,"serviceType":5,"state":1,"content":"录入信息啦","imageUrl":null,"hopeGotoTime":null,"temporaryTaskId":437,"createTime":"2019-04-25 15:27:13","updateTime":"2019-04-25 15:27:13","startCreateTime":null,"endCreateTime":null,"sysUserName":null,"repairUserName":"郭海楠","repairUserMobile":"18810891140","villageMsg":"褐石公园小区（测试） -一期 -2号楼","roomNumber":null,"villageName":"褐石公园小区（测试）","portionName":"一期","towerNumber":"2号楼","cellName":null,"distributeTime":null,"receiveTime":null,"arriveTime":null,"repairMsg":null,"useMaterial":null,"remark":null,"finishJobTime":null,"repairHour":null,"repairMinute":null,"engineeringManager":null,"evaluateArrive":null,"evaluateAttitude":null,"evaluateQuality":null,"paid":null,"repairMoney":null,"suggest":null},{"id":84,"source":2,"propertyId":30,"villageId":27,"portionId":216,"towerId":518,"cellId":662,"roomId":86132,"sysUserId":42,"repairUserId":19,"ownerName":"业主姓名","mobile":"13051862092","ghsUserId":86445,"serviceType":5,"state":1,"content":"明申呼叫中心任务","imageUrl":null,"hopeGotoTime":null,"temporaryTaskId":434,"createTime":"2019-04-24 17:40:58","updateTime":"2019-04-24 17:40:58","startCreateTime":null,"endCreateTime":null,"sysUserName":"龙洋","repairUserName":"王明申","repairUserMobile":"15890086993","villageMsg":"褐石公园小区（测试） -二期 -2号楼 -2单元 -105","roomNumber":"105","villageName":"褐石公园小区（测试）","portionName":"二期","towerNumber":"2号楼","cellName":"2单元","distributeTime":null,"receiveTime":null,"arriveTime":null,"repairMsg":null,"useMaterial":null,"remark":null,"finishJobTime":null,"repairHour":null,"repairMinute":null,"engineeringManager":null,"evaluateArrive":null,"evaluateAttitude":null,"evaluateQuality":null,"paid":null,"repairMoney":null,"suggest":null},{"id":83,"source":2,"propertyId":30,"villageId":27,"portionId":215,"towerId":517,"cellId":767,"roomId":95994,"sysUserId":42,"repairUserId":40,"ownerName":"海楠","mobile":"13051862092","ghsUserId":86445,"serviceType":5,"state":2,"content":"海南物管后台临时任务装，走廊灯坏了，修理一下","imageUrl":null,"hopeGotoTime":null,"temporaryTaskId":433,"createTime":"2019-04-24 17:40:11","updateTime":"2019-05-10 10:41:51","startCreateTime":null,"endCreateTime":null,"sysUserName":"龙洋","repairUserName":"郭海楠","repairUserMobile":"18810891140","villageMsg":"褐石公园小区（测试） -一期 -1号楼 -20单元 -1201","roomNumber":"1201","villageName":"褐石公园小区（测试）","portionName":"一期","towerNumber":"1号楼","cellName":"20单元","distributeTime":null,"receiveTime":null,"arriveTime":null,"repairMsg":null,"useMaterial":null,"remark":null,"finishJobTime":null,"repairHour":null,"repairMinute":null,"engineeringManager":null,"evaluateArrive":null,"evaluateAttitude":null,"evaluateQuality":null,"paid":null,"repairMoney":null,"suggest":null}]
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
         * id : 226
         * source : 2
         * propertyId : 30
         * villageId : 27
         * portionId : 215
         * towerId : 521
         * cellId : 683
         * roomId : null
         * sysUserId : 42
         * repairUserId : 42
         * ownerName : 明申
         * mobile : 15890086993
         * ghsUserId : 86445
         * serviceType : 5
         * state : 1
         * content : 明申测试
         * imageUrl : null
         * hopeGotoTime : null
         * temporaryTaskId : 585
         * createTime : 2019-05-23 13:49:59
         * updateTime : 2019-05-23 13:49:59
         * startCreateTime : null
         * endCreateTime : null
         * sysUserName : 龙洋
         * repairUserName : 龙洋
         * repairUserMobile : 18500975534
         * villageMsg : 褐石公园小区（测试） -一期 -2号楼 -7单元
         * roomNumber : null
         * villageName : 褐石公园小区（测试）
         * portionName : 一期
         * towerNumber : 2号楼
         * cellName : 7单元
         * distributeTime : null
         * receiveTime : null
         * arriveTime : null
         * repairMsg : null
         * useMaterial : null
         * remark : null
         * finishJobTime : null
         * repairHour : null
         * repairMinute : null
         * engineeringManager : null
         * evaluateArrive : null
         * evaluateAttitude : null
         * evaluateQuality : null
         * paid : null
         * repairMoney : null
         * suggest : null
         */

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
}
