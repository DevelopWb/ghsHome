package com.ghs.ghshome.bean;

/**
 * Author:wang_sir
 * Time:2018/7/31 19:27
 * Description:This is SectorChartTitleBean
 */
public class SectorChartContentBean {
    private String childName;//标题名称
    private int childMark;//标题图标
    private String childNameContent;//标题名称内容

    public String getChildName() {
        return childName;
    }

    public void setChildName(String childName) {
        this.childName = childName;
    }

    public int getChildMark() {
        return childMark;
    }

    public void setChildMark(int childMark) {
        this.childMark = childMark;
    }

    public String getChildNameContent() {
        return childNameContent;
    }

    public void setChildNameContent(String childNameContent) {
        this.childNameContent = childNameContent;
    }

    public SectorChartContentBean(String childName, int childMark, String childNameContent) {
        this.childName = childName;
        this.childMark = childMark;
        this.childNameContent = childNameContent;
    }
}
