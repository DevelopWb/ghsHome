package com.ghs.ghshome.bean;

import java.util.List;

/**
 * Author:wang_sir
 * Time:2018/8/20 11:51
 * Description:This is GrowthTaskListBean
 * 成长任务中的任务类别实体类
 */
public class GrowthTaskListBean {

    /**
     * code : 1000
     * message : 成功
     * data : {"integral":185,"limitTimeTaskList":[{"integralTaskId":19,"taskName":"这里有惊喜","description":"快来领优惠券","rangeTime":"7.25-8.25","taskType":3,"imageUrl":"2018072615484610348389.jpg","taskUrl":"","integral":null,"top":1,"everyDayNum":null,"compleNum":0,"receiveNum":0}],"newPeopleTaskList":[{"integralTaskId":9,"taskName":"首次联系客服","description":"首次联系客服 30积分","rangeTime":"-","taskType":1,"imageUrl":"","taskUrl":"","integral":30,"top":1,"everyDayNum":1,"compleNum":1,"receiveNum":1},{"integralTaskId":8,"taskName":"首次钥匙授权","description":"首次钥匙授权20积分","rangeTime":"-","taskType":1,"imageUrl":"","taskUrl":"","integral":20,"top":1,"everyDayNum":1,"compleNum":1,"receiveNum":1},{"integralTaskId":1,"taskName":"首次登陆","description":"首次登陆 10积分","rangeTime":"-","taskType":1,"imageUrl":"2018072613594699553299.jpg","taskUrl":"","integral":10,"top":1,"everyDayNum":1,"compleNum":1,"receiveNum":1}],"everyDayTaskList":[{"integralTaskId":10,"taskName":"开门","description":"每天可以开门5次，每次5积分","rangeTime":"5.15-10.24","taskType":2,"imageUrl":"2018071315124108594171.png","taskUrl":"","integral":5,"top":1,"everyDayNum":5,"compleNum":1,"receiveNum":0}]}
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
         * integral : 185
         * limitTimeTaskList : [{"integralTaskId":19,"taskName":"这里有惊喜","description":"快来领优惠券","rangeTime":"7.25-8.25","taskType":3,"imageUrl":"2018072615484610348389.jpg","taskUrl":"","integral":null,"top":1,"everyDayNum":null,"compleNum":0,"receiveNum":0}]
         * newPeopleTaskList : [{"integralTaskId":9,"taskName":"首次联系客服","description":"首次联系客服 30积分","rangeTime":"-","taskType":1,"imageUrl":"","taskUrl":"","integral":30,"top":1,"everyDayNum":1,"compleNum":1,"receiveNum":1},{"integralTaskId":8,"taskName":"首次钥匙授权","description":"首次钥匙授权20积分","rangeTime":"-","taskType":1,"imageUrl":"","taskUrl":"","integral":20,"top":1,"everyDayNum":1,"compleNum":1,"receiveNum":1},{"integralTaskId":1,"taskName":"首次登陆","description":"首次登陆 10积分","rangeTime":"-","taskType":1,"imageUrl":"2018072613594699553299.jpg","taskUrl":"","integral":10,"top":1,"everyDayNum":1,"compleNum":1,"receiveNum":1}]
         * everyDayTaskList : [{"integralTaskId":10,"taskName":"开门","description":"每天可以开门5次，每次5积分","rangeTime":"5.15-10.24","taskType":2,"imageUrl":"2018071315124108594171.png","taskUrl":"","integral":5,"top":1,"everyDayNum":5,"compleNum":1,"receiveNum":0}]
         */

        private int integral;
        private List<TaskBean> limitTimeTaskList;
        private List<TaskBean> newPeopleTaskList;
        private List<TaskBean> everyDayTaskList;

        public int getIntegral() {
            return integral;
        }

        public void setIntegral(int integral) {
            this.integral = integral;
        }

        public List<TaskBean> getLimitTimeTaskList() {
            return limitTimeTaskList;
        }

        public void setLimitTimeTaskList(List<TaskBean> limitTimeTaskList) {
            this.limitTimeTaskList = limitTimeTaskList;
        }

        public List<TaskBean> getNewPeopleTaskList() {
            return newPeopleTaskList;
        }

        public void setNewPeopleTaskList(List<TaskBean> newPeopleTaskList) {
            this.newPeopleTaskList = newPeopleTaskList;
        }

        public List<TaskBean> getEveryDayTaskList() {
            return everyDayTaskList;
        }

        public void setEveryDayTaskList(List<TaskBean> everyDayTaskList) {
            this.everyDayTaskList = everyDayTaskList;
        }

        public static class TaskBean {
            /**
             * integralTaskId : 19
             * taskName : 这里有惊喜
             * description : 快来领优惠券
             * rangeTime : 7.25-8.25
             * taskType : 3
             * imageUrl : 2018072615484610348389.jpg
             * taskUrl :
             * integral : null
             * top : 1
             * everyDayNum : null
             * compleNum : 0
             * receiveNum : 0
             */

            private int integralTaskId;
            private String taskName;
            private String description;
            private String rangeTime;
            private int taskType;
            private String imageUrl;
            private String taskUrl;
            private int integral;
            private int top;
            private int everyDayNum;
            private int compleNum;
            private int receiveNum;

            public int getIntegralTaskId() {
                return integralTaskId;
            }

            public void setIntegralTaskId(int integralTaskId) {
                this.integralTaskId = integralTaskId;
            }

            public String getTaskName() {
                return taskName;
            }

            public void setTaskName(String taskName) {
                this.taskName = taskName;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getRangeTime() {
                return rangeTime;
            }

            public void setRangeTime(String rangeTime) {
                this.rangeTime = rangeTime;
            }

            public int getTaskType() {
                return taskType;
            }

            public void setTaskType(int taskType) {
                this.taskType = taskType;
            }

            public String getImageUrl() {
                return imageUrl;
            }

            public void setImageUrl(String imageUrl) {
                this.imageUrl = imageUrl;
            }

            public String getTaskUrl() {
                return taskUrl;
            }

            public void setTaskUrl(String taskUrl) {
                this.taskUrl = taskUrl;
            }


            public int getTop() {
                return top;
            }

            public void setTop(int top) {
                this.top = top;
            }

            public int getIntegral() {
                return integral;
            }

            public void setIntegral(int integral) {
                this.integral = integral;
            }

            public int getEveryDayNum() {
                return everyDayNum;
            }

            public void setEveryDayNum(int everyDayNum) {
                this.everyDayNum = everyDayNum;
            }

            public int getCompleNum() {
                return compleNum;
            }

            public void setCompleNum(int compleNum) {
                this.compleNum = compleNum;
            }

            public int getReceiveNum() {
                return receiveNum;
            }

            public void setReceiveNum(int receiveNum) {
                this.receiveNum = receiveNum;
            }
        }


    }
}
