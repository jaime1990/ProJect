package com.leohulabb.module.base;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.leohulabb.utils.widget.ProgressWheel;

/**
 * Create by Leo on 2016/7/7
 */
public abstract class BaseFragment extends Fragment
{
    protected View rootView;
    private ProgressWheel mLoginView;
    protected BasePresenter mPresenter;
    protected Context context;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null)
            rootView = inflater.inflate(getLayoutResource(), container, false);

        context = getActivity();
        mLoginView = new ProgressWheel(context);
        onInitView();
        return rootView;
    }

    protected abstract int getLayoutResource();

    protected abstract void onInitView();

    public void showLoadingView() {
        mLoginView.setVisibility(View.VISIBLE);
    }

    public void hideLoadingView() {
        mLoginView.setVisibility(View.GONE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mPresenter != null)
            mPresenter.detachView();
    }

}
