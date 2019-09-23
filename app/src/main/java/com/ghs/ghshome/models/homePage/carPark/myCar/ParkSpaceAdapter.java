package com.ghs.ghshome.models.homePage.carPark.myCar;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ghs.ghshome.R;
import com.ghs.ghshome.bean.ParkSpaceBean;
import com.ghs.ghshome.tools.CalendarUtil;
import com.ghs.ghshome.tools.StrUtils;

/**
 * Author:wang_sir
 * Time:2018/11/12 16:04
 * Description:This is CarParkAdapter
 * 我的车位
 */
public class ParkSpaceAdapter extends BaseQuickAdapter<ParkSpaceBean.DataBean, BaseViewHolder> {
    public ParkSpaceAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, ParkSpaceBean.DataBean item) {
        helper.setText(R.id.park_space_name_tv,item.getAreaName()+":"+item.getCarportNum());
        helper.setText(R.id.park_space_type_tv,"绑定类型:"+(1==item.getBindType()?"产权人":"租赁人"));
        String  startTime = item.getStartTime();
        if (StrUtils.isStringValueOk(startTime)) {
            startTime = CalendarUtil.getTimeFromStringTime("yyyy-MM-dd","yyyy-MM-dd HH:mm:ss",startTime);
        }
        helper.setText(R.id.park_space_effective_start_tv,"开始时间:"+startTime);
        String  endTime = item.getEndTime();
        if (StrUtils.isStringValueOk(endTime)) {
            endTime = CalendarUtil.getTimeFromStringTime("yyyy-MM-dd","yyyy-MM-dd HH:mm:ss",endTime);
        }
        helper.setText(R.id.park_space_effective_end_tv,"结束时间:"+endTime);

    }
}
