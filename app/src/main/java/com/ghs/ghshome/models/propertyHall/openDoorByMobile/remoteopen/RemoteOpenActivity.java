package com.ghs.ghshome.models.propertyHall.openDoorByMobile.remoteopen;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ghs.ghshome.R;
import com.ghs.ghshome.base.BaseActivity;
import com.ghs.ghshome.base.network.RequestStatus;
import com.ghs.ghshome.bean.RemoteOpenLockDevBean;
import com.ghs.ghshome.models.homePage.seed.SeedTaskAdapter;
import com.ghs.ghshome.models.main.MainContact;
import com.ghs.ghshome.models.main.MainModel;
import com.ghs.ghshome.models.propertyHall.openDoorByMobile.remoteopen.adapter.RemoteOpenDoorAdapter;
import com.ghs.ghshome.base.network.NetWorkUtil;
import com.ghs.ghshome.tools.PubUtil;
import com.ghs.ghshome.tools.UserInfoUtil;

import java.util.Date;
import java.util.List;

public class RemoteOpenActivity extends BaseActivity<RemoteOpenContract.IRemoteOpenView, RemoteOpenPresent> implements RemoteOpenContract.IRemoteOpenView, RequestStatus {

    private RecyclerView mRemoteOpenRv;
    private RemoteOpenDoorAdapter remoteOpenDoorAdapter;
    private ImageView mHeaderLeftIv;
    private TextView mHeaderTitleTv;
    private ImageView mKeyLockExplain;
    private TextView blue_key_village_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setLayout() {
        setContentView(R.layout.activity_remote_open);

    }

    @Override
    public void initLayoutView() {
        initView();
    }

    @Override
    public RemoteOpenPresent creatPresenter() {
        return new RemoteOpenPresent();
    }

    @Override
    public void getDate() {
        showProgressDialog();
        getPresenter().getLockDeviceList(UserInfoUtil.getInstance().getVillageId(), UserInfoUtil.getInstance().getCellId(), RemoteOpenContract.GET_DEV_LIST);
    }

    @Override
    public void actionBarRightTvOnClick() {

    }

    private void initView() {
        mRemoteOpenRv = (RecyclerView) findViewById(R.id.network_rv);
        GridLayoutManager bigdoor_manager = new GridLayoutManager(this, 4, GridLayoutManager.VERTICAL, false);
        mRemoteOpenRv.setLayoutManager(bigdoor_manager);
        remoteOpenDoorAdapter = new RemoteOpenDoorAdapter(R.layout.network_item_layout, null);
        mRemoteOpenRv.setAdapter(remoteOpenDoorAdapter);

        remoteOpenDoorAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                openDoorEngin(adapter, position);
            }
        });

        mHeaderTitleTv = (TextView) findViewById(R.id.header_title_tv);
        mHeaderTitleTv.setText("远程开门");
        mKeyLockExplain = (ImageView) findViewById(R.id.key_lock_explain);
        mKeyLockExplain.setVisibility(View.GONE);
        mHeaderLeftIv = (ImageView) findViewById(R.id.header_left_iv);
        mHeaderLeftIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        blue_key_village_name = findViewById(R.id.blue_key_village_name);
        blue_key_village_name.setText(UserInfoUtil.getInstance().getVillageName());
    }

    /**
     * 开门
     *
     * @param adapter
     * @param position
     */
    private void openDoorEngin(BaseQuickAdapter adapter, int position) {
        showProgressDialog();
        RemoteOpenLockDevBean.DataBean dataBean = remoteOpenDoorAdapter.getData().get(position);
        if (dataBean != null) {
            if (NetWorkUtil.isNetworkAvailable()) {//有网络
                Log.d(TAG, "未搜索到设备，有网络，直接走网络开锁" + sdf.format(new Date()));
                new MainModel().openDoorByNet(dataBean.getId(), UserInfoUtil.getInstance().getRoomUserId(), this);

            } else {
                Log.d(TAG, "未搜索到设备，无网络，展示本地密码" + sdf.format(new Date()));

                showPwdDialog(false, "");
            }
        }

    }

    /**
     * 弹出密码框
     * isNet : true 网络请求返回的密码，false 本地密钥
     */
    private void showPwdDialog(boolean isNet, String pwd) {
        stopProgressDialog();
        View view = LayoutInflater.from(this).inflate(R.layout.open_door_pwd_layout, null);
        TextView textView = view.findViewById(R.id.open_door_pwd_content_tv);
        if (isNet) {
            textView.setText(pwd);
        } else {
            textView.setText(UserInfoUtil.getInstance().getLockPassword());
        }
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setView(view)
                .create();
        dialog.show();

        //设置弹出框的长宽
        dialog.getWindow().setLayout(PubUtil.ScreenWidth / 6 * 5, PubUtil.ScreenHeight / 2);
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
            case RemoteOpenContract.GET_DEV_LIST:
                //网络开门列表
                RemoteOpenLockDevBean openLockDevBean = (RemoteOpenLockDevBean) o;
                if (openLockDevBean != null) {
                    List<RemoteOpenLockDevBean.DataBean> data = openLockDevBean.getData();
                    remoteOpenDoorAdapter.setNewData(data);
                }

                break;
            default:
                break;
        }
    }

    @Override
    public void onError(String tag) {
        switch (tag) {
            case MainContact.OPEN_BY_NET:
                openDoorByNetFailed();
                break;
            case RemoteOpenContract.GET_PWD:
                showPwdDialog(false, "");
                break;
            default:
                break;
        }
    }

    /**
     * 网络开门失败
     */
    private void openDoorByNetFailed() {
        if (NetWorkUtil.isNetworkAvailable()) {
            Log.d(TAG, "未搜索到蓝牙设备，网络开门失败，请求网络密钥");
            new RemoteOpenModel().getPassword(UserInfoUtil.getInstance().getRoomUserId(), this, RemoteOpenContract.GET_PWD);

        } else {
            Log.d(TAG, "未搜索到蓝牙设备，网络开门失败，无网络，展示本地密钥");

            showPwdDialog(false, "");

        }

    }

    @Override
    public void onStart(String tag) {

    }

    @Override
    public void onSuccess(Object o, String tag) {
        switch (tag) {
            case MainContact.OPEN_BY_NET:

                if ("1000".equals(o)) {//网络开门成功
                    Log.d(TAG, "网络开门成功" + sdf.format(new Date()));
                    showToast("开门成功");
                    execSeedTask(SeedTaskAdapter.SEED_TASK_OPEN_BY_MOBILE,-1);

                } else {// 网络开门失败，蓝牙连接服务端

                    openDoorByNetFailed();

                }
                break;
            case RemoteOpenContract.GET_PWD:
                Log.d(TAG, "获取网络密钥成功" + sdf.format(new Date()));

                String pwd = (String) o;
                showPwdDialog(true, pwd);
                break;
            default:
                break;
        }
    }

}
