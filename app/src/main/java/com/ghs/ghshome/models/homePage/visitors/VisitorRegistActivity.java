package com.ghs.ghshome.models.homePage.visitors;

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
import com.ghs.ghshome.base.network.RequestStatus;
import com.ghs.ghshome.bean.VisitorsBean;
import com.ghs.ghshome.models.homePage.visitors.addVisitor.AddVisitorActivity;
import com.ghs.ghshome.models.homePage.visitors.addVisitor.VistorDetailActivity;
import com.ghs.ghshome.models.homePage.visitors.visitorRequest.VisitorRequestActivity;
import com.ghs.ghshome.tools.ActivityResultManager;
import com.ghs.ghshome.tools.IntentUtil;

import java.util.List;

/**
 * created by tobato
 * created date 2018/11/16 15:37.
 * application  访客登记
 */
public class VisitorRegistActivity extends BaseActivity<VisitorContract.IVisitorView, VisitorPresent> implements VisitorContract.IVisitorView, View.OnClickListener {

    private RecyclerView mVisitorRegistRv;


    private VisitorAdapter visitorAdapter;
    private int offset = 0;
    private int limit = 10;
    private SwipeRefreshLayout mSwipeLayout;
    private ConstraintLayout mVisitorRequestCl;
    /**
     * 1
     */
    private TextView mVisitorAmountTv;
    private TextView entry_confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void getDate() {
        showProgressDialog();
        offset = 0;
        getPresenter().searchVisitors(mUserInfoUtil.getVillageId(), mUserInfoUtil.getRoomId(), mUserInfoUtil.getUserId(), 1, 0, 20, VisitorContract.SEARCH_VISITOR_REQUEST);
        getPresenter().searchVisitors(mUserInfoUtil.getVillageId(), mUserInfoUtil.getRoomId(), mUserInfoUtil.getUserId(), 2, offset, limit, RequestStatus.REFRESH);
    }

    @Override
    public void initLayoutView() {
        initView();
        initActionBar("访客登记", "");

    }

    @Override
    public void setLayout() {
        setContentView(R.layout.activity_visitor_regist);

    }

    @Override
    public void actionBarRightTvOnClick() {
//        startActivityForResult(new Intent(this, AddVisitorActivity.class), ActivityResultManager.ADD_VISITOR_ADD_VISITOR);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (IntentUtil.getInstance().REQUEST_CODE == requestCode || ActivityResultManager.ADD_VISITOR_DEAL_REQUEST == resultCode) {
            getDate();
        }
    }


    @Override
    public VisitorPresent creatPresenter() {
        return new VisitorPresent();
    }


    private void initView() {
        mVisitorRegistRv = (RecyclerView) findViewById(R.id.visitor_regist_rv);
//        mHeaderRightTv.setTextSize(20);
        visitorAdapter = new VisitorAdapter(R.layout.visitor_item);
        visitorAdapter.setEmptyView(getAdapterEmptyView("暂无内容"));
        initRecyclerview(mVisitorRegistRv, visitorAdapter, LinearLayoutManager.VERTICAL, false);
        visitorAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                getPresenter().searchVisitors(mUserInfoUtil.getVillageId(), mUserInfoUtil.getRoomId(), mUserInfoUtil.getUserId(), 2, offset, limit, VisitorContract.SEARCH_VISITOR);

            }
        }, mVisitorRegistRv);
        mSwipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipeLayout);
        mSwipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                visitorAdapter.setEnableLoadMore(false);//这里的作用是防止下拉刷新的时候还可以上拉加载
                mSwipeLayout.setRefreshing(true);
                getDate();
            }
        });
        mVisitorRequestCl = (ConstraintLayout) findViewById(R.id.visitor_request_cl);
        mVisitorRequestCl.setOnClickListener(this);
        mVisitorAmountTv = (TextView) findViewById(R.id.visitor_amount_tv);
        entry_confirm = findViewById(R.id.entry_confirm);
        entry_confirm.setOnClickListener(this);
        visitorAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                VisitorsBean.DataBean dataBean = visitorAdapter.getData().get(position);
                if (dataBean != null) {
                    IntentUtil.getInstance().startActivityWithParcelableData(dataBean, VisitorRegistActivity.this, VistorDetailActivity.class);
                }


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
        if (VisitorContract.SEARCH_VISITOR.equals(tag) || RequestStatus.REFRESH.equals(tag)) {
            mSwipeLayout.setRefreshing(false);
            VisitorsBean visitorsBean = (VisitorsBean) o;
            boolean isFresh = RequestStatus.REFRESH.equals(tag) ? true : false;
            if (visitorsBean != null) {
                List<VisitorsBean.DataBean> beans = visitorsBean.getData();

                if (beans != null) {
                    offset += beans.size();
                    if (isFresh) {
                        visitorAdapter.setNewData(beans);
                    } else {
                        visitorAdapter.addData(beans);
                    }
                    if (beans.size() < limit) {
                        //第一页如果不够一页就不显示没有更多数据布局
                        visitorAdapter.loadMoreEnd(isFresh);
                    } else {
                        visitorAdapter.loadMoreComplete();
                    }
                }
            }
        } else if (VisitorContract.SEARCH_VISITOR_REQUEST.equals(tag)) {
            VisitorsBean visitorsRequestBean = (VisitorsBean) o;
            if (visitorsRequestBean != null) {
                List<VisitorsBean.DataBean> beans = visitorsRequestBean.getData();
                if (beans != null) {
                    if (beans.size() > 0) {
                        mVisitorRequestCl.setVisibility(View.VISIBLE);
                        mVisitorAmountTv.setText(String.valueOf(beans.size()));
                    } else {
                        mVisitorRequestCl.setVisibility(View.GONE);

                    }

                }
            }
        }
    }

    @Override
    public void onError(String tag) {
        mSwipeLayout.setRefreshing(false);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.visitor_request_cl:
                startActivityForResult(new Intent(VisitorRegistActivity.this, VisitorRequestActivity.class), ActivityResultManager.ADD_VISITOR_DEAL_REQUEST);

                break;
            case R.id.entry_confirm:
                IntentUtil.getInstance().startActivityWithoutData(VisitorRegistActivity.this, AddVisitorActivity.class);
                break;
        }
    }
}
