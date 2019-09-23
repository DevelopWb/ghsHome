package com.ghs.ghshome.models.discover.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ghs.ghshome.R;
import com.ghs.ghshome.bean.CommunityListBean;
import com.ghs.ghshome.tools.Contract;
import com.ghs.ghshome.tools.glide.GlideManagerUtil;

import java.util.List;

public class CommunityListAdapter extends BaseQuickAdapter<CommunityListBean.DataBean,BaseViewHolder> {

    public CommunityListAdapter(int layoutResId, @Nullable List<CommunityListBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CommunityListBean.DataBean item) {
        GlideManagerUtil.getInstance(mContext).loadNormalPic(Contract.ImageBasePath + item.getImage(),R.drawable.default_image,(ImageView) helper.getView(R.id.community_image));
        helper.setText(R.id.community_title,item.getTitle());
        helper.setText(R.id.community_time,item.getStartTime()+"至"+item.getEndTime());
        helper.setText(R.id.community_address,item.getAddress()== null?"暂无地址哦！":item.getAddress());
        helper.setText(R.id.community_state,item.getFreeFlag()==0?"付费":"免费");

    }
}
