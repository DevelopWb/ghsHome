package com.ghs.ghshome.bean;

import java.util.List;

/**
 * created by guohainan
 * created date 2019/6/18
 * application
 * package name com.ghs.ghshome.bean
 */
public class FacePhotoListBean {


    /**
     * code : 0
     * data : [{"faceUrl":"","guid":""}]
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
         * faceUrl :
         * guid :
         */

        private String faceUrl;
        private String guid;

        public String getFaceUrl() {
            return faceUrl;
        }

        public void setFaceUrl(String faceUrl) {
            this.faceUrl = faceUrl;
        }

        public String getGuid() {
            return guid;
        }

        public void setGuid(String guid) {
            this.guid = guid;
        }
    }
}
