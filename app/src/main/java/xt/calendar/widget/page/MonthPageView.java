package xt.calendar.widget.page;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;
import xt.calendar.R;
import xt.calendar.base.ListAdapter;
import xt.calendar.base.ListHolder;
import xt.calendar.util.CalendarUtil;
import xt.calendar.util.UiUtils;
import xt.calendar.widget.calendar.BaseCalendarView;
import xt.calendar.widget.calendar.MonthCalendarView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Administrator on 2016/10/17.
 */
public class MonthPageView extends BasePageView {

    private ArrayList<Calendar> calList;
    private MouthAdapter mouthAdapter;
    private MonthCalendarView monthCalendarView;

    public void setMonthCalendarView(MonthCalendarView monthCalendarView) {
        this.monthCalendarView = monthCalendarView;
    }

    public MonthPageView(Context context) {
        this(context, null);
    }

    public MonthPageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MonthPageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    @Override
    protected void onFocusChanged(boolean gainFocus, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
    }

    @Override
    public void setCurrentCalendar( Calendar currentCal) {
        if(null == currentCal){
            return;
        }
        this.mCurrentCalendar = currentCal;
        if(monthCalendarView.mSelectDay >=CalendarUtil.getDaysCount(mCurrentCalendar)){

        }else {
            monthCalendarView.mSelectDay = CalendarUtil.getDay(mCurrentCalendar);
        }
        mouthAdapter.notifyDataSetChanged();
    }


    @Override
    public void initPage(Calendar cal, BaseCalendarView baseCalendarView) {
        this.monthCalendarView = (MonthCalendarView) baseCalendarView;
        this.mStandardCal = cal;
        calList = new ArrayList();
        Calendar firstDayCal = Calendar.getInstance();
        firstDayCal.set(CalendarUtil.getYear(mStandardCal), CalendarUtil.getMonth(mStandardCal), 1);
        int firstDayOfWeek = CalendarUtil.getDayOfWeek(firstDayCal);

        //补全之前的日历
        for (int a = 1; a < firstDayOfWeek; a++) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(CalendarUtil.getYear(mStandardCal), CalendarUtil.getMonth(mStandardCal), a+1 - firstDayOfWeek);
            calList.add(calendar);
        }


        //补全之前的日历
        for (int a = 0; a < CalendarUtil.getDaysCount(mStandardCal); a++) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(CalendarUtil.getYear(mStandardCal), CalendarUtil.getMonth(mStandardCal), a +1 );
            calList.add(calendar);
        }
        mouthAdapter = new MouthAdapter(calList);

        mGridView.setAdapter(mouthAdapter);
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mCurrentCalendar = calList.get(i);
                mouthAdapter.notifyDataSetChanged();
                //mXtCalendarView.refreshMcurrentCalendar(mCurrentCalendar);
                Toast.makeText(view.getContext(),CalendarUtil.toString(mCurrentCalendar),Toast.LENGTH_SHORT).show();

                monthCalendarView.mSelectDay = CalendarUtil.getDay(mCurrentCalendar);
            }
        });


    }


    private class MouthAdapter extends ListAdapter<Calendar> {


        public MouthAdapter(List<Calendar> datas) {
            super(datas);
        }

        @Override
        protected ListHolder<Calendar> getHolder(ViewGroup parent) {
            return new ListHolder<Calendar>(parent) {
                private TextView mTextview;

                @Override
                protected View initView(ViewGroup parent) {
                    View rootView = View.inflate(parent.getContext(), R.layout.item_cal, null);
                    mTextview = (TextView) rootView.findViewById(R.id.textview);
                    LayoutParams layoutParams =  new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
                    layoutParams.width = (UiUtils.getScreenPixelsWidth() - UiUtils.dip2px(10))/7;
                    layoutParams.height =  layoutParams.width;
                    mTextview.setLayoutParams(layoutParams);
                    return rootView;
                }

                @Override
                protected void refreshUI(Calendar data, int postion) {
                    if (CalendarUtil.isTheSameMouthAndYear(data, mStandardCal)) {
                        mTextview.setVisibility(VISIBLE);
                        mTextview.setText(CalendarUtil.getDay(data)+"");
                    } else {
                        mTextview.setVisibility(INVISIBLE);
                    }

                    if(CalendarUtil.isTheDayOfMouth(data, mCurrentCalendar)){
                        mTextview.setBackgroundColor(Color.RED);
                        monthCalendarView.mSellectCalendar = data;
                    }else {
                        mTextview.setBackgroundColor(Color.WHITE);
                    }
                }
            };
        }


    }

}
