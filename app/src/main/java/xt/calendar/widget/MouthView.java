package xt.calendar.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
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
public class MouthView extends GridView {
    public MouthView(Context context) {
        this(context, null);
    }

    public MouthView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MouthView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init() {
        setNumColumns(7);
    }


    private Calendar mCurrentCalendar;

    public Calendar getCurrentCalendar() {
        return mCurrentCalendar;
    }

    public void setCurrentCalendar(Calendar mCurrentCalendar) {
        this.mCurrentCalendar = mCurrentCalendar;

        ArrayList<Calendar> list = new ArrayList();
        Calendar firstDayCal = Calendar.getInstance();
        firstDayCal.set(CalendarUtil.getYear(mCurrentCalendar), CalendarUtil.getMouth(mCurrentCalendar), 1);
        int firstDayOfWeek = CalendarUtil.getDayOfWeek(firstDayCal);

        //补全之前的日历
        for (int a = 1; a < firstDayOfWeek; a++) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(CalendarUtil.getYear(mCurrentCalendar), CalendarUtil.getMouth(mCurrentCalendar), a+1 - firstDayOfWeek);
            int mouth = CalendarUtil.getMouth(calendar);
            int day = CalendarUtil.getDay(calendar);

            list.add(calendar);
        }


        //补全之前的日历
        for (int a = 0; a < CalendarUtil.getDaysCount(mCurrentCalendar); a++) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(CalendarUtil.getYear(mCurrentCalendar), CalendarUtil.getMouth(mCurrentCalendar), a +1 );
            list.add(calendar);
        }

        setAdapter(new MouthAdapter(list));
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
                    return rootView;
                }

                @Override
                protected void refreshUI(Calendar data, int postion) {
                    if (CalendarUtil.isTheSameMouth(data, mCurrentCalendar)) {
                        mTextview.setText(CalendarUtil.getDay(data)+"");
                    } else {
                        mTextview.setText("");
                    }
                }
            };
        }


    }

}
