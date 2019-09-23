package com.ghs.ghshome.models.homePage.seed;

import android.support.v4.content.ContextCompat;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ghs.ghshome.R;
import com.ghs.ghshome.bean.SeedTaskBean;


/**
 * Author:wang_sir
 * Time:2018/7/23 14:11
 * Description:This is VillageNoticeAdapter
 */
public class SeedTaskAdapter extends BaseQuickAdapter<SeedTaskBean.DataBean, BaseViewHolder> {
    public static final String SEED_TASK_SIGN = "sign";//签到
    public static final String SEED_TASK_BINDCAR = "bindCar";//绑定车辆
    public static final String SEED_TASK_LOGIN = "firstLogin";//首次登陆
    public static final String SEED_TASK_GRANT_HOUSE = "grantHouse";//授权住户
    public static final String SEED_TASK_OPEN_BY_MOBILE = "mobileOpenDoor";//手机开门
    public static final String SEED_TASK_WATER_FEE = "waterFee";//缴纳税费
    public static final String SEED_TASK_AMMETER_FEE = "ammeterFee";//缴纳电费
    public static final String SEED_TASK_PROPERTY_FEE = "propertyFee";//缴纳物业费
    public static final String SEED_TASK_UPLOAD_HEAD_PIC = "uploadHead";//上传头像

    public SeedTaskAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, SeedTaskBean.DataBean item) {
        String taskType = item.getTaskType();
        String taskName = item.getTaskName();
        String taskRuleDes = item.getSeedRuleText();
        //状态 1:已完成 2：未完成
        int state = item.getState();
        helper.setText(R.id.seed_task_name_tv, taskName);
        helper.setText(R.id.seed_rule_tv, taskRuleDes);
        helper.setGone(R.id.seed_task_progress_tv,false);
        if (1==state) {
            helper.setText(R.id.seed_task_commit_tv,"已完成");
            helper.setTextColor(R.id.seed_task_commit_tv, ContextCompat.getColor(mContext,R.color.unclick_gray_deep));
        }else{
            helper.setText(R.id.seed_task_commit_tv,"进行中");
            helper.setTextColor(R.id.seed_task_commit_tv, ContextCompat.getColor(mContext,R.color.app_green));
        }
        switch (taskType) {
            case SEED_TASK_SIGN:
                helper.setGone(R.id.seed_task_progress_tv,true);
                helper.setText(R.id.seed_task_progress_tv,item.getProgress());
                if (1==state) {
                    helper.setImageResource(R.id.seed_task_item_icon,R.mipmap.seed_task_sign_gray);
                }else{
                    helper.setImageResource(R.id.seed_task_item_icon,R.mipmap.seed_task_sign_light);
                    helper.setText(R.id.seed_task_commit_tv,"签到");
                }
                break;
            case SEED_TASK_BINDCAR:
                if (1==state) {
                    helper.setImageResource(R.id.seed_task_item_icon,R.mipmap.seed_task_bind_car_gray);
                }else{
                    helper.setImageResource(R.id.seed_task_item_icon,R.mipmap.seed_task_bind_car_light);
                }
                break;
            case SEED_TASK_LOGIN:
                if (1==state) {
                    helper.setImageResource(R.id.seed_task_item_icon,R.mipmap.seed_task_login_gray);
                }else{
                    helper.setImageResource(R.id.seed_task_item_icon,R.mipmap.seed_task_login_light);
                }
                break;
            case SEED_TASK_GRANT_HOUSE:
                if (1==state) {
                    helper.setImageResource(R.id.seed_task_item_icon,R.mipmap.seed_task_regist_gray);
                }else{
                    helper.setImageResource(R.id.seed_task_item_icon,R.mipmap.seed_task_regist_light);
                }
                break;
            case SEED_TASK_OPEN_BY_MOBILE:
                helper.setGone(R.id.seed_task_progress_tv,true);
                helper.setText(R.id.seed_task_progress_tv,item.getProgress());
                if (1==state) {
                    helper.setImageResource(R.id.seed_task_item_icon,R.mipmap.seed_task_mobile_open_gray);
                }else{
                    helper.setImageResource(R.id.seed_task_item_icon,R.mipmap.seed_task_mobile_open_light);
                }
                break;
            case SEED_TASK_WATER_FEE:
                if (1==state) {
                    helper.setImageResource(R.id.seed_task_item_icon,R.mipmap.seed_task_water_fee_gray);
                }else{
                    helper.setImageResource(R.id.seed_task_item_icon,R.mipmap.seed_task_water_fee_light);
                }
                break;
            case SEED_TASK_AMMETER_FEE:
                if (1==state) {
                    helper.setImageResource(R.id.seed_task_item_icon,R.mipmap.seed_task_ammeter_gray);
                }else{
                    helper.setImageResource(R.id.seed_task_item_icon,R.mipmap.seed_task_ammeter_light);
                }
                break;
            case SEED_TASK_PROPERTY_FEE:
                if (1==state) {
                    helper.setImageResource(R.id.seed_task_item_icon,R.mipmap.seed_task_property_fee_gray);
                }else{
                    helper.setImageResource(R.id.seed_task_item_icon,R.mipmap.seed_task_property_fee_light);
                }
                break;
            case SEED_TASK_UPLOAD_HEAD_PIC:
                if (1==state) {
                    helper.setImageResource(R.id.seed_task_item_icon,R.mipmap.seed_task_upload_pic_gray);
                }else{
                    helper.setImageResource(R.id.seed_task_item_icon,R.mipmap.seed_task_upload_pic_light);
                    helper.setText(R.id.seed_task_commit_tv,"去完成");
                }
                break;
            default:

                break;
        }
    }
}
