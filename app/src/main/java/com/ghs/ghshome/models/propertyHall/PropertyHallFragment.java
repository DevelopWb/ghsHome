package com.ghs.ghshome.models.propertyHall;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ghs.ghshome.R;
import com.ghs.ghshome.base.BaseFragment;
import com.ghs.ghshome.bean.EditServiceMultiItem;
import com.ghs.ghshome.bean.LifeServiceBean;
import com.ghs.ghshome.models.homePage.HomePageContract;
import com.ghs.ghshome.models.homePage.HomePagePresent;
import com.ghs.ghshome.models.homePage.editUsuallyUsedServices.EditServerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * created by tobato
 * created date 2019/6/13 15:38.
 * application   物业大厅
 */
public class PropertyHallFragment extends BaseFragment<HomePageContract.IHomePageView, HomePagePresent> implements EditServerAdapter.OnAllServiceAdapterClickListener, HomePageContract.IHomePageView {


    private RecyclerView mPropertyHallRv;
    private EditServerAdapter allServersAdapter;

    /**
     * 所有菜单的点击事件
     *
     * @param adapter
     * @param position
     */
    @Override
    public void onAllServiceItemClick(BaseQuickAdapter adapter, int position) {
        LifeServiceBean.DataBean bean = (LifeServiceBean.DataBean) adapter.getData().get(position);
        String menuName = bean.getName();
        int open = bean.getOpen();
        //开通：1:开通 2:未开通
        if (2 == open) {
            getBaseActivity().showToast("该小区暂未开通此服务");
            return;
        }
        initMenuLogic(menuName);
    }

    @Override
    public void startLoading(String tag) {

    }

    @Override
    public void stopLoading(String tag) {

    }

    @Override
    public void updateView(Object o, String tag) {
        LifeServiceBean lifeServiceBean = (LifeServiceBean) o;
        if (lifeServiceBean != null) {
            List<LifeServiceBean.DataBean> services = lifeServiceBean.getData();
            if (services != null && services.size() > 0) {
                List<LifeServiceBean.DataBean> lifeServices = new ArrayList<>();
                List<LifeServiceBean.DataBean> houseServices = new ArrayList<>();
                for (LifeServiceBean.DataBean service : services) {
                    String name = service.getName();
                    service.setEditType(0);
                    if (getHouseServices().contains(name)) {
                        houseServices.add(service);
                    } else {
                        lifeServices.add(service);
                    }
                }
                List<EditServiceMultiItem> arrays = new ArrayList<>();
                if (lifeServices.size() > 0) {
                    arrays.add(new EditServiceMultiItem(EditServiceMultiItem.EDIT_SERVER_TITLE, "生活服务"));
                    arrays.add(new EditServiceMultiItem(EditServiceMultiItem.EDIT_SERVER_CONTENT, lifeServices));
                }
                if (houseServices.size() > 0) {
                    arrays.add(new EditServiceMultiItem(EditServiceMultiItem.EDIT_SERVER_TITLE, "房屋服务"));
                    arrays.add(new EditServiceMultiItem(EditServiceMultiItem.EDIT_SERVER_CONTENT, houseServices));
                }
                allServersAdapter.setNewData(arrays);
            }
        }
    }

    @Override
    public void onError(String tag) {
        getBaseActivity().showToast(tag);
    }

//    private static class PropertyHallFragmentHolder {
//        private static PropertyHallFragment instatce = new PropertyHallFragment();
//    }
//
//    public static PropertyHallFragment getInstance() {
//        return PropertyHallFragmentHolder.instatce;
//    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void lazyLoad() {
        getPresenter().getAllServices(mUserInfoUtil.getVillageId(), HomePageContract.GET_ALL_SERVICES);

    }


    @Override
    protected View initFragmentViewLayout(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_property_hall, container, false);
        initView(view);
        return view;
    }

    @Override
    protected HomePagePresent createPresenter() {
        return new HomePagePresent();
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public void initView(View view) {

        mPropertyHallRv = (RecyclerView) view.findViewById(R.id.property_hall_rv);
        allServersAdapter = new EditServerAdapter(null, this);
        allServersAdapter.setEmptyView(getAdapterEmptyView("暂无内容"));
        initRecyclerview(mPropertyHallRv, allServersAdapter, LinearLayoutManager.VERTICAL, false);
    }

}
