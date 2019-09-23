package com.ghs.ghshome.models.homePage.carPark.myCar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ghs.ghshome.R;
import com.ghs.ghshome.base.BaseActivity;
import com.ghs.ghshome.bean.BoundCarsBean;
import com.ghs.ghshome.bean.OutParkBean;
import com.ghs.ghshome.bean.ParkSpaceBean;
import com.ghs.ghshome.models.homePage.carPark.CarParkContract;
import com.ghs.ghshome.models.homePage.carPark.CarParkPresent;
import com.ghs.ghshome.models.homePage.carPark.ParkRecordActivity;
import com.ghs.ghshome.models.homePage.carPark.bindcarnum.BindCarNumActivity;
import com.ghs.ghshome.models.homePage.carPark.payfee.PayFeeActivity;
import com.ghs.ghshome.tools.ActivityResultManager;
import com.ghs.ghshome.tools.IntentUtil;
import com.ghs.ghshome.tools.PubUtil;
import com.ghs.ghshome.tools.UserInfoUtil;

import java.util.List;

/**
 * created by tobato
 * created date 2019/7/25 18:41.
 * application   车辆车位
 */
public class MineCarActivity extends BaseActivity<CarParkContract.ICarParkView, CarParkPresent> implements CarParkContract.ICarParkView, View.OnClickListener {

    private RecyclerView mCarParkMyCarsRv;
    private CarParkAdapter carParkAdapter;
    private RecyclerView mMineParkingSpaceRv;
    private ParkSpaceAdapter parkSpaceAdapter;
    /**
     * 我的车辆
     */
    private TextView mMineCarWarnTag;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setLayout() {
        setContentView(R.layout.activity_mine_car);

    }

    @Override
    public void initLayoutView() {
        initView();
    }

    @Override
    public CarParkPresent creatPresenter() {
        return new CarParkPresent();
    }

    @Override
    public void getDate() {
        showProgressDialog();
        getPresenter().getBoundCars(UserInfoUtil.getInstance().getVillageId(), UserInfoUtil.getInstance().getRoomId(), UserInfoUtil.getInstance().getUserId(), CarParkContract.GET_BOUND_CARS);
        getPresenter().getParkSpaceList(mUserInfoUtil.getVillageId(), mUserInfoUtil.getUserId(), CarParkContract.PARKING_SPACE_LIST);
    }

    private void initView() {

        mCarParkMyCarsRv = (RecyclerView) findViewById(R.id.my_cars_rv);
        carParkAdapter = new CarParkAdapter(R.layout.car_park_item);
        initRecyclerview(mCarParkMyCarsRv, carParkAdapter, LinearLayoutManager.VERTICAL, false,true);
        addDivider(true, mCarParkMyCarsRv, false, true);
        carParkAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                BoundCarsBean.DataBean bean = (BoundCarsBean.DataBean) adapter.getData().get(position);
                IntentUtil.getInstance().startActivityWithParcelableData(bean, MineCarActivity.this, ParkRecordActivity.class);
            }
        });
        mCustomCab.getActionBarRightIv().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //绑定车辆，
                PubUtil.ADD_CAR_NUM_ENTER = 0;
                startActivityForResult(new Intent(MineCarActivity.this, BindCarNumActivity.class), ActivityResultManager.CAR_PARK_ADD_CAR);

            }
        });
        mMineParkingSpaceRv = (RecyclerView) findViewById(R.id.mine_parking_space_rv);
        parkSpaceAdapter = new ParkSpaceAdapter(R.layout.park_space_item);
        parkSpaceAdapter.setEmptyView(getAdapterEmptyView("暂无车位信息"));
        initRecyclerview(mMineParkingSpaceRv, parkSpaceAdapter, LinearLayoutManager.VERTICAL, false,true);
        mMineCarWarnTag = (TextView) findViewById(R.id.mine_car_warn_tag);
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
            case CarParkContract.GET_BOUND_CARS:

                BoundCarsBean boundCarsBean = (BoundCarsBean) o;
                if (boundCarsBean != null) {
                    List<BoundCarsBean.DataBean> beans = boundCarsBean.getData();
                    if (beans != null && beans.size() > 0) {
                        mMineCarWarnTag.setVisibility(View.VISIBLE);
                    } else {
                        mMineCarWarnTag.setVisibility(View.GONE);
                    }
                    carParkAdapter.setNewData(beans);
                }
                break;

            case CarParkContract.UNBIND_CAR:
                int position = (int) o;
                carParkAdapter.remove(position);
                mCustomCab.getActionBarRightIv().setVisibility(View.VISIBLE);
                showToast("解绑成功");


                break;

            case CarParkContract.OUT_OF_PARK:
                OutParkBean outParkBean = (OutParkBean) o;
                if (outParkBean != null) {
                    OutParkBean.DataBean dataBean = outParkBean.getData();
                    if (dataBean != null) {
                        Intent intent = new Intent(MineCarActivity.this, PayFeeActivity.class);
                        intent.putExtra(ActivityResultManager.OUT_PARK_BEAN, dataBean);
                        startActivityForResult(intent, ActivityResultManager.PARKING_PAYED);
                    } else {
                        showToast("该车辆未进场");
                    }
                }
                break;
            case CarParkContract.PARKING_SPACE_LIST:
                ParkSpaceBean parkSpaceBean = (ParkSpaceBean) o;
                if (parkSpaceBean != null) {
                    List<ParkSpaceBean.DataBean> arrays = parkSpaceBean.getData();
                    if (arrays != null) {
                        parkSpaceAdapter.setNewData(arrays);
                    }
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onError(String tag) {
        showToast(tag);
    }


    @Override
    public void onClick(View v) {
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ActivityResultManager.CAR_PARK_ADD_CAR || requestCode == IntentUtil.getInstance().REQUEST_CODE) {
            getPresenter().getBoundCars(UserInfoUtil.getInstance().getVillageId(), UserInfoUtil.getInstance().getRoomId(), UserInfoUtil.getInstance().getUserId(), CarParkContract.GET_BOUND_CARS);
        }
    }
}