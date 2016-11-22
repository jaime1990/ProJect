package com.leohulabb.splash;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.commonui.activity.FragmentController;
import com.commonui.activity.base.BaseActivity;
import com.commonui.navigation.NavigationBar;
import com.commonui.navigation.WidgeButton;
import com.commonui.tablayout.BottomTabLayout;
import com.commonui.tablayout.CustomTabEntity;
import com.commonui.tablayout.OnTabSelectListener;
import com.commonui.tablayout.TabEntity;
import com.commonutils.LogUtils;
import com.leohulabb.R;
import com.leohulabb.module.ButtonFragment;
import com.leohulabb.module.LoginFragment;
import com.leohulabb.module.TestFragment;

import java.util.ArrayList;

public class MainActivity extends BaseActivity
{
    private NavigationBar navigationBar;

    //记录最近一次点击的位置
    private int clickPosition;

    //Fragment管理类
    private FragmentController fragmentController;

    //模块名
    private String[] mTitles = {"活动", "赛事", "我的"};

    //模块选中图片
    private int[] mIconUnselectIds = {R.mipmap.menu_home, R.mipmap.menu_fashaoyou, R.mipmap.menu_personal};

    //模块未选中图片
    private int[] mIconSelectIds = {R.mipmap.menu_home_hov, R.mipmap.menu_fashaoyou_hov, R.mipmap.menu_personal_hov};

    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private BottomTabLayout mTabLayout;

    private LoginFragment newsMainFragment;
    private TestFragment photosMainFragment;
    private ButtonFragment buttonFragment;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initPresenter() {
    }

    @Override
    public void initView() {

        WidgeButton[] widgeButtons = new WidgeButton[] {
                new WidgeButton(this, R.string.base_back),
                new WidgeButton(this, R.string.base_confirm),
                new WidgeButton(this, R.string.base_back) };

        navigationBar = (NavigationBar) findViewById(R.id.navigationBar);
        navigationBar.setAppWidgeTitle("首页");
        navigationBar.setLeftMenus(widgeButtons);
        navigationBar.setRightMenu(new WidgeButton(this, R.string.base_back));

        initTab();
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

        mTabLayout = (BottomTabLayout) findViewById(R.id.tab_layout);

        mTabLayout.setTabData(mTabEntities);
        mTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
//                if (position == 2 && EmptyUtils.isEmpty(DataCenter.getInstance().getToken())) {
//                    mTabLayout.setCurrentTab(clickPosition);
//                    DataUtil.certiLogin(context);
//                } else {
                    clickPosition = position;
                    fragmentController.showFragment(position);
//                }
            }

            @Override
            public void onTabReselect(int position) {
                // TODO: 2016/11/17  单个Tab重复点击
            }
        });
    }

    /**
     * 初始化碎片
     */
    @Override
    public void initFragment(Bundle savedInstanceState) {
        super.initFragment(savedInstanceState);
        int currentTabPosition = 0;
        if (savedInstanceState != null) {
            newsMainFragment = (LoginFragment) getSupportFragmentManager().findFragmentByTag("newsMainFragment");
            photosMainFragment = (TestFragment) getSupportFragmentManager().findFragmentByTag("photosMainFragment");
            buttonFragment = (ButtonFragment) getSupportFragmentManager().findFragmentByTag("buttonFragment");
            currentTabPosition = savedInstanceState.getInt("HOME_CURRENT_TAB_POSITION");
        } else {
            newsMainFragment = new LoginFragment();
            photosMainFragment = new TestFragment();
            buttonFragment = new ButtonFragment();

        }

        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(newsMainFragment);
        fragments.add(photosMainFragment);
        fragments.add(buttonFragment);

        fragmentController = FragmentController.getInstance(this, R.id.main_container, fragments);

        mTabLayout.setCurrentTab(currentTabPosition);
        fragmentController.showFragment(currentTabPosition);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //保存当前选中的选项状态
        //奔溃前保存位置
        LogUtils.e("TAG", "onSaveInstanceState进来了1");
        if (mTabLayout != null) {
            LogUtils.e("TAG", "onSaveInstanceState进来了2");
            outState.putInt("HOME_CURRENT_TAB_POSITION", mTabLayout.getCurrentTab());
        }
    }
}
