package com.ghs.ghshome.bean;

import java.io.Serializable;
import java.util.List;

/**
 * created by guohainan
 * created date 2019/9/18
 * application
 * package name com.ghs.ghshome.bean
 */
public class TenementRoomBean {


    /**
     * code : 0
     * data : [{"carparkCarNum":"","cellId":0,"cellName":"","checkWay":0,"createTime":"","fullName":"","ghsUserId":0,"headImage":"","id":0,"lockPassword":"","mobile":"","nickName":"","portionId":0,"portionName":"","roleType":0,"roomId":0,"roomNumber":"","roomType":0,"showLockLog":0,"size":0,"towerId":0,"towerNumber":"","ufaceCreateTime":"","ufacePhotoList":[{}],"ufaceUserId":"","updateTime":"","userState":0,"villageId":0,"villageMsg":"","villageName":"","virtualKey":""}]
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

    public static class DataBean implements Serializable{
        /**
         * carparkCarNum :
         * cellId : 0
         * cellName :
         * checkWay : 0
         * createTime :
         * fullName :
         * ghsUserId : 0
         * headImage :
         * id : 0
         * lockPassword :
         * mobile :
         * nickName :
         * portionId : 0
         * portionName :
         * roleType : 0
         * roomId : 0
         * roomNumber :
         * roomType : 0
         * showLockLog : 0
         * size : 0
         * towerId : 0
         * towerNumber :
         * ufaceCreateTime :
         * ufacePhotoList : [{}]
         * ufaceUserId :
         * updateTime :
         * userState : 0
         * villageId : 0
         * villageMsg :
         * villageName :
         * virtualKey :
         */

        private String carparkCarNum;
        private int cellId;
        private String cellName;
        private int checkWay;
        private String createTime;
        private String fullName;
        private int ghsUserId;
        private String headImage;
        private int id;
        private String lockPassword;
        private String mobile;
        private String nickName;
        private int portionId;
        private String portionName;
        private int roleType;
        private int roomId;
        private String roomNumber;
        private int roomType;
        private int showLockLog;
        private int size;
        private int towerId;
        private String towerNumber;
        private String ufaceCreateTime;
        private String ufaceUserId;
        private String updateTime;
        private int userState;
        private int villageId;
        private String villageMsg;
        private String villageName;
        private String virtualKey;
        private List<UfacePhotoListBean> ufacePhotoList;

        public String getCarparkCarNum() {
            return carparkCarNum;
        }

        public void setCarparkCarNum(String carparkCarNum) {
            this.carparkCarNum = carparkCarNum;
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

        public int getCheckWay() {
            return checkWay;
        }

        public void setCheckWay(int checkWay) {
            this.checkWay = checkWay;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        public int getGhsUserId() {
            return ghsUserId;
        }

        public void setGhsUserId(int ghsUserId) {
            this.ghsUserId = ghsUserId;
        }

        public String getHeadImage() {
            return headImage;
        }

        public void setHeadImage(String headImage) {
            this.headImage = headImage;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getLockPassword() {
            return lockPassword;
        }

        public void setLockPassword(String lockPassword) {
            this.lockPassword = lockPassword;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
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

        public int getRoleType() {
            return roleType;
        }

        public void setRoleType(int roleType) {
            this.roleType = roleType;
        }

        public int getRoomId() {
            return roomId;
        }

        public void setRoomId(int roomId) {
            this.roomId = roomId;
        }

        public String getRoomNumber() {
            return roomNumber;
        }

        public void setRoomNumber(String roomNumber) {
            this.roomNumber = roomNumber;
        }

        public int getRoomType() {
            return roomType;
        }

        public void setRoomType(int roomType) {
            this.roomType = roomType;
        }

        public int getShowLockLog() {
            return showLockLog;
        }

        public void setShowLockLog(int showLockLog) {
            this.showLockLog = showLockLog;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
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

        public String getUfaceCreateTime() {
            return ufaceCreateTime;
        }

        public void setUfaceCreateTime(String ufaceCreateTime) {
            this.ufaceCreateTime = ufaceCreateTime;
        }

        public String getUfaceUserId() {
            return ufaceUserId;
        }

        public void setUfaceUserId(String ufaceUserId) {
            this.ufaceUserId = ufaceUserId;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public int getUserState() {
            return userState;
        }

        public void setUserState(int userState) {
            this.userState = userState;
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

        public String getVirtualKey() {
            return virtualKey;
        }

        public void setVirtualKey(String virtualKey) {
            this.virtualKey = virtualKey;
        }

        public List<UfacePhotoListBean> getUfacePhotoList() {
            return ufacePhotoList;
        }

        public void setUfacePhotoList(List<UfacePhotoListBean> ufacePhotoList) {
            this.ufacePhotoList = ufacePhotoList;
        }

        public static class UfacePhotoListBean {
        }
    }
}
