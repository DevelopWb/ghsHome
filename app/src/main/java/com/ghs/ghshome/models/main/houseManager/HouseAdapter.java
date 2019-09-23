package com.ghs.ghshome.models.main.houseManager;

import android.support.v4.content.ContextCompat;

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
public class HouseAdapter extends BaseQuickAdapter<UserAndRoomBean.DataBean.GhsUserRoomDOBean, BaseViewHolder> {
    public HouseAdapter(int layoutResId) {
        super(layoutResId);
    }


    @Override
    protected void convert(BaseViewHolder helper, UserAndRoomBean.DataBean.GhsUserRoomDOBean item) {
        helper.setBackgroundRes(R.id.select_village_info_cl, 0);
        PubUtil.margin(helper.getView(R.id.select_village_info_cl),0,0,0,0);
        helper.setText(R.id.select_village_name_tv, item.getVillageName() + item.getPortionName());
        helper.setText(R.id.select_village_unit_tv,PubUtil.getRoomMsg(item));
        helper.setText(R.id.select_village_userType_tv, PubUtil.getUserType(item.getRoleType()));
        //用户状态 1.认证通过、2.认证不通过、3.待认证
        int checkStatus = item.getUserState();
        helper.setTextColor(R.id.house_manager_bt_tv, ContextCompat.getColor(mContext,R.color.gray_deeper));
        helper.addOnClickListener(R.id.house_manager_bt_tv);
        switch (checkStatus) {
            case 1:
                helper.setBackgroundRes(R.id.id_check_status_tag_iv,R.mipmap.check_pass_icon);
                helper.setText(R.id.id_check_status_tv,"认证通过");
                helper.setText(R.id.house_manager_bt_tv,"退出房间");
                helper.setTextColor(R.id.house_manager_bt_tv, ContextCompat.getColor(mContext,R.color.text_red));
                break;
            case 2:
                helper.setBackgroundRes(R.id.id_check_status_tag_iv,R.mipmap.check_reject_icon);
                helper.setText(R.id.id_check_status_tv,"认证不通过");
                helper.setText(R.id.house_manager_bt_tv,"删除");
                helper.setTextColor(R.id.house_manager_bt_tv, ContextCompat.getColor(mContext,R.color.text_red));
                break;
            case 3:
                helper.setBackgroundRes(R.id.id_check_status_tag_iv,R.mipmap.check_wait_icon);
                helper.setText(R.id.id_check_status_tv,"待认证");
                helper.setText(R.id.house_manager_bt_tv,"可联系物业或房屋业主进行催办");
                helper.setTextColor(R.id.house_manager_bt_tv, ContextCompat.getColor(mContext,R.color.gray_deeper));
                break;
            default:
                break;
        }
    }


}
