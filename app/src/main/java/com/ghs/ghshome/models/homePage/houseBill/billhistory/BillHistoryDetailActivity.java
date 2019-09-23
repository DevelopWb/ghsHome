package com.ghs.ghshome.models.homePage.houseBill.billhistory;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ghs.ghshome.R;
import com.ghs.ghshome.base.BaseActivity;
import com.ghs.ghshome.base.network.RequestStatus;
import com.ghs.ghshome.bean.KeyValueBean;
import com.ghs.ghshome.bean.MyBillInfolBean;
import com.ghs.ghshome.bean.PropertyTelBean;
import com.ghs.ghshome.models.homePage.HomePageContract;
import com.ghs.ghshome.models.homePage.houseBill.houseBillUnpay.BillUnPayChildAdapter;
import com.ghs.ghshome.models.main.MainModel;
import com.ghs.ghshome.tools.StrUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * created by tobato
 * created date 2019/6/4 10:27.
 * application   历史账单详情
 */
public class BillHistoryDetailActivity extends BaseActivity<BillHistoryContract.IBaseBillInfoView, BillHistoryPresent> implements BillHistoryContract.IBaseBillInfoView, View.OnClickListener, RequestStatus {

    private ImageView mBillDetailIconIv;
    /**
     * 物业名称
     */
    private TextView mBillDetailNameTv;
    /**
     * -100
     */
    private TextView mBillDetailAccountTv;
    /**
     * -100
     */
    private TextView mBillDetailDealStatusTv;
    private RecyclerView mBillDetailRv;
    /**
     * 153321312
     */
    private TextView mBillDetailPropertyTelTv;
    private BillUnPayChildAdapter adapter;
    private String telNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void setLayout() {
        setContentView(R.layout.activity_bill_history_detail);
        initView();
        initActionBar("账单详情", null);
    }

    @Override
    public BillHistoryPresent creatPresenter() {
        return new BillHistoryPresent();
    }

    @Override
    public void getDate() {
        new MainModel().customerServerTel(this,HomePageContract.CUSTOMER_SERVER_TEL);
        if (getIntent() != null) {
            int orderId = getIntent().getIntExtra(BillHistoryContract.BILL_HIS_ORDERID, 0);
            getPresenter().getBillDetail(orderId, "");
        }
    }

    @Override
    public void startLoading(String tag) {

    }

    @Override
    public void stopLoading(String tag) {

    }

    @Override
    public void updateView(Object o, String tag) {

        MyBillInfolBean.DataBean bean = (MyBillInfolBean.DataBean) o;
        if (bean != null) {
            adapter.setNewData(getChildAdapterData(bean));
            String type = bean.getType();
            switch (type) {
                case "water":
                    mBillDetailIconIv.setImageResource(R.mipmap.mine_bill_water_fee);
                    break;
                case "ammeter":
                    mBillDetailIconIv.setImageResource(R.mipmap.mine_bill_electricity_fee);
                    break;
                case "property":
                    mBillDetailIconIv.setImageResource(R.mipmap.mine_bill_property_fee);
                    break;
                default:
                    break;
            }
            mBillDetailNameTv.setText(bean.getPropertyName());
            mBillDetailAccountTv.setText("-" + String.valueOf(bean.getPayMoney()));
            mBillDetailDealStatusTv.setText("交易成功");

        }

    }


    @Override
    public void onStart(String tag) {

    }

    @Override
    public void onSuccess(Object o, String tag) {
        PropertyTelBean telBean = (PropertyTelBean) o;
        if (telBean != null) {
            PropertyTelBean.DataBean dataBean = telBean.getData();
            telNum = dataBean.getMobile();
            String content = "联系客服：" + telNum;
            StrUtils.setTextPartColor(mBillDetailPropertyTelTv, content, 5, content.length(), "#2AA146");
        }
    }

    @Override
    public void onError(String tag) {

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
        arrays.add(new KeyValueBean("付款方式：", "微信支付"));
        arrays.add(new KeyValueBean("付款时间：", item.getPayTime()));
        arrays.add(new KeyValueBean("账单分类：", getBillTypeName(item)));
        arrays.add(new KeyValueBean("创建时间：", item.getCreateTime()));
        arrays.add(new KeyValueBean("订单编号：", item.getOrderNum()));
        arrays.add(new KeyValueBean("业主名：", item.getOwnerName()));
        arrays.add(new KeyValueBean("费用地址：",item.getVillageMsg()));
//        arrays.add(new KeyValueBean("备注：",item.getTitle()));
        return arrays;
    }

    /**
     * 获取账单类型
     *
     * @param item
     * @return
     */
    private String getBillTypeName(MyBillInfolBean.DataBean item) {
        String billName = "";
        switch (item.getType()) {
            case "water":
                billName = "水费";
                break;
            case "property":
                billName = "物业费";
                break;
            case "ammeter":
                billName = "电费";
                break;
            default:
                break;
        }
        return billName;
    }

    private void initView() {
        mBillDetailIconIv = (ImageView) findViewById(R.id.bill_detail_icon_iv);
        mBillDetailNameTv = (TextView) findViewById(R.id.bill_detail_name_tv);
        mBillDetailAccountTv = (TextView) findViewById(R.id.bill_detail_account_tv);
        mBillDetailDealStatusTv = (TextView) findViewById(R.id.bill_detail_deal_status_tv);
        mBillDetailRv = (RecyclerView) findViewById(R.id.bill_detail_rv);
        mBillDetailPropertyTelTv = (TextView) findViewById(R.id.bill_detail_property_tel_tv);
        adapter = new BillUnPayChildAdapter(R.layout.bill_unpay_child_item);
        initRecyclerview(mBillDetailRv, adapter, LinearLayoutManager.VERTICAL, false);
        mBillDetailPropertyTelTv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.bill_detail_property_tel_tv:
                if (StrUtils.isStringValueOk(telNum)) {
                    makeAPhoneCall(telNum);
                }
                break;
        }
    }
}

