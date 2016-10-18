package xt.calendar.widget;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import xt.calendar.R;
import xt.calendar.base.ListAdapter;
import xt.calendar.base.ListHolder;
import xt.calendar.util.CalendarUtil;
import xt.calendar.util.UiUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Administrator on 2016/10/17.
 */
public class MouthView extends BaseDateView {

    private ArrayList<Calendar> calList;
    private MouthAdapter mouthAdapter;

    public MouthView(Context context) {
        this(context, null);
    }

    public MouthView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MouthView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }


    @Override
    public void setCurrentCalendar( Calendar currentCal) {
        this.mCurrentCalendar = currentCal;

        calList = new ArrayList();
        Calendar firstDayCal = Calendar.getInstance();
        firstDayCal.set(CalendarUtil.getYear(mCurrentCalendar), CalendarUtil.getMouth(mCurrentCalendar), 1);
        int firstDayOfWeek = CalendarUtil.getDayOfWeek(firstDayCal);

        //补全之前的日历
        for (int a = 1; a < firstDayOfWeek; a++) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(CalendarUtil.getYear(mCurrentCalendar), CalendarUtil.getMouth(mCurrentCalendar), a+1 - firstDayOfWeek);
            calList.add(calendar);
        }


        //补全之前的日历
        for (int a = 0; a < CalendarUtil.getDaysCount(mCurrentCalendar); a++) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(CalendarUtil.getYear(mCurrentCalendar), CalendarUtil.getMouth(mCurrentCalendar), a +1 );
            calList.add(calendar);
        }
        mouthAdapter = new MouthAdapter(calList);

        mGridView.setAdapter(mouthAdapter);
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mCurrentCalendar = calList.get(i);
                mouthAdapter.notifyDataSetChanged();
                mCalendarView.refreshMcurrentCalendar(mCurrentCalendar);
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
                    if (CalendarUtil.isTheSameMouthAndYear(data, mCurrentCalendar)) {
                        mTextview.setVisibility(VISIBLE);
                        mTextview.setText(CalendarUtil.getDay(data)+"");
                    } else {
                        mTextview.setVisibility(INVISIBLE);
                    }

                    if(CalendarUtil.isTheDayOfMouth(data, mCurrentCalendar)){
                        mTextview.setBackgroundColor(Color.RED);
                    }else {
                        mTextview.setBackgroundColor(Color.WHITE);
                    }
                }
            };
        }


    }

}
