package com.ghs.ghshome.models.homePage.oneKeyCall;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ghs.ghshome.R;
import com.ghs.ghshome.bean.WorkOrderDesBean;

/**
 * Author:wang_sir
 * Time:2019/5/23 18:10
 * Description:This is OrderStatusAdapter
 */
public class OrderStatusDetailAdapter extends BaseQuickAdapter<WorkOrderDesBean.DataBean.StatusRecordListBean, BaseViewHolder> {
    public OrderStatusDetailAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, WorkOrderDesBean.DataBean.StatusRecordListBean item) {
        helper.setText(R.id.order_status_value_tv, item.getContent());
        helper.setText(R.id.order_deal_time_tv, item.getCreateTime());
    }
}
