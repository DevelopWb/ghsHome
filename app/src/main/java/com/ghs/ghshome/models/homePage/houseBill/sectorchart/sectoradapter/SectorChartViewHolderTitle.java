package com.ghs.ghshome.models.homePage.houseBill.sectorchart.sectoradapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.ghs.ghshome.R;

/**
 * Author:wang_sir
 * Time:2018/7/4 17:06
 * Description:This is GrowthTaskViewHolderTitle
 */
public class SectorChartViewHolderTitle extends RecyclerView.ViewHolder {
    TextView mSectorChartTitleTv;
    TextView mSectorChartTitleContentTv;

    public SectorChartViewHolderTitle(View itemView) {
        super(itemView);
        mSectorChartTitleTv = (TextView) itemView.findViewById(R.id.sector_chart_title_tv);
        mSectorChartTitleContentTv = (TextView) itemView.findViewById(R.id.sector_chart_title_content_tv);
    }
}
