package com.ghs.ghshome.models.propertyHall.openDoorByMobile.openrecord.adapter;

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
public class ViewHolderContent extends RecyclerView.ViewHolder {
    ImageView mOpenRecordMarkIv;
    ImageView mOpenRecordHeadIv;
    TextView mOpenRecordTimeTv;
    TextView mOpenRecordTerminalTv;
    TextView mOpenRecordNameTv;
    TextView mOpenRecordDoorNOTv;
    public ViewHolderContent(View view) {
        super(view);
        mOpenRecordMarkIv = (ImageView) view.findViewById(R.id.open_record_mark_iv);
        mOpenRecordHeadIv = (ImageView) view.findViewById(R.id.open_record_head_iv);
        mOpenRecordTimeTv = (TextView) view.findViewById(R.id.open_record_time_tv);
        mOpenRecordTerminalTv = (TextView) view.findViewById(R.id.open_record_terminal_tv);
        mOpenRecordNameTv = (TextView) view.findViewById(R.id.open_record_name_tv);
        mOpenRecordDoorNOTv = (TextView) view.findViewById(R.id.open_record_door_NO_tv);

    }
}
