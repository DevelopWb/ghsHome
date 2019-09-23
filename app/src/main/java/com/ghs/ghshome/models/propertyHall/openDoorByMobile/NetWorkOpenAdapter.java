package com.ghs.ghshome.models.propertyHall.openDoorByMobile;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ghs.ghshome.R;
import com.ghs.ghshome.bean.RemoteOpenLockDevBean;

import java.util.List;

public class NetWorkOpenAdapter extends BaseQuickAdapter<RemoteOpenLockDevBean.DataBean, BaseViewHolder> {


    public NetWorkOpenAdapter(int layoutResId, @Nullable List<RemoteOpenLockDevBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, RemoteOpenLockDevBean.DataBean item) {
        helper.setText(R.id.netwok_item_text, item.getDeviceName());

    }
}
