package com.ghs.ghshome.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ghs.ghshome.R;
import com.ghs.ghshome.tools.PubUtil;


/**
 * created by tobato
 * created date 2019/7/9 11:42.
 * application  自定义顶部布局
 */
public class CustomActionBar extends RelativeLayout {

    private final TextView mActionBarLeftTv;
    private final TextView mActionBarRightTv;
    private final TextView mActionBarTitleTv;
    private final ImageView mActionBarLeftIv;
    private final ImageView mActionBarRightIv;
    private Context mContext;

    public CustomActionBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        LayoutInflater.from(context).inflate(R.layout.custom_action_bar_view, this, true);
        mActionBarLeftTv = (TextView) findViewById(R.id.custom_action_bar_left_tv);
        mActionBarRightTv = (TextView) findViewById(R.id.custom_action_bar_right_tv);
        mActionBarTitleTv = (TextView) findViewById(R.id.custom_action_bar_title_tv);
        mActionBarLeftIv = (ImageView) findViewById(R.id.custom_action_bar_left_iv);
        mActionBarRightIv = (ImageView) findViewById(R.id.custom_action_bar_right_iv);
        initViewStatus(context, attrs);
    }

    private void initViewStatus(Context context, AttributeSet attrs) {
        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.CustomActionBar);
        if (attributes != null) {
            //处理actionBar背景色
            Drawable titleBarBackGround = attributes.getDrawable(R.styleable.CustomActionBar_action_bar_background);
            setBackgroundDrawable(titleBarBackGround);
            //先处理左边ImageView  如果没有配置图片 控件隐藏
            int leftImageSrcId = attributes.getResourceId(R.styleable.CustomActionBar_action_bar_left_image_src, -1);
            if (-1 == leftImageSrcId) {
                //没有配置左边的图片资源
                mActionBarLeftIv.setVisibility(GONE);
            } else {
                mActionBarLeftIv.setVisibility(VISIBLE);
                int leftIvPadding = (int) attributes.getDimensionPixelSize(R.styleable.CustomActionBar_action_bar_left_image_padding, PubUtil.dip2px(context, 0));
                setPadding(PubUtil.dip2px(context, leftIvPadding), PubUtil.dip2px(context, leftIvPadding), PubUtil.dip2px(context, leftIvPadding), PubUtil.dip2px(context, leftIvPadding));

                mActionBarLeftIv.setImageResource(leftImageSrcId);
            }

//处理左侧的TextView  如果没有配置内容 控件隐藏
            String leftTextStr = attributes.getString(R.styleable.CustomActionBar_action_bar_left_text);
            if (!TextUtils.isEmpty(leftTextStr)) {
                mActionBarLeftTv.setVisibility(VISIBLE);
                mActionBarLeftTv.setText(leftTextStr);
//                //设置左边文字颜色
                int leftTextColor = attributes.getColor(R.styleable.CustomActionBar_action_bar_left_text_color, Color.BLACK);
                mActionBarLeftTv.setTextColor(leftTextColor);
                //设置左边文字的大小
                int leftTextSize = (int) attributes.getDimensionPixelSize(R.styleable.CustomActionBar_action_bar_left_text_size, PubUtil.dip2px(context, 18));
                mActionBarLeftTv.setTextSize(TypedValue.COMPLEX_UNIT_PX, leftTextSize);
            } else {
                mActionBarLeftTv.setVisibility(GONE);

            }
//处理中间的TextView  如果没有配置内容 控件隐藏
            String titleTextStr = attributes.getString(R.styleable.CustomActionBar_action_bar_title_text);
            if (!TextUtils.isEmpty(titleTextStr)) {
                mActionBarTitleTv.setVisibility(VISIBLE);
                mActionBarTitleTv.setText(titleTextStr);
//                //设置左边文字颜色
                int titleTextColor = attributes.getColor(R.styleable.CustomActionBar_action_bar_title_text_color, Color.BLACK);
                mActionBarTitleTv.setTextColor(titleTextColor);
                //设置左边文字的大小
                int titleTextSize = (int) attributes.getDimensionPixelSize(R.styleable.CustomActionBar_action_bar_title_text_size, PubUtil.dip2px(context, 18));
                mActionBarTitleTv.setTextSize(TypedValue.COMPLEX_UNIT_PX, titleTextSize);
            } else {
                mActionBarTitleTv.setVisibility(GONE);

            }
//处理右侧的TextView  如果没有配置内容 控件隐藏
            String rightTextStr = attributes.getString(R.styleable.CustomActionBar_action_bar_right_text);
            if (!TextUtils.isEmpty(rightTextStr)) {
                mActionBarRightTv.setVisibility(VISIBLE);
                mActionBarRightTv.setText(rightTextStr);
//                //设置左边文字颜色
                int rightTextColor = attributes.getColor(R.styleable.CustomActionBar_action_bar_right_text_color, Color.BLACK);
                mActionBarRightTv.setTextColor(rightTextColor);
                //设置左边文字的大小
                int rightTextSize = (int) attributes.getDimensionPixelSize(R.styleable.CustomActionBar_action_bar_right_text_size, PubUtil.dip2px(context, 18));
                mActionBarRightTv.setTextSize(TypedValue.COMPLEX_UNIT_PX, rightTextSize);
            } else {
                mActionBarRightTv.setVisibility(GONE);

            }
            //先处理最右边边ImageView  如果没有配置图片 控件隐藏
            int rightImageSrcId = attributes.getResourceId(R.styleable.CustomActionBar_action_bar_right_image_src, -1);
            if (-1 == rightImageSrcId) {
                //没有配置左边的图片资源
                mActionBarRightIv.setVisibility(GONE);
            } else {
                mActionBarRightIv.setVisibility(VISIBLE);
                int rightIvPadding = (int) attributes.getDimensionPixelSize(R.styleable.CustomActionBar_action_bar_right_image_padding, PubUtil.dip2px(context, 0));
                setPadding(PubUtil.dip2px(context, rightIvPadding), PubUtil.dip2px(context, rightIvPadding), PubUtil.dip2px(context, rightIvPadding), PubUtil.dip2px(context, rightIvPadding));
                mActionBarRightIv.setImageResource(rightImageSrcId);
            }
            attributes.recycle();
        }
    }


    /**
     * 获取左控件实例
     *
     * @return
     */
    public TextView getActionBarLeftTv() {
        return mActionBarLeftTv;
    }

    /**
     * 获取title实例
     *
     * @return
     */
    public TextView getActionBarTitleTv() {
        mActionBarTitleTv.setVisibility(VISIBLE);
        mActionBarTitleTv.setTextColor(ContextCompat.getColor(mContext, R.color.text_color));
        return mActionBarTitleTv;
    }

    /**
     * 获取左控件实例
     *
     * @return
     */
    public ImageView getActionBarLeftIv() {
        return mActionBarLeftIv;
    }

    /**
     * 获取右控件实例
     *
     * @return
     */
    public ImageView getActionBarRightIv() {
        return mActionBarRightIv;
    }


    /**
     * 获取右控件实例
     *
     * @return
     */
    public TextView getActionBarRightTv() {
        return mActionBarRightTv;
    }

    /**
     * 设置标题
     *
     * @param msg
     */
    public void setTitleText(String msg) {
        mActionBarTitleTv.setVisibility(VISIBLE);
        mActionBarTitleTv.setText(msg);
        mActionBarTitleTv.setTextColor(Color.BLACK);
    }

//    /**
//     * 设置左控件图标的大小
//     */
//    public void settitleBarLeftTvIconSize(int width, int height) {
//        ViewGroup.LayoutParams params = titleBarLeftTv.getLayoutParams();
//        params.width = width;
//        params.height = height;
//        titleBarLeftTv.setLayoutParams(params);
//    }


}