package com.ghs.ghshome.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.ghs.ghshome.models.homePage.seed.SeedRecordAdapter;

/**
 * Author:wang_sir
 * Time:2019/7/30 16:33
 * Description:This is SeedRecordItem1
 */
public class CarFeeRecordItem2 implements MultiItemEntity {
    private CarPayRecord.DataBean.CarparkTempOrderDOListBean bean;

    public CarFeeRecordItem2(CarPayRecord.DataBean.CarparkTempOrderDOListBean bean) {
        this.bean = bean;
    }

    public CarPayRecord.DataBean.CarparkTempOrderDOListBean getBean() {
        return bean;
    }

    public void setBean(CarPayRecord.DataBean.CarparkTempOrderDOListBean bean) {
        this.bean = bean;
    }

    @Override
    public int getItemType() {
        return SeedRecordAdapter.TYPE_LEVEL_4;
    }
}
