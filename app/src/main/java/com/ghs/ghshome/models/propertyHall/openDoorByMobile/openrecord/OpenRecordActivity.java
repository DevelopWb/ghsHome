package com.ghs.ghshome.models.propertyHall.openDoorByMobile.openrecord;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Switch;

import com.ghs.ghshome.R;
import com.ghs.ghshome.base.BaseActivity;
import com.ghs.ghshome.base.network.RequestStatus;
import com.ghs.ghshome.bean.UserAndRoomBean;
import com.ghs.ghshome.models.propertyHall.openDoorByMobile.openrecord.adapter.OpenRecordAdapter;
import com.ghs.ghshome.tools.ActivityResultManager;
import com.ghs.ghshome.tools.CalendarUtil;
import com.ghs.ghshome.tools.UserInfoUtil;
import com.orhanobut.hawk.Hawk;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OpenRecordActivity extends BaseActivity<OpenRecordContract.OpenRecordView, OpenRecordPresent> implements OpenRecordContract.OpenRecordView {

    private RecyclerView mOpenRecordRv;
    private Switch mOpenRecordAllowedS;
    private SwipeRefreshLayout mOpenRecordSl;
    private OpenRecordAdapter adapter;
    private LinearLayout mOpenRecordAllowsLl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public void getDate() {
        getPresenter().getOpenRecordList(UserInfoUtil.getInstance().getUserId(), UserInfoUtil.getInstance().getRoomId(), UserInfoUtil.getInstance().getRoleType(), CalendarUtil.getDateSomeDaysAgo(-7), new SimpleDateFormat("yyyy-MM-dd").format(new Date()), RequestStatus.UPDATE);

    }

    @Override
    public void initLayoutView() {
        initView();
        initActionBar("开门记录", "");


    }

    @Override
    public void onBackPressed() {
        setResult(ActivityResultManager.OPEN_RECORD);
        super.onBackPressed();
    }

    @Override
    public void setLayout() {
        setContentView(R.layout.activity_open_record);

    }


    @Override
    public OpenRecordPresent creatPresenter() {
        return new OpenRecordPresent();
    }


    private void initView() {
        mOpenRecordRv = (RecyclerView) findViewById(R.id.open_record_rv);
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mOpenRecordRv.setLayoutManager(manager);
        adapter = new OpenRecordAdapter();
        mOpenRecordRv.setAdapter(adapter);
        mOpenRecordAllowedS = (Switch) findViewById(R.id.open_record_allowed_s);
        mOpenRecordAllowsLl = findViewById(R.id.open_record_allowed_ll);

        mOpenRecordSl = (SwipeRefreshLayout) findViewById(R.id.open_record_sl);
        if (UserInfoUtil.getInstance().getRoleType() == 1 || UserInfoUtil.getInstance().getRoleType() == 3) {//角色：业主
            mOpenRecordAllowsLl.setVisibility(View.GONE);
        } else {
            mOpenRecordAllowsLl.setVisibility(View.VISIBLE);
            if (UserInfoUtil.getInstance().getShowLockLog() == 1) {//展示
                mOpenRecordAllowedS.setChecked(true);
            } else {
                mOpenRecordAllowedS.setChecked(false);
            }

        }
        mOpenRecordSl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getPresenter().getOpenRecordList(UserInfoUtil.getInstance().getUserId(), UserInfoUtil.getInstance().getRoomId(), UserInfoUtil.getInstance().getRoleType(), CalendarUtil.getDateSomeDaysAgo(-7), new SimpleDateFormat("yyyy-MM-dd").format(new Date()), RequestStatus.REFRESH);

            }
        });
        mOpenRecordAllowedS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!mOpenRecordAllowedS.isChecked()) {
                    getPresenter().allowedToSee(UserInfoUtil.getInstance().getRoomUserId(), 2, OpenRecordContract.UN_ALLOW_SHOW);

                } else {
                    getPresenter().allowedToSee(UserInfoUtil.getInstance().getRoomUserId(), 1, OpenRecordContract.ALLOW_SHOW);

                }
            }
        });
    }

    @Override
    public void startLoading(String tag) {
        if (RequestStatus.UPDATE.equals(tag)) {
            showProgressDialog();
        }else{
            mOpenRecordSl.setRefreshing(true);

        }
    }

    @Override
    public void stopLoading(String tag) {
        if (RequestStatus.UPDATE.equals(tag)) {
          stopProgressDialog();
        }else{
            mOpenRecordSl.setRefreshing(false);
        }

    }

    @Override
    public void updateView(Object o, String tag) {
        switch (tag) {
            case RequestStatus.REFRESH:
                mOpenRecordSl.setRefreshing(false);
                showToast("刷新成功");
                initRecycleData(o);
                break;
            case RequestStatus.UPDATE:
                initRecycleData(o);
                break;
            case OpenRecordContract.ALLOW_SHOW:
                refreshUserRoomBean(1);
                showToast("已允许");
                break;
            case OpenRecordContract.UN_ALLOW_SHOW:
                refreshUserRoomBean(2);

                showToast("已拒绝");
                break;
            default:
                break;
        }


        if (tag.equals(RequestStatus.REFRESH)) {

        } else if (tag.equals(RequestStatus.UPDATE)) {

        }

    }

    /**
     * 刷新userRoom实体类  1 允许将 2 不允许
     */
    private void refreshUserRoomBean(int i) {
        UserAndRoomBean bean = Hawk.get(mUserInfoUtil.getUserAndRoomKey());
        if (bean.getData().getGhsUserRoomDO() != null) {
            bean.getData().getGhsUserRoomDO().setShowLockLog(i);
        }
        Hawk.put(mUserInfoUtil.getUserAndRoomKey(),bean);
    }

    /**
     * 初始化无数据时的布局
     *
     * @param o
     */
    private void initRecycleData(Object o) {
        List<Object> arrays = new ArrayList<>();
        arrays = (List<Object>) o;
        if (arrays.size() == 0) {
            showNoDataActivityLayout(true, "暂无内容");
        } else {
            showNoDataActivityLayout(false, "暂无内容");
            adapter.setDate(arrays);
        }
    }

    @Override
    public void onError(String tag) {

    }

}
