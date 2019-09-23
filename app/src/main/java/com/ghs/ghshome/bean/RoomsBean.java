package com.ghs.ghshome.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Author:wang_sir
 * Time:2019/9/16 15:19
 * Description:This is RoomsBean  身份认证中的 房间列表
 */
public class RoomsBean {

    /**
     * code : 1000
     * message : 成功
     * data : [{"id":96976,"propertyId":null,"cellId":1070,"roomNumber":"201","size":120,"roomType":1,"empty":null,"handRoom":null,"decoration":null,"roomShape":null,"remark":null,"createTime":"2019-07-23 17:42:07","updateTime":"2019-07-23 17:42:07","cellName":"2单元","towerId":517,"towerNumber":"1号楼","portionId":215,"portionName":"一期","villageId":27,"villageName":"褐石公园小区（测试）","villageMsg":"褐石公园小区（测试）-一期-1号楼-2单元-201","ownerName":null,"userNum":null}]
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

    public static class DataBean implements Parcelable {
        /**
         * id : 96976
         * propertyId : null
         * cellId : 1070
         * roomNumber : 201
         * size : 120.0
         * roomType : 1
         * empty : null
         * handRoom : null
         * decoration : null
         * roomShape : null
         * remark : null
         * createTime : 2019-07-23 17:42:07
         * updateTime : 2019-07-23 17:42:07
         * cellName : 2单元
         * towerId : 517
         * towerNumber : 1号楼
         * portionId : 215
         * portionName : 一期
         * villageId : 27
         * villageName : 褐石公园小区（测试）
         * villageMsg : 褐石公园小区（测试）-一期-1号楼-2单元-201
         * ownerName : null
         * userNum : null
         */

        private int id;
        private int propertyId;
        private int cellId;
        private String roomNumber;
        private double size;
        private int roomType;
        private int empty;
        private int handRoom;
        private int decoration;
        private String roomShape;
        private String remark;
        private String createTime;
        private String updateTime;
        private String cellName;
        private int towerId;
        private String towerNumber;
        private int portionId;
        private String portionName;
        private int villageId;
        private String villageName;
        private String villageMsg;
        private String ownerName;
        private int userNum;

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

        public int getCellId() {
            return cellId;
        }

        public void setCellId(int cellId) {
            this.cellId = cellId;
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

        public int getRoomType() {
            return roomType;
        }

        public void setRoomType(int roomType) {
            this.roomType = roomType;
        }

        public int getEmpty() {
            return empty;
        }

        public void setEmpty(int empty) {
            this.empty = empty;
        }

        public int getHandRoom() {
            return handRoom;
        }

        public void setHandRoom(int handRoom) {
            this.handRoom = handRoom;
        }

        public int getDecoration() {
            return decoration;
        }

        public void setDecoration(int decoration) {
            this.decoration = decoration;
        }

        public String getRoomShape() {
            return roomShape == null ? "" : roomShape;
        }

        public void setRoomShape(String roomShape) {
            this.roomShape = roomShape;
        }

        public String getRemark() {
            return remark == null ? "" : remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
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

        public String getCellName() {
            return cellName == null ? "" : cellName;
        }

        public void setCellName(String cellName) {
            this.cellName = cellName;
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

        public String getOwnerName() {
            return ownerName == null ? "" : ownerName;
        }

        public void setOwnerName(String ownerName) {
            this.ownerName = ownerName;
        }

        public int getUserNum() {
            return userNum;
        }

        public void setUserNum(int userNum) {
            this.userNum = userNum;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.id);
            dest.writeInt(this.propertyId);
            dest.writeInt(this.cellId);
            dest.writeString(this.roomNumber);
            dest.writeDouble(this.size);
            dest.writeInt(this.roomType);
            dest.writeInt(this.empty);
            dest.writeInt(this.handRoom);
            dest.writeInt(this.decoration);
            dest.writeString(this.roomShape);
            dest.writeString(this.remark);
            dest.writeString(this.createTime);
            dest.writeString(this.updateTime);
            dest.writeString(this.cellName);
            dest.writeInt(this.towerId);
            dest.writeString(this.towerNumber);
            dest.writeInt(this.portionId);
            dest.writeString(this.portionName);
            dest.writeInt(this.villageId);
            dest.writeString(this.villageName);
            dest.writeString(this.villageMsg);
            dest.writeString(this.ownerName);
            dest.writeInt(this.userNum);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.id = in.readInt();
            this.propertyId = in.readInt();
            this.cellId = in.readInt();
            this.roomNumber = in.readString();
            this.size = in.readDouble();
            this.roomType = in.readInt();
            this.empty = in.readInt();
            this.handRoom = in.readInt();
            this.decoration = in.readInt();
            this.roomShape = in.readString();
            this.remark = in.readString();
            this.createTime = in.readString();
            this.updateTime = in.readString();
            this.cellName = in.readString();
            this.towerId = in.readInt();
            this.towerNumber = in.readString();
            this.portionId = in.readInt();
            this.portionName = in.readString();
            this.villageId = in.readInt();
            this.villageName = in.readString();
            this.villageMsg = in.readString();
            this.ownerName = in.readString();
            this.userNum = in.readInt();
        }

        public static final Parcelable.Creator<DataBean> CREATOR = new Parcelable.Creator<DataBean>() {
            @Override
            public DataBean createFromParcel(Parcel source) {
                return new DataBean(source);
            }

            @Override
            public DataBean[] newArray(int size) {
                return new DataBean[size];
            }
        };
    }
}
