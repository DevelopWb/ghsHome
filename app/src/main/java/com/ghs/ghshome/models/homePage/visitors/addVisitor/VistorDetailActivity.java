package com.ghs.ghshome.models.homePage.visitors.addVisitor;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ghs.ghshome.R;
import com.ghs.ghshome.base.BaseActivity;
import com.ghs.ghshome.bean.VisitorsBean;
import com.ghs.ghshome.models.homePage.visitors.VisitorContract;
import com.ghs.ghshome.models.homePage.visitors.VisitorPresent;
import com.ghs.ghshome.models.homePage.visitors.visitorRequest.VisitorMsgAdapter;
import com.ghs.ghshome.tools.IntentUtil;

/**
 * created by tobato
 * created date 2019/8/13 15:11.
 * application   访客详情
 */
public class VistorDetailActivity extends BaseActivity<VisitorContract.IVisitorView, VisitorPresent> implements VisitorContract.IVisitorView{



    private RecyclerView mVisitorMsgRv;
    private VisitorMsgAdapter adapter;

    @Override
    public void setLayout() {
        setContentView(R.layout.activity_vistor_particulars);

    }

    @Override
    public void initLayoutView() {
        initActionBar("详情", "");
        initView();
    }

    @Override
    public VisitorPresent creatPresenter() {
        return new VisitorPresent();
    }

    @Override
    public void getDate() {

        VisitorsBean.DataBean dataBean = (VisitorsBean.DataBean) IntentUtil.getInstance().getIntentParcelableData(VistorDetailActivity.this);
        if (dataBean != null) {
            adapter.setNewData(getPresenter().getVisitorDetailMsg(dataBean,true));
        }
    }
    @Override
    public void actionBarRightTvOnClick() {

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public void initView() {
        mVisitorMsgRv = (RecyclerView) findViewById(R.id.visitor_msg_rv);
        adapter = new VisitorMsgAdapter(R.layout.visitor_msg_item);
        initRecyclerview(mVisitorMsgRv, adapter, LinearLayoutManager.VERTICAL,false);

    }

    @Override
    public void startLoading(String tag) {

    }

    @Override
    public void stopLoading(String tag) {

    }

    @Override
    public void updateView(Object o, String tag) {

    }

    @Override
    public void onError(String tag) {

    }
}
