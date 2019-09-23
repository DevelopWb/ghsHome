package com.ghs.ghshome.models.homePage.carPark;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.ghs.ghshome.R;
import com.ghs.ghshome.base.BaseActivity;
import com.ghs.ghshome.bean.BoundCarsBean;
import com.ghs.ghshome.bean.CarFeeRecordItem1;
import com.ghs.ghshome.bean.CarFeeRecordItem2;
import com.ghs.ghshome.bean.CarPayRecord;
import com.ghs.ghshome.models.homePage.seed.SeedRecordAdapter;
import com.ghs.ghshome.tools.IntentUtil;
import com.ghs.ghshome.tools.AlterDialogManager;
import com.ghs.ghshome.tools.StrUtils;
import com.ghs.ghshome.tools.UserInfoUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * created by tobato
 * created date 2019/8/12 18:41.
 * application  停车记录
 */
public class ParkRecordActivity extends BaseActivity<CarParkContract.ICarParkView, CarParkPresent> implements CarParkContract.ICarParkView {

    private RecyclerView mCarFeeRecordRv;
    private SeedRecordAdapter adapter;
    private BoundCarsBean.DataBean bean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setLayout() {
        setContentView(R.layout.activity_park_fee_record);
    }
    @Override
    protected void initLayoutView() {
        super.initLayoutView();
        initView();
    }

    @Override
    public CarParkPresent creatPresenter() {
        return new CarParkPresent();
    }

    @Override
    public void getDate() {
        bean = (BoundCarsBean.DataBean) IntentUtil.getInstance().getIntentParcelableData(this);
        if (bean != null) {
            mCustomCab.setTitleText(StrUtils.initCarNumStatus(bean.getCarNum()));
        }
        getPresenter().getCarFeeRecord(mUserInfoUtil.getVillageId(), mUserInfoUtil.getUserId(),bean.getCarNum(), "");
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
            case CarParkContract.UNBIND_CAR:
                finish();
                break;
            default:
                CarPayRecord recordBean = (CarPayRecord) o;
                if (recordBean != null) {
                    List<CarPayRecord.DataBean> arrays = recordBean.getData();
                    if (arrays != null) {
                        ArrayList<MultiItemEntity> res = new ArrayList<>();
                        for (CarPayRecord.DataBean array : arrays) {
                            CarFeeRecordItem1 recordItem1 = new CarFeeRecordItem1(array.getMonth());
                            List<CarPayRecord.DataBean.CarparkTempOrderDOListBean> detailRecords = array.getCarparkTempOrderDOList();
                            if (detailRecords != null) {
                                for (CarPayRecord.DataBean.CarparkTempOrderDOListBean detailRecord : detailRecords) {
                                    CarFeeRecordItem2 recordItem2 = new CarFeeRecordItem2(detailRecord);
                                    recordItem1.addSubItem(recordItem2);

                                }
                            }
                            res.add(recordItem1);
                        }
                        adapter.setNewData(res);
                        adapter.expandAll();
                    }
                }
                break;
        }

    }

    @Override
    public void onError(String tag) {
        showToast(tag);
    }

    private void initView() {
        mCustomCab.getActionBarRightTv().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bean != null) {
                    AlterDialogManager.getInstance().showMaterialAlterDialog(ParkRecordActivity.this, R.string.delete_car, R.string.cancel, R.string.confirm, new AlterDialogManager.OnAlterDialogCallBack() {
                        @Override
                        public void leftBtClicked() {

                        }

                        @Override
                        public void rightBtClicked() {
                            showProgressDialog();
                            getPresenter().unBindCar(UserInfoUtil.getInstance().getUserId(), bean.getId(), CarParkContract.UNBIND_CAR);
                        }
                    });

                }
            }
        });
        mCarFeeRecordRv = (RecyclerView) findViewById(R.id.car_fee_record_rv);
        adapter = new SeedRecordAdapter(null);
        adapter.setEmptyView(getAdapterEmptyView("暂无数据"));
        initRecyclerview(mCarFeeRecordRv, adapter, LinearLayoutManager.VERTICAL, false);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                MultiItemEntity multiItemEntity = (MultiItemEntity) adapter.getData().get(position);
                if (SeedRecordAdapter.TYPE_LEVEL_4 == multiItemEntity.getItemType()) {
                    CarFeeRecordItem2 carFeeRecordItem2 = (CarFeeRecordItem2) multiItemEntity;
                    CarPayRecord.DataBean.CarparkTempOrderDOListBean bean = carFeeRecordItem2.getBean();
                    IntentUtil.getInstance().startActivityWithParcelableData(bean, ParkRecordActivity.this, ParkFeeDetailActivity.class);
                }
            }
        });
    }
}
