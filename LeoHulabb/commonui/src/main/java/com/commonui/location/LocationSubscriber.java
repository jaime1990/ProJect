package com.commonui.location;

import com.amap.api.location.AMapLocation;
import com.commonutils.EmptyUtils;

import rx.Subscriber;

public abstract class LocationSubscriber extends Subscriber<AMapLocation> {

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable throwable) {
        onLocatedFail(null);
    }

    @Override
    public void onNext(AMapLocation location) {
        if (EmptyUtils.isNotEmpty(location)) {
            onLocatedSuccess(location);
        } else {
            onLocatedFail(location);
        }
    }

    public abstract void onLocatedSuccess(AMapLocation location);
    public abstract void onLocatedFail(AMapLocation location);

}