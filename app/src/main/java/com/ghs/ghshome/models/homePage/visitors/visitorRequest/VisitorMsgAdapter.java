package com.ghs.ghshome.models.homePage.visitors.visitorRequest;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ghs.ghshome.R;
import com.ghs.ghshome.bean.KeyValueBean;

/**
 * Author:wang_sir
 * Time:2018/11/16 17:27
 * Description:This is VisitorAdapter
 */
public class VisitorMsgAdapter extends BaseQuickAdapter<KeyValueBean, BaseViewHolder> {
    public VisitorMsgAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper,KeyValueBean item) {
        helper.setText(R.id.visitor_msg_key_tv, item.getKey());
        helper.setText(R.id.visitor_msg_value_tv, item.getValue());
    }
}
