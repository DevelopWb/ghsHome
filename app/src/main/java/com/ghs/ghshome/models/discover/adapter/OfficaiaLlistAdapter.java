package com.ghs.ghshome.models.discover.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ghs.ghshome.R;
import com.ghs.ghshome.bean.OfficialListBean;
import com.ghs.ghshome.tools.Contract;
import com.ghs.ghshome.tools.glide.GlideManagerUtil;

import java.util.List;
/**
 * created by tobato
 * created date 2019/7/31 15:02.
 * application  发现模块 官方活动列表的适配器
 */
public class OfficaiaLlistAdapter extends BaseQuickAdapter<OfficialListBean.DataBean, BaseViewHolder> {

    public OfficaiaLlistAdapter(int layoutResId, @Nullable List<OfficialListBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, OfficialListBean.DataBean item) {
        GlideManagerUtil.getInstance(mContext).loadNormalPic(Contract.ImageBasePath + item.getImage(),R.drawable.default_image,(ImageView) helper.getView(R.id.officaial_image));
        helper.setText(R.id.officaial_title, item.getTitle());
        helper.setText(R.id.officaial_content, item.getIntroduction());

    }
}
