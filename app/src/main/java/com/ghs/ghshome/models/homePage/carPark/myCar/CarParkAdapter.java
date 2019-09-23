package com.ghs.ghshome.models.homePage.carPark.myCar;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ghs.ghshome.R;
import com.ghs.ghshome.bean.BoundCarsBean;
import com.ghs.ghshome.tools.StrUtils;

/**
 * Author:wang_sir
 * Time:2018/11/12 16:04
 * Description:This is CarParkAdapter
 * 我的车辆 绑定的
 */
public class CarParkAdapter extends BaseQuickAdapter<BoundCarsBean.DataBean, BaseViewHolder> {
    public CarParkAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, BoundCarsBean.DataBean item) {
        helper.setText(R.id.car_N0_tv, StrUtils.initCarNumStatus(item.getCarNum()));
    }
}
