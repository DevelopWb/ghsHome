package com.ghs.ghshome.models.homePage.houseBill.billhistory;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.bigkoo.pickerview.adapter.ArrayWheelAdapter;
import com.contrarywind.listener.OnItemSelectedListener;
import com.contrarywind.view.WheelView;
import com.ghs.ghshome.R;
import com.ghs.ghshome.base.BaseActivity;
import com.ghs.ghshome.base.BasePresent;
import com.ghs.ghshome.tools.ActivityResultManager;
import com.ghs.ghshome.tools.CalendarUtil;
import com.ghs.ghshome.tools.PubUtil;
import com.ghs.ghshome.tools.UserInfoUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
/**
 * created by tobato
 * created date 2019/6/4 10:24.
 * application   根据年月筛选账单
 */
public class MineBillSelectDateActivity extends BaseActivity implements View.OnClickListener {

    /**
     * 确 定
     */
    private TextView mMineBillSelectDateConfirmTv;
    private WheelView mMineBillSelectYearWv;
    private WheelView mMineBillSelectMonthWv;
    private List<String> years;
    private List<String> months;
    private int currentYearIndex;
    private int currentMonthIndex;
    private int currentYear;
    private int currentMonth;
    private String selectedYear = "";
    private String selectedMonth = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void getDate() {

    }

    @Override
    public void initLayoutView() {
        initView();

        initActionBar("选择时间", "");

    }

    @Override
    public void setLayout() {
        setContentView(R.layout.activity_mine_bill_select_date);

    }

    @Override
    public BasePresent creatPresenter() {
        return null;
    }


    private void initView() {
        Calendar calendar = Calendar.getInstance();
        currentYear = calendar.get(Calendar.YEAR);
        currentMonth = calendar.get(Calendar.MONTH) + 1;
        final String currentDate = String.valueOf(currentYear) + "年" + String.valueOf(currentMonth) + "月";
        mMineBillSelectDateConfirmTv = (TextView) findViewById(R.id.mine_bill_select_date_confirm_tv);
        mMineBillSelectDateConfirmTv.setOnClickListener(this);
        initWheelView(currentDate);

    }

    /**
     * 初始化WheelView
     * @param currentDate
     */
    private void initWheelView(final String currentDate) {
        mMineBillSelectYearWv = (WheelView) findViewById(R.id.mine_bill_select_year_wv);
        mMineBillSelectMonthWv = (WheelView) findViewById(R.id.mine_bill_select_month_wv);
        mMineBillSelectYearWv.setCyclic(false);
        years = geYearsr();
        mMineBillSelectYearWv.setAdapter(new ArrayWheelAdapter(years));
        mMineBillSelectYearWv.setCurrentItem(currentYearIndex);
        if (PubUtil.MINE_BILL_TAG!=3) {//物业
            mMineBillSelectMonthWv.setVisibility(View.VISIBLE);
            months = getMonths();
            mMineBillSelectMonthWv.setCyclic(false);
            mMineBillSelectMonthWv.setAdapter(new ArrayWheelAdapter(months));
            mMineBillSelectMonthWv.setCurrentItem(currentMonthIndex);
            mMineBillSelectMonthWv.setOnItemSelectedListener(new OnItemSelectedListener() {
                @Override
                public void onItemSelected(int index) {
                    selectedMonth = months.get(index);
                    initConfirmStatus(selectedYear, selectedMonth, currentDate);
                }
            });
        }else{
            mMineBillSelectMonthWv.setVisibility(View.GONE);
        }


        mMineBillSelectYearWv.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                selectedYear = years.get(index);
                initConfirmStatus(selectedYear, selectedMonth, currentDate);
            }
        });

    }

    /**
     * 初始化确定按钮的状态
     *
     * @param currentDate
     */
    private void initConfirmStatus(String selectedYear, String selectedMonth, String currentDate) {
        if (!CalendarUtil.compareDateSize("yyyy年MM月", currentDate, getSelectedDate(selectedYear, selectedMonth, true))) {
            mMineBillSelectDateConfirmTv.setBackgroundResource(R.drawable.bt_unclick_grey_shape);
            mMineBillSelectDateConfirmTv.setClickable(false);
        } else {
            mMineBillSelectDateConfirmTv.setBackgroundResource(R.drawable.bt_selecter_default_green);
            mMineBillSelectDateConfirmTv.setClickable(true);

        }
    }

    /**
     * 获取最后确定的日期
     *
     * @return tag为true 代表带有年或月字
     */
    private String getSelectedDate(String selectedYear, String selectedMonth, boolean tag) {
        if (TextUtils.isEmpty(selectedYear)) {
            selectedYear = String.valueOf(currentYear) + "年";
        }
        if (TextUtils.isEmpty(selectedMonth)) {
            selectedMonth = String.valueOf(currentMonth) + "月";
        }
        if (tag) {
            return selectedYear + selectedMonth;
        } else {
            selectedYear = selectedYear.substring(0, selectedYear.length() - 1);
            selectedMonth = selectedMonth.substring(0, selectedMonth.length() - 1);
            if (selectedMonth.length() == 1) {
                selectedMonth = "0" + selectedMonth;
            }
            if (3== PubUtil.MINE_BILL_TAG) {
                return selectedYear;
            }
            return selectedYear + "-" + selectedMonth;
        }

    }

    /**
     * 获取月份数据
     *
     * @return
     */
    private List<String> getMonths() {
        List<String> months = new ArrayList<>();

        for (int i = 0; i < 12; i++) {
            int startMonth = 1;
            startMonth = startMonth + i;
            if (currentMonth == startMonth) {
                currentMonthIndex = i;
            }
            months.add(String.valueOf(startMonth) + "月");
        }
        return months;
    }

    /**
     * 获取年份数据
     *
     * @return
     */
    private List<String> geYearsr() {
        List<String> years = new ArrayList<>();
        int registYear = Integer.parseInt(UserInfoUtil.getInstance().getRegistTime("yyyy"));
        for (int i = 0; i < 50; i++) {
            int year = 0;
            year = registYear+i;
            years.add(String.valueOf(year) + "年");
            if (currentYear == year) {
                currentYearIndex = i;
                break;
            }
        }
        return years;
    }




    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.mine_bill_select_date_confirm_tv:
                String selectedDate = getSelectedDate(selectedYear, selectedMonth, false);
                Intent intent = new Intent();
                intent.putExtra("SELECTED_DATE", selectedDate);
                setResult(ActivityResultManager.MINE_BILL_SELECT_DATE, intent);
                finish();
                break;
        }
    }
}
