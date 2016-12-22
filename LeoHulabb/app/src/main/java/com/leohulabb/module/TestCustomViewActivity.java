package com.leohulabb.module;

import com.commonui.activity.base.BaseActivity;
import com.commonui.view.LRadarView;
import com.commonui.view.MyPieView;
import com.commonui.view.MyRadarView;
import com.commonui.view.MySlantedTextView;
import com.commonui.view.SmileyLoadingView;
import com.leohulabb.R;

public class TestCustomViewActivity extends BaseActivity {

    private MyPieView vPieView;
    private MyRadarView vRadView;
    private LRadarView lRadView;
    private MySlantedTextView mySlantedTextView;
    private SmileyLoadingView smileyLoadingView;

    @Override
    public int getLayoutId() {
        return R.layout.activity_customview;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        mySlantedTextView = (MySlantedTextView) findViewById(R.id.couponView);

        vPieView = (MyPieView) findViewById(R.id.v_pie);
        vRadView = (MyRadarView) findViewById(R.id.v_rad);
        lRadView = (LRadarView) findViewById(R.id.lrv_view);
        smileyLoadingView = (SmileyLoadingView) findViewById(R.id.smile);
    }

    @Override
    public void setData() {

    }

    @Override
    public void setListener() {

    }

}