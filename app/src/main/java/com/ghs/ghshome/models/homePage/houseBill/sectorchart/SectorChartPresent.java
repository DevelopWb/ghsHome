package com.ghs.ghshome.models.homePage.houseBill.sectorchart;

import android.graphics.Color;

import com.ghs.ghshome.R;
import com.ghs.ghshome.base.BasePresent;
import com.ghs.ghshome.base.network.networkProxy.HttpProxy;
import com.ghs.ghshome.base.network.okgo.NetResponseCallBack;
import com.ghs.ghshome.bean.SectorChartBean;
import com.ghs.ghshome.bean.SectorChartContentBean;
import com.ghs.ghshome.bean.SectorChartTitleBean;
import com.ghs.ghshome.tools.Contract;
import com.ghs.ghshome.tools.GsonManager;
import com.ghs.ghshome.tools.StrUtils;

import org.xclcharts.chart.PieData;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Author:wang_sir
 * Time:2018/7/31 17:57
 * Description:This is SectorChartPresent
 */
public class SectorChartPresent extends BasePresent<SectorChartContract.ISectorChartView> implements SectorChartContract.ISectorChartPresent {

    @Override
    public void getSectorChartData(int roomId, int payUser, int year, final String tag) {
        if (getView() != null) {
            getView().startLoading(tag);
        }
        HttpProxy.getInstance()
                .params("roomId", roomId)
                .params("payUser", payUser)
                .params("year", year)
                .postToNetwork(Contract.SECTOR_CHART_DATE,new NetResponseCallBack() {
                    @Override
                    public void onSuccess(String content) {
                        if (getView() != null) {
                            getView().stopLoading(tag);
                            SectorChartBean sectorChartBean = GsonManager.getInstance().parseJsonToBean(content, SectorChartBean.class);
                            if (sectorChartBean != null) {
                                SectorChartBean.DataBean dataBean = sectorChartBean.getData();
                                if (dataBean != null) {
                                    getView().updateView(getRvData(dataBean), tag);
                                    getChartData(dataBean, SectorChartContract.CHART_DATA);
                                }

                            }

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

    @Override
    public void getChartData(SectorChartBean.DataBean dataBean, String tag) {
        LinkedList<PieData> chartData = new LinkedList<PieData>();
        double allFee = dataBean.getAllFee();
        double ammeterFee = dataBean.getAmmeterFee();
        double propertyFee = dataBean.getPropertyFee();
        double waterFee = dataBean.getWaterFee();
        if (allFee > 0) {
            double waterRatio = Double.parseDouble(StrUtils.formatDoubleData(waterFee / allFee * 100));
            double ammeterRatio = Double.parseDouble(StrUtils.formatDoubleData(ammeterFee / allFee * 100));
            double propertyRatio = Double.parseDouble(StrUtils.formatDoubleData(propertyFee / allFee * 100));

            chartData.add(new PieData("水费", "水费：" + StrUtils.formatDoubleData(waterFee) + "元", waterRatio, Color.parseColor("#65C6F3")));

            //将此比例块突出显示
            PieData pd = new PieData("电费", "电费：" + StrUtils.formatDoubleData(ammeterFee) + "元", ammeterRatio, Color.parseColor("#FF9474"));
            chartData.add(pd);


            PieData pdTea = new PieData("物业费", "物业费：" + StrUtils.formatDoubleData(propertyFee) + "元", propertyRatio, Color.parseColor("#FF7672"));
            chartData.add(pdTea);

        }
        if (getView() != null) {
            getView().updateView(chartData, tag);
        }

    }

    /**
     * 初始化adapter数据
     *
     * @param dataBean
     */
    private List<Object> getRvData(SectorChartBean.DataBean dataBean) {
        DecimalFormat sdf = new DecimalFormat("0.00");
        List<Object> arrays = new ArrayList<>();
        double allFee = dataBean.getAllFee();
        double ammeterFee = dataBean.getAmmeterFee();
        double propertyFee = dataBean.getPropertyFee();
        double waterFee = dataBean.getWaterFee();
        int noAllNum = dataBean.getNoAllNum();
        int noAmmeterNum = dataBean.getNoAmmeterNum();
        int noPropertyNum = dataBean.getNoPropertyNum();
        int noWaterNum = dataBean.getNoWaterNum();
        if (allFee>0&&noAllNum>0) {
            arrays.add("divider");
            arrays.add(new SectorChartTitleBean("费用比例", "全年缴费：" + sdf.format(allFee) + "元"));

        }
        if (allFee > 0) {
            arrays.add(new SectorChartContentBean("水费占比", R.mipmap.mine_bill_water_fee, String.valueOf(sdf.format(waterFee / allFee * 100)) + "%"));
            arrays.add(new SectorChartContentBean("电费占比", R.mipmap.mine_bill_electricity_fee, String.valueOf(sdf.format(ammeterFee / allFee * 100)) + "%"));
            arrays.add(new SectorChartContentBean("物业费占比", R.mipmap.mine_bill_property_fee, String.valueOf(sdf.format(propertyFee / allFee * 100)) + "%"));
        }

        if (noAllNum > 0) {
            arrays.add("divider2");
            arrays.add(new SectorChartTitleBean("未缴账单", "未缴项目" + noAllNum + "项"));
            if (noWaterNum > 0) {
                arrays.add(new SectorChartContentBean("水费未缴", R.mipmap.mine_bill_water_fee, String.valueOf(noWaterNum) + "项"));
            }
            if (noAmmeterNum > 0) {
                arrays.add(new SectorChartContentBean("电费未缴", R.mipmap.mine_bill_electricity_fee, String.valueOf(noAmmeterNum) + "项"));
            }
            if (noPropertyNum > 0) {
                arrays.add(new SectorChartContentBean("物业费未缴", R.mipmap.mine_bill_property_fee, String.valueOf(noPropertyNum) + "项"));
            }
        }
        return arrays;
    }

}
