package com.commonui.indicator;

import android.support.v4.view.ViewPager;

/**
 * 简化和ViewPager绑定
 * @author Leo
 * Created at 2016/9/26
 */
public class ViewPagerHelper
{
    /**
     * 绑定indicator与viewpager
     * @param magicIndicator 自定义indicator
     * @param viewPager      滑页指示器
     */
    public static void bind(final MagicIndicator magicIndicator, ViewPager viewPager) {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                magicIndicator.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                magicIndicator.onPageSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                magicIndicator.onPageScrollStateChanged(state);
            }
        });
    }
}
