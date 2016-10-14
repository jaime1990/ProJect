package com.leohulabb.splash.presenter;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.content.Context;
import android.view.animation.AccelerateInterpolator;

import com.commonutils.LogUtils;
import com.leohulabb.splash.contract.SplashContract;

/**
* Created by MVPHelper on 2016/10/10
*/

public class SplashPresenterImpl extends SplashContract.Presenter {

    @Override
    public void onStart() {
        super.onStart();
        LogUtils.e("Splash", "start");
    }

    @Override
    public void checkIsFirstIn(Context context, Animator... items)
    {
        final boolean isFirstIn = false;

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(items[0], items[1]);
        animatorSet.setInterpolator(new AccelerateInterpolator());
        animatorSet.setDuration(3000);
        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                if (isFirstIn) {
                    mView.readyToGuide();
                } else
                    mView.readyToMain();
            }

            @Override
            public void onAnimationCancel(Animator animator) {
            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        animatorSet.start();
    }
}