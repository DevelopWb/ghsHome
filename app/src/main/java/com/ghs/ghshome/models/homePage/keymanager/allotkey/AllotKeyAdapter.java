package com.ghs.ghshome.models.homePage.keymanager.allotkey;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ghs.ghshome.R;
import com.ghs.ghshome.bean.AllotKeyBean;
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

public class AllotKeyAdapter extends RecyclerView.Adapter<AllotKeyAdapter.ViewHolder> {

    private AllotKeyItemClickListener listener;
    private List<AllotKeyBean.DataBean> arrays;
    private Context mContext;
    private boolean show = false;//显示编辑布局

    public void setOnItemClickListener(AllotKeyItemClickListener listener) {
        this.listener = listener;
    }

    /**
     * 适配数据
     * @param allotKeyBean
     */
    public void setDate(AllotKeyBean allotKeyBean) {
        this.arrays = allotKeyBean.getData();
        notifyDataSetChanged();
    }
    /**
     *
     * 初始化布局
     */
    public void initLinearLayout(boolean show) {
        this.show = show;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(mContext).inflate(R.layout.allot_key_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final AllotKeyBean.DataBean bean = arrays.get(position);
        final int roleType = bean.getRoleType();
        String headImagePath = bean.getHeadImage();
        if (show) {
            holder.mAllotKeyEditLl.setVisibility(View.VISIBLE);
        }else{
            holder.mAllotKeyEditLl.setVisibility(View.GONE);
        }
        if (StrUtils.isStringValueOk(headImagePath)) {
            GlideManagerUtil.getInstance(mContext)
                    .loadCirclePic(Contract.ImageBasePath +headImagePath, R.mipmap.default_user_head_icon, holder.mUserPhotoIv);
        }
        holder.mUserNameTv.setText(bean.getFullName());
        setUserStatus(holder, bean);
        setUserMark(holder, roleType);
        int keynum = bean.getLeftKeyNum();
        holder.mKeyAccountTv.setText("x" + keynum);
        holder.allot_key_user_info_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.mAllotKeyEditLl.isShown()) {
                    holder.mAllotKeyEditLl.setVisibility(View.GONE);
                    return;
                } else {
                    if (roleType != 1) {
                        if (roleType == UserInfoUtil.getInstance().getRoleType()) {
                            holder.mAllotKeyEditLl.setVisibility(View.GONE);
                        } else {
                            holder.mAllotKeyEditLl.setVisibility(View.VISIBLE);
                        }
                        if (4 == UserInfoUtil.getInstance().getRoleType()) {
                            if (roleType == 3) {
                                holder.mAllotKeyEditLl.setVisibility(View.GONE);
                            }
                        }
                        if (roleType == 2 || roleType == 4) {//业主家人或者租户家人
                            holder.mAllotKeyLl.setVisibility(View.GONE);
                        } else if (roleType == 3) {//租户
                            holder.mAllotKeyLl.setVisibility(View.VISIBLE);
                        }
                    }

                }
            }
        });
        holder.mReleaseBoundTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.releaseBound(bean, holder.mAllotKeyEditLl);
                }
            }
        });
        holder.mAllotKeyEditLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.modifyKeyAmount(bean, holder.mAllotKeyEditLl);
                }
            }
        });
    }

    /**
     * 设置用户的状态
     *
     * @param holder
     * @param bean   1正常 2停用 3删除
     */
    private void setUserStatus(ViewHolder holder, AllotKeyBean.DataBean bean) {

        int userState = bean.getUserState();
        String content = "";
        switch (userState) {
            case 1:
                content = "已授权";
                break;
            case 2:
                content = "";
                break;
            default:
                break;
        }

        holder.mUserStatusTv.setText(content);
    }

    /**
     * 设置用户对应的标识
     */
    private void setUserMark(ViewHolder holder, int roleType) {

        int drawble = -1;
        String role = "";
        switch (roleType) {
            case 1:
                drawble = R.mipmap.master_mark_icon;
                role = "业主";
                break;
            case 2:
                drawble = R.mipmap.custom_mark_icon;
                role = "业主家人";
                break;
            case 3:
                drawble = R.mipmap.custom_mark_icon;
                role = "租客";
                break;
            case 4:
                drawble = R.mipmap.custom_mark_icon;
                role = "租客家人";
                break;
            default:
                break;
        }

//        holder.user_mark_tv.setBackgroundResource(drawble);
        holder.user_mark_tv.setText(role);
    }


    @Override
    public int getItemCount() {

        return arrays == null ? 0 : arrays.size();
    }

    public interface AllotKeyItemClickListener {

        void releaseBound(AllotKeyBean.DataBean beans, LinearLayout linearLayout);//解除绑定

        void modifyKeyAmount(AllotKeyBean.DataBean beans, LinearLayout linearLayout);//钥匙变更

    }

    static

    public class ViewHolder extends RecyclerView.ViewHolder {
        View view;
        ImageView mUserPhotoIv;
        TextView user_mark_tv;
        TextView mUserNameTv;
        TextView mUserStatusTv;
        TextView mKeyAccountTv;
        LinearLayout mKeyAccountLl;
        TextView mReleaseBoundTv;
        LinearLayout mAllotKeyLl;
        LinearLayout mAllotKeyEditLl;
        LinearLayout allot_key_user_info_ll;

        ViewHolder(View view) {
            super(view);
            this.view = view;
            mUserPhotoIv = (ImageView) view.findViewById(R.id.user_photo_iv);
            user_mark_tv = (TextView) view.findViewById(R.id.user_mark_tv);
            mUserNameTv = (TextView) view.findViewById(R.id.user_name_tv);
            mUserStatusTv = (TextView) view.findViewById(R.id.user_status_tv);
            mKeyAccountTv = (TextView) view.findViewById(R.id.key_account_tv);
            mKeyAccountLl = (LinearLayout) view.findViewById(R.id.key_account_ll);
            mReleaseBoundTv = (TextView) view.findViewById(R.id.release_bound_tv);

            mAllotKeyLl = (LinearLayout) view.findViewById(R.id.allot_key_ll);
            mAllotKeyEditLl = (LinearLayout) view.findViewById(R.id.allot_key_edit_ll);

            allot_key_user_info_ll = (LinearLayout) view.findViewById(R.id.allot_key_user_info_ll);
        }
    }

}

