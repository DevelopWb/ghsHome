package com.ghs.ghshome.bean;

import java.util.List;

/**
 * created by guohainan
 * created date 2019/6/18
 * application
 * package name com.ghs.ghshome.bean
 */
public class FaceUfaceUserInfoBean {


    /**
     * code : 0
     * data : {"state":0,"ufaceDeviceDOList":[{"cellId":0,"createTime":"","deviceKey":"","deviceName":"","deviceType":0,"id":0,"propertyId":0,"propertyName":"","right":0,"state":0,"status":0,"updateTime":"","versionNo":"","villageId":0,"villageName":""}]}
     * message :
     */

    private int code;
    private DataBean data;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class DataBean {
        /**
         * state : 0
         * ufaceDeviceDOList : [{"cellId":0,"createTime":"","deviceKey":"","deviceName":"","deviceType":0,"id":0,"propertyId":0,"propertyName":"","right":0,"state":0,"status":0,"updateTime":"","versionNo":"","villageId":0,"villageName":""}]
         */

        private int state;
        private List<UfaceDeviceDOListBean> ufaceDeviceDOList;

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public List<UfaceDeviceDOListBean> getUfaceDeviceDOList() {
            return ufaceDeviceDOList;
        }

        public void setUfaceDeviceDOList(List<UfaceDeviceDOListBean> ufaceDeviceDOList) {
            this.ufaceDeviceDOList = ufaceDeviceDOList;
        }

        public static class UfaceDeviceDOListBean {
            /**
             * cellId : 0
             * createTime :
             * deviceKey :
             * deviceName :
             * deviceType : 0
             * id : 0
             * propertyId : 0
             * propertyName :
             * right : 0
             * state : 0
             * status : 0
             * updateTime :
             * versionNo :
             * villageId : 0
             * villageName :
             */

            private int cellId;
            private String createTime;
            private String deviceKey;
            private String deviceName;
            private int deviceType;
            private int id;
            private int propertyId;
            private String propertyName;
            private int right;
            private int state;
            private int status;
            private String updateTime;
            private String versionNo;
            private int villageId;
            private String villageName;

            public int getCellId() {
                return cellId;
            }

            public void setCellId(int cellId) {
                this.cellId = cellId;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getDeviceKey() {
                return deviceKey;
            }

            public void setDeviceKey(String deviceKey) {
                this.deviceKey = deviceKey;
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

            public String getPropertyName() {
                return propertyName;
            }

            public void setPropertyName(String propertyName) {
                this.propertyName = propertyName;
            }

            public int getRight() {
                return right;
            }

            public void setRight(int right) {
                this.right = right;
            }

            public int getState() {
                return state;
            }

            public void setState(int state) {
                this.state = state;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(String updateTime) {
                this.updateTime = updateTime;
            }

            public String getVersionNo() {
                return versionNo;
            }

            public void setVersionNo(String versionNo) {
                this.versionNo = versionNo;
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
        }
    }
}
