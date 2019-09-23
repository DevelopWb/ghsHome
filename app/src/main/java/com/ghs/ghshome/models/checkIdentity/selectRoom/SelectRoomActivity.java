package com.ghs.ghshome.models.checkIdentity.selectRoom;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ghs.ghshome.R;
import com.ghs.ghshome.base.BaseActivity;
import com.ghs.ghshome.bean.CellsBean;
import com.ghs.ghshome.bean.RoomsBean;
import com.ghs.ghshome.bean.UserAndRoomBean;
import com.ghs.ghshome.bean.VillageListBean;
import com.ghs.ghshome.models.checkIdentity.CheckIdentityContract;
import com.ghs.ghshome.models.checkIdentity.CheckIdentityPresent;
import com.ghs.ghshome.tools.IntentUtil;

import java.util.List;

/**
 * created by tobato
 * created date 2019/9/11 16:22.
 * application  选择房间
 */
public class SelectRoomActivity extends BaseActivity<CheckIdentityContract.ICheckIdentityView, CheckIdentityPresent> implements CheckIdentityContract.ICheckIdentityView{

    private RecyclerView mSelectRoomRv;
    private CellAdapter cellAdapter;
    private DrawerLayout mSelectRoomDrawerlayout;
    /**
     * 标题
     */
    private TextView mCellNameTv;
    private RecyclerView mRoomListRv;
    private SwipeRefreshLayout mCellSl;
    private VillageListBean.DataBean dataBean;
    private RoomAdapter roomAdapter;
    private UserAndRoomBean.DataBean.GhsUserRoomDOBean roomDOBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void setLayout() {
        setContentView(R.layout.activity_select_room);
        initView();
    }

    @Override
    public CheckIdentityPresent creatPresenter() {
        return new CheckIdentityPresent();
    }
    @Override
    public void getDate() {

        dataBean = (VillageListBean.DataBean) IntentUtil.getInstance().getIntentParcelableData(this);
        if (dataBean != null) {
            showProgressDialog();
            getPresenter().getCellList(dataBean.getId(), CheckIdentityContract.GET_CELL_LIST);
            mCustomCab.getActionBarTitleTv().setText(dataBean.getVillageName());
        }else{
            roomDOBean = mUserInfoUtil.getCurrentVillageBean();
            if (roomDOBean != null) {
                showProgressDialog();
                getPresenter().getCellList(roomDOBean.getVillageId(), CheckIdentityContract.GET_CELL_LIST);
                mCustomCab.getActionBarTitleTv().setText(roomDOBean.getVillageName());
            }
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
        switch (tag) {
            case CheckIdentityContract.GET_CELL_LIST:
                CellsBean cellsBean = (CellsBean) o;
                if (cellsBean != null) {
                    List<CellsBean.DataBean> arrays = cellsBean.getData();
                    if (arrays != null) {
                        cellAdapter.setNewData(arrays);
                    }
                }
                break;
            case CheckIdentityContract.GET_ROOM_LIST:
                RoomsBean roomsBean = (RoomsBean) o;
                if (roomsBean != null) {
                    List<RoomsBean.DataBean> arrays = roomsBean.getData();
                    if (arrays != null) {
                        roomAdapter.setNewData(arrays);
                    }
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onError(String tag) {

    }

    private void initView() {
        mSelectRoomRv = (RecyclerView) findViewById(R.id.select_room_rv);
        cellAdapter = new CellAdapter(R.layout.simple_text_item);
        cellAdapter.setEmptyView(getAdapterEmptyView("暂无数据"));
        initRecyclerview(mSelectRoomRv, cellAdapter, LinearLayoutManager.VERTICAL, false);
        addDivider(true, mSelectRoomRv, false, false);
        mSelectRoomDrawerlayout = (DrawerLayout) findViewById(R.id.select_room_drawerlayout);
        mSelectRoomDrawerlayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        mCellNameTv = (TextView) findViewById(R.id.cell_name_tv);
        mRoomListRv = (RecyclerView) findViewById(R.id.room_list_rv);
        cellAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                mSelectRoomDrawerlayout.openDrawer(Gravity.RIGHT);
                CellsBean.DataBean dataBean = (CellsBean.DataBean) adapter.getData().get(position);
                mCellNameTv.setText(dataBean.getCellFullName());
                showProgressDialog();
                getPresenter().getRoomList(dataBean.getId(), CheckIdentityContract.GET_ROOM_LIST);
            }
        });
        mSelectRoomDrawerlayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View view, float v) {

            }

            @Override
            public void onDrawerOpened(@NonNull View view) {
                mSelectRoomDrawerlayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
            }

            @Override
            public void onDrawerClosed(@NonNull View view) {
                mSelectRoomDrawerlayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            }

            @Override
            public void onDrawerStateChanged(int i) {

            }
        });
        roomAdapter = new RoomAdapter(R.layout.simple_text_item);
        initRecyclerview(mRoomListRv, roomAdapter, LinearLayoutManager.VERTICAL, false);
        addDivider(true, mRoomListRv, false, true);
        roomAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                mSelectRoomDrawerlayout.closeDrawers();
                RoomsBean.DataBean dataBean = (RoomsBean.DataBean) adapter.getData().get(position);
                IntentUtil.getInstance().setIntentResultWithParcelableData(SelectRoomActivity.this,dataBean);
            }
        });
        mCellSl = (SwipeRefreshLayout) findViewById(R.id.cell_sl);
        mCellSl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                    int id = dataBean!=null?dataBean.getId():roomDOBean.getVillageId();
                    showProgressDialog();
                    getPresenter().getCellList(id, CheckIdentityContract.GET_CELL_LIST);
            }
        });
    }
}
