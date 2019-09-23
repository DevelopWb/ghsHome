package com.ghs.ghshome.models.propertyHall.openByFace;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ghs.ghshome.R;
import com.ghs.ghshome.bean.FaceUfaceUserInfoBean;

import java.util.List;

/**
 * created by guohainan
 * created date 2019/6/18
 * application
 * package name com.ghs.ghshome.models.propertyHall.openByFace
 */
public class FaceRangeDoorAdapter extends BaseQuickAdapter<FaceUfaceUserInfoBean.DataBean.UfaceDeviceDOListBean, BaseViewHolder> {


    public FaceRangeDoorAdapter(int layoutResId, @Nullable List<FaceUfaceUserInfoBean.DataBean.UfaceDeviceDOListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FaceUfaceUserInfoBean.DataBean.UfaceDeviceDOListBean item) {
          helper.setText(R.id.face_door_item_doorName,item.getDeviceName());
    }
}
