package com.ghs.ghshome.models.homePage.keymanager.tenementmanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ghs.ghshome.R;
import com.ghs.ghshome.base.BaseActivity;
import com.ghs.ghshome.base.BasePresent;
import com.ghs.ghshome.base.network.RequestStatus;
import com.ghs.ghshome.bean.TenementRoomBean;
import com.ghs.ghshome.models.homePage.keymanager.tenementmanager.adapter.TenementAdapter;
import com.ghs.ghshome.tools.ActivityResultManager;
import com.ghs.ghshome.tools.UserInfoUtil;

import java.util.List;

public class TenementManagerActivity extends BaseActivity<TenementContract.TenementView, TenementPresent> implements TenementContract.TenementView, View.OnClickListener {

    private RecyclerView mTenementRv;
    private SwipeRefreshLayout mTenementSrf;
    /**
     * 添加授权
     */
    private TextView mAddKeyFab;
    private TenementAdapter tenementAdapter;
    private TextView add_key_fab;


    @Override
    protected void setLayout() {
        setContentView(R.layout.activity_tenement_manager);
        initView();
        initActionBar("授权管理", "");

    }

    @Override
    public TenementPresent creatPresenter() {
        return new TenementPresent();
    }


    @Override
    public void getDate() {

        getPresenter().getUserRoomList(UserInfoUtil.getInstance().getRoomId(), TenementContract.TENEMENT_ROOMLIST);

    }

    private void initView() {
        mTenementRv = (RecyclerView) findViewById(R.id.tenement_rv);
        add_key_fab = findViewById(R.id.add_key_fab);
        add_key_fab.setOnClickListener(this);
        if(UserInfoUtil.getInstance().getCurrentVillageBean().getRoleType()!=1){
            add_key_fab.setVisibility(View.GONE);
        }
        mTenementSrf = (SwipeRefreshLayout) findViewById(R.id.tenement_srf);
        tenementAdapter = new TenementAdapter(R.layout.allot_key_item, null);
        tenementAdapter.setEmptyView(getAdapterEmptyView("暂无数据"));
        tenementAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                List<TenementRoomBean.DataBean> dataBeans = tenementAdapter.getData();
                TenementRoomBean.DataBean dataBean = dataBeans.get(position);
                Intent intent = new Intent(TenementManagerActivity.this, TenementDetailsActivity.class);
                intent.putExtra(TenementContract.TENEMENT_DETAILS, dataBean);
                startActivityForResult(intent, ActivityResultManager.TENEMENT_DETAILS);


            }
        });
        initRecyclerview(mTenementRv, tenementAdapter, LinearLayoutManager.VERTICAL, false);
        mAddKeyFab = (TextView) findViewById(R.id.add_key_fab);
        //刷新
        mTenementSrf.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                getPresenter().getUserRoomList(UserInfoUtil.getInstance().getRoomId(), RequestStatus.REFRESH);

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
        if (RequestStatus.REFRESH.equals(tag)) {
            mTenementSrf.setRefreshing(false);
        }
        TenementRoomBean tenementRoomBean = (TenementRoomBean) o;
        List<TenementRoomBean.DataBean> tenementRoomBeanData = tenementRoomBean.getData();
        tenementAdapter.setNewData(tenementRoomBeanData);


    }

    @Override
    public void onError(String tag) {
        showToast(tag);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ActivityResultManager.TENEMENT_DETAILS) {
            getPresenter().getUserRoomList(UserInfoUtil.getInstance().getRoomId(), TenementContract.TENEMENT_ROOMLIST);

        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            default:
                break;
            case R.id.add_key_fab:
                startActivityForResult(new Intent(TenementManagerActivity.this, AddTenementActivity.class),ActivityResultManager.TENEMENT_DETAILS);

                break;
        }


    }
}
