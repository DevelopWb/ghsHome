package com.ghs.ghshome.models.homePage.houseBill;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ghs.ghshome.R;
import com.ghs.ghshome.base.BaseActivity;
import com.ghs.ghshome.base.BasePresent;
import com.ghs.ghshome.bean.FuctionBean;
import com.ghs.ghshome.models.homePage.houseBill.houseBillUnpay.LifeBillUnPayActivity;
import com.ghs.ghshome.tools.ActivityResultManager;
import com.ghs.ghshome.tools.PubUtil;

import java.util.ArrayList;
import java.util.List;
/**
 * created by tobato
 * created date 2019/6/3 10:24.
 * application   房屋账单
 */
public class MyBillActivity extends BaseActivity {

    private RecyclerView mMineMyBillRv;
    private MyBillAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void getDate() {
        adapter.setNewData(getRvData());
    }

    /**
     * 获取recyclerview 数据
     *
     * @return
     */
    private List<FuctionBean> getRvData() {
        List<FuctionBean> fuctionBeans = new ArrayList<>();
        String[] names = { "物业费", "电费","水费"};
        Integer[] resources = {R.mipmap.mine_bill_property_fee, R.mipmap.mine_bill_electricity_fee, R.mipmap.mine_bill_water_fee};
        for (int i = 0; i < 3; i++) {
            FuctionBean fuctionBean = new FuctionBean();
            fuctionBean.setName(names[i]);
            fuctionBean.setResourceId(resources[i]);
            fuctionBeans.add(fuctionBean);
        }
        return fuctionBeans;
    }

    @Override
    public void initLayoutView() {
        initView();
        initActionBar("房屋账单", "");


    }

    @Override
    public void setLayout() {
        setContentView(R.layout.activity_my_bill);

    }

    @Override
    public void actionBarRightTvOnClick() {
//        if (!PubUtil.BILL_INFO_FROM_MINE) {
//            startActivity(new Intent(this, SectorChartActivity.class));
//        }
    }

    @Override
    public BasePresent creatPresenter() {
        return null;
    }


    private void initView() {
        mMineMyBillRv = (RecyclerView) findViewById(R.id.mine_my_bill_rv);
        GridLayoutManager manager = new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false);
        mMineMyBillRv.setLayoutManager(manager);
        adapter = new MyBillAdapter(R.layout.mine_bill_item);
        mMineMyBillRv.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                switch (position) {
                    case 0:
                        PubUtil.MINE_BILL_TAG = 3;
                        break;
                    case 1:
                        PubUtil.MINE_BILL_TAG = 2;
                        break;
                    case 2:
                        PubUtil.MINE_BILL_TAG = 1;
                        break;
                    default:
                        break;
                }
                startActivity(new Intent(MyBillActivity.this, LifeBillUnPayActivity.class));
            }
        });
    }


    @Override
    public void onBackPressed() {
        setResult(ActivityResultManager.BILL_HISTORY);
        super.onBackPressed();
    }
}
