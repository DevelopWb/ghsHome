package com.ghs.ghshome.bean;

import java.util.List;

/**
 * Author:wang_sir
 * Time:2018/7/18 14:57
 * Description:This is MyBillInfolBean
 */
public class MyBillInfolBean {

    /**
     * code : 1000
     * message : 成功
     * data : [{"id":27,"type":"property","villageId":27,"orderNum":"2019060311115843012118","channelOrderNum":null,"roomId":86129,"ownerId":null,"title":"2018年3月份物业管理费","fee":0.01,"payMoney":0.01,"couponUserId":null,"payState":2,"payTime":null,"payType":null,"payUser":null,"createTime":"2019-06-03 11:11:58","updateTime":"2019-06-03 11:11:58","propertyId":30,"propertyName":"长春大禹物业公司","villageMsg":"二期-2号楼-2单元-102","villageName":"褐石公园小区（测试）","portionName":"二期","towerNumber":"2号楼","cellName":"2单元","roomNumber":"102","payUserName":null,"ownerName":"王斌"},{"id":28,"type":"property","villageId":27,"orderNum":"2019060311115846189310","channelOrderNum":null,"roomId":86129,"ownerId":null,"title":"7月-9月物业管理费","fee":0.01,"payMoney":0.01,"couponUserId":null,"payState":2,"payTime":null,"payType":null,"payUser":null,"createTime":"2019-06-03 11:11:58","updateTime":"2019-06-03 11:11:58","propertyId":30,"propertyName":"长春大禹物业公司","villageMsg":"二期-2号楼-2单元-102","villageName":"褐石公园小区（测试）","portionName":"二期","towerNumber":"2号楼","cellName":"2单元","roomNumber":"102","payUserName":null,"ownerName":"王斌"},{"id":29,"type":"property","villageId":27,"orderNum":"2019060311115849007253","channelOrderNum":null,"roomId":86129,"ownerId":null,"title":"2019年3月份物业管理费","fee":0.01,"payMoney":0.01,"couponUserId":null,"payState":2,"payTime":null,"payType":null,"payUser":null,"createTime":"2019-06-03 11:11:58","updateTime":"2019-06-03 11:11:58","propertyId":30,"propertyName":"长春大禹物业公司","villageMsg":"二期-2号楼-2单元-102","villageName":"褐石公园小区（测试）","portionName":"二期","towerNumber":"2号楼","cellName":"2单元","roomNumber":"102","payUserName":null,"ownerName":"王斌"},{"id":30,"type":"property","villageId":27,"orderNum":"2019060311115852089999","channelOrderNum":null,"roomId":86129,"ownerId":null,"title":"4月-5月物业管理费","fee":0.01,"payMoney":0.01,"couponUserId":null,"payState":2,"payTime":null,"payType":null,"payUser":null,"createTime":"2019-06-03 11:11:58","updateTime":"2019-06-03 11:12:10","propertyId":30,"propertyName":"长春大禹物业公司","villageMsg":"二期-2号楼-2单元-102","villageName":"褐石公园小区（测试）","portionName":"二期","towerNumber":"2号楼","cellName":"2单元","roomNumber":"102","payUserName":null,"ownerName":"王斌"}]
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
         * id : 27
         * type : property
         * villageId : 27
         * orderNum : 2019060311115843012118
         * channelOrderNum : null
         * roomId : 86129
         * ownerId : null
         * title : 2018年3月份物业管理费
         * fee : 0.01
         * payMoney : 0.01
         * couponUserId : null
         * payState : 2
         * payTime : null
         * payType : null
         * payUser : null
         * createTime : 2019-06-03 11:11:58
         * updateTime : 2019-06-03 11:11:58
         * propertyId : 30
         * propertyName : 长春大禹物业公司
         * villageMsg : 二期-2号楼-2单元-102
         * villageName : 褐石公园小区（测试）
         * portionName : 二期
         * towerNumber : 2号楼
         * cellName : 2单元
         * roomNumber : 102
         * payUserName : null
         * ownerName : 王斌
         */

        private int id;
        private String type;
        private int villageId;
        private String orderNum;
        private String channelOrderNum;
        private int roomId;
        private int position;
        private int ownerId;
        private String title;
        private double fee;
        private double payMoney;
        private int couponUserId;
        private int payState;
        private String payTime;
        private int payType;
        private int payUser;
        private String createTime;
        private String updateTime;
        private int propertyId;
        private String propertyName;
        private String villageMsg;
        private String villageName;
        private String portionName;
        private String towerNumber;
        private String cellName;
        private String roomNumber;
        private String payUserName;
        private String ownerName;

        public int getId() {
            return id;
        }

        public int getPosition() {
            return position;
        }

        public void setPosition(int position) {
            this.position = position;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getType() {
            return type == null ? "" : type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getVillageId() {
            return villageId;
        }

        public void setVillageId(int villageId) {
            this.villageId = villageId;
        }

        public String getOrderNum() {
            return orderNum == null ? "" : orderNum;
        }

        public void setOrderNum(String orderNum) {
            this.orderNum = orderNum;
        }

        public String getChannelOrderNum() {
            return channelOrderNum == null ? "" : channelOrderNum;
        }

        public void setChannelOrderNum(String channelOrderNum) {
            this.channelOrderNum = channelOrderNum;
        }

        public int getRoomId() {
            return roomId;
        }

        public void setRoomId(int roomId) {
            this.roomId = roomId;
        }

        public int getOwnerId() {
            return ownerId;
        }

        public void setOwnerId(int ownerId) {
            this.ownerId = ownerId;
        }

        public String getTitle() {
            return title == null ? "" : title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public double getFee() {
            return fee;
        }

        public void setFee(double fee) {
            this.fee = fee;
        }

        public double getPayMoney() {
            return payMoney;
        }

        public void setPayMoney(double payMoney) {
            this.payMoney = payMoney;
        }

        public int getCouponUserId() {
            return couponUserId;
        }

        public void setCouponUserId(int couponUserId) {
            this.couponUserId = couponUserId;
        }

        public int getPayState() {
            return payState;
        }

        public void setPayState(int payState) {
            this.payState = payState;
        }

        public String getPayTime() {
            return payTime == null ? "" : payTime;
        }

        public void setPayTime(String payTime) {
            this.payTime = payTime;
        }

        public int getPayType() {
            return payType;
        }

        public void setPayType(int payType) {
            this.payType = payType;
        }

        public int getPayUser() {
            return payUser;
        }

        public void setPayUser(int payUser) {
            this.payUser = payUser;
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

        public String getVillageMsg() {
            return villageMsg == null ? "" : villageMsg;
        }

        public void setVillageMsg(String villageMsg) {
            this.villageMsg = villageMsg;
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

        public String getRoomNumber() {
            return roomNumber == null ? "" : roomNumber;
        }

        public void setRoomNumber(String roomNumber) {
            this.roomNumber = roomNumber;
        }

        public String getPayUserName() {
            return payUserName == null ? "" : payUserName;
        }

        public void setPayUserName(String payUserName) {
            this.payUserName = payUserName;
        }

        public String getOwnerName() {
            return ownerName == null ? "" : ownerName;
        }

        public void setOwnerName(String ownerName) {
            this.ownerName = ownerName;
        }
    }
}