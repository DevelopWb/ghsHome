package com.ghs.ghshome.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Author:wang_sir
 * Time:2019/7/30 16:33
 * Description:This is SeedRecordItem1
 */
public class SeedRecordItem2 implements MultiItemEntity {
    private SeedRecordBean.DataBean.SeedDetailDOListBean bean;

    public SeedRecordItem2(SeedRecordBean.DataBean.SeedDetailDOListBean bean) {
        this.bean = bean;
    }

    public SeedRecordBean.DataBean.SeedDetailDOListBean getBean() {
        return bean;
    }

    public void setBean(SeedRecordBean.DataBean.SeedDetailDOListBean bean) {
        this.bean = bean;
    }

    @Override
    public int getItemType() {
        return 2;
    }
}
