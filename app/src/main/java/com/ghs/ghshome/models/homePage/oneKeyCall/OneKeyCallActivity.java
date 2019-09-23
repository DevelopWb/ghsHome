package com.ghs.ghshome.models.homePage.oneKeyCall;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ghs.ghshome.R;
import com.ghs.ghshome.base.BaseActivity;
import com.ghs.ghshome.bean.PropertyTelBean;
import com.ghs.ghshome.bean.WorkOrderBean;
import com.ghs.ghshome.models.homePage.HomePageContract;
import com.ghs.ghshome.models.homePage.HomePagePresent;
import com.ghs.ghshome.tools.ActivityResultManager;
import com.ghs.ghshome.tools.StrUtils;

import java.util.List;

/**
 * created by tobato
 * created date 2019/5/23 10:20.
 * application   一键呼叫
 */
public class OneKeyCallActivity extends BaseActivity<HomePageContract.IHomePageView, HomePagePresent> implements HomePageContract.IHomePageView, View.OnClickListener {

    /**
     * 时间
     */
    private TextView mCallCenterWorkTimeTv;
    private TextView mTelNum;
    private RecyclerView mWorkOrderListRv;
    /**
     * 电话号码
     */
    private ConstraintLayout mCallCl;
    private int offset = 0;
    private int limit = 5;
    private SwipeRefreshLayout mSwipeLayout;
    private WorkOrderListAdapter adapter;
    private String telNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setLayout() {
        setContentView(R.layout.activity_one_key_call);

    }

    @Override
    public void initLayoutView() {
        initView();
        initActionBar("一键呼叫", "");
    }


    @Override
    public HomePagePresent creatPresenter() {
        return new HomePagePresent();
    }

    @Override
    public void getDate() {
        offset = 0;
        getPresenter().workOrderList(offset, limit, mUserInfoUtil.getVillageId(), mUserInfoUtil.getUserId(), HomePageContract.WORK_ORDER_LIST_REFRESH);
        getPresenter().customerServerTel(HomePageContract.CUSTOMER_SERVER_TEL);
    }


    @Override
    public void startLoading(String tag) {

    }

    @Override
    public void stopLoading(String tag) {

    }

    @Override
    public void updateView(Object o, String tag) {
        mSwipeLayout.setRefreshing(false);
        boolean isFresh = HomePageContract.WORK_ORDER_LIST_REFRESH.equals(tag) ? true : false;
        switch (tag) {
            case HomePageContract.WORK_ORDER_LIST:
                initAdapterData((WorkOrderBean) o, isFresh);
                break;
            case HomePageContract.WORK_ORDER_LIST_REFRESH:
                initAdapterData((WorkOrderBean) o, isFresh);
                break;
            case HomePageContract.CUSTOMER_SERVER_TEL:
                PropertyTelBean  telBean = (PropertyTelBean) o;
                if (telBean != null) {
                    PropertyTelBean.DataBean dataBean = telBean.getData();
                    telNum = dataBean.getMobile();
                    String  workTime = dataBean.getServiceTime();
                    mCallCenterWorkTimeTv.setText(workTime);
                    mTelNum.setText("呼叫 "+ telNum);
                }
                break;
            default:
                break;
        }
    }

    /**
     * 填充数据
     *
     * @param o
     * @param isFresh
     */
    private void initAdapterData(WorkOrderBean o, boolean isFresh) {
        WorkOrderBean workOrderBean = o;
        if (workOrderBean != null) {
            List<WorkOrderBean.DataBean> arrays = workOrderBean.getData();
            if (arrays != null) {
                offset += arrays.size();
                if (isFresh) {
                    adapter.setNewData(arrays);
                } else {
                    adapter.addData(arrays);
                }
                if (arrays.size() < limit) {
                    //第一页如果不够一页就不显示没有更多数据布局
                    adapter.loadMoreEnd(isFresh);
                } else {
                    adapter.loadMoreComplete();
                }
            }
        }
    }

    @Override
    public void onError(String tag) {
        mSwipeLayout.setRefreshing(false);

    }


    private void initView() {
        mCallCenterWorkTimeTv = (TextView) findViewById(R.id.call_center_work_time_tv);
        mTelNum = (TextView) findViewById(R.id.tel_num);
        mWorkOrderListRv = (RecyclerView) findViewById(R.id.work_order_list_rv);
        mCallCl = (ConstraintLayout) findViewById(R.id.call_cl);
        mCallCl.setOnClickListener(this);
        adapter = new WorkOrderListAdapter(R.layout.work_order_list_item,false);
        adapter.setEmptyView(getAdapterEmptyView("暂无内容"));
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                WorkOrderBean.DataBean bean = (WorkOrderBean.DataBean) adapter.getData().get(position);
                Intent intent = new Intent(OneKeyCallActivity.this, WorkOrderDetailActivity.class);
                intent.putExtra("workOrderId", bean.getId());
                startActivityForResult(intent, ActivityResultManager.ENTER_ORDER_DETAIL);
            }
        });
        initRecyclerview(mWorkOrderListRv, adapter, LinearLayoutManager.VERTICAL, false);
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                getPresenter().workOrderList(offset, limit, mUserInfoUtil.getVillageId(), mUserInfoUtil.getUserId(), HomePageContract.WORK_ORDER_LIST);
            }
        }, mWorkOrderListRv);
        mSwipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipeLayout);
        mSwipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                offset = 0;
                adapter.setEnableLoadMore(false);//这里的作用是防止下拉刷新的时候还可以上拉加载
                mSwipeLayout.setRefreshing(true);
                getPresenter().workOrderList(offset, limit, mUserInfoUtil.getVillageId(), mUserInfoUtil.getUserId(), HomePageContract.WORK_ORDER_LIST_REFRESH);

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.call_cl:
                if (StrUtils.isStringValueOk(telNum)) {
                    makeAPhoneCall(telNum);
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ActivityResultManager.ENTER_ORDER_DETAIL) {
            offset = 0;
            getPresenter().workOrderList(offset, limit, mUserInfoUtil.getVillageId(), mUserInfoUtil.getUserId(), HomePageContract.WORK_ORDER_LIST_REFRESH);
            getPresenter().customerServerTel(HomePageContract.CUSTOMER_SERVER_TEL);
        }
    }
}
