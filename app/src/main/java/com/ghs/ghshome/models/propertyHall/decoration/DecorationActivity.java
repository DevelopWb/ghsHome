package com.ghs.ghshome.models.propertyHall.decoration;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ghs.ghshome.R;
import com.ghs.ghshome.base.BaseActivity;
import com.ghs.ghshome.base.network.RequestStatus;
import com.ghs.ghshome.bean.DecorationBean;
import com.ghs.ghshome.tools.ActivityResultManager;
import com.ghs.ghshome.tools.UserInfoUtil;

import java.util.List;

/**
 * 装修备案列表
 */
public class DecorationActivity extends BaseActivity<DecorationContrat.DecorationView, DecorationPresenter> implements View.OnClickListener, DecorationContrat.DecorationView {


    private RelativeLayout mDecorDecoration;
    private RelativeLayout mDecorFire;
    private RecyclerView mDecorRv;
    private DecorationAdapter decorationAdapter;
    private SwipeRefreshLayout decor_sfl;

    private int offset = 0;
    private int limit = 10;

    @Override
    public void setLayout() {
        setContentView(R.layout.activity_decoration);

    }

    @Override
    public void initLayoutView() {
        initActionBar("装修备案", "");
        initView();
    }

    @Override
    public DecorationPresenter creatPresenter() {
        return new DecorationPresenter();
    }


    @Override
    public void getDate() {
        offset = 0;
            showProgressDialog();
            getPresenter().getDecorationList(offset, limit, UserInfoUtil.getInstance().getUserId(), UserInfoUtil.getInstance().getRoomId(), RequestStatus.REFRESH);

    }



    public void initView() {
        mDecorDecoration = (RelativeLayout) findViewById(R.id.decor_decoration);
        mDecorDecoration.setOnClickListener(this);
        mDecorFire = (RelativeLayout) findViewById(R.id.decor_fire);
        mDecorFire.setOnClickListener(this);
        mDecorRv = (RecyclerView) findViewById(R.id.decor_rv);
        decorationAdapter = new DecorationAdapter(R.layout.decoration_rv_item_layout, null);
        initRecyclerview(mDecorRv, decorationAdapter, LinearLayoutManager.VERTICAL, false);
        decorationAdapter.setEmptyView(getAdapterEmptyView("暂无内容"));
        decor_sfl = findViewById(R.id.decor_sfl);
        decor_sfl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                decor_sfl.setRefreshing(true);
                offset = 0;
                decorationAdapter.setEnableLoadMore(false);//这里的作用是防止下拉刷新的时候还可以上拉加载
                getPresenter().getDecorationList(offset, limit, UserInfoUtil.getInstance().getUserId(), UserInfoUtil.getInstance().getRoomId(), RequestStatus.REFRESH);

            }
        });

        decorationAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                DecorationBean.DataBean.RowsBean rowsBean = decorationAdapter.getData().get(position);
                if (rowsBean != null) {
                    Intent intent = new Intent(DecorationActivity.this, DecorationDetailActivity.class);
                    intent.putExtra("decoration_type", rowsBean.getType());
                    intent.putExtra("decoration_id", rowsBean.getId());
                    startActivity(intent);

                }


            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.decor_decoration:
                //新建装修备案
                startActivityForResult(new Intent(DecorationActivity.this, CreateDecorationActivity.class), ActivityResultManager.FITMENT_SW);


                break;
            case R.id.decor_fire:
                startActivityForResult(new Intent(DecorationActivity.this, CreateFireEntryActivity.class), ActivityResultManager.FITMENT_SW);

                break;
        }
    }

    @Override
    public void startLoading(String tag) {

    }

    @Override
    public void stopLoading(String tag) {

    }

    @Override
    public void updateView(Object o, String tag) {
        decor_sfl.setRefreshing(false);
        boolean isFresh = RequestStatus.REFRESH.equals(tag) ? true : false;
        DecorationBean decorationBean = (DecorationBean) o;
        DecorationBean.DataBean decorationBeanData = decorationBean.getData();
        if (decorationBeanData != null) {
            List<DecorationBean.DataBean.RowsBean> rows = decorationBeanData.getRows();
            offset += rows.size();
            if (isFresh) {
                decorationAdapter.setNewData(rows);
            } else {
                decorationAdapter.addData(rows);
            }
            if (rows.size() < limit) {
                //第一页如果不够一页就不显示没有更多数据布局
                decorationAdapter.loadMoreEnd(isFresh);
            } else {
                decorationAdapter.loadMoreComplete();
            }


        }

    }

    @Override
    public void onError(String tag) {
        showToast(tag);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (ActivityResultManager.FITMENT_SW == requestCode) {
                getPresenter().getDecorationList(0, 10, UserInfoUtil.getInstance().getUserId(), UserInfoUtil.getInstance().getRoomId(), RequestStatus.REFRESH);
        }

    }
}
