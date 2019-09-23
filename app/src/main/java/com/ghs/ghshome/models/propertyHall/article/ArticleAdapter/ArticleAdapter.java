package com.ghs.ghshome.models.propertyHall.article.ArticleAdapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ghs.ghshome.R;
import com.ghs.ghshome.bean.PropertyListBean;
import com.ghs.ghshome.tools.StrUtils;

import java.util.List;

public class ArticleAdapter extends BaseQuickAdapter<PropertyListBean.DataBean.RowsBean, BaseViewHolder> {

    public ArticleAdapter(int layoutResId, @Nullable List<PropertyListBean.DataBean.RowsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, PropertyListBean.DataBean.RowsBean item) {

        helper.setText(R.id.properpty_roomname_tv, StrUtils.isStringValueOk(item.getRoomFullName())?item.getRoomFullName():"暂无记录");
        helper.setText(R.id.properpty_name_tv, StrUtils.isStringValueOk(item.getFullName())?item.getFullName():"暂无记录");
        helper.setText(R.id.properpty_mobile_tv, StrUtils.isStringValueOk(item.getMobile())?item.getMobile():"暂无记录");
        helper.setText(R.id.properpty_content_tv, StrUtils.isStringValueOk(item.getContent() )?item.getContent() :"暂无记录");
        helper.setText(R.id.properpty_state_tv, StrUtils.isStringValueOk(getState(item.getState()))?getState(item.getState()) :"暂无记录");

    }

    public String getState(int state) {
        String states = "";
        switch (state) {
            case 1:
                states = "审核中";
                break;

            case 2:
                states = "通过";
                break;

            case 3:
                states = "不通过";
                break;


        }

        return states;

    }
}
