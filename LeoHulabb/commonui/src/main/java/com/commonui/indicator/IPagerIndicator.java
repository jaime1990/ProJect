package com.commonui.indicator;

import java.util.List;

/**
 * 抽象的viewpager指示器，适用于CommonNavigator
 * @author Leo
 * Created at 2016/9/26
 */
public interface IPagerIndicator {
    void onPageScrolled(int position, float positionOffset, int positionOffsetPixels);

    void onPageSelected(int position);

    void onPageScrollStateChanged(int state);

    void onPositionDataProvide(List<PositionData> dataList);
}
