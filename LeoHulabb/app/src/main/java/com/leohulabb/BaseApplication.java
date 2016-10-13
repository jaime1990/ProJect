package com.leohulabb;

import android.app.Application;
import android.content.Context;

import com.squareup.picasso.Picasso;

/**
 * Create by Leo on 2016/7/7
 */
public class BaseApplication extends Application {

    private static BaseApplication ourInstance;
    private static Context mContext;

    public static BaseApplication getInstance() {
        return ourInstance;
    }

    public static Context getContext() {
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        ourInstance = this;
        mContext = getApplicationContext();
    }
}
