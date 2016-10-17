package xt.calendar.util;

import android.support.annotation.NonNull;

import java.util.Calendar;

/**
 * Created by Administrator on 2016/10/17.
 */
public class CalendarUtil {


    public static int getFirstDayOfWeek(@NonNull Calendar calendar) {
        return calendar.getFirstDayOfWeek();
    }


    public static int getDaysCount(@NonNull Calendar calendar) {
        return calendar.getActualMaximum(Calendar.DATE);
    }


    public static int getDayOfWeek(@NonNull Calendar calendar) {
        return calendar.get(Calendar.DAY_OF_WEEK);
    }


    public static int getMouth(@NonNull Calendar calendar) {
        return calendar.get(Calendar.MONTH);
    }

    public static int getYear(@NonNull Calendar calendar) {
        return calendar.get(Calendar.YEAR);
    }

    public static int getDay(@NonNull Calendar calendar) {
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    public static boolean isTheSameMouth(Calendar firstCalendar, Calendar secondCalendar) {
        if (CalendarUtil.getMouth(firstCalendar) == CalendarUtil.getMouth(secondCalendar)
                && CalendarUtil.getYear(firstCalendar) == CalendarUtil.getYear(firstCalendar)) {
            return true;
        } else {
            return false;
        }
    }
}
