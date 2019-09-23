package com.ghs.ghshome.models.homePage.visitors.visitorRequest;

import android.os.Bundle;
import android.support.constraint.Group;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ghs.ghshome.R;
import com.ghs.ghshome.base.BaseActivity;
import com.ghs.ghshome.bean.VisitorsBean;
import com.ghs.ghshome.models.homePage.visitors.VisitorContract;
import com.ghs.ghshome.models.homePage.visitors.VisitorPresent;
import com.ghs.ghshome.models.homePage.visitors.addVisitor.VistorDetailActivity;
import com.ghs.ghshome.tools.IntentUtil;

/**
 * created by tobato
 * created date 2019/8/13 16:06.
 * application   处理访客请求
 */
public class DealVisitorRequestActivity extends BaseActivity<VisitorContract.IVisitorView, VisitorPresent> implements VisitorContract.IVisitorView, View.OnClickListener {


    private RecyclerView mVisitorMsgRv;
    /**
     * 审核通过
     */
    private TextView mVisitorRequestAgreeTv;
    /**
     * 拒绝
     */
    private TextView mVisitorRequestRejectTv;
    private VisitorMsgAdapter adapter;
    private VisitorsBean.DataBean beanDetail;
    private LinearLayout mVisitorNoticeLl;
    private Group mDealRequestBtsGp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void setLayout() {
        setContentView(R.layout.activity_deal_visitor_request);
        initView();
    }

    @Override
    public VisitorPresent creatPresenter() {
        return new VisitorPresent();
    }

    @Override
    public void getDate() {
        int visitorId = IntentUtil.getInstance().getIntentIntData(DealVisitorRequestActivity.this);
        getPresenter().getVisitorMsg(visitorId, VisitorContract.GET_VISITOR_MSG);
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
            case VisitorContract.ACCESS_VISITOR:
                VisitorsBean.DataBean addVistorbeanDataBean = (VisitorsBean.DataBean) o;
                IntentUtil.getInstance().startActivityWithParcelableData(addVistorbeanDataBean, this, VistorDetailActivity.class);
                finish();
                break;
            case VisitorContract.REJECT_VISITOR:
                //拒绝
                VisitorsBean.DataBean bean = (VisitorsBean.DataBean) o;
                IntentUtil.getInstance().startActivityWithParcelableData(bean, this, VistorDetailActivity.class);
                finish();
                break;
            case VisitorContract.GET_VISITOR_MSG:
                beanDetail = (VisitorsBean.DataBean) o;
                if (beanDetail != null) {
                    //状态 1:审核中 2:通过 3：驳回
                    int state = beanDetail.getState();

                    if (2 == state) {
                        mVisitorNoticeLl.setVisibility(View.VISIBLE);
                        initActionBar("详情", null);
                        adapter.setNewData(getPresenter().getVisitorDetailMsg(beanDetail, true));
                    } else {
                        mVisitorNoticeLl.setVisibility(View.GONE);
                        mDealRequestBtsGp.setVisibility(View.VISIBLE);
                        initActionBar("访客请求审核", null);
                        adapter.setNewData(getPresenter().getVisitorDetailMsg(beanDetail, false));
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
        mVisitorMsgRv = (RecyclerView) findViewById(R.id.visitor_msg_rv);
        mVisitorRequestAgreeTv = (TextView) findViewById(R.id.visitor_request_agree_tv);
        mVisitorRequestAgreeTv.setOnClickListener(this);
        mVisitorRequestRejectTv = (TextView) findViewById(R.id.visitor_request_reject_tv);
        mVisitorRequestRejectTv.setOnClickListener(this);
        adapter = new VisitorMsgAdapter(R.layout.visitor_msg_item);
        initRecyclerview(mVisitorMsgRv, adapter, LinearLayoutManager.VERTICAL, false);

        mVisitorNoticeLl = (LinearLayout) findViewById(R.id.visitor_notice_ll);
        mDealRequestBtsGp = (Group) findViewById(R.id.deal_request_bts_gp);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.visitor_request_agree_tv:
                    if (beanDetail != null) {
                        getPresenter().accessVisitors(beanDetail.getId(), beanDetail.getGhsUserId(), VisitorContract.ACCESS_VISITOR);
                }
                break;
            case R.id.visitor_request_reject_tv:
                    if (beanDetail != null) {
                        getPresenter().rejectVisitors(beanDetail.getId(), beanDetail.getGhsUserId(), VisitorContract.REJECT_VISITOR);
                }
                break;
        }
    }
}
