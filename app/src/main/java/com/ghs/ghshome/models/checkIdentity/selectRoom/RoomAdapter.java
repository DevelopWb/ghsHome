package com.ghs.ghshome.models.checkIdentity.selectRoom;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ghs.ghshome.R;
import com.ghs.ghshome.bean.RoomsBean;

/**
 * Author:wang_sir
 * Time:2019/7/19 16:33
 * Description:This is 单元适配器
 */
public class RoomAdapter extends BaseQuickAdapter<RoomsBean.DataBean, BaseViewHolder> {
    public RoomAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, RoomsBean.DataBean item) {
        helper.setText(R.id.simple_text_tv,item.getRoomNumber());
    }
}
