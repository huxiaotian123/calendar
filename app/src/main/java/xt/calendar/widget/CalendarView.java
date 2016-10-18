package xt.calendar.widget;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;
import xt.calendar.Listener.MouthWeekListener;
import xt.calendar.util.CalendarUtil;

import java.util.Calendar;

/**
 * Created by Administrator on 2016/10/13.
 */
public class CalendarView extends ViewPager {

    private float deviation = 200;//偏移量
    private float startY; //起点位置
    private boolean isMoth = true;//默认是下面
    private Calendar mCurrentCalendar;



    public void setMouthWeekListener(MouthWeekListener mouthWeekListener) {
        this.mouthWeekListener = mouthWeekListener;
    }

    private MouthWeekListener mouthWeekListener;

    public CalendarView(Context context) {
        super(context);
    }

    public CalendarView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


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
            setAdapter(new WeekViewPagerAdapter());
            if (null != mouthWeekListener) {
                mouthWeekListener.showWeekView();
            }
        }
    }

    /**
     * 需要下移
     */
    private void showMouth() {
        if (!isMoth) {

            isMoth = true;
            setAdapter(new MouthViewPagerAdapter());
            if (null != mouthWeekListener) {
                mouthWeekListener.showMouthView();
            }
        }
    }

    private int mSelectDay;
    private Calendar startCaldar;
    public void refreshMcurrentCalendar(Calendar calendar){
        this.mCurrentCalendar = calendar;
        mSelectDay = CalendarUtil.getDay(mCurrentCalendar);
        Toast.makeText(CalendarView.this.getContext(),CalendarUtil.getMouth(mCurrentCalendar)+1+"月",Toast.LENGTH_SHORT).show();
        if(isMoth){
            setAdapter(new MouthViewPagerAdapter());
        }else {
            setAdapter(new WeekViewPagerAdapter());
        }
    }


    public void setCurrentCalendar(final Calendar currentCal) {
        startCaldar = currentCal;
        mCurrentCalendar = currentCal;
        mSelectDay = CalendarUtil.getDay(mCurrentCalendar);
        if(isMoth){
            setAdapter(new MouthViewPagerAdapter());
        }else {
            setAdapter(new WeekViewPagerAdapter());
        }

        setOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
//
             //    Toast.makeText(CalendarView.this.getContext(),CalendarUtil.getMouth(mCurrentCalendar)+1+"月",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public class MouthViewPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return 100;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            MouthView mouthView = new MouthView(container.getContext());
            mouthView.setCalendarView(CalendarView.this);
            Calendar calendar = Calendar.getInstance();
                calendar.set(CalendarUtil.getYear(mCurrentCalendar), CalendarUtil.getMouth(mCurrentCalendar)+position, 1);
            int daysCount = CalendarUtil.getDaysCount(calendar);
            if(daysCount <= mSelectDay){
                calendar.set(Calendar.DAY_OF_MONTH,daysCount);
            }else {
                calendar.set(Calendar.DAY_OF_MONTH,mSelectDay);
            }
            mouthView.setCurrentCalendar(calendar);
            container.addView(mouthView);
            return mouthView;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }


    public class WeekViewPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return 100;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            WeekView weekView = new WeekView(container.getContext());
            weekView.setCalendarView(CalendarView.this);
            Calendar calendar = CalendarUtil.copyCalendar(mCurrentCalendar);
            calendar.set(Calendar.DAY_OF_MONTH, CalendarUtil.getDay(mCurrentCalendar) + position*7 );
            weekView.setCurrentCalendar(calendar);
            container.addView(weekView);
            return weekView;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }



}
