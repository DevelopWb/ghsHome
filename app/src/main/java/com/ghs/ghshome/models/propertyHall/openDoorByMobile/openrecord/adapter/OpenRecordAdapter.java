package com.ghs.ghshome.models.propertyHall.openDoorByMobile.openrecord.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ghs.ghshome.R;
import com.ghs.ghshome.bean.OpenRecordBean;
import com.ghs.ghshome.tools.Contract;
import com.ghs.ghshome.tools.StrUtils;
import com.ghs.ghshome.tools.UserInfoUtil;
import com.ghs.ghshome.tools.glide.GlideManagerUtil;

import java.util.List;


/**
 * Author:wang_sir
 * Time:2018/6/29 11:16
 * Description:This is HomeAddapter
 */

public class OpenRecordAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private int ITEM_TITLE = 1;
    private int ITEM_CONTENT = 2;
    private List<Object> objects;
    private Context mContext;


    public void setDate(List<Object> objects) {
        this.objects = objects;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        mContext = parent.getContext();
        RecyclerView.ViewHolder holder = null;
        if (viewType == ITEM_TITLE) {
            view = LayoutInflater.from(mContext).inflate(R.layout.open_record_title, parent, false);
            holder = new ViewHolderTitle(view);
        } else if (viewType == ITEM_CONTENT) {
            view = LayoutInflater.from(mContext).inflate(R.layout.open_record_content, parent, false);
            holder = new ViewHolderContent(view);

        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolderTitle) {

            String title = (String) objects.get(position);
            ((ViewHolderTitle) holder).mOpenRecordDateTv.setText(title);
        } else if (holder instanceof ViewHolderContent) {
            OpenRecordBean.DataBean.LogDOListBean bean = (OpenRecordBean.DataBean.LogDOListBean) objects.get(position);
            if (UserInfoUtil.getInstance().getUserId()==bean.getGhsUserId()) {
                ((ViewHolderContent) holder).mOpenRecordMarkIv.setImageResource(R.drawable.open_record_circle_shape);
            }else{
                ((ViewHolderContent) holder).mOpenRecordMarkIv.setImageResource(R.drawable.open_record_circle_green_shape);

            }
            if (StrUtils.isStringValueOk(bean.getUpdateTime())) {
                String time = bean.getUpdateTime().substring(10, bean.getUpdateTime().length()).trim();
                ((ViewHolderContent) holder).mOpenRecordTimeTv.setText(time);
            }


            ((ViewHolderContent) holder).mOpenRecordDoorNOTv.setText(bean.getDeviceName());
            ((ViewHolderContent) holder).mOpenRecordNameTv.setText(bean.getFullName());
            ((ViewHolderContent) holder).mOpenRecordTerminalTv.setText(getOpenType(bean.getOpenType()));
            String headImagePath = bean.getHeadImage();
            if (StrUtils.isStringValueOk(headImagePath)) {
                GlideManagerUtil.getInstance(mContext)
                        .loadCirclePic(Contract.ImageBasePath + headImagePath, R.mipmap.default_user_head_icon, ((ViewHolderContent) holder).mOpenRecordHeadIv);
            }
        }

    }

    /**
     * 获取开门类型
     *
     * @param openType 1：蓝牙开门 2：网络开门 3：ic卡开门 4：密码开门
     * @return
     */
    private String getOpenType(int openType) {
        String openType_str = "";
        switch (openType) {
            case 1:
                openType_str = "蓝牙开门";
                break;
            case 2:
                openType_str = "网络开门";
                break;
            case 3:
                openType_str = "ic卡开门";
                break;
            case 4:
                openType_str = "密码开门";
                break;
            default:
                break;
        }
        return openType_str;
    }

    @Override
    public int getItemViewType(int position) {
        if (objects.get(position) instanceof String) {
            return ITEM_TITLE;
        } else if (objects.get(position) instanceof OpenRecordBean.DataBean.LogDOListBean) {
            return ITEM_CONTENT;
        }
        return super.getItemViewType(position);
    }


    @Override
    public int getItemCount() {
        return objects == null ? 0 : objects.size();
    }


}

