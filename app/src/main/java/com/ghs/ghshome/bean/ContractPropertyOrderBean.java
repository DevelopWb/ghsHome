package com.ghs.ghshome.bean;

import java.util.List;

/**
 * Author:wang_sir
 * Time:2018/7/5 16:13
 * Description:This is ContractPropertyOrderBean  联系物业中的订单
 */
public class ContractPropertyOrderBean {


    /**
     * code : 1000
     * message : 成功
     * data : [{"id":2,"propertyId":1,"sysUserId":1,"sysUserName":null,"roomId":3159,"ownerType":2,"ownerName":"业主家人","mobile":"123456","ghsUserId":3075,"serviceType":3,"state":2,"content":"长期不住，物业费降低额度","star":3,"createTime":"2018-04-20 12:00:43","finishTime":"2018-04-20 12:00:43","updateTime":"2018-04-20 15:57:59","startCreateTime":null,"endCreateTime":null,"villageMsg":"褐石公园小区 -- 一期 -- 13号楼 -- 1单元 -- 602","roomNumber":"602","villageId":24,"villageName":"褐石公园小区","portionId":8,"portionName":"一期","towerId":242,"towerNumber":"13号楼","cellId":335,"cellName":"1单元"},{"id":1,"propertyId":1,"sysUserId":1,"sysUserName":null,"roomId":3159,"ownerType":3,"ownerName":"普通租客一枚","mobile":"12345678912","ghsUserId":3075,"serviceType":2,"state":2,"content":"单元门打不开呀！弄了好半天！","star":4,"createTime":"2018-04-19 14:10:22","finishTime":null,"updateTime":"2018-06-15 16:57:18","startCreateTime":null,"endCreateTime":null,"villageMsg":"褐石公园小区 -- 一期 -- 13号楼 -- 1单元 -- 602","roomNumber":"602","villageId":24,"villageName":"褐石公园小区","portionId":8,"portionName":"一期","towerId":242,"towerNumber":"13号楼","cellId":335,"cellName":"1单元"}]
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
         * id : 2
         * propertyId : 1
         * sysUserId : 1
         * sysUserName : null
         * roomId : 3159
         * ownerType : 2
         * ownerName : 业主家人
         * mobile : 123456
         * ghsUserId : 3075
         * serviceType : 3
         * state : 2
         * content : 长期不住，物业费降低额度
         * star : 3
         * createTime : 2018-04-20 12:00:43
         * finishTime : 2018-04-20 12:00:43
         * updateTime : 2018-04-20 15:57:59
         * startCreateTime : null
         * endCreateTime : null
         * villageMsg : 褐石公园小区 -- 一期 -- 13号楼 -- 1单元 -- 602
         * roomNumber : 602
         * villageId : 24
         * villageName : 褐石公园小区
         * portionId : 8
         * portionName : 一期
         * towerId : 242
         * towerNumber : 13号楼
         * cellId : 335
         * cellName : 1单元
         */

        private int id;
        private int propertyId;
        private int sysUserId;
        private Object sysUserName;
        private int roomId;
        private int ownerType;
        private String ownerName;
        private String mobile;
        private int ghsUserId;
        private int serviceType;
        private int state;
        private String content;
        private int star;
        private String createTime;
        private String finishTime;
        private String updateTime;
        private Object startCreateTime;
        private Object endCreateTime;
        private String villageMsg;
        private String roomNumber;
        private int villageId;
        private String villageName;
        private int portionId;
        private String portionName;
        private int towerId;
        private String towerNumber;
        private int cellId;
        private String cellName;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getPropertyId() {
            return propertyId;
        }

        public void setPropertyId(int propertyId) {
            this.propertyId = propertyId;
        }

        public int getSysUserId() {
            return sysUserId;
        }

        public void setSysUserId(int sysUserId) {
            this.sysUserId = sysUserId;
        }

        public Object getSysUserName() {
            return sysUserName;
        }

        public void setSysUserName(Object sysUserName) {
            this.sysUserName = sysUserName;
        }

        public int getRoomId() {
            return roomId;
        }

        public void setRoomId(int roomId) {
            this.roomId = roomId;
        }

        public int getOwnerType() {
            return ownerType;
        }

        public void setOwnerType(int ownerType) {
            this.ownerType = ownerType;
        }

        public String getOwnerName() {
            return ownerName;
        }

        public void setOwnerName(String ownerName) {
            this.ownerName = ownerName;
        }

        public String getMobile() {
            return mobile;
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
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getStar() {
            return star;
        }

        public void setStar(int star) {
            this.star = star;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getFinishTime() {
            return finishTime;
        }

        public void setFinishTime(String finishTime) {
            this.finishTime = finishTime;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public Object getStartCreateTime() {
            return startCreateTime;
        }

        public void setStartCreateTime(Object startCreateTime) {
            this.startCreateTime = startCreateTime;
        }

        public Object getEndCreateTime() {
            return endCreateTime;
        }

        public void setEndCreateTime(Object endCreateTime) {
            this.endCreateTime = endCreateTime;
        }

        public String getVillageMsg() {
            return villageMsg;
        }

        public void setVillageMsg(String villageMsg) {
            this.villageMsg = villageMsg;
        }

        public String getRoomNumber() {
            return roomNumber;
        }

        public void setRoomNumber(String roomNumber) {
            this.roomNumber = roomNumber;
        }

        public int getVillageId() {
            return villageId;
        }

        public void setVillageId(int villageId) {
            this.villageId = villageId;
        }

        public String getVillageName() {
            return villageName;
        }

        public void setVillageName(String villageName) {
            this.villageName = villageName;
        }

        public int getPortionId() {
            return portionId;
        }

        public void setPortionId(int portionId) {
            this.portionId = portionId;
        }

        public String getPortionName() {
            return portionName;
        }

        public void setPortionName(String portionName) {
            this.portionName = portionName;
        }

        public int getTowerId() {
            return towerId;
        }

        public void setTowerId(int towerId) {
            this.towerId = towerId;
        }

        public String getTowerNumber() {
            return towerNumber;
        }

        public void setTowerNumber(String towerNumber) {
            this.towerNumber = towerNumber;
        }

        public int getCellId() {
            return cellId;
        }

        public void setCellId(int cellId) {
            this.cellId = cellId;
        }

        public String getCellName() {
            return cellName;
        }

        public void setCellName(String cellName) {
            this.cellName = cellName;
        }
    }
}
