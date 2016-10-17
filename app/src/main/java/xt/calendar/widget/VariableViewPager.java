package xt.calendar.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import xt.calendar.Listener.MouthWeekListener;

/**
 * Created by Administrator on 2016/10/13.
 */
public class VariableViewPager extends ViewPager  {

    private float deviation = 200;//偏移量
    private float startY; //起点位置
    private boolean isMoth = true;//默认是下面
    private MouthWeekListener mouthWeekListener;

    public VariableViewPager(Context context) {
        super(context);
    }

    public VariableViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                startY = ev.getY();
                Log.e("hxt","patcdown-----"+startY);
                break;
            case MotionEvent.ACTION_MOVE:
            case MotionEvent.ACTION_UP:
                float moveY = ev.getY() - startY;
                Log.e("hxt","patcmove-----"+moveY);
                if(Math.abs(moveY)>deviation){
                    if(moveY > 0 ){//下移
                        showMouth();
                    }else {//上移
                        showWeek();
                    }
                }
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

//    @Override
//    public boolean onTouchEvent(MotionEvent ev) {
//        switch (ev.getAction()){
//            case MotionEvent.ACTION_DOWN:
//                startY = ev.getY();
//                Log.e("hxt","down-----"+startY);
//                break;
//            case MotionEvent.ACTION_MOVE:
//            case MotionEvent.ACTION_UP:
//                float moveY = ev.getY() - startY;
//                Log.e("hxt","move-----"+moveY);
//                if(Math.abs(moveY)>deviation){
//                    if(moveY > 0 ){//下移
//                        showMouth();
//                    }else {//上移
//                        showWeek();
//                    }
//                }
//                break;
//
//        }
//        return super.onTouchEvent(ev);
//    }

    /**
     * 上移
     */
    private void showWeek() {
        if(isMoth){
            LinearLayout.LayoutParams LayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,200);
            this.setLayoutParams(LayoutParams);
            isMoth = false;
            if(null!= mouthWeekListener){
                mouthWeekListener.showWeekView();
            }
        }
    }

    /**
     * 需要下移
     */
    private void showMouth() {
        if(!isMoth){
            LinearLayout.LayoutParams LayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
            this.setLayoutParams(LayoutParams);
            isMoth = true;
            if(null!= mouthWeekListener){
                mouthWeekListener.showMouthView();
            }
        }
    }
}
