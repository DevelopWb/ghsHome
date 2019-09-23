package com.ghs.ghshome.bean;

/**
 * Author:wang_sir
 * Time:2018/7/31 19:27
 * Description:This is SectorChartTitleBean
 */
public class SectorChartTitleBean {
    private String titleName;//标题名称
    private String titleNameContent;//标题名称内容

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    public String getTitleNameContent() {
        return titleNameContent;
    }

    public void setTitleNameContent(String titleNameContent) {
        this.titleNameContent = titleNameContent;
    }

    public SectorChartTitleBean(String titleName, String titleNameContent) {
        this.titleName = titleName;
        this.titleNameContent = titleNameContent;
    }
}
