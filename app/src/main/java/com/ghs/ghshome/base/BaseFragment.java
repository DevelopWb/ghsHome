package com.ghs.ghshome.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ghs.ghshome.MyApplication;
import com.ghs.ghshome.R;
import com.ghs.ghshome.models.homePage.carPark.myCar.MineCarActivity;
import com.ghs.ghshome.models.homePage.houseBill.MyBillActivity;
import com.ghs.ghshome.models.homePage.keymanager.allotkey.AllotKeyActivity;
import com.ghs.ghshome.models.homePage.keymanager.tenementmanager.TenementManagerActivity;
import com.ghs.ghshome.models.homePage.villagenotice.VillageNoticeActivity;
import com.ghs.ghshome.models.homePage.visitors.VisitorRegistActivity;
import com.ghs.ghshome.models.propertyHall.article.ArticlePassActivity;
import com.ghs.ghshome.models.propertyHall.complaint.ComplaintAndSuggestActivity;
import com.ghs.ghshome.models.propertyHall.decoration.DecorationActivity;
import com.ghs.ghshome.models.propertyHall.openByFace.FaceOpenActivity;
import com.ghs.ghshome.models.propertyHall.openDoorByMobile.MobileOpenActivity;
import com.ghs.ghshome.models.propertyHall.repairs.RepairsActivity;
import com.ghs.ghshome.tools.DividerItemDecoration;
import com.ghs.ghshome.tools.EventBusProperty;
import com.ghs.ghshome.tools.PubUtil;
import com.ghs.ghshome.tools.ScreenUtils;
import com.ghs.ghshome.tools.StrUtils;
import com.ghs.ghshome.tools.UserInfoUtil;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import io.javac.ManyBlue.interfaces.BaseNotifyListener;
import io.javac.ManyBlue.manager.EventManager;


public abstract class BaseFragment<V, T extends BasePresent<V>> extends Fragment {
    private static final String STATE_SAVE_IS_HIDDEN = "STATE_SAVE_IS_HIDDEN";
    public String TAG = getClass().getSimpleName() + "";
    protected T mPresenter;

    private TextView activityNoDataTv;
    public ImageView mHeaderLeftIv;
    /**
     * 设置
     */
    private TextView mHeaderTitleTv;
    /**
     * 设置
     */
    private TextView mHeaderRightBt;
    private boolean isHidden;//fragment是否隐藏
    /**
     * 是否创建了View
     */
//    private boolean isCreateView;

    /**
     * 当从另一个activity回到fragment所在的activity
     * 当fragment回调onResume方法的时候，可以通过这个变量判断fragment是否可见，来决定是否要刷新数据
     */
    protected UserInfoUtil mUserInfoUtil;

    protected Context mContext;

    /**
     * 获取房屋服务的数据
     *
     * @return
     */
    protected List<String> getHouseServices() {
        List<String> houseServiceNames = new ArrayList<>();
        houseServiceNames.add("装修备案");
        houseServiceNames.add("授权管理");
        return houseServiceNames;
    }

    /*
     * 此方法在viewpager嵌套fragment时会回调
     * 查看FragmentPagerAdapter源码中instantiateItem和setPrimaryItem会调用此方法
     * 在所有生命周期方法前调用
     * 这个基类适用于在viewpager嵌套少量的fragment页面
     * 该方法是第一个回调，可以将数据放在这里处理（viewpager默认会预加载一个页面）
     * 只在fragment可见时加载数据，加快响应速度
     * */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (isVisibleToUser) {
            if (mPresenter == null) {
                mPresenter = createPresenter();//创建presenter
            }
            if (mUserInfoUtil == null) {
                mUserInfoUtil = UserInfoUtil.getInstance();
            }
            lazyLoad();
        }
        super.setUserVisibleHint(isVisibleToUser);

    }


    /**
     * 接受String类型的消息
     *
     * @param msg
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receiveStringMsg(String msg) {
        if (EventBusProperty.SELECTED_ROOM.equals(msg)) {
            if (!isHidden) {
                //选择完房间
                onSelectedRoom();
            }
        }

    }

    protected void onSelectedRoom() {
    }

    /**
     * 初始化actionBar
     */
    public void initActionBar(View view, String titleContent, String rightTvContent) {

        mHeaderLeftIv = (ImageView) view.findViewById(R.id.header_left_iv);
        mHeaderTitleTv = (TextView) view.findViewById(R.id.header_title_tv);
        mHeaderTitleTv.setText(titleContent);
        mHeaderRightBt = (TextView) view.findViewById(R.id.header_right_tv);
        if (StrUtils.isStringValueOk(rightTvContent)) {
            mHeaderRightBt.setText(rightTvContent);
        } else {
            mHeaderRightBt.setVisibility(View.INVISIBLE);
        }

    }


    /**
     * 获取present实例
     *
     * @return
     */
    public T getPresenter() {
        return mPresenter;
    }

    //订阅消息
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEventd(String str) {
        if (this instanceof BaseNotifyListener) {
        }
    }


    /**
     * 创建Presenter对象
     */
    protected abstract T createPresenter();

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 当Fragment进行创建的时候执行的方法
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initWidows();
        EventManager.getLibraryEvent().register(this);//注册

    }

    @Override
    public void onAttach(Context context) {
        if (mUserInfoUtil == null) {
            mUserInfoUtil = UserInfoUtil.getInstance();
        }
        mContext = context;
        if (mPresenter == null) {
            mPresenter = createPresenter();//创建presenter
        }
        if (null != mPresenter) {
            mPresenter.onAttachView((V) this);
        }
        super.onAttach(context);
    }

    /**
     * 初始化窗口 在界面为初始化之前调用
     */
    protected void initWidows() {
        //设置屏幕适配 360为设计图尺寸px/2
        ScreenUtils screenUtils = ScreenUtils.getInstance(getContext().getApplicationContext());
        if (screenUtils.isPortrait()) {
            screenUtils.adaptScreen4VerticalSlide(getActivity(), 360);
        } else {
            screenUtils.adaptScreen4HorizontalSlide(getActivity(), 360);
        }

    }

    /**
     * 这个方法是关于Fragment完成创建的过程中，进行界面填充的方法,该方法返回的是一个view对象
     * 在这个对象中封装的就是Fragment对应的布局
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mRoot = initFragmentViewLayout(inflater, container);
        return mRoot;
    }

    /**
     * 这个方法当onCreateView方法中的view创建完成之后，执行
     * 在inflate完成view的创建之后，可以将对应view中的各个控件进行查找findViewById
     */
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
    }

    /**
     * fragment可见的时候 加载网络数据
     */
    protected abstract void lazyLoad();


    /**
     * 这个方法是在Fragment完成创建操作之后，进行数据填充操作的时候执行的方法
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putBoolean(STATE_SAVE_IS_HIDDEN, isHidden());
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (null != mPresenter) {
            mPresenter.detachView();
        }
        EventManager.getLibraryEvent().unregister(this);
        if (MyApplication.OpenRefWatcher) {
            MyApplication.getRefWatcher(getBaseActivity()).watch(this);
        }

    }


    /**
     * 完成打气筒操作
     */
    protected abstract View initFragmentViewLayout(LayoutInflater inflater, ViewGroup container);


    /**
     * 获取屏幕高度(px)
     *
     * @param
     * @return
     */
    public int getScreenHeight() {
        DisplayMetrics dm = getContext().getResources().getDisplayMetrics();
        return dm.heightPixels;
    }


    public View getAdapterEmptyView(String text) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.empty_view, null);
        TextView noticeTv = view.findViewById(R.id.empty_view_tv);
        noticeTv.setText(text);
        return view;
    }


    /**
     * 初始化recyclerview LinearLayoutManager
     */
    public void initRecyclerview(RecyclerView recyclerView, BaseQuickAdapter baseQuickAdapter, @RecyclerView.Orientation int orientation, boolean reverseLayout) {
        LinearLayoutManager managere = new LinearLayoutManager(getContext(), orientation, reverseLayout);
        recyclerView.setLayoutManager(managere);
        recyclerView.setAdapter(baseQuickAdapter);
    }

    /**
     * 初始化recyclerview LinearLayoutManager
     */
    public void initRecyclerview(RecyclerView recyclerView, BaseQuickAdapter baseQuickAdapter, @RecyclerView.Orientation int orientation, boolean reverseLayout, boolean noSlide) {
        LinearLayoutManager managere = new LinearLayoutManager(getContext(), orientation, reverseLayout) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recyclerView.setLayoutManager(managere);
        recyclerView.setAdapter(baseQuickAdapter);
    }


    /**
     * 添加分割线
     *
     * @param recyclerView
     * @param haveTopLine
     * @param isHorizontalDivider 水平分割线
     * @param haveEndLine         最后一个item下是否划线
     */
    public void addDivider(boolean isHorizontalDivider, RecyclerView recyclerView, boolean haveTopLine, boolean haveEndLine) {
        DividerItemDecoration dividerItemDecoration;
        if (isHorizontalDivider) {
            dividerItemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL_LIST, R.drawable.divider_hor_line_sp);
        } else {
            dividerItemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.HORIZONTAL_LIST);
        }
        if (haveTopLine) {
            if (haveEndLine) {
                dividerItemDecoration.setDividerMode(DividerItemDecoration.ALL);
            } else {
                dividerItemDecoration.setDividerMode(DividerItemDecoration.START);
            }
        } else {
            if (haveEndLine) {
                dividerItemDecoration.setDividerMode(DividerItemDecoration.END);
            } else {
                dividerItemDecoration.setDividerMode(DividerItemDecoration.INSIDE);

            }
        }
        recyclerView.addItemDecoration(dividerItemDecoration);
    }


    /**
     * 设置alertdialog的宽高
     *
     * @param dialog
     * @param width  -1代表屏幕宽度  0 代表 wrap_content  其他就是自定义值了
     * @param height
     */
    public void setAlertDialogHeightWidth(AlertDialog dialog, int width, int height) {
        // 设置dialog的宽度
        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        if (-1 == width) {
            params.width = PubUtil.ScreenWidth;
        } else if (0 == width) {
            params.width = params.width;
        } else {
            params.width = width;
        }
        if (-1 == height) {
            params.height = PubUtil.ScreenHeight;
        } else if (0 == height) {
            params.height = params.height;
        } else {
            params.height = height;
        }
        dialog.getWindow().setAttributes(params);
    }


    /**
     * 隐藏控件  Invisible  gone
     *
     * @param isGone gone
     * @param views
     */
    protected void setViewsInvisible(boolean isGone, View... views) {
        if (views != null && views.length > 0) {
            for (View view : views) {
                if (view != null) {
                    if (isGone) {
                        view.setVisibility(View.GONE);
                    } else {
                        view.setVisibility(View.INVISIBLE);
                    }
                }
            }
        }
    }

    /**
     * 显示控件  Invisible  gone
     *
     * @param views
     */
    protected void setViewsVisible(View... views) {
        if (views != null && views.length > 0) {
            for (View view : views) {
                if (view != null) {
                    view.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        isHidden = hidden;
        if (!hidden) {
            lazyLoad();
        }
        super.onHiddenChanged(hidden);
    }
    /**
     * 菜单逻辑
     * @param menuName
     */
    protected void initMenuLogic(String menuName) {
        switch (menuName) {
            case "小区公告":
                startActivity(new Intent(getActivity(), VillageNoticeActivity.class));
                break;
            case "报修":
                if (getBaseActivity().checkStatus()) {
                    startActivity(new Intent(getActivity(), RepairsActivity.class));
                }
                break;
            case "投诉建议":
                if (getBaseActivity().checkStatus()) {
                    startActivity(new Intent(getActivity(), ComplaintAndSuggestActivity.class));
                }

                break;
            case "房屋账单":
                if (getBaseActivity().checkStatus()) {
                    startActivity(new Intent(getActivity(), MyBillActivity.class));
                }
                break;
            case "手机开门":
                if (getBaseActivity().checkStatus()) {
                    startActivity(new Intent(getActivity(), MobileOpenActivity.class));
                }
                break;
            case "邀请访客":
                if (getBaseActivity().checkStatus()) {
                    startActivity(new Intent(getActivity(), VisitorRegistActivity.class));
                }
                break;
            case "物品放行":
                if (getBaseActivity().checkStatus()) {
                    startActivity(new Intent(getActivity(), ArticlePassActivity.class));}
                break;
            case "装修备案":
                if (getBaseActivity().checkStatus()) {
                    startActivity(new Intent(getActivity(), DecorationActivity.class));}
                break;
            case "授权管理":
                if (getBaseActivity().checkStatus()) {
                    startActivity(new Intent(getActivity(), TenementManagerActivity.class));}
                break;
            case "人脸开门":
                if (getBaseActivity().checkStatus()) {
                    startActivity(new Intent(getActivity(), FaceOpenActivity.class));}
                break;
            case "车辆车位":
                //  跳转我的车辆
                if (getBaseActivity().checkStatus()) {
                    startActivity(new Intent(getActivity(), MineCarActivity.class));
                }
                break;
            default:
                break;
        }
    }

    /**
     * 获取BaseActivity对象
     *
     * @return
     */
    protected BaseActivity getBaseActivity() {
        return (BaseActivity) getActivity();
    }
}