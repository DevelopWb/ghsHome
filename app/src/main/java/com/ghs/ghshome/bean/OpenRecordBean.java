package com.ghs.ghshome.bean;

import java.util.List;

/**
 * Author:wang_sir
 * Time:2018/7/3 14:16
 * Description:This is OpenRecordBean
 */
public class OpenRecordBean {
    /**
     * code : 1000
     * message : 成功
     * data : [{"dateTitle":"2018-06-14","logDOList":[{"id":4,"ghsUserId":3075,"villageId":24,"cellId":335,"roomId":3159,"deviceId":null,"macAddress":null,"deviceName":"11","deviceType":1,"openType":1,"cardNum":null,"fullName":"李昊","openState":1,"updateTime":"2018-06-14 18:03:55","headImage":"201806261517534560000848.jpeg","villageName":null,"portionId":null,"portionName":null,"towerId":null,"towerNumber":null,"cellName":null,"roomNumber":null}]}]
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
         * dateTitle : 2018-06-14
         * logDOList : [{"id":4,"ghsUserId":3075,"villageId":24,"cellId":335,"roomId":3159,"deviceId":null,"macAddress":null,"deviceName":"11","deviceType":1,"openType":1,"cardNum":null,"fullName":"李昊","openState":1,"updateTime":"2018-06-14 18:03:55","headImage":"201806261517534560000848.jpeg","villageName":null,"portionId":null,"portionName":null,"towerId":null,"towerNumber":null,"cellName":null,"roomNumber":null}]
         */

        private String dateTitle;
        private List<LogDOListBean> logDOList;

        public String getDateTitle() {
            return dateTitle;
        }

        public void setDateTitle(String dateTitle) {
            this.dateTitle = dateTitle;
        }

        public List<LogDOListBean> getLogDOList() {
            return logDOList;
        }

        public void setLogDOList(List<LogDOListBean> logDOList) {
            this.logDOList = logDOList;
        }

        public static class LogDOListBean {
            /**
             * id : 4
             * ghsUserId : 3075
             * villageId : 24
             * cellId : 335
             * roomId : 3159
             * deviceId : null
             * macAddress : null
             * deviceName : 11
             * deviceType : 1
             * openType : 1
             * cardNum : null
             * fullName : 李昊
             * openState : 1
             * updateTime : 2018-06-14 18:03:55
             * headImage : 201806261517534560000848.jpeg
             * villageName : null
             * portionId : null
             * portionName : null
             * towerId : null
             * towerNumber : null
             * cellName : null
             * roomNumber : null
             */

            private int id;
            private int ghsUserId;
            private int villageId;
            private int cellId;
            private int roomId;
            private Object deviceId;
            private Object macAddress;
            private String deviceName;
            private int deviceType;
            private int openType;
            private Object cardNum;
            private String fullName;
            private int openState;
            private String updateTime;
            private String headImage;
            private Object villageName;
            private Object portionId;
            private Object portionName;
            private Object towerId;
            private Object towerNumber;
            private Object cellName;
            private Object roomNumber;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getGhsUserId() {
                return ghsUserId;
            }

            public void setGhsUserId(int ghsUserId) {
                this.ghsUserId = ghsUserId;
            }

            public int getVillageId() {
                return villageId;
            }

            public void setVillageId(int villageId) {
                this.villageId = villageId;
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

            public Object getDeviceId() {
                return deviceId;
            }

            public void setDeviceId(Object deviceId) {
                this.deviceId = deviceId;
            }

            public Object getMacAddress() {
                return macAddress;
            }

            public void setMacAddress(Object macAddress) {
                this.macAddress = macAddress;
            }

            public String getDeviceName() {
                return deviceName;
            }

            public void setDeviceName(String deviceName) {
                this.deviceName = deviceName;
            }

            public int getDeviceType() {
                return deviceType;
            }

            public void setDeviceType(int deviceType) {
                this.deviceType = deviceType;
            }

            public int getOpenType() {
                return openType;
            }

            public void setOpenType(int openType) {
                this.openType = openType;
            }

            public Object getCardNum() {
                return cardNum;
            }

            public void setCardNum(Object cardNum) {
                this.cardNum = cardNum;
            }

            public String getFullName() {
                return fullName;
            }

            public void setFullName(String fullName) {
                this.fullName = fullName;
            }

            public int getOpenState() {
                return openState;
            }

            public void setOpenState(int openState) {
                this.openState = openState;
            }

            public String getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(String updateTime) {
                this.updateTime = updateTime;
            }

            public String getHeadImage() {
                return headImage;
            }

            public void setHeadImage(String headImage) {
                this.headImage = headImage;
            }

            public Object getVillageName() {
                return villageName;
            }

            public void setVillageName(Object villageName) {
                this.villageName = villageName;
            }

            public Object getPortionId() {
                return portionId;
            }

            public void setPortionId(Object portionId) {
                this.portionId = portionId;
            }

            public Object getPortionName() {
                return portionName;
            }

            public void setPortionName(Object portionName) {
                this.portionName = portionName;
            }

            public Object getTowerId() {
                return towerId;
            }

            public void setTowerId(Object towerId) {
                this.towerId = towerId;
            }

            public Object getTowerNumber() {
                return towerNumber;
            }

            public void setTowerNumber(Object towerNumber) {
                this.towerNumber = towerNumber;
            }

            public Object getCellName() {
                return cellName;
            }

            public void setCellName(Object cellName) {
                this.cellName = cellName;
            }

            public Object getRoomNumber() {
                return roomNumber;
            }

            public void setRoomNumber(Object roomNumber) {
                this.roomNumber = roomNumber;
            }
        }
    }
}
