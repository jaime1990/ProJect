package com.leohulabb.module.login;

import android.content.Intent;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.commonui.activity.base.BaseActivity;
import com.commonui.guideview.BGABanner;
import com.leohulabb.R;
import com.leohulabb.module.MainActivity;
import com.leohulabb.utils.BGABannerAdapter;

import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends BaseActivity implements View.OnClickListener
{
    private static final String TAG = GuideActivity.class.getSimpleName();
    private TextView mSkipTv;
    private Button mEnterBtn;
    private BGABanner mBackgroundBanner;
    private BGABanner mForegroundBanner;

    @Override
    public int getLayoutId()
    {
        return R.layout.activity_guide;
    }

    @Override
    public void initPresenter()
    {
    }

    @Override
    public void initView()
    {
        mSkipTv = (TextView) findViewById(R.id.tv_guide_skip);
        mEnterBtn = (Button) findViewById(R.id.btn_guide_enter);
        mBackgroundBanner = (BGABanner) findViewById(R.id.banner_guide_background);
        mForegroundBanner = (BGABanner) findViewById(R.id.banner_guide_foreground);
    }

    @Override
    public void setData()
    {
        processLogic();
    }

    @Override
    public void setListener() {
        mSkipTv.setOnClickListener(this);
        mEnterBtn.setOnClickListener(this);

        // 监听页码切换事件，控制「跳过按钮」和「进入主界面的显示与隐藏」
        mForegroundBanner.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position == mForegroundBanner.getItemCount() - 2) {
                    ViewCompat.setAlpha(mEnterBtn, positionOffset);
                    ViewCompat.setAlpha(mSkipTv, 1.0f - positionOffset);

                    if (positionOffset > 0.5f) {
                        mEnterBtn.setVisibility(View.VISIBLE);
                        mSkipTv.setVisibility(View.GONE);
                    } else {
                        mEnterBtn.setVisibility(View.GONE);
                        mSkipTv.setVisibility(View.VISIBLE);
                    }
                } else if (position == mForegroundBanner.getItemCount() - 1) {
                    mSkipTv.setVisibility(View.GONE);
                    mEnterBtn.setVisibility(View.VISIBLE);
                    ViewCompat.setAlpha(mEnterBtn, 1.0f);
                } else {
                    mSkipTv.setVisibility(View.VISIBLE);
                    ViewCompat.setAlpha(mSkipTv, 1.0f);
                    mEnterBtn.setVisibility(View.GONE);
                }
            }
        });
    }

    private void processLogic() {
        mBackgroundBanner.setOverScrollMode(View.OVER_SCROLL_NEVER);
        mForegroundBanner.setOverScrollMode(View.OVER_SCROLL_NEVER);

        // 初始化方式1：通过传入数据模型并结合Adapter的方式初始化
        mBackgroundBanner.setAdapter(new BGABannerAdapter(this));
        mForegroundBanner.setAdapter(new BGABannerAdapter(this));

        List<Integer> lists = new ArrayList<>();

        lists.add(R.mipmap.guide_main);
        lists.add(R.mipmap.guide_next);
        lists.add(R.mipmap.guide_third);

        List<Integer> forback = new ArrayList<>();

        forback.add(R.mipmap.uoko_guide_foreground_1);
        forback.add(R.mipmap.uoko_guide_foreground_2);
        forback.add(R.mipmap.uoko_guide_foreground_3);

        mBackgroundBanner.setData(lists, null);
        mForegroundBanner.setData(forback, null);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_guide_enter || view.getId() == R.id.tv_guide_skip) {
            startActivity(new Intent(GuideActivity.this, MainActivity.class));
            onBackPressed();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // 在界面可见时给背景Banner设置一个白色背景，避免滑动过程中两个Banner都设置透明度后能看到Launcher
        mBackgroundBanner.setBackgroundResource(android.R.color.white);
    }
}