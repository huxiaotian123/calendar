package xt.calendar.widget.calendar;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
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
 */
public class WeekCalendarView extends BaseCalendarView {



    public WeekCalendarView(Context context) {
        super(context);
    }

    public WeekCalendarView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public void initCalenDar() {
        setAdapter(new WeekViewPagerAdapter());
    }

    @Override
    public void setSellectCalendar(Calendar mSellectCalendar) {
        this.mSellectCalendar = mSellectCalendar;
        int weekCount = CalendarUtil.getWeekCount(mStartCalendar, mSellectCalendar) -1;
        setCurrentItem(weekCount);

    }




    public void setCurrentCalendar(final Calendar currentCal) {
        mSellectCalendar  =currentCal;

    }



    public class WeekViewPagerAdapter extends PagerAdapter {

        private BasePageView basePageView;

        @Override
        public void setPrimaryItem(ViewGroup container, int position, Object object) {
            super.setPrimaryItem(container, position, object);
            basePageView = (BasePageView) object;
        }

        public Calendar getCurrentCal(){
            return basePageView.getCurrentCalendar();
        }

        public void setCurrentCal(Calendar cal){
            if(null != basePageView){
                basePageView.setCurrentCalendar(cal);
            }
        }


        @Override
        public int getCount() {
            return CalendarUtil.getWeekCount(mStartCalendar,mEndCalendar);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            WeekPageView weekPageView = new WeekPageView(container.getContext());
         //   weekPageView.setCalendarView(WeekCalendarView.this);
            Calendar calendar = CalendarUtil.copyCalendar(mStartCalendar);
            calendar.set(Calendar.DAY_OF_MONTH, CalendarUtil.getDay(mStartCalendar) + position*7 );
            weekPageView.setCurrentCalendar(calendar);
            container.addView(weekPageView);
            return weekPageView;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }



}
