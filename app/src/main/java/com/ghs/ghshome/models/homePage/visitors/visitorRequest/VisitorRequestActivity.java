package com.ghs.ghshome.models.homePage.visitors.visitorRequest;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ghs.ghshome.R;
import com.ghs.ghshome.base.BaseActivity;
import com.ghs.ghshome.base.network.RequestStatus;
import com.ghs.ghshome.bean.VisitorsBean;
import com.ghs.ghshome.models.homePage.visitors.VisitorContract;
import com.ghs.ghshome.models.homePage.visitors.VisitorPresent;
import com.ghs.ghshome.tools.ActivityResultManager;
import com.ghs.ghshome.tools.IntentUtil;
import com.ghs.ghshome.tools.UserInfoUtil;

import java.util.List;

/**
 * created by tobato
 * created date 2019/8/13 15:10.
 * application  访客请求
 */
public class VisitorRequestActivity extends BaseActivity<VisitorContract.IVisitorView, VisitorPresent> implements VisitorContract.IVisitorView {

    /**
     * 访客：---
     */
    private TextView mVisitorNameTv;
    /**
     * 到达日期:---
     */
    private TextView mVisitorArrivedTv;
    /**
     * 到访时间段:---
     */
    private TextView mVisitorPeriodTv;
    /**
     * 车牌号:---
     */
    private TextView mVistorCarNoTv;
    /**
     * 同意
     */
    private TextView mTaskDetailAgreeTv;
    /**
     * 驳回
     */
    private TextView mTaskDetailUnAgreeTv;
    private RecyclerView mVisitorRequestRv;
    private SwipeRefreshLayout mSwipeLayout;
    private UserInfoUtil userInfoUtil;
    private VisitorRequestAdapter adapter;
    private int offset = 0;
    private int limit = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setLayout() {
        setContentView(R.layout.activity_visitor_request);

    }


    @Override
    public void initLayoutView() {
        initView();

        initActionBar("访客请求", null);

    }

    @Override
    public VisitorPresent creatPresenter() {
        return new VisitorPresent();
    }

    @Override
    public void getDate() {


    }

    private void getNewestData() {
        showProgressDialog();

        offset = 0;
        userInfoUtil = UserInfoUtil.getInstance();
        getPresenter().searchVisitors(userInfoUtil.getVillageId(), userInfoUtil.getRoomId(), userInfoUtil.getUserId(), 1, offset, limit, RequestStatus.REFRESH);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getNewestData();
    }

    private void initView() {
        mVisitorRequestRv = (RecyclerView) findViewById(R.id.visitor_request_rv);
        adapter = new VisitorRequestAdapter(R.layout.visitor_request_item);
        initRecyclerview(mVisitorRequestRv, adapter, LinearLayoutManager.VERTICAL, false);
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                getPresenter().searchVisitors(userInfoUtil.getVillageId(), userInfoUtil.getRoomId(), userInfoUtil.getUserId(), 1, offset, limit, VisitorContract.SEARCH_VISITOR_REQUEST);

            }
        }, mVisitorRequestRv);
        mSwipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipeLayout);
        mSwipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                adapter.setEnableLoadMore(false);//这里的作用是防止下拉刷新的时候还可以上拉加载
                mSwipeLayout.setRefreshing(true);
                getNewestData();

            }
        });
        adapter.setEmptyView(getAdapterEmptyView("暂无访客请求审核"));
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                VisitorsBean.DataBean dataBean = (VisitorsBean.DataBean) adapter.getData().get(position);
                IntentUtil.getInstance().startActivityWithIntData(dataBean.getId(),VisitorRequestActivity.this,DealVisitorRequestActivity.class);
            }
        });
    }

    @Override
    public void startLoading(String tag) {

    }

    @Override
    public void stopLoading(String tag) {

    }

    @Override
    public void updateView(Object o, String tag) {
        if (RequestStatus.REFRESH.equals(tag) || VisitorContract.SEARCH_VISITOR_REQUEST.equals(tag)) {
            mSwipeLayout.setRefreshing(false);
            boolean isFresh = RequestStatus.REFRESH.equals(tag) ? true : false;
            VisitorsBean visitorsRequestBean = (VisitorsBean) o;
            if (visitorsRequestBean != null) {
                List<VisitorsBean.DataBean> arrays = visitorsRequestBean.getData();
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
        } else if (VisitorContract.ACCESS_VISITOR.equals(tag)) {
            int position = (int) o;
            VisitorsBean.DataBean dataBean = adapter.getData().get(position);
            dataBean.setState(2);
            adapter.notifyItemChanged(position);
        } else if (VisitorContract.REJECT_VISITOR.equals(tag)) {
            int position = (int) o;
            VisitorsBean.DataBean dataBean = adapter.getData().get(position);
            dataBean.setState(3);
            adapter.notifyItemChanged(position);

        }

    }

    @Override
    public void onError(String tag) {
        showToast(tag);
    }


    @Override
    public void onBackPressed() {
        setResult(ActivityResultManager.ADD_VISITOR_DEAL_REQUEST);
        super.onBackPressed();
    }
}
