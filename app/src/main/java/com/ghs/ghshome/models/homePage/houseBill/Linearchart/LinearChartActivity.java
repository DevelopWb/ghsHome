package com.ghs.ghshome.models.homePage.houseBill.Linearchart;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ghs.ghshome.R;
import com.ghs.ghshome.base.BaseActivity;
import com.ghs.ghshome.base.network.RequestStatus;
import com.ghs.ghshome.bean.LinearChartBean;
import com.ghs.ghshome.custom.LineChartView;
import com.ghs.ghshome.models.homePage.houseBill.sectorchart.sectoradapter.BillYearAdapter;
import com.ghs.ghshome.tools.CalendarUtil;
import com.ghs.ghshome.tools.DividerItemDecoration;
import com.ghs.ghshome.tools.PubUtil;
import com.ghs.ghshome.tools.UserInfoUtil;

import java.util.ArrayList;
import java.util.List;

public class LinearChartActivity extends BaseActivity<LinearChartContract.ILinearChartView, LinearChartPresent> implements LinearChartContract.ILinearChartView,View.OnClickListener {

    /**
     * 近一年
     */
    private TextView mLinearChartYearValueTv;
    private LinearLayout mLinearChartYearLl;
    /**
     * 总支出
     */
    private TextView mLinearChartSumTv;
    /**
     * 平均值
     */
    private TextView mLinearChartAverageTv;
    private LineChartView mLineChartView;
    private TextView mLinearChartAddedTv;
    private TextView mLinearChartAddedTypeTv;
    private TextView mLinearChartUnpayTv;
    private TextView mLinearChartPayedTv;
    private List<Float> dataArr = new ArrayList<>();
    private List<Integer> textArr_X = new ArrayList<>();//x轴上的数字
    private PopupWindow popupWindow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void getDate() {
        getFeeDate(CalendarUtil.getLastDateOfMonth(-11));

    }

    /**
     * 获取费用的数据
     */
    private void getFeeDate(String startMonth) {
        switch (PubUtil.MINE_BILL_TAG_STR) {
            case "water":
                getPresenter().getLinearChartData(UserInfoUtil.getInstance().getRoomId(), UserInfoUtil.getInstance().getUserId(), startMonth, CalendarUtil.getCurrentMonth(), "water", RequestStatus.UPDATE);
                break;
            case "ammeter":
                getPresenter().getLinearChartData(UserInfoUtil.getInstance().getRoomId(), UserInfoUtil.getInstance().getUserId(), startMonth, CalendarUtil.getCurrentMonth(), "ammeter", RequestStatus.UPDATE);
                break;
            case "property":
                getPresenter().getLinearChartData(UserInfoUtil.getInstance().getRoomId(), UserInfoUtil.getInstance().getUserId(), startMonth, CalendarUtil.getCurrentMonth(), "property", RequestStatus.UPDATE);

                break;
            default:
                break;
        }
    }

    @Override
    public void initLayoutView() {
        initView();
        switch (PubUtil.MINE_BILL_TAG_STR) {
            case "water":
                initActionBar("水费统计", null);
                break;
            case "ammeter":
                initActionBar("电费统计", null);
                break;
            case "property":
                initActionBar("物业费统计", null);
                break;
            default:
                break;
        }


    }

    @Override
    public void setLayout() {
        setContentView(R.layout.activity_linear_chart);
    }


    @Override
    public LinearChartPresent creatPresenter() {
        return new LinearChartPresent();
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
        LinearChartBean linearChart = (LinearChartBean) o;
        LinearChartBean.DataBean  linearChartBean = linearChart.getData();
        if (linearChartBean != null) {
            double payed = linearChartBean.getPayFee();
            double unpay = linearChartBean.getNoPayFee();
            float difValue = Float.parseFloat(String.valueOf(linearChartBean.getMaxFee() - linearChartBean.getMinFee()));
            List<LinearChartBean.DataBean.ListBean> payList = linearChartBean.getPayList();
            List<LinearChartBean.DataBean.ListBean> noPayList = linearChartBean.getNoPayList();
            if (payed==0) {
                showNoDataActivityLayout(true,"您还没有账单，暂时无法统计");
                return;
            }else{
                showNoDataActivityLayout(false,"您还没有账单，暂时无法统计");
            }
            if (payList != null && !payList.isEmpty()) {
                dataArr.clear();
                textArr_X.clear();
                for (LinearChartBean.DataBean.ListBean payListBean : payList) {
                    dataArr.add(payListBean.getFee());
                    textArr_X.add(CalendarUtil.getMonth(payListBean.getMonth()));
                }
                initLinearChartData(dataArr, textArr_X, difValue);
            }

            double total = payed + unpay;
            if (total > 0) {
                mLinearChartAddedTypeTv.setText(linearChartBean.getYearOnYearMsg());
                mLinearChartSumTv.setText("总支出：" + String.valueOf(payed) + "元");
                mLinearChartAverageTv.setText("平均值：" + String.valueOf(linearChartBean.getAverageFee()) + "元");
                mLinearChartAddedTv.setText(linearChartBean.getYearOnYear());
                mLinearChartUnpayTv.setText(linearChartBean.getNoPayRate());
                mLinearChartPayedTv.setText(linearChartBean.getPayRate());
            }

      }
    }

    @Override
    public void onError(String tag) {

    }

    private void initView() {
        mLinearChartYearValueTv = (TextView) findViewById(R.id.linear_chart_year_value_tv);
        mLinearChartYearLl = (LinearLayout) findViewById(R.id.linear_chart_year_ll);
        mLinearChartYearLl.setOnClickListener(this);
        mLinearChartSumTv = (TextView) findViewById(R.id.linear_chart_sum_tv);
        mLinearChartAverageTv = (TextView) findViewById(R.id.linear_chart_average_tv);
        mLineChartView = (LineChartView) findViewById(R.id.line_chart_view);
        mLinearChartAddedTv = (TextView) findViewById(R.id.linear_chart_added_tv);
        mLinearChartAddedTypeTv = (TextView) findViewById(R.id.linear_chart_added_type_tv);
        mLinearChartUnpayTv = (TextView) findViewById(R.id.linear_chart_unpay_tv);
        mLinearChartPayedTv = (TextView) findViewById(R.id.linear_chart_payed_tv);

    }

    /**
     * 初始化线型统计表的数据
     */
    private void initLinearChartData(List<Float> dataArr, List<Integer> textArr_X, float difValue) {
        List<LineChartView.Data> datas = new ArrayList<>();
        for (float value : dataArr) {
            LineChartView.Data data = new LineChartView.Data(value);
            datas.add(data);
        }
        if (textArr_X.size()>0) {
            mLineChartView.setXTexts(textArr_X);
        }

        mLineChartView.setData(datas);
        mLineChartView.playAnim();
        mLineChartView.setRulerYSpace(difValue / 2);
        mLineChartView.setStepSpace(px2dip(this, getScreenWidth() - 60) / dataArr.size() - 6);
        mLineChartView.setShowTable(true);
    }


    /**
     * 选择账单年份
     *
     * @return
     */
    private PopupWindow selectBillDate() {
        View view = LayoutInflater.from(this).inflate(R.layout.bill_statistical_year, null);
        RecyclerView recyclerView = view.findViewById(R.id.bill_statistical_year_rv);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        BillYearAdapter billYearAdapter = new BillYearAdapter(R.layout.bill_year_item);
        initRecyclerview(recyclerView, billYearAdapter, LinearLayoutManager.VERTICAL, false);
        List<String> lists = new ArrayList<>();
        lists.add("近一年");
        lists.add("近半年");
        lists.add("近三个月");
        billYearAdapter.setNewData(lists);
        final PopupWindow pop = new PopupWindow(view, dip2px(this, 80), dip2px(this, 100), false);
        pop.setOutsideTouchable(true);
        setBackgroundAlpha(0.5f);
        pop.setFocusable(true);
        billYearAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (popupWindow.isShowing()) {
                    popupWindow.dismiss();
                }
                String text = (String) adapter.getData().get(position);
                mLinearChartYearValueTv.setText(text);
                switch (text) {
                    case "近一年":
                        getFeeDate(CalendarUtil.getLastDateOfMonth(-11));
                        break;
                    case "近半年":
                        getFeeDate(CalendarUtil.getLastDateOfMonth(-5));
                        break;
                    case "近三个月":
                        getFeeDate(CalendarUtil.getLastDateOfMonth(-2));
                        break;
                    default:
                        break;
                }

            }
        });
        pop.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                setBackgroundAlpha(1.0f);
            }
        });
        return pop;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dataArr.clear();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.linear_chart_year_ll:
                popupWindow = selectBillDate();
                popupWindow.showAsDropDown(mLinearChartYearValueTv, 10, -10);
                break;
        }
    }
}
