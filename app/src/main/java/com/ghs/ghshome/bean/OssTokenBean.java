package com.ghs.ghshome.bean;

/**
 * Author:wang_sir
 * Time:2018/8/8 15:30
 * Description:This is OssTokenBean
 */
public class OssTokenBean {

    /**
     * code : 1000
     * message : 成功
     * data : {"SecurityToken":"CAISmwJ1q6Ft5B2yfSjIr4vGGe/N245CwriONVTVkVAiXeV7oqfc0zz2IH1LfXNgBesYtvkxlWlY6/0clrh+W4NIX0rNaY5t9ZlN9wqkbtIEFzgYVPhW5qe+EE2/VjTJvqaLEdibIfrZfvCyESem8gZ43br9cxi7QlWhKufnoJV7b9MRLGbaAD1dH4UUXEgAzvUXLnzML/2gHwf3i27LdipStxF7lHl05NbYoKiV4QGMi0bhmK1H5dazAOD9NpAwbc0nAo3rhbMqKvub6kMKtUgWrpURpbdf5DLKsuuaB1Rs+BicO4LWiIYyc1YjNvRhRfYZ8qmlxaAj5PagnoD22gtLOvpOTyPcSYavzc3JAuq1McwjcrL2K4AfPTZbngycGoABPArBM37uxJIFZIsA3cCfYVyR7I1Pey7N59ck50B6ruwXjChZFFBfAtS13cpphuDd1hMmkF2d7IxdmZOYjioktNis4bVkqELHWp1N75Kz1I1jZ0beSapVs/ypAR0Z/i8h/a0RI9oGw0Vl8urhQFm9crUbz6LgmW9/xZbTAUxQ5BA=","RequestId":"55621004-4D60-4DE4-9CB9-7DBA2BEA81A2","AccessKeyId":"STS.NHsRUy6Qcuze7rduPwQjWMbw1","AccessKeySecret":"CumytWkzjrjKRDoJRZeWFb8Ew5eGfsH6AbvUMGwrufJj","Expiration":"2018-08-08T08:28:51Z"}
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
         * SecurityToken : CAISmwJ1q6Ft5B2yfSjIr4vGGe/N245CwriONVTVkVAiXeV7oqfc0zz2IH1LfXNgBesYtvkxlWlY6/0clrh+W4NIX0rNaY5t9ZlN9wqkbtIEFzgYVPhW5qe+EE2/VjTJvqaLEdibIfrZfvCyESem8gZ43br9cxi7QlWhKufnoJV7b9MRLGbaAD1dH4UUXEgAzvUXLnzML/2gHwf3i27LdipStxF7lHl05NbYoKiV4QGMi0bhmK1H5dazAOD9NpAwbc0nAo3rhbMqKvub6kMKtUgWrpURpbdf5DLKsuuaB1Rs+BicO4LWiIYyc1YjNvRhRfYZ8qmlxaAj5PagnoD22gtLOvpOTyPcSYavzc3JAuq1McwjcrL2K4AfPTZbngycGoABPArBM37uxJIFZIsA3cCfYVyR7I1Pey7N59ck50B6ruwXjChZFFBfAtS13cpphuDd1hMmkF2d7IxdmZOYjioktNis4bVkqELHWp1N75Kz1I1jZ0beSapVs/ypAR0Z/i8h/a0RI9oGw0Vl8urhQFm9crUbz6LgmW9/xZbTAUxQ5BA=
         * RequestId : 55621004-4D60-4DE4-9CB9-7DBA2BEA81A2
         * AccessKeyId : STS.NHsRUy6Qcuze7rduPwQjWMbw1
         * AccessKeySecret : CumytWkzjrjKRDoJRZeWFb8Ew5eGfsH6AbvUMGwrufJj
         * Expiration : 2018-08-08T08:28:51Z
         */

        private String SecurityToken;
        private String RequestId;
        private String AccessKeyId;
        private String AccessKeySecret;
        private String Expiration;

        public String getSecurityToken() {
            return SecurityToken;
        }

        public void setSecurityToken(String SecurityToken) {
            this.SecurityToken = SecurityToken;
        }

        public String getRequestId() {
            return RequestId;
        }

        public void setRequestId(String RequestId) {
            this.RequestId = RequestId;
        }

        public String getAccessKeyId() {
            return AccessKeyId;
        }

        public void setAccessKeyId(String AccessKeyId) {
            this.AccessKeyId = AccessKeyId;
        }

        public String getAccessKeySecret() {
            return AccessKeySecret;
        }

        public void setAccessKeySecret(String AccessKeySecret) {
            this.AccessKeySecret = AccessKeySecret;
        }

        public String getExpiration() {
            return Expiration;
        }

        public void setExpiration(String Expiration) {
            this.Expiration = Expiration;
        }
    }
}
