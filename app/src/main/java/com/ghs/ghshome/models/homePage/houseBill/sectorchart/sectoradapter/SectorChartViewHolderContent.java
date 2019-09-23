package com.ghs.ghshome.models.homePage.houseBill.sectorchart.sectoradapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ghs.ghshome.R;

/**
 * Author:wang_sir
 * Time:2018/7/4 17:06
 * Description:This is GrowthTaskViewHolderTitle
 */
public class SectorChartViewHolderContent extends RecyclerView.ViewHolder {
    ImageView mSectorChartContentIconIv;
    TextView mSectorChartContentNameTv;
    TextView mSectorChartContentValueTv;
    public SectorChartViewHolderContent(View view) {
        super(view);
        mSectorChartContentIconIv = (ImageView) view.findViewById(R.id.sector_chart_content_icon_iv);
        mSectorChartContentNameTv = (TextView) view.findViewById(R.id.sector_chart_content_name_tv);
        mSectorChartContentValueTv = (TextView) view.findViewById(R.id.sector_chart_content_value_tv);
    }
}
