package com.ghs.ghshome.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Author:wang_sir
 * Time:2018/7/26 13:33
 * Description:This is UserAndRoomBean
 */
public class UserAndRoomBean {

    /**
     * code : 1000
     * message : 成功
     * data : {"ghsUserDO":{"id":3075,"mobile":"13578790511","unionId":"om_iM1go3xLWkGaN0L1K2CGxKgoc","nickName":"乔布斯","fullName":"李昊","cardNum":null,"headImage":"201807271055023690000218.jpeg","carNum":null,"carInfo":null,"registerTime":"2018-05-29 14:41:22","updateTime":"2018-08-06 15:26:36","integral":289239203840,"loginFlag":"7c141eafd67d4a3c8e3c861b95ab2b10","token":null},"ghsUserRoomDO":{"id":3150,"ghsUserId":3075,"roomId":3159,"userState":1,"roleType":1,"leftKeyNum":46,"virtualKey":"38628c516a8a4b4ab9226b9edd161074","lockPassword":"85738499","showLockLog":1,"updateTime":"2018-08-06 03:00:00","fullName":"李昊","mobile":"13578790511","cardNum":null,"carNum":null,"carInfo":null,"headImage":null,"villageId":24,"villageName":"褐石公园小区","villageMsg":"褐石公园小区 - 一期 - 13号楼 - 1单元 - 602","portionId":8,"portionName":"一期","towerId":242,"towerNumber":"13号楼","cellId":335,"cellName":"1单元","roomNumber":"602","size":134,"excelResult":null}}
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
         * ghsUserDO : {"id":3075,"mobile":"13578790511","unionId":"om_iM1go3xLWkGaN0L1K2CGxKgoc","nickName":"乔布斯","fullName":"李昊","cardNum":null,"headImage":"201807271055023690000218.jpeg","carNum":null,"carInfo":null,"registerTime":"2018-05-29 14:41:22","updateTime":"2018-08-06 15:26:36","integral":289239203840,"loginFlag":"7c141eafd67d4a3c8e3c861b95ab2b10","token":null}
         * ghsUserRoomDO : {"id":3150,"ghsUserId":3075,"roomId":3159,"userState":1,"roleType":1,"leftKeyNum":46,"virtualKey":"38628c516a8a4b4ab9226b9edd161074","lockPassword":"85738499","showLockLog":1,"updateTime":"2018-08-06 03:00:00","fullName":"李昊","mobile":"13578790511","cardNum":null,"carNum":null,"carInfo":null,"headImage":null,"villageId":24,"villageName":"褐石公园小区","villageMsg":"褐石公园小区 - 一期 - 13号楼 - 1单元 - 602","portionId":8,"portionName":"一期","towerId":242,"towerNumber":"13号楼","cellId":335,"cellName":"1单元","roomNumber":"602","size":134,"excelResult":null}
         */

        private GhsUserDOBean ghsUserDO;
        private GhsUserRoomDOBean ghsUserRoomDO;

        public GhsUserDOBean getGhsUserDO() {
            return ghsUserDO;
        }

        public void setGhsUserDO(GhsUserDOBean ghsUserDO) {
            this.ghsUserDO = ghsUserDO;
        }

        public GhsUserRoomDOBean getGhsUserRoomDO() {
            return ghsUserRoomDO;
        }

        public void setGhsUserRoomDO(GhsUserRoomDOBean ghsUserRoomDO) {
            this.ghsUserRoomDO = ghsUserRoomDO;
        }

        public static class GhsUserDOBean {
            /**
             * id : 86446
             * mobile : 17568086930
             * unionId : null
             * nickName : 王斌测
             * fullName : 王斌
             * cardNum : null
             * headImage : 2019072918524021947311.jpeg
             * carNum : null
             * carInfo : null
             * registerTime : 2018-11-05 13:03:39
             * recommendUserId : null
             * updateTime : 2019-08-21 14:50:47
             * loginFlag : daa9db11055546efbef638ce36279364
             * gender : null
             * postion : null
             * urgentContactUser : null
             * urgentContactMobile : null
             * familyMemberMsg : null
             * remark : null
             * feedPet : null
             * hobby : null
             * workUnit : null
             * ufaceUserId : 360719877FB44BC3AEB3AA40D70DEC75
             * ufaceCreateTime : 2019-07-01 14:48:29
             * token : null
             */

            private int id;
            private String mobile;
            private String unionId;
            private String nickName;
            private String fullName;
            private String cardNum;
            private String headImage;
            private String carNum;
            private String carInfo;
            private String registerTime;
            private String headImageBackGroudColor;
            private int recommendUserId;
            private String updateTime;
            private String loginFlag;
            private int gender;
            private String postion;
            private String urgentContactUser;
            private String urgentContactMobile;
            private String familyMemberMsg;
            private String remark;
            private String feedPet;
            private String hobby;
            private String workUnit;
            private String ufaceUserId;
            private String ufaceCreateTime;
            private String token;

            public String getHeadImageBackGroudColor() {
                return headImageBackGroudColor == null ? "" : headImageBackGroudColor;
            }

            public void setHeadImageBackGroudColor(String headImageBackGroudColor) {
                this.headImageBackGroudColor = headImageBackGroudColor;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getMobile() {
                return mobile == null ? "" : mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getUnionId() {
                return unionId == null ? "" : unionId;
            }

            public void setUnionId(String unionId) {
                this.unionId = unionId;
            }

            public String getNickName() {
                return nickName == null ? "" : nickName;
            }

            public void setNickName(String nickName) {
                this.nickName = nickName;
            }

            public String getFullName() {
                return fullName == null ? "" : fullName;
            }

            public void setFullName(String fullName) {
                this.fullName = fullName;
            }

            public String getCardNum() {
                return cardNum == null ? "" : cardNum;
            }

            public void setCardNum(String cardNum) {
                this.cardNum = cardNum;
            }

            public String getHeadImage() {
                return headImage == null ? "" : headImage;
            }

            public void setHeadImage(String headImage) {
                this.headImage = headImage;
            }

            public String getCarNum() {
                return carNum == null ? "" : carNum;
            }

            public void setCarNum(String carNum) {
                this.carNum = carNum;
            }

            public String getCarInfo() {
                return carInfo == null ? "" : carInfo;
            }

            public void setCarInfo(String carInfo) {
                this.carInfo = carInfo;
            }

            public String getRegisterTime() {
                return registerTime == null ? "" : registerTime;
            }

            public void setRegisterTime(String registerTime) {
                this.registerTime = registerTime;
            }

            public int getRecommendUserId() {
                return recommendUserId;
            }

            public void setRecommendUserId(int recommendUserId) {
                this.recommendUserId = recommendUserId;
            }

            public String getUpdateTime() {
                return updateTime == null ? "" : updateTime;
            }

            public void setUpdateTime(String updateTime) {
                this.updateTime = updateTime;
            }

            public String getLoginFlag() {
                return loginFlag == null ? "" : loginFlag;
            }

            public void setLoginFlag(String loginFlag) {
                this.loginFlag = loginFlag;
            }

            public int getGender() {
                return gender;
            }

            public void setGender(int gender) {
                this.gender = gender;
            }

            public String getPostion() {
                return postion == null ? "" : postion;
            }

            public void setPostion(String postion) {
                this.postion = postion;
            }

            public String getUrgentContactUser() {
                return urgentContactUser == null ? "" : urgentContactUser;
            }

            public void setUrgentContactUser(String urgentContactUser) {
                this.urgentContactUser = urgentContactUser;
            }

            public String getUrgentContactMobile() {
                return urgentContactMobile == null ? "" : urgentContactMobile;
            }

            public void setUrgentContactMobile(String urgentContactMobile) {
                this.urgentContactMobile = urgentContactMobile;
            }

            public String getFamilyMemberMsg() {
                return familyMemberMsg == null ? "" : familyMemberMsg;
            }

            public void setFamilyMemberMsg(String familyMemberMsg) {
                this.familyMemberMsg = familyMemberMsg;
            }

            public String getRemark() {
                return remark == null ? "" : remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public String getFeedPet() {
                return feedPet == null ? "" : feedPet;
            }

            public void setFeedPet(String feedPet) {
                this.feedPet = feedPet;
            }

            public String getHobby() {
                return hobby == null ? "" : hobby;
            }

            public void setHobby(String hobby) {
                this.hobby = hobby;
            }

            public String getWorkUnit() {
                return workUnit == null ? "" : workUnit;
            }

            public void setWorkUnit(String workUnit) {
                this.workUnit = workUnit;
            }

            public String getUfaceUserId() {
                return ufaceUserId == null ? "" : ufaceUserId;
            }

            public void setUfaceUserId(String ufaceUserId) {
                this.ufaceUserId = ufaceUserId;
            }

            public String getUfaceCreateTime() {
                return ufaceCreateTime == null ? "" : ufaceCreateTime;
            }

            public void setUfaceCreateTime(String ufaceCreateTime) {
                this.ufaceCreateTime = ufaceCreateTime;
            }

            public String getToken() {
                return token == null ? "" : token;
            }

            public void setToken(String token) {
                this.token = token;
            }
        }

        public static class GhsUserRoomDOBean {
            /**
             * id : 93472
             * ghsUserId : 86446
             * roomId : 86129
             * userState : 1
             * roleType : 1
             * leftKeyNum : 27
             * virtualKey : 97c7f3c23c3f498a9388201cf02328a5
             * lockPassword : 23176191
             * showLockLog : 1
             * updateTime : 2019-08-21 14:49:12
             * createTime : 2018-11-05 13:03:39
             * fullName : 王斌
             * mobile : 17568086930
             * cardNum : null
             * carNum : null
             * carparkCarNum : null
             * carInfo : null
             * headImage : 2019072918524021947311.jpeg
             * roomType : 1
             * gender : null
             * postion : null
             * urgentContactUser : null
             * urgentContactMobile : null
             * familyMemberMsg :
             * familyName1 : null
             * familyGender1 : null
             * familyRelation1 : null
             * familyMobile1 : null
             * familyName2 : null
             * familyGender2 : null
             * familyRelation2 : null
             * familyMobile2 : null
             * familyName3 : null
             * familyGender3 : null
             * familyRelation3 : null
             * familyMobile3 : null
             * remark : null
             * feedPet : null
             * hobby : null
             * workUnit : null
             * ufaceUserId : 360719877FB44BC3AEB3AA40D70DEC75
             * ufaceCreateTime : 2019-07-01 14:48:29.0
             * ufacePhotoList : []
             * villageId : 27
             * villageName : 褐石公园小区（测试）
             * villageMsg : 褐石公园小区（测试）-二期-2号楼-2单元-102
             * portionId : 216
             * portionName : 二期
             * towerId : 518
             * towerNumber : 2号楼
             * cellId : 662
             * cellName : 2单元
             * roomNumber : 102
             * size : 121.0
             * nickName : 王斌测
             */
            private boolean isSelected;
            private int id;
            private int ghsUserId;
            private int roomId;
            private int userState;
            private int roleType;
            private int checkWay;
            private String virtualKey;
            private String lockPassword;
            private int showLockLog;
            private String updateTime;
            private String createTime;
            private String fullName;
            private String nickName;
            private String mobile;
            private String carparkCarNum;
            private String headImage;
            private int roomType;
            private String ufaceUserId;
            private String ufaceCreateTime;
            private int villageId;
            private String villageName;
            private String villageMsg;
            private int portionId;
            private String portionName;
            private int towerId;
            private String towerNumber;
            private int cellId;
            private String cellName;
            private String roomNumber;
            private double size;
            private List<?> ufacePhotoList;

            public boolean isSelected() {
                return isSelected;
            }

            public void setSelected(boolean selected) {
                isSelected = selected;
            }

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

            public int getRoomId() {
                return roomId;
            }

            public void setRoomId(int roomId) {
                this.roomId = roomId;
            }

            public int getUserState() {
                return userState;
            }

            public void setUserState(int userState) {
                this.userState = userState;
            }

            public int getRoleType() {
                return roleType;
            }

            public void setRoleType(int roleType) {
                this.roleType = roleType;
            }

            public int getCheckWay() {
                return checkWay;
            }

            public void setCheckWay(int checkWay) {
                this.checkWay = checkWay;
            }

            public String getVirtualKey() {
                return virtualKey == null ? "" : virtualKey;
            }

            public void setVirtualKey(String virtualKey) {
                this.virtualKey = virtualKey;
            }

            public String getLockPassword() {
                return lockPassword == null ? "" : lockPassword;
            }

            public void setLockPassword(String lockPassword) {
                this.lockPassword = lockPassword;
            }

            public int getShowLockLog() {
                return showLockLog;
            }

            public void setShowLockLog(int showLockLog) {
                this.showLockLog = showLockLog;
            }

            public String getUpdateTime() {
                return updateTime == null ? "" : updateTime;
            }

            public void setUpdateTime(String updateTime) {
                this.updateTime = updateTime;
            }

            public String getCreateTime() {
                return createTime == null ? "" : createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getFullName() {
                return fullName == null ? "" : fullName;
            }

            public void setFullName(String fullName) {
                this.fullName = fullName;
            }

            public String getNickName() {
                return nickName == null ? "" : nickName;
            }

            public void setNickName(String nickName) {
                this.nickName = nickName;
            }

            public String getMobile() {
                return mobile == null ? "" : mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getCarparkCarNum() {
                return carparkCarNum == null ? "" : carparkCarNum;
            }

            public void setCarparkCarNum(String carparkCarNum) {
                this.carparkCarNum = carparkCarNum;
            }

            public String getHeadImage() {
                return headImage == null ? "" : headImage;
            }

            public void setHeadImage(String headImage) {
                this.headImage = headImage;
            }

            public int getRoomType() {
                return roomType;
            }

            public void setRoomType(int roomType) {
                this.roomType = roomType;
            }

            public String getUfaceUserId() {
                return ufaceUserId == null ? "" : ufaceUserId;
            }

            public void setUfaceUserId(String ufaceUserId) {
                this.ufaceUserId = ufaceUserId;
            }

            public String getUfaceCreateTime() {
                return ufaceCreateTime == null ? "" : ufaceCreateTime;
            }

            public void setUfaceCreateTime(String ufaceCreateTime) {
                this.ufaceCreateTime = ufaceCreateTime;
            }

            public int getVillageId() {
                return villageId;
            }

            public void setVillageId(int villageId) {
                this.villageId = villageId;
            }

            public String getVillageName() {
                return villageName == null ? "" : villageName;
            }

            public void setVillageName(String villageName) {
                this.villageName = villageName;
            }

            public String getVillageMsg() {
                return villageMsg == null ? "" : villageMsg;
            }

            public void setVillageMsg(String villageMsg) {
                this.villageMsg = villageMsg;
            }

            public int getPortionId() {
                return portionId;
            }

            public void setPortionId(int portionId) {
                this.portionId = portionId;
            }

            public String getPortionName() {
                return portionName == null ? "" : portionName;
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
                return towerNumber == null ? "" : towerNumber;
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

            public double getSize() {
                return size;
            }

            public void setSize(double size) {
                this.size = size;
            }

            public List<?> getUfacePhotoList() {
                if (ufacePhotoList == null) {
                    return new ArrayList<>();
                }
                return ufacePhotoList;
            }

            public void setUfacePhotoList(List<?> ufacePhotoList) {
                this.ufacePhotoList = ufacePhotoList;
            }
        }
    }
}