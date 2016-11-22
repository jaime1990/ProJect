package com.commonui.location;

import android.content.Context;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.commonutils.EmptyUtils;

import rx.Observable;
import rx.Subscriber;

/**
 * @desc:         获取上次定位信息
 * @author:       Leo
 * @date:         2016/11/16
 */
public class LocationLateKnownOnSubscribe implements Observable.OnSubscribe<AMapLocation> {

    private final Context context;

    public LocationLateKnownOnSubscribe(Context context) {
        this.context = context;
    }

    @Override
    public void call(final Subscriber<? super AMapLocation> subscriber) {
        AMapLocation lateKnownLocation = LocationClient.get(context).getLateKnownLocation();
        if (EmptyUtils.isNotEmpty(lateKnownLocation)) {
            subscriber.onNext(lateKnownLocation);
            subscriber.onCompleted();
        } else {
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
}