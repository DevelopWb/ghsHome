package com.ghs.ghshome.models.checkIdentity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ghs.ghshome.R;
import com.ghs.ghshome.base.BaseActivity;
import com.ghs.ghshome.base.BaseBotoomSheetDialogFragment;
import com.ghs.ghshome.base.promission.CheckPermListener;
import com.ghs.ghshome.base.promission.EasyPermissions;
import com.ghs.ghshome.bean.VillageListBean;
import com.ghs.ghshome.custom.CustomActionBar;
import com.ghs.ghshome.models.checkIdentity.searchVillage.SearchActivity;
import com.ghs.ghshome.models.checkIdentity.selectCity.SelectCityActivity;
import com.ghs.ghshome.tools.IntentUtil;
import com.ghs.ghshome.tools.LocationManager;
import com.ghs.ghshome.tools.PubUtil;
import com.ghs.ghshome.tools.StrUtils;

import java.util.List;

/**
 * created by tobato
 * created date 2019/9/9 16:35.
 * application   选择社区
 */
public class SelectVillageActivity extends BaseActivity<CheckIdentityContract.ICheckIdentityView, CheckIdentityPresent> implements CheckIdentityContract.ICheckIdentityView, View.OnClickListener, BaseBotoomSheetDialogFragment.Listener {

    /**
     * 当前城市
     */
    private TextView mSelectVillageCurrentCityTv;
    /**
     * 切换城市
     */
    private TextView mSelectVillageSwitchCityTv;
    private RecyclerView mSelectVillageRv;
    private CustomActionBar mSelectVillageCab;
    private LocationManager locationManager;
    private SwipeRefreshLayout mSelectVillageSl;
    private SelectVillageAdapter selectVillageAdapter;
    private String mCityName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        locationManager = LocationManager.getInstance().setContext(this);
        locationManager.initLocation();
        locationManager.setOnLocateCallBack(new LocationManager.OnLocateCallBack() {
            @Override
            public void onLocationChanged(AMapLocation loc) {
                Log.d(TAG, "定位返回数据");
                if (loc != null) {
                    if (mSelectVillageSl != null) {
                        mSelectVillageSl.setRefreshing(false);
                    }
                    String cityName = loc.getCity();
                    if (StrUtils.isStringValueOk(cityName)) {
                        mCityName = cityName;
                        mSelectVillageCurrentCityTv.setText("当前城市  " + cityName);
                        showProgressDialog();
                        getPresenter().getVillageList(cityName, CheckIdentityContract.GET_VILLAGE_LIST);
                        locationManager.stopLocation();
                    } else {
                        mSelectVillageCurrentCityTv.setText("当前城市  无法获取城市信息");
                    }
                } else {
                    mSelectVillageCurrentCityTv.setText("当前城市  无法获取城市信息");
                }
            }
        });
        checkLocationPromission();
    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }

    /**
     * 检查定位权限
     */
    private void checkLocationPromission() {
        checkAppPermissions(new CheckPermListener() {
            @Override
            public void agreeAllPermission() {
                locationManager.stopLocation();
                locationManager.initLocation();
            }

            @Override
            public void selectedAllPermission() {

            }
        }, R.string.perm_location, PubUtil.promissions[0]);
    }

    @Override
    public void onRefuseGivePromission() {
        super.onRefuseGivePromission();

    }

    @Override
    protected void setLayout() {
        setContentView(R.layout.activity_select_village);
        initView();
    }
    @Override
    public CheckIdentityPresent creatPresenter() {
        return new CheckIdentityPresent();
    }
    @Override
    public void getDate() {


    }

    private void initView() {
        mSelectVillageCab = (CustomActionBar) findViewById(R.id.select_village_cab);
        //搜索
        mSelectVillageCab.getActionBarRightIv().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SelectVillageActivity.this, SearchActivity.class));
//                BaseBotoomSheetDialogFragment.newInstance(5).show(getSupportFragmentManager(),"dialog");
            }
        });
        mSelectVillageCurrentCityTv = (TextView) findViewById(R.id.select_village_current_city_tv);
        mSelectVillageSwitchCityTv = (TextView) findViewById(R.id.select_village_switch_city_tv);
        mSelectVillageSwitchCityTv.setOnClickListener(this);
        mSelectVillageRv = (RecyclerView) findViewById(R.id.select_village_rv);
        selectVillageAdapter = new SelectVillageAdapter(R.layout.simple_text_item);
        initRecyclerview(mSelectVillageRv, selectVillageAdapter, LinearLayoutManager.VERTICAL, false);
        addDivider(true, mSelectVillageRv, false, true);
        selectVillageAdapter.setEmptyView(getAdapterEmptyView("暂无数据"));
        selectVillageAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                VillageListBean.DataBean dataBean = (VillageListBean.DataBean) adapter.getData().get(position);
                IntentUtil.getInstance().startActivityWithParcelableData(dataBean,SelectVillageActivity.this,CheckIdentityActivity.class);
            }
        });
        mSelectVillageSl = (SwipeRefreshLayout) findViewById(R.id.select_village_sl);
        mSelectVillageSl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                showProgressDialog();
                getPresenter().getVillageList(mCityName, CheckIdentityContract.GET_VILLAGE_LIST);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.select_village_switch_city_tv:
                //切换城市
                IntentUtil.getInstance().startActivityWithoutData(this, SelectCityActivity.class);
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
        mSelectVillageSl.setRefreshing(false);
        VillageListBean listBean = (VillageListBean) o;
        if (listBean != null) {
            List<VillageListBean.DataBean> villages = listBean.getData();
            if (villages != null) {
                selectVillageAdapter.setNewData(villages);
            }
        }
    }

    @Override
    public void onError(String tag) {
        mSelectVillageSl.setRefreshing(false);
        showToast(tag);
    }

    @Override
    public void onItemClicked(int position) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == EasyPermissions.SETTINGS_REQ_CODE) {
            //配置权限，设置返回
            checkLocationPromission();
        } else if (resultCode == IntentUtil.getInstance().RESULT_CODE) {
            if (data != null) {
                String cityName = data.getStringExtra(IntentUtil.getInstance().INTENT_KEY);
                mCityName = cityName;
                mSelectVillageCurrentCityTv.setText("当前城市  " + cityName);
                showProgressDialog();
                getPresenter().getVillageList(cityName, CheckIdentityContract.GET_VILLAGE_LIST);
            }
        }
    }

    @Override
    protected void onDestroy() {
        locationManager.stopLocation();
        super.onDestroy();
    }
}
