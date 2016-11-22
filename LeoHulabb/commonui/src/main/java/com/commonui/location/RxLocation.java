package com.commonui.location;

import android.content.Context;

import com.amap.api.location.AMapLocation;

import rx.Observable;


/**
 * @desc:         RxLocation
 * @author:       Leo
 * @date:         2016/11/16
 */
public class RxLocation {

    private static RxLocation instance = new RxLocation();

    private RxLocation () {}

    public static RxLocation get() {
        return instance;
    }

    public Observable<AMapLocation> locate(Context context) {
        return Observable.create(new LocationOnSubscribe(context));
    }

    public Observable<AMapLocation> locateLastKnown(Context context) {
        return Observable.create(new LocationLateKnownOnSubscribe(context));
    }

}