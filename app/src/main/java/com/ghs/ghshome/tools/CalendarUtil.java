package com.ghs.ghshome.tools;

import android.support.annotation.NonNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by ${王sir} on 2017/11/14.
 * application
 */

public class CalendarUtil {

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    @NonNull
    private static Calendar getCalendar() {
        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.HOUR_OF_DAY, 0);
        ca.clear(Calendar.MINUTE);
        ca.clear(Calendar.SECOND);
        ca.clear(Calendar.MILLISECOND);
        return ca;
    }

    /**
     * 获取对应格式的时间
     *
     * @param date
     * @return
     */
    public static String getTimeFromDate(String format, Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }


    /**
     * 获取当前的时间
     *
     * @return
     */
    public static String getCurrentTime(String str) {
        SimpleDateFormat sdf = new SimpleDateFormat(str);
        Calendar ca = Calendar.getInstance();
        return sdf.format(ca.getTime());

    }

    /**
     * 获取当天12点的时间
     *
     * @return
     */
    public static String getTimeMidOfDay() {
        Calendar ca = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(ca.getTime()) + " " + "12:00:00";

    }

//    /**
//     * 获取0点的时间
//     *
//     * @return yyyy-MM-dd 00:00:00
//     * @time yyyy-MM-dd **:**:**
//     */
//    public static String getZeroTime(String time) {
//        String time_return = "";
//        SimpleDateFormat sdf_a = new SimpleDateFormat("yyyy-MM-dd");
//        try {
//            Date date = sdf.parse(time);
//            time_return = sdf_a.format(date) + " 00:00:00";
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        return time_return;
//    }

//    /**
//     * 获取本周的第一天||上周的最后一天
//     *
//     * @return
//     */
//    public static String getTimeOfWeekStart() {
//
//        Calendar ca = getCalendar();
//        ca.set(Calendar.DAY_OF_WEEK, ca.getFirstDayOfWeek() + 1);
//        String time = sdf.format(ca.getTime());
//        return time;
//    }

//    /**
//     * 获取本周的最后一天
//     *
//     * @return
//     */
//    public static String getTimeOfWeekEnd() {
//        Calendar ca = getCalendar();
//        ca.set(Calendar.DAY_OF_WEEK, ca.getFirstDayOfWeek() + 1);
//        ca.add(Calendar.DAY_OF_WEEK, 7);
//        String time = sdf.format(ca.getTime());
//        return time;
//    }

//    /**
//     * 获取上周的第一天
//     *
//     * @return
//     */
//    public static String getTimeOfLastWeekStart() {
//
//        Calendar ca = getCalendar();
//        ca.set(Calendar.DAY_OF_WEEK, ca.getFirstDayOfWeek() + 1);
//        ca.add(Calendar.DAY_OF_MONTH, -7);
//        String time = sdf.format(ca.getTime());
//        return time;
//    }

    /**
     * 获取开始时间 month:-11代表最近一年的开始年月，-5代表最近半年，-2代表最近3个月 0代表最近一个月
     *
     * @return
     */
    public static String getLastDateOfMonth(int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, month);
        Date date = calendar.getTime();
        String time = new SimpleDateFormat("yyyy-MM").format(date);
        return time;
    }

    /**
     * 获取开始时间 month:-11代表最近一年的开始年月，-5代表最近半年，-2代表最近3个月 0代表最近一个月
     *
     * @return
     */
    public static String getLastDateOfMonth(int month, String format) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, month);
        Date date = calendar.getTime();
        String time = new SimpleDateFormat(format).format(date);
        return time;
    }

    /**
     * 获取下次提醒的时间,day天后
     */
    public static String getNextWarnTime(int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, day);
        Date date = calendar.getTime();
        String time = new SimpleDateFormat("yyyy-MM-dd").format(date);
        return time;
    }

    /**
     * 通过日期获取对应的星期
     *
     * @param time yyyy年MM月dd日
     * @return
     */
    public static String GetWeekFromDate(String time) {

        Calendar cal = Calendar.getInstance();

        int i = -1;

// 对 calendar 设置时间的方法

// 设置传入的时间格式

        try {

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日");

// 指定一个日期

            Date date;

            date = dateFormat.parse(time);

            cal.setTime(date);

            i = cal.get(Calendar.DAY_OF_WEEK);

        } catch (ParseException e) {


            e.printStackTrace();

        }
        String week = "";
        if (i == 1) {
            week = "星期日";
        }
        if (i == 2) {
            week = "星期一";
        }
        if (i == 3) {
            week = "星期二";
        }
        if (i == 4) {
            week = "星期三";
        }
        if (i == 5) {
            week = "星期四";
        }
        if (i == 6) {
            week = "星期五";
        }
        if (i == 7) {
            week = "星期六";
        }
        return week;
    }


    /**
     * 比较两个时间串的大小
     *
     * @param dateFormat 时间格式
     * @param startTime  开始时间
     * @param endTime    结束时间
     * @return
     */
    public static boolean compareDateSize(String dateFormat, String startTime, String endTime) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        try {
            Long a = sdf.parse(startTime).getTime();
            Long b = sdf.parse(endTime).getTime();
            if (a > b || a.equals(b)) {
                return true;
            } else {
                return false;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 比较两个时间串的大小
     * 活动详情时间串对比
     * @param dateFormat 时间格式
     * @param startTime  开始时间
     * @param endTime    结束时间
     * @return
     */
    public static boolean compareDateSizediscover(String dateFormat, String startTime, String endTime) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        try {
            Long a = sdf.parse(startTime).getTime();
            Long b = sdf.parse(endTime).getTime();
            if (a > b) {
                if (a.equals(b)) {
                    return false;
                }
                return true;
            } else {
                return false;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }


    /**
     * 比较两个时间串的大小
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return
     */
    public static boolean compareTimeOfAddApply(String startTime, String endTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        try {
            Long a = sdf.parse(startTime).getTime();
            Long b = sdf.parse(endTime).getTime();
            Long currentTime = sdf.parse(getCurrentTime("yyyy年MM月dd日")).getTime();
            if (a < currentTime) {
                return false;
            } else {
                if (a > b) {
                    return false;
                } else {
                    return true;
                }
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 根据传入的日期，得到对应的日期
     *
     * @param time 一个日期 yyyy-MM-dd HH:mm:ss
     */
    public  String[] getWeekStartAndWeekEndBaseTime(String time) {
        StringBuilder sb = new StringBuilder();
        Calendar ca = getCalendar();
        try {
            Date date = sdf.parse(time);
            ca.setTime(date);
            ca.set(Calendar.DAY_OF_WEEK, ca.getFirstDayOfWeek() + 1);
            String time_start = sdf.format(ca.getTime());
            sb.append(time_start + ",");
            ca.add(Calendar.DAY_OF_WEEK, 6);
            String time_end = sdf.format(ca.getTime());
            sb.append(time_end);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return sb.toString().split(",");
    }


    /**
     * @param date yyyy-MM
     * @return
     */
    public static int getMonth(String date) {
        Calendar calendar = Calendar.getInstance();
        int month = -1;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        try {
            Date time = sdf.parse(date);
            calendar.setTime(time);
            month = calendar.get(Calendar.MONTH);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return month + 1;
    }

    /**
     * 获取当前的月份
     *
     * @return
     */
    public static String getCurrentMonth() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        return String.valueOf(year) + "-" + String.valueOf(month);
    }

    /**
     * 获取当年
     *
     * @return
     */
    public static String getCurrentYear() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        return String.valueOf(year);
    }

    /**
     * 获取7天前得日期  yyyy-MM-dd
     *
     * @return
     */
    public static String getDateSomeDaysAgo(int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, day);
        Date date = calendar.getTime();
        String time = new SimpleDateFormat("yyyy-MM-dd").format(date);
        return time;
    }


    /**
     * 获取对应格式的时间
     *
     * @return
     */
    public static String getTimeFromStringTime(String format_return, String format_older, String time) {
        if (!StrUtils.isStringValueOk(time)) {
            return "无";
        }
        Date date = null;
        SimpleDateFormat sdf_return = new SimpleDateFormat(format_return, Locale.CHINA);
        SimpleDateFormat sdf_older = new SimpleDateFormat(format_older, Locale.CHINA);
        try {
            date = sdf_older.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return sdf_return.format(date);
    }


}
