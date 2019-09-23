package com.ghs.ghshome.models.main.houseManager;

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
import com.ghs.ghshome.bean.RoomListBean;
import com.ghs.ghshome.bean.UserAndRoomBean;
import com.ghs.ghshome.models.login.LoginActivity;
import com.ghs.ghshome.models.checkIdentity.SelectVillageActivity;
import com.ghs.ghshome.models.main.MainContact;
import com.ghs.ghshome.models.main.MainPresent;
import com.ghs.ghshome.tools.AlterDialogManager;
import com.ghs.ghshome.tools.HawkProperty;
import com.ghs.ghshome.tools.PubUtil;
import com.ghs.ghshome.tools.StrUtils;
import com.orhanobut.hawk.Hawk;

import java.util.List;

/**
 * created by tobato
 * created date 2019/9/12 17:03.
 * application   房屋管理
 */
public class HouseManagerActivity extends BaseActivity<MainContact.IMainView, MainPresent> implements MainContact.IMainView, View.OnClickListener {

    private RecyclerView mHouseManagerRv;
    private SwipeRefreshLayout mHouseManagerSl;
    /**
     * 添加新房屋
     */
    private TextView mAddHouseTv;
    private HouseAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void setLayout() {
        setContentView(R.layout.activity_house_manager);
        initView();
    }

    @Override
    public MainPresent creatPresenter() {
        return new MainPresent();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        getDate();
    }

    @Override
    public void getDate() {
//请求网络 获取小区列表
        getPresenter().getUserRoomList(mUserInfoUtil.getUserId());
    }

    @Override
    public void startLoading(String tag) {

    }

    @Override
    public void stopLoading(String tag) {

    }

    @Override
    public void updateView(Object o, String tag) {
        mHouseManagerSl.setRefreshing(false);
        switch (tag) {
            case MainContact.GET_ROOMS:
                RoomListBean roomListBean = (RoomListBean) o;
                List<UserAndRoomBean.DataBean.GhsUserRoomDOBean> dataBeans = roomListBean.getData();
                if (dataBeans != null) {
                    if (dataBeans.size() != 0) {
                        if (!mUserInfoUtil.isSelectedCurrentVillage()) {
                            mUserInfoUtil.saveCurrentVillageBean(dataBeans.get(0));
                        }
                    }
                    adapter.setNewData(dataBeans);
                }
                break;
            case MainContact.QUIT_ROOM:
                int userRoomId = (int) o;
                if (mUserInfoUtil.getCurrentVillageBean().getId() == userRoomId) {
                    Hawk.delete(mUserInfoUtil.getCurrentVillageKey());
                }
                getDate();


                break;
            default:
                break;
        }

    }

    @Override
    public void onError(String tag) {
        mHouseManagerSl.setRefreshing(false);
        if (StrUtils.isStringValueOk(tag)) {
            if (tag.contains("未检测到小区权限")) {
                if (Hawk.contains(mUserInfoUtil.getCurrentVillageKey())) {
                    Hawk.delete(mUserInfoUtil.getCurrentVillageKey());
                }
                //删除的是当前已选的房间 跳转到登录界面
                //当需要删除登录状态的时候 必须后删除
                Hawk.delete(HawkProperty.LOGIN_BEAN);
                startActivity(new Intent(this, LoginActivity.class));
            } else {
                showToast(tag);
            }
        }

    }

    private void initView() {
        mHouseManagerRv = (RecyclerView) findViewById(R.id.house_manager_rv);
        adapter = new HouseAdapter(R.layout.house_manager_item);
        initRecyclerview(mHouseManagerRv, adapter, LinearLayoutManager.VERTICAL, false);
        mHouseManagerSl = (SwipeRefreshLayout) findViewById(R.id.house_manager_sl);
        mHouseManagerSl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getDate();
            }
        });
        mAddHouseTv = (TextView) findViewById(R.id.add_house_tv);
        mAddHouseTv.setOnClickListener(this);
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                UserAndRoomBean.DataBean.GhsUserRoomDOBean bean = (UserAndRoomBean.DataBean.GhsUserRoomDOBean) adapter.getData().get(position);
                TextView textView = (TextView) view;
                String btContent = textView.getText().toString().trim();
                switch (btContent) {
                    case "退出房间":
                        AlterDialogManager.getInstance().showMaterialAlterDialogWithTitle(HouseManagerActivity.this, R.string.notice, R.string.quit_house, R.string.cancel, R.string.confirm, new AlterDialogManager.OnAlterDialogCallBack() {
                            @Override
                            public void leftBtClicked() {

                            }

                            @Override
                            public void rightBtClicked() {
                                getPresenter().quitHouse(bean.getId(), MainContact.QUIT_ROOM);
                            }
                        });
                        break;
                    case "删除":
                        AlterDialogManager.getInstance().showMaterialAlterDialogWithTitle(HouseManagerActivity.this, R.string.notice, R.string.delete_house, R.string.cancel, R.string.confirm, new AlterDialogManager.OnAlterDialogCallBack() {
                            @Override
                            public void leftBtClicked() {

                            }

                            @Override
                            public void rightBtClicked() {
                                getPresenter().quitHouse(bean.getId(), MainContact.QUIT_ROOM);
                            }
                        });
                        break;
                    default:
                        break;
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.add_house_tv:
                PubUtil.SUBMIT_CHECK_ENTRY = 1;
                startActivity(new Intent(this, SelectVillageActivity.class));
                break;
        }
    }
}
