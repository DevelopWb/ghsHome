package com.ghs.ghshome.models.propertyHall.article.ArticleAdapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ghs.ghshome.R;
import com.ghs.ghshome.tools.Contract;
import com.ghs.ghshome.tools.glide.GlideManagerUtil;

import java.util.List;

public class ImagesAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public ImagesAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

        ImageView illegally_item_image = helper.getView(R.id.property_item_img);
        GlideManagerUtil.getInstance(mContext).loadSquarePic(Contract.ImageBasePath + item,R.mipmap.splash_shadow,illegally_item_image);
        helper.addOnClickListener(R.id.property_item_img);


    }
}
