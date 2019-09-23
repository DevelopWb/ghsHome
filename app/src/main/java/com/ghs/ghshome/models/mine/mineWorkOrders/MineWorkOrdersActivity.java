package com.ghs.ghshome.models.mine.mineWorkOrders;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.ghs.ghshome.R;
import com.ghs.ghshome.base.BaseActivity;
import com.ghs.ghshome.base.FragmentAdapter;
import com.ghs.ghshome.base.FragmentFactory;
import com.ghs.ghshome.models.mine.MineContract;
import com.ghs.ghshome.models.mine.MinePresent;
import com.ghs.ghshome.tools.PubUtil;

/**
 * created by tobato
 * created date 2019/5/31 11:10.
 * application   我的工单
 */
public class MineWorkOrdersActivity extends BaseActivity<MineContract.IMineView, MinePresent> implements MineContract.IMineView {

    private TabLayout mMineWorkOrderTab;
    private ViewPager mMineWorkOrderVp;
    private String[] tabs = {"全部", "待跟进", "跟进中", "已完成"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void setLayout() {
        setContentView(R.layout.activity_mine_work_orders);
        initView();
        initActionBar("我的工单", null,R.color.app_white);
    }

    @Override
    public MinePresent creatPresenter() {
        return new MinePresent();
    }

    @Override
    public void getDate() {

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

    private void initView() {
        mMineWorkOrderTab = (TabLayout) findViewById(R.id.mine_work_order_tab);
        mMineWorkOrderVp = (ViewPager) findViewById(R.id.mine_work_order_vp);
        initViewPageWithTabLayout();

    }

    /**
     * viewpage和tablayout绑定数据
     */
    private void initViewPageWithTabLayout() {

//        FragmentManager fragmentManager = getChildFragmentManager();//外面如果还有一层fragment时，使用这个方法
        FragmentManager fragmentManager = getSupportFragmentManager();//外面不是fragment包裹时，使用这个方法
        FragmentAdapter fragmentAdapter = new FragmentAdapter(fragmentManager, FragmentFactory.getFragmentsOfMineOrder());
        mMineWorkOrderVp.setAdapter(fragmentAdapter);
        mMineWorkOrderTab.setupWithViewPager(mMineWorkOrderVp);

        for (int i = 0; i < tabs.length; i++) {
            TabLayout.Tab tab = mMineWorkOrderTab.getTabAt(i);
            if (tab != null) {
                tab.setCustomView(getTabView(i, tabs));//自定义View

            }
        }
        //tab的字体选择器,默认黑色,选择时红色
        mMineWorkOrderTab.setTabTextColors(ContextCompat.getColor(this, R.color.app_black), ContextCompat.getColor(this, R.color.text_press));
        //tab的下划线颜色,默认是粉红色
        mMineWorkOrderTab.setSelectedTabIndicatorColor(ContextCompat.getColor(this, R.color.text_press));
//        //更改tab下划线的宽度
        PubUtil.setIndicator(mMineWorkOrderTab, 15, 15);
        //将tab下划线隐藏，也可以在xml里面配置高的值为0dp来实现隐藏
//        mMineWorkOrderTab.setSelectedTabIndicatorHeight(0);

    }

    /**
     * 获取自定义tab布局
     * @param position
     * @param titles
     * @return
     */
    public View getTabView(int position, String[] titles) {
        View v = LayoutInflater.from(this).inflate(R.layout.task_tab_item, null);
        TextView tv = (TextView) v.findViewById(R.id.task_tab_item_tv);
        tv.setText(titles[position]);
        TextView unReadTag = (TextView) v.findViewById(R.id.task_tab_item_unread_tag_tv);
        unReadTag.setVisibility(View.GONE);
//        if (0==position) {
//            unReadTag.setVisibility(View.VISIBLE);
//        }else{
//
//
//        }
        return v;
    }
}
