package com.commonui.activity.base;

import android.content.Context;

import com.commonutils.baserx.RxBusManager;

/**
  * @desc:         MVP基类Presenter
  * @author:       Leo
  * @date:         2016/10/17
  */
public abstract class BasePresenter<T, E>
{
    public Context context;
    public E mModel;
    public T mView;
    public RxBusManager mRxManage = new RxBusManager();

    public void setVM(T v, E m) {
        this.mView = v;
        this.mModel = m;
        this.onStart();
    }

    public void onStart(){
    }
    
    public void onDestroy() {
        mRxManage.clear();
    }
}
