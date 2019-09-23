package com.ghs.ghshome.bean;

/**
 * Author:wang_sir
 * Time:2018/7/30 14:34
 * Description:This is SelectCouponBean
 */
public class SelectCouponBean {

    private String couponName;//优惠券名称
    private int couponID;//优惠券ID
    private int couponUserID;//优惠券关系ID
    private double money;//优惠券的金额
    private boolean couponSelectStatus;//选择状态

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public boolean isCouponSelectStatus() {
        return couponSelectStatus;
    }

    public void setCouponSelectStatus(boolean couponSelectStatus) {
        this.couponSelectStatus = couponSelectStatus;
    }

    public int getCouponID() {
        return couponID;
    }

    public void setCouponID(int couponID) {
        this.couponID = couponID;
    }

    public int getCouponUserID() {
        return couponUserID;
    }

    public void setCouponUserID(int couponUserID) {
        this.couponUserID = couponUserID;
    }

    public SelectCouponBean(String couponName, int couponID, int couponUserID, double money, boolean couponSelectStatus) {
        this.couponName = couponName;
        this.couponID = couponID;
        this.couponUserID = couponUserID;
        this.money = money;
        this.couponSelectStatus = couponSelectStatus;
    }
}
