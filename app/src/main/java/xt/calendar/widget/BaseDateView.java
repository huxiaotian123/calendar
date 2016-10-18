package xt.calendar.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridView;
import android.widget.LinearLayout;
import xt.calendar.R;

import java.util.Calendar;

/**
 * Created by Administrator on 2016/10/18.
 */
public abstract class BaseDateView extends LinearLayout {
    public Calendar mCurrentCalendar;
    public CalendarView mCalendarView;
    public GridView mGridView;


    public void setCalendarView(CalendarView mCalendarView) {
        this.mCalendarView = mCalendarView;
    }

    public Calendar getCurrentCalendar() {
        return mCurrentCalendar;
    }

    public abstract void setCurrentCalendar(Calendar currentCal);

    public BaseDateView(Context context) {
        this(context, null);
    }

    public BaseDateView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseDateView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        // 导入布局
        View inflate = LayoutInflater.from(context).inflate(R.layout.view_basedate, this, true);
        mGridView = (GridView) inflate.findViewById(R.id.grideview);

    }


}
