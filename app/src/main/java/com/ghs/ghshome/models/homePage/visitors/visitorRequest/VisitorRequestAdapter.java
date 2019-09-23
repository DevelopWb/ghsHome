package com.ghs.ghshome.models.homePage.visitors.visitorRequest;

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
public class VisitorRequestAdapter extends BaseQuickAdapter<VisitorsBean.DataBean, BaseViewHolder> {
    public VisitorRequestAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, VisitorsBean.DataBean item) {
        helper.setText(R.id.visitor_name_tv, item.getVisitorName());
        helper.setText(R.id.visitor_mobile, item.getVisitorMobile());
        String  arriveTime = item.getVisitDay();
        if (StrUtils.isStringValueOk(arriveTime)) {
            helper.setText(R.id.visitor_arrived_tv, CalendarUtil.getTimeFromStringTime("yyyy-MM-dd","yyyy-MM-dd HH:mm:ss",arriveTime));
        }


    }
}
