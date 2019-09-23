package com.ghs.ghshome.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Author:wang_sir
 * Time:2019/5/21 14:17
 * Description:This is LifeServiceBean
 * 生活服务
 */
public class LifeServiceBean {

    /**
     * code : 1000
     * message : 成功
     * data : [{"villageId":27,"menuId":2,"name":"报修","open":2},{"villageId":27,"menuId":4,"name":"房屋账单","open":2},{"villageId":27,"menuId":5,"name":"手机开门","open":1},{"villageId":27,"menuId":7,"name":"物品放行","open":1}]
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
         * villageId : 27
         * menuId : 2
         * name : 报修
         * open : 2
         * editType  编辑标识  0代表非编辑状态  1代表编辑状态
         */

        private int villageId;
        private int menuId;
        private String name;
        private int open;
        private int editType;
        private boolean  isFalseData;//是否是假数据

        public boolean isFalseData() {
            return isFalseData;
        }

        public void setFalseData(boolean falseData) {
            isFalseData = falseData;
        }

        public int getEditType() {
            return editType;
        }

        public void setEditType(int editType) {
            this.editType = editType;
        }

        public int getVillageId() {
            return villageId;
        }

        public void setVillageId(int villageId) {
            this.villageId = villageId;
        }

        public int getMenuId() {
            return menuId;
        }

        public void setMenuId(int menuId) {
            this.menuId = menuId;
        }

        public String getName() {
            return name == null ? "" : name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getOpen() {
            return open;
        }

        public void setOpen(int open) {
            this.open = open;
        }

        public DataBean() {
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.villageId);
            dest.writeInt(this.menuId);
            dest.writeString(this.name);
            dest.writeInt(this.open);
            dest.writeInt(this.editType);
        }

        protected DataBean(Parcel in) {
            this.villageId = in.readInt();
            this.menuId = in.readInt();
            this.name = in.readString();
            this.open = in.readInt();
            this.editType = in.readInt();
        }

        public static final Creator<DataBean> CREATOR = new Creator<DataBean>() {
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
