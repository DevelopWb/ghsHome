package com.ghs.ghshome.bean;

import java.util.List;

/**
 * Author:wang_sir
 * Time:2019/9/16 11:12
 * Description:This is CitiesBean  城市列表
 */
public class CitiesBean {
    /**
     * code : 1000
     * message : 成功
     * data : [{"firstLetter":"B","cityList":[{"id":1,"cityName":"北京市","pid":null,"zipCode":null}]},{"firstLetter":"C","cityList":[{"id":51,"cityName":"长春市","pid":null,"zipCode":null}]},{"firstLetter":"F","cityList":[{"id":133,"cityName":"抚州市","pid":null,"zipCode":null}]},{"firstLetter":"J","cityList":[{"id":67,"cityName":"佳木斯市","pid":null,"zipCode":null}]},{"firstLetter":"S","cityList":[{"id":37,"cityName":"沈阳市","pid":null,"zipCode":null}]},{"firstLetter":"T","cityList":[{"id":14,"cityName":"太原市","pid":null,"zipCode":null},{"id":2,"cityName":"天津市","pid":null,"zipCode":null}]}]
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
         * firstLetter : B
         * cityList : [{"id":1,"cityName":"北京市","pid":null,"zipCode":null}]
         */

        private String firstLetter;
        private List<CityListBean> cityList;

        public String getFirstLetter() {
            return firstLetter;
        }

        public void setFirstLetter(String firstLetter) {
            this.firstLetter = firstLetter;
        }

        public List<CityListBean> getCityList() {
            return cityList;
        }

        public void setCityList(List<CityListBean> cityList) {
            this.cityList = cityList;
        }

        public static class CityListBean {
            /**
             * id : 1
             * cityName : 北京市
             * pid : null
             * zipCode : null
             */

            private int id;
            private String cityName;
            private int pid;
            private String zipCode;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getCityName() {
                return cityName == null ? "" : cityName;
            }

            public void setCityName(String cityName) {
                this.cityName = cityName;
            }

            public int getPid() {
                return pid;
            }

            public void setPid(int pid) {
                this.pid = pid;
            }

            public String getZipCode() {
                return zipCode == null ? "" : zipCode;
            }

            public void setZipCode(String zipCode) {
                this.zipCode = zipCode;
            }
        }
    }
}
