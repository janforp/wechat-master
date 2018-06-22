package com.janita.wechat.common.utils;


import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Janita on 2018-03-29 09:36
 */
public class DateTimeUtils {

    private static final String FMT_yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";

    /**
     * 获取上个月的年月 2018-02
     * @return
     */
    public static String getDateOfLastMonth() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, -1);
        SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM");
        return format.format(c.getTime());
    }

    /**
     * 获取上个月的年份，如：2018 或者 2017
     * @return
     */
    public static String getYearOfLastMonth() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, -1);
        SimpleDateFormat format =  new SimpleDateFormat("yyyy");
        return format.format(c.getTime());
    }

    public static String getLastYear() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat format =  new SimpleDateFormat("yyyy");
        String currentYear = format.format(c.getTime());
        return String.valueOf(Integer.valueOf(currentYear) - 1);
    }

    /**
     * 获取上个月的月份
     * @return
     */
    public static String getLastMonth() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, -1);
        SimpleDateFormat format =  new SimpleDateFormat("MM");
        return format.format(c.getTime());
    }

    /**
     * 获取当前月份
     * @return
     */
    public static String getCurrentMonth() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat format =  new SimpleDateFormat("MM");
        return format.format(c.getTime());
    }

    /**
     * 获取当前年份
     * @return
     */
    public static String getCurrentYear() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat format =  new SimpleDateFormat("yyyy");
        return format.format(c.getTime());
    }

    /**
     * 获取当前日期的季度
     * @param date
     * @return
     */
    public static int getSeason(Date date) {
        int season = 0;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int month = calendar.get(Calendar.MONTH);
        switch (month) {
            case Calendar.JANUARY:
            case Calendar.FEBRUARY:
            case Calendar.MARCH:
                season = 1;
                break;

            case Calendar.APRIL:
            case Calendar.MAY:
            case Calendar.JUNE:
                season = 2;
                break;
            case Calendar.JULY:
            case Calendar.AUGUST:
            case Calendar.SEPTEMBER:
                season = 3;
                break;
            case Calendar.OCTOBER:
            case Calendar.NOVEMBER:
            case Calendar.DECEMBER:
                season = 4;
                break;
            default:
                break;
        }
        return season;
    }

    public static void main(String[] args) {
        System.out.println(dateToWeek("2018-04-04"));
    }

    public static String getCurrentDay() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat format =  new SimpleDateFormat("dd");
        return format.format(c.getTime());
    }

    /**
     * 日期转星期
     * @param datetime
     * @return
     */
    public static String dateToWeek(String datetime) {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
        Calendar cal = Calendar.getInstance(); // 获得一个日历
        Date datet = null;
        try {
            datet = f.parse(datetime);
            cal.setTime(datet);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1; // 指示一个星期中的某天。
        if (w < 0) {
            w = 0;
        }
        return weekDays[w];
    }

    public static String timestampToDate(Timestamp timeStamp) {
        if (timeStamp == null) {
            return null;
        }
        long time = timeStamp.getTime();
        return new SimpleDateFormat(FMT_yyyy_MM_dd_HH_mm_ss).format(new Date(time));
    }

    /**
     * 获取当前时间是一年中的第几周
     * @return
     */
    public static int getCurrentWeekOfYear() {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTime(date);
        return calendar.get(Calendar.WEEK_OF_YEAR);
    }
}
