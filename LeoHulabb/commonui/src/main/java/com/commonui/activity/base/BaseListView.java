package com.commonui.activity.base;

 /**
  * @desc:         数据加载基本状态
  * @author:       Leo
  * @date:         2016/10/17
  */
public interface BaseListView
 {
    //显示加载页
    void showProgress();
    //关闭加载页
    void hideProgress();
    //显示加载失败
    void showLoadFailMsg();
    //显示已加载所有数据
    void showLoadCompleteAllData();
    //显示无数据
    void showNoData();
}
