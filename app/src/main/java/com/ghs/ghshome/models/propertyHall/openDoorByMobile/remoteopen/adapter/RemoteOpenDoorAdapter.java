package com.ghs.ghshome.models.propertyHall.openDoorByMobile.remoteopen.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ghs.ghshome.R;
import com.ghs.ghshome.bean.RemoteOpenLockDevBean;

import java.util.List;


/**
 * Author:wang_sir
 * Time:2018/6/29 11:16
 * Description:This is HomeAddapter
 */

public class RemoteOpenDoorAdapter extends BaseQuickAdapter<RemoteOpenLockDevBean.DataBean,BaseViewHolder> {
    public RemoteOpenDoorAdapter(int layoutResId, @Nullable List<RemoteOpenLockDevBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, RemoteOpenLockDevBean.DataBean item) {
        helper.setText(R.id.netwok_item_text, item.getDeviceName());
    }


//    public RemoteOpenDoorAdapter(int layoutResId) {
//        super(layoutResId);
//    }
//
//    @Override
//    protected void convert(BaseViewHolder helper, RemoteOpenLockDevBean.DataBean.VillageLockBean item) {
//        helper.setText(R.id.remote_open_door_name_tv, item.getDeviceName());
//
//    }
}

