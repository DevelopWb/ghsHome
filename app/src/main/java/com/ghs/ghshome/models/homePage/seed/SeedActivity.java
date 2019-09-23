package com.ghs.ghshome.models.homePage.seed;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ghs.ghshome.R;
import com.ghs.ghshome.base.BaseActivity;
import com.ghs.ghshome.bean.LifeServiceBean;
import com.ghs.ghshome.bean.SeedTaskBean;
import com.ghs.ghshome.models.homePage.HomePageContract;
import com.ghs.ghshome.models.homePage.HomePagePresent;
import com.ghs.ghshome.models.homePage.carPark.bindcarnum.BindCarNumActivity;
import com.ghs.ghshome.models.homePage.houseBill.houseBillUnpay.LifeBillUnPayActivity;
import com.ghs.ghshome.models.homePage.keymanager.addkey.AddkeyActivity;
import com.ghs.ghshome.models.mine.edituserinfo.EditUserInfoActivity;
import com.ghs.ghshome.models.propertyHall.openDoorByMobile.MobileOpenActivity;
import com.ghs.ghshome.tools.ActivityResultManager;
import com.ghs.ghshome.tools.PubUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * created by tobato
 * created date 2019/7/24 15:50.
 * application   光合种子
 */
public class SeedActivity extends BaseActivity<HomePageContract.IHomePageView, HomePagePresent> implements HomePageContract.IHomePageView, View.OnClickListener {

    private ImageView mSeedBackIv;
    private ImageView mSeedRecordIv;
    /**
     * 46541233
     */
    private TextView mSeedAmountTv;
    private RecyclerView mTakeSeedListRv;
    private SeedTaskAdapter adapter;
    private List<String> opendMenus = new ArrayList<>();
    private PopupWindow popupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        opendMenus.clear();
    }

    @Override
    protected void setLayout() {
        setContentView(R.layout.activity_seed);
        initView();

    }

    @Override
    public HomePagePresent creatPresenter() {
        return new HomePagePresent();
    }

    @Override
    public void getDate() {
        requestData();
    }

    @Override
    public void startLoading(String tag) {

    }

    @Override
    public void stopLoading(String tag) {

    }

    @Override
    protected void signedSuccessfuly() {
        super.signedSuccessfuly();
        requestData();
    }

    @Override
    public void updateView(Object o, String tag) {
        switch (tag) {
            case HomePageContract.SEED_TASK_LIST:
                SeedTaskBean seedTaskBean = (SeedTaskBean) o;
                if (seedTaskBean != null) {
                    List<SeedTaskBean.DataBean> arrays = seedTaskBean.getData();
                    if (arrays != null) {
                        adapter.setNewData(arrays);
                    }
                }
                break;
            case HomePageContract.SEED_AMOUNT:
                String seedAmount = (String) o;
                mSeedAmountTv.setText(seedAmount);
                break;
            case HomePageContract.GET_ALL_SERVICES:
                LifeServiceBean lifeServiceBean = (LifeServiceBean) o;
                if (lifeServiceBean != null) {
                    List<LifeServiceBean.DataBean> services = lifeServiceBean.getData();
                    if (services != null && services.size() > 0) {
                        for (LifeServiceBean.DataBean service : services) {
                            int open = service.getOpen();
                            String name = service.getName();
                            //开通：1:开通 2:未开通
                            if (1 == open) {
                                opendMenus.add(name);
                            }
                        }
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
        mSeedBackIv = (ImageView) findViewById(R.id.seed_back_iv);
        mSeedBackIv.setOnClickListener(this);
        mSeedRecordIv = (ImageView) findViewById(R.id.seed_record_iv);
        mSeedRecordIv.setOnClickListener(this);
        mSeedAmountTv = (TextView) findViewById(R.id.seed_amount_tv);
        mTakeSeedListRv = (RecyclerView) findViewById(R.id.take_seed_list_rv);
        adapter = new SeedTaskAdapter(R.layout.seed_task_item);
        initRecyclerview(mTakeSeedListRv, adapter, LinearLayoutManager.VERTICAL, false);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                SeedTaskBean.DataBean dataBean = (SeedTaskBean.DataBean) adapter.getData().get(position);
                String taskType = dataBean.getTaskType();
                //状态 1:已完成 2：未完成
                int state = dataBean.getState();
                if (1 == state) {
                    return;
                }
                Intent intent = new Intent();
                switch (taskType) {
                    case SeedTaskAdapter.SEED_TASK_SIGN:
                        execSeedTask(SeedTaskAdapter.SEED_TASK_SIGN, -1);
                        break;
                    case SeedTaskAdapter.SEED_TASK_BINDCAR:
                        if (checkLoginAndSelectRoomToWarn()) {
                            return;
                        }
                        if (!opendMenus.contains("车辆车位")) {
                            showToast("该小区暂未开通此服务");
                        }
                        //绑定车辆
                        intent.setClass(SeedActivity.this, BindCarNumActivity.class);
                        startActivityForResult(intent, ActivityResultManager.SEED_TASK_LIST_CLICK);
                        break;
                    case SeedTaskAdapter.SEED_TASK_LOGIN:

                        break;
                    case SeedTaskAdapter.SEED_TASK_GRANT_HOUSE:
                        if (checkLoginAndSelectRoomToWarn()) {
                            return;
                        }
                        if (!opendMenus.contains("授权管理")) {
                            showToast("该小区暂未开通此服务");
                        }
                        //钥匙授权
                        intent.setClass(SeedActivity.this, AddkeyActivity.class);
                        startActivityForResult(intent, ActivityResultManager.SEED_TASK_LIST_CLICK);
                        break;
                    case SeedTaskAdapter.SEED_TASK_OPEN_BY_MOBILE:
                        if (checkLoginAndSelectRoomToWarn()) {
                            return;
                        }
                        if (!opendMenus.contains("手机开门")) {
                            showToast("该小区暂未开通此服务");
                        }
//手机开门
                        intent.setClass(SeedActivity.this, MobileOpenActivity.class);
                        startActivityForResult(intent, ActivityResultManager.SEED_TASK_LIST_CLICK);
                        break;
                    case SeedTaskAdapter.SEED_TASK_WATER_FEE:
                        if (checkLoginAndSelectRoomToWarn()) {
                            return;
                        }
                        if (!opendMenus.contains("房屋账单")) {
                            showToast("该小区暂未开通此服务");
                        }
                        PubUtil.MINE_BILL_TAG = 1;
                        intent.setClass(SeedActivity.this, LifeBillUnPayActivity.class);
                        startActivityForResult(intent, ActivityResultManager.SEED_TASK_LIST_CLICK);

                        break;
                    case SeedTaskAdapter.SEED_TASK_AMMETER_FEE:

                        if (checkLoginAndSelectRoomToWarn()) {
                            return;
                        }
                        if (!opendMenus.contains("房屋账单")) {
                            showToast("该小区暂未开通此服务");
                        }
                        PubUtil.MINE_BILL_TAG = 2;
                        intent.setClass(SeedActivity.this, LifeBillUnPayActivity.class);
                        startActivityForResult(intent, ActivityResultManager.SEED_TASK_LIST_CLICK);
                        break;
                    case SeedTaskAdapter.SEED_TASK_PROPERTY_FEE:
                        if (checkLoginAndSelectRoomToWarn()) {
                            return;
                        }
                        if (!opendMenus.contains("房屋账单")) {
                            showToast("该小区暂未开通此服务");
                        }
                        PubUtil.MINE_BILL_TAG = 3;
                        intent.setClass(SeedActivity.this, LifeBillUnPayActivity.class);
                        startActivityForResult(intent, ActivityResultManager.SEED_TASK_LIST_CLICK);
                        break;
                    case SeedTaskAdapter.SEED_TASK_UPLOAD_HEAD_PIC:
                        //上传头像
                        intent.setClass(SeedActivity.this, EditUserInfoActivity.class);
                        startActivityForResult(intent, ActivityResultManager.SEED_TASK_LIST_CLICK);
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
            case R.id.seed_back_iv:
                onBackPressed();
                break;
            case R.id.seed_record_iv:
                popupWindow = selectSeedRecordPop();
                popupWindow.showAsDropDown(mSeedRecordIv,  dip2px(this, -30),  dip2px(this, -20));

                break;
            case R.id.consume_seed_record_tv:
                if (popupWindow != null) {
                    if (popupWindow.isShowing()) {
                        popupWindow.dismiss();
                    }
                }
                //种子消费记录
                PubUtil.SEED_RECORD_TYPE = 2;
                startActivity(new Intent(this, SeedRecordActivity.class));

                break;
            case R.id.get_seed_record_tv:
                if (popupWindow != null) {
                    if (popupWindow.isShowing()) {
                        popupWindow.dismiss();
                    }
                }
                PubUtil.SEED_RECORD_TYPE = 1;
                startActivity(new Intent(this, SeedRecordActivity.class));

                break;
        }
    }
    /**
     * 选择种子记录
     *
     * @return
     */
    private PopupWindow selectSeedRecordPop() {
        View view = LayoutInflater.from(this).inflate(R.layout.select_seed_record, null);
        final PopupWindow pop = new PopupWindow(view, dip2px(this, 80), dip2px(this, 80), false);
       view.findViewById(R.id.get_seed_record_tv).setOnClickListener(this);
       view.findViewById(R.id.consume_seed_record_tv).setOnClickListener(this);
        pop.setOutsideTouchable(true);
        setBackgroundAlpha(0.5f);
        pop.setFocusable(true);
        pop.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                setBackgroundAlpha(1.0f);
            }
        });
        return pop;
    }
    @Override
    public void onBackPressed() {
        setResult(ActivityResultManager.QUIT_SEED_ACTIVITY);
        super.onBackPressed();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (ActivityResultManager.SEED_TASK_LIST_CLICK == requestCode) {
            requestData();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 请求数据
     */
    private void requestData() {
        getPresenter().getAllServices(mUserInfoUtil.getVillageId(), HomePageContract.GET_ALL_SERVICES);
        getPresenter().getSeedTaskList(mUserInfoUtil.getUserId(), HomePageContract.SEED_TASK_LIST);
        getPresenter().getSeedAmount(mUserInfoUtil.getUserId(), HomePageContract.SEED_AMOUNT);
    }
}
