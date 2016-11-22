package com.commonui.activity.mvp;

import com.commonutils.EmptyUtils;
import com.commonutils.baserx.RxManager;

import java.lang.ref.SoftReference;


/**
 * @desc:         基类Presenter
 * @author:       Leo
 * @date:         2016/10/26
 */
public abstract class BasePresenter<V extends BaseView> {

    private SoftReference<V> mViewRef;
    protected RxManager rxManager;
    protected V mView;

    public void attachView(V view) {
        if (EmptyUtils.isNull(view)) {
            throw new NullPointerException("BasePresenter#attechView view can not be null");
        }
        mViewRef = new SoftReference<>(view);
        rxManager = new RxManager();
        mView = view;
    }

    protected boolean isViewAttached() {
        return EmptyUtils.isNotEmpty(mViewRef) && EmptyUtils.isNotEmpty(mViewRef.get());
    }

    protected V getView() {
        return mViewRef.get();
    }

    public void detachView() {
        if (isViewAttached()) {
            mViewRef.clear();
            mViewRef = null;
            rxManager.clear();
        }
    }
}
