package xt.calendar.widget.calendar;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import xt.calendar.Listener.MonthWeekListener;
import xt.calendar.util.CalendarUtil;
import xt.calendar.widget.page.BasePageView;
import xt.calendar.widget.page.MonthPageView;
import xt.calendar.widget.page.WeekPageView;

import java.util.Calendar;

/**
 * Created by Administrator on 2016/10/13.
 * 月历/周历的基类
 */
public abstract class BaseCalendarView extends ViewPager {


    public Calendar mSellectCalendar;
    public Calendar mStartCalendar;//开始时间
    public Calendar mEndCalendar;//结束时间


    public BaseCalendarView(Context context) {
        this(context, null);
    }

    public BaseCalendarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        //起始时间默认1970.1.1
        mStartCalendar = Calendar.getInstance();
        mStartCalendar.set(1970, 0, 1);
        //结束时间默认2070.12.31
        mEndCalendar = Calendar.getInstance();
        mEndCalendar.set(2070, 11, 31);
        initCalenDar();
    }


    private int mSelectDay;
    private Calendar startCaldar;


    public void setStart2EndCalendar(Calendar startCalendar,Calendar endCalendar) {
        this.mStartCalendar = startCalendar;
        this.mEndCalendar = endCalendar;
        initCalenDar();
    }

    public abstract void initCalenDar();

    public abstract void setSellectCalendar(Calendar mSellectCalendar);

    public interface OnCalendarChangeListener{
        void  onChange(Calendar calendar);
    }

    public OnCalendarChangeListener mOnCalendarChangeListener;

    public void setOnPagerCalendarChangeListener(OnCalendarChangeListener mOnCalendarChangeListener) {
        this.mOnCalendarChangeListener = mOnCalendarChangeListener;
    }


}
