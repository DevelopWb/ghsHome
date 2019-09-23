package com.ghs.ghshome.base.displayPhotos;

import android.support.constraint.ConstraintLayout;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ghs.ghshome.R;
import com.ghs.ghshome.tools.PubUtil;
import com.ghs.ghshome.tools.glide.GlideManagerUtil;

/**
 * Author:wang_sir
 * Time:2018/7/19 10:52
 * Description:This is AppSuggestionAdapter
 */
public class ShowSelectedPicsAdapter extends BaseQuickAdapter<String, BaseViewHolder> {


    private int widthAndHeigh = 60;

    public void setWidthAndHeigh(int widthAndHeigh) {
    this.widthAndHeigh = widthAndHeigh;
    }

    public ShowSelectedPicsAdapter(int layoutResId) {
        super(layoutResId);
    }


    @Override
    protected void convert(BaseViewHolder helper, String item) {
        if ("-1".equals(item)) {
            GlideManagerUtil.getInstance(mContext).loadSquarePic(R.mipmap.mine_suggest_add,R.mipmap.mine_suggest_add,(ImageView) helper.getView(R.id.mine_sugguest_icon_iv));
        } else {
            GlideManagerUtil.getInstance(mContext).loadSquarePic(item,R.mipmap.ic_launcher,(ImageView) helper.getView(R.id.mine_sugguest_icon_iv));
        }
        helper.addOnClickListener(R.id.mine_sugguest_icon_iv);
        helper.addOnClickListener(R.id.mine_sugguest_delete_iv);
        ImageView imageView = helper.getView(R.id.mine_sugguest_icon_iv);
        ConstraintLayout.LayoutParams linearParams = (ConstraintLayout.LayoutParams) imageView.getLayoutParams(); //取控件textView当前的布局参数 linearParams.height = 20;// 控件的高强制设成20
        linearParams.width = PubUtil.dip2px(mContext, widthAndHeigh);// 控件的宽强制设成30
        linearParams.height = PubUtil.dip2px(mContext, widthAndHeigh);// 控件的高强制设成30

        imageView.setLayoutParams(linearParams); //使设置好的布局参数应用到控件
    }
}