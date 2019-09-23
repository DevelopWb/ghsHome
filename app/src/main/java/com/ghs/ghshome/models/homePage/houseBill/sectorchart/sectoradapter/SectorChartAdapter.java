package com.ghs.ghshome.models.homePage.houseBill.sectorchart.sectoradapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ghs.ghshome.R;
import com.ghs.ghshome.bean.SectorChartContentBean;
import com.ghs.ghshome.bean.SectorChartTitleBean;

import java.util.List;


/**
 * Author:wang_sir
 * Time:2018/6/29 11:16
 * Description:This is HomeAddapter
 */

public class SectorChartAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private int ITEM_TITLE = 1;
    private int ITEM_CONTENT = 2;
    private int ITEM_DIVIDER = 0;//分割线
    private List<Object> objects;
    private Context mContext;

    public void setDate(List<Object> objects) {
        this.objects = objects;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        mContext = parent.getContext();
        RecyclerView.ViewHolder holder = null;
        if (viewType == ITEM_DIVIDER) {
            view = LayoutInflater.from(mContext).inflate(R.layout.sector_chart_divider_item, parent, false);
            holder = new SectorChartViewHolderDivider(view);
        } else if (viewType == ITEM_TITLE) {
            view = LayoutInflater.from(mContext).inflate(R.layout.sector_title_item, parent, false);
            holder = new SectorChartViewHolderTitle(view);
        } else if (viewType == ITEM_CONTENT) {
            view = LayoutInflater.from(mContext).inflate(R.layout.sector_chart_content_item, parent, false);
            holder = new SectorChartViewHolderContent(view);

        }
        return holder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof SectorChartViewHolderDivider) {

        } else if (holder instanceof SectorChartViewHolderTitle) {
            SectorChartTitleBean titleBean = (SectorChartTitleBean) objects.get(position);
            ((SectorChartViewHolderTitle) holder).mSectorChartTitleTv.setText(titleBean.getTitleName());
            ((SectorChartViewHolderTitle) holder).mSectorChartTitleContentTv.setText(titleBean.getTitleNameContent());
        } else if (holder instanceof SectorChartViewHolderContent) {
            SectorChartContentBean chartContentBean = (SectorChartContentBean) objects.get(position);
            ((SectorChartViewHolderContent) holder).mSectorChartContentIconIv.setImageResource(chartContentBean.getChildMark());
            ((SectorChartViewHolderContent) holder).mSectorChartContentNameTv.setText(chartContentBean.getChildName());
            ((SectorChartViewHolderContent) holder).mSectorChartContentValueTv.setText(chartContentBean.getChildNameContent());
        }


    }

    @Override
    public int getItemViewType(int position) {
        if (objects.get(position) instanceof String) {
            return ITEM_DIVIDER;
        } else if (objects.get(position) instanceof SectorChartTitleBean) {
            return ITEM_TITLE;
        } else if (objects.get(position) instanceof SectorChartContentBean) {
            return ITEM_CONTENT;
        }
        return super.getItemViewType(position);
    }


    @Override
    public int getItemCount() {
        return objects == null ? 0 : objects.size();
    }


}

