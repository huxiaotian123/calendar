package xt.calendar;

import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import xt.calendar.util.CalendarUtil;
import xt.calendar.widget.MouthView;
import xt.calendar.widget.VariableViewPager;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        VariableViewPager viewPager = (VariableViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new ViewPagerAdapter());
//        MouthView mouthView = (MouthView) findViewById(R.id.mouthview);
//        Calendar instance = Calendar.getInstance();
//         mouthView.setCurrentCalendar(instance);

    }



    public class ViewPagerAdapter extends PagerAdapter{

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
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.MONTH, CalendarUtil.getMouth(cal)+position);
            mouthView.setCurrentCalendar(cal);
            container.addView(mouthView);
            return mouthView;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
