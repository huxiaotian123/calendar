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
import xt.calendar.Listener.MouthWeekListener;
import xt.calendar.util.CalendarUtil;

import java.util.Calendar;

/**
 * Created by Administrator on 2016/10/13.
 */
public class VariableViewPager extends ViewPager  {

    private float deviation = 200;//偏移量
    private float startY; //起点位置
    private boolean isMoth = true;//默认是下面
    private Calendar mCurrentCalendar;



    public void setMouthWeekListener(MouthWeekListener mouthWeekListener) {
        this.mouthWeekListener = mouthWeekListener;
    }

    private MouthWeekListener mouthWeekListener;

    public VariableViewPager(Context context) {
        super(context);
    }

    public VariableViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                startY = ev.getY();
                Log.e("hxt","patcdown-----"+startY);
                break;
            case MotionEvent.ACTION_MOVE:
            case MotionEvent.ACTION_UP:
                float moveY = ev.getY() - startY;
                Log.e("hxt","patcmove-----"+moveY);
                if(Math.abs(moveY)>deviation){
                    if(moveY > 0 ){//下移
                        showMouth();
                    }else {//上移
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
        if(isMoth){
            LinearLayout.LayoutParams LayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,200);
            this.setLayoutParams(LayoutParams);
            isMoth = false;
            querySelectCalendar();
            setAdapter(new WeekViewPagerAdapter());
            if(null!= mouthWeekListener){
                mouthWeekListener.showWeekView();
            }
        }
    }

    /**
     * 需要下移
     */
    private void showMouth() {
        if(!isMoth){
            LinearLayout.LayoutParams LayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
            this.setLayoutParams(LayoutParams);
            isMoth = true;
            querySelectCalendar();
            setAdapter(new MouthViewPagerAdapter());
            if(null!= mouthWeekListener){
                mouthWeekListener.showMouthView();
            }
        }
    }

    public void setCurrentCalendar(Calendar mCurrentCalendar) {
        this.mCurrentCalendar = mCurrentCalendar;

        setAdapter(new MouthViewPagerAdapter());


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
            mCurrentCalendar.set(Calendar.MONTH, CalendarUtil.getMouth(mCurrentCalendar)+position);
            mouthView.setCurrentCalendar(mCurrentCalendar);
            container.addView(mouthView);
            return mouthView;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }


    public class WeekViewPagerAdapter extends PagerAdapter{

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
            WeekView mouthView = new WeekView(container.getContext());

            mCurrentCalendar.set(Calendar.DAY_OF_MONTH, CalendarUtil.getDay(mCurrentCalendar)+position*7);
            mouthView.setCurrentCalendar(mCurrentCalendar);
            container.addView(mouthView);
            return mouthView;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

    private void querySelectCalendar(){
        int currentItem = getCurrentItem();
        View childView = getChildAt(currentItem);
        if (childView instanceof  MouthView){
            mCurrentCalendar =  ((MouthView)childView).getCurrentCalendar();
        }else {
            mCurrentCalendar =   ((WeekView)childView).getCurrentCalendar();
        }
    }

}
