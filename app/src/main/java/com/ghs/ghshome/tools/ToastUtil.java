package com.ghs.ghshome.tools;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ghs.ghshome.R;

/**
 * Author:wang_sir
 * Time:2018/8/10 15:00
 * Description:This is ToastUtil
 */
public class ToastUtil {

    /**
     * Toast对象
     */
    private static Toast mToast = null;
    private static Toast mSeedToast = null;



//    /**
//     * 将Toast封装在一个方法中，在其它地方使用时直接输入要弹出的内容即可
//     */
//
//
//    public static void showToast(Context context, String messages) {
//        View view = LayoutInflater.from(context).inflate(R.layout.custom_toast, null);
//        TextView content = (TextView) view.findViewById(R.id.custom_toast_message_tv);
//        if (mToast == null) {
//            mToast = new Toast(context);
//        }
//        content.setText(messages);
//        mToast.setGravity(Gravity.CENTER, 12, 20);//setGravity用来设置Toast显示的位置，相当于xml中的android:gravity或android:layout_gravity
//        mToast.setDuration(Toast.LENGTH_SHORT);//setDuration方法：设置持续时间，以毫秒为单位。该方法是设置补间动画时间长度的主要方法
//        mToast.setView(view); //添加视图文件
//        mToast.show();
//    }

    /**
     * 获取种子的弹框
     */
    public  static void showTakeSeedToast(Context context, String seedAmount, String seedTaskName) {
        context = context.getApplicationContext();
        View view = LayoutInflater.from(context).inflate(R.layout.take_seed_toast, null);
        TextView seedAmountTv = (TextView) view.findViewById(R.id.taked_seed_amount_tv);
        TextView seedTaskNameTv = (TextView) view.findViewById(R.id.seed_task_name_tv);
        if (mSeedToast == null) {
            mSeedToast = new Toast(context);
        }
        seedAmountTv.setText("+" + seedAmount);
        seedTaskNameTv.setText(seedTaskName);
        mSeedToast.setGravity(Gravity.CENTER, 12, 20);//setGravity用来设置Toast显示的位置，相当于xml中的android:gravity或android:layout_gravity
        mSeedToast.setDuration(Toast.LENGTH_LONG);//setDuration方法：设置持续时间，以毫秒为单位。该方法是设置补间动画时间长度的主要方法
        mSeedToast.setView(view); //添加视图文件
        mSeedToast.show();
    }

    /**
     * 普通展示
     *
     * @param msg
     */
    public static void showNormalToast(Context context, int duration, String msg) {
        context = context.getApplicationContext();
        if (mToast == null) {
            mToast = Toast.makeText(context, msg, duration);
        } else {
            mToast.setText(msg);
            mToast.setDuration(duration);
        }

        mToast.setGravity(Gravity.BOTTOM, 0, PubUtil.dip2px(context, 100));
        mToast.show();
    }


}


