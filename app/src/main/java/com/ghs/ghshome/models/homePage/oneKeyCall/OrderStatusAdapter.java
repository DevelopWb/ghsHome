package com.ghs.ghshome.models.homePage.oneKeyCall;

import android.support.v4.content.ContextCompat;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ghs.ghshome.R;
import com.ghs.ghshome.bean.WorkOrderStatusValueBean;
import com.ghs.ghshome.tools.PubUtil;

/**
 * Author:wang_sir
 * Time:2019/5/23 18:10
 * Description:This is OrderStatusAdapter
 */
public class OrderStatusAdapter extends BaseQuickAdapter<WorkOrderStatusValueBean, BaseViewHolder> {
    public OrderStatusAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, WorkOrderStatusValueBean item) {
        int position = helper.getAdapterPosition();
        LinearLayout linearLayout = helper.getView(R.id.order_status_item_ll);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(linearLayout.getLayoutParams());
        if (0 == position) {
            layoutParams.setMargins(0, 0, 0, 0);
            helper.setGone(R.id.order_status_line_v, false);
        } else {
            int size = PubUtil.dip2px(mContext, -18);
            layoutParams.setMargins(size, 0, 0, 0);
            helper.setGone(R.id.order_status_line_v, true);
        }
        linearLayout.setLayoutParams(layoutParams);

        String value = item.getStatusValue();
        helper.setText(R.id.order_status_tv, value);
        boolean isLight = item.isLight();
        if (isLight) {
            helper.setBackgroundColor(R.id.order_status_line_v, ContextCompat.getColor(mContext, R.color.tab_bg));
            helper.setImageResource(R.id.order_status_point_iv, R.drawable.sp_default_green_circle);
            helper.setTextColor(R.id.order_status_tv, ContextCompat.getColor(mContext, R.color.tab_bg));
        } else {
            helper.setBackgroundColor(R.id.order_status_line_v,ContextCompat.getColor(mContext, R.color.gray_deeper));
            helper.setImageResource(R.id.order_status_point_iv, R.drawable.gray_circle_shape);
            helper.setTextColor(R.id.order_status_tv, ContextCompat.getColor(mContext, R.color.gray_deeper));
        }


    }
}
