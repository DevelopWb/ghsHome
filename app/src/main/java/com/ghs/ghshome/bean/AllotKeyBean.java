package com.ghs.ghshome.bean;

import java.util.List;

/**
 * Author:wang_sir
 * Time:2018/7/5 11:35
 * Description:This is AllotKeyBean
 */
public class AllotKeyBean {


    /**
     * code : 1000
     * data : [{"cardNum":"123","cellId":1059,"cellName":"1","createTime":"2019-05-08 17:29:43","familyMemberMsg":"","feedPet":"","fullName":"刘士勋","gender":1,"ghsUserId":94478,"headImage":"defaultHeadImage.jpg","hobby":"","id":105726,"leftKeyNum":29,"lockPassword":"97705715","mobile":"18630686124","nickName":"刘","portionId":243,"portionName":"一期","postion":"","remark":"","roleType":1,"roomId":96797,"roomNumber":"201","roomType":1,"showLockLog":1,"size":96.4,"towerId":832,"towerNumber":"2","ufacePhotoList":[],"updateTime":"2019-06-12 14:56:33","urgentContactMobile":"","urgentContactUser":"","userState":1,"villageId":54,"villageMsg":"剑桥郡十三期-一期-2-1-201","villageName":"剑桥郡十三期","virtualKey":"70b860772f28456e88beaad7cd6ef003","workUnit":"盖尔瑞孚汽车零部件"},{"cellId":1059,"cellName":"1","createTime":"2019-06-12 14:56:33","familyMemberMsg":"","fullName":"123","ghsUserId":98101,"headImage":"defaultHeadImage.jpg","id":105919,"leftKeyNum":1,"lockPassword":"40207691","mobile":"12345678901","nickName":"123","portionId":243,"portionName":"一期","roleType":3,"roomId":96797,"roomNumber":"201","roomType":1,"showLockLog":1,"size":96.4,"towerId":832,"towerNumber":"2","ufacePhotoList":[],"updateTime":"2019-06-12 14:56:33","userState":1,"villageId":54,"villageMsg":"剑桥郡十三期-一期-2-1-201","villageName":"剑桥郡十三期","virtualKey":"0b3b84fa204f468cb0e1e9f572de8d83"}]
     * message : 成功
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
         * cardNum : 123
         * cellId : 1059
         * cellName : 1
         * createTime : 2019-05-08 17:29:43
         * familyMemberMsg :
         * feedPet :
         * fullName : 刘士勋
         * gender : 1
         * ghsUserId : 94478
         * headImage : defaultHeadImage.jpg
         * hobby :
         * id : 105726
         * leftKeyNum : 29
         * lockPassword : 97705715
         * mobile : 18630686124
         * nickName : 刘
         * portionId : 243
         * portionName : 一期
         * postion :
         * remark :
         * roleType : 1
         * roomId : 96797
         * roomNumber : 201
         * roomType : 1
         * showLockLog : 1
         * size : 96.4
         * towerId : 832
         * towerNumber : 2
         * ufacePhotoList : []
         * updateTime : 2019-06-12 14:56:33
         * urgentContactMobile :
         * urgentContactUser :
         * userState : 1
         * villageId : 54
         * villageMsg : 剑桥郡十三期-一期-2-1-201
         * villageName : 剑桥郡十三期
         * virtualKey : 70b860772f28456e88beaad7cd6ef003
         * workUnit : 盖尔瑞孚汽车零部件
         */

        private String cardNum;
        private int cellId;
        private String cellName;
        private String createTime;
        private String familyMemberMsg;
        private String feedPet;
        private String fullName;
        private int gender;
        private int ghsUserId;
        private String headImage;
        private String hobby;
        private int id;
        private int leftKeyNum;
        private String lockPassword;
        private String mobile;
        private String nickName;
        private int portionId;
        private String portionName;
        private String postion;
        private String remark;
        private int roleType;
        private int roomId;
        private String roomNumber;
        private int roomType;
        private int showLockLog;
        private double size;
        private int towerId;
        private String towerNumber;
        private String updateTime;
        private String urgentContactMobile;
        private String urgentContactUser;
        private int userState;
        private int villageId;
        private String villageMsg;
        private String villageName;
        private String virtualKey;
        private String workUnit;
        private List<?> ufacePhotoList;

        public String getCardNum() {
            return cardNum;
        }

        public void setCardNum(String cardNum) {
            this.cardNum = cardNum;
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

        public String getFamilyMemberMsg() {
            return familyMemberMsg;
        }

        public void setFamilyMemberMsg(String familyMemberMsg) {
            this.familyMemberMsg = familyMemberMsg;
        }

        public String getFeedPet() {
            return feedPet;
        }

        public void setFeedPet(String feedPet) {
            this.feedPet = feedPet;
        }

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        public int getGender() {
            return gender;
        }

        public void setGender(int gender) {
            this.gender = gender;
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

        public String getHobby() {
            return hobby;
        }

        public void setHobby(String hobby) {
            this.hobby = hobby;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getLeftKeyNum() {
            return leftKeyNum;
        }

        public void setLeftKeyNum(int leftKeyNum) {
            this.leftKeyNum = leftKeyNum;
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

        public String getPostion() {
            return postion;
        }

        public void setPostion(String postion) {
            this.postion = postion;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
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

        public double getSize() {
            return size;
        }

        public void setSize(double size) {
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

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public String getUrgentContactMobile() {
            return urgentContactMobile;
        }

        public void setUrgentContactMobile(String urgentContactMobile) {
            this.urgentContactMobile = urgentContactMobile;
        }

        public String getUrgentContactUser() {
            return urgentContactUser;
        }

        public void setUrgentContactUser(String urgentContactUser) {
            this.urgentContactUser = urgentContactUser;
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

        public String getWorkUnit() {
            return workUnit;
        }

        public void setWorkUnit(String workUnit) {
            this.workUnit = workUnit;
        }

        public List<?> getUfacePhotoList() {
            return ufacePhotoList;
        }

        public void setUfacePhotoList(List<?> ufacePhotoList) {
            this.ufacePhotoList = ufacePhotoList;
        }
    }
}
