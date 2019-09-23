package com.ghs.ghshome.bean;

import java.util.List;

/**
 * Author:wang_sir
 * Time:2019/9/16 14:42
 * Description:This is CellsBean  单元
 */
public class CellsBean {

    /**
     * code : 1000
     * message : 成功
     * data : [{"id":808,"towerId":596,"cellName":"1","updateTime":"2019-05-07 11:46:07","towerNumber":"1","portionId":232,"portionName":"一期","villageId":51,"villageName":"关东巷社区","cellFullName":"一期-1-1"},{"id":833,"towerId":617,"cellName":"2","updateTime":"2019-05-07 18:35:16","towerNumber":"2","portionId":236,"portionName":"二期","villageId":51,"villageName":"关东巷社区","cellFullName":"二期-2-2"}]
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
         * id : 808
         * towerId : 596
         * cellName : 1
         * updateTime : 2019-05-07 11:46:07
         * towerNumber : 1
         * portionId : 232
         * portionName : 一期
         * villageId : 51
         * villageName : 关东巷社区
         * cellFullName : 一期-1-1
         */

        private int id;
        private int towerId;
        private String cellName;
        private String updateTime;
        private String towerNumber;
        private int portionId;
        private String portionName;
        private int villageId;
        private String villageName;
        private String cellFullName;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getTowerId() {
            return towerId;
        }

        public void setTowerId(int towerId) {
            this.towerId = towerId;
        }

        public String getCellName() {
            return cellName == null ? "" : cellName;
        }

        public void setCellName(String cellName) {
            this.cellName = cellName;
        }

        public String getUpdateTime() {
            return updateTime == null ? "" : updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
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

        public String getCellFullName() {
            return cellFullName == null ? "" : cellFullName;
        }

        public void setCellFullName(String cellFullName) {
            this.cellFullName = cellFullName;
        }
    }
}
