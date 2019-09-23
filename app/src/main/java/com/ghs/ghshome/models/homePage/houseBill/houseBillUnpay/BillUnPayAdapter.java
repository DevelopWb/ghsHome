package com.ghs.ghshome.models.homePage.houseBill.houseBillUnpay;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ghs.ghshome.R;
import com.ghs.ghshome.bean.KeyValueBean;
import com.ghs.ghshome.bean.MyBillInfolBean;
import com.ghs.ghshome.tools.UserInfoUtil;

import java.util.ArrayList;
import java.util.List;


/**
 * Author:wang_sir
 * Time:2018/6/29 11:16
 * Description:This is   未支付订单
 */

public class BillUnPayAdapter extends BaseQuickAdapter<MyBillInfolBean.DataBean, BaseViewHolder> {


    public BillUnPayAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, MyBillInfolBean.DataBean item) {
        RecyclerView mRecyclerView = helper.getView(R.id.my_bill_rv);
        BillUnPayChildAdapter adapter = new BillUnPayChildAdapter(R.layout.bill_unpay_child_item);
        LinearLayoutManager manager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(manager);
        adapter.setNewData(getChildAdapterData(item));
        String billName = getBillName(item);
        helper.setText(R.id.bill_unpay_title_tv, billName);
        helper.setText(R.id.bill_unpay_total_amount_should_pay_tv, String.valueOf(item.getPayMoney()) + "元");
        helper.addOnClickListener(R.id.bill_unpay_pay_tv);
        helper.setText(R.id.bill_unpay_pay_tv, getDealStatus(item));
    }

    /**
     * 获取交易状态
     *
     * @param bean
     * @return 交易状态 1:待核对、2:未支付、3：支付中、4:已支付、5：关闭
     */
    private String getDealStatus(MyBillInfolBean.DataBean bean) {
        String payStatus = "";
        int payState = bean.getPayState();
        int  payUserId = bean.getPayUser();
        switch (payState) {
            case 1:
                payStatus = "交易待核对";
                break;
            case 2:
                payStatus = "立即缴费";
                break;
            case 3:
                if (payUserId== UserInfoUtil.getInstance().getUserId()) {
                    payStatus = "继续缴费";
                }else{
                    payStatus = "缴费中";
                }

                break;
            case 4:
                payStatus = "已缴费";
                break;
            case 5:
                payStatus = "交易关闭";
                break;
            default:
                break;
        }
        return payStatus;
    }

    /**
     * 获取账单名称
     *
     * @param item
     * @return
     */
    private String getBillName(MyBillInfolBean.DataBean item) {
        String billName = "";
        switch (item.getType()) {
            case "property":
                billName = "物业管理费";
                break;
            case "water":
                billName = "待交水费";
                break;
            case "ammeter":
                billName = "待交电费";
                break;
            default:
                break;
        }
        return billName;
    }

    /**
     * 获取adapter数据
     *
     * @param item
     * @return
     */
    private List<KeyValueBean> getChildAdapterData(MyBillInfolBean.DataBean item) {
        List<KeyValueBean> arrays = new ArrayList<>();
        arrays.add(new KeyValueBean("费用项目：", item.getTitle()));
        arrays.add(new KeyValueBean("收费单位：", item.getPropertyName()));
        arrays.add(new KeyValueBean("创建时间：", item.getCreateTime()));
        arrays.add(new KeyValueBean("订单编号：", item.getOrderNum()));
        arrays.add(new KeyValueBean("业主名：", item.getOwnerName()));
        arrays.add(new KeyValueBean("费用地址：",item.getVillageMsg()));
        arrays.add(new KeyValueBean("总金额（元）：", String.valueOf(item.getPayMoney())));
//        arrays.add(new KeyValueBean("备注：",item.getTitle()));
        return arrays;
    }
}

