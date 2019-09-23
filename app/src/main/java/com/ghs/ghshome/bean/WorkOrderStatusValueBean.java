package com.ghs.ghshome.bean;

/**
 * Author:wang_sir
 * Time:2019/5/24 10:22
 * Description:This is WorkOrderStatusValueBean
 */
public class WorkOrderStatusValueBean {

    private boolean  isLight;
    private String  statusValue;

    public WorkOrderStatusValueBean(boolean isLight, String statusValue) {
        this.isLight = isLight;
        this.statusValue = statusValue;
    }

    public boolean isLight() {
        return isLight;
    }

    public void setLight(boolean light) {
        isLight = light;
    }

    public String getStatusValue() {
        return statusValue == null ? "" : statusValue;
    }

    public void setStatusValue(String statusValue) {
        this.statusValue = statusValue;
    }
}
