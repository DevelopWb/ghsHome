package com.ghs.ghshome.models.homePage;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ghs.ghshome.R;
import com.ghs.ghshome.bean.LifeServiceBean;
import com.ghs.ghshome.tools.PubUtil;
import com.ghs.ghshome.tools.StrUtils;

/**
 * Author:wang_sir
 * Time:2019/5/20 10:37
 * Description:This is 所有的服务 生活类
 */
public class LifeServicesAdapter extends BaseQuickAdapter<LifeServiceBean.DataBean, BaseViewHolder> {
    public LifeServicesAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, LifeServiceBean.DataBean item) {
        String name = item.getName();
        int  menuId = item.getMenuId();
        helper.setText(R.id.life_service_title_tv,name);
        int editType = item.getEditType();
        if (0==editType) {
            helper.setVisible(R.id.life_service_edit_tag_iv,false);
        }else{
            helper.setVisible(R.id.life_service_edit_tag_iv,true);
            if (PubUtil.usuallyServers.contains(String.valueOf(menuId))) {
                helper.setImageResource(R.id.life_service_edit_tag_iv,R.mipmap.edit_service_remove);
            }else{
                helper.setImageResource(R.id.life_service_edit_tag_iv,R.mipmap.edit_service_add);

            }


        }
        if (StrUtils.isStringValueOk(name)) {

            switch (name) {
                case "小区公告":
                    helper.setImageResource(R.id.life_service_icon_iv,R.mipmap.services_property_announcement);
                    break;
                case "报修":
                    helper.setImageResource(R.id.life_service_icon_iv,R.mipmap.services_repair);
                    break;
                case "投诉建议":
                    helper.setImageResource(R.id.life_service_icon_iv,R.mipmap.services_suggestion);
                    break;
                case "房屋账单":
                    helper.setImageResource(R.id.life_service_icon_iv,R.mipmap.services_bill);
                    break;
                case "手机开门":
                    helper.setImageResource(R.id.life_service_icon_iv,R.mipmap.services_open_door);
                    break;
                case "邀请访客":
                    helper.setImageResource(R.id.life_service_icon_iv,R.mipmap.services_visitor);
                    break;
                case "物品放行":
                    helper.setImageResource(R.id.life_service_icon_iv,R.mipmap.services_goods_release);
                    break;
                case "装修备案":
                    helper.setImageResource(R.id.life_service_icon_iv,R.mipmap.service_decoration);
                    break;
                case "人脸开门":
                    helper.setImageResource(R.id.life_service_icon_iv,R.mipmap.services_human_face_icon);
                    break;
                case "授权管理":
                    helper.setImageResource(R.id.life_service_icon_iv,R.mipmap.services_key_authorize);
                    break;
                case "车辆车位":
                    helper.setImageResource(R.id.life_service_icon_iv,R.mipmap.carpark_icon);
                    break;
                default:
                    break;
            }
        }

    }
}
