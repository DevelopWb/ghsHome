package com.ghs.ghshome.models.homePage.houseBill;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ghs.ghshome.R;
import com.ghs.ghshome.bean.FuctionBean;

/**
 * Author:wang_sir
 * Time:2018/7/18 13:24
 * Description:This is MyBillAdapter
 */
public class MyBillAdapter extends BaseQuickAdapter<FuctionBean, BaseViewHolder> {
    public MyBillAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, FuctionBean item) {

        helper.setText(R.id.mine_tv,item.getName());
        helper.setImageResource(R.id.mine_iv,item.getResourceId());
    }
}
