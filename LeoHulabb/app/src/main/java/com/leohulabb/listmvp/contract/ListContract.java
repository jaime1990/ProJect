package com.leohulabb.listmvp.contract;

import com.commonui.activity.base.BaseListView;
import com.commonui.activity.base.BaseModel;
import com.commonui.activity.base.BasePresenter;
import com.leohulabb.data.TestListData;

import java.util.List;

import rx.Observable;

/**
 * @desc:   列表Contract
 * @author: Leo
 * @date:   2016/10/17
 */
public class ListContract
{
    public interface View<UniversityListDto> extends BaseListView {
        void loadResultData(List<UniversityListDto> lists);
    }

    public abstract static class Presenter extends BasePresenter<View, Model> {
        /**
         * 请求数据
         * @param PageIndex      页序号
         * @param PageSize       页大小
         * @param isFirstLoad    是否初次加载
         */
        public abstract void loadResultData(int PageIndex, int PageSize, boolean isFirstLoad);
    }

    public interface Model extends BaseModel {
        Observable<List<TestListData>> loadResultData(int PageIndex, int PageSize);
    }
}