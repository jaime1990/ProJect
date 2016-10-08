package com.commonui.listener;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;

import com.commonutils.NetworkUtils;

/**
 * @desc:         点击时判断网络是否连接
 * @author:       Leo
 * @date:         2016/10/8
 */
public abstract class OnClickNetworkListener extends BaseClickListener
{
    @Override
    public void onClick(View v) {
        boolean isNetworkOk = NetworkUtils.isWorked(v.getContext());

        if (isNetworkOk) {
            onNetworkClick(v);
        } else {
            onNoNetworkClick(v);
        }
    }

    // 点击事件--有网络
    public abstract void onNetworkClick(View v);

    // 点击事件--没有网络
    public abstract void onNoNetworkClick(View v);

    /**
     * 网络连接是否正常
     * @param context 上下文
     * @return 网络是否连接
     */
    public static boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }
}