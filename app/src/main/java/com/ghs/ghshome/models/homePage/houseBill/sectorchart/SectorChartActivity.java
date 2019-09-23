package com.ghs.ghshome.models.homePage.houseBill.sectorchart;

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
import com.ghs.ghshome.custom.SectorChartView;
import com.ghs.ghshome.models.homePage.houseBill.sectorchart.sectoradapter.BillYearAdapter;
import com.ghs.ghshome.models.homePage.houseBill.sectorchart.sectoradapter.SectorChartAdapter;
import com.ghs.ghshome.tools.DividerItemDecoration;
import com.ghs.ghshome.tools.PubUtil;
import com.ghs.ghshome.tools.UserInfoUtil;

import org.xclcharts.chart.PieData;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

/**
 * created by 8级大的狂风
 * created date 2018/7/31 17:52.
 * application  图表统计
 */
public class SectorChartActivity extends BaseActivity<SectorChartContract.ISectorChartView, SectorChartPresent> implements SectorChartContract.ISectorChartView, SectorChartContract.ISectorChartClickListener, View.OnClickListener {

    /**
     * 2017年
     */
    private TextView mSecotrChartYearValueTv;
    private LinearLayout mSecotrChartYearLl;
    private RecyclerView mSecotrChartRv;
    private SectorChartAdapter adapter;
    private SectorChartView mSectorChartScv;
    private PopupWindow popupWindow;
    private int currentYear;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setLayout() {
        setContentView(R.layout.activity_sector_chart);

    }

    @Override
    public void initLayoutView() {
        initView();

        initActionBar("账单统计", null);
    }

    @Override
    public SectorChartPresent creatPresenter() {
        return new SectorChartPresent();
    }

    @Override
    public void getDate() {
        getPresenter().getSectorChartData(UserInfoUtil.getInstance().getRoomId(), UserInfoUtil.getInstance().getUserId(), getCurrentYear(), RequestStatus.UPDATE);

    }

    private void initView() {
        mSecotrChartYearValueTv = (TextView) findViewById(R.id.secotr_chart_year_value_tv);
        mSecotrChartYearValueTv.setText(getCurrentYear() + "年");
        mSecotrChartYearLl = (LinearLayout) findViewById(R.id.secotr_chart_year_ll);
        mSecotrChartYearLl.setOnClickListener(this);
        mSecotrChartRv = (RecyclerView) findViewById(R.id.secotr_chart_rv);
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mSecotrChartRv.setLayoutManager(manager);
        adapter = new SectorChartAdapter();
        mSecotrChartRv.setAdapter(adapter);
        mSectorChartScv = (SectorChartView) findViewById(R.id.sector_chart_scv);
        Calendar calendar = Calendar.getInstance();
        currentYear = calendar.get(Calendar.YEAR);
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
            case SectorChartContract.CHART_DATA:
                LinkedList<PieData> linkedList = (LinkedList<PieData>) o;
                if (linkedList.size() > 0) {
                    showNoDataActivityLayout(false, "");
                    mSectorChartScv.setChartData(linkedList, this);
                } else {
                    showNoDataActivityLayout(true, "暂无内容");
                }
                break;
            case RequestStatus.UPDATE:
                List<Object> objects = (List<Object>) o;
                adapter.setDate(objects);
                break;
            default:
                break;
        }
    }

    @Override
    public void onError(String tag) {

    }

    @Override
    public void sectorClicked(PieData pieData) {

        switch (pieData.getKey()) {
            case "水费":
                PubUtil.MINE_BILL_TAG_STR = "water";
                break;
            case "电费":
                PubUtil.MINE_BILL_TAG_STR = "ammeter";
                break;
            case "物业费":
                PubUtil.MINE_BILL_TAG_STR = "property";
                break;
            default:
                break;
        }
//        startActivity(new Intent(this, LinearChartActivity.class));
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
        billYearAdapter.setNewData(geYearsr());
        final PopupWindow pop = new PopupWindow(view, dip2px(this, 80), dip2px(this, 80), false);
        pop.setOutsideTouchable(true);
        setBackgroundAlpha(0.5f);
        pop.setFocusable(true);
        billYearAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (popupWindow.isShowing()) {
                    popupWindow.dismiss();
                }
                mSecotrChartYearValueTv.setText((String) adapter.getData().get(position));
                String year = (String) adapter.getData().get(position);
                String year_new = year.substring(0, year.length() - 1);
                getPresenter().getSectorChartData(UserInfoUtil.getInstance().getRoomId(), UserInfoUtil.getInstance().getUserId(), Integer.parseInt(year_new), RequestStatus.UPDATE);

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

    /**
     * 获取年份数据
     *
     * @return
     */
    private List<String> geYearsr() {
        List<String> years = new ArrayList<>();
        if (mUserInfoUtil.getRegistTime("yyyy") != null) {
            int registYear = Integer.parseInt(mUserInfoUtil.getRegistTime("yyyy"));
            for (int i = 0; i < 50; i++) {
                int year = registYear + i;
                years.add(String.valueOf(year) + "年");
                if (currentYear == year) {
//                currentYearIndex = i;
                    break;
                }
            }
            return years;
        }
        return years;

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.secotr_chart_year_ll:
                popupWindow = selectBillDate();
                popupWindow.showAsDropDown(mSecotrChartYearValueTv, dip2px(this, 10), dip2px(this, -5));
                break;
        }
    }
}
