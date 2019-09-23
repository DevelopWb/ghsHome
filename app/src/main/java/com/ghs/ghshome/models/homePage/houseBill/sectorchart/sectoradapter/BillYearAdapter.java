package com.ghs.ghshome.models.homePage.houseBill.sectorchart.sectoradapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ghs.ghshome.R;

/**
 * Author:wang_sir
 * Time:2018/8/1 17:14
 * Description:This is BillYearAdapter
 */
public class BillYearAdapter extends BaseQuickAdapter<String,BaseViewHolder> {
    public BillYearAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.bill_statistical_year_tv,item);
    }
}
