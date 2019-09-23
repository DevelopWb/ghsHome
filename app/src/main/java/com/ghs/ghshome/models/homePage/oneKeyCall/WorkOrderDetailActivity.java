package com.ghs.ghshome.models.homePage.oneKeyCall;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.ghs.ghshome.R;
import com.ghs.ghshome.base.BaseActivity;
import com.ghs.ghshome.base.displayPhotos.DisplayPhotosActivity;
import com.ghs.ghshome.bean.WorkOrderDesBean;
import com.ghs.ghshome.bean.WorkOrderStatusValueBean;
import com.ghs.ghshome.models.homePage.HomePageContract;
import com.ghs.ghshome.models.homePage.HomePagePresent;
import com.ghs.ghshome.tools.PubUtil;
import com.ghs.ghshome.tools.StrUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * created by tobato
 * created date 2019/5/23 17:36.
 * application  工单详情
 */
public class WorkOrderDetailActivity extends BaseActivity<HomePageContract.IHomePageView, HomePagePresent> implements HomePageContract.IHomePageView, View.OnClickListener {

    /**
     * 工单类型
     */
    private TextView mOrderTypeTv;
    /**
     * 反馈时间
     */
    private TextView mFeedbackTimeTv;
    /**
     * 内容
     */
    private TextView mOrderContentTv;
    /**
     * 跟进人
     */
    private TextView mProcessingPersonTv;
    /**
     * 12313123
     */
    private TextView mProcessingPersonTelTv;
    private RecyclerView mOrderStatusRv;
    private RecyclerView mOrderStatusDetailRv;
    private OrderStatusAdapter orderStatusAdapter;
    private TextView mOrderTypeValueTv;
    private OrderStatusDetailAdapter orderStatusDetailAdapter;
    /**
     * 查看图片
     */
    private TextView mOrderLookImageTv;
    private String imageUrl;
    private View mWorkOrderDividerV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setLayout() {
        setContentView(R.layout.activity_work_order_detail);

    }

    @Override
    public void initLayoutView() {
        initView();
        initActionBar("工单详情", null);
    }

    @Override
    public HomePagePresent creatPresenter() {
        return new HomePagePresent();
    }

    @Override
    public void getDate() {
        Intent intent = getIntent();
        if (intent != null) {
            int workOrderId = intent.getIntExtra("workOrderId", 0);
            getPresenter().workOrderdes(workOrderId, "");

        }
    }

    private void initView() {
        mOrderTypeTv = (TextView) findViewById(R.id.order_type_tv);
        mFeedbackTimeTv = (TextView) findViewById(R.id.feedback_time_tv);
        mOrderContentTv = (TextView) findViewById(R.id.order_content_tv);
        mOrderContentTv.setSingleLine(false);
        mProcessingPersonTv = (TextView) findViewById(R.id.processing_person_tv);
        mProcessingPersonTelTv = (TextView) findViewById(R.id.processing_person_tel_tv);
        mOrderStatusRv = (RecyclerView) findViewById(R.id.order_status_rv);
        orderStatusAdapter = new OrderStatusAdapter(R.layout.order_status_item);
        initRecyclerview(mOrderStatusRv, orderStatusAdapter, LinearLayoutManager.HORIZONTAL, false, true);
        mOrderStatusDetailRv = (RecyclerView) findViewById(R.id.order_status_detail_rv);
        orderStatusDetailAdapter = new OrderStatusDetailAdapter(R.layout.order_status_detail_item);
        initRecyclerview(mOrderStatusDetailRv, orderStatusDetailAdapter, LinearLayoutManager.VERTICAL, false);
        mOrderTypeValueTv = (TextView) findViewById(R.id.order_type_value_tv);
        addDivider(true, mOrderStatusDetailRv, false, true);
        mOrderLookImageTv = (TextView) findViewById(R.id.order_look_image_tv);
        mOrderLookImageTv.setOnClickListener(this);
        mWorkOrderDividerV = (View) findViewById(R.id.work_order_divider_v);
        mWorkOrderDividerV.setVisibility(View.GONE);
    }

    /**
     * 获取工单状态值得数据
     *
     * @param i 状态类型 状态1.跟进中2.已完成 3:待跟进
     * @return
     */
    private List<WorkOrderStatusValueBean> getOrderStatusData(int i) {
        List<WorkOrderStatusValueBean> arrays = new ArrayList<>();
        arrays.add(new WorkOrderStatusValueBean(true, "已下单"));
        arrays.add(new WorkOrderStatusValueBean(true, "待跟进"));
        switch (i) {
            case 1:
                arrays.add(new WorkOrderStatusValueBean(true, "跟进中"));
                arrays.add(new WorkOrderStatusValueBean(false, "已完成"));
                break;
            case 2:
                arrays.add(new WorkOrderStatusValueBean(true, "跟进中"));
                arrays.add(new WorkOrderStatusValueBean(true, "已完成"));
                break;
            case 4:
                arrays.add(new WorkOrderStatusValueBean(true, "跟进中"));
                arrays.add(new WorkOrderStatusValueBean(false, "已完成"));
                break;
            default:
                arrays.add(new WorkOrderStatusValueBean(false, "跟进中"));
                arrays.add(new WorkOrderStatusValueBean(false, "已完成"));
                break;
        }
        return arrays;
    }


    @Override
    public void startLoading(String tag) {

    }

    @Override
    public void stopLoading(String tag) {

    }

    @Override
    public void updateView(Object o, String tag) {
        WorkOrderDesBean workOrderDesBean = (WorkOrderDesBean) o;
        if (workOrderDesBean != null) {
            WorkOrderDesBean.DataBean.ServiceWorkDOBean orderDesBean = workOrderDesBean.getData().getServiceWorkDO();
            if (orderDesBean != null) {
                int type = orderDesBean.getServiceType();
                String orderName = PubUtil.getOrderTypeName(type);
                mOrderTypeTv.setText(orderName);
                int orderStatus = orderDesBean.getState();
                orderStatusAdapter.setNewData(getOrderStatusData(orderStatus));
                String orderStatusStr = getOrderTypeValue(orderStatus);
                mOrderTypeValueTv.setText(orderStatusStr);
                imageUrl = orderDesBean.getImageUrl();
                if (StrUtils.isStringValueOk(imageUrl)) {
                    mOrderLookImageTv.setVisibility(View.VISIBLE);
                } else {
                    mOrderLookImageTv.setVisibility(View.GONE);
                }
                mFeedbackTimeTv.setText(orderDesBean.getCreateTime());
                mOrderContentTv.setText(orderDesBean.getContent());
                String repairPeople = orderDesBean.getRepairUserName();
                repairPeople = StrUtils.isStringValueOk(repairPeople) ? "跟进人：" + repairPeople : "跟进人：暂无跟进人";
                mProcessingPersonTv.setText(repairPeople);
                mProcessingPersonTelTv.setText(orderDesBean.getRepairUserMobile());
            }
            List<WorkOrderDesBean.DataBean.StatusRecordListBean> arrays = workOrderDesBean.getData().getStatusRecordList();
            if (arrays != null) {
                orderStatusDetailAdapter.setNewData(arrays);
            }
        }
    }

    /**
     * @return 获取工单状态值  状态1.跟进中2.已完成 3:待跟进
     */
    private String getOrderTypeValue(int type) {
        String name = "";
        switch (type) {
            case 1:
                name = "跟进中";
                break;
            case 2:
                name = "已完成";
                break;
            case 3:
                name = "待跟进";
                break;
            case 4:
                name = "跟进中";
                break;
            default:
                break;
        }
        return name;

    }

    @Override
    public void onError(String tag) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.order_look_image_tv:
                //查看图片
                new DisplayPhotosActivity().startDisplayPhotosActivity(WorkOrderDetailActivity.this, imageUrl, 0);
                break;
        }
    }
}
