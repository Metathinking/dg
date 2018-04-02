package com.hu.dg.util;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 类的功能，目的，描述等写在此处
 *
 * @author 胡玉波
 * @version 1.0
 */
public class DateUtil {
    public static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    public static SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    public static ArrayList getSortedHashtableByValue(Map h) {
        ArrayList<Map.Entry<String, Integer>> l = new ArrayList<Map.Entry<String, Integer>>(h.entrySet());
        Collections.sort(l, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return (o1.getValue() - o2.getValue());
            }
        });
        return l;
    }

    //把2014-01-01  格式的时间转换成long类型
    public static long getTime(String time) throws ParseException {
        Date date = format.parse(time);
        return date.getTime();
    }

    //把2014-01-01 12:12:12格式的时间转换成long类型
    public static long getTime2(String time) throws ParseException {
        Date date = format2.parse(time);
        return date.getTime();
    }

    //获取指定年月日的开始时间
    public static long getZdDayBeginTime(int year, int month, int day) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month - 1);
        c.set(Calendar.DAY_OF_MONTH, day);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime().getTime();
    }

    //获取指定年月日的结束时间
    public static long getZdDayEndTime(int year, int month, int day) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month - 1);
        c.set(Calendar.DAY_OF_MONTH, day);
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        return c.getTime().getTime();
    }

    //获取指定年月的开始时间
    public static long getZdYearMonthBeginTime(int year, int month) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month - 1);
        c.set(Calendar.DAY_OF_MONTH, 1);//设置为1号,当前日期既为本月第一天
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime().getTime();
    }

    //获取指定年月的结束时间
    public static long getZdYearMonthEndTime(int year, int month) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month - 1);
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        return c.getTime().getTime();
    }

    //获取指定年的开始时间
    public static long getZDYearBeginTime(int year) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH, 1);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime().getTime();
    }

    //获取指定年的结束时间
    public static long getZDYearEndTime(int year) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, 11);
        c.set(Calendar.DAY_OF_MONTH, 31);
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        return c.getTime().getTime();
    }

    //获取昨天开始时间
    public static long yesterdayBegTime() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, -1);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime().getTime();
    }

    //获取昨天结束时间
    public static long yesterdayEndTime() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, -1);
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        return c.getTime().getTime();
    }

    //获取当天开始时间
    public static long currentDayBeginTime() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND,0);
        return c.getTime().getTime();
    }

    //获取当天结束时间
    public static long currentDayEndTime() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        return c.getTime().getTime();
    }

    //获取当天指定时分秒的时间
    public static long currentDayHMSTime(int h, int m, int s) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, h);
        c.set(Calendar.MINUTE, m);
        c.set(Calendar.SECOND, s);
        return c.getTime().getTime();
    }

    public static long getZDDayHMSTime(long time, String hhmmss) {
        try {
            String[] array = hhmmss.split(":");
            Calendar c = Calendar.getInstance();
            c.setTime(new Date(time));
            c.set(Calendar.HOUR_OF_DAY, Integer.parseInt(array[0]));
            c.set(Calendar.MINUTE, Integer.parseInt(array[1]));
            c.set(Calendar.SECOND, Integer.parseInt(array[2]));
            return c.getTime().getTime();
        } catch (Exception ex) {
            Calendar c = Calendar.getInstance();
            c.set(Calendar.HOUR_OF_DAY, 23);
            c.set(Calendar.MINUTE, 59);
            c.set(Calendar.SECOND, 59);
            return c.getTime().getTime();
        }

    }

    //获取当月开始时间毫秒数
    public static long currentMonthBeginTime() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH, 1);//设置为1号,当前日期既为本月第一天
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime().getTime();
    }

    //获取当月结束时间毫秒数
    public static long currentMonthEndTime() {
        Calendar ca = Calendar.getInstance();
        ca.add(Calendar.MONTH, 0);
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
        ca.set(Calendar.HOUR_OF_DAY, 23);
        ca.set(Calendar.MINUTE, 59);
        ca.set(Calendar.SECOND, 59);
        return ca.getTime().getTime();
    }

    //获取当年开始时间毫秒数
    public static long currentYearBeginTime() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH, 1);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime().getTime();
    }

    //获取当年结束时间毫秒数
    public static long currentYearEndTime() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.MONTH, 11);
        c.set(Calendar.DAY_OF_MONTH, 31);
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        System.out.println(format.format(c.getTime()));
        return c.getTime().getTime();
    }

    //根据时间毫秒数获取年
    public static int getYear(long time) {
        Date date = new Date(time);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.YEAR);
    }

    //根据时间毫秒数获取月
    public static int getMonth(long time) {
        Date date = new Date(time);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.MONTH) + 1;
    }

    //根据时间毫秒数获取日
    public static int getDay(long time) {
        Date date = new Date(time);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.DAY_OF_MONTH);
    }

    public static String getDateStringByFormat(long time, String format) {
        SimpleDateFormat f = new SimpleDateFormat(format);
        return f.format(new Date(time));
    }

    /**
     * 得到当前时间之后n天之后的时间
     *
     * @param currTime
     * @param dayCount
     * @return
     */
    public static long getAfterDayTime(long currTime, int dayCount) {
        return currTime + dayCount * 24l * 60l * 60l * 1000l;
    }

    public static long getBeforeDayTime(long currTime, int dayCount) {
        return currTime - dayCount * 24l * 60l * 60l * 1000l;
    }

    public static long getAfterMonthTime(long currTime, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(currTime));
        calendar.add(Calendar.MONTH, month);
        return calendar.getTime().getTime();
    }

    //获取 yyyyMMddHHmmss 格式的时间
    public static String getyMdHmsateDate() {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        return df.format(new Date());
    }

    //获取 yyyyMMdd 格式的时间
    public static String getyMdDate() {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        return df.format(new Date());
    }

    /**
     * 判断时间是否是今天的时间
     *
     * @param time
     * @return
     */
    public static boolean isInToday(long time) {
        long begin = currentDayBeginTime();
        long end = currentDayEndTime();
        return (time >= begin && time <= end);
    }
}
