package com.ghs.ghshome.models.propertyHall.openDoorByMobile.openrecord.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.ghs.ghshome.R;

/**
 * Author:wang_sir
 * Time:2018/7/4 17:06
 * Description:This is GrowthTaskViewHolderTitle
 */
public class ViewHolderTitle extends RecyclerView.ViewHolder {
     TextView mOpenRecordDateTv;
    public ViewHolderTitle(View itemView) {
        super(itemView);
        mOpenRecordDateTv = (TextView) itemView.findViewById(R.id.open_record_date_tv);

    }
}
