package xt.calendar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import xt.calendar.net.NetUtil;
import xt.calendar.net.QMBStringCallback;
import xt.calendar.net.QmbRequestUrl;
import xt.calendar.util.CalendarUtil;
import xt.calendar.widget.XtCalendarView;

import java.util.Calendar;
import java.util.TreeMap;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        XtCalendarView xtCalendarView = (XtCalendarView) findViewById(R.id.viewpager);
        //xtCalendarView.setCurrentCalendar(Calendar.getInstance());

        Calendar calendar = Calendar.getInstance();

        TreeMap map = new TreeMap();
        map.put("date", CalendarUtil.getDate(calendar));
        NetUtil.doPost(QmbRequestUrl.GET_BABY_RECORDLIST, map, new QMBStringCallback() {
            @Override
            public void onQmbResponse(String response, int id) {

            }
        });

        xtCalendarView.setSelectCalendar(Calendar.getInstance());
    }


}
