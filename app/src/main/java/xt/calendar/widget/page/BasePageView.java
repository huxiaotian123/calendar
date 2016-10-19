package xt.calendar.widget.page;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridView;
import android.widget.LinearLayout;
import xt.calendar.R;
import xt.calendar.widget.XtCalendarView;
import xt.calendar.widget.calendar.BaseCalendarView;

import java.util.Calendar;

/**
 * Created by Administrator on 2016/10/18.
 */
public abstract class BasePageView extends LinearLayout {

    public Calendar mStandardCal;

    public Calendar mCurrentCalendar;
    public XtCalendarView mXtCalendarView;
    public GridView mGridView;


    public void setCalendarView(XtCalendarView mXtCalendarView) {
        this.mXtCalendarView = mXtCalendarView;
    }

    public Calendar getCurrentCalendar() {
        return mCurrentCalendar;
    }

    public abstract void setCurrentCalendar(Calendar currentCal);
    public abstract void initPage(Calendar mSellectCalendar, BaseCalendarView baseCalendarView);

    public BasePageView(Context context) {
        this(context, null);
    }

    public BasePageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BasePageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        // 导入布局
        View inflate = LayoutInflater.from(context).inflate(R.layout.view_basedate, this, true);
        mGridView = (GridView) inflate.findViewById(R.id.grideview);
    }


}
