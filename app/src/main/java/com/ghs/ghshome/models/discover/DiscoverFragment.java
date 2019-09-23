package com.ghs.ghshome.models.discover;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ghs.ghshome.R;
import com.ghs.ghshome.base.BaseFragment;
import com.ghs.ghshome.bean.BannerListBean;
import com.ghs.ghshome.bean.CommunityListBean;
import com.ghs.ghshome.bean.OfficialListBean;
import com.ghs.ghshome.custom.LoadingDialog;
import com.ghs.ghshome.models.discover.adapter.CommunityListAdapter;
import com.ghs.ghshome.models.discover.adapter.OfficaiaLlistAdapter;
import com.ghs.ghshome.models.discover.adapter.OfficaialWebActivity;
import com.ghs.ghshome.models.discover.adapter.OfficialPaticularsActivity;
import com.ghs.ghshome.tools.Contract;
import com.ghs.ghshome.tools.StrUtils;
import com.ghs.ghshome.tools.UserInfoUtil;
import com.ghs.ghshome.tools.glide.GlideManagerUtil;
import com.stx.xhb.xbanner.XBanner;

import java.util.List;

/**
 * created by tobato
 * created date 2019/5/17 14:24.
 * application   发现模块
 */
public class DiscoverFragment extends BaseFragment<DiscoverContract.IDiscoverView, DiscoverPresenter> implements DiscoverContract.IDiscoverView {


    private XBanner mBanner;
    private RecyclerView mOfficialRv;
    private RecyclerView mCommunityRv;
    private List<BannerListBean.DataBean> dataBeanList;
    private CommunityListAdapter communityAdapter;
    private OfficaiaLlistAdapter officaiaLlistAdapter;
    private List<OfficialListBean.DataBean> officialListBeanData;
    private SwipeRefreshLayout discover_swipeRefreshLayout;
    private ScrollView discover_slv;
    private LinearLayout discover_comm_ly;
    private LinearLayout discover_off_ly;
    int emptyViewState = 0;
    private ConstraintLayout discover_empty_view;

    @Override
    protected DiscoverPresenter createPresenter() {
        return new DiscoverPresenter();
    }

    @Override
    protected void lazyLoad() {
        emptyViewState = 0;
        LoadingDialog.getInstance().showProgress(getActivity());
        getPresenter().getBannerList(mUserInfoUtil.getVillageId(),DiscoverContract.BANNER_LIST);
    }

    @Override
    protected View initFragmentViewLayout(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_discover, container, false);
        initView(view);
        return view;
    }

    @Override
    public void startLoading(String tag) {

    }

    @Override
    public void stopLoading(String tag) {

    }

    @Override
    public void updateView(Object o, String tag) {
        if (discover_swipeRefreshLayout != null) {
            discover_swipeRefreshLayout.setRefreshing(false);
        }
        Log.i("TAG", tag);
        switch (tag) {
            case DiscoverContract.BANNER_LIST:
                // 为XBanner绑定数据
                BannerListBean bannerListBean = (BannerListBean) o;
                if (bannerListBean != null) {
                    dataBeanList = bannerListBean.getData();
                    if (dataBeanList.size() > 0) {
                        //加载官方资讯
                        mBanner.setVisibility(View.VISIBLE);
                        if (mBanner != null) {
                            mBanner.setBannerData(dataBeanList);
                            // XBanner适配数据
                            mBanner.loadImage(new XBanner.XBannerAdapter() {
                                @Override
                                public void loadBanner(XBanner banner, Object model, View view, int position) {
                                    BannerListBean.DataBean dataBean = (BannerListBean.DataBean) model;
                                    GlideManagerUtil.getInstance(mContext).loadNormalPic(Contract.ImageBasePath + dataBean.getImageUrl(),R.drawable.default_image,(ImageView) view);
                                }
                            });
                            getPresenter().getOfficialList(DiscoverContract.AC_LIST_OFFICAIAL);
                        }

                    } else {
                        emptyViewState++;
                        getPresenter().getOfficialList(DiscoverContract.AC_LIST_OFFICAIAL);
                        mBanner.setVisibility(View.GONE);
                    }
                }

                break;

            case DiscoverContract.AC_CHICK:

                BannerListBean bean = (BannerListBean) o;
                if (bean != null) {
                    Log.i("TAG", "记录" + bean.getMessage());
                }

                break;

            case DiscoverContract.AC_LIST_COMMUNITY:
                LoadingDialog.getInstance().dismissProgress();
                CommunityListBean communityListBean = (CommunityListBean) o;
                if (communityListBean != null) {
                    List<CommunityListBean.DataBean> communityListBeanData = communityListBean.getData();
                    if (communityListBeanData.size() > 0) {
                        discover_comm_ly.setVisibility(View.VISIBLE);
                        communityAdapter.setNewData(communityListBeanData);
                    } else {
                        emptyViewState++;
                        discover_comm_ly.setVisibility(View.GONE);
                    }

                }

                if (emptyViewState == 3) {
                    discover_empty_view.setVisibility(View.VISIBLE);
                } else {
                    discover_empty_view.setVisibility(View.GONE);
                }

                break;

            case DiscoverContract.AC_LIST_OFFICAIAL:

                OfficialListBean officialListBean = (OfficialListBean) o;
                if (officialListBean != null) {
                    officialListBeanData = officialListBean.getData();
                    if (officialListBeanData.size() > 0) {
                        discover_off_ly.setVisibility(View.VISIBLE);
                        officaiaLlistAdapter.setNewData(officialListBeanData);
                        //加载社区活动
                        getPresenter().getCommunityList(UserInfoUtil.getInstance().getUserId(), UserInfoUtil.getInstance().getVillageId(), DiscoverContract.AC_LIST_COMMUNITY);
                    } else {
                        discover_off_ly.setVisibility(View.GONE);
                        emptyViewState++;
                        getPresenter().getCommunityList(UserInfoUtil.getInstance().getUserId(), UserInfoUtil.getInstance().getVillageId(), DiscoverContract.AC_LIST_COMMUNITY);

                    }
                }


                break;

        }


    }


    @Override
    public void onError(String tag) {
        getBaseActivity().showToast(tag);
        LoadingDialog.getInstance().dismissProgress();

    }

    public void initView(View view) {
        discover_empty_view = view.findViewById(R.id.discover_empty_view);
        discover_comm_ly = view.findViewById(R.id.discover_comm_ly);
        discover_off_ly = view.findViewById(R.id.discover_off_ly);
        mBanner = (XBanner) view.findViewById(R.id.banner);
        discover_swipeRefreshLayout = view.findViewById(R.id.discover_swipeRefreshLayout);
        discover_swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                discover_swipeRefreshLayout.setRefreshing(true);
                    emptyViewState = 0;
                    getPresenter().getBannerList(mUserInfoUtil.getVillageId(),DiscoverContract.BANNER_LIST);
            }
        });
        discover_slv = view.findViewById(R.id.discover_slv);

        discover_slv.fullScroll(ScrollView.FOCUS_UP);
        if (discover_slv != null) {
            discover_slv.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
                @Override
                public void onScrollChanged() {
                    if (discover_swipeRefreshLayout != null) {
                        discover_swipeRefreshLayout.setEnabled(discover_slv.getScrollY() == 0);
                    }
                }
            });
        }

        mOfficialRv = (RecyclerView) view.findViewById(R.id.official_rv);
        addDivider(true, mOfficialRv, false, false);
        //滑动卡顿
        mOfficialRv.setHasFixedSize(true);
        mOfficialRv.setNestedScrollingEnabled(false);

        officaiaLlistAdapter = new OfficaiaLlistAdapter(R.layout.officaial_item_layout, null);
        officaiaLlistAdapter.setEmptyView(getAdapterEmptyView("暂无资讯"));
        initRecyclerview(mOfficialRv, officaiaLlistAdapter, LinearLayoutManager.VERTICAL, false);
        officaiaLlistAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (officialListBeanData != null) {
                    Intent intent = new Intent(getActivity(), OfficialPaticularsActivity.class);
                    intent.putExtra("OFFICIAL_PL_ID", officialListBeanData.get(position).getId());
                    startActivity(intent);

                }

            }
        });
        mCommunityRv = (RecyclerView) view.findViewById(R.id.community_rv);
        //滑动卡顿
        mCommunityRv.setHasFixedSize(true);
        mCommunityRv.setNestedScrollingEnabled(false);

        communityAdapter = new CommunityListAdapter(R.layout.commity_item_layout, null);
        addDivider(true, mCommunityRv, false, false);
        communityAdapter.setEmptyView(getAdapterEmptyView("暂无活动"));
        communityAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                List<CommunityListBean.DataBean> data = communityAdapter.getData();
                if (data != null) {
                    Intent intent = new Intent(getActivity(), CommunityDetailActivity.class);
                    int id = data.get(position).getId();
                    intent.putExtra("DIS_COMMUN_PL_ID", id);
                    startActivity(intent);

                }

            }
        });
        initRecyclerview(mCommunityRv, communityAdapter, LinearLayoutManager.VERTICAL, false);
        // XBanner中某一项的点击事件
        mBanner.setOnItemClickListener(new XBanner.OnItemClickListener() {
            @Override
            public void onItemClick(XBanner banner, Object model, View view, int position) {

                if (dataBeanList != null) {
                    //点击记录
                    getPresenter().getClickBanner(dataBeanList.get(position).getId(), DiscoverContract.AC_CHICK);
                    //活动 1 ，外部链接 2

                    switch (dataBeanList.get(position).getType()) {
                        case 1:
                            int id = dataBeanList.get(position).getId();
                            Intent intentApp = new Intent(getActivity(), CommunityDetailActivity.class);
                            intentApp.putExtra("DIS_COMMUN_PL_ID", dataBeanList.get(position).getActivityId());
                            startActivity(intentApp);
                            break;

                        case 2:

                            String url = dataBeanList.get(position).getUrl();
                            if (StrUtils.isStringValueOk(url)) {
                                Intent intent = new Intent(getActivity(), OfficaialWebActivity.class);
                                intent.putExtra("web_url", url);
                                startActivity(intent);
                            }


                    }
                }
            }
        });


    }

    @Override
    public void onResume() {
        super.onResume();
        mBanner.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        mBanner.stopAutoPlay();
    }

//    private static class DiscoverFragmentHolder {
//        private static DiscoverFragment instatce = new DiscoverFragment();
//    }
//
//    public static DiscoverFragment getInstance() {
//        return DiscoverFragmentHolder.instatce;
//    }

    @Override
    protected void onSelectedRoom() {
            getPresenter().getCommunityList(UserInfoUtil.getInstance().getUserId(), UserInfoUtil.getInstance().getVillageId(), DiscoverContract.AC_LIST_COMMUNITY);
        super.onSelectedRoom();

    }
}
