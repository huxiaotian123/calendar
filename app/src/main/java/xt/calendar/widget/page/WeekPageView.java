package xt.calendar.widget.page;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import xt.calendar.R;
import xt.calendar.base.ListAdapter;
import xt.calendar.base.ListHolder;
import xt.calendar.util.CalendarUtil;
import xt.calendar.util.UiUtils;
import xt.calendar.widget.calendar.BaseCalendarView;
import xt.calendar.widget.calendar.MonthCalendarView;
import xt.calendar.widget.calendar.WeekCalendarView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Administrator on 2016/10/17.
 */
public class WeekPageView extends BasePageView {

    private ArrayList<Calendar> calList;
    private WeekAdapter weekAdapter;
    private WeekCalendarView weekCalendarView;

    public WeekPageView(Context context) {
        this(context, null);
    }

    public WeekPageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WeekPageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    @Override
    public void setCurrentCalendar(Calendar currentCal) {
        if(null == currentCal){
            return;
        }
        mCurrentCalendar = currentCal;
        weekAdapter.notifyDataSetChanged();
    }

    @Override
    public void initPage(Calendar cal, BaseCalendarView baseCalendarView) {
        mStandardCal = cal;
        weekCalendarView = (WeekCalendarView) baseCalendarView;
       calList = new ArrayList();
        int dayOfWeek = CalendarUtil.getDayOfWeek(mStandardCal);
        //补全之前的日历
        if (dayOfWeek == 1) {
            dayOfWeek = 7;
        } else {
            dayOfWeek--;
        }

        //补全之前的日历
        for (int a = 1; a < 8; a++) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(CalendarUtil.getYear(mStandardCal), CalendarUtil.getMonth(mStandardCal), CalendarUtil.getDay(mStandardCal) - dayOfWeek + a);
            calList.add(calendar);
        }
        weekAdapter = new WeekAdapter(calList);
        mGridView.setAdapter(weekAdapter);
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mCurrentCalendar = calList.get(i);
                weekAdapter.notifyDataSetChanged();
                Toast.makeText(view.getContext(),CalendarUtil.toString(mCurrentCalendar),Toast.LENGTH_SHORT).show();
            }
        });
    }


    private class WeekAdapter extends ListAdapter<Calendar> {


        public WeekAdapter(List<Calendar> datas) {
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
                    FrameLayout.LayoutParams layoutParams =  new  FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
                    layoutParams.width = (UiUtils.getScreenPixelsWidth() - UiUtils.dip2px(10))/7;
                    layoutParams.height =  layoutParams.width;
                    mTextview.setLayoutParams(layoutParams);
                    return rootView;
                }

                @Override
                protected void refreshUI(Calendar data, int postion) {
                    mTextview.setText(CalendarUtil.getDay(data) + "");
                    if (CalendarUtil.isTheDayOfMouth(data, mCurrentCalendar)) {
                        mTextview.setBackgroundResource(R.drawable.yellow_cir);
                        weekCalendarView.mSellectCalendar = data;
                    } else {
                        mTextview.setBackgroundColor(Color.WHITE);
                    }
                }
            };
        }
    }
}
