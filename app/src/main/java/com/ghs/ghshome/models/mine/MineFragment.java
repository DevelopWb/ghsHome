package com.ghs.ghshome.models.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.Group;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ghs.ghshome.R;
import com.ghs.ghshome.base.BaseFragment;
import com.ghs.ghshome.base.BasePresent;
import com.ghs.ghshome.base.network.RequestStatus;
import com.ghs.ghshome.bean.MineMenuBean;
import com.ghs.ghshome.bean.MultiItemBean;
import com.ghs.ghshome.bean.UserAndRoomBean;
import com.ghs.ghshome.models.homePage.HomePageContract;
import com.ghs.ghshome.models.homePage.HomePageModel;
import com.ghs.ghshome.models.homePage.houseBill.sectorchart.SectorChartActivity;
import com.ghs.ghshome.models.homePage.seed.SeedActivity;
import com.ghs.ghshome.models.login.LoginActivity;
import com.ghs.ghshome.models.mine.appSuggestion.AppSuggestionActivity;
import com.ghs.ghshome.models.mine.edituserinfo.EditUserInfoActivity;
import com.ghs.ghshome.models.mine.mineWorkOrders.MineWorkOrdersActivity;
import com.ghs.ghshome.models.mine.myevent.MyEventActivity;
import com.ghs.ghshome.models.mine.set.SetActivity;
import com.ghs.ghshome.models.mine.useguide.UseGuideActivity;
import com.ghs.ghshome.tools.ActivityResultManager;
import com.ghs.ghshome.tools.PubUtil;
import com.ghs.ghshome.tools.UserInfoUtil;

import java.util.ArrayList;
import java.util.List;

import io.javac.ManyBlue.manager.EventManager;


/**
 * Author:wang_sir
 * Time:2018/6/20 21:18
 * Description:   我的模块
 */


public class MineFragment extends BaseFragment implements RequestStatus, View.OnClickListener {
    private static MineFragment mFragment = null;
    private View view;
    private ImageView mMineUserHeadIv;
    /**
     * 平静如水
     */
    private TextView mMineUserNameTv;
    private TextView mMineUserAddrTv;
    /**
     * 家人
     */
    private TextView mMineUserUserTypeTv;
    /**
     * 登录
     */
    private TextView mMineUserLoginTv;
    private Group mMineMenuGp;
    private RecyclerView mMineMenuRv;
    private ConstraintLayout mMineUserInfoCl;
    private TextView mMineSeedAmountTv;
    private ConstraintLayout mMineSeedInfoCl;
    private TextView mMineUserHeadTv;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mine_fragment, container, false);
        initView(view);
        return view;
    }

    @Override
    protected void onSelectedRoom() {
        lazyLoad();
        super.onSelectedRoom();
    }

    @Override
    protected void lazyLoad() {
        if (mUserInfoUtil.isLogin()) {
            setViewsVisible(mMineMenuGp);
            setViewsInvisible(true, mMineUserAddrTv, mMineUserUserTypeTv, mMineUserLoginTv);
            mMineUserNameTv.setText(mUserInfoUtil.getNickName());

            if (mUserInfoUtil.isSelectedCurrentVillage()) {
                setViewsVisible(mMineUserAddrTv, mMineUserUserTypeTv);
                UserAndRoomBean.DataBean.GhsUserRoomDOBean roomBean = mUserInfoUtil.getCurrentVillageBean();
                mMineUserUserTypeTv.setText(PubUtil.getUserType(roomBean.getRoleType()));
                mMineUserAddrTv.setText(PubUtil.getRoomMsg(roomBean));
                mMineUserNameTv.setText(mUserInfoUtil.getFullName());
            }
            getBaseActivity().setHeadPicBgResource(mMineUserHeadIv,mMineUserHeadTv,false);
            new HomePageModel().getSeedAmount(mUserInfoUtil.getUserId(), this, HomePageContract.SEED_AMOUNT);
        } else {
            setViewsInvisible(true, mMineMenuGp);
            setViewsInvisible(true, mMineUserAddrTv, mMineUserUserTypeTv);
            mMineUserLoginTv.setVisibility(View.VISIBLE);
            mMineUserHeadIv.setImageResource(R.mipmap.default_user_head_icon);
        }
    }

    /**
     * 获取适配器数据
     *
     * @return
     */
    private List<MultiItemBean> getAdapterData() {
        List<MultiItemBean> arrays = new ArrayList<>();
        arrays.add(new MultiItemBean(MultiItemBean.MINE_FRAGMENT_DIVIDER, ""));
        arrays.add(new MultiItemBean(MultiItemBean.MINE_FRAGMENT_CONTENT, new MineMenuBean(R.mipmap.mine_work_service_icon,
                "我的工单", "查看报修，投诉与建议工单信息")));
        arrays.add(new MultiItemBean(MultiItemBean.MINE_FRAGMENT_CONTENT, new MineMenuBean(R.mipmap.mine_statistics_icon,
                "账单统计", "查看房屋物业费、水电费花费统计")));
        arrays.add(new MultiItemBean(MultiItemBean.MINE_FRAGMENT_DIVIDER, ""));
        arrays.add(new MultiItemBean(MultiItemBean.MINE_FRAGMENT_CONTENT, new MineMenuBean(R.mipmap.mine_activity_icon,
                "我的活动", "")));
        arrays.add(new MultiItemBean(MultiItemBean.MINE_FRAGMENT_CONTENT, new MineMenuBean(R.mipmap.mine_help_icon,
                "使用指南", "")));
        arrays.add(new MultiItemBean(MultiItemBean.MINE_FRAGMENT_CONTENT, new MineMenuBean(R.mipmap.mine_suggestion_icon,
                "APP反馈建议", "")));
        arrays.add(new MultiItemBean(MultiItemBean.MINE_FRAGMENT_DIVIDER, ""));
        arrays.add(new MultiItemBean(MultiItemBean.MINE_FRAGMENT_CONTENT, new MineMenuBean(R.mipmap.mine_setting_icon,
                "设置", "")));
        return arrays;
    }


    private void initView(View view) {
        mMineUserHeadIv = (ImageView) view.findViewById(R.id.mine_user_head_iv);
//        mMineUserHeadIv.setOnClickListener(this);
        mMineUserNameTv = (TextView) view.findViewById(R.id.mine_user_name_tv);
        mMineUserAddrTv = (TextView) view.findViewById(R.id.mine_user_addr_tv);
        mMineUserUserTypeTv = (TextView) view.findViewById(R.id.mine_user_userType_tv);
        mMineUserLoginTv = (TextView) view.findViewById(R.id.mine_user_login_tv);
        mMineUserLoginTv.setOnClickListener(this);
        mMineMenuGp = (Group) view.findViewById(R.id.mine_menu_gp);
        mMineMenuRv = (RecyclerView) view.findViewById(R.id.mine_menu_rv);
        MineMenuAdapter adapter = new MineMenuAdapter(getAdapterData());
        initRecyclerview(mMineMenuRv, adapter, LinearLayoutManager.VERTICAL, false);
        mMineUserInfoCl = (ConstraintLayout) view.findViewById(R.id.mine_user_info_cl);
        mMineUserInfoCl.setOnClickListener(this);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                MultiItemBean mineMenuMultiItem = (MultiItemBean) adapter.getData().get(position);
                int type = mineMenuMultiItem.getItemType();
                if (MultiItemBean.MINE_FRAGMENT_CONTENT == type) {
                    MineMenuBean bean = (MineMenuBean) mineMenuMultiItem.getObject();
                    String title = bean.getTitle();
                    switch (title) {
                        case "我的工单":
                            if (getBaseActivity().checkLoginAndSelectRoomToWarn()) {
                                return;
                            }
                            startActivity(new Intent(getContext(), MineWorkOrdersActivity.class));
                            break;
                        case "账单统计":
                            if (getBaseActivity().checkLoginAndSelectRoomToWarn()) {
                                return;
                            }
                            startActivity(new Intent(getContext(), SectorChartActivity.class));
                            break;
                        case "我的活动":
                            if (getBaseActivity().checkLoginAndSelectRoomToWarn()) {
                                return;
                            }
                            startActivity(new Intent(getContext(), MyEventActivity.class));
                            break;
                        case "使用指南":
                            startActivity(new Intent(getContext(), UseGuideActivity.class));
                            break;
                        case "APP反馈建议":
                            if (getBaseActivity().checkToLogin()) {
                                return;
                            }
                            startActivity(new Intent(getContext(), AppSuggestionActivity.class));
                            break;
                        case "设置":
                            startActivityForResult(new Intent(getContext(), SetActivity.class), ActivityResultManager.MINE_SET_QUIT_APP);
                            break;
                        default:
                            break;
                    }
                }
            }
        });
        mMineSeedAmountTv = (TextView) view.findViewById(R.id.mine_seed_amount_tv);
        mMineSeedInfoCl = (ConstraintLayout) view.findViewById(R.id.mine_seed_info_cl);
        mMineSeedInfoCl.setOnClickListener(this);
        mMineUserHeadTv = (TextView) view.findViewById(R.id.mine_user_head_tv);
    }

    @Override
    protected View initFragmentViewLayout(LayoutInflater inflater, ViewGroup container) {
        return null;
    }


    @Override
    protected BasePresent createPresenter() {
        return null;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (ActivityResultManager.EDIT_USER_INFO == resultCode || ActivityResultManager.LOGIN_MOBILE_SUCESSED == resultCode
                || resultCode == ActivityResultManager.QUIT_SEED_ACTIVITY) {
            lazyLoad();
        } else if (resultCode == ActivityResultManager.MINE_SET_QUIT_APP) {
            //跳转到登录界面
            PubUtil.isQuiteCurrentAccount = true;
            startActivity(new Intent(getContext(), LoginActivity.class));
        } else if (resultCode == ActivityResultManager.QUIT_CURRENT_ACCOUNT_FOR_LOGIN_CANCEL) {
        }
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onStart(String tag) {

    }

    @Override
    public void onSuccess(Object o, String tag) {
        switch (tag) {
            case HomePageContract.SEED_AMOUNT:
                String seedAmount = (String) o;
                mMineSeedAmountTv.setText(seedAmount);
                break;
            default:
                break;
        }


    }

    @Override
    public void onError(String tag) {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.mine_user_head_iv:
                if ("defaultHeadImage.jpg".equals(UserInfoUtil.getInstance().getHeadImage())) {
                    return;
                }
                EventManager.sendImage(mMineUserHeadIv);
                break;
            case R.id.mine_user_login_tv:
                PubUtil.LOGIN_ENTER = 2;
                startActivityForResult(new Intent(getContext(), LoginActivity.class), ActivityResultManager.QUIT_CURRENT_ACCOUNT_FOR_LOGIN_CANCEL);
                break;
            case R.id.mine_user_info_cl:
                startActivityForResult(new Intent(getContext(), EditUserInfoActivity.class), ActivityResultManager.EDIT_USER_INFO);
                break;
            case R.id.mine_seed_info_cl:
                startActivityForResult(new Intent(getContext(), SeedActivity.class), ActivityResultManager.QUIT_SEED_ACTIVITY);
                break;
        }
    }

//    private static class MineFragmentHolder {
//        private static MineFragment instatce = new MineFragment();
//    }
//    public static MineFragment getInstance() {
//        return MineFragmentHolder.instatce;
//    }

}
