package com.leohulabb.testmsp.contract;

import com.commonui.activity.base.BaseListView;
import com.commonui.activity.base.BaseModel;
import com.commonui.activity.base.BasePresenter;
import com.leohulabb.data.UniversityListDto;
import com.leohulabb.testmsp.OnLoadDataListListener;

import java.util.List;

import rx.Observable;

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
        void loadData(List<UniversityListDto> list);
        //添加更多数据
        void addDatas(List<UniversityListDto> list);
        //显示加载失败
        void showLoadFailMsg();
        //显示已加载所有数据
        void showLoadCompleteAllData();
        //显示无数据
        void showNoData();
    }

    public abstract static class Presenter extends BasePresenter<BaseListView, Model> {
        public abstract void loadData(int pageSize, int pageIndex, boolean isLoadMore);
    }

    public interface Model extends BaseModel {
        Observable<List<UniversityListDto>> loadData(int PageIndex, int PageSize, final OnLoadDataListListener listener);
    }
}