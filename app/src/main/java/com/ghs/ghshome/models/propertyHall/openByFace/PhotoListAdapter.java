package com.ghs.ghshome.models.propertyHall.openByFace;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ghs.ghshome.R;
import com.ghs.ghshome.bean.FacePhotoListBean;
import com.ghs.ghshome.tools.glide.GlideManagerUtil;

import java.util.List;

/**
 * created by guohainan
 * created date 2019/6/19
 * application
 * package name com.ghs.ghshome.models.propertyHall.openByFace
 */
public class PhotoListAdapter extends BaseQuickAdapter<FacePhotoListBean.DataBean, BaseViewHolder> {
    public PhotoListAdapter(int layoutResId, @Nullable List<FacePhotoListBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FacePhotoListBean.DataBean item) {
        helper.addOnClickListener(R.id.photo_tasked_item_img_del);
        GlideManagerUtil.getInstance(mContext).loadNormalPic(item.getFaceUrl(),R.drawable.default_image,(ImageView) helper.getView(R.id.photo_tasked_item_img));

    }
}
