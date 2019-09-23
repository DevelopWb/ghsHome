package com.ghs.ghshome.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Author:wang_sir
 * Time:2019/9/16 10:57
 * Description:This is VillageListBean
 */
public class VillageListBean {

    /**
     * code : 1000
     * message : 成功
     * data : [{"id":52,"propertyId":49,"villageName":"张维霖物业服务中心","communityId":null,"provinceId":1,"cityId":1,"districtId":null,"address":null,"commId":"1.000.523","visitorCheckFlag":0,"updateTime":"2019-05-07 16:08:22","propertyName":"维霖物业集团","communityName":null,"provinceName":"北京市","cityName":"北京市","districtName":null,"deleteFlag":1,"createTime":"2019-05-07 16:08:22"},{"id":51,"propertyId":48,"villageName":"关东巷社区","communityId":null,"provinceId":1,"cityId":1,"districtId":null,"address":null,"commId":"1.000.523","visitorCheckFlag":0,"updateTime":"2019-04-25 15:37:11","propertyName":"朝外soho","communityName":null,"provinceName":"北京市","cityName":"北京市","districtName":null,"deleteFlag":1,"createTime":"2019-04-25 15:37:11"},{"id":50,"propertyId":48,"villageName":"新城小区","communityId":null,"provinceId":1,"cityId":1,"districtId":null,"address":null,"commId":"1.000.523","visitorCheckFlag":0,"updateTime":"2019-04-25 14:36:29","propertyName":"朝外soho","communityName":null,"provinceName":"北京市","cityName":"北京市","districtName":null,"deleteFlag":1,"createTime":"2019-04-25 14:36:29"},{"id":49,"propertyId":39,"villageName":"大禹褐石","communityId":null,"provinceId":1,"cityId":1,"districtId":null,"address":null,"commId":"1.000.523","visitorCheckFlag":0,"updateTime":"2019-04-16 16:55:53","propertyName":"大禹测试企业","communityName":null,"provinceName":"北京市","cityName":"北京市","districtName":null,"deleteFlag":1,"createTime":"2019-04-16 16:55:53"},{"id":46,"propertyId":45,"villageName":"国家大剧院小区","communityId":null,"provinceId":1,"cityId":1,"districtId":null,"address":null,"commId":"1.000.523","visitorCheckFlag":0,"updateTime":"2019-04-16 13:48:09","propertyName":"苹果园社区","communityName":null,"provinceName":"北京市","cityName":"北京市","districtName":null,"deleteFlag":1,"createTime":"2019-04-16 13:48:09"},{"id":34,"propertyId":38,"villageName":"wjf3通天塔","communityId":null,"provinceId":1,"cityId":1,"districtId":null,"address":null,"commId":"1.000.523","visitorCheckFlag":0,"updateTime":"2019-04-16 16:52:38","propertyName":"wjf","communityName":null,"provinceName":"北京市","cityName":"北京市","districtName":null,"deleteFlag":1,"createTime":"2019-04-10 16:04:55"},{"id":32,"propertyId":12,"villageName":"内置角色小区","communityId":null,"provinceId":1,"cityId":1,"districtId":null,"address":null,"commId":"1.000.523","visitorCheckFlag":0,"updateTime":"2019-03-29 14:06:58","propertyName":"长春大禹物业管理有限公司","communityName":null,"provinceName":"北京市","cityName":"北京市","districtName":null,"deleteFlag":1,"createTime":"2019-03-29 14:06:58"},{"id":30,"propertyId":33,"villageName":"西子莲花","communityId":22,"provinceId":1,"cityId":1,"districtId":2,"address":"西子莲花","commId":"1.000.523","visitorCheckFlag":0,"updateTime":"2019-03-06 12:43:02","propertyName":"guolun11","communityName":"测试设区","provinceName":"北京市","cityName":"北京市","districtName":"西城区","deleteFlag":1,"createTime":null},{"id":29,"propertyId":32,"villageName":"田园风光雅苑","communityId":null,"provinceId":1,"cityId":1,"districtId":1,"address":"产品区","commId":"1.000.523","visitorCheckFlag":0,"updateTime":"2018-11-26 19:09:40","propertyName":"郭伦物业","communityName":null,"provinceName":"北京市","cityName":"北京市","districtName":"东城区","deleteFlag":1,"createTime":null},{"id":28,"propertyId":30,"villageName":"测试小区","communityId":22,"provinceId":1,"cityId":1,"districtId":5,"address":"万通中心","commId":"1.000.523","visitorCheckFlag":0,"updateTime":"2018-10-09 10:37:58","propertyName":"长春大禹物业公司","communityName":"测试设区","provinceName":"北京市","cityName":"北京市","districtName":"朝阳区","deleteFlag":1,"createTime":null},{"id":25,"propertyId":26,"villageName":"中铁建，花语城","communityId":21,"provinceId":1,"cityId":1,"districtId":3,"address":"北京万通中心商铺2003","commId":"1.000.523","visitorCheckFlag":0,"updateTime":"2018-07-23 11:12:41","propertyName":"中铁建物业","communityName":"中铁.花语城","provinceName":"北京市","cityName":"北京市","districtName":"崇文区","deleteFlag":1,"createTime":null}]
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
         * id : 52
         * propertyId : 49
         * villageName : 张维霖物业服务中心
         * communityId : null
         * provinceId : 1
         * cityId : 1
         * districtId : null
         * address : null
         * commId : 1.000.523
         * visitorCheckFlag : 0
         * updateTime : 2019-05-07 16:08:22
         * propertyName : 维霖物业集团
         * communityName : null
         * provinceName : 北京市
         * cityName : 北京市
         * districtName : null
         * deleteFlag : 1
         * createTime : 2019-05-07 16:08:22
         */

        private int id;
        private int propertyId;
        private String villageName;
        private int communityId;
        private int provinceId;
        private int cityId;
        private int districtId;
        private String address;
        private String commId;
        private int visitorCheckFlag;
        private String updateTime;
        private String propertyName;
        private String communityName;
        private String provinceName;
        private String cityName;
        private String districtName;
        private int deleteFlag;
        private String createTime;

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

        public String getVillageName() {
            return villageName == null ? "" : villageName;
        }

        public void setVillageName(String villageName) {
            this.villageName = villageName;
        }

        public int getCommunityId() {
            return communityId;
        }

        public void setCommunityId(int communityId) {
            this.communityId = communityId;
        }

        public int getProvinceId() {
            return provinceId;
        }

        public void setProvinceId(int provinceId) {
            this.provinceId = provinceId;
        }

        public int getCityId() {
            return cityId;
        }

        public void setCityId(int cityId) {
            this.cityId = cityId;
        }

        public int getDistrictId() {
            return districtId;
        }

        public void setDistrictId(int districtId) {
            this.districtId = districtId;
        }

        public String getAddress() {
            return address == null ? "" : address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getCommId() {
            return commId == null ? "" : commId;
        }

        public void setCommId(String commId) {
            this.commId = commId;
        }

        public int getVisitorCheckFlag() {
            return visitorCheckFlag;
        }

        public void setVisitorCheckFlag(int visitorCheckFlag) {
            this.visitorCheckFlag = visitorCheckFlag;
        }

        public String getUpdateTime() {
            return updateTime == null ? "" : updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public String getPropertyName() {
            return propertyName == null ? "" : propertyName;
        }

        public void setPropertyName(String propertyName) {
            this.propertyName = propertyName;
        }

        public String getCommunityName() {
            return communityName == null ? "" : communityName;
        }

        public void setCommunityName(String communityName) {
            this.communityName = communityName;
        }

        public String getProvinceName() {
            return provinceName == null ? "" : provinceName;
        }

        public void setProvinceName(String provinceName) {
            this.provinceName = provinceName;
        }

        public String getCityName() {
            return cityName == null ? "" : cityName;
        }

        public void setCityName(String cityName) {
            this.cityName = cityName;
        }

        public String getDistrictName() {
            return districtName == null ? "" : districtName;
        }

        public void setDistrictName(String districtName) {
            this.districtName = districtName;
        }

        public int getDeleteFlag() {
            return deleteFlag;
        }

        public void setDeleteFlag(int deleteFlag) {
            this.deleteFlag = deleteFlag;
        }

        public String getCreateTime() {
            return createTime == null ? "" : createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.id);
            dest.writeInt(this.propertyId);
            dest.writeString(this.villageName);
            dest.writeInt(this.communityId);
            dest.writeInt(this.provinceId);
            dest.writeInt(this.cityId);
            dest.writeInt(this.districtId);
            dest.writeString(this.address);
            dest.writeString(this.commId);
            dest.writeInt(this.visitorCheckFlag);
            dest.writeString(this.updateTime);
            dest.writeString(this.propertyName);
            dest.writeString(this.communityName);
            dest.writeString(this.provinceName);
            dest.writeString(this.cityName);
            dest.writeString(this.districtName);
            dest.writeInt(this.deleteFlag);
            dest.writeString(this.createTime);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.id = in.readInt();
            this.propertyId = in.readInt();
            this.villageName = in.readString();
            this.communityId = in.readInt();
            this.provinceId = in.readInt();
            this.cityId = in.readInt();
            this.districtId = in.readInt();
            this.address = in.readString();
            this.commId = in.readString();
            this.visitorCheckFlag = in.readInt();
            this.updateTime = in.readString();
            this.propertyName = in.readString();
            this.communityName = in.readString();
            this.provinceName = in.readString();
            this.cityName = in.readString();
            this.districtName = in.readString();
            this.deleteFlag = in.readInt();
            this.createTime = in.readString();
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
