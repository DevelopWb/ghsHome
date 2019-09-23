package com.ghs.ghshome.models.propertyHall.decoration;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ghs.ghshome.R;
import com.ghs.ghshome.bean.DecorationBean;

import java.util.List;

public class DecorationAdapter extends BaseQuickAdapter<DecorationBean.DataBean.RowsBean, BaseViewHolder> {

    public DecorationAdapter(int layoutResId, @Nullable List<DecorationBean.DataBean.RowsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DecorationBean.DataBean.RowsBean item) {
        helper.setText(R.id.decoration_type, item.getType() == 1 ? "装修备案申请" : "动火备案申请");
        helper.setText(R.id.decoration_house, item.getRoomFullName());
        helper.setText(R.id.decoration_time, item.getCreateTime());
        helper.setText(R.id.decoration_name, item.getUserName());
        helper.setText(R.id.decoration_state, setState(item.getType() == 1 ? "装修备案申请" : "动火备案申请", item.getState()));
    }

    /**
     * 动火状态：1.审核中、2.审核不通过、3.动火监控中、4.已结束、5.已取消；
     * 装修状态：1.审核中，2.审核不通过，3.待签署协议、4.装修监控中、5.待验收、
     * 6.延期待备案、7.延期已备案、8.验收失败、9.验收通过、10.已结束、11.已取消
     *
     * @param type
     * @param state
     * @return
     */
    public String setState(String type, int state) {
        String states = "";
        switch (state) {
            case 1:
                states = "审核中";
                break;

            case 2:
                states = "审核不通过";
                break;
            case 3:

                if ("装修备案申请".equals(type)) {

                    states = "待签署协议";
                }else {

                    states = "动火监控中";
                }

                break;


            case 4:

                if ("装修备案申请".equals(type)) {
                    states = "装修监控中";
                }else {
                    states = "已结束";
                }

                break;

            case 5:
                if ("装修备案申请".equals(type)) {
                    states = "待验收";
                }else {
                    states = "已取消";
                }

                break;

            case 6:
                states = "延期待备案";
                break;

            case 7:
                states = "延期已备案";
                break;


            case 8:
                states = "验收失败";
                break;


            case 9:
                states = "验收通过";
                break;

            case 10:
                states = "已结束";
                break;
            case 11:
                states = "已取消";
                break;
        }

        return states;
    }
}
