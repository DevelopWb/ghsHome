package com.ghs.ghshome.models.checkIdentity;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ghs.ghshome.R;
import com.ghs.ghshome.bean.VillageListBean;

/**
 * Author:wang_sir
 * Time:2019/7/19 16:33
 * Description:This is SearchRommAdapter
 */
public class SelectVillageAdapter extends BaseQuickAdapter<VillageListBean.DataBean, BaseViewHolder> {
    public SelectVillageAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, VillageListBean.DataBean dataBean) {
        helper.setText(R.id.simple_text_tv,dataBean.getVillageName());
    }
}
