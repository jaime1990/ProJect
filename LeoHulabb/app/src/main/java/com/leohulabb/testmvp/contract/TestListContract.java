package com.leohulabb.testmvp.contract;

import com.commonui.activity.base.BaseListView;
import com.commonui.activity.base.BaseModel;
import com.commonui.activity.base.BasePresenter;
import com.leohulabb.data.TestListData;
import com.leohulabb.testmvp.OnLoadDataListListener;

import java.util.List;

import rx.Observable;

/**
 * @desc:
 * @author: Leo
 * @date: 2016/10/11
 */
public class TestListContract
{
    public interface View<T> extends BaseListView {
        //加载新数据
        void loadData(List<T> list);
        //添加更多数据
        void addData(List<T> list);
    }

    public abstract static class Presenter extends BasePresenter<View, Model> {
        public abstract void loadData(int pageSize, int pageIndex, boolean isLoadMore);
    }

    public interface Model extends BaseModel {
        Observable<List<TestListData>> loadData(int PageIndex, int PageSize, final OnLoadDataListListener listener);
    }
}