package com.ghs.ghshome.models.homePage.houseBill.Linearchart;

import com.ghs.ghshome.base.BaseViewInterface;

/**
 * Author:wang_sir
 * Time:2018/8/1 19:00
 * Description:This is LinearChartContract
 */
public interface LinearChartContract {

    interface ILinearChartView extends BaseViewInterface {


    }

    interface ILinearChartPresent {
        void getLinearChartData(int roomId,int payUser,String startMonth,String endMonth,String feeType,String tag);
    }
}
