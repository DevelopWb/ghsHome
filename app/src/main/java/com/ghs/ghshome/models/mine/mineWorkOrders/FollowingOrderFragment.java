package com.ghs.ghshome.models.mine.mineWorkOrders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ghs.ghshome.R;
import com.ghs.ghshome.base.BaseFragment;
import com.ghs.ghshome.base.network.RequestStatus;
import com.ghs.ghshome.bean.WorkOrderBean;
import com.ghs.ghshome.models.homePage.oneKeyCall.WorkOrderDetailActivity;
import com.ghs.ghshome.models.homePage.oneKeyCall.WorkOrderListAdapter;
import com.ghs.ghshome.models.mine.MineContract;
import com.ghs.ghshome.models.mine.MinePresent;
import com.ghs.ghshome.tools.ActivityResultManager;

import java.util.List;

/**
 * created by tobato
 * created date 2019/5/31 13:59.
 * application   跟进中工单
 */

public class FollowingOrderFragment extends BaseFragment<MineContract.IMineView, MinePresent> implements MineContract.IMineView {


    private View view;
    private RecyclerView mUnNormalNoticeRv;
    private int offset = 0;
    private int limit = 10;
    private int status = 1 ;
    private SwipeRefreshLayout mSwipeLayout;
    private WorkOrderListAdapter adapter;

//    public static FollowingOrderFragment getInstance() {
//        return FollowingOrderHolder.instatce;
//    }
//    private static class FollowingOrderHolder {
//        private static FollowingOrderFragment instatce = new FollowingOrderFragment();
//    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mine_work_order, container, false);
        initView(view);
        return view;
    }

    @Override
    protected void lazyLoad() {
        offset = 0;
        getPresenter().getCustomerWorkOrders(offset, limit, mUserInfoUtil.getVillageId(), mUserInfoUtil.getUserId(), status, RequestStatus.REFRESH);

    }

    private void initView(View view) {
        mUnNormalNoticeRv = (RecyclerView) view.findViewById(R.id.mine_work_order_rv);
        adapter = new WorkOrderListAdapter(R.layout.work_order_list_item,true);
        initRecyclerview(mUnNormalNoticeRv, adapter, LinearLayoutManager.VERTICAL, false);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                WorkOrderBean.DataBean bean = (WorkOrderBean.DataBean) adapter.getData().get(position);
                Intent intent = new Intent(getContext(), WorkOrderDetailActivity.class);
                intent.putExtra("workOrderId", bean.getId());
                startActivityForResult(intent, ActivityResultManager.ENTER_ORDER_DETAIL);
            }
        });
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                getPresenter().getCustomerWorkOrders(offset, limit, mUserInfoUtil.getVillageId(), mUserInfoUtil.getUserId(), status,"");

            }
        }, mUnNormalNoticeRv);
        mSwipeLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeLayout);
        mSwipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                offset = 0;
                adapter.setEnableLoadMore(false);//这里的作用是防止下拉刷新的时候还可以上拉加载
                mSwipeLayout.setRefreshing(true);
                getPresenter().getCustomerWorkOrders(offset, limit, mUserInfoUtil.getVillageId(), mUserInfoUtil.getUserId(), status, RequestStatus.REFRESH);


            }
        });
        adapter.setEmptyView(getAdapterEmptyView("暂无内容"));

    }

    @Override
    protected View initFragmentViewLayout(LayoutInflater inflater, ViewGroup container) {
        return null;
    }


    @Override
    protected MinePresent createPresenter() {
        return new MinePresent();
    }

    @Override
    public void onResume() {
        super.onResume();


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ActivityResultManager.ENTER_ORDER_DETAIL) {
            offset = 0;
            getPresenter().getCustomerWorkOrders(offset, limit, mUserInfoUtil.getVillageId(), mUserInfoUtil.getUserId(), status, RequestStatus.REFRESH);
        }
    }

//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void stringMsgReceived(String str) {
//        if (MainContact.CHANGE_VILLAGE.equals(str)||ActivityResultManager.TASK_FRAGMENT_REFRESH.equals(str)) {//
//            offset = 0;
//                 getPresenter().getCustomerWorkOrders(offset, limit, mUserInfoUtil.getVillageId(), mUserInfoUtil.getUserId(), status, RequestStatus.REFRESH);
//
//        }
//    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onStop() {
        super.onStop();

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
        boolean isFresh = RequestStatus.REFRESH.equals(tag) ? true : false;
        WorkOrderBean workOrderBean = (WorkOrderBean) o;
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





}
