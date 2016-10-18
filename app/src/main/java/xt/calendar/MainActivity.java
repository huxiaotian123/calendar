package xt.calendar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import xt.calendar.widget.CalendarView;
import xt.calendar.widget.MouthView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CalendarView calendarView = (CalendarView) findViewById(R.id.viewpager);
        calendarView.setCurrentCalendar(Calendar.getInstance());

    }





}
