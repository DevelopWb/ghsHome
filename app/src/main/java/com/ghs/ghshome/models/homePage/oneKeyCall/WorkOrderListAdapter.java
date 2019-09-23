package com.ghs.ghshome.models.homePage.oneKeyCall;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ghs.ghshome.R;
import com.ghs.ghshome.bean.WorkOrderBean;
import com.ghs.ghshome.tools.PubUtil;
import com.ghs.ghshome.tools.StrUtils;

/**
 * Author:wang_sir
 * Time:2019/5/23 15:58
 * Description:This is WorkOrderListAdapter
 */
public class WorkOrderListAdapter extends BaseQuickAdapter<WorkOrderBean.DataBean, BaseViewHolder> {
    private boolean dividerTopShow;

    public WorkOrderListAdapter(int layoutResId, boolean dividerTopShow) {
        super(layoutResId);
        this.dividerTopShow = dividerTopShow;
    }

    @Override
    protected void convert(BaseViewHolder helper, WorkOrderBean.DataBean item) {
        int type = item.getServiceType();
        int position = helper.getAdapterPosition();
        if (!dividerTopShow) {
            //一键呼叫
            if (position == 0) {
                helper.setGone(R.id.work_order_divider_v, false);
            } else {
                helper.setGone(R.id.work_order_divider_v, true);
            }
        } else {
            //我的工单中 顶部的分割线需要显示
            helper.setGone(R.id.work_order_divider_v, true);
        }

        String orderName = PubUtil.getOrderTypeName(type);
        helper.setText(R.id.order_type_tv, orderName);
        helper.setText(R.id.feedback_time_tv, item.getCreateTime());
        helper.setText(R.id.order_content_tv, item.getContent());
        String repairPeople = item.getRepairUserName();
        repairPeople = StrUtils.isStringValueOk(repairPeople) ? "跟进人：" + repairPeople : "跟进人：暂无跟进人";
        helper.setText(R.id.processing_person_tv, repairPeople);
        helper.setText(R.id.processing_person_tel_tv, item.getRepairUserMobile());
        helper.setText(R.id.order_type_value_tv, getStatus(item.getState()));

    }

    /**
     * 获取状态名
     *
     * @param status
     * @return
     */
    private String getStatus(int status) {
        String statusName = "";
        switch (status) {
            case 1:
                statusName = "跟进中";
                break;
            case 2:
                statusName = "已完成";
                break;
            case 3:
                statusName = "待跟进";
                break;
            case 4:
                statusName = "跟进中";
                break;
            default:
                break;
        }
        return statusName;
    }
}
