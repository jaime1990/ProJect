package com.leohulabb.module.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;

import com.leohulabb.utils.widget.ProgressWheel;

/**
 * Create by Leo on 2016/7/7
 */
public abstract class BaseActivity extends AppCompatActivity {

    private ProgressWheel mLoadingView;
    protected BasePresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(getLayoutResource());
        mLoadingView = new ProgressWheel(this);
        onInitView();
    }

    protected abstract int getLayoutResource();

    protected abstract void onInitView();

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        // 打开Activity动画
    }

    public void showLoadingView() {
        mLoadingView.setVisibility(View.VISIBLE);
    }

    public void hideLoadingView() {
        mLoadingView.setVisibility(View.GONE);
    }

    //获得该页面的实例
//    public <T> T getLogicImpl(Class cls, BaseView o) {
//        return LogicProxy.getInstance().bind(cls, o);
//    }

    @Override
    public void finish() {
        super.finish();
        // 关闭动画
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null)
            mPresenter.detachView();
    }

}
