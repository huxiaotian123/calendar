package xt.calendar.util;

import android.support.annotation.NonNull;

import java.util.Calendar;

/**
 * Created by Administrator on 2016/10/17.
 */
public class CalendarUtil {


    /**
     * 一个月有多少天
     * @param calendar
     * @return
     */
    public static int getDaysCount(@NonNull Calendar calendar) {
        return calendar.getActualMaximum(Calendar.DATE);
    }


    public static int getDayOfWeek(@NonNull Calendar calendar) {
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    public static int getDayOfYear(@NonNull Calendar calendar) {
        return calendar.get(Calendar.DAY_OF_YEAR);
    }


    public static int getMonth(@NonNull Calendar calendar) {
        return calendar.get(Calendar.MONTH);
    }

    public static int getRealMonth(@NonNull Calendar calendar) {
        return calendar.get(Calendar.MONTH)+1;
    }

    public static int getYear(@NonNull Calendar calendar) {
        return calendar.get(Calendar.YEAR);
    }

    public static int getDay(@NonNull Calendar calendar) {
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 年月份相同
     * @param firstCalendar
     * @param secondCalendar
     * @return
     */
    public static boolean isTheSameMouthAndYear(Calendar firstCalendar, Calendar secondCalendar) {
        if (CalendarUtil.getMonth(firstCalendar) == CalendarUtil.getMonth(secondCalendar)
                && CalendarUtil.getYear(firstCalendar) == CalendarUtil.getYear(firstCalendar)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 每月的同一天
     * @param firstCalendar
     * @param secondCalendar
     * @return
     */
    public static boolean isTheDayOfMouth(Calendar firstCalendar, Calendar secondCalendar) {
        if(null == firstCalendar || null == secondCalendar){
            return false;
        }
        if (CalendarUtil.getDay(firstCalendar) == CalendarUtil.getDay(secondCalendar)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 每周的同一天
     * @param firstCalendar
     * @param secondCalendar
     * @return
     */
    public static boolean isTheDayOfWeek(Calendar firstCalendar, Calendar secondCalendar) {
        if (CalendarUtil.getDayOfWeek(firstCalendar) == CalendarUtil.getDayOfWeek(secondCalendar)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 复制相同的日历
     * @param firstCalendar
     * @return
     */
    public static Calendar copyCalendar(Calendar firstCalendar){
        Calendar calendar = Calendar.getInstance();
        calendar.set(CalendarUtil.getYear(firstCalendar), CalendarUtil.getMonth(firstCalendar), CalendarUtil.getDay(firstCalendar));
        return calendar;
    }


    /**
     * 时间间隔了多少个月
     */
    public static  int getMonthCount(Calendar firstCalendar,Calendar secondCalendar){
        int addYear = CalendarUtil.getYear(secondCalendar) - CalendarUtil.getYear(firstCalendar);
        int addMonth = CalendarUtil.getMonth(secondCalendar) - CalendarUtil.getMonth(firstCalendar);
        if(addYear < 0){
            return 0;
        }
        return  addYear*12+addMonth+1;
    }

    /**
     * 时间间隔了多少周
     */
    public static  int getWeekCount(Calendar firstCalendar,Calendar secondCalendar){
        int firstDayOfWeek = getDayOfWeek(firstCalendar);
        int secondDayOfWeek = getDayOfWeek(secondCalendar);
        int weekCount = (getDayCount(firstCalendar, secondCalendar) + firstDayOfWeek - 1 + 7 - secondDayOfWeek) / 7;
        return weekCount;
    }

    /**
     * 时间间隔了多少天
     */
    public static  int getDayCount(Calendar firstCalendar,Calendar secondCalendar){
        //设置时间为0时
        firstCalendar.set(Calendar.HOUR_OF_DAY, 0);
        firstCalendar.set(Calendar.MINUTE, 0);
        firstCalendar.set(Calendar.SECOND, 0);
        secondCalendar.set(Calendar.HOUR_OF_DAY, 0);
        secondCalendar.set(Calendar.MINUTE, 0);
        secondCalendar.set(Calendar.SECOND, 0);
        //得到两个日期相差的天数
        int days = ((int)(secondCalendar.getTime().getTime()/1000)-(int)(firstCalendar.getTime().getTime()/1000))/3600/24;

        return days;
    }

    public static String toString(Calendar cal){
        return getYear(cal)+"年"+getRealMonth(cal)+"月"+getDay(cal)+"日";
    }

}
