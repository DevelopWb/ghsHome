package com.ghs.ghshome.models.homePage;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ghs.ghshome.R;
import com.ghs.ghshome.bean.HomePageHouseKeeperBean;
import com.ghs.ghshome.tools.PubUtil;

/**
 * Author:wang_sir
 * Time:2019/5/20 10:37
 * Description:This is HomePageHouseKeeperAdapter   管家服务适配器
 */
public class HomePageHouseKeeperAdapter extends BaseQuickAdapter<HomePageHouseKeeperBean.DataBean.HousekeeperListBean, BaseViewHolder> {
    public HomePageHouseKeeperAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomePageHouseKeeperBean.DataBean.HousekeeperListBean item) {
        helper.setText(R.id.server_title_tv, PubUtil.getMsgTypeName(item.getMsgType()));
        helper.setText(R.id.server_title_des_tv, item.getTitile());
        helper.setText(R.id.server_warn_time_tv, item.getCreateTimeStr());
    }
}
