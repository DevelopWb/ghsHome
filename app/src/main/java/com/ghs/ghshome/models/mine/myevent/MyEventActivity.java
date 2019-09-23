package com.ghs.ghshome.models.mine.myevent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ghs.ghshome.R;
import com.ghs.ghshome.base.BaseActivity;
import com.ghs.ghshome.bean.CommunityListBean;
import com.ghs.ghshome.models.discover.CommunityDetailActivity;
import com.ghs.ghshome.models.discover.DiscoverContract;
import com.ghs.ghshome.models.discover.DiscoverPresenter;
import com.ghs.ghshome.models.discover.adapter.CommunityListAdapter;

import java.util.List;
/**
 * created by tobato
 * created date 2019/7/23 16:50.
 * application  我的活动
 */
public class MyEventActivity extends BaseActivity<DiscoverContract.IDiscoverView, DiscoverPresenter> implements DiscoverContract.IDiscoverView {

    private RecyclerView mMyEventRv;
    private CommunityListAdapter listAdapter;
    private SwipeRefreshLayout mMyEventSw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_event);
        initView();
        initActionBar("我的活动", "");

    }

    @Override
    protected void setLayout() {

    }

    @Override
    public DiscoverPresenter creatPresenter() {
        return new DiscoverPresenter();
    }

    @Override
    public void getDate() {


            showProgressDialog();
            getPresenter().getMyEventList(mUserInfoUtil.getUserId(), mUserInfoUtil.getVillageId(), DiscoverContract.MY_EVENT_LIST);

    }

    @Override
    public void startLoading(String tag) {

    }

    @Override
    public void stopLoading(String tag) {

    }

    @Override
    public void updateView(Object o, String tag) {
        mMyEventSw.setRefreshing(false);
        CommunityListBean myListBean = (CommunityListBean) o;
        List<CommunityListBean.DataBean> myListBeanData = myListBean.getData();
        if (myListBeanData != null) {
            listAdapter.setNewData(myListBeanData);
        }

    }

    @Override
    public void onError(String tag) {
        showToast(tag);
    }

    private void initView() {
        mMyEventRv = (RecyclerView) findViewById(R.id.my_event_rv);
        listAdapter = new CommunityListAdapter(R.layout.commity_item_layout, null);
        listAdapter.setEmptyView(getAdapterEmptyView("暂时没有参加的活动！"));
        initRecyclerview(mMyEventRv, listAdapter, LinearLayoutManager.VERTICAL, false);
        addDivider(true,mMyEventRv,false,true);
        mMyEventSw = (SwipeRefreshLayout) findViewById(R.id.my_event_sw);
        mMyEventSw.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                  //防止继续下拉
                mMyEventSw.setRefreshing(true);
                    getPresenter().getMyEventList(mUserInfoUtil.getUserId(), mUserInfoUtil.getVillageId(), DiscoverContract.MY_EVENT_LIST);

            }
        });

        listAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                List<CommunityListBean.DataBean> data = listAdapter.getData();
                if (data != null) {
                    Intent intent = new Intent(MyEventActivity.this, CommunityDetailActivity.class);
                    int id = data.get(position).getId();
                    intent.putExtra("DIS_COMMUN_PL_ID", id);
                    startActivity(intent);

                }

            }
        });
    }
}
