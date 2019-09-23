package com.ghs.ghshome.models.homePage.houseBill.houseBillUnpay;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ghs.ghshome.R;
import com.ghs.ghshome.bean.KeyValueBean;


/**
 * Author:wang_sir
 * Time:2018/6/29 11:16
 * Description:This is   未支付订单里面的rv
 */

public class BillUnPayChildAdapter extends BaseQuickAdapter<KeyValueBean, BaseViewHolder> {


    public BillUnPayChildAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, KeyValueBean item) {
        helper.setText(R.id.bill_detail_help_tv, item.getKey());
        helper.setText(R.id.bill_detail_property_tel_tv, item.getValue());
    }
}

