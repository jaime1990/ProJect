package com.leohulabb.splash;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.commonui.R;
import com.commonui.activity.base.BaseActivity;
import com.leohulabb.module.MainActivity;
import com.leohulabb.module.login.GuideActivity;
import com.leohulabb.splash.contract.SplashContract;
import com.leohulabb.splash.model.SplashModelImpl;
import com.leohulabb.splash.presenter.SplashPresenterImpl;

/**
 * 启动页
 * Created by zwl on 16/9/5.
 */
public class SplashActivity extends BaseActivity<SplashPresenterImpl, SplashModelImpl> implements SplashContract.View
{
    private ImageView ivLogo;
    private LinearLayout tvName;

    @Override
    public int getLayoutId() {
        return R.layout.activity_guide;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    public void initView() {
        ivLogo = findView(R.id.iv_logo);
        tvName = findView(R.id.tv_name);
    }

    @Override
    public void setData() {
        PropertyValuesHolder alpha = PropertyValuesHolder.ofFloat("alpha", 0.3f, 1f);
        PropertyValuesHolder scaleX = PropertyValuesHolder.ofFloat("scaleX", 0.3f, 1f);
        PropertyValuesHolder scaleY = PropertyValuesHolder.ofFloat("scaleY", 0.3f, 1f);
        ObjectAnimator objectAnimator1 = ObjectAnimator.ofPropertyValuesHolder(tvName, alpha, scaleX, scaleY);
        ObjectAnimator objectAnimator2 = ObjectAnimator.ofPropertyValuesHolder(ivLogo, alpha, scaleX, scaleY);

        mPresenter.checkIsFirstIn(context, objectAnimator1, objectAnimator2);
    }

    @Override
    public void setListener() {
    }

    @Override
    public void readyToMain() {
        startActivity(MainActivity.class);
        finish();
    }

    @Override
    public void readyToGuide() {
        startActivity(GuideActivity.class);
        finish();
    }
}
