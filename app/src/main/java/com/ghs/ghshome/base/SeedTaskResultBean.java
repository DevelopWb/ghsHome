package com.ghs.ghshome.base;

/**
 * Author:wang_sir
 * Time:2019/7/29 16:56
 * Description:This is SeedTaskResultBean
 * 种子任务执行完成的结果
 */
public class SeedTaskResultBean {

    /**
     * code : 1000
     * message : 成功
     * data : {"showSeed":1,"seed":5000,"source":"绑定车辆"}
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
         * showSeed : 1
         * seed : 5000
         * source : 绑定车辆
         */

        private int showSeed;
        private int seed;
        private String source;

        public int getShowSeed() {
            return showSeed;
        }

        public void setShowSeed(int showSeed) {
            this.showSeed = showSeed;
        }

        public int getSeed() {
            return seed;
        }

        public void setSeed(int seed) {
            this.seed = seed;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }
    }
}
