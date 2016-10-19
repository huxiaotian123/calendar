package xt.calendar.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import xt.calendar.Listener.MonthWeekListener;
import xt.calendar.R;
import xt.calendar.widget.calendar.MonthCalendarView;
import xt.calendar.widget.calendar.WeekCalendarView;

import java.util.Calendar;

/**
 * Created by Administrator on 2016/10/13.
 */
public class XtCalendarView extends FrameLayout {

    private float deviation = 200;//偏移量
    private float startY; //起点位置
    private boolean isMoth = true;//默认是下面
    private Calendar mCurrentCalendar;
    private MonthCalendarView monthCalendarView; //月历
    private WeekCalendarView weekCalendarView;  //周历




    public XtCalendarView(Context context) {
        this(context,null);
    }

    public XtCalendarView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public XtCalendarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View rootView = LayoutInflater.from(context).inflate(R.layout.view_xtcalendar, this, true);
        monthCalendarView = (MonthCalendarView) rootView.findViewById(R.id.monthcalendarview);
        weekCalendarView = (WeekCalendarView) rootView.findViewById(R.id.weekcalendarview);

        showViewByState();

    }


    private void showViewByState() {
        if(isMoth){
            monthCalendarView.setVisibility(VISIBLE);
            weekCalendarView.setVisibility(GONE);
        }else {
            monthCalendarView.setVisibility(GONE);
            weekCalendarView.setVisibility(VISIBLE);
        }
    }


    public void setMonthWeekListener(MonthWeekListener monthWeekListener) {
        this.monthWeekListener = monthWeekListener;
    }

    private MonthWeekListener monthWeekListener;




    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startY = ev.getY();
                Log.e("hxt", "patcdown-----" + startY);
                break;
            case MotionEvent.ACTION_MOVE:
            case MotionEvent.ACTION_UP:
                float moveY = ev.getY() - startY;
                Log.e("hxt", "patcmove-----" + moveY);
                if (Math.abs(moveY) > deviation) {
                    if (moveY > 0) {//下移
                        showMouth();
                    } else {//上移
                        showWeek();
                    }
                }
                break;
        }
        return super.dispatchTouchEvent(ev);
    }


    /**
     * 上移
     */
    private void showWeek() {
        if (isMoth) {
            isMoth = false;
            showViewByState();
            if (null != monthWeekListener) {
                monthWeekListener.showWeekView();
            }
        }
    }

    /**
     * 需要下移
     */
    private void showMouth() {
        if (!isMoth) {
            isMoth = true;
            showViewByState();
            if (null != monthWeekListener) {
                monthWeekListener.showMonthView();
            }
        }
    }



    public void setStart2EndCalendar(Calendar startCalendar,Calendar endCalendar) {
        monthCalendarView.setStart2EndCalendar(startCalendar,endCalendar);
        weekCalendarView.setStart2EndCalendar(startCalendar,endCalendar);
    }

    public void setSelectCalendar(Calendar cal){
        if(isMoth){
            monthCalendarView.setSellectCalendar(cal);
        }else {
            weekCalendarView.setSellectCalendar(cal);

        }
    }

}
