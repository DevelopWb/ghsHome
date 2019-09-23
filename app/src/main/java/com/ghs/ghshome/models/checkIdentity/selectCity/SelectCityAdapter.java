package com.ghs.ghshome.models.checkIdentity.selectCity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ghs.ghshome.R;
import com.ghs.ghshome.bean.CitiesBean;
import com.ghs.ghshome.bean.MultiItemBean;
import com.ghs.ghshome.tools.PubUtil;

import java.util.List;

/**
 * Author:wang_sir
 * Time:2019/5/30 15:12
 * Description:This is 选择城市
 */
public class SelectCityAdapter extends BaseMultiItemQuickAdapter<MultiItemBean, BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */

    private OnCityClickCallBack cityClickCallBack;

    public SelectCityAdapter(List<MultiItemBean> data, OnCityClickCallBack cityClickCallBack) {
        super(data);
        addItemType(MultiItemBean.SELECT_CITY_DIVIDER, R.layout.divider_deep_gray);
        addItemType(MultiItemBean.SELECT_CITY_CONTENT, R.layout.simple_rv_item);
        this.cityClickCallBack = cityClickCallBack;
    }

    public interface OnCityClickCallBack {
        void cityClicked(String cityName);
    }

    @Override
    protected void convert(BaseViewHolder helper, MultiItemBean item) {
        switch (helper.getItemViewType()) {
            case MultiItemBean.SELECT_CITY_DIVIDER:
                String tag = (String) item.getObject();
                helper.setText(R.id.public_divider_tv, tag);
                helper.setBackgroundRes(R.id.public_divider_tv, R.drawable.bg_gray_only_bottom_deeper_gray_shape_1px);
                TextView tagTv = helper.getView(R.id.public_divider_tv);
                tagTv.setPadding(PubUtil.dip2px(mContext, 20), PubUtil.dip2px(mContext, 5), PubUtil.dip2px(mContext, 20), PubUtil.dip2px(mContext, 5));
                break;
            case MultiItemBean.SELECT_CITY_CONTENT:
                List<CitiesBean.DataBean.CityListBean> content = (List<CitiesBean.DataBean.CityListBean>) item.getObject();
                RecyclerView recyclerView = helper.getView(R.id.simple_rv_item_rv);
//                recyclerView.setPadding(PubUtil.dip2px(mContext,20),0,0,0);
                CitiesAdapter adapter = new CitiesAdapter(R.layout.simple_text_item);
                LinearLayoutManager manager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
                recyclerView.setLayoutManager(manager);
                recyclerView.setAdapter(adapter);
                adapter.setNewData(content);
                adapter.setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        CitiesBean.DataBean.CityListBean  city = (CitiesBean.DataBean.CityListBean) adapter.getData().get(position);
                        if (cityClickCallBack != null) {
                            cityClickCallBack.cityClicked(city.getCityName());
                        }
                    }
                });
                break;
            default:
                break;
        }

    }

}
