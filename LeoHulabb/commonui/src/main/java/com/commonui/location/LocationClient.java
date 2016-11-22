package com.commonui.location;

import android.content.Context;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;

public class LocationClient {

    private AMapLocationClient realClient;
    private static volatile LocationClient proxyClient;

    private AMapLocationClientOption locationOption = null;

    private LocationClient(Context context) {

        realClient = new AMapLocationClient(context);

        AMapLocationClientOption locationOption = new AMapLocationClientOption();

        locationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        locationOption.setNeedAddress(true);
        locationOption.setGpsFirst(true);
        locationOption.setOnceLocation(true);

        //设置高德定位参数
        realClient.setLocationOption(locationOption);
    }

    public static LocationClient get(Context context) {
        if (proxyClient == null) {
            synchronized (LocationClient.class) {
                if (proxyClient == null) {
                    proxyClient = new LocationClient(context.getApplicationContext());
                }
            }
        }
        return proxyClient;
    }

    /**
     * 开启定位
     * @param locationListener 定位监听
     */
    public void locate(final AMapLocationListener locationListener) {
        AMapLocationListener listener = new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation aMapLocation) {
                locationListener.onLocationChanged(aMapLocation);
                realClient.unRegisterLocationListener(this);
                destoryLocation();
                if (aMapLocation != null) {
                    System.out.println("Latitude:" + aMapLocation.getLatitude() + "," + "Longitude:" + aMapLocation.getLongitude());
                    System.out.println("Accuracy:" + aMapLocation.getAccuracy() + "," + "Speed" + aMapLocation.getSpeed());
                } else {
                    System.out.println("定位失败");
                }
            }
        };

        realClient.setLocationListener(listener);

        if (!realClient.isStarted()) {
            realClient.startLocation();
        }
    }

    public void destoryLocation()
    {
        if (null != realClient) {
            realClient.stopLocation();
            realClient.onDestroy();        //销毁定位客户端，同时销毁本地定位服务。
            realClient = null;
            locationOption = null;
        }
    }

    //获取上次定位地址
    public AMapLocation getLateKnownLocation() {
        return realClient.getLastKnownLocation();
    }
}