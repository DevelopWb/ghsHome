package com.ghs.ghshome.models.homePage.carPark.payfee;

import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ghs.ghshome.R;
import com.ghs.ghshome.base.BaseActivity;
import com.ghs.ghshome.base.network.RequestStatus;
import com.ghs.ghshome.bean.CouponsBean;
import com.ghs.ghshome.bean.OutParkBean;
import com.ghs.ghshome.bean.SelectCouponBean;
import com.ghs.ghshome.bean.WXPayResultBean;
import com.ghs.ghshome.models.homePage.carPark.CarParkContract;
import com.ghs.ghshome.models.homePage.carPark.CarParkPresent;
import com.ghs.ghshome.models.homePage.houseBill.SelectCouponsAdapter;
import com.ghs.ghshome.tools.ActivityResultManager;
import com.ghs.ghshome.tools.DividerItemDecoration;
import com.ghs.ghshome.tools.HawkProperty;
import com.ghs.ghshome.tools.PubUtil;
import com.ghs.ghshome.tools.StrUtils;
import com.ghs.ghshome.tools.UserInfoUtil;
import com.orhanobut.hawk.Hawk;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class PayFeeActivity extends BaseActivity<CarParkContract.ICarParkView, CarParkPresent> implements View.OnClickListener, CarParkContract.ICarParkView, RequestStatus {

    /**
     * 京N.1523322
     */
    private TextView mPayFeeCarNoTv;
    /**
     * 2018.3.6
     */
    private TextView mPayFeeDateTv;
    /**
     * 2018.3.6
     */
    private TextView mPayFeeTimeTv;
    /**
     * 停车位
     */
    private TextView mPayFeeStayTimeTv;
    /**
     * 停车费 3元/小时
     */
    private TextView mPayFeeStandardTv;
    /**
     * 6.00元
     */
    private TextView mPayFeeSumTv;
    /**
     * 优惠券
     */
    private TextView mPayFeeCouponsTv;
    /**
     * 不使用优惠券 >
     */
    private TextView mPayFeeSelectCouponsTv;
    /**
     * 6.00元
     */
    private TextView mPayFeePaySumTv;
    /**
     * 支付
     */
    private TextView mPayFeeTv;
    private OutParkBean.DataBean dataBean;
    private SelectCouponBean selectCouponBean;
    private CheckBox mSelectedWeixinCb;
    private RelativeLayout mWeixinPayRl;
    private RelativeLayout mAliPayRl;
    private TextView mConfirmPayTv;
    private CheckBox mSelectedAliPayCb;
    private BottomSheetDialog bottomSheetDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void getDate() {
        dataBean = getIntent().getParcelableExtra(ActivityResultManager.OUT_PARK_BEAN);
        if (dataBean != null) {
            mPayFeeCarNoTv.setText(StrUtils.initCarNumStatus(dataBean.getCarNum()));
            mPayFeeDateTv.setText(dataBean.getServiceDay());
            mPayFeeTimeTv.setText(dataBean.getServiceTime());
            String totalFee = StrUtils.formatDoubleData(dataBean.getTotalFee()) + "元";
            mPayFeeSumTv.setText(totalFee);
            mPayFeeStayTimeTv.setText(dataBean.getServiceHoursAndMinute());
            mPayFeePaySumTv.setText(StrUtils.formatDoubleData(dataBean.getTotalFee()) + "元");
            mPayFeeStandardTv.setText("停车费：" + Hawk.get(HawkProperty.CARPART_STANDED));
        }

    }


    @Override
    protected void onResume() {
        super.onResume();
        switch (PubUtil.CAR_WEI_XIN_PAY_TAG) {
            case ActivityResultManager.WEI_XIN_PAY_SUCCESS://缴费成功
                payFinished(0);

                break;
            case ActivityResultManager.WEI_XIN_PAY_ERROR://缴费失败
                payFinished(1);

                break;
            default:
                break;
        }
    }

    /**
     * 支付结束后的提示  0代表成功  1代表失败
     */
    private void payFinished(final int state) {
        //这个是自定义对话框
        View view = LayoutInflater.from(this).inflate(R.layout.pay_error_layout, null);
        final AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setView(view)
                .create();
        alertDialog.show();
        //设置弹出框的长宽
        if (alertDialog.getWindow() != null) {
            alertDialog.getWindow().setLayout(PubUtil.dip2px(this, 200), PubUtil.dip2px(this, 170));
        }

        TextView confirm = view.findViewById(R.id.pay_confirm_tv);
        TextView stateText = view.findViewById(R.id.pay_state_tv);
        if (0 == state) {
            stateText.setText("缴费成功");
        } else {
            stateText.setText("缴费失败");

        }
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
                if (0 == state) {
                    setResult(ActivityResultManager.PARKING_PAYED);
                    finish();
                }
            }
        });
    }

    @Override
    public void initLayoutView() {
        initView();
        initActionBar("支付车费", null);
    }

    @Override
    protected void onDestroy() {
        PubUtil.CAR_WEI_XIN_PAY_TAG = "";
        super.onDestroy();
    }

    @Override
    public void setLayout() {
        setContentView(R.layout.activity_pay_fee);

    }

    @Override
    public CarParkPresent creatPresenter() {
        return new CarParkPresent();
    }


    private void initView() {
        mPayFeeCarNoTv = (TextView) findViewById(R.id.pay_fee_car_no_tv);
        mPayFeeDateTv = (TextView) findViewById(R.id.pay_fee_date_tv);
        mPayFeeTimeTv = (TextView) findViewById(R.id.pay_fee_time_tv);
        mPayFeeStayTimeTv = (TextView) findViewById(R.id.pay_fee_stay_time_tv);
        mPayFeeStandardTv = (TextView) findViewById(R.id.pay_fee_standard_tv);
        mPayFeeSumTv = (TextView) findViewById(R.id.pay_fee_sum_tv);
        mPayFeeCouponsTv = (TextView) findViewById(R.id.pay_fee_coupons_tv);
        mPayFeeSelectCouponsTv = (TextView) findViewById(R.id.pay_fee_select_coupons_tv);
        mPayFeeSelectCouponsTv.setOnClickListener(this);
        mPayFeePaySumTv = (TextView) findViewById(R.id.pay_fee_pay_sum_tv);
        mPayFeeTv = (TextView) findViewById(R.id.pay_fee_tv);
        mPayFeeTv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.pay_fee_select_coupons_tv://选择优惠券
                if (dataBean != null) {
                    getPresenter().getCouponListNoPage(UserInfoUtil.getInstance().getUserId(), dataBean.getTotalFee(), 5, 1, CarParkContract.COUPON);

                }

                break;
            case R.id.pay_fee_tv://支付
//                if (!NetWorkUtil.checkNetworkState(this)) {
//                    return;
//                }
//                bottomSheetDialog = selectPayTypeDialog();
//                bottomSheetDialog.show();
                break;
//            case R.id.confirm_pay_tv://确认支付
//                if (mSelectedWeixinCb.isChecked()) {
//                    if (!PubUtil.isWeixinAvilible(this)) {
//                        ToastUtil.showNormalToast(this, Toast.LENGTH_LONG, "您还没有安装微信，请安装后再使用微信支付");
//                        return;
//                    }
//                    if (bottomSheetDialog.isShowing()) {
//                        bottomSheetDialog.dismiss();
//                        bottomSheetDialog = null;
//                    }
//                    if (dataBean != null) {
//                        int couponUserID = selectCouponBean == null ? -1 : selectCouponBean.getCouponUserID();
//                        new WeiXinPayModel().getWeiXinPayInfo(dataBean.getOrderId(), PubUtil.getIPAddress(this), UserInfoUtil.getInstance().getUserId(), "car", couponUserID, this);
//
//                    }
//
//                } else {
//                    showToast("请选择支付方式");
//                }
//
//
//                break;
        }
    }

    /**
     * 选择支付方式
     */
//    private BottomSheetDialog selectPayTypeDialog() {
//        final BottomSheetDialog dialog = new BottomSheetDialog(this);
//        View view = LayoutInflater.from(this)
//                .inflate(R.layout.select_pay_type, null);
//        mSelectedWeixinCb = (CheckBox) view.findViewById(R.id.selected_weixin_cb);
//        mWeixinPayRl = (RelativeLayout) view.findViewById(R.id.weixin_pay_rl);
//        mWeixinPayRl.setOnClickListener(this);
////        mSelectedAliPayCb = (CheckBox)view. findViewById(R.id.selected_ali_pay_cb);
////        mAliPayRl = (RelativeLayout) view.findViewById(R.id.ali_pay_rl);
////        mAliPayRl.setOnClickListener(this);
//        mConfirmPayTv = (TextView) view.findViewById(R.id.confirm_pay_tv);
//        mConfirmPayTv.setOnClickListener(this);
//        dialog.setContentView(view);
//        return dialog;
//    }
    @Override
    public void startLoading(String tag) {

    }

    @Override
    public void stopLoading(String tag) {

    }

    @Override
    public void updateView(Object o, String tag) {

        CouponsBean couponsBean = (CouponsBean) o;
        if (couponsBean != null) {
            List<CouponsBean.DataBean> dataBean = couponsBean.getData();
            List<SelectCouponBean> arrays = new ArrayList<>();
            if (dataBean != null && dataBean.size() > 0) {
                for (CouponsBean.DataBean bean : dataBean) {
                    arrays.add(new SelectCouponBean(bean.getDescription(), bean.getCouponId(), bean.getId(), bean.getMoney(), false));
                }
                arrays.add(new SelectCouponBean("不使用优惠券", -1, -1, 0, false));
                showSelectCouponsDialog(arrays);
            } else {
                showToast("无可用优惠券");
            }

        }
    }

    /**
     * 选择代金券dialog
     */
    private void showSelectCouponsDialog(final List<SelectCouponBean> arrays) {
        final BottomSheetDialog dialog = new BottomSheetDialog(this);
        View dialogView = LayoutInflater.from(this)
                .inflate(R.layout.select_coupons_layout, null);
        RecyclerView recyclerView = dialogView.findViewById(R.id.select_coupons_rv);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        SelectCouponsAdapter adapter = new SelectCouponsAdapter(R.layout.select_coupons_item);
        initRecyclerview(recyclerView, adapter, LinearLayoutManager.VERTICAL, false);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                for (SelectCouponBean array : arrays) {
                    if (array.equals(adapter.getItem(position))) {
                        selectCouponBean = array;
                        array.setCouponSelectStatus(true);
                    } else {
                        array.setCouponSelectStatus(false);

                    }
                }
                adapter.notifyDataSetChanged();
            }
        });
        adapter.setNewData(arrays);
        dialog.setContentView(dialogView);
        dialog.show();
        dialogView.findViewById(R.id.select_coupons_cancel_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                if (selectCouponBean != null) {
                    if (0 == selectCouponBean.getMoney()) {
                        mPayFeeSelectCouponsTv.setText("不使用优惠券 >");
                    } else {
                        double totalSum = dataBean.getTotalFee();
                        double couponSun = selectCouponBean.getMoney();
                        double savedSum = totalSum > couponSun ? couponSun : totalSum;
                        mPayFeeSelectCouponsTv.setText("省" + String.valueOf(savedSum) + "元 >");
                    }
                    if (dataBean != null) {
                        double shouldPay = Double.parseDouble(new DecimalFormat("0.00").format(dataBean.getTotalFee() - selectCouponBean.getMoney()));
                        String shouldPayStr = shouldPay < 0 ? "0.00元" : (String.valueOf(shouldPay) + "元");
                        mPayFeePaySumTv.setText(shouldPayStr);
                    }

                }

            }
        });
    }

    @Override
    public void onStart(String tag) {

    }

    @Override
    public void onSuccess(Object o, String tag) {
        PubUtil.WEI_XIN_PAY_ENTER = 1;
        IWXAPI api;
        WXPayResultBean wxPayResultBean = (WXPayResultBean) o;
        if (wxPayResultBean != null) {
            WXPayResultBean.DataBean dataBean = wxPayResultBean.getData();
            if (dataBean != null) {
                int payState = dataBean.getPayState();
                if (1 == payState) {
                    PayReq req = new PayReq();
                    //req.appId = "wxf8b4f85f3a794e77";  // 测试用appId
                    req.appId = dataBean.getAppid();
                    //微信支付分配的商户号
                    req.partnerId = dataBean.getPartnerid();
                    //预支付交易会话ID,微信返回的支付交易会话ID
                    req.prepayId = dataBean.getPrepayid();
                    //随机字符串，不长于32位
                    req.nonceStr = dataBean.getNoncestr();
                    //标准北京时间，时区为东八区，自1970年1月1日 0点0分0秒以来的秒数。注意：部分系统取到的值为毫秒级，需要转换成秒(10位数字)。
                    req.timeStamp = dataBean.getTimestamp();
                    //暂填写固定值Sign=WXPay
                    req.packageValue = dataBean.getPackAge();
                    //签名
                    req.sign = dataBean.getSign();
                    req.extData = "app data"; // optional
//						Toast.makeText(PayActivity.this, "正常调起支付", Toast.LENGTH_SHORT).show();
                    // 在支付之前，如果应用没有注册到微信，应该先调用IWXMsg.registerApp将应用注册到微信
                    api = WXAPIFactory.createWXAPI(PayFeeActivity.this, null);
                    api.registerApp(dataBean.getAppid());// 将该app注册到微信
                    api.sendReq(req);
                } else {
                    payFinished(0);

                }

            }
        }

    }

    @Override
    public void onError(String tag) {
        showToast(tag);
    }
}
