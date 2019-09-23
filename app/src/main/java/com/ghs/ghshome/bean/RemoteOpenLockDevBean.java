package com.ghs.ghshome.bean;

import java.util.List;

/**
 * Author:wang_sir
 * Time:2018/8/19 15:01
 * Description:This is RemoteOpenLockDevBean  远程开门中 获取设备列表的实体类
 */
public class RemoteOpenLockDevBean {

    /**
     * code : 0
     * data : [{"apkManagerId":0,"apkUpdateTime":"","cellId":0,"cellName":"","createTime":"","deviceCode":"","deviceName":"","deviceState":0,"deviceType":0,"handler":"","id":0,"lastTime":"","lifeCycle":0,"macAddress":"","portionId":0,"portionName":"","putInCity":"","remark":"","signAddress":"","signTime":"","signUser":"","towerId":0,"towerNumber":"","updateTime":"","version":"","villageId":0,"villageMsg":"","villageName":""}]
     * message :
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
         * apkManagerId : 0
         * apkUpdateTime :
         * cellId : 0
         * cellName :
         * createTime :
         * deviceCode :
         * deviceName :
         * deviceState : 0
         * deviceType : 0
         * handler :
         * id : 0
         * lastTime :
         * lifeCycle : 0
         * macAddress :
         * portionId : 0
         * portionName :
         * putInCity :
         * remark :
         * signAddress :
         * signTime :
         * signUser :
         * towerId : 0
         * towerNumber :
         * updateTime :
         * version :
         * villageId : 0
         * villageMsg :
         * villageName :
         */

        private int apkManagerId;
        private String apkUpdateTime;
        private int cellId;
        private String cellName;
        private String createTime;
        private String deviceCode;
        private String deviceName;
        private int deviceState;
        private int deviceType;
        private String handler;
        private int id;
        private String lastTime;
        private int lifeCycle;
        private String macAddress;
        private int portionId;
        private String portionName;
        private String putInCity;
        private String remark;
        private String signAddress;
        private String signTime;
        private String signUser;
        private int towerId;
        private String towerNumber;
        private String updateTime;
        private String version;
        private int villageId;
        private String villageMsg;
        private String villageName;

        public int getApkManagerId() {
            return apkManagerId;
        }

        public void setApkManagerId(int apkManagerId) {
            this.apkManagerId = apkManagerId;
        }

        public String getApkUpdateTime() {
            return apkUpdateTime;
        }

        public void setApkUpdateTime(String apkUpdateTime) {
            this.apkUpdateTime = apkUpdateTime;
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

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getDeviceCode() {
            return deviceCode;
        }

        public void setDeviceCode(String deviceCode) {
            this.deviceCode = deviceCode;
        }

        public String getDeviceName() {
            return deviceName;
        }

        public void setDeviceName(String deviceName) {
            this.deviceName = deviceName;
        }

        public int getDeviceState() {
            return deviceState;
        }

        public void setDeviceState(int deviceState) {
            this.deviceState = deviceState;
        }

        public int getDeviceType() {
            return deviceType;
        }

        public void setDeviceType(int deviceType) {
            this.deviceType = deviceType;
        }

        public String getHandler() {
            return handler;
        }

        public void setHandler(String handler) {
            this.handler = handler;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getLastTime() {
            return lastTime;
        }

        public void setLastTime(String lastTime) {
            this.lastTime = lastTime;
        }

        public int getLifeCycle() {
            return lifeCycle;
        }

        public void setLifeCycle(int lifeCycle) {
            this.lifeCycle = lifeCycle;
        }

        public String getMacAddress() {
            return macAddress;
        }

        public void setMacAddress(String macAddress) {
            this.macAddress = macAddress;
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

        public String getPutInCity() {
            return putInCity;
        }

        public void setPutInCity(String putInCity) {
            this.putInCity = putInCity;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getSignAddress() {
            return signAddress;
        }

        public void setSignAddress(String signAddress) {
            this.signAddress = signAddress;
        }

        public String getSignTime() {
            return signTime;
        }

        public void setSignTime(String signTime) {
            this.signTime = signTime;
        }

        public String getSignUser() {
            return signUser;
        }

        public void setSignUser(String signUser) {
            this.signUser = signUser;
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

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public int getVillageId() {
            return villageId;
        }

        public void setVillageId(int villageId) {
            this.villageId = villageId;
        }

        public String getVillageMsg() {
            return villageMsg;
        }

        public void setVillageMsg(String villageMsg) {
            this.villageMsg = villageMsg;
        }

        public String getVillageName() {
            return villageName;
        }

        public void setVillageName(String villageName) {
            this.villageName = villageName;
        }
    }
}
