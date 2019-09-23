package com.ghs.ghshome.models.homePage.editUsuallyUsedServices;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ghs.ghshome.R;
import com.ghs.ghshome.base.BaseActivity;
import com.ghs.ghshome.bean.EditServiceMultiItem;
import com.ghs.ghshome.bean.LifeServiceBean;
import com.ghs.ghshome.models.homePage.HomePageContract;
import com.ghs.ghshome.models.homePage.HomePagePresent;
import com.ghs.ghshome.models.homePage.LifeServicesAdapter;
import com.ghs.ghshome.tools.ActivityResultManager;
import com.ghs.ghshome.base.network.NetWorkUtil;
import com.ghs.ghshome.tools.PubUtil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * created by tobato
 * created date 2019/5/21 16:41.
 * application   编辑常用服务
 */
public class EditUsuallyUsedServicesActivity extends BaseActivity<HomePageContract.IHomePageView, HomePagePresent> implements HomePageContract.IHomePageView, View.OnClickListener, EditServerAdapter.OnAllServiceAdapterClickListener {

    /**
     * 取消
     */
    private TextView mEditServiceCancelTv;
    /**
     * 确定
     */
    private TextView mEditServiceConfirmTv;
    private RecyclerView mEditServerUsuallyUsedRv;
    private RecyclerView mEditServerAllDataRv;
    private EditServerAdapter allServersAdapter;
    private LifeServicesAdapter usuallyServicesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setLayout() {
        setContentView(R.layout.activity_edit_usually_used_services);

    }

    @Override
    public void initLayoutView() {
        initView();
    }

    @Override
    public HomePagePresent creatPresenter() {
        return new HomePagePresent();
    }

    @Override
    public void getDate() {
        getPresenter().getAllServices(mUserInfoUtil.getVillageId(), HomePageContract.GET_ALL_SERVICES);
        Intent intent = getIntent();
        if (intent != null) {
            ArrayList<LifeServiceBean.DataBean> arrays = intent.getParcelableArrayListExtra(ActivityResultManager.EDIT_SERVICES_USUALLY);
            if (arrays != null && arrays.size() > 0) {
                PubUtil.usuallyServers.clear();
                for (LifeServiceBean.DataBean array : arrays) {
                    array.setEditType(1);
                    PubUtil.usuallyServers.add(String.valueOf(array.getMenuId()));
                }
                usuallyServicesAdapter.setNewData(arrays);
            }
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
        switch (tag) {
            case HomePageContract.SAVE_USUALLY_USED_SERVICES:
                showToast("保存成功");
                onBackPressed();
                break;
            default:
                LifeServiceBean lifeServiceBean = (LifeServiceBean) o;
                if (lifeServiceBean != null) {
                    List<LifeServiceBean.DataBean> services = lifeServiceBean.getData();
                    if (services != null && services.size() > 0) {
                        List<LifeServiceBean.DataBean> lifeServices = new ArrayList<>();
                        List<LifeServiceBean.DataBean> houseServices = new ArrayList<>();
                        for (LifeServiceBean.DataBean service : services) {
                            String name = service.getName();
                            service.setEditType(1);
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
                break;

        }
    }

    @Override
    public void onError(String tag) {

    }


    private void initView() {
        mEditServiceCancelTv = (TextView) findViewById(R.id.edit_service_cancel_tv);
        mEditServiceCancelTv.setOnClickListener(this);
        mEditServiceConfirmTv = (TextView) findViewById(R.id.edit_service_confirm_tv);
        mEditServiceConfirmTv.setOnClickListener(this);
        mEditServerUsuallyUsedRv = (RecyclerView) findViewById(R.id.edit_server_usually_used_rv);
        usuallyServicesAdapter = new LifeServicesAdapter(R.layout.life_service_item);
        usuallyServicesAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (PubUtil.usuallyServers.size() == 1) {
                    showToast("至少保留一个服务");
                    return;
                }
                List<LifeServiceBean.DataBean> data = adapter.getData();
                data.remove(position);
                PubUtil.usuallyServers.remove(position);
                adapter.notifyDataSetChanged();
                allServersAdapter.notifyDataSetChanged();
            }
        });
        GridLayoutManager manager = new GridLayoutManager(this, 4);
        mEditServerUsuallyUsedRv.setLayoutManager(manager);
        mEditServerUsuallyUsedRv.setAdapter(usuallyServicesAdapter);
        mEditServerAllDataRv = (RecyclerView) findViewById(R.id.edit_server_all_data_rv);
        allServersAdapter = new EditServerAdapter(null, this);
        initRecyclerview(mEditServerAllDataRv, allServersAdapter, LinearLayoutManager.VERTICAL, false);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.edit_service_cancel_tv:
                onBackPressed();
                break;
            case R.id.edit_service_confirm_tv:
                    getPresenter().saveUsuallyUsedService(mUserInfoUtil.getUserId(), getMenuIds(), HomePageContract.SAVE_USUALLY_USED_SERVICES);
                break;
        }
    }

    /**
     * 获取菜单id
     *
     * @return
     */
    private String getMenuIds() {
        StringBuilder sb = new StringBuilder(4);
        for (int i = 0; i < PubUtil.usuallyServers.size(); i++) {
            String id = PubUtil.usuallyServers.get(i);
            if (i == PubUtil.usuallyServers.size() - 1) {
                sb.append(id);
            } else {
                sb.append(id + ",");
            }
        }
        return sb.toString().trim();
    }

    @Override
    public void onBackPressed() {
        setResult(ActivityResultManager.HOME_PAGE_REQUEST);
        finish();
        super.onBackPressed();
    }

    /**
     * 所有服务适配器的点击事件
     *
     * @param adapter
     * @param position
     */
    @Override
    public void onAllServiceItemClick(BaseQuickAdapter adapter, int position) {
        LifeServiceBean.DataBean dataBean = (LifeServiceBean.DataBean) adapter.getData().get(position);
        int menuId = dataBean.getMenuId();
        if (PubUtil.usuallyServers.contains(String.valueOf(menuId))) {
            if (PubUtil.usuallyServers.size() == 1) {
                showToast("至少保留一个服务");
                return;
            }
            List<LifeServiceBean.DataBean> data = usuallyServicesAdapter.getData();
            Iterator iterator = data.iterator();
            while (iterator.hasNext()) {
                LifeServiceBean.DataBean bean = (LifeServiceBean.DataBean) iterator.next();
                int id = bean.getMenuId();
                if (menuId == id) {
                    iterator.remove();
                }
            }
            PubUtil.usuallyServers.remove(String.valueOf(menuId));

        } else {
            if (PubUtil.usuallyServers.size() == 4) {
                showToast("最多只能添加4个常用服务");
                return;
            }
            PubUtil.usuallyServers.add(String.valueOf(menuId));
            usuallyServicesAdapter.addData(dataBean);

        }
        adapter.notifyItemChanged(position);
        //通知常用服务适配器刷新
        usuallyServicesAdapter.notifyDataSetChanged();
    }
}
