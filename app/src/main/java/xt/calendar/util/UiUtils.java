package xt.calendar.util;

import android.content.Context;
import xt.calendar.base.BaseApp;

/**
 * Created by Administrator on 2016/10/19.
 */
public class UiUtils {
    public static Context context = BaseApp.mApp;
    /**
     * 获取屏幕宽度的像素值
     */
    public static int getScreenPixelsWidth() {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    public static int dip2px( float dipValue) {
        return (int) (dipValue * getScreenScale() + 0.5f);
    }

    /**
     * 屏幕像素值转换为dip值
     *
     * @param pxValue 屏幕像素值
     * @return int 屏幕dip值
     */
    public static int px2dip(float pxValue) {
         return (int) (pxValue / getScreenDensity() + 0.5f);
    }



    public static float getScreenScale() {
        try {
            return context.getResources().getDisplayMetrics().density;
        } catch (Throwable e) {
            return 1;
        }
    }

    public static float getScreenDensity() {
        try {
            return context.getResources().getDisplayMetrics().density;
        } catch (Throwable e) {
            return 1;
        }
    }
}
