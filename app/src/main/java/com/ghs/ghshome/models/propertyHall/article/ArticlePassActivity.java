package com.ghs.ghshome.models.propertyHall.article;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ghs.ghshome.R;
import com.ghs.ghshome.base.BaseActivity;
import com.ghs.ghshome.base.network.RequestStatus;
import com.ghs.ghshome.bean.PropertyListBean;
import com.ghs.ghshome.models.propertyHall.article.ArticleAdapter.ArticleAdapter;
import com.ghs.ghshome.tools.ActivityResultManager;
import com.ghs.ghshome.tools.UserInfoUtil;

import java.util.List;

public class ArticlePassActivity extends BaseActivity<ArticlePassContract.ProPertyView, ArticlePassPresenter> implements ArticlePassContract.ProPertyView {
    private int offset = 0;
    private int limit = 10;
    private RecyclerView mPropretyRv;
    private SwipeRefreshLayout mPropertySr;
    private ArticleAdapter propertyAdapter;
    private boolean haveData = true;//有数据
    private TextView entry_confirm;

    @Override
    public void setLayout() {

        setContentView(R.layout.activity_property_pass2);
    }

    @Override
    public void initLayoutView() {

        initActionBar("物品放行", "");
        initView();
    }

    @Override
    public ArticlePassPresenter creatPresenter() {
        return new ArticlePassPresenter();
    }

    @Override
    public void getDate() {
        offset = 0;
            showProgressDialog();
            getPresenter().getPropertyList(offset, limit, UserInfoUtil.getInstance().getUserId(), UserInfoUtil.getInstance().getRoomId(), RequestStatus.REFRESH);


    }

    @Override
    public void actionBarRightTvOnClick() {

    }

    @Override
    public void startLoading(String tag) {

    }

    @Override
    public void stopLoading(String tag) {

    }

    @Override
    public void updateView(Object o, String tag) {
        mPropertySr.setRefreshing(false);
        boolean isFresh = RequestStatus.REFRESH.equals(tag) ? true : false;
        PropertyListBean propertyListBean = (PropertyListBean) o;
        PropertyListBean.DataBean data = propertyListBean.getData();
        if (data != null) {
            List<PropertyListBean.DataBean.RowsBean> rows = data.getRows();
            if (rows != null) {
                offset += rows.size();
                if (isFresh) {
                    propertyAdapter.setNewData(rows);
                } else {
                    propertyAdapter.addData(rows);
                }
                if (rows.size() < limit) {
                    //第一页如果不够一页就不显示没有更多数据布局
                    propertyAdapter.loadMoreEnd(isFresh);
                } else {
                    propertyAdapter.loadMoreComplete();
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
                mPropertySr.setRefreshing(false);
                break;
            default:
                break;
        }
    }


    public void initView() {
        mPropretyRv = (RecyclerView) findViewById(R.id.proprety_rv);
        mPropertySr = (SwipeRefreshLayout) findViewById(R.id.property_sr);
        mPropertySr.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPropertySr.setRefreshing(true);
                offset = 0;
                propertyAdapter.setEnableLoadMore(false);//这里的作用是防止下拉刷新的时候还可以上拉加载
                getPresenter().getPropertyList(offset, limit, UserInfoUtil.getInstance().getUserId(), UserInfoUtil.getInstance().getRoomId(), RequestStatus.REFRESH);

            }
        });
        propertyAdapter = new ArticleAdapter(R.layout.property_list_item_layout, null);
        initRecyclerview(mPropretyRv, propertyAdapter, LinearLayoutManager.VERTICAL, false);
        propertyAdapter.setEmptyView(getAdapterEmptyView("暂无内容"));
        propertyAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                getPresenter().getPropertyList(offset, limit, UserInfoUtil.getInstance().getUserId(), UserInfoUtil.getInstance().getRoomId(), RequestStatus.UPDATE);

            }
        }, mPropretyRv);

        entry_confirm = findViewById(R.id.entry_confirm);
        entry_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                startActivityForResult(new Intent(ArticlePassActivity.this, AddArticlePassActivity.class), ActivityResultManager.ADD_PROPERTY);
            }
        });


        propertyAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {


                PropertyListBean.DataBean.RowsBean rowsBean = propertyAdapter.getData().get(position);
                if (rowsBean != null) {
                    int id = rowsBean.getId();
                    Intent intent = new Intent(ArticlePassActivity.this, ArticleDetailActivity.class);
                    intent.putExtra(ActivityResultManager.ARTICLE_ID, id);
                    startActivity(intent);

                }

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ActivityResultManager.ADD_PROPERTY) {
            mPropertySr.setRefreshing(true);
            offset = 0;
            propertyAdapter.setEnableLoadMore(false);//这里的作用是防止下拉刷新的时候还可以上拉加载
               getPresenter().getPropertyList(offset, limit, UserInfoUtil.getInstance().getUserId(), UserInfoUtil.getInstance().getRoomId(), RequestStatus.REFRESH);
        }
    }
}
