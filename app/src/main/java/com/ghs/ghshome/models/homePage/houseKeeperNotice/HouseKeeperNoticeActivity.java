package com.ghs.ghshome.models.homePage.houseKeeperNotice;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ghs.ghshome.R;
import com.ghs.ghshome.base.BaseActivity;
import com.ghs.ghshome.bean.HouseKeeperBean;
import com.ghs.ghshome.bean.MyBillInfolBean;
import com.ghs.ghshome.models.discover.CommunityDetailActivity;
import com.ghs.ghshome.models.homePage.HomePageContract;
import com.ghs.ghshome.models.homePage.HomePagePresent;
import com.ghs.ghshome.models.homePage.houseBill.billhistory.BillHistoryContract;
import com.ghs.ghshome.models.homePage.houseBill.billhistory.BillHistoryDetailActivity;
import com.ghs.ghshome.models.homePage.houseBill.houseBillUnpay.LifeBillUnPayActivity;
import com.ghs.ghshome.models.homePage.oneKeyCall.WorkOrderDetailActivity;
import com.ghs.ghshome.models.homePage.villagenotice.NoticeDetailActivity;
import com.ghs.ghshome.models.homePage.visitors.visitorRequest.DealVisitorRequestActivity;
import com.ghs.ghshome.models.propertyHall.article.ArticleDetailActivity;
import com.ghs.ghshome.models.propertyHall.decoration.DecorationDetailActivity;
import com.ghs.ghshome.tools.ActivityResultManager;
import com.ghs.ghshome.tools.IntentUtil;
import com.ghs.ghshome.tools.PubUtil;
import com.m7.imkfsdk.KfStartHelper;
import com.moor.imkf.IMChatManager;
import com.moor.imkf.utils.MoorUtils;

import java.util.List;

/**
 * created by tobato
 * created date 2019/5/27 16:28.
 * application   管家通知
 */
public class HouseKeeperNoticeActivity extends BaseActivity<HomePageContract.IHomePageView, HomePagePresent> implements HomePageContract.IHomePageView, View.OnClickListener {

    private RecyclerView mHouseKeeperRv;
    private int offset = 0;
    private int limit = 5;
    private HouseKeeperNoticeAdapter houseKeeperAdapter;
    //    private SwipeRefreshLayout mSwipeLayout;
    private ImageView mHeaderLeftIv;
    private TextView mHeaderTitleTv;
    private TextView mCustomerChatUnreadTv;
    private ConstraintLayout mCustomerChatCl;
    private ConstraintLayout mHeadLayoutCl;
    private KfStartHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    protected void setLayout() {
        setContentView(R.layout.activity_house_keeper);
        initView();
    }


    @Override
    public HomePagePresent creatPresenter() {
        return new HomePagePresent();
    }

    @Override
    public void getDate() {
        offset = 0;

        if (MoorUtils.isInitForUnread(getApplicationContext())) {
            IMChatManager.getInstance().getMsgUnReadCountFromService(new IMChatManager.HttpUnReadListen() {
                @Override
                public void getUnRead(int acount) {
                    if (acount > 0) {
                        mCustomerChatUnreadTv.setVisibility(View.VISIBLE);
                    } else {
                        mCustomerChatUnreadTv.setVisibility(View.GONE);

                    }
                }
            });
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (MoorUtils.isInitForUnread(getApplicationContext())) {
            IMChatManager.getInstance().getMsgUnReadCountFromService(new IMChatManager.HttpUnReadListen() {
                @Override
                public void getUnRead(int acount) {
                    if (acount > 0) {
                        mCustomerChatUnreadTv.setVisibility(View.VISIBLE);
                    } else {
                        mCustomerChatUnreadTv.setVisibility(View.GONE);

                    }

                }
            });
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
        if (HomePageContract.LIFE_BILL_DETAIL.equals(tag)) {
            MyBillInfolBean.DataBean bean = (MyBillInfolBean.DataBean) o;
            if (bean != null) {
                int payState = bean.getPayState();
                if (2 == payState) {//未支付 跳转到待支付列表
                    String type = bean.getType();
                    switch (type) {
                        //1 水 2 电 3 物业
                        case "water":
                            PubUtil.MINE_BILL_TAG = 1;
                            break;
                        case "ammeter":
                            PubUtil.MINE_BILL_TAG = 2;
                            break;
                        case "property":
                            PubUtil.MINE_BILL_TAG = 3;
                            break;
                        default:
                            break;
                    }
                    Intent intent = new Intent(HouseKeeperNoticeActivity.this, LifeBillUnPayActivity.class);
                    intent.putExtra(BillHistoryContract.BILL_HIS_ORDERID, bean.getId());
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(HouseKeeperNoticeActivity.this, BillHistoryDetailActivity.class);
                    intent.putExtra(BillHistoryContract.BILL_HIS_ORDERID, bean.getId());
                    startActivity(intent);
                }

            }
        } else {

            //        mSwipeLayout.setRefreshing(false);
            HouseKeeperBean houseKeeperBean = (HouseKeeperBean) o;
            if (houseKeeperBean != null) {
                List<HouseKeeperBean.DataBean> arrays = houseKeeperBean.getData();
                if (arrays != null) {
                    //第一次请求 将数据滚到最底部
                    if (0==offset) {
                        mHouseKeeperRv.scrollToPosition(arrays.size()-1);
                    }

                    offset += arrays.size();
                    //在顶部添加新数据
                    houseKeeperAdapter.addData(0,arrays);
                    /**
                     * set fetching off when network request ends.
                     * 网络请求结束后 设为false
                     */
                    houseKeeperAdapter.setUpFetching(false);

                    if (arrays.size() < limit) {
                        //第一页如果不够一页就不显示没有更多数据布局
                        /**
                         * 不需要下拉刷新的时候调用
                         */
                        houseKeeperAdapter.setUpFetchEnable(false);
                    }
                }
            }
        }
    }

    @Override
    public void onError(String tag) {
//        mSwipeLayout.setRefreshing(false);

    }


    private void initView() {
        //初始化客服
        helper = new KfStartHelper(HouseKeeperNoticeActivity.this);
        mHouseKeeperRv = (RecyclerView) findViewById(R.id.house_keeper_rv);
        houseKeeperAdapter = new HouseKeeperNoticeAdapter(R.layout.house_keeper_item);
        houseKeeperAdapter.setEmptyView(getAdapterEmptyView("暂无内容"));
        houseKeeperAdapter.setUpFetchEnable(true);
        houseKeeperAdapter.setUpFetchListener(new BaseQuickAdapter.UpFetchListener() {
            @Override
            public void onUpFetch() {
                houseKeeperAdapter.setUpFetching(true);
                Log.d(TAG,"onUpFetch________________"+offset);
                getPresenter().getAllHouseKeeperData(offset, limit, mUserInfoUtil.getVillageId(), mUserInfoUtil.getUserId(), mUserInfoUtil.getRoomId(), "");

            }
        });
        initRecyclerview(mHouseKeeperRv, houseKeeperAdapter, LinearLayoutManager.VERTICAL, false);

        houseKeeperAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                HouseKeeperBean.DataBean bean = (HouseKeeperBean.DataBean) adapter.getData().get(position);
                String msgType = bean.getMsgType();
                int noticeId = bean.getMsgDataId();
                Intent intent = null;
                switch (msgType) {
                    case HomePageContract.NOTICI_VILLAGE:
                        intent = new Intent(HouseKeeperNoticeActivity.this, NoticeDetailActivity.class);
                        intent.putExtra(HomePageContract.NOTICI_ID, noticeId);
                        intent.putExtra(HomePageContract.NOTICI_TYPE, HomePageContract.NOTICI_VILLAGE);
                        startActivityForResult(intent, ActivityResultManager.VILLIAGE_NOTICE_DETAIL);
                        break;
                    case HomePageContract.NOTICI_REPAIR:
                        //报修
                        startToWorkOrderDetailActivity(bean);
                        break;
                    case HomePageContract.NOTICI_SUGGESTION:
                        //投诉建议
                        startToWorkOrderDetailActivity(bean);
                        break;
                    case HomePageContract.NOTICI_BILL:
                        //房屋账单  两种情况 一种是未支付账单消息 （跳转到未支付列表） 一种是已支付账单消息（跳转到账单详情）
                        getPresenter().getBillDetail(noticeId, HomePageContract.LIFE_BILL_DETAIL);
                        break;
                    case HomePageContract.NOTICI_VISITOR:
                        //访客消息
                        break;
                    case HomePageContract.NOTICI_GOODS_INTO:
                        //物品放行
                        intent = new Intent(HouseKeeperNoticeActivity.this, ArticleDetailActivity.class);
                        intent.putExtra(ActivityResultManager.ARTICLE_ID, noticeId);
                        startActivity(intent);
                        break;
                    case HomePageContract.NOTICI_FIRE:
                        //动火备案
                        intent = new Intent(HouseKeeperNoticeActivity.this, DecorationDetailActivity.class);
                        intent.putExtra("decoration_type", 2);
                        intent.putExtra("decoration_id", noticeId);
                        startActivity(intent);
                        break;
                    case HomePageContract.NOTICI_DECORATION:
                        //装修备案详情
                        intent = new Intent(HouseKeeperNoticeActivity.this, DecorationDetailActivity.class);
                        intent.putExtra("decoration_type", 1);
                        intent.putExtra("decoration_id", noticeId);
                        startActivity(intent);
                        break;
                    case HomePageContract.NOTICI_SERVICE_WORK:
                        //一键呼叫
                        startToWorkOrderDetailActivity(bean);
                        break;
                    case HomePageContract.OFFICICAL_ACTIVITY:
                        //官方活动
                        //社区活动
                        intent = new Intent(HouseKeeperNoticeActivity.this, CommunityDetailActivity.class);
                        intent.putExtra("DIS_COMMUN_PL_ID", noticeId);
                        startActivity(intent);

                        break;
                    case HomePageContract.NOTICI_VILLAGRE_ACTIVITY:
                        //社区活动
                        intent = new Intent(HouseKeeperNoticeActivity.this, CommunityDetailActivity.class);
                        intent.putExtra("DIS_COMMUN_PL_ID", noticeId);
                        startActivity(intent);
                        break;
                    case HomePageContract.VISITOR_CHECK:
                        //访客审核
                            IntentUtil.getInstance().startActivityWithIntData(noticeId, HouseKeeperNoticeActivity.this, DealVisitorRequestActivity.class);
                        break;
                    case HomePageContract.USER_AUTH:
                        //用户认证
//                            IntentUtil.getInstance().startActivityWithIntData(noticeId, HouseKeeperNoticeActivity.this, DealVisitorRequestActivity.class);
                        break;
                    default:
                        break;
                }
            }
        });
        mHeaderLeftIv = (ImageView) findViewById(R.id.header_left_iv);
        mHeaderTitleTv = (TextView) findViewById(R.id.header_title_tv);
        mHeaderTitleTv.setText("管家通知");
        mCustomerChatUnreadTv = (TextView) findViewById(R.id.customer_chat_unread_tv);
        mCustomerChatCl = (ConstraintLayout) findViewById(R.id.customer_chat_cl);
        mCustomerChatCl.setOnClickListener(this);
        mHeaderLeftIv.setOnClickListener(this);

    }

    /**
     * 跳转至工单详情页
     *
     * @param bean
     */
    private void startToWorkOrderDetailActivity(HouseKeeperBean.DataBean bean) {
        Intent intent_repair = new Intent(HouseKeeperNoticeActivity.this, WorkOrderDetailActivity.class);
        intent_repair.putExtra("workOrderId", bean.getMsgDataId());
        startActivityForResult(intent_repair, ActivityResultManager.ENTER_ORDER_DETAIL);
    }

    @Override
    public void onBackPressed() {
        setResult(ActivityResultManager.HOME_PAGE_REQUEST);
        finish();
        super.onBackPressed();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.customer_chat_cl:
                //进入客服聊天界面

                /**
                 * 第一步：初始化help 文件
                 */
                if (helper != null) {
                    helper.initSdkChat("3396b060-7204-11e9-97b4-35ec03025fa1", mUserInfoUtil.getFullName(), mUserInfoUtil.getUserId() + "");//陈辰正式
                }
                break;
            case R.id.header_left_iv:
                onBackPressed();
                break;
        }
    }
}
