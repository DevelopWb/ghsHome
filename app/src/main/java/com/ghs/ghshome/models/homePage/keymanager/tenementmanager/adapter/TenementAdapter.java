package com.ghs.ghshome.models.homePage.keymanager.tenementmanager.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ghs.ghshome.R;
import com.ghs.ghshome.bean.TenementRoomBean;
import com.ghs.ghshome.tools.Contract;
import com.ghs.ghshome.tools.glide.GlideManagerUtil;

import java.util.List;

/**
 * created by guohainan
 * created date 2019/9/18
 * application
 * package name com.ghs.ghshome.models.homePage.keymanager.tenementmanager.adapter
 */
public class TenementAdapter extends BaseQuickAdapter<TenementRoomBean.DataBean, BaseViewHolder> {

    public TenementAdapter(int layoutResId, @Nullable List<TenementRoomBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TenementRoomBean.DataBean item) {

        GlideManagerUtil.getInstance(mContext)
                .loadCirclePic(Contract.ImageBasePath + item.getHeadImage(), R.mipmap.default_user_head_icon, helper.getView(R.id.user_photo_iv));

        helper.setText(R.id.user_name_tv, item.getFullName());
        helper.setText(R.id.user_status_tv, "认证通过");
        helper.setText(R.id.user_check_way_tv,item.getCheckWay()==1?"业主认证":"物业认证");
        helper.setText(R.id.user_mark_tv, getStatus(item.getRoleType()));
    }

    /**
     * 角色 1业主 2业主家人 3租客 4租客家人 5其他
     */
    public String getStatus(int state) {
        String str = "";
        switch (state) {
            case 1:

                str = "业主";
                break;
            case 2:
                str = "业主家人";
                break;
            case 3:
                str = "租客";
                break;

            case 4:
                str = "租客家人";
                break;

            case 5:
                str = "其他";
                break;
        }
        return str;
    }
}
