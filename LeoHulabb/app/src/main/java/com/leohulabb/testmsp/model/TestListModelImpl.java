package com.leohulabb.testmsp.model;

import com.leohulabb.data.HttpData.HttpData;
import com.leohulabb.data.UniversityListDto;
import com.leohulabb.testmsp.OnLoadDataListListener;
import com.leohulabb.testmsp.contract.TestListContract;

import java.util.List;

import rx.Observable;

/**
* Created by MVPHelper on 2016/10/11
*/

public class TestListModelImpl implements TestListContract.Model {

    @Override
    public Observable<List<UniversityListDto>> loadData(int PageIndex, int PageSize, final OnLoadDataListListener listener) {
        return HttpData.getInstance()
                .HttpDataToSchoolList(PageIndex, PageSize);
    }
}