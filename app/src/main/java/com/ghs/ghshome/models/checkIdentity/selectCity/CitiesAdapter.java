package com.ghs.ghshome.models.checkIdentity.selectCity;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ghs.ghshome.R;
import com.ghs.ghshome.bean.CitiesBean;
import com.ghs.ghshome.tools.PubUtil;

/**
 * Author:wang_sir
 * Time:2019/7/19 16:33
 * Description:This is 城市内容列表
 */
public class CitiesAdapter extends BaseQuickAdapter<CitiesBean.DataBean.CityListBean, BaseViewHolder> {
    public CitiesAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, CitiesBean.DataBean.CityListBean dataBean) {
        helper.setText(R.id.simple_text_tv,dataBean.getCityName());
        helper.setBackgroundRes(R.id.simple_text_tv,R.drawable.bg_white_only_bottom_gray_shape_1px);
        helper.getView(R.id.simple_text_tv).setPadding(PubUtil.dip2px(mContext,20),PubUtil.dip2px(mContext,15),PubUtil.dip2px(mContext,20),PubUtil.dip2px(mContext,15));

    }
}
