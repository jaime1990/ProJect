package com.commonui.activity.mvp;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.commonui.activity.listener.OnOnceClickListener;
import com.commonui.toast.ToastManager;

/**
 * @desc:         MVP模式
 * @author:       Leo
 * @date:         2016/10/26
 * @param <V>     扩展View
 * @param <P>     扩展Presenter
 */
public abstract class BaseMvpActivity<V extends BaseView, P extends BasePresenter<V>>
        extends FragmentActivity implements BaseView {

    protected P presenter;

    @SuppressWarnings("unchecked")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = createPresenterInstance();
        if (presenter != null) {
            presenter.attachView((V) this);
        }
        setContentView(getLayoutId());
        onViewCreated();
    }

    //初始化Presenter在setContentView之前
    protected abstract P createPresenterInstance();

    protected abstract int getLayoutId();

    /**
     * Invoked after {@link #setContentView(int)}
     */
    protected abstract void onViewCreated();

    @Override
    public Activity visitActivity() {
        return getActivity();
    }

    protected Activity getActivity() {
        return this;
    }

    @Override
    public void showToastMsg(String msg) {
        ToastManager.shortShow(msg);
    }

    @Override
    public void showToastMsg(int resId) {
        ToastManager.shortShow(resId);
    }

    @Override
    public void showProgressingDialog() {

    }

    @Override
    public void dismissProgressDialog() {

    }

    /**
     * Invoke the method after you have implemented method {@link BaseMvpActivity#onViewClicked(View, int)}
     *
     * @param id id of View
     */
    protected void attachClickListener(int id) {
        View view = findViewById(id);
        if (view != null) {
            view.setOnClickListener(clickListener);
        }
    }

    private OnOnceClickListener clickListener = new OnOnceClickListener() {
        @Override
        public void onOnceClick(View v) {
            onViewClicked(v, v.getId());
        }
    };

    /**
     * Clicked views' implementation
     *
     * @param view which view has clicked
     * @param id   id of View
     */
    protected void onViewClicked(View view, int id) {

    }

    @Override
    protected void onDestroy() {
        if (presenter != null) {
            presenter.detachView();
        }
        super.onDestroy();
    }
}