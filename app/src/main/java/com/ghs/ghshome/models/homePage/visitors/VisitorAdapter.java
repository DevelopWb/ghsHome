package com.ghs.ghshome.models.homePage.visitors;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ghs.ghshome.R;
import com.ghs.ghshome.bean.VisitorsBean;
import com.ghs.ghshome.tools.CalendarUtil;
import com.ghs.ghshome.tools.StrUtils;

/**
 * Author:wang_sir
 * Time:2018/11/16 17:27
 * Description:This is VisitorAdapter
 */
public class VisitorAdapter extends BaseQuickAdapter<VisitorsBean.DataBean, BaseViewHolder> {
    public VisitorAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, VisitorsBean.DataBean item) {
        helper.setText(R.id.visitor_name_tv, item.getVisitorName());
        String arrivedDate = item.getVisitDay() != null ? CalendarUtil.getTimeFromStringTime("yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", item.getVisitDay()) : "----";
        helper.setText(R.id.visitor_arrived_tv, arrivedDate);
        String mobile = StrUtils.isStringValueOk(item.getVisitorMobile()) ?item.getVisitorMobile() :"暂无记录";
        helper.setText(R.id.visitor_mobile, mobile);
    }
}
