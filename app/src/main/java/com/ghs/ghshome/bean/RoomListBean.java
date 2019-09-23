package com.ghs.ghshome.bean;

import java.util.List;

/**
 * Author:wang_sir
 * Time:2018/7/13 14:20
 * Description:This is RoomListBean
 */
public class RoomListBean {

    /**
     * code : 1000
     * message : 成功
     * data : [{"id":105726,"ghsUserId":94478,"roomId":96797,"userState":1,"roleType":1,"leftKeyNum":30,"virtualKey":"70b860772f28456e88beaad7cd6ef003","lockPassword":"97705715","showLockLog":1,"updateTime":"2019-06-11 11:07:05","createTime":"2019-05-08 17:29:43","fullName":"刘士勋","mobile":"18630686124","cardNum":"123","carNum":null,"carparkCarNum":null,"carInfo":null,"headImage":"defaultHeadImage.jpg","roomType":1,"gender":1,"postion":"","urgentContactUser":"","urgentContactMobile":"","familyMemberMsg":"","familyName1":null,"familyGender1":null,"familyRelation1":null,"familyMobile1":null,"familyName2":null,"familyGender2":null,"familyRelation2":null,"familyMobile2":null,"familyName3":null,"familyGender3":null,"familyRelation3":null,"familyMobile3":null,"remark":"","feedPet":"","hobby":"","workUnit":"盖尔瑞孚汽车零部件","ufaceUserId":null,"ufaceCreateTime":null,"ufacePhotoList":[],"villageId":54,"villageName":"剑桥郡十三期","villageMsg":"剑桥郡十三期-一期-2-1-201","portionId":243,"portionName":"一期","towerId":832,"towerNumber":"2","cellId":1059,"cellName":"1","roomNumber":"201","size":96.4,"nickName":"用户091325"},{"id":101506,"ghsUserId":94478,"roomId":94156,"userState":1,"roleType":1,"leftKeyNum":29,"virtualKey":"ddcc6e4a8ea44e60bcb396d2f8c5575c","lockPassword":"28099857","showLockLog":1,"updateTime":"2019-06-10 03:00:45","createTime":"2018-12-11 15:15:33","fullName":"刘士勋","mobile":"18630686124","cardNum":"123","carNum":null,"carparkCarNum":null,"carInfo":null,"headImage":"defaultHeadImage.jpg","roomType":1,"gender":1,"postion":"","urgentContactUser":"","urgentContactMobile":"","familyMemberMsg":"","familyName1":null,"familyGender1":null,"familyRelation1":null,"familyMobile1":null,"familyName2":null,"familyGender2":null,"familyRelation2":null,"familyMobile2":null,"familyName3":null,"familyGender3":null,"familyRelation3":null,"familyMobile3":null,"remark":"","feedPet":"","hobby":"","workUnit":"盖尔瑞孚汽车零部件","ufaceUserId":null,"ufaceCreateTime":null,"ufacePhotoList":[],"villageId":27,"villageName":"褐石公园小区（测试）","villageMsg":"褐石公园小区（测试）-一期-1号楼-1单元-309","portionId":215,"portionName":"一期","towerId":517,"towerNumber":"1号楼","cellId":661,"cellName":"1单元","roomNumber":"309","size":500,"nickName":"用户091325"}]
     */

    private int code;
    private String message;
    private List<UserAndRoomBean.DataBean.GhsUserRoomDOBean> data;

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

    public List<UserAndRoomBean.DataBean.GhsUserRoomDOBean> getData() {
        return data;
    }

    public void setData(List<UserAndRoomBean.DataBean.GhsUserRoomDOBean> data) {
        this.data = data;
    }

}
