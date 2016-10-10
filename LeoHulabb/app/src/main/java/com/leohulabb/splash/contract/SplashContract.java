package com.leohulabb.splash.contract;

import android.animation.Animator;
import android.content.Context;

import com.commonui.activity.base.BaseModel;
import com.commonui.activity.base.BasePresenter;

/**
 * @desc:
 * @author: Leo
 * @date:   2016/10/10
 */
public interface SplashContract
{
    interface View {
        void readyToMain();
        void readyToGuide();
    }

    abstract class Presenter extends BasePresenter<View, Model> {
        public abstract void checkIsFirstIn(Context context, Animator... items);
    }

    interface Model extends BaseModel {
    }
}