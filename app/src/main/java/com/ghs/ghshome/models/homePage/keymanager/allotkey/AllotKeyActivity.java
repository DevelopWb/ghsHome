package com.ghs.ghshome.models.homePage.keymanager.allotkey;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ghs.ghshome.R;
import com.ghs.ghshome.base.BaseActivity;
import com.ghs.ghshome.base.network.RequestStatus;
import com.ghs.ghshome.bean.AllotKeyBean;
import com.ghs.ghshome.models.homePage.keymanager.addkey.AddkeyActivity;
import com.ghs.ghshome.models.propertyHall.openDoorByMobile.remoteopen.RemoteOpenActivity;
import com.ghs.ghshome.tools.ActivityResultManager;
import com.ghs.ghshome.tools.HawkProperty;
import com.ghs.ghshome.tools.PubUtil;
import com.orhanobut.hawk.Hawk;

import java.util.List;

/**
 * created by wang
 * created date 2018/6/29 13:40.
 * application  名额分配
 */
public class AllotKeyActivity extends BaseActivity<AllotKeyContract.AllotKeyView<List<AllotKeyBean.DataBean>>, AllotKeyPresent> implements AllotKeyContract.AllotKeyView<List<AllotKeyBean.DataBean>>, View.OnClickListener {

    private RecyclerView mAllotKeyRv;
    private TextView mAddKeyFab;
    private SwipeRefreshLayout mAllotKeySrl;
    private AllotKeyAdapter adapter;
    private int resultCode;
    private int key_amount_to_add = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    /**
     * 初始化adapter
     */
    private void initAdapter() {
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mAllotKeyRv.setLayoutManager(manager);
        adapter = new AllotKeyAdapter();
        mAllotKeyRv.setAdapter(adapter);

        adapter.setOnItemClickListener(new AllotKeyAdapter.AllotKeyItemClickListener() {
            @Override
            public void releaseBound(final AllotKeyBean.DataBean bean, final LinearLayout linearLayout) {
                //这个是自定义对话框
//                AlertDialog alertDialog =  new AlertDialog.Builder(AllotKeyActivity.this)
//                       .setView(R.layout.allot_key_release_bound)
//                        .create();
//                alertDialog.show();
                AlertDialog alertDialog = new AlertDialog.Builder(AllotKeyActivity.this)
                        .setMessage(R.string.allot_key_release_bound)
                        .setTitle("提示")
                        .setNegativeButton("是", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                linearLayout.setVisibility(View.GONE);
                                getPresenter().releaseKeyAmount(mUserInfoUtil.getRoomUserId(), bean.getId(), bean.getRoleType(), bean.getRoomId());
                            }
                        }).setPositiveButton("否", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).create();
                alertDialog.show();
            }

            @Override
            public void modifyKeyAmount(final AllotKeyBean.DataBean bean, final LinearLayout linearLayout) {
                key_amount_to_add = 0;
                //这个是自定义对话框
                View view = LayoutInflater.from(AllotKeyActivity.this).inflate(R.layout.allot_key_modify, null);
                final AlertDialog alertDialog = new AlertDialog.Builder(AllotKeyActivity.this)
                        .setView(view)
                        .create();
                alertDialog.show();

                //设置弹出框的长宽
                alertDialog.getWindow().setLayout(PubUtil.dip2px(AllotKeyActivity.this, 300), PubUtil.dip2px(AllotKeyActivity.this, 180));
                TextView remove_key = view.findViewById(R.id.allot_key_remove_tv);
                TextView add_key = view.findViewById(R.id.allot_key_add_tv);
                TextView confirm_modify = view.findViewById(R.id.allot_key_confirm_tv);
                final TextView key_amount = view.findViewById(R.id.allot_key_modify_amount_tv);
                key_amount.setText(String.valueOf(bean.getLeftKeyNum()));
                remove_key.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int key_amount_int = Integer.parseInt(key_amount.getText().toString().trim());
                        if (key_amount_int > 1) {
                            key_amount_int--;
                        }
                        key_amount_to_add--;
                        key_amount.setText(String.valueOf(key_amount_int));
                    }
                });
                add_key.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        int key_amount_int = Integer.parseInt(key_amount.getText().toString().trim());
                        key_amount_int++;
                        key_amount_to_add++;
                        if (key_amount_to_add > PubUtil.LEFT_KEY_NUM - 1) {
                            showToast("超出总名额数量");
                            return;
                        }
                        key_amount.setText(String.valueOf(key_amount_int));
                    }
                });
                confirm_modify.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        linearLayout.setVisibility(View.GONE);
                        //请求修改名额数量的接口
                        alertDialog.dismiss();
                        bean.getId();//被修改的用户房间关系表id
                        getPresenter().modifyKeyAmount(mUserInfoUtil.getRoomUserId(), bean.getId(), Integer.parseInt(key_amount.getText().toString().trim()));
                    }
                });
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

    }


    @Override
    public void getDate() {
        getPresenter().getUserInfos(mUserInfoUtil.getUserId(), mUserInfoUtil.getRoleType(), mUserInfoUtil.getRoomId(), RequestStatus.UPDATE);
    }

    @Override
    public void initLayoutView() {
        initView();
        initAdapter();
        initActionBar("房屋授权列表", "");

    }

    @Override
    public void setLayout() {
        setContentView(R.layout.activity_allot_key);

    }

    @Override
    public void actionBarRightTvOnClick() {
        adapter.initLinearLayout(false);
        startActivity(new Intent(AllotKeyActivity.this, RemoteOpenActivity.class));

    }

    @Override
    public AllotKeyPresent creatPresenter() {
        return new AllotKeyPresent();
    }


    private void initView() {
        mAllotKeyRv = (RecyclerView) findViewById(R.id.allot_key_rv);
        mAddKeyFab = (TextView) findViewById(R.id.add_key_fab);
        mAddKeyFab.setOnClickListener(this);
        mAllotKeySrl = (SwipeRefreshLayout) findViewById(R.id.allot_key_srl);
        mAllotKeySrl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getPresenter().getUserInfos(mUserInfoUtil.getUserId(), mUserInfoUtil.getRoleType(), mUserInfoUtil.getRoomId(), RequestStatus.REFRESH);


            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == ActivityResultManager.ADD_KEY_RESULT) {
            getPresenter().getUserInfos(mUserInfoUtil.getUserId(), mUserInfoUtil.getRoleType(), mUserInfoUtil.getRoomId(), RequestStatus.UPDATE);

        }
    }

    /**
     * 获取当前用户名额分配的实体
     */
    private AllotKeyBean.DataBean getCurrentUserAllotKeyBean() {
        AllotKeyBean.DataBean dataBeanReturn = null;
        if (Hawk.contains(HawkProperty.ALLOT_KEY_BEAN)) {
            AllotKeyBean bean = Hawk.get(HawkProperty.ALLOT_KEY_BEAN);
            if (bean.getData().size() > 0) {
                List<AllotKeyBean.DataBean> dataBeans = bean.getData();
                for (AllotKeyBean.DataBean dataBean : dataBeans) {
                    if (dataBean.getGhsUserId() == mUserInfoUtil.getUserId()) {
                        dataBeanReturn = dataBean;
                        break;
                    }
                }
            }
        }
        return dataBeanReturn;
    }

    @Override
    public void startLoading(String tag) {
        if (RequestStatus.REFRESH.equals(tag)) {
            mAllotKeySrl.setRefreshing(true);
        } else {
            showProgressDialog();
        }

    }

    @Override
    public void stopLoading(String tag) {
        if (RequestStatus.REFRESH.equals(tag)) {
            mAllotKeySrl.setRefreshing(false);
        }
    }

    @Override
    public void updateView(Object o, String tag) {
        switch (tag) {
            case RequestStatus.REFRESH:
                showToast("刷新成功");
                readyForAdapter((AllotKeyBean) o);

                break;
            case RequestStatus.UPDATE:
                readyForAdapter((AllotKeyBean) o);


                break;
            case AllotKeyContract.RELEASE:
                showToast("解除授权成功");
                getPresenter().getUserInfos(mUserInfoUtil.getUserId(), mUserInfoUtil.getRoleType(), mUserInfoUtil.getRoomId(), RequestStatus.UPDATE);

                break;
            case AllotKeyContract.MODIFY:
                showToast("更改成功");
                key_amount_to_add = 0;
                getPresenter().getUserInfos(mUserInfoUtil.getUserId(), mUserInfoUtil.getRoleType(), mUserInfoUtil.getRoomId(), RequestStatus.UPDATE);

                break;
            default:
                break;
        }
    }

    private void readyForAdapter(AllotKeyBean o) {
        AllotKeyBean bean = o;
        if (bean != null) {
            if (bean.getData() != null && bean.getData().size() > 0) {
                initAdapterData(bean);
                for (AllotKeyBean.DataBean dataBean : bean.getData()) {
                    if (dataBean.getGhsUserId() == mUserInfoUtil.getUserId()) {
                        PubUtil.LEFT_KEY_NUM = dataBean.getLeftKeyNum();
                    }
                    if (3 == dataBean.getRoleType()) {//已经有添加的租户 了
                        PubUtil.ADDED_GUESTER = true;
                    } else {
                        PubUtil.ADDED_GUESTER = false;
                    }
                }
            }

        }
    }

    /**
     * 初始化adapter数据
     *
     * @param o
     */
    private void initAdapterData(AllotKeyBean o) {
        AllotKeyBean bean_update = o;
        if (bean_update != null) {
            Hawk.put(HawkProperty.ALLOT_KEY_BEAN, bean_update);
            List<AllotKeyBean.DataBean> dataBeans = bean_update.getData();
            if (dataBeans != null && dataBeans.size() > 0) {
                if (mUserInfoUtil.getRoleType() == 1) {//业主
                    if (dataBeans.size() < 2) {//提示空值
//                        showNoDataActivityLayout(true, "您没有分配名额，快去分配吧");
                    } else {
//                        showNoDataActivityLayout(false, "您没有分配名额，快去分配吧");

                    }
                } else if (mUserInfoUtil.getRoleType() == 3) {
                    if (dataBeans.size() < 3) {//提示空值
//                        showNoDataActivityLayout(true, "您没有分配名额，快去分配吧");
                    } else {
//                        showNoDataActivityLayout(false, "您没有分配名额，快去分配吧");

                    }
                }
            }

        }
        adapter.setDate(bean_update);
    }

    @Override
    public void onError(String tag) {
        showToast(tag);
    }

//    @Override
//    public void updateView(List<AllotKeyBean.DataBean> beans) {
//        if (beans!= null) {
//            adapter.setDate(beans);
//        }else {
//            showToast("更改成功");
//        }
//        }


    @Override
    public void onBackPressed() {
        setResult(ActivityResultManager.ALLOT_KEY);
        super.onBackPressed();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.add_key_fab:
                adapter.initLinearLayout(false);
                AllotKeyBean.DataBean dataBean = getCurrentUserAllotKeyBean();
                if (dataBean != null) {
                    int roleType = dataBean.getRoleType();
                    int leftKey = dataBean.getLeftKeyNum();
                    //角色 1业主 2业主家人 3租客 4租客家人
                    if (roleType == 2 || roleType == 4) {//业主家人或者租户家人，没有权限分配名额
                        showToast("您没有权限分配名额");
                        return;
                    } else {
                        if (leftKey < 2) {
                            showToast("您没有多余的名额用来分配");
                            return;

                        }
                    }
                }
                startActivityForResult(new Intent(this, AddkeyActivity.class), ActivityResultManager.ADD_KEY_RESULT);
                break;
        }
    }
}
