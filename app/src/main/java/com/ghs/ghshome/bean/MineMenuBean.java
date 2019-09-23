package com.ghs.ghshome.bean;

/**
 * Author:wang_sir
 * Time:2019/5/30 15:09
 * Description:This is MineMenuBean  我的模块的菜单实体类
 */
public class MineMenuBean {

    private int  drawbleId;//图片资源id
    private String  title;//标题
    private String  des;//描述

    public MineMenuBean(int drawbleId, String title, String des) {
        this.drawbleId = drawbleId;
        this.title = title;
        this.des = des;
    }

    public int getDrawbleId() {
        return drawbleId;
    }

    public void setDrawbleId(int drawbleId) {
        this.drawbleId = drawbleId;
    }

    public String getTitle() {
        return title == null ? "" : title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDes() {
        return des == null ? "" : des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}
