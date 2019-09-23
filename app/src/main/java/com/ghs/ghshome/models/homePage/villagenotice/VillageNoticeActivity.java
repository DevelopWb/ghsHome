package com.ghs.ghshome.models.homePage.villagenotice;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ghs.ghshome.R;
import com.ghs.ghshome.base.BaseActivity;
import com.ghs.ghshome.base.network.RequestStatus;
import com.ghs.ghshome.bean.VillageNoticeBean;
import com.ghs.ghshome.models.homePage.HomePageContract;
import com.ghs.ghshome.tools.ActivityResultManager;
import com.ghs.ghshome.tools.UserInfoUtil;

import java.util.List;

/**
 * created by wang
 * created date 2018/7/23 13:58.
 * application  小区公告
 */
public class VillageNoticeActivity extends BaseActivity<VillageNoticeContract.IVillageNoticeView, VillageNoticePresent> implements VillageNoticeContract.IVillageNoticeView {

    private RecyclerView mMinePrizeRv;
    private VillageNoticeAdapter adapter;
    private SwipeRefreshLayout mVillageNoticeSl;
    private int offset = 0;
    private int limit = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void getDate() {
        showProgressDialog();
        offset = 0;
        getPresenter().getNoticeList(offset, limit, UserInfoUtil.getInstance().getUserId(), UserInfoUtil.getInstance().getVillageId(), RequestStatus.REFRESH);
        setResult(ActivityResultManager.VILLIAGE_NOTICE);
    }

    @Override
    public void initLayoutView() {
        initView();
        initActionBar("小区公告", null);
    }

    @Override
    public void setLayout() {
        setContentView(R.layout.activity_village_notice);

    }

    @Override
    public VillageNoticePresent creatPresenter() {

        return new VillageNoticePresent();
    }

    @Override
    public void startLoading(String tag) {

    }

    @Override
    public void stopLoading(String tag) {

    }

    @Override
    public void updateView(Object o, String tag) {
        mVillageNoticeSl.setRefreshing(false);
        boolean isFresh = RequestStatus.REFRESH.equals(tag) ? true : false;
        VillageNoticeBean villageNoticeBean = (VillageNoticeBean) o;
        if (villageNoticeBean != null) {
            VillageNoticeBean.DataBean dataBean = villageNoticeBean.getData();
            if (dataBean != null) {
                List<VillageNoticeBean.DataBean.RowsBean> rows = dataBean.getRows();
                if (rows != null) {
                    offset += rows.size();
                    if (isFresh) {
                        adapter.setNewData(rows);
                    } else {
                        adapter.addData(rows);
                    }
                    if (rows.size() < limit) {
                        //第一页如果不够一页就不显示没有更多数据布局
                        adapter.loadMoreEnd(isFresh);
                    } else {
                        adapter.loadMoreComplete();
                    }
                }
            }


        }

    }

    @Override
    public void onError(String tag) {
        switch (tag) {
            case RequestStatus.UPDATE:
                break;
            case RequestStatus.REFRESH:
                mVillageNoticeSl.setRefreshing(false);
                break;
            default:
                break;
        }
    }

    private void initView() {
        mMinePrizeRv = (RecyclerView) findViewById(R.id.mine_prize_rv);
        adapter = new VillageNoticeAdapter(R.layout.village_notice_item);
        adapter.setEmptyView(getAdapterEmptyView("暂无内容"));
        initRecyclerview(mMinePrizeRv, adapter, LinearLayoutManager.VERTICAL, false);
        addDivider(true,mMinePrizeRv,false,true);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                VillageNoticeBean.DataBean.RowsBean dataBean = (VillageNoticeBean.DataBean.RowsBean) adapter.getData().get(position);
                int noticeId = dataBean.getId();
                Intent intent = new Intent(VillageNoticeActivity.this, NoticeDetailActivity.class);
                intent.putExtra(HomePageContract.NOTICI_ID,noticeId);
                intent.putExtra(HomePageContract.NOTICI_TYPE, HomePageContract.NOTICI_VILLAGE);
                startActivityForResult(intent, ActivityResultManager.VILLIAGE_NOTICE_DETAIL);
            }
        });
        mVillageNoticeSl = (SwipeRefreshLayout) findViewById(R.id.village_notice_sl);
        mVillageNoticeSl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mVillageNoticeSl.setRefreshing(true);
                offset = 0;
                adapter.setEnableLoadMore(false);//这里的作用是防止下拉刷新的时候还可以上拉加载
                getPresenter().getNoticeList(offset, limit, UserInfoUtil.getInstance().getUserId(), UserInfoUtil.getInstance().getVillageId(), RequestStatus.REFRESH);

            }
        });
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                getPresenter().getNoticeList(offset, limit, UserInfoUtil.getInstance().getUserId(), UserInfoUtil.getInstance().getVillageId(), RequestStatus.UPDATE);

            }
        }, mMinePrizeRv);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        offset = 0;
//        getPresenter().getNoticeList(offset, limit, UserInfoUtil.getInstance().getUserId(), UserInfoUtil.getInstance().getVillageId(), RequestStatus.REFRESH);


    }

}
