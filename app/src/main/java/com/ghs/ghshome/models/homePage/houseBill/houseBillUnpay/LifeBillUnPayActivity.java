package com.ghs.ghshome.models.homePage.houseBill.houseBillUnpay;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ghs.ghshome.R;
import com.ghs.ghshome.base.BaseActivity;
import com.ghs.ghshome.base.network.RequestStatus;
import com.ghs.ghshome.bean.MyBillInfolBean;
import com.ghs.ghshome.bean.WXPayResultBean;
import com.ghs.ghshome.custom.recyclerviewlayoutmanagerscrolloneitem.ViewPagerLayoutManager;
import com.ghs.ghshome.models.homePage.houseBill.billhistory.BillHistoryActivity;
import com.ghs.ghshome.models.homePage.houseBill.billhistory.BillHistoryContract;
import com.ghs.ghshome.models.homePage.houseBill.billhistory.BillHistoryPresent;
import com.ghs.ghshome.models.homePage.seed.SeedTaskAdapter;
import com.ghs.ghshome.tools.ActivityResultManager;
import com.ghs.ghshome.tools.PubUtil;
import com.ghs.ghshome.tools.UserInfoUtil;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * created by tobato
 * created date 2019/6/3 10:33.
 * application    未支付账单
 */
public class LifeBillUnPayActivity extends BaseActivity<BillHistoryContract.IBaseBillInfoView, BillHistoryPresent> implements BillHistoryContract.IBaseBillInfoView, RequestStatus, View.OnClickListener {

    private RecyclerView mLifeBillUnPayRv;
    private BillUnPayAdapter billUnPayAdapter;
    private RelativeLayout mWeixinPayRl;
    private MyBillInfolBean.DataBean mDataBean;
    /**
     * 取消
     */
    private BottomSheetDialog bottomSheetDialog;
    private TextView mCancelPayTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        switch (PubUtil.WEI_XIN_PAY_TAG) {
            case ActivityResultManager.WEI_XIN_PAY_SUCCESS://缴费成功
                paySuccessedDisplay();
                payFinished(0);
                break;
            case ActivityResultManager.WEI_XIN_PAY_ERROR://缴费失败
                paySuccessedDisplay();
                payFinished(1);
                break;
            default:
                break;
        }
    }

    @Override
    public void getDate() {
        showProgressDialog();
        getUnpayBillList();
    }

    /**
     * 获取未支付的账单
     */
    private void getUnpayBillList() {
        String feeType = "";
        switch (PubUtil.MINE_BILL_TAG) {
            //1 水 2 电 3 物业
            case 1:
                feeType = "water";
                initActionBar("水费", "缴费记录");
                break;
            case 2:
                feeType = "ammeter";
                initActionBar("电费", "缴费记录");
                break;
            case 3:
                feeType = "property";
                initActionBar("物业费", "缴费记录");
                break;
            default:
                break;
        }
        mHeaderRightTv.setTextColor(ContextCompat.getColor(this,R.color.app_green));
        getPresenter().getBillInfo(UserInfoUtil.getInstance().getRoomId(), 1, feeType, 0, "", "", "", "", "", "");
    }

    @Override
    public void initLayoutView() {
        initView();

    }

    @Override
    public void setLayout() {
        setContentView(R.layout.activity_un_pay_life_bill);

    }

    @Override
    public void actionBarRightTvOnClick() {
        startActivityForResult(new Intent(this, BillHistoryActivity.class), ActivityResultManager.BILL_HISTORY);
    }

    @Override
    public BillHistoryPresent creatPresenter() {
        return new BillHistoryPresent();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PubUtil.WEI_XIN_PAY_TAG = "";

    }

    private void initView() {
        mLifeBillUnPayRv = (RecyclerView) findViewById(R.id.life_bill_unPay_rv);
        billUnPayAdapter = new BillUnPayAdapter(R.layout.bill_un_pay_item_content);
        billUnPayAdapter.setEmptyView(getAdapterEmptyView("暂无内容"));
        mLifeBillUnPayRv.setAdapter(billUnPayAdapter);
        ViewPagerLayoutManager manager = new ViewPagerLayoutManager(this, ViewPagerLayoutManager.HORIZONTAL);
        mLifeBillUnPayRv.setLayoutManager(manager);
        billUnPayAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                MyBillInfolBean.DataBean dataBean = (MyBillInfolBean.DataBean) adapter.getData().get(position);
                TextView payTV = (TextView) adapter.getViewByPosition(mLifeBillUnPayRv, position, R.id.bill_unpay_pay_tv);
                String text = payTV.getText().toString().trim();
                if ("立即缴费".equals(text)||"继续缴费".equals(text)) {
                    mDataBean = null;
                    mDataBean = dataBean;
                    mDataBean.setPosition(dataBean.getPosition());
                    String totalAmount = String.valueOf(dataBean.getPayMoney());
                    if ("完成".equals(text)) {
                    } else {
                        bottomSheetDialog = selectPayTypeDialog(totalAmount);
                        bottomSheetDialog.show();
                    }

                }
            }
        });

    }

    /**
     * 支付成功后展示
     */
    private void paySuccessedDisplay() {
        getUnpayBillList();
        switch (PubUtil.WEI_XIN_PAY_TAG) {
            case ActivityResultManager.WEI_XIN_PAY_SUCCESS://缴费成功
                PubUtil.WEI_XIN_PAY_TAG = "";
                break;
            case ActivityResultManager.WEI_XIN_PAY_ERROR://缴费失败
                PubUtil.WEI_XIN_PAY_TAG = "";
                break;
        }


    }

    /**
     * 支付结束后的提示  0代表成功  1代表失败
     */
    private void payFinished(int state) {
        if (0 == state) {
            AlertDialog alertDialog = new AlertDialog.Builder(this)
                    .setMessage("已缴费成功！")
                    .setNegativeButton("知道了", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            switch (PubUtil.MINE_BILL_TAG) {
                                //1 水 2 电 3 物业
                                case 1:
                                    execSeedTask(SeedTaskAdapter.SEED_TASK_WATER_FEE,mDataBean.getPayMoney());
                                    break;
                                case 2:
                                    execSeedTask(SeedTaskAdapter.SEED_TASK_AMMETER_FEE,mDataBean.getPayMoney());
                                    break;
                                case 3:
                                    execSeedTask(SeedTaskAdapter.SEED_TASK_PROPERTY_FEE,mDataBean.getPayMoney());
                                    break;
                                default:
                                    break;
                            }
                        }
                    }).show();
            alertDialog.setCancelable(false);
            setAlertDialogHeightWidth(alertDialog, -1, 0);

        } else {
            showMaterialAlterdialogOnlyOneBtToClose("缴费失败！如果已扣款请联系客服处理","知道了");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (ActivityResultManager.BILL_HISTORY == resultCode) {
            getDate();
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
    }

    @Override
    public void startLoading(String tag) {
        showProgressDialog();
    }

    @Override
    public void stopLoading(String tag) {
    }

    @Override
    public void updateView(Object o, String tag) {

        switch (tag) {
            case RequestStatus.UPDATE:
                MyBillInfolBean myBillInfolBean = (MyBillInfolBean) o;
                if (myBillInfolBean != null) {
                    billUnPayAdapter.setNewData(myBillInfolBean.getData());
                }
                break;
            case RequestStatus.WEI_XIN_PAY:
                PubUtil.WEI_XIN_PAY_ENTER = 0;
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
                            api = WXAPIFactory.createWXAPI(LifeBillUnPayActivity.this, null);
                            api.registerApp(dataBean.getAppid());// 将该app注册到微信
                            api.sendReq(req);
                        } else {
                            PubUtil.WEI_XIN_PAY_TAG = ActivityResultManager.WEI_XIN_PAY_SUCCESS;
                            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm dd/MM/yyyy");
                            mDataBean.setPayTime(sdf.format(new Date()));
                            paySuccessedDisplay();
                            payFinished(0);
                        }
                    }
                }

                break;
            case RequestStatus.SELECT_COUPON:
                //选择优惠券
                break;
            default:
                break;
        }

    }


    @Override
    public void onError(String tag) {
        showToast(tag);
    }

    @Override
    public void onStart(String tag) {

    }

    @Override
    public void onSuccess(Object o, String tag) {

    }


    /**
     * 选择支付方式
     */
    private BottomSheetDialog selectPayTypeDialog(String amount) {
        final BottomSheetDialog dialog = new BottomSheetDialog(this);
        View view = LayoutInflater.from(this)
                .inflate(R.layout.select_pay_type, null);
        mWeixinPayRl = (RelativeLayout) view.findViewById(R.id.weixin_pay_rl);
        mWeixinPayRl.setOnClickListener(this);
        TextView amountTv = (TextView) view.findViewById(R.id.pay_type_total_amount_tv);
        amountTv.setText("金额：" + amount + "元");
        TextView mCancelPayTv = (TextView) view.findViewById(R.id.pay_type_cancel_tv);
        mCancelPayTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.setContentView(view);
        return dialog;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.weixin_pay_rl://选择微信支付
                if (!PubUtil.isWeixinAvilible(this)) {
                    showToast("您还没有安装微信，请安装后再使用微信支付");
                    return;
                }
                if (bottomSheetDialog.isShowing()) {
                    bottomSheetDialog.dismiss();
                    bottomSheetDialog = null;
                }
                if (mDataBean != null) {
                    getPresenter().getWeiXinPayInfo(mDataBean.getId(), PubUtil.getIPAddress(this), UserInfoUtil.getInstance().getUserId(), mDataBean.getType(), mDataBean.getCouponUserId());

                }

                break;
        }
    }
}