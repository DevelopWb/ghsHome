package com.ghs.ghshome.models.propertyHall.openDoorByMobile.remoteopen.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ghs.ghshome.bean.RemoteOpenLockDevBean;

import java.util.List;


/**
 * Author:wang_sir
 * Time:2018/6/29 11:16
 * Description:This is HomeAddapter  单元门 适配器
 */

public class RemoteOpenCellDoorAdapter extends BaseQuickAdapter<RemoteOpenLockDevBean.DataBean, BaseViewHolder> {
    public RemoteOpenCellDoorAdapter(int layoutResId, @Nullable List<RemoteOpenLockDevBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, RemoteOpenLockDevBean.DataBean item) {

    }

//
//    public RemoteOpenCellDoorAdapter(int layoutResId) {
//        super(layoutResId);
//    }
//
//    @Override
//    protected void convert(BaseViewHolder helper, RemoteOpenLockDevBean.DataBean.VillageLockBean item) {
//        helper.setText(R.id.remote_open_cell_door_name_tv, item.getDeviceName());
//    }
}

