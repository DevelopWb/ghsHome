package com.ghs.ghshome.models.homePage.houseBill.Linearchart;

import com.ghs.ghshome.base.BasePresent;
import com.ghs.ghshome.base.network.networkProxy.HttpProxy;
import com.ghs.ghshome.base.network.okgo.NetResponseCallBack;
import com.ghs.ghshome.bean.LinearChartBean;
import com.ghs.ghshome.tools.Contract;
import com.ghs.ghshome.tools.GsonManager;

/**
 * Author:wang_sir
 * Time:2018/8/1 19:02
 * Description:This is LinearChartPresent
 */
public class LinearChartPresent extends BasePresent<LinearChartContract.ILinearChartView> implements LinearChartContract.ILinearChartPresent {
    @Override
    public void getLinearChartData(int roomId, int payUser, String startMonth, String endMonth, String feeType, final String tag) {
        if (getView() != null) {
            getView().startLoading(tag);
        }

        HttpProxy.getInstance()
                .params("roomId", roomId)
                .params("payUser", payUser)
                .params("startMonth", startMonth)
                .params("endMonth", endMonth)
                .params("feeType", feeType)
                .postToNetwork(Contract.LINEAR_CHART_DATE,new NetResponseCallBack() {
                    @Override
                    public void onSuccess(String content) {
                        if (getView() != null) {
                            getView().stopLoading(tag);
                            getView().updateView(GsonManager.getInstance().parseJsonToBean(content, LinearChartBean.class), tag);

                        }
                    }

                    @Override
                    public void onError(String str) {
                        if (getView() != null) {
                            getView().onError(str);
                        }
                    }
                });

    }

}
