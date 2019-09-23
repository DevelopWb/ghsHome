package com.ghs.ghshome.models.homePage.houseBill;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ghs.ghshome.R;
import com.ghs.ghshome.bean.SelectCouponBean;

/**
 * Author:wang_sir
 * Time:2018/7/30 14:05
 * Description:This is SelectCouponsAdapter
 */
public class SelectCouponsAdapter extends BaseQuickAdapter<SelectCouponBean, BaseViewHolder> {
    public SelectCouponsAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, SelectCouponBean item) {
        helper.setText(R.id.select_coupon_name_tv, item.getCouponName());
        if (item.isCouponSelectStatus()) {
            helper.setImageResource(R.id.select_coupon_iv, R.mipmap.coupon_selected);
        } else {
            helper.setImageResource(R.id.select_coupon_iv, R.drawable.coupon_unselected_circel_yellow_shape);
        }
    }
}
