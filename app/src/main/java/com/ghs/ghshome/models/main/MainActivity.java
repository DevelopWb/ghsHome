package com.ghs.ghshome.models.main;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ghs.ghshome.R;
import com.ghs.ghshome.base.BaseActivity;
import com.ghs.ghshome.base.network.RequestStatus;
import com.ghs.ghshome.bean.BluetoothsBean;
import com.ghs.ghshome.bean.RoomListBean;
import com.ghs.ghshome.bean.UserAndRoomBean;
import com.ghs.ghshome.models.discover.DiscoverFragment;
import com.ghs.ghshome.models.homePage.HomePageFragment;
import com.ghs.ghshome.models.justtalk.CallActivity;
import com.ghs.ghshome.models.justtalk.JCManager;
import com.ghs.ghshome.models.justtalk.jcevent.JCEvent;
import com.ghs.ghshome.models.main.houseManager.HouseManagerActivity;
import com.ghs.ghshome.models.mine.MineFragment;
import com.ghs.ghshome.models.propertyHall.PropertyHallFragment;
import com.ghs.ghshome.models.push.MobileInfoUtils;
import com.ghs.ghshome.models.push.jcush.HwPushManager;
import com.ghs.ghshome.tools.ActivityResultManager;
import com.ghs.ghshome.tools.CalendarUtil;
import com.ghs.ghshome.tools.EventBusProperty;
import com.ghs.ghshome.tools.HawkProperty;
import com.ghs.ghshome.tools.IntentUtil;
import com.ghs.ghshome.tools.PubUtil;
import com.ghs.ghshome.tools.RxScheduler;
import com.ghs.ghshome.tools.SoundPoolUtils;
import com.ghs.ghshome.tools.StrUtils;
import com.orhanobut.hawk.Hawk;
import com.umeng.analytics.MobclickAgent;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.javac.ManyBlue.manager.EventManager;

/**
 * created by tobato
 * created date 2019/7/12 10:29.
 * application   主activity
 */
public class MainActivity extends BaseActivity<MainContact.IMainView, MainPresent> implements MainContact.IMainView, View.OnClickListener, MainContact.OnBetweenMainAndFragmentsCallBack {


    private FrameLayout mContentFl;
    private ImageView mNavigationHomeIv;
    /**
     * 首页
     */
    private TextView mNavigationHomeTv;
    private LinearLayout mNavigationHomeLl;
    private ImageView mNavigationMineIv;
    /**
     * 我的
     */
    private TextView mNavigationMineTv;
    private LinearLayout mNavigationMineLl;
    private LinearLayout mNavigationBottom;

    private boolean isExit = false;//确定退出程序
    private ImageView mNavigationPropertyIv;
    /**
     * 物业大厅
     */
    private TextView mNavigationPropertyTv;
    private LinearLayout mNavigationPropertyLl;
    private ImageView mNavigationDiscoverIv;
    /**
     * 发现
     */
    private TextView mNavigationDiscoverTv;
    private LinearLayout mNavigationDiscoverLl;
    private ImageView mDrawerbleLayoutUserHeadIv;
    private TextView mDrawerbleLayoutUserNameTv;
    private LinearLayout mSelectVillageCurrentVillageLl;

    /**
     * 核实社区一区
     */
    private TextView mSelectVillageNameTv;
    /**
     * 1号楼1单元101
     */
    private TextView mSelectVillageUnitTv;
    /**
     * 家人
     */
    private TextView mSelectVillageUserTypeTv;
    private ConstraintLayout mSelectVillageInfoCl;
    /**
     * 所有小区
     */
    private TextView mSelectVillageAllVillageTv;
    private RecyclerView mSelectVillageRv;
    private DrawerLayout mMainDrawerlayout;
    private SwitchHouseAdapter selectVillageAdapter;
    private HomePageFragment homePageFragment;
    private PropertyHallFragment propertyHallFragment;
    private DiscoverFragment discoverFragment;
    private MineFragment mineFragment;
    private String HOME_FRAGMENT_KEY = "homePageFragment";
    private String PROPERTY_HALL_FRAGMENT_KEY = "propertyHallFragment";
    private String DISCOVER_FRAGMENT_KEY = "discoverFragment";
    private String MINE_FRAGMENT_KEY = "mineFragment";
    /**
     * 房屋管理
     */
    private TextView mDrawerbleLayoutHouseManagerTv;
    /**
     * 认证不通过
     */
    private TextView mIdCheckStatusTv;
    private ImageView mIdCheckStatusTagIv;
    private TextView mDrawerbleLayoutUserHeadTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            homePageFragment = (HomePageFragment) getSupportFragmentManager().findFragmentByTag(HOME_FRAGMENT_KEY);
            propertyHallFragment = (PropertyHallFragment) getSupportFragmentManager().findFragmentByTag(PROPERTY_HALL_FRAGMENT_KEY);
            discoverFragment = (DiscoverFragment) getSupportFragmentManager().findFragmentByTag(DISCOVER_FRAGMENT_KEY);
            mineFragment = (MineFragment) getSupportFragmentManager().findFragmentByTag(MINE_FRAGMENT_KEY);
        } else {
            initFragments();
        }
        initBottomViewStatus(0);
        checkNoticePromission();
    }


    /**
     * 获取各个fragment对象
     */
    private void initFragments() {
        if (homePageFragment == null) {
            homePageFragment = new HomePageFragment();
        }
        if (propertyHallFragment == null) {
            propertyHallFragment = new PropertyHallFragment();
        }
        if (discoverFragment == null) {
            discoverFragment = new DiscoverFragment();
        }
        if (mineFragment == null) {
            mineFragment = new MineFragment();
        }
    }

    /**
     * 初始化fragment
     *
     * @param i
     */
    private void initFragmentSelected(int i) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        hindFragments(fragmentTransaction);
        initFragments();
        switch (i) {
            case 0:
                if (!homePageFragment.isAdded()) {
                    fragmentTransaction.add(R.id.content_fl, homePageFragment, HOME_FRAGMENT_KEY);
                }
                fragmentTransaction.show(homePageFragment);
                break;
            case 1:
                if (!propertyHallFragment.isAdded()) {
                    fragmentTransaction.add(R.id.content_fl, propertyHallFragment, PROPERTY_HALL_FRAGMENT_KEY);
                }
                fragmentTransaction.show(propertyHallFragment);
                break;
            case 2:
                if (!discoverFragment.isAdded()) {
                    fragmentTransaction.add(R.id.content_fl, discoverFragment, DISCOVER_FRAGMENT_KEY);
                }
                fragmentTransaction.show(discoverFragment);
                break;
            case 3:
                if (!mineFragment.isAdded()) {
                    fragmentTransaction.add(R.id.content_fl, mineFragment, MINE_FRAGMENT_KEY);
                }
                fragmentTransaction.show(mineFragment);
                break;
            default:
                break;
        }
        fragmentTransaction.commitAllowingStateLoss();
    }

    /**
     * 隐藏所有的fragment
     *
     * @param fragmentTransaction
     */
    private void hindFragments(FragmentTransaction fragmentTransaction) {

        if (homePageFragment != null) {
            fragmentTransaction.hide(homePageFragment);
        }
        if (propertyHallFragment != null) {
            fragmentTransaction.hide(propertyHallFragment);
        }
        if (discoverFragment != null) {
            fragmentTransaction.hide(discoverFragment);
        }
        if (mineFragment != null) {
            fragmentTransaction.hide(mineFragment);
        }
    }

    /**
     * 获取当前显示的fragment
     *
     * @return
     */
    public Fragment getVisibleFragment() {
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        for (Fragment fragment : fragments) {
            if (fragment != null && fragment.isVisible()) {
                return fragment;
            }
        }
        return null;
    }

    @Override
    public void setLayout() {
        setContentView(R.layout.activity_main);
    }

    @Override
    public void initLayoutView() {
        initView();
        HwPushManager.getInstance().init(this);
    }

    private void initView() {
        mContentFl = (FrameLayout) findViewById(R.id.content_fl);
        mNavigationHomeIv = (ImageView) findViewById(R.id.navigation_home_iv);
        mNavigationHomeTv = (TextView) findViewById(R.id.navigation_home_tv);
        mNavigationHomeLl = (LinearLayout) findViewById(R.id.navigation_home_ll);
        mNavigationHomeLl.setOnClickListener(this);
        mNavigationMineIv = (ImageView) findViewById(R.id.navigation_mine_iv);
        mNavigationMineTv = (TextView) findViewById(R.id.navigation_mine_tv);
        mNavigationMineLl = (LinearLayout) findViewById(R.id.navigation_mine_ll);
        mNavigationMineLl.setOnClickListener(this);
        mNavigationBottom = (LinearLayout) findViewById(R.id.navigation_bottom);
        mNavigationPropertyIv = (ImageView) findViewById(R.id.navigation_property_iv);
        mNavigationPropertyTv = (TextView) findViewById(R.id.navigation_property_tv);
        mNavigationPropertyLl = (LinearLayout) findViewById(R.id.navigation_property_ll);
        mNavigationPropertyLl.setOnClickListener(this);
        mNavigationDiscoverIv = (ImageView) findViewById(R.id.navigation_discover_iv);
        mNavigationDiscoverTv = (TextView) findViewById(R.id.navigation_discover_tv);
        mNavigationDiscoverLl = (LinearLayout) findViewById(R.id.navigation_discover_ll);
        mNavigationDiscoverLl.setOnClickListener(this);
        mDrawerbleLayoutUserHeadIv = (ImageView) findViewById(R.id.drawerbleLayout_user_head_iv);
        mDrawerbleLayoutUserHeadTv = (TextView) findViewById(R.id.drawerbleLayout_user_head_tv);
        mDrawerbleLayoutUserHeadIv.setOnClickListener(this);
        mSelectVillageCurrentVillageLl = (LinearLayout) findViewById(R.id.select_village_currentVillage_ll);

        mDrawerbleLayoutUserNameTv = (TextView) findViewById(R.id.drawerbleLayout_userName_tv);
        mSelectVillageNameTv = (TextView) findViewById(R.id.select_village_name_tv);
        mSelectVillageUnitTv = (TextView) findViewById(R.id.select_village_unit_tv);
        mSelectVillageUserTypeTv = (TextView) findViewById(R.id.select_village_userType_tv);
        mSelectVillageInfoCl = (ConstraintLayout) findViewById(R.id.select_village_info_cl);
        mSelectVillageInfoCl.setOnClickListener(this);
        mSelectVillageAllVillageTv = (TextView) findViewById(R.id.select_village_allVillage_tv);
        mSelectVillageRv = (RecyclerView) findViewById(R.id.select_village_rv);
        selectVillageAdapter = new SwitchHouseAdapter(R.layout.select_house_item);
        mDrawerbleLayoutHouseManagerTv = (TextView) findViewById(R.id.drawerbleLayout_house_manager_tv);
        mDrawerbleLayoutHouseManagerTv.setOnClickListener(this);
        mIdCheckStatusTv = (TextView) findViewById(R.id.id_check_status_tv);
        initRecyclerview(mSelectVillageRv, selectVillageAdapter, LinearLayoutManager.VERTICAL, false);
        selectVillageAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                initRvItemSelectedStatus(adapter, position);
            }
        });
        mMainDrawerlayout = (DrawerLayout) findViewById(R.id.main_drawerlayout);
        mIdCheckStatusTagIv = (ImageView) findViewById(R.id.id_check_status_tag_iv);
        mMainDrawerlayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {
                //状态栏字体颜色是否为深色
                mImmersionBar.statusBarDarkFont(true, 0.2f).init();
                mDrawerbleLayoutUserNameTv.setText(mUserInfoUtil.getFullName());
                setHeadPicBgResource(mDrawerbleLayoutUserHeadIv, mDrawerbleLayoutUserHeadTv,false);

                initHousesData();
                //请求网络 获取小区列表
                getPresenter().getUserRoomList(mUserInfoUtil.getUserId());

            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {

                if (getVisibleFragment() instanceof HomePageFragment) {
                    //状态栏字体颜色是否为深色
                    mImmersionBar.statusBarDarkFont(false, 0.2f).init();
                } else {
                    mImmersionBar.statusBarDarkFont(true, 0.2f).init();
                }

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });

    }

    /**
     * 滑动抽屉栏后 初始化adapter数据
     */
    private void initHousesData() {
        if (!mUserInfoUtil.isSelectedCurrentVillage()) {//已经选过小区了
            mSelectVillageCurrentVillageLl.setVisibility(View.GONE);
            mSelectVillageAllVillageTv.setText("所有房屋");
        } else {
            initCurrentHouseData();
        }
    }

    /**
     * 配置当前房屋数据
     */
    private void initCurrentHouseData() {
        mSelectVillageCurrentVillageLl.setVisibility(View.VISIBLE);
        UserAndRoomBean.DataBean.GhsUserRoomDOBean currentVillage = mUserInfoUtil.getCurrentVillageBean();
        mSelectVillageNameTv.setText(currentVillage.getVillageName() + currentVillage.getPortionName());
        mSelectVillageUnitTv.setText(PubUtil.getRoomMsg(currentVillage));
        initCheckStatus(currentVillage);
        mSelectVillageUserTypeTv.setText(PubUtil.getUserType(currentVillage.getRoleType()));
        mSelectVillageInfoCl.setBackgroundResource(R.drawable.stroke_green_bg);
        mSelectVillageAllVillageTv.setText("其他房屋");
    }

    /**
     * 初始化认证状态
     *
     * @param currentVillage
     */
    private void initCheckStatus(UserAndRoomBean.DataBean.GhsUserRoomDOBean currentVillage) {
        int checkStatus = currentVillage.getUserState();
        switch (checkStatus) {
            case 1:
                mIdCheckStatusTagIv.setBackgroundResource(R.mipmap.check_pass_icon);
                mIdCheckStatusTv.setText("认证通过");
                break;
            case 2:
                mIdCheckStatusTagIv.setBackgroundResource(R.mipmap.check_reject_icon);
                mIdCheckStatusTv.setText("认证不通过");
                break;
            case 3:
                mIdCheckStatusTagIv.setBackgroundResource(R.mipmap.check_wait_icon);
                mIdCheckStatusTv.setText("待认证");
                break;
            default:
                break;
        }
    }

    /**
     * 刷新adapter
     *
     * @param adapter
     * @param position
     */
    private void initRvItemSelectedStatus(BaseQuickAdapter adapter, int position) {
        List<UserAndRoomBean.DataBean.GhsUserRoomDOBean> arrays = adapter.getData();
        UserAndRoomBean.DataBean.GhsUserRoomDOBean bean = arrays.get(position);
        for (int i = 0; i < arrays.size(); i++) {
            UserAndRoomBean.DataBean.GhsUserRoomDOBean dataBean = arrays.get(i);
            if (bean.getId() == dataBean.getId()) {
                if (dataBean.isSelected()) {
                    dataBean.setSelected(false);
                } else {
                    dataBean.setSelected(true);
                    mUserInfoUtil.saveCurrentVillageBean(dataBean);
                    mMainDrawerlayout.closeDrawers();
                    //通知fragment 重新请求网络数据
                    EventManager.sendStringMsg(EventBusProperty.SELECTED_ROOM);
                }
            } else {
                dataBean.setSelected(false);

            }
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public MainPresent creatPresenter() {
        return new MainPresent();
    }

    @Override
    public void getDate() {
        if (mUserInfoUtil.isLogin()) {
            RxScheduler.doOnIoThread(new RxScheduler.IOTask<Void>() {
                @Override
                public void doOnIOThread() {
                    JCManager.getInstance().client.login(mUserInfoUtil.getMobile(), "1000");
                }
            });
        }
        if (mUserInfoUtil.isLoadAndSelectVillage()) {
            getPresenter().getBlueMacList(mUserInfoUtil.getVillageId(), mUserInfoUtil.getCellId(), MainContact.BLUE_MAC_ADDR);
        }
    }


    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PubUtil.LOGIN_ENTER = 100;
        PubUtil.tokenExpiredFirstNotice = true;
        MobclickAgent.onKillProcess(this);
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    /**
     * 初始化底部控件的状态
     *
     * @param i
     */
    private void initBottomViewStatus(int i) {
        mNavigationHomeIv.setImageResource(R.mipmap.navigation_home_normal);
        mNavigationPropertyIv.setImageResource(R.mipmap.navigation_property_hall_normal);
        mNavigationDiscoverIv.setImageResource(R.mipmap.navigation_find_normal);
        mNavigationMineIv.setImageResource(R.mipmap.navigation_mine_normal);

        mNavigationHomeTv.setTextColor(Color.parseColor("#979797"));
        mNavigationPropertyTv.setTextColor(Color.parseColor("#979797"));
        mNavigationDiscoverTv.setTextColor(Color.parseColor("#979797"));
        mNavigationMineTv.setTextColor(Color.parseColor("#979797"));

        switch (i) {
            case 0:
                initFragmentSelected(0);
                //状态栏字体颜色是否为深色
                mImmersionBar.statusBarDarkFont(false, 0.2f).init();
                mNavigationHomeIv.setImageResource(R.mipmap.navigation_home_press);
                mNavigationHomeTv.setTextColor(getResources().getColor(R.color.text_press));
                break;
            case 1:
                mImmersionBar.statusBarDarkFont(true, 0.2f).init();
                initFragmentSelected(1);
                mNavigationPropertyIv.setImageResource(R.mipmap.navigation_property_hall_press);
                mNavigationPropertyTv.setTextColor(getResources().getColor(R.color.text_press));
                break;
            case 2:
                mImmersionBar.statusBarDarkFont(true, 0.2f).init();
                initFragmentSelected(2);
                mNavigationDiscoverIv.setImageResource(R.mipmap.navigation_find_press);
                mNavigationDiscoverTv.setTextColor(getResources().getColor(R.color.text_press));
                break;
            case 3:
                mImmersionBar.statusBarDarkFont(true, 0.2f).init();
                initFragmentSelected(3);
                mNavigationMineIv.setImageResource(R.mipmap.navigation_mine_press);
                mNavigationMineTv.setTextColor(getResources().getColor(R.color.text_press));
                break;
            default:
                break;
        }
    }


    /**
     * 检测通知相关权限，首先判断通知权限是否开启，其次是自启动权限是否开启，通知权限如果没有开启，一天提醒一次，自启动权限
     * 如果没有开启，一天提醒一次 可以设置不再提醒，因为监听不到是否开启自
     */
    private void checkNoticePromission() {
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        boolean areNotificationsEnabled = notificationManagerCompat.areNotificationsEnabled();
        if (!areNotificationsEnabled) {//未开启允许通知权限
            String savedDate = Hawk.get(HawkProperty.NOTICE_ALLOWED);
            if (StrUtils.isStringValueOk(savedDate)) {
                if (!CalendarUtil.compareDateSize("yyyy-MM-dd", CalendarUtil.getTimeFromDate("yyyy-MM-dd", new Date()), savedDate)) {
                    return;
                }
            }
            AlertDialog alertDialog = new AlertDialog.Builder(this)
                    .setIcon(R.mipmap.app_icon_round)
                    .setTitle("通知提醒")
                    .setCancelable(false)
                    .setMessage("为了便于您收到通知，建议您开启通知\n\n注：不开启通知就不能收到软件发出的通知提醒")
                    .setPositiveButton("前往设置", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            MobileInfoUtils.jumpToAllowNotice(MainActivity.this);
                            Hawk.put(HawkProperty.NOTICE_ALLOWED, CalendarUtil.getNextWarnTime(1));
                        }
                    })
                    .setNegativeButton("下次再说", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            Hawk.put(HawkProperty.NOTICE_ALLOWED, CalendarUtil.getNextWarnTime(1));

                        }
                    }).show();
            setAlertDialogHeightWidth(alertDialog, -1, 0);

            return;
        }

        boolean autorun = Hawk.get(HawkProperty.AUTORUN_NOTICE, true);//自启动权限选择提醒,默认提醒，一天一次
        if (autorun) {
            String savedDate = Hawk.get(HawkProperty.AUTO_RUN_NOTICE_DATE);
            if (StrUtils.isStringValueOk(savedDate)) {
                if (!CalendarUtil.compareDateSize("yyyy-MM-dd", CalendarUtil.getTimeFromDate("yyyy-MM-dd", new Date()), savedDate)) {
                    return;
                }
            }
            AlertDialog alertDialog = new AlertDialog.Builder(this)
                    .setTitle("温馨提示")
                    .setCancelable(false)
                    .setMessage("如果您想在软件被后台清理掉后也能收到消息提醒(类似微信)\n建议您开启自启动权限，能够有效的接收消息")
                    .setPositiveButton("前往设置", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            MobileInfoUtils.jumpStartInterface(MainActivity.this);
                            Hawk.put(HawkProperty.AUTO_RUN_NOTICE_DATE, CalendarUtil.getNextWarnTime(1));

                        }
                    })
                    .setNegativeButton("不再提醒", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            Hawk.put(HawkProperty.AUTORUN_NOTICE, false);

                        }
                    }).show();
            setAlertDialogHeightWidth(alertDialog, -1, 0);

        }
    }


    @Override
    public void onRefuseGivePromission() {
        super.onRefuseGivePromission();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == ActivityResultManager.LOGIN_MOBILE_SUCESSED || resultCode == ActivityResultManager.SELECT_VILLAGE_SUCESSED
                || resultCode == ActivityResultManager.VILLIAGE_NOTICE
                || resultCode == ActivityResultManager.LIFE_BILL || resultCode == ActivityResultManager.CONTACT_PROPERTY || resultCode == ActivityResultManager.EDIT_USER_INFO
                || resultCode == ActivityResultManager.GROWTH_TASK || resultCode == ActivityResultManager.OPEN_RECORD
                || requestCode == ActivityResultManager.ALLOT_KEY || resultCode == ActivityResultManager.MINE_SET_QUIT_APP
                || resultCode == ActivityResultManager.HOME_PAGE_REQUEST || resultCode == ActivityResultManager.QUIT_SEED_ACTIVITY) {
            super.onActivityResult(requestCode, resultCode, data);
        }
//        else if (requestCode == ActivityResultManager.REQUEST_OPEN_BLUETOOTH) {
//            if (!myBluetoothAdapter.isEnabled()) {
//
//            } else {
//                startScanBluetoothDevice();
//            }


//        }
//        else if (resultCode == ActivityResultManager.QUIT_CURRENT_ACCOUNT_FOR_LOGIN) {
//            super.onActivityResult(requestCode, resultCode, data);
//            getDate();
//        }
        else if (resultCode == ActivityResultManager.QUIT_CURRENT_ACCOUNT_FOR_LOGIN_CANCEL) {//注销登录过程中取消登录
            initBottomViewStatus(3);
            super.onActivityResult(requestCode, resultCode, data);
        }
        if (requestCode == IntentUtil.getInstance().REQUEST_CODE) {
            //请求网络 获取小区列表
            getPresenter().getUserRoomList(mUserInfoUtil.getUserId());
        }
    }

    @Override
    public void onBackPressed() {
        if (isExit == false) {
            isExit = true;
            showToast("再按一次退出程序");
            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    isExit = false;
                }

            }, 2000);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onSelectedRoom() {
        super.onSelectedRoom();
        getDate();

    }

    @Override
    protected void onNewIntent(Intent intent) {
        getDate();
        initBottomViewStatus(0);
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
            case MainContact.BLUE_MAC_ADDR:
                BluetoothsBean bluetoothsBean = (BluetoothsBean) o;
                if (bluetoothsBean != null) {
                    Hawk.put(HawkProperty.BLUE_ADDRS, bluetoothsBean);
                }
                if (bluetoothsBean.getData() != null) {
                    List<BluetoothsBean.DataBean> arrays = bluetoothsBean.getData();
                    if (arrays != null && arrays.size() > 0) {
                        List<String> bluetooths = new ArrayList<>();
                        for (BluetoothsBean.DataBean array : arrays) {
                            bluetooths.add(array.getMacAddress());
                        }
                        if (bluetooths != null) {
                            Hawk.put(HawkProperty.BLUE_ADDRS_LIST, bluetooths);

                        }

                    }
                }


                break;
            case RequestStatus.UPDATE:
                Log.d(TAG, "密钥" + o + sdf.format(new Date()));
                break;
            case MainContact.GET_ROOMS:
                RoomListBean roomListBean = (RoomListBean) o;
                List<UserAndRoomBean.DataBean.GhsUserRoomDOBean> dataBeans = roomListBean.getData();
                if (dataBeans == null || dataBeans.size() < 1) {
                    mSelectVillageAllVillageTv.setVisibility(View.GONE);
                    mSelectVillageCurrentVillageLl.setVisibility(View.GONE);
                } else {
                    if (mUserInfoUtil.isSelectedCurrentVillage()) {
                        List<UserAndRoomBean.DataBean.GhsUserRoomDOBean> dataBeans_new = new ArrayList<>();
                        for (UserAndRoomBean.DataBean.GhsUserRoomDOBean dataBean : dataBeans) {
                            if (mUserInfoUtil.getCurrentVillageBean().getRoomId() != dataBean.getRoomId()) {
                                dataBeans_new.add(dataBean);
                            } else {
                                mUserInfoUtil.saveCurrentVillageBean(dataBean);
                                //通知fragment 重新请求网络数据
                                EventManager.sendStringMsg(EventBusProperty.SELECTED_ROOM);
                                initHousesData();
                            }
                        }
                        if (dataBeans.size() == 1) {
                            mSelectVillageAllVillageTv.setVisibility(View.GONE);
                        } else {
                            mSelectVillageAllVillageTv.setVisibility(View.VISIBLE);

                        }
                        selectVillageAdapter.setNewData(dataBeans_new);
                    } else {
                        selectVillageAdapter.setNewData(dataBeans);

                    }

                }
                break;

            default:
                break;
        }
    }


    @Override
    public void onError(String tag) {
        switch (tag) {
            case MainContact.GET_ROOMS:
                if (tag.contains("未检测到小区权限")) {
                    mSelectVillageAllVillageTv.setVisibility(View.GONE);
                    mSelectVillageCurrentVillageLl.setVisibility(View.GONE);
                    showNoDataActivityLayout(true, "未检测到小区信息，请联系物业或业主完善您的信息");
                } else {
                    showToast(tag);
                }
                break;
            default:
                showToast(tag);
                break;
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.navigation_home_ll:
                initBottomViewStatus(0);
                break;
            case R.id.navigation_property_ll:
                if (checkLoginAndSelectRoomToWarn()) {
                    return;
                }
                initBottomViewStatus(1);
                break;
            case R.id.navigation_discover_ll:
                if (checkLoginAndSelectRoomToWarn()) {
                    return;
                }
                if (checkStatus()) {
                    initBottomViewStatus(2);
                }

                break;
            case R.id.navigation_mine_ll:
                initBottomViewStatus(3);
                break;
            case R.id.drawerbleLayout_user_head_iv:
                if ("defaultHeadImage.jpg".equals(mUserInfoUtil.getHeadImage())) {
                    return;
                }
                mMainDrawerlayout.closeDrawers();
//                EventManager.sendImage(mDrawerbleLayoutUserHeadIv);
                break;
            case R.id.select_village_info_cl:
                break;
            case R.id.drawerbleLayout_house_manager_tv:
                IntentUtil.getInstance().startActivityWithoutData(this, HouseManagerActivity.class);
                break;
        }
    }


    @Override
    public void hindDrawerLayout() {
        mMainDrawerlayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

    }

    @Override
    public void unHindDrawerLayout() {
        mMainDrawerlayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);

    }

    @Override
    public void openDrawerLayout() {
        mMainDrawerlayout.openDrawer(GravityCompat.START);

    }


    @Subscribe
    public void onEvent(JCEvent event) {
        if (event.getEventType() == JCEvent.EventType.CALL_ADD) {
            SoundPoolUtils.getInstance(this).startVibrator(15000);
            startActivity(new Intent(this, CallActivity.class));
//            overridePendingTransition(R.anim.activity_in, R.anim.activity_stop);

        } else if (event.getEventType() == JCEvent.EventType.CALL_RELEASE) {
            //页面移除
            SoundPoolUtils.getInstance(this).release();
        }
    }
}
