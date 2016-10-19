package xt.calendar.widget.calendar;

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
import xt.calendar.Listener.MonthWeekListener;
import xt.calendar.util.CalendarUtil;
import xt.calendar.util.UiUtils;
import xt.calendar.widget.page.BasePageView;
import xt.calendar.widget.page.MonthPageView;
import xt.calendar.widget.page.WeekPageView;

import java.util.Calendar;

/**
 * Created by Administrator on 2016/10/13.
 */
public class WeekCalendarView extends BaseCalendarView {


    private WeekViewPagerAdapter mAdapter;

    public WeekCalendarView(Context context) {
        super(context);
    }

    public WeekCalendarView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void initCalenDar() {

        mAdapter = new WeekViewPagerAdapter();
        setAdapter(mAdapter);
        setOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(isMoveByUser){
                    if(mSellectCalendar == null){
                        return;
                    }
                    Calendar calendarByPostion = getCalendarByPostion(position);
                    // TODO: 2016/10/20 加上toString以后才能正确设置 DAY_OF_WEEK -->待study
                    CalendarUtil. toString(calendarByPostion);
                    //Log.e("hxt","----"+CalendarUtil.toString(calendarByPostion));
                    calendarByPostion.set(Calendar.DAY_OF_WEEK,CalendarUtil.getDayOfWeek(mSellectCalendar));
                    mSellectCalendar = null;
                    Log.e("hxt",CalendarUtil.toString(calendarByPostion));
                    mSellectCalendar = calendarByPostion;
                }else {
                    isMoveByUser = true;
                }
                Toast.makeText(WeekCalendarView.this.getContext(), CalendarUtil.toString(mSellectCalendar), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }


    @Override
    public void setSellectCalendar(Calendar cal) {
        isMoveByUser = false;
        mSellectCalendar = cal;
        int weekCount = CalendarUtil.getWeekCount(mStartCalendar, mSellectCalendar) -1;
        setCurrentItem(weekCount);

    }



    public class WeekViewPagerAdapter extends PagerAdapter {

        private WeekPageView mCurrentWeekPageView;

        public WeekPageView getCurrentWeekPageView() {
            return mCurrentWeekPageView;
        }

        @Override
        public int getCount() {
            int weekCount = CalendarUtil.getWeekCount(mStartCalendar, mEndCalendar);
            return weekCount;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            WeekPageView weekPageView = new WeekPageView(container.getContext());
            Calendar calendar = getCalendarByPostion(position);
            weekPageView.initPage(calendar,WeekCalendarView.this);
            weekPageView.setCurrentCalendar(mSellectCalendar);
            mCurrentWeekPageView = weekPageView;

            container.addView(weekPageView);
            return weekPageView;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }



    private Calendar getCalendarByPostion(int position) {
        Calendar calendar = CalendarUtil.copyCalendar(mStartCalendar);
        calendar.set(Calendar.DAY_OF_MONTH, CalendarUtil.getDay(mStartCalendar) + position*7 );
        return calendar;
    }
}
