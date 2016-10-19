package xt.calendar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import xt.calendar.widget.XtCalendarView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        XtCalendarView xtCalendarView = (XtCalendarView) findViewById(R.id.viewpager);
        //xtCalendarView.setCurrentCalendar(Calendar.getInstance());
        xtCalendarView.setSelectCalendar(Calendar.getInstance());
    }





}
