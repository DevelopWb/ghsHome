package com.ghs.ghshome.models.homePage.officialMsg;

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
import com.ghs.ghshome.bean.OfficialNoticeListBean;
import com.ghs.ghshome.models.homePage.villagenotice.NoticeDetailActivity;
import com.ghs.ghshome.models.homePage.HomePageContract;
import com.ghs.ghshome.models.homePage.HomePagePresent;
import com.ghs.ghshome.tools.ActivityResultManager;

import java.util.List;
/**
 * created by tobato
 * created date 2019/6/5 10:36.
 * application  官方消息
 */
public class OfficialMessageActivity extends BaseActivity<HomePageContract.IHomePageView, HomePagePresent> implements HomePageContract.IHomePageView {


    private OfficialMessageAdapter adapter;
    private int offset = 0;
    private int limit = 5;
    private SwipeRefreshLayout mOfficialMsgSl;
    private RecyclerView mOfficialMsgRv;
    private int clickedPosition;//点击的条目
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afficial_message);
        initView();
        initActionBar("官方消息", "");

    }
    @Override
    public void getDate() {
        offset = 0;
        getPresenter().getOfficialNoticeList(offset, limit, mUserInfoUtil.getUserId(), RequestStatus.REFRESH);
    }

    @Override
    public void initLayoutView() {

    }

    private void initView() {
        mOfficialMsgSl = (SwipeRefreshLayout) findViewById(R.id.official_msg_sl);
        mOfficialMsgRv = (RecyclerView) findViewById(R.id.official_msg_rv);
        adapter = new OfficialMessageAdapter(R.layout.village_notice_item);
        adapter.setEmptyView(getAdapterEmptyView("暂无内容"));
        initRecyclerview(mOfficialMsgRv, adapter, LinearLayoutManager.VERTICAL, false);
        addDivider(true, mOfficialMsgRv, false, true);

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                clickedPosition = position;
                OfficialNoticeListBean.DataBean.RowsBean bean = (OfficialNoticeListBean.DataBean.RowsBean) adapter.getData().get(position);
                int  noticeId = bean.getId();
                Intent intent = new Intent(OfficialMessageActivity.this, NoticeDetailActivity.class);
                intent.putExtra(HomePageContract.NOTICI_ID, noticeId);
                intent.putExtra(HomePageContract.NOTICI_TYPE, HomePageContract.OFFICICAL_NOTICE);
                startActivityForResult(intent,ActivityResultManager.OFFICAL_MSG_DETAIL);

            }
        });
        mOfficialMsgSl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                adapter.setEnableLoadMore(false);//这里的作用是防止下拉刷新的时候还可以上拉加载
                mOfficialMsgSl.setRefreshing(true);
                offset = 0;
                getPresenter().getOfficialNoticeList(offset, limit, mUserInfoUtil.getUserId(), RequestStatus.REFRESH);

            }
        });

        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                getPresenter().getOfficialNoticeList(offset, limit, mUserInfoUtil.getUserId(), "");

            }
        }, mOfficialMsgRv);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode== ActivityResultManager.OFFICAL_MSG_DETAIL) {
            OfficialNoticeListBean.DataBean.RowsBean bean = (OfficialNoticeListBean.DataBean.RowsBean) adapter.getData().get(clickedPosition);
            bean.setUserNoticeType(1);
            adapter.notifyItemChanged(clickedPosition);
        }
    }

    @Override
    public void setLayout() {
    }
    @Override
    public void onBackPressed() {
        setResult(ActivityResultManager.HOME_PAGE_REQUEST);
        finish();
        super.onBackPressed();
    }

    @Override
    public HomePagePresent creatPresenter() {
        return new HomePagePresent();
    }



    @Override
    public void startLoading(String tag) {

    }

    @Override
    public void stopLoading(String tag) {

    }

    @Override
    public void updateView(Object o, String tag) {
        mOfficialMsgSl.setRefreshing(false);
        boolean isFresh = RequestStatus.REFRESH.equals(tag) ? true : false;
        OfficialNoticeListBean officialNoticeListBean = (OfficialNoticeListBean) o;
        if (officialNoticeListBean != null) {
            OfficialNoticeListBean.DataBean dataBean = officialNoticeListBean.getData();
            if (dataBean != null) {
                List<OfficialNoticeListBean.DataBean.RowsBean> arrays = dataBean.getRows();
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
    }

    @Override
    public void onError(String tag) {
        mOfficialMsgSl.setRefreshing(false);
    }




}
