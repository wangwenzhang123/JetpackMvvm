package com.example.library_base.util;

import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by DS.Hu on 2016/9/30.
 */

public class DateUtils {

    public static final long SECOND_UNITE = 1000;
    public static final long MINUTE_UNITE = 60 * SECOND_UNITE;
    public static final long HOUR_UNITE = 60 * MINUTE_UNITE;
    public static final long DAY_UNITE = 24 * HOUR_UNITE;


    public static final String DATE_FORMAT_1 = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORMAT_2 = "yyyy-MM-dd HH:mm";
    public static final String DATE_FORMAT_3 = "yyyy-MM-dd";
    public static final String DATE_FORMAT_4 = "yyyy.MM.dd";
    public static final String DATE_FORMAT_5 = "HH:mm";
    public static final String DATE_FORMAT_6 = "yyyy年MM月dd日";
    public static final String DATE_FORMAT_7 = "MM-dd HH:mm:ss";
    public static final String DATE_FORMAT_8 = "yyyy.MM.dd HH:mm:ss";
    public static final String DATE_FORMAT_9 = "yyyyMMddHHmmss";

    /**
     * 获取SimpleDateFormat
     * <p>格式为参数format</p>
     *
     * @return SimpleDateFormat
     */
    public static SimpleDateFormat getSimpleDateFormat(String format) {
        return new SimpleDateFormat(format, Locale.getDefault());
    }


    /**
     * 将时间戳转为时间字符串
     * <p>格式为用户自定义</p>
     *
     * @param milliseconds 毫秒时间戳
     * @param format       时间格式
     * @return 时间字符串
     */
    public static String formatDateFromMillis(long milliseconds, SimpleDateFormat format) {
        return format.format(new Date(milliseconds));
    }

    /**
     * 将时间戳转为时间字符串
     * <p>格式为用户自定义</p>
     *
     * @param milliseconds 毫秒时间戳
     * @param format       时间格式
     * @return 时间字符串
     */
    public static String formatDateFromMillis(long milliseconds, String format) {
        SimpleDateFormat dateFormat = getSimpleDateFormat(format);
        return dateFormat.format(new Date(milliseconds));
    }

    /**
     * 将时间戳转为时间字符串
     * <p>格式为用户自定义</p>
     *
     * @param milliseconds 毫秒时间戳
     * @param format       时间格式
     * @return 时间字符串
     */
    public static String formatDateFromMillis(String milliseconds, SimpleDateFormat format) {
        long millis;
        try {
            millis = Long.parseLong(milliseconds);
        } catch (Exception ex) {
            return "";
        }
        return format.format(new Date(millis));
    }

    /**
     * 将时间戳转为时间字符串
     * <p>格式为用户自定义</p>
     *
     * @param milliseconds 毫秒时间戳
     * @param format       时间格式
     * @return 时间字符串
     */
    public static String formatDateFromMillis(String milliseconds, String format) {
        long millis;
        try {
            millis = Long.parseLong(milliseconds);
        } catch (Exception ex) {
            return "";
        }
        SimpleDateFormat dateFormat = getSimpleDateFormat(format);
        return dateFormat.format(new Date(millis));
    }

    /**
     * 将Date类型转为时间字符串
     * <p>格式为用户自定义</p>
     *
     * @param time   Date类型时间
     * @param format 时间格式
     * @return 时间字符串
     */
    public static String date2String(Date time, SimpleDateFormat format) {
        return format.format(time);
    }


    /**
     * 切换日期格式
     *
     * @param dateStr   日期格式字符串
     * @param oldFormat 当前字符串的格式
     * @param newFormat 字符串格式
     * @return
     */
    public static String changeDateFormat(String dateStr, String oldFormat, String newFormat) {
        if (TextUtils.isEmpty(dateStr) || TextUtils.isEmpty(oldFormat) || TextUtils.isEmpty(newFormat))
            return null;
        SimpleDateFormat oldSimple = getSimpleDateFormat(oldFormat);
        Date date;
        try {
            date = oldSimple.parse(dateStr);
        } catch (ParseException e) {
            return null;
        }
        return date2String(date, getSimpleDateFormat(newFormat));
    }

    /**
     * 将Date类型转为时间戳
     *
     * @param time Date类型时间
     * @return 毫秒时间戳
     */
    public static long date2Millis(Date time) {
        return time.getTime();
    }

    /**
     * 将时间字符串转为时间戳
     * <p>格式为用户自定义</p>
     *
     * @param time   时间字符串
     * @param format 时间格式
     * @return 毫秒时间戳
     */
    public static long string2Millis(String time, SimpleDateFormat format) {
        try {
            return format.parse(time).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * 判断闰年
     *
     * @param year 年份
     * @return {@code true}: 闰年<br>{@code false}: 平年
     */
    public static boolean isLeapYear(int year) {
        return year % 4 == 0 && year % 100 != 0 || year % 400 == 0;
    }

    /**
     * 指定年月，获取月天数
     *
     * @param year 年份
     * @return {@code int}: 天数
     */
    public static int getDayOfMonth(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, 1);
        return calendar.getActualMaximum(Calendar.DATE);
    }

    /**
     * 获取当前的年份
     *
     * @return {@code int}: 年份
     */
    public static int getCurrentYear() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 获取当前的月份
     *
     * @return {@code int}: 月份
     */
    public static int getCurrentMonth() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 获取当前的日期
     *
     * @return {@code int}: day
     */
    public static int getCurrentDay() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.DAY_OF_MONTH);
    }
}
