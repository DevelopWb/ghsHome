package com.ghs.ghshome.bean;

/**
 * Author:wang_sir
 * Time:2018/7/14 15:35
 * Description:This is LoginBean
 */
public class LoginBean {


    /**
     * code : 1000
     * message : 成功
     * data : {"id":3075,"mobile":"13578790511","unionId":"om_iM1go3xLWkGaN0L1K2CGxKgoc","nickName":"乔布斯","fullName":"李昊","cardNum":null,"headImage":"201807271055023690000218.jpeg","carNum":null,"carInfo":null,"registerTime":"2018-05-29 14:41:22","updateTime":"2018-08-06 15:19:20","integral":36154900480,"loginFlag":"5b5afd5ead024aa8a0ca5c7f324bcdd3","token":"eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOiIzMDc1IiwidXNlck5hbWUiOiLmnY7mmIoiLCJpYXQiOjE1MzM1NDAwODIsImV4cCI6MTUzNjEzMjA4Mn0.SFsglXl81Hpf4jL_x-2C5JVkeF7LXAi0OemXay8jX8Q"}
     */

    private int code;
    private String message;
    private UserAndRoomBean.DataBean.GhsUserDOBean data;

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

    public UserAndRoomBean.DataBean.GhsUserDOBean getData() {
        return data;
    }

    public void setData(UserAndRoomBean.DataBean.GhsUserDOBean data) {
        this.data = data;
    }

}
