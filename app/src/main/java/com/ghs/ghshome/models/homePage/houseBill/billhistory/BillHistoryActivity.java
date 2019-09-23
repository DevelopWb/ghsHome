package com.ghs.ghshome.models.homePage.houseBill.billhistory;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ghs.ghshome.R;
import com.ghs.ghshome.base.BaseActivity;
import com.ghs.ghshome.base.network.RequestStatus;
import com.ghs.ghshome.bean.MyBillInfolBean;
import com.ghs.ghshome.tools.ActivityResultManager;
import com.ghs.ghshome.tools.CalendarUtil;
import com.ghs.ghshome.tools.PubUtil;
import com.ghs.ghshome.tools.UserInfoUtil;

/**
 * created by tobato
 * created date 2019/6/3 10:43.
 * application   历史账单
 */
public class BillHistoryActivity extends BaseActivity<BillHistoryContract.IBaseBillInfoView, BillHistoryPresent> implements BillHistoryContract.IBaseBillInfoView, View.OnClickListener {

    private RecyclerView mMineBillInfoRv;
    private BillHistoryAdapter adapter;
    /**
     * 查看更早记录
     */
    private TextView mSeeMoreRecordTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void getDate() {

        getBillList(CalendarUtil.getLastDateOfMonth(-11), CalendarUtil.getCurrentMonth(), false);

    }

    @Override
    public void initLayoutView() {
        initView();
        initActionBar("历史账单", "");
    }

    @Override
    public void setLayout() {
        setContentView(R.layout.activity_basebill);

    }

    @Override
    public BillHistoryPresent creatPresenter() {
        return new BillHistoryPresent();
    }


    private void initView() {
        mMineBillInfoRv = (RecyclerView) findViewById(R.id.mine_bill_info_rv);
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mMineBillInfoRv.setLayoutManager(manager);
        adapter = new BillHistoryAdapter(R.layout.mine_bill_base_item);
        mMineBillInfoRv.setAdapter(adapter);
        mSeeMoreRecordTv = (TextView) findViewById(R.id.see_more_record_tv);
        mSeeMoreRecordTv.setOnClickListener(this);
        adapter.setEmptyView(getAdapterEmptyView("暂无内容"));
        addDivider(true, mMineBillInfoRv, false, true);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                MyBillInfolBean.DataBean dataBean = (MyBillInfolBean.DataBean) adapter.getData().get(position);
                int orderId = dataBean.getId();
                Intent intent = new Intent(BillHistoryActivity.this, BillHistoryDetailActivity.class);
                intent.putExtra(BillHistoryContract.BILL_HIS_ORDERID, orderId);
                startActivity(intent);
            }
        });
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
                    if (myBillInfolBean.getData() != null) {
                        adapter.setNewData(myBillInfolBean.getData());
                    }
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onError(String tag) {

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (ActivityResultManager.MINE_BILL_SELECT_DATE == requestCode) {
            if (data != null) {
                String date = data.getStringExtra("SELECTED_DATE");
                getBillList(date, date, true);
            }
        }
    }

    /**
     * 获取账单列表
     *
     * @param startDate 开始日期
     * @param endDate   结束日期
     *                  isFilter  是否过滤
     */
    private void getBillList(String startDate, String endDate, boolean isFilter) {
        switch (PubUtil.MINE_BILL_TAG) {
            case 1:
                getPresenter().getBillInfo(UserInfoUtil.getInstance().getRoomId(), 2, "water", UserInfoUtil.getInstance().getUserId(), "", "", startDate, endDate, "", "");
                break;
            case 2:
                getPresenter().getBillInfo(UserInfoUtil.getInstance().getRoomId(), 2, "ammeter", UserInfoUtil.getInstance().getUserId(), "", "", startDate, endDate, "", "");
                break;
            case 3:
                if (isFilter) {
                    getPresenter().getBillInfo(UserInfoUtil.getInstance().getRoomId(), 2, "property", UserInfoUtil.getInstance().getUserId(), "", "", "", "", startDate, endDate);
                } else {
                    getPresenter().getBillInfo(UserInfoUtil.getInstance().getRoomId(), 2, "property", UserInfoUtil.getInstance().getUserId(), "", "", startDate, endDate, "", "");
                }
                break;
            default:
                break;
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.see_more_record_tv:
                startActivityForResult(new Intent(this, MineBillSelectDateActivity.class), ActivityResultManager.MINE_BILL_SELECT_DATE);

                break;
        }
    }
}
