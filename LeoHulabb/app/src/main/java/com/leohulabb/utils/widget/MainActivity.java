package com.leohulabb.utils.widget;

import android.os.Bundle;
import android.view.View;

import com.commonui.navigation.NavigationBar;
import com.commonui.navigation.WidgeButton;
import com.leohulabb.R;
import com.leohulabb.module.base.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        this.requestWindowFeature(android.view.Window.FEATURE_NO_TITLE);

        WidgeButton[] widgeButtons = new WidgeButton[] {
                new WidgeButton(this, R.string.base_back),
                new WidgeButton(this, R.string.base_confirm),
                new WidgeButton(this, R.string.base_back) };

        NavigationBar navigationBar = (NavigationBar) findViewById(R.id.navigationBar);
        navigationBar.setAppWidgeTitle("首页");
        navigationBar.setLeftMenus(widgeButtons);
        navigationBar.setRightMenu(new WidgeButton(this, R.string.base_back));

        widgeButtons[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
//
//        navigateTabBar = (TabStripView) findViewById(R.id.navigateTabBar);
//        //对应xml中的containerId
//        navigateTabBar.setFrameLayoutId(R.id.main_container);
//        //对应xml中的navigateTabTextColor
//        navigateTabBar.setTabTextColor(Color.parseColor("#5d5d5d"));
//        //对应xml中的navigateTabSelectedTextColor
//        navigateTabBar.setSelectedTabTextColor(Color.parseColor("#F3F3F3"));
//
//        //恢复选项状态
//        navigateTabBar.onRestoreInstanceState(savedInstanceState);
//
//        navigateTabBar.addTab(LoginFragment.class, new TabStripView.TabParam(R.mipmap.ic_tab_bar_home, R.mipmap.ic_tab_bar_home_selected, "首页"));
//        navigateTabBar.addTab(TestFragment.class, new TabStripView.TabParam(R.mipmap.ic_tab_bar_find, R.mipmap.ic_tab_bar_find_selected, "收藏"));
//        navigateTabBar.addTab(LoginFragment.class, new TabStripView.TabParam(R.mipmap.ic_tab_bar_person, R.mipmap.ic_tab_bar_person_selected, "会员中心"));
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_main;
    }

    @Override
    protected void onInitView() {

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //保存当前选中的选项状态
//        navigateTabBar.onSaveInstanceState(outState);
    }
}
