package com.ghs.ghshome.bean;

import java.util.List;

/**
 * Author:wang_sir
 * Time:2019/7/29 11:05
 * Description:This is SeedTaskBean
 * 种子任务列表
 */
public class SeedTaskBean {

    /**
     * code : 1000
     * message : 成功
     * data : [{"taskId":1,"taskType":"sign","taskName":"连续7天签到","seedRule":"1,2,3,4,5,6,7","progress":"0/7","signCount":0,"seedRuleText":"+28颗","state":2},{"taskId":2,"taskType":"bindCar","taskName":"绑定车辆","seedRule":"5000","progress":null,"signCount":null,"seedRuleText":"+5000颗","state":2},{"taskId":3,"taskType":"firstLogin","taskName":"首次登陆","seedRule":"10","progress":null,"signCount":null,"seedRuleText":"+10颗","state":2},{"taskId":4,"taskType":"grantHouse","taskName":"授权住户注册完成","seedRule":"5","progress":null,"signCount":null,"seedRuleText":"+5颗","state":2},{"taskId":5,"taskType":"mobileOpenDoor","taskName":"手机开门成功","seedRule":"2","progress":"0/2","signCount":null,"seedRuleText":"+2颗","state":2},{"taskId":6,"taskType":"waterFee","taskName":"缴纳水费","seedRule":"5","progress":null,"signCount":null,"seedRuleText":"+按金额的5%计算种子","state":2},{"taskId":7,"taskType":"ammeterFee","taskName":"缴纳电费","seedRule":"5","progress":null,"signCount":null,"seedRuleText":"+按金额的5%计算种子","state":2},{"taskId":8,"taskType":"propertyFee","taskName":"缴纳物业费","seedRule":"10","progress":null,"signCount":null,"seedRuleText":"+按金额的10%计算种子","state":2},{"taskId":9,"taskType":"uploadHead","taskName":"上传头像","seedRule":"5","progress":null,"signCount":null,"seedRuleText":"+5颗","state":2}]
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
         * taskId : 1
         * taskType : sign
         * taskName : 连续7天签到
         * seedRule : 1,2,3,4,5,6,7
         * progress : 0/7
         * signCount : 0
         * seedRuleText : +28颗
         * state : 2
         */

        private int taskId;
        private String taskType;
        private String taskName;
        private String seedRule;
        private String progress;
        private int signCount;
        private String seedRuleText;
        private int state;

        public int getTaskId() {
            return taskId;
        }

        public void setTaskId(int taskId) {
            this.taskId = taskId;
        }

        public String getTaskType() {
            return taskType == null ? "" : taskType;
        }

        public void setTaskType(String taskType) {
            this.taskType = taskType;
        }

        public String getTaskName() {
            return taskName == null ? "" : taskName;
        }

        public void setTaskName(String taskName) {
            this.taskName = taskName;
        }

        public String getSeedRule() {
            return seedRule == null ? "" : seedRule;
        }

        public void setSeedRule(String seedRule) {
            this.seedRule = seedRule;
        }

        public String getProgress() {
            return progress == null ? "" : progress;
        }

        public void setProgress(String progress) {
            this.progress = progress;
        }

        public int getSignCount() {
            return signCount;
        }

        public void setSignCount(int signCount) {
            this.signCount = signCount;
        }

        public String getSeedRuleText() {
            return seedRuleText == null ? "" : seedRuleText;
        }

        public void setSeedRuleText(String seedRuleText) {
            this.seedRuleText = seedRuleText;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }
    }
}
