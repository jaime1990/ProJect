package com.commonui.indicator;

/**
 * 可测量内容区域的指示器标题
 * @author Leo
 * Created at 2016/9/26
 */
public interface IMeasurablePagerTitleView extends IPagerTitleView
{
    int getContentLeft();

    int getContentTop();

    int getContentRight();

    int getContentBottom();
}
