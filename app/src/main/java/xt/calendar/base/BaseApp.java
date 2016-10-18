package xt.calendar.base;

import android.app.Application;

/**
 * Created by Administrator on 2016/10/19.
 */
public class BaseApp extends Application {

    public static BaseApp mApp = null;

    public BaseApp() {
        super();
        mApp = this;
    }
}
