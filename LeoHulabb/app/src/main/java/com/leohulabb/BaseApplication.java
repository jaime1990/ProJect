package com.leohulabb;

import android.app.Application;
import android.content.Context;

import com.squareup.picasso.Picasso;

/**
 * Create by Leo on 2016/7/7
 */
public class BaseApplication extends Application {

    private static BaseApplication ourInstance = new BaseApplication();
    private static Context mContext;

    public static BaseApplication getInstance() {
        return ourInstance;
    }

    public static Context getContext() {
        return mContext;
    }
    public static Picasso mPicasso;

    @Override
    public void onCreate() {
        super.onCreate();
        ourInstance = this;
        mContext = getApplicationContext();

//        Picasso.Builder picassoBuilder = PicassoUtils.setPicassoConfig(getContext());
//
//        if(BuildConfig.DEBUG)
//            { picassoBuilder.loggingEnabled(true); }
//        if(isLowMemoryDevice())
//            { picassoBuilder.defaultBitmapConfig(Bitmap.Config.RGB_565); }
//
//        Picasso.setSingletonInstance(picassoBuilder.build());
//
//        setmPicasso(picassoBuilder.build());
    }

//    private boolean isLowMemoryDevice() {
//        if(Build.VERSION.SDK_INT >= 19) {
//            return ((ActivityManager) getSystemService(ACTIVITY_SERVICE)).isLowRamDevice();
//        } else {
//            return false;
//        }
//    }
//
//    public static Picasso getmPicasso() {
//        return mPicasso;
//    }
//
//    public static void setmPicasso(Picasso picasso) {
//        mPicasso = picasso;
//    }
}
