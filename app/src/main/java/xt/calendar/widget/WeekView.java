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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Administrator on 2016/10/17.
 */
public class WeekView extends GridView {

    private Calendar mCurrentCalendar;
    private ArrayList<Calendar> calList;
    private WeekAdapter weekAdapter;

    public WeekView(Context context) {
        this(context, null);
    }

    public WeekView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WeekView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init() {
        setNumColumns(7);
    }

    public Calendar getCurrentCalendar() {
        return mCurrentCalendar;
    }

    public void setCurrentCalendar( Calendar currentCal) {
        this.mCurrentCalendar = currentCal;
        calList = new ArrayList();
        int dayOfWeek = CalendarUtil.getDayOfWeek(mCurrentCalendar);

        //补全之前的日历
        for (int a = 1; a < 8; a++) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(CalendarUtil.getYear(mCurrentCalendar), CalendarUtil.getMouth(mCurrentCalendar),CalendarUtil.getDay(mCurrentCalendar)-dayOfWeek+a);
            calList.add(calendar);
        }
        weekAdapter = new WeekAdapter(calList);
        setAdapter(weekAdapter);
        setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mCurrentCalendar = calList.get(i);
                weekAdapter.notifyDataSetChanged();
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
                    return rootView;
                }

                @Override
                protected void refreshUI(Calendar data, int postion) {
                    mTextview.setText(CalendarUtil.getDay(data)+"");
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
