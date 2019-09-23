package com.ghs.ghshome.models.checkIdentity.selectRoom;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ghs.ghshome.R;
import com.ghs.ghshome.bean.CellsBean;

/**
 * Author:wang_sir
 * Time:2019/7/19 16:33
 * Description:This is 单元适配器
 */
public class CellAdapter extends BaseQuickAdapter<CellsBean.DataBean, BaseViewHolder> {
    public CellAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, CellsBean.DataBean item) {
        helper.setText(R.id.simple_text_tv,item.getCellFullName());
    }
}
