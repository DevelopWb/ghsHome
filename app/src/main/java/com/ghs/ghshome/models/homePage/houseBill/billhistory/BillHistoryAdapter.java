package com.ghs.ghshome.models.homePage.houseBill.billhistory;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ghs.ghshome.R;
import com.ghs.ghshome.bean.MyBillInfolBean;
import com.ghs.ghshome.tools.PubUtil;

/**
 * Author:wang_sir
 * Time:2018/7/18 14:46
 * Description:This is BillHistoryAdapter
 */
public class BillHistoryAdapter extends BaseQuickAdapter<MyBillInfolBean.DataBean, BaseViewHolder> {
    public BillHistoryAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, MyBillInfolBean.DataBean item) {
        switch (PubUtil.MINE_BILL_TAG) {
            case 1:
                helper.setImageResource(R.id.mine_bill_mark_iv, R.mipmap.mine_bill_water_fee);
                break;
            case 2:
                helper.setImageResource(R.id.mine_bill_mark_iv, R.mipmap.mine_bill_electricity_fee);
                break;
            case 3:
                helper.setImageResource(R.id.mine_bill_mark_iv, R.mipmap.mine_bill_property_fee);
                break;
            default:
                break;
        }
        helper.setText(R.id.mine_bill_name, item.getTitle());
        helper.setText(R.id.mine_bill_time,item.getPayTime());
        helper.setText(R.id.mine_bill_sum_tv,"-"+String.valueOf(item.getPayMoney()));
    }
}
