package com.ghs.ghshome.models.homePage.houseKeeperNotice;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ghs.ghshome.R;
import com.ghs.ghshome.bean.HouseKeeperBean;
import com.ghs.ghshome.models.homePage.HomePageContract;
import com.ghs.ghshome.tools.PubUtil;

/**
 * Author:wang_sir
 * Time:2019/5/20 10:37
 * Description:This is HomePageHouseKeeperAdapter   管家服务适配器
 */
public class HouseKeeperNoticeAdapter extends BaseQuickAdapter<HouseKeeperBean.DataBean, BaseViewHolder> {
    public HouseKeeperNoticeAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, HouseKeeperBean.DataBean item) {
        String noticeType = item.getMsgType();
        if (HomePageContract.USER_AUTH.equals(noticeType)) {
         helper.setGone(R.id.house_keeper_see_more_ll,false);
        }else{
            helper.setGone(R.id.house_keeper_see_more_ll,true);
        }
        helper.setText(R.id.house_keeper_creat_time_tv, item.getCreateTimeStr());
        helper.setText(R.id.house_keeper_type_tv, PubUtil.getMsgTypeName(item.getMsgType()));
        helper.setText(R.id.house_keeper_title_tv, item.getTitile());
        helper.setText(R.id.house_keeper_content_tv, item.getContent());

    }


}
