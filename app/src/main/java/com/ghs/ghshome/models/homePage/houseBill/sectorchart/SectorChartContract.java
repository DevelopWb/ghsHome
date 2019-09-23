package com.ghs.ghshome.models.homePage.houseBill.sectorchart;

import com.ghs.ghshome.base.BaseViewInterface;
import com.ghs.ghshome.bean.SectorChartBean;

import org.xclcharts.chart.PieData;

/**
 * Author:wang_sir
 * Time:2018/7/31 17:56
 * Description:This is SectorChartContract
 */
public interface SectorChartContract {
    String CHART_DATA = "chart_data";

    interface ISectorChartView extends BaseViewInterface {

    }

    interface ISectorChartPresent {
        void getSectorChartData(int roomId, int payUser, int year, String tag);

        void getChartData(SectorChartBean.DataBean dataBean,String tag);
    }

    interface ISectorChartClickListener {
        //扇形被点击
        void sectorClicked(PieData pieData);
    }
}
