package com.ghs.ghshome.models.homePage;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ghs.ghshome.MyApplication;
import com.ghs.ghshome.R;
import com.ghs.ghshome.base.BaseFragment;
import com.ghs.ghshome.base.network.RequestStatus;
import com.ghs.ghshome.base.promission.CheckPermListener;
import com.ghs.ghshome.bean.HomePageHouseKeeperBean;
import com.ghs.ghshome.bean.LifeServiceBean;
import com.ghs.ghshome.bean.LoginBean;
import com.ghs.ghshome.bean.RoomListBean;
import com.ghs.ghshome.bean.UserAndRoomBean;
import com.ghs.ghshome.models.homePage.editUsuallyUsedServices.EditUsuallyUsedServicesActivity;
import com.ghs.ghshome.models.homePage.houseKeeperNotice.HouseKeeperNoticeActivity;
import com.ghs.ghshome.models.homePage.officialMsg.OfficialMessageActivity;
import com.ghs.ghshome.models.homePage.oneKeyCall.OneKeyCallActivity;
import com.ghs.ghshome.models.homePage.seed.SeedActivity;
import com.ghs.ghshome.models.justtalk.JCManager;
import com.ghs.ghshome.models.login.LoginActivity;
import com.ghs.ghshome.models.main.MainContact;
import com.ghs.ghshome.models.push.AliPushManager;
import com.ghs.ghshome.tools.ActivityResultManager;
import com.ghs.ghshome.tools.HawkProperty;
import com.ghs.ghshome.tools.PubUtil;
import com.ghs.ghshome.tools.StrUtils;
import com.ghs.ghshome.tools.UserInfoUtil;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;
import java.util.List;

/**
 * created by tobato
 * created date 2019/5/17 14:24.
 * application   首页
 */
public class HomePageFragment extends BaseFragment<HomePageContract.IHomePageView, HomePagePresent> implements View.OnClickListener, HomePageContract.IHomePageView, RequestStatus {


    private TextView mVilliageNameTv;
    private RecyclerView mHousekeeperListRv;
    /**
     * 进入管家服务
     */
    private TextView mInHousekeeperListTv;
    private RecyclerView mUsuallyUsedServicesRv;
    private ConstraintLayout mOneKeyCallCl;
    private HomePageHouseKeeperAdapter houseKeeperAdapter;
    private LifeServicesAdapter lifeServicesAdapter;
    private MainContact.OnBetweenMainAndFragmentsCallBack callBack;
    private View view;
    /**
     * 编辑
     */
    private TextView mEditServicesTv;
    /**
     * 99
     */
    private TextView mUnreadHouseKeeperServiceNumTv;
    private ConstraintLayout mSysNewsCl;
    private TextView mUnreadOfficalNewsNumTv;
    private boolean isFistLoadAPP = false;//首次进APP
    private ConstraintLayout mVillageInfoCl;
    private ConstraintLayout mSysSeedCl;
    private ConstraintLayout mInHousekeeperListCl;
    private AlertDialog dialog;
    /**
     * 小区名字
     */
    private TextView mRoomNameTv;
    private ImageView mVillageAddrIconIv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_home_page, null);
        initView(view);
        return view;
    }


    public void initView(View view) {
        mVilliageNameTv = (TextView) view.findViewById(R.id.villiage_name_tv);
        mHousekeeperListRv = (RecyclerView) view.findViewById(R.id.housekeeper_list_rv);
        houseKeeperAdapter = new HomePageHouseKeeperAdapter(R.layout.home_page_house_keeper_item);
        houseKeeperAdapter.setEmptyView(getAdapterEmptyView("暂无内容"));
        initRecyclerview(mHousekeeperListRv, houseKeeperAdapter, LinearLayoutManager.VERTICAL, false);
        mInHousekeeperListTv = (TextView) view.findViewById(R.id.in_housekeeper_list_tv);
        mUsuallyUsedServicesRv = (RecyclerView) view.findViewById(R.id.usually_used_services_rv);
        lifeServicesAdapter = new LifeServicesAdapter(R.layout.life_service_item);
        lifeServicesAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                LifeServiceBean.DataBean bean = (LifeServiceBean.DataBean) adapter.getData().get(position);
                String menuName = bean.getName();
                if (getBaseActivity().checkLoginAndSelectRoomToWarn()) {
                    return;
                }
                int open = bean.getOpen();
                //开通：1:开通 2:未开通
                if (2 == open) {
                    getBaseActivity().showToast("该小区暂未开通此服务");
                    return;
                }
                initMenuLogic(menuName);
            }
        });
        GridLayoutManager manager = new GridLayoutManager(getContext(), 4);
        mUsuallyUsedServicesRv.setLayoutManager(manager);
        mUsuallyUsedServicesRv.setAdapter(lifeServicesAdapter);
        mOneKeyCallCl = (ConstraintLayout) view.findViewById(R.id.one_key_call_cl);
        mOneKeyCallCl.setOnClickListener(this);
        mEditServicesTv = (TextView) view.findViewById(R.id.edit_services_tv);
        mEditServicesTv.setOnClickListener(this);
        mUnreadHouseKeeperServiceNumTv = (TextView) view.findViewById(R.id.unread_house_keeper_service_num_tv);
        houseKeeperAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivityForResult(new Intent(getContext(), HouseKeeperNoticeActivity.class), ActivityResultManager.HOME_PAGE_REQUEST);
            }
        });
        mSysNewsCl = (ConstraintLayout) view.findViewById(R.id.sys_news_cl);
        mSysNewsCl.setOnClickListener(this);
        mUnreadOfficalNewsNumTv = (TextView) view.findViewById(R.id.unread_offical_news_num_tv);
        mVillageInfoCl = (ConstraintLayout) view.findViewById(R.id.village_info_cl);
        mVillageInfoCl.setOnClickListener(this);
        mSysSeedCl = (ConstraintLayout) view.findViewById(R.id.sys_seed_cl);
        mSysSeedCl.setOnClickListener(this);
        mInHousekeeperListCl = (ConstraintLayout) view.findViewById(R.id.in_housekeeper_list_cl);
        mInHousekeeperListCl.setOnClickListener(this);
        mRoomNameTv = (TextView) view.findViewById(R.id.room_name_tv);
        mVillageAddrIconIv = (ImageView) view.findViewById(R.id.village_addr_icon_iv);
    }



    @Override
    protected void onSelectedRoom() {
        initVillageNameView();
        super.onSelectedRoom();
    }

    /**
     * 常用服务 填充假数据
     */
    private void fillFakeDataOfLifeService() {
        String[] names = {"小区公告", "报修", "投诉建议", "房屋账单"};
        List<LifeServiceBean.DataBean> arrays = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            LifeServiceBean.DataBean bean = new LifeServiceBean.DataBean();
            bean.setName(names[i]);
            bean.setEditType(0);
            bean.setFalseData(true);
            arrays.add(bean);
        }
        lifeServicesAdapter.setNewData(arrays);
    }

    private void initVillageNameView() {
        if (mUserInfoUtil.isLogin()) {
            if (mUserInfoUtil.isSelectedCurrentVillage()) {
                //显示抽屉
                if (callBack != null) {
                    callBack.unHindDrawerLayout();
                }
                //已经选择当前小区
                //阿里推送 绑定别名和标签
                new HomePageModel().getUserAndRoomInfo(UserInfoUtil.getInstance().getUserId(), UserInfoUtil.getInstance().getRoomUserId(), this, RequestStatus.UPDATE);
                UserAndRoomBean.DataBean.GhsUserRoomDOBean bean = mUserInfoUtil.getCurrentVillageBean();
                mVilliageNameTv.setText(bean.getVillageName());
                initCheckStatus(bean);
                mRoomNameTv.setText(PubUtil.getRoomMsg(bean));
                //获取常用服务
                getPresenter().getUsuallyUsedService(mUserInfoUtil.getVillageId(), mUserInfoUtil.getUserId(), HomePageContract.GET_USUALLY_USED_SERVICES);
                getPresenter().getHomePageHouseKeeperData(mUserInfoUtil.getVillageId(), mUserInfoUtil.getUserId(), mUserInfoUtil.getCurrentVillageBean().getRoomId(), HomePageContract.HOME_HOUSE_KEEPER_DATA);
                getPresenter().getUnReadOfficialNoticeNum(mUserInfoUtil.getUserId(), HomePageContract.UNREAD_OFFICIAL_NOTICE);
            } else {
                //隐藏抽屉
                if (callBack != null) {
                    callBack.hindDrawerLayout();
                }
                mVilliageNameTv.setText(mUserInfoUtil.getNickName());
                fillFakeDataOfLifeService();
                new HomePageModel().getUserAndRoomInfo(UserInfoUtil.getInstance().getUserId(), 0, this, RequestStatus.UPDATE);
            }
        } else {
            mVilliageNameTv.setText("点击登录");
            fillFakeDataOfLifeService();
            //隐藏抽屉
            if (callBack != null) {
                callBack.hindDrawerLayout();
            }
        }
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
                mVillageAddrIconIv.setBackgroundResource(R.mipmap.check_pass_icon);
                break;
            case 2:
                mVillageAddrIconIv.setBackgroundResource(R.mipmap.check_reject_icon);
                break;
            case 3:
                mVillageAddrIconIv.setBackgroundResource(R.mipmap.check_wait_icon);
                break;
            default:
                break;
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.in_housekeeper_list_cl:
                //进入管家服务
                startActivityForResult(new Intent(getContext(), HouseKeeperNoticeActivity.class), ActivityResultManager.HOME_PAGE_REQUEST);

                break;
            case R.id.one_key_call_cl:
                //一键呼叫
                startActivity(new Intent(getContext(), OneKeyCallActivity.class));
                break;
            case R.id.village_info_cl:
                //登录 选择房间
                String name = mVilliageNameTv.getText().toString().trim();
                if ("点击登录".equals(name)) {
                    PubUtil.LOGIN_ENTER = 1;
                    startActivityForResult(new Intent(getContext(), LoginActivity.class), ActivityResultManager.LOGIN_MOBILE_SUCESSED);
                } else {
                    if (mUserInfoUtil.isSelectedCurrentVillage()) {
                        if (callBack != null) {
                            callBack.openDrawerLayout();
                        }
                    }
                }
                break;
            case R.id.edit_services_tv:
                if (getBaseActivity().checkLoginAndSelectRoomToWarn()) {
                    return;
                }
//编辑点击事件
                ArrayList<LifeServiceBean.DataBean> usuallyServices = (ArrayList<LifeServiceBean.DataBean>) lifeServicesAdapter.getData();
                Intent intent = new Intent(getContext(), EditUsuallyUsedServicesActivity.class);
                intent.putParcelableArrayListExtra(ActivityResultManager.EDIT_SERVICES_USUALLY, usuallyServices);
                startActivityForResult(intent, ActivityResultManager.HOME_PAGE_REQUEST);
                break;
            case R.id.sys_news_cl:
                //系统消息
                startActivityForResult(new Intent(getActivity(), OfficialMessageActivity.class), ActivityResultManager.HOME_PAGE_REQUEST);

                break;
            case R.id.sys_seed_cl:
                if (getBaseActivity().checkToLogin()) {
                    return;
                }
                //种子
                startActivity(new Intent(getContext(), SeedActivity.class));
                break;
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == ActivityResultManager.LOGIN_MOBILE_SUCESSED
                || resultCode == ActivityResultManager.HOME_PAGE_REQUEST) {
            initVillageNameView();
        }
        super.onActivityResult(requestCode, resultCode, data);
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
            case HomePageContract.GET_USUALLY_USED_SERVICES:
                //获取常用服务
                LifeServiceBean lifeServiceBean = (LifeServiceBean) o;
                if (lifeServiceBean != null) {
                    List<LifeServiceBean.DataBean> services = lifeServiceBean.getData();
                    if (services != null && services.size() > 0) {
                        for (LifeServiceBean.DataBean service : services) {
                            service.setEditType(0);
                        }
                        lifeServicesAdapter.setNewData(services);
                    }
                }
                break;

            case HomePageContract.HOME_HOUSE_KEEPER_DATA:
                HomePageHouseKeeperBean houseKeeperBean = (HomePageHouseKeeperBean) o;
                if (houseKeeperBean != null) {
                    HomePageHouseKeeperBean.DataBean dataBean = houseKeeperBean.getData();
                    int unReadMsgAmount = dataBean.getUnreadNum();
                    unReadMsgAmount = unReadMsgAmount > 99 ? 99 : unReadMsgAmount;
                    if (unReadMsgAmount > 0) {
                        mUnreadHouseKeeperServiceNumTv.setVisibility(View.VISIBLE);
                        mUnreadHouseKeeperServiceNumTv.setText(String.valueOf(unReadMsgAmount));
                    } else {
                        mUnreadHouseKeeperServiceNumTv.setVisibility(View.GONE);
                    }
                    List<HomePageHouseKeeperBean.DataBean.HousekeeperListBean> arrays = dataBean.getHousekeeperList();
                    if (arrays != null) {
                        houseKeeperAdapter.setNewData(arrays);
                    }
                }

                break;
            case HomePageContract.UNREAD_OFFICIAL_NOTICE:
                String data = (String) o;
                if (StrUtils.isStringValueOk(data)) {
                    int unReadMsgAmount = Integer.parseInt(data);
                    if (unReadMsgAmount > 0) {
                        mUnreadOfficalNewsNumTv.setVisibility(View.VISIBLE);
                        mUnreadOfficalNewsNumTv.setText(String.valueOf(unReadMsgAmount));
                    } else {
                        mUnreadOfficalNewsNumTv.setVisibility(View.GONE);
                    }

                }
                break;
            case  MainContact.GET_ROOMS:
                RoomListBean roomListBean = (RoomListBean) o;
                List<UserAndRoomBean.DataBean.GhsUserRoomDOBean> dataBeans = roomListBean.getData();
                if (dataBeans != null) {
                    if (dataBeans.size() != 0) {
                        if (!mUserInfoUtil.isSelectedCurrentVillage()) {
                            mUserInfoUtil.saveCurrentVillageBean(dataBeans.get(0));
                        }
                    }
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onError(String tag) {
        if (StrUtils.isStringValueOk(tag)) {
            if (tag.contains("未检测到小区权限")) {
                if (Hawk.contains(mUserInfoUtil.getCurrentVillageKey())) {
                    Hawk.delete(mUserInfoUtil.getCurrentVillageKey());
                }
                //删除的是当前已选的房间 跳转到登录界面
                //当需要删除登录状态的时候 必须后删除
                Hawk.delete(HawkProperty.LOGIN_BEAN);
                startActivity(new Intent(mContext, LoginActivity.class));
            } else {
                getBaseActivity().showToast(tag);
            }
        }
    }


    @Override
    public void onAttach(Context context) {
        if (context instanceof MainContact.OnBetweenMainAndFragmentsCallBack) {
            callBack = (MainContact.OnBetweenMainAndFragmentsCallBack) context;
        }
        super.onAttach(context);
    }

    @Override
    public void onStart(String tag) {

    }

    @Override
    public void onSuccess(Object o, String tag) {
        UserAndRoomBean bean = (UserAndRoomBean) o;

        if (bean != null) {
            Log.d(TAG, isFistLoadAPP + "---------------------------------");
            if (Hawk.contains(HawkProperty.LOGIN_BEAN)) {
                if (isFistLoadAPP) {
                    isFistLoadAPP = false;
                }
                String flog = UserInfoUtil.getInstance().getLoginFlog();
                Log.d(TAG, flog + "----------------保存的-----------------");
                String flogNow = "";
                if (bean.getData() != null) {
                    if (bean.getData().getGhsUserDO() != null) {
                        flogNow = bean.getData().getGhsUserDO().getLoginFlag();
                    }
                }
                Log.d(TAG, flogNow + "----------------现在的-----------------");
                if (StrUtils.isStringValueOk(flog)) {
                    if (!flog.equals(flogNow)) {//标识不一样，提示账号已在别的设备登录
                        showMoreDeviceLoaded();
                    } else {
                        Hawk.put(mUserInfoUtil.getUserAndRoomKey(), bean);
                        LoginBean loginBean = new LoginBean();
                        loginBean.setData(bean.getData().getGhsUserDO());
                        Hawk.put(HawkProperty.LOGIN_BEAN, loginBean);
                        UserAndRoomBean.DataBean.GhsUserRoomDOBean roomDOBean = bean.getData().getGhsUserRoomDO();
                        if (roomDOBean == null) {
                            //房间被后台删除 或者被APP端删除
                            if (mUserInfoUtil.isSelectedCurrentVillage()) {
                                if (Hawk.delete(mUserInfoUtil.getCurrentVillageKey())) {
                                    //请求小区接口
                                    getPresenter().getUserRoomList(mUserInfoUtil.getUserId());
                                }
                            }
                        }
                    }
                }
            }
        }

    }

    /**
     * 单点登录
     */
    private void showMoreDeviceLoaded() {
        if (dialog == null) {
            dialog = new AlertDialog.Builder(mContext)
                    .setCancelable(false)
                    .setTitle("下线通知")
                    .setMessage("您的账号已在另一台设备登录，如非本人操作，请重新登录")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            PubUtil.LOGIN_ENTER = 1;
                            Intent intent = new Intent(getContext(), LoginActivity.class);
                            intent.putExtra(ActivityResultManager.SAVED_MOBILE, UserInfoUtil.getInstance().getMobile());
                            //登出视频
                            JCManager.getInstance().client.logout();
                            //解绑小区id及标签
                            AliPushManager.getInstance().unbindAll();
                            Hawk.delete(mUserInfoUtil.getUserAndRoomKey());
                            Hawk.delete(HawkProperty.LOGIN_BEAN);


                            startActivityForResult(intent, ActivityResultManager.LOGIN_MOBILE_SUCESSED);
                        }
                    })
                    /* .setPositiveButton("取消", new DialogInterface.OnClickListener() {
                         @Override
                         public void onClick(DialogInterface dialogInterface, int i) {
                             EventManager.sendStringMsg(ActivityResultManager.FINISH);
                         }
                     })*/
                    .show();
            setAlertDialogHeightWidth(dialog, -1, 0);
        } else {
            if (!dialog.isShowing()) {
                dialog.show();
            }
        }


    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isFistLoadAPP = true;
        if (MyApplication.IsDeBugMoDe) {
            getBaseActivity().checkAppPermissions(new CheckPermListener() {
                @Override
                public void agreeAllPermission() {

                }

                @Override
                public void selectedAllPermission() {

                }
            }, R.string.perm_store, PubUtil.promissions[1]);
        }

    }

    @Override
    protected void lazyLoad() {
        initVillageNameView();

    }

    @Override
    public void onResume() {
        super.onResume();
//        AliPushManager.getInstance().seachBoundAlias();
//        AliPushManager.getInstance().seachBoundTags();
        if (!isFistLoadAPP) {
            if (!PubUtil.isQuiteCurrentAccount) {
                initVillageNameView();
            }
        }
    }


    @Override
    protected View initFragmentViewLayout(LayoutInflater inflater, ViewGroup container) {
        return null;
    }


    @Override
    protected HomePagePresent createPresenter() {
        return new HomePagePresent();
    }

    @Override
    public void onDetach() {
        callBack = null;
        super.onDetach();
    }

}
