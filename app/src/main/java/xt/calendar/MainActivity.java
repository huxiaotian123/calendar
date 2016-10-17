package xt.calendar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import xt.calendar.widget.VariableViewPager;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        VariableViewPager viewPager = (VariableViewPager) findViewById(R.id.viewpager);
        ;viewPager.setCurrentCalendar(Calendar.getInstance());
//        viewPager.setMouthWeekListener(new MouthWeekListener() {
//            @Override
//            public void showMouthView() {
//
//            }
//
//            @Override
//            public void showWeekView() {
//
//            }
//        });

//        MouthView mouthView = (MouthView) findViewById(R.id.mouthview);
//        Calendar instance = Calendar.getInstance();
//         mouthView.setCurrentCalendar(instance);

    }





}
