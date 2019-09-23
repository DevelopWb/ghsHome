package com.ghs.ghshome.models.homePage.seed;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.ghs.ghshome.R;
import com.ghs.ghshome.base.BaseActivity;
import com.ghs.ghshome.bean.SeedRecordBean;
import com.ghs.ghshome.bean.SeedRecordItem1;
import com.ghs.ghshome.bean.SeedRecordItem2;
import com.ghs.ghshome.models.homePage.HomePageContract;
import com.ghs.ghshome.models.homePage.HomePagePresent;
import com.ghs.ghshome.tools.PubUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * created by tobato
 * created date 2019/7/24 15:53.
 * application   种子记录
 */
public class SeedRecordActivity extends BaseActivity<HomePageContract.IHomePageView, HomePagePresent> implements HomePageContract.IHomePageView {

    private RecyclerView mSeedRecordRv;
    private SeedRecordAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setLayout() {
        setContentView(R.layout.activity_seed_record);
        initView();
        if (1== PubUtil.SEED_RECORD_TYPE) {
            initActionBar("种子记录",null,R.color.all_white);
        }else{
            initActionBar("使用记录",null,R.color.all_white);

        }
    }

    @Override
    public HomePagePresent creatPresenter() {
        return new HomePagePresent();
    }

    @Override
    public void getDate() {
        //SEED_RECORD_TYPE 类型：1:增加 2：消耗
        getPresenter().getSeedRecordList(mUserInfoUtil.getUserId(), PubUtil.SEED_RECORD_TYPE,HomePageContract.SEED_RECORD_LIST);
    }

    @Override
    public void startLoading(String tag) {

    }

    @Override
    public void stopLoading(String tag) {

    }

    @Override
    public void updateView(Object o, String tag) {
        SeedRecordBean recordBean = (SeedRecordBean) o;
        if (recordBean != null) {
            List<SeedRecordBean.DataBean> arrays = recordBean.getData();
            if (arrays != null) {
                ArrayList<MultiItemEntity> res = new ArrayList<>();
                for (SeedRecordBean.DataBean array : arrays) {
                    SeedRecordItem1 seedRecordItem1 = new SeedRecordItem1(array.getMonth());
                    List<SeedRecordBean.DataBean.SeedDetailDOListBean> detailRecords = array.getSeedDetailDOList();
                    if (detailRecords != null) {
                        for (SeedRecordBean.DataBean.SeedDetailDOListBean detailRecord : detailRecords) {
                            SeedRecordItem2 seedRecordItem2 = new SeedRecordItem2(detailRecord);
                            seedRecordItem1.addSubItem(seedRecordItem2);

                        }
                    }
                    res.add(seedRecordItem1);
                }
                adapter.setNewData(res);
                adapter.expandAll();
            }
        }
    }

    @Override
    public void onError(String tag) {

    }

    private void initView() {
        mSeedRecordRv = (RecyclerView) findViewById(R.id.seed_record_rv);
        adapter = new SeedRecordAdapter(null);
        adapter.setEmptyView(getAdapterEmptyView("暂无数据"));
        initRecyclerview(mSeedRecordRv,adapter, LinearLayoutManager.VERTICAL,false);
    }
}
