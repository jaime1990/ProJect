package com.leohulabb.splash;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.ViewGroup;

import com.commonui.activity.base.BaseActivity;
import com.commonui.navigation.NavigationBar;
import com.commonui.navigation.WidgeButton;
import com.commonui.tabview.CommonBottomTabLayout;
import com.commonui.tabview.CustomTabEntity;
import com.commonui.tabview.OnTabSelectListener;
import com.commonutils.LogUtils;
import com.leohulabb.R;
import com.leohulabb.module.TabEntity;
import com.leohulabb.module.login.LoginFragment;
import com.leohulabb.module.login.TestFragment;

import java.util.ArrayList;

public class MainActivity extends BaseActivity
{
    private CommonBottomTabLayout tabLayout;
    private NavigationBar navigationBar;

    private String[] mTitles = {"美女","视频"};
    private int[] mIconUnselectIds = {
            R.mipmap.ic_tab_bar_home,R.mipmap.ic_tab_bar_find,R.mipmap.ic_tab_bar_person,R.mipmap.ic_tab_bar_home};
    private int[] mIconSelectIds = {
            R.mipmap.ic_tab_bar_home_selected,R.mipmap.ic_tab_bar_find_selected, R.mipmap.ic_tab_bar_person_selected,R.mipmap.ic_tab_bar_home_selected};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();

    private LoginFragment newsMainFragment;
    private TestFragment photosMainFragment;
    private static int tabLayoutHeight;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initPresenter() {
    }

    @Override
    public void initView() {
        tabLayout = (CommonBottomTabLayout) findViewById(R.id.tab_layout);

        WidgeButton[] widgeButtons = new WidgeButton[] {
                new WidgeButton(this, R.string.base_back),
                new WidgeButton(this, R.string.base_confirm),
                new WidgeButton(this, R.string.base_back) };

        navigationBar = (NavigationBar) findViewById(R.id.navigationBar);
        navigationBar.setAppWidgeTitle("首页");
        navigationBar.setLeftMenus(widgeButtons);
        navigationBar.setRightMenu(new WidgeButton(this, R.string.base_back));

        initTab();
        //初始化frament
        tabLayout.measure(0,0);
        tabLayoutHeight=tabLayout.getMeasuredHeight();
    }

    @Override
    public void setData() {
    }


    @Override
    public void setListener() {

    }

    /**
     * 初始化tab
     */
    private void initTab() {
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }
        tabLayout.setTabData(mTabEntities);
        //点击监听
        tabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                SwitchTo(position);
            }
            @Override
            public void onTabReselect(int position) {
            }
        });
    }

    /**
     * 初始化碎片
     */
    @Override
    public void initFragment(Bundle savedInstanceState) {
        super.initFragment(savedInstanceState);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        int currentTabPosition = 0;
        if (savedInstanceState != null) {
            newsMainFragment = (LoginFragment) getSupportFragmentManager().findFragmentByTag("newsMainFragment");
            photosMainFragment = (TestFragment) getSupportFragmentManager().findFragmentByTag("photosMainFragment");
            currentTabPosition = savedInstanceState.getInt("HOME_CURRENT_TAB_POSITION");
        } else {
            newsMainFragment = new LoginFragment();
            photosMainFragment = new TestFragment();

            transaction.add(R.id.main_container, newsMainFragment, "newsMainFragment");
            transaction.add(R.id.main_container, photosMainFragment, "photosMainFragment");
        }
        transaction.commit();
        SwitchTo(currentTabPosition);
        tabLayout.setCurrentTab(currentTabPosition);
    }

    /**
     * 切换
     */
    private void SwitchTo(int position) {
        LogUtils.d("TAG", "主页菜单position" + position);
        tabLayout.showDot(position);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (position) {
            //首页
            case 0:
                transaction.hide(photosMainFragment);
                transaction.show(newsMainFragment);
                transaction.commitAllowingStateLoss();
                break;
            //美女
            case 1:
                transaction.hide(newsMainFragment);
                transaction.show(photosMainFragment);
                transaction.commitAllowingStateLoss();
                break;
            //视频
            case 2:
                transaction.hide(newsMainFragment);
                transaction.hide(photosMainFragment);
                transaction.commitAllowingStateLoss();
                break;
            //关注
            case 3:
                transaction.hide(newsMainFragment);
                transaction.hide(photosMainFragment);
                transaction.commitAllowingStateLoss();
                break;
            default:
                break;
        }
    }

    /**
     * 菜单显示隐藏动画
     * @param showOrHide
     */
    private void startAnimation(boolean showOrHide){
        final ViewGroup.LayoutParams layoutParams = tabLayout.getLayoutParams();
        ValueAnimator valueAnimator;
        ObjectAnimator alpha;
        if(!showOrHide){
            valueAnimator = ValueAnimator.ofInt(tabLayoutHeight, 0);
            alpha = ObjectAnimator.ofFloat(tabLayout, "alpha", 1, 0);
        }else{
            valueAnimator = ValueAnimator.ofInt(0, tabLayoutHeight);
            alpha = ObjectAnimator.ofFloat(tabLayout, "alpha", 0, 1);
        }
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                layoutParams.height= (int) valueAnimator.getAnimatedValue();
                tabLayout.setLayoutParams(layoutParams);
            }
        });
        AnimatorSet animatorSet=new AnimatorSet();
        animatorSet.setDuration(500);
        animatorSet.playTogether(valueAnimator,alpha);
        animatorSet.start();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //保存当前选中的选项状态
        //奔溃前保存位置
        LogUtils.e("TAG", "onSaveInstanceState进来了1");
        if (tabLayout != null) {
            LogUtils.e("TAG", "onSaveInstanceState进来了2");
            outState.putInt("HOME_CURRENT_TAB_POSITION", tabLayout.getCurrentTab());
        }
    }
}
