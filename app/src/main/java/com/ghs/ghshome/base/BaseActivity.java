package com.ghs.ghshome.base;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ghs.ghshome.R;
import com.ghs.ghshome.base.network.NetWorkUtil;
import com.ghs.ghshome.base.network.networkProxy.HttpProxy;
import com.ghs.ghshome.base.network.okgo.NetResponseCallBack;
import com.ghs.ghshome.base.promission.CheckPermListener;
import com.ghs.ghshome.base.promission.PromissionManagerActivity;
import com.ghs.ghshome.custom.CustomActionBar;
import com.ghs.ghshome.custom.LoadingDialog;
import com.ghs.ghshome.models.homePage.houseKeeperNotice.HouseKeeperNoticeActivity;
import com.ghs.ghshome.models.homePage.officialMsg.OfficialMessageActivity;
import com.ghs.ghshome.models.login.LoginActivity;
import com.ghs.ghshome.models.checkIdentity.CheckNoticeActivity;
import com.ghs.ghshome.models.main.MainActivity;
import com.ghs.ghshome.tools.ActivityManagerTool;
import com.ghs.ghshome.tools.ActivityResultManager;
import com.ghs.ghshome.tools.CalendarUtil;
import com.ghs.ghshome.tools.Contract;
import com.ghs.ghshome.tools.DividerItemDecoration;
import com.ghs.ghshome.tools.EventBusProperty;
import com.ghs.ghshome.tools.GsonManager;
import com.ghs.ghshome.tools.HawkProperty;
import com.ghs.ghshome.tools.PickerManager;
import com.ghs.ghshome.tools.PubUtil;
import com.ghs.ghshome.tools.ScreenUtils;
import com.ghs.ghshome.tools.StrUtils;
import com.ghs.ghshome.tools.ToastUtil;
import com.ghs.ghshome.tools.UserInfoUtil;
import com.ghs.ghshome.tools.glide.GlideCircleTransform;
import com.gyf.barlibrary.ImmersionBar;
import com.orhanobut.hawk.Hawk;
import com.umeng.analytics.MobclickAgent;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import io.javac.ManyBlue.ManyBlue;
import io.javac.ManyBlue.bean.NotifyMessage;
import io.javac.ManyBlue.interfaces.BaseNotifyListener;
import io.javac.ManyBlue.manager.EventManager;

/**
 * Author:wang_sir
 * Time:2018/5/13 14:37
 * Description:This is BaseActivity
 * 泛型V代表View   activity的基类
 */
public abstract class BaseActivity<V, T extends BasePresent<V>> extends PromissionManagerActivity {
    public String TAG = getClass().getSimpleName() + "";
    public SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
    private T mPresenter;
    protected ImageView mHeaderLeftIv;
    /**
     * 设置
     */
    private TextView mHeaderTitleTv;
    /**
     * 设置
     */
    private RelativeLayout mHeaderLayoutRl;
    public TextView mHeaderRightTv;
    private ConnectivityManager manager;
    private TextView activityNoDataTv;

    protected ImmersionBar mImmersionBar;
    protected UserInfoUtil mUserInfoUtil;
    protected String[] arrs = {"1", "2", "3"};
    protected CustomActionBar mCustomCab;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //创建presenter
        mPresenter = creatPresenter();
        if (mPresenter != null) {
            mPresenter.onAttachView((V) this);
        }
        mUserInfoUtil = UserInfoUtil.getInstance();
        PubUtil.ScreenWidth = getScreenWidth();
        PubUtil.ScreenHeight = getScreenHeight();
        initImmersionBar();
        initUI();
        ActivityManagerTool.getInstance().addActivity(this);
        EventManager.getLibraryEvent().register(this);//注册
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);// 锁定竖屏
        // where this is an Activity or Fragment instance
        getDate();
    }

    /**
     * 初始化布局
     */
    private void initUI() {
        setLayout();
        initCustomActionBar();
        initLayoutView();
        adapterScreen();
    }

    /**
     * 初始化窗口 在界面为初始化之前调用
     */
    protected void adapterScreen() {
        //设置屏幕适配 360为设计图尺寸px/2
        ScreenUtils screenUtils = ScreenUtils.getInstance(this);
        if (screenUtils.isPortrait()) {
            screenUtils.adaptScreen4VerticalSlide(this, 360);
        } else {
            screenUtils.adaptScreen4HorizontalSlide(this, 360);
        }

    }

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

    /**
     * 初始化布局中的view
     */
    protected abstract void setLayout();


    /**
     * 初始化布局中的view
     */
    protected void initLayoutView() {
    }

    /**
     * 初始化头布局
     */
    private void initCustomActionBar() {
        mCustomCab = (CustomActionBar) findViewById(R.id.custom_action_bar);
        if (mCustomCab != null) {
            mCustomCab.getActionBarLeftIv().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
        }

    }

    /**
     * 沉浸式初始化
     */
    private void initImmersionBar() {
        mImmersionBar = ImmersionBar.with(this);
        if (this instanceof MainActivity) {
        } else {
            mImmersionBar
                    .statusBarDarkFont(true, 0.2f) //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
                    .init();
        }
    }

    /**
     * 创建presenter
     */
    public abstract T creatPresenter();

    /**
     * 获取数据
     */
    public abstract void getDate();

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    /**
     * 隐藏软键盘  view 可以是当前点击的view 没必要全是edittext
     */
    public void hideKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        ActivityManagerTool.getInstance().removeActivity(this);
        EventManager.getLibraryEvent().unregister(this);
        stopProgressDialog();
        if (mImmersionBar != null) {
            mImmersionBar.destroy();  //必须调用该方法，防止内存泄漏，不调用该方法，如果界面bar发生改变，在不关闭app的情况下，退出此界面再进入将记忆最后一次bar改变的状态
        }


    }


    /**
     * 关闭dialog
     */
    public void stopProgressDialog() {
        LoadingDialog.getInstance().dismissProgress();

    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void stringMsgReceived(String str) {
        switch (str) {
            case "eventbus_tocken"://token过期
                if (PubUtil.tokenExpiredFirstNotice) {
                    switch (str) {
                        case PubUtil.EVENTBUS_TOCKEN_NOT_EXIST:
                            showToast("您还未登录,请登录后重试");
                            break;
                        case PubUtil.EVENTBUS_TOCKEN_EXPIRED:
                            showToast("登录已过期,请重新登录");
                            break;
                        case PubUtil.EVENTBUS_TOCKEN_TEST_FAILED:
                            showToast("身份验证失败,请重新登录");
                            break;
                        case PubUtil.EVENTBUS_TOCKEN_NO_ACTION:
                            break;
                        default:
                            break;
                    }
                    PubUtil.LOGIN_ENTER = 3;
                    PubUtil.tokenExpiredFirstNotice = false;

                    Intent intent = new Intent(this, LoginActivity.class);
                    intent.putExtra(ActivityResultManager.SAVED_MOBILE_TOKEN_EXPIRED, UserInfoUtil.getInstance().getMobile());
                    startActivity(intent);
                    Hawk.delete(HawkProperty.LOGIN_BEAN);
                    Hawk.delete(mUserInfoUtil.getUserAndRoomKey());
                }
                break;
            case EventBusProperty.SELECTED_ROOM:
                onSelectedRoom();
                break;
            case EventBusProperty.NET_WORK_UNCONNECT:
                showToast("网络异常，请检查网络");
                stopProgressDialog();
                break;
            case EventBusProperty.INTENT_TO_HOUSE_KEEPER_NOTICE:
                startActivity(new Intent(this, HouseKeeperNoticeActivity.class));
                break;
            case EventBusProperty.INTENT_TO_OFFICAL_NOTICE:
                startActivity(new Intent(this, OfficialMessageActivity.class));
                break;
            default:
                break;
        }


    }

    /**
     * 检测网络连接状态
     *
     * @return
     */
    protected boolean isNetWorkConnected() {
        if (!NetWorkUtil.isNetworkAvailable()) {
            showToast("网络异常，请检查网络");
            return false;
        }
        return true;
    }

    protected void onSelectedRoom() {
    }

    /**
     * 获取present实例
     *
     * @return
     */
    public T getPresenter() {
        return mPresenter;
    }


    /**
     *
     */
    public void showProgressDialog() {
        LoadingDialog.getInstance().showProgress(this);


    }


    //订阅消息
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(NotifyMessage notifyMessage) {
        if (this instanceof BaseNotifyListener) {
            ManyBlue.dealtListener((BaseNotifyListener) this, notifyMessage);//处理监听
        }
    }

    /**
     * 获取空布局
     *
     * @param text
     * @return
     */
    public View getAdapterEmptyView(String text) {
        View view = LayoutInflater.from(this).inflate(R.layout.empty_view, null);
        TextView noticeTv = view.findViewById(R.id.empty_view_tv);
        noticeTv.setText(text);
        return view;
    }

    /**
     * 展示没有数据时activity的布局
     */
    public void showNoDataActivityLayout(boolean show, String text) {
        if (activityNoDataTv == null) {
            activityNoDataTv = findViewById(R.id.activity_no_data_tv);
        }
        if (show) {
            activityNoDataTv.setVisibility(View.VISIBLE);
            activityNoDataTv.setText(text);
        } else {
            activityNoDataTv.setVisibility(View.GONE);
        }

    }

    /**
     * 初始化actionBar
     */
    public void initActionBar(String titleContent, String rightTvContent) {

        if (this instanceof MainActivity) {

        } else {
            mHeaderLeftIv = (ImageView) findViewById(R.id.header_left_iv);
            mHeaderTitleTv = (TextView) findViewById(R.id.header_title_tv);
            mHeaderLayoutRl = (RelativeLayout) findViewById(R.id.top_title_layout_rl);
            mHeaderTitleTv.setText(titleContent);
            mHeaderRightTv = (TextView) findViewById(R.id.header_right_tv);
            if (StrUtils.isStringValueOk(rightTvContent)) {
                mHeaderRightTv.setText(rightTvContent);
            } else {
                mHeaderRightTv.setVisibility(View.INVISIBLE);
            }
            mHeaderLeftIv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onBackPressed();
                }
            });
            mHeaderRightTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    actionBarRightTvOnClick();
                }
            });
        }

    }

    /**
     * 初始化actionBar
     *
     * @param titleContent
     * @param rightTvContent
     * @param bgColor        背景色
     */
    public void initActionBar(String titleContent, String rightTvContent, int bgColor) {

        if (this instanceof MainActivity) {

        } else {
            mHeaderLeftIv = (ImageView) findViewById(R.id.header_left_iv);
            mHeaderTitleTv = (TextView) findViewById(R.id.header_title_tv);
            mHeaderLayoutRl = (RelativeLayout) findViewById(R.id.top_title_layout_rl);
            mHeaderLayoutRl.setBackgroundColor(ContextCompat.getColor(this, bgColor));
            mHeaderTitleTv.setText(titleContent);
            mHeaderRightTv = (TextView) findViewById(R.id.header_right_tv);
            if (StrUtils.isStringValueOk(rightTvContent)) {
                mHeaderRightTv.setText(rightTvContent);
            } else {
                mHeaderRightTv.setVisibility(View.INVISIBLE);
            }
            mHeaderLeftIv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onBackPressed();
                }
            });
            mHeaderRightTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    actionBarRightTvOnClick();
                }
            });
        }

    }

    /**
     * 头布局中rightTextView的点击事件
     */
    protected void actionBarRightTvOnClick() {
    }

    ;

    @Override
    protected void onPause() {
        super.onPause();
        if (this instanceof MainActivity) {

        } else {
            MobclickAgent.onPageEnd(this.getClass().getName());
        }
        MobclickAgent.onPause(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (this instanceof MainActivity) {

        } else {
            MobclickAgent.onPageStart(this.getClass().getName());//统计页面
        }
        MobclickAgent.onResume(this);//统计时长
    }

    /**
     * 获取屏幕宽度(px)
     *
     * @param
     * @return
     */
    public int getScreenWidth() {
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;

        return width;
    }

    /**
     * 获取屏幕高度(px)
     *
     * @param
     * @return
     */
    public int getScreenHeight() {
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int height = dm.heightPixels;

        return height;
    }

    /**
     * 获取状态栏高度
     *
     * @return
     */
    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
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
            dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST, R.drawable.divider_hor_line_sp);
        } else {
            dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL_LIST);
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
     * 拨打电话
     */
    public void makeAPhoneCall(String telNum) {
        View view = getLayoutInflater().inflate(R.layout.contract_property_call_layout, null);
        final AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setView(view)
                .create();
        alertDialog.show();
        setAlertDialogHeightWidth(alertDialog, -1, 0);

        final TextView phone = view.findViewById(R.id.property_phone_no_tv);
        phone.setText(telNum);
        view.findViewById(R.id.call_property_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAppPermissions(new CheckPermListener() {
                    @Override
                    public void agreeAllPermission() {
                        alertDialog.dismiss();
                        PubUtil.callPhone(BaseActivity.this, phone.getText().toString().trim());
                    }

                    @Override
                    public void selectedAllPermission() {
                    }
                }, R.string.perm_call, PubUtil.promissions[2]);
            }
        });
        view.findViewById(R.id.cancel_call_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
    }

    /**
     * 添加分割线
     *
     * @param recyclerView
     * @param haveTopLine
     * @param isHorizontalDivider 水平分割线
     * @param haveEndLine         最后一个item下是否划线
     */
    public void addDivider(boolean isHorizontalDivider, RecyclerView recyclerView, boolean haveTopLine, boolean haveEndLine, int drawableId) {
        DividerItemDecoration dividerItemDecoration;
        if (isHorizontalDivider) {
            dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST, drawableId);
        } else {
            dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL_LIST);
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
     * 初始化recyclerview LinearLayoutManager
     */
    public void initRecyclerview(RecyclerView recyclerView, BaseQuickAdapter baseQuickAdapter, @RecyclerView.Orientation int orientation, boolean reverseLayout) {
        LinearLayoutManager managere = new LinearLayoutManager(this, orientation, reverseLayout);
        recyclerView.setLayoutManager(managere);
        recyclerView.setAdapter(baseQuickAdapter);
    }

    /**
     * 初始化recyclerview LinearLayoutManager
     */
    public void initRecyclerview(RecyclerView recyclerView, BaseQuickAdapter baseQuickAdapter, @RecyclerView.Orientation int orientation, boolean reverseLayout, boolean noSlide) {
        LinearLayoutManager managere = new LinearLayoutManager(this, orientation, reverseLayout) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recyclerView.setLayoutManager(managere);
        recyclerView.setAdapter(baseQuickAdapter);
    }

    /**
     * 描述：dip转换为px.
     *
     * @param context the context
     * @return px值
     */
    public int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 获取当年是哪年
     *
     * @return
     */
    public int getCurrentYear() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha 屏幕透明度0.0-1.0 1表示完全不透明
     */
    public void setBackgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow()
                .getAttributes();
        lp.alpha = bgAlpha;
        getWindow().setAttributes(lp);
    }

    /**
     * 在activity中调用 监听非EditText的onTouich事件
     *
     * @param activity
     * @param rootView
     */
    public void setOnTouchListenerOfViews(final Activity activity, View rootView) {
        //Set up touch listener for non-text box views to hide keyboard.
        if (!(rootView instanceof EditText)) {
            rootView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard(activity);  //Main.this是我的activity名
                    return false;
                }
            });
        }

        //If a layout container, iterate over children and seed recursion.
        if (rootView instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) rootView).getChildCount(); i++) {
                View innerView = ((ViewGroup) rootView).getChildAt(i);
                setOnTouchListenerOfViews(activity, innerView);
            }
        }
    }

    /**
     * 隐藏软键盘
     *
     * @param activity
     */
    private void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null) {
            inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);

        }
    }

    /**
     * 检查用户名的格式
     *
     * @param text
     */
    public boolean checkUserNameFormat(String text) {
        if (StrUtils.isStringValueOk(text)) {
            if (!StrUtils.checkAccountMark(text)) {
                showToast("只支持字母数字下划线和汉字");
                return false;
            } else {
                if (text.length() > 10) {
                    showToast("用户名最大长度10位");
                    return false;
                }
            }
        } else {
            showToast("用户名不能为空");
            return false;
        }
        return true;
    }

    /**
     * 弹出Toast
     *
     * @param text
     */
    public void showToast(String text) {
        ToastUtil.showNormalToast(this, Toast.LENGTH_SHORT, text);

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
            params.width = getScreenWidth();
        } else if (0 == width) {
            params.width = params.width;
        } else {
            params.width = width;
        }
        if (-1 == height) {
            params.height = getScreenHeight();
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

    /**
     * 配置view的margin属性
     */
    protected void setMargin(View view, int left, int top, int right, int bottom) {
        left = dip2px(this, left);
        top = dip2px(this, top);
        right = dip2px(this, right);
        bottom = dip2px(this, bottom);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(view.getLayoutParams());
        layoutParams.setMargins(left, top, right, bottom);
        view.setLayoutParams(layoutParams);
    }


    /**
     * @param textView 显示的控件
     * @param type     显示的时间格式 year month day time
     */
    protected void showTimeView(final TextView textView, final String type) {
        Calendar startDate = Calendar.getInstance();
        int currentYear = getCurrentYear();
        startDate.set(currentYear, 1, 0);
        Calendar endDate = Calendar.getInstance();
        endDate.set(currentYear + 1, 12, 0);
        PickerManager.getInstance().showTimePickerViewIncludeRangDate(this, null, "", new PickerManager.OnTimePickerTimeSelectedListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                textView.setText(CalendarUtil.getTimeFromDate(type, date));
            }
        }, startDate, endDate);
    }


    @Override
    public void onBackPressed() {
        if (mHeaderLeftIv != null) {
            hideKeyboard(mHeaderLeftIv);
        }
        super.onBackPressed();
    }

    /**
     * 只要一个按钮  知道了
     */
    public void showMaterialAlterdialogOnlyOneBtToClose(String msg, String btName) {

        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setMessage(msg)
                .setNegativeButton(btName, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();
        setAlertDialogHeightWidth(alertDialog, -1, 0);

    }

    /**
     * 执行种子任务
     *
     * @param taskType
     * @param payMoney
     */
    public void execSeedTask(String taskType, double payMoney) {
        HttpProxy.getInstance()
                .params("userId", mUserInfoUtil.getUserId())
                .params("taskType", taskType)
                .params("payMoney", payMoney)
                .postToNetwork(Contract.EXEC_SEED_TASK, new NetResponseCallBack() {
                    @Override
                    public void onSuccess(String content) {
                        SeedTaskResultBean seedTaskResultBean = GsonManager.getInstance().parseJsonToBean(content, SeedTaskResultBean.class);
                        if (seedTaskResultBean != null) {
                            SeedTaskResultBean.DataBean dataBean = seedTaskResultBean.getData();
                            //是否显示种子 1：是 2：否
                            int showCode = dataBean.getShowSeed();
                            int seedAmount = dataBean.getSeed();
                            String seedSource = dataBean.getSource();
                            if (1 == showCode) {
                                ToastUtil.showTakeSeedToast(BaseActivity.this, String.valueOf(seedAmount), seedSource);
                            }
                            signedSuccessfuly();
                        }
                    }

                    @Override
                    public void onError(String str) {

                    }
                });
    }

    /**
     * 签到成功
     */
    protected void signedSuccessfuly() {
    }

    /**
     * 检测跳转登录界面或者提醒
     *
     * @return
     */
    public boolean checkLoginAndSelectRoomToWarn() {
        if (mUserInfoUtil.isLogin()) {
            if (!mUserInfoUtil.isSelectedCurrentVillage()) {
                showToast("请联系该小区物业绑定房屋");
                return true;
            }
        } else {
            EventManager.sendStringMsg(PubUtil.EVENTBUS_TOCKEN_NO_ACTION);
            return true;
        }
        return false;
    }

    /**
     * 检测跳转登录界面
     *
     * @return
     */
    public boolean checkToLogin() {
        if (!mUserInfoUtil.isLogin()) {
            EventManager.sendStringMsg(PubUtil.EVENTBUS_TOCKEN_NO_ACTION);
            return true;
        }
        return false;
    }

    /**
     * 认证状态
     *
     * @return
     */
    public boolean checkStatus() {
        int status = mUserInfoUtil.getCheckStatus();
        if (1 == status) {
            return true;
        } else {
            startActivity(new Intent(this, CheckNoticeActivity.class));
        }

        return false;
    }

    /**
     * 头像
     *
     * @param imageView
     * @param isGone    TextView是否隐藏
     */
    public void setHeadPicBgResource(ImageView imageView, TextView textView, boolean isGone) {
        String headImage = mUserInfoUtil.getHeadImage();
        String headName = mUserInfoUtil.getFullName();
        String headBgColor = mUserInfoUtil.getHeadBgColor();

        if (StrUtils.isStringValueOk(headImage) && !"defaultHeadImage.jpg".equals(headImage)) {
            //已上传头像
            setViewsVisible(imageView);
            if (isGone) {
                setViewsInvisible(true, textView);
            } else {
                setViewsInvisible(false, textView);
            }
            if (isValidContextForGlide(this)) {
                Glide.with(this).load(Contract.ImageBasePath + headImage)
                        .skipMemoryCache(false)
                        .placeholder(R.mipmap.default_user_head_icon)
                        // 设置占位图
                        .transform(new GlideCircleTransform(this))//圆角
                        .into(imageView);
            }

        } else {
            setViewsVisible(textView);
            if (isGone) {
                setViewsInvisible(true, imageView);
            } else {
                setViewsInvisible(false, imageView);
            }
            if (StrUtils.isStringValueOk(headName)) {
                headName = headName.substring(headName.length() - 1, headName.length());
            }
            textView.setText(headName);
            headBgColor = StrUtils.isStringValueOk(headBgColor) ? headBgColor : "#ffffff";
            if (headBgColor.contains("53C68C")) {
                textView.setBackgroundResource(R.drawable.head_pic_circle_bg_green_shape);
            } else if (headBgColor.contains("2781FC")) {
                textView.setBackgroundResource(R.drawable.head_pic_circle_bg_blue_shape);
            } else if (headBgColor.contains("FFB243")) {
                textView.setBackgroundResource(R.drawable.head_pic_circle_bg_orange_shape);
            } else {
                textView.setBackgroundResource(R.drawable.head_pic_circle_bg_white_shape);
            }
        }
    }

    /**
     * 检测glide加载环境是否ok
     *
     * @param context
     * @return
     */
    public static boolean isValidContextForGlide(final Context context) {
        if (context == null) {
            return false;
        }
        if (context instanceof Activity) {
            final Activity activity = (Activity) context;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                if (activity.isDestroyed() || activity.isFinishing()) {
                    return false;
                }
            } else {
                if (activity.isFinishing()) {
                    return false;
                }
            }
        }
        return true;
    }

}
