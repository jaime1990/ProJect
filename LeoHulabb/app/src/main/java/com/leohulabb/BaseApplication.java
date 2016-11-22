package com.leohulabb;

import com.commonutils.ContextUtils;

/**
 * Create by Leo on 2016/7/7
 */
public class BaseApplication extends ContextUtils {

    private static BaseApplication ourInstance;

    public static BaseApplication getInstance() {
        return ourInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        ourInstance = this;
    }
}
