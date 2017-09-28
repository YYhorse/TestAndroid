package com.testandroid.www;

import android.app.Application;

import org.xutils.x;

/**
 * Created by yy on 2017/8/24.
 */
public class BaseApplication extends Application {
    public static BaseApplication instance;
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        x.Ext.init(this);
    }

    public static BaseApplication getInstance() {
        return instance;
    }
}
