package com.commonui.location;

import android.content.Context;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;

import rx.Observable;
import rx.Subscriber;


/**
 * @desc:         立即开启定位
 * @author:       Leo
 * @date:         2016/11/16
 */
public class LocationOnSubscribe implements Observable.OnSubscribe<AMapLocation> {

    private final Context context;

    public LocationOnSubscribe(Context context) {
        this.context = context;
    }

    @Override
    public void call(final Subscriber<? super AMapLocation> subscriber) {
        AMapLocationListener locationListener = new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation aMapLocation) {
                subscriber.onNext(aMapLocation);
                subscriber.onCompleted();
            }
        };
        LocationClient.get(context).locate(locationListener);
    }
}