package com.ghs.ghshome.tools;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.regex.Pattern;

/**
 * Author:wang_sir
 * Time:2018/5/18 9:57
 * Description:This is StrUtils
 */
public class StrUtils {


    private static boolean isOut;

    /**
     * 设置textView结尾...后面显示的文字和颜色
     *
     * @param context    上下文
     * @param textView   textview
     * @param minLines   最少的行数
     * @param originText 原文本
     * @param endText    结尾文字
     * @param endColorID 结尾文字颜色id
     * @param isExpand   当前是否是展开状态
     */
    public static void toggleEllipsize(final Context context,
                                       final TextView textView,
                                       final int minLines,
                                       final String originText,
                                       final String endText,
                                       final int endColorID,
                                       final boolean isExpand) {

        textView.setText(originText);
        //判定是否超过一行
        TextPaint mTextPaint = textView.getPaint();
        mTextPaint.setTextSize(textView.getTextSize());
        int mTextViewWidth = (int) mTextPaint.measureText(originText);
        if (mTextViewWidth > textView.getWidth()) {//超出一行

        } else {
            return;
        }
        textView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver
                .OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {

                if (isExpand) {
                    String text = originText + "收起";
                    SpannableString spannableString = new SpannableString(text);
                    ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.parseColor("#2367C9"));
                    spannableString.setSpan(colorSpan, spannableString.length() - 2, spannableString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                    textView.setText(spannableString);
                } else {
                    int paddingLeft = textView.getPaddingLeft();
                    int paddingRight = textView.getPaddingRight();
                    TextPaint paint = textView.getPaint();
                    float moreText = textView.getTextSize() * endText.length();
                    float availableTextWidth = (textView.getWidth() - paddingLeft - paddingRight) *
                            minLines - moreText;
                    CharSequence ellipsizeStr = TextUtils.ellipsize(originText, paint,
                            availableTextWidth, TextUtils.TruncateAt.END);
                    if (ellipsizeStr.length() < originText.length()) {
                        CharSequence temp = ellipsizeStr + endText;
                        SpannableStringBuilder ssb = new SpannableStringBuilder(temp);
                        ssb.setSpan(new ForegroundColorSpan(context.getResources().getColor
                                        (endColorID)),
                                temp.length() - endText.length(), temp.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
                        textView.setText(ssb);
                    } else {
                        textView.setText(originText);
                    }
                }
                if (Build.VERSION.SDK_INT >= 16) {
                    textView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                } else {
                    textView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                }
            }
        });
    }

    /**
     * 判断str是否为空或者是空字符串
     *
     * @param str
     * @return
     */
    public static boolean isStringValueOk(String str) {
        if (str != null && !TextUtils.isEmpty(str)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     *
     */
    public static String formatMobile(String mobile) {
        StringBuilder sb = new StringBuilder();
        if (isStringValueOk(mobile)) {
            if (PubUtil.isMobileNO(mobile)) {
                for (int i = 0; i < mobile.length(); i++) {
                    char c = mobile.charAt(i);
                    if (i >= 3 && i <= 6) {
                        sb.append('*');
                    } else {
                        sb.append(c);
                    }
                }

            }
        }
        return sb.toString();
    }

    /**
     * 验证用户名只包含字母，数字，中文,下划线
     *
     * @param account
     * @return
     */
    public static boolean checkAccountMark(String account) {
        String all = "^[a-zA-Z0-9\\u4e00-\\u9fa5\\w]+$";
        Pattern pattern = Pattern.compile(all);
        return pattern.matches(all, account);
    }

    /**
     * 判定text内容是否超出一行
     *
     * @return
     */
    public static boolean checkTextContentSize(final TextView textView, final String content) {
        isOut = false;
        textView.setText(content);
        ViewTreeObserver textViewOb = textView.getViewTreeObserver();
        textViewOb.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                TextPaint mTextPaint = textView.getPaint();
                mTextPaint.setTextSize(textView.getTextSize());
                int mTextViewWidth = (int) mTextPaint.measureText(content);
                textView.setText(content);
                if (mTextViewWidth > textView.getWidth()) {//超出一行
                    isOut = true;
                } else {
                    isOut = false;
                }
            }
        });
        return isOut;
    }

    /**
     * 格式化double数据  保留小数点后两位
     * @return
     */
    public static String formatDoubleData(double  data){
        return new DecimalFormat("0.00").format(data);
    }

    /**
     *  转换车牌格式 京A888888  京A.888888
     * @param carNum
     * @return
     */
    public static String  initCarNumStatus(String  carNum){
        StringBuilder retrunStr = new StringBuilder();
        if (isStringValueOk(carNum)) {
            if (carNum.length()>6) {
                retrunStr.append(carNum.substring(0,2));
                retrunStr.append("·");
                retrunStr.append(carNum.substring(2,carNum.length()));

            }
        }
        return retrunStr.toString();
    }

    /**
     * 设置文字部分颜色
     * @return
     */
    public  static void setTextPartColor(TextView textView,String  content,int startIndex,int endIndex,String  textColor){
        SpannableString spannableString = null;
        if (StrUtils.isStringValueOk(content)) {
             spannableString = new SpannableString(content);
            ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.parseColor(textColor));
            spannableString.setSpan(colorSpan, startIndex, endIndex, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            textView.setText(spannableString);
        }
    }

}
