package com.leohulabb.testmsp.contract;

import com.commonui.activity.base.BaseModel;
import com.commonui.activity.base.BasePresenter;
import com.leohulabb.data.TestData;

import java.util.ArrayList;

/**
 * @desc:
 * @author: Leo
 * @date: 2016/10/11
 */
public class TestListContract
{
    public interface View {
        //显示加载页
        void showProgress();
        //关闭加载页
        void hideProgress();
        //加载新数据
        void loadData(ArrayList<TestData> list);
        //添加更多数据
        void addDatas(ArrayList<TestData> list);
        //显示加载失败
        void showLoadFailMsg();
        //显示已加载所有数据
        void showLoadCompleteAllData();
        //显示无数据
        void showNoData();

    }

    public abstract static class Presenter extends BasePresenter<View, Model> {
        public abstract void loadData(int pageSize, int pageIndex, boolean isLoadMore);
    }

    public interface Model extends BaseModel{
    }
}