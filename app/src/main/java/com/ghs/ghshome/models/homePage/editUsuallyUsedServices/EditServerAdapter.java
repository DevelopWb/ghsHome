package com.ghs.ghshome.models.homePage.editUsuallyUsedServices;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ghs.ghshome.R;
import com.ghs.ghshome.bean.EditServiceMultiItem;
import com.ghs.ghshome.bean.LifeServiceBean;
import com.ghs.ghshome.models.homePage.LifeServicesAdapter;
import com.ghs.ghshome.tools.PubUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Author:wang_sir
 * Time:2019/5/21 17:30
 * Description:This is EditServerAdapter
 */
public class EditServerAdapter extends BaseMultiItemQuickAdapter<EditServiceMultiItem, BaseViewHolder> {
    private OnAllServiceAdapterClickListener childClickListener;

    public EditServerAdapter(List<EditServiceMultiItem> data, OnAllServiceAdapterClickListener itemClickListener) {
        super(data);
        this.childClickListener = itemClickListener;
        addItemType(EditServiceMultiItem.EDIT_SERVER_TITLE, R.layout.edit_server_title_item);
        addItemType(EditServiceMultiItem.EDIT_SERVER_CONTENT, R.layout.edit_server_content_item);
    }


    public interface OnAllServiceAdapterClickListener {
        void onAllServiceItemClick(BaseQuickAdapter adapter, int position);
    }

    @Override
    protected void convert(BaseViewHolder helper, EditServiceMultiItem item) {
        switch (helper.getItemViewType()) {
            case EditServiceMultiItem.EDIT_SERVER_TITLE:
                String title = (String) item.getObject();
                helper.setText(R.id.server_type_title_tv, title);
                break;
            case EditServiceMultiItem.EDIT_SERVER_CONTENT:
                List<LifeServiceBean.DataBean> arrays = (List<LifeServiceBean.DataBean>) item.getObject();
                RecyclerView recyclerView = helper.getView(R.id.edit_server_content_rv);
                if (arrays != null && arrays.size() > 0) {
                    List<LifeServiceBean.DataBean> adapterData = new ArrayList<>();
                    List<String> menus = PubUtil.getPropertyHallMenus();
                    for (LifeServiceBean.DataBean array : arrays) {
                        if (menus.contains(array.getName())) {
                            adapterData.add(array);
                        }
                    }
                    LifeServicesAdapter lifeServicesAdapter = new LifeServicesAdapter(R.layout.life_service_item);
                    GridLayoutManager manager = new GridLayoutManager(mContext, 4);
                    recyclerView.setLayoutManager(manager);
                    recyclerView.setAdapter(lifeServicesAdapter);
                    lifeServicesAdapter.setNewData(adapterData);
                    lifeServicesAdapter.setOnItemClickListener(new OnItemClickListener() {
                        @Override
                        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                            if (childClickListener != null) {
                                childClickListener.onAllServiceItemClick(adapter, position);
                            }
                        }

                    });
                }
                break;
            default:
                break;
        }
    }
}
