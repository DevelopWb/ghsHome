package com.ghs.ghshome.models.mine.appSuggestion;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ghs.ghshome.R;
import com.ghs.ghshome.tools.glide.GlideManagerUtil;

/**
 * Author:wang_sir
 * Time:2018/7/19 10:52
 * Description:This is AppSuggestionAdapter
 */
public class AppSuggestionAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public AppSuggestionAdapter(int layoutResId) {
        super(layoutResId);
    }


    @Override
    protected void convert(BaseViewHolder helper, String item) {
        if ("-1".equals(item)) {
            GlideManagerUtil.getInstance(mContext).loadSquarePic(R.mipmap.mine_suggest_add,R.mipmap.mine_suggest_add,(ImageView) helper.getView(R.id.mine_sugguest_icon_iv));
        }else{
            GlideManagerUtil.getInstance(mContext).loadSquarePic(item,R.mipmap.ic_launcher,(ImageView) helper.getView(R.id.mine_sugguest_icon_iv));
        }
        helper.addOnClickListener(R.id.mine_sugguest_icon_iv);
        helper.addOnClickListener(R.id.mine_sugguest_delete_iv);
    }
}