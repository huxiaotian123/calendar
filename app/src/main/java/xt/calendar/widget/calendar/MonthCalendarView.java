package xt.calendar.widget.calendar;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import xt.calendar.util.CalendarUtil;
import xt.calendar.widget.page.MonthPageView;

import java.util.Calendar;

/**
 * Created by Administrator on 2016/10/13.
 */
public class MonthCalendarView extends BaseCalendarView {

    public int mSelectDay; //辅助 -->暂时记录 31号/30号/28号的情况
    private MonthViewPagerAdapter mAdapter;

    public MonthCalendarView(Context context) {
        super(context);
    }

    public MonthCalendarView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void initCalenDar() {
        mSelectDay = CalendarUtil.getDay(mStartCalendar);
        mAdapter = new MonthViewPagerAdapter();
        setAdapter(mAdapter);
        setOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(isMoveByUser){
                    mSellectCalendar  = getCalendarByPostion(position, mSelectDay);
                }else {
                    isMoveByUser = true;
                }
                Toast.makeText(MonthCalendarView.this.getContext(), CalendarUtil.toString(mSellectCalendar), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }





    @Override
    public void setSellectCalendar(Calendar mSellectCalendar) {
        isMoveByUser = false;
        this.mSellectCalendar = mSellectCalendar;
        mSelectDay = CalendarUtil.getDay(mSellectCalendar);
        int monthCount = CalendarUtil.getMonthCount(mStartCalendar, mSellectCalendar) - 1;
        setCurrentItem(monthCount);
    }




    public class MonthViewPagerAdapter extends PagerAdapter {

        private MonthPageView mCurrentMonthPageView;

        public MonthPageView getCurrentMonthPageView() {
            return mCurrentMonthPageView;
        }

        @Override
        public int getCount() {
            return CalendarUtil.getMonthCount(mStartCalendar, mEndCalendar);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            MonthPageView mouthView = new MonthPageView(container.getContext());
            Calendar calendar = Calendar.getInstance();
            calendar.set(CalendarUtil.getYear(mStartCalendar), CalendarUtil.getMonth(mStartCalendar) + position, 1);
            mouthView.initPage(calendar,MonthCalendarView.this);
            mouthView.setCurrentCalendar(mSellectCalendar);
            mCurrentMonthPageView = mouthView;
            container.addView(mouthView);
            return mouthView;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }


    private Calendar getCalendarByPostion(int position, int selectDay) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(CalendarUtil.getYear(mStartCalendar), CalendarUtil.getMonth(mStartCalendar) + position, 1);
        if (mSelectDay <= 0) {
            calendar.set(Calendar.DAY_OF_MONTH, CalendarUtil.getDay(mSellectCalendar));
        } else {
            int daysCount = CalendarUtil.getDaysCount(calendar);
            if (daysCount <= selectDay) {
                calendar.set(Calendar.DAY_OF_MONTH, daysCount);
            } else {
                calendar.set(Calendar.DAY_OF_MONTH, selectDay);
            }
        }
        return calendar;
    }


}
