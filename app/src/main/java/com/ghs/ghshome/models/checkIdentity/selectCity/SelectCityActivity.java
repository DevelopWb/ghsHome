package com.ghs.ghshome.models.checkIdentity.selectCity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ghs.ghshome.R;
import com.ghs.ghshome.base.BaseActivity;
import com.ghs.ghshome.bean.CitiesBean;
import com.ghs.ghshome.bean.MultiItemBean;
import com.ghs.ghshome.models.checkIdentity.CheckIdentityContract;
import com.ghs.ghshome.models.checkIdentity.CheckIdentityPresent;
import com.ghs.ghshome.tools.IntentUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * created by tobato
 * created date 2019/9/10 9:35.
 * application   切换城市
 */
public class SelectCityActivity extends BaseActivity<CheckIdentityContract.ICheckIdentityView, CheckIdentityPresent> implements CheckIdentityContract.ICheckIdentityView,SelectCityAdapter.OnCityClickCallBack {


    private RecyclerView mSelectCityRv;
    private SwipeRefreshLayout mSelectCitySl;
    private SelectCityAdapter selectCityAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void setLayout() {
        setContentView(R.layout.activity_select_city);
        initView();
    }

    @Override
    public CheckIdentityPresent creatPresenter() {
        return new CheckIdentityPresent();
    }

    @Override
    public void getDate() {
        showProgressDialog();
        getPresenter().getCityList(CheckIdentityContract.GET_CITY_LIST);
    }


    @Override
    public void startLoading(String tag) {

    }

    @Override
    public void stopLoading(String tag) {

    }

    @Override
    public void updateView(Object o, String tag) {
        mSelectCitySl.setRefreshing(false);
        CitiesBean citiesBean = (CitiesBean) o;
        if (citiesBean != null) {
            List<CitiesBean.DataBean> dataBeanList = citiesBean.getData();
            if (dataBeanList != null) {
                List<MultiItemBean> multiItemBeanList = new ArrayList<>();
                for (CitiesBean.DataBean dataBean : dataBeanList) {
                    multiItemBeanList.add(new MultiItemBean(MultiItemBean.SELECT_CITY_DIVIDER, dataBean.getFirstLetter()));
                    multiItemBeanList.add(new MultiItemBean(MultiItemBean.SELECT_CITY_CONTENT, dataBean.getCityList()));
                }
                selectCityAdapter.setNewData(multiItemBeanList);
            }
        }
    }

    @Override
    public void onError(String tag) {
        mSelectCitySl.setRefreshing(false);
    }

    private void initView() {
        mSelectCityRv = (RecyclerView) findViewById(R.id.select_city_rv);
        mSelectCitySl = (SwipeRefreshLayout) findViewById(R.id.select_city_sl);
        mSelectCitySl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getDate();
            }
        });
        selectCityAdapter = new SelectCityAdapter(null, this);
        initRecyclerview(mSelectCityRv, selectCityAdapter, LinearLayoutManager.VERTICAL, false);
    }

    @Override
    public void cityClicked(String cityName) {
        IntentUtil.getInstance().setIntentResult(this,cityName);
    }
}
