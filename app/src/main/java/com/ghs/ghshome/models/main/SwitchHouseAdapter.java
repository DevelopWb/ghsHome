package com.ghs.ghshome.models.main;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ghs.ghshome.R;
import com.ghs.ghshome.bean.UserAndRoomBean;
import com.ghs.ghshome.tools.PubUtil;

/**
 * Author:wang_sir
 * Time:2018/7/13 15:05
 * Description:This is SwitchHouseAdapter
 */
public class SwitchHouseAdapter extends BaseQuickAdapter<UserAndRoomBean.DataBean.GhsUserRoomDOBean, BaseViewHolder> {
    public SwitchHouseAdapter(int layoutResId) {
        super(layoutResId);
    }


    @Override
    protected void convert(BaseViewHolder helper, UserAndRoomBean.DataBean.GhsUserRoomDOBean item) {
        if (item.isSelected()) {
            helper.setBackgroundRes(R.id.select_village_info_cl,R.drawable.stroke_green_bg);
        }else{
            helper.setBackgroundRes(R.id.select_village_info_cl,R.drawable.stroke_gray_bg);
        }
        helper.setText(R.id.select_village_name_tv,item.getVillageName()+item.getPortionName());
        helper.setText(R.id.select_village_unit_tv,PubUtil.getRoomMsg(item));
        helper.setText(R.id.select_village_userType_tv, PubUtil.getUserType(item.getRoleType()));
        //用户状态 1.认证通过、2.认证不通过、3.待认证
        int checkStatus = item.getUserState();
        switch (checkStatus) {
            case 1:
                helper.setBackgroundRes(R.id.id_check_status_tag_iv,R.mipmap.check_pass_icon);
                helper.setText(R.id.id_check_status_tv,"认证通过");
                break;
            case 2:
                helper.setBackgroundRes(R.id.id_check_status_tag_iv,R.mipmap.check_reject_icon);
                helper.setText(R.id.id_check_status_tv,"认证不通过");
                break;
            case 3:
                helper.setBackgroundRes(R.id.id_check_status_tag_iv,R.mipmap.check_wait_icon);
                helper.setText(R.id.id_check_status_tv,"待认证");
                break;
            default:
                break;
        }
    }


}
