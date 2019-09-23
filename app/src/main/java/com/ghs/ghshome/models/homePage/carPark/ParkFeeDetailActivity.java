package com.ghs.ghshome.models.homePage.carPark;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ghs.ghshome.R;
import com.ghs.ghshome.base.BaseActivity;
import com.ghs.ghshome.base.network.RequestStatus;
import com.ghs.ghshome.bean.CarPayRecord;
import com.ghs.ghshome.bean.KeyValueBean;
import com.ghs.ghshome.bean.PropertyTelBean;
import com.ghs.ghshome.models.homePage.HomePageContract;
import com.ghs.ghshome.models.homePage.houseBill.houseBillUnpay.BillUnPayChildAdapter;
import com.ghs.ghshome.models.main.MainModel;
import com.ghs.ghshome.tools.IntentUtil;
import com.ghs.ghshome.tools.StrUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * created by tobato
 * created date 2019/8/20 15:59.
 * application   停车费详情
 */
public class ParkFeeDetailActivity extends BaseActivity<CarParkContract.ICarParkView, CarParkPresent> implements CarParkContract.ICarParkView , View.OnClickListener, RequestStatus {
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
        initActionBar("账单详情",null);
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
        mBillDetailIconIv.setImageResource(R.mipmap.carpark_icon);
    }
    @Override
    public CarParkPresent creatPresenter() {
        return new CarParkPresent();
    }

    @Override
    public void getDate() {
       new MainModel().customerServerTel(this,HomePageContract.CUSTOMER_SERVER_TEL);
        CarPayRecord.DataBean.CarparkTempOrderDOListBean bean = (CarPayRecord.DataBean.CarparkTempOrderDOListBean) IntentUtil.getInstance().getIntentParcelableData(this);
        if (bean != null) {
            adapter.setNewData(getChildAdapterData(bean));
            mBillDetailNameTv.setText(bean.getPropertyName());
            if (bean.getPayMoney()>0) {
                mBillDetailAccountTv.setVisibility(View.VISIBLE);
                mBillDetailAccountTv.setText("-" + String.valueOf(bean.getPayMoney()));
            }else{
                mBillDetailAccountTv.setVisibility(View.GONE);
            }
            int  state = bean.getPayState();
            //支付状态：2:未支付 3:支付中 4:已支付
            switch (state) {
                case 2:
                    mBillDetailDealStatusTv.setText("未支付");
                    break;
                case 3:
                    mBillDetailDealStatusTv.setText("支付中");
                    break;
                case 4:
                    mBillDetailDealStatusTv.setText("交易成功");
                    break;
                default:
                    break;
            }

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

    /**
     * 获取adapter数据
     *
     * @param item
     * @return
     */
    private List<KeyValueBean> getChildAdapterData( CarPayRecord.DataBean.CarparkTempOrderDOListBean  item) {
        List<KeyValueBean> arrays = new ArrayList<>();
        arrays.add(new KeyValueBean("费用项目：", "停车费"));
        arrays.add(new KeyValueBean("付款方式：", "微信支付"));
        arrays.add(new KeyValueBean("付款时间：", item.getPaySuccessTime()));
        arrays.add(new KeyValueBean("账单分类：", "停车费"));
//        arrays.add(new KeyValueBean("创建时间：", item.getCreateTime()));
        arrays.add(new KeyValueBean("订单号：", item.getOrderNum()));
        int  usedSeedAmount = item.getUseSeedNum();
        if (usedSeedAmount>0) {
            arrays.add(new KeyValueBean("抵扣方式：", "光合种子"));
            arrays.add(new KeyValueBean("抵扣金额：",String.valueOf(item.getSeedDiscountMoney())));
        }

        arrays.add(new KeyValueBean("车主：",item.getUserName()));
        return arrays;
    }
}
