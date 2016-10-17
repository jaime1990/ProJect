package com.leohulabb.testmvp.model;

import com.leohulabb.data.HttpData.HttpData;
import com.leohulabb.data.TestListData;
import com.leohulabb.testmvp.OnLoadDataListListener;
import com.leohulabb.testmvp.contract.TestListContract;

import java.util.List;

import rx.Observable;

/**
* Created by MVPHelper on 2016/10/11
*/

public class TestListModelImpl implements TestListContract.Model {

    @Override
    public Observable<List<TestListData>> loadData(int PageIndex, int PageSize, final OnLoadDataListListener listener) {
        return HttpData.getInstance()
                .HttpDataToSchoolList(PageIndex, PageSize);
    }
}