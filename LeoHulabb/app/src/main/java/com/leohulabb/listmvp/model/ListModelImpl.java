package com.leohulabb.listmvp.model;
import com.leohulabb.data.HttpData.HttpData;
import com.leohulabb.data.TestListData;
import com.leohulabb.listmvp.contract.ListContract;

import java.util.List;

import rx.Observable;

/**
 * @desc:         列表Model
 * @author:       Leo
 * @date:         2016/10/17
 */
public class ListModelImpl implements ListContract.Model {

    @Override
    public Observable<List<TestListData>> loadResultData(int PageIndex, int PageSize) {
        return HttpData.getInstance().HttpDataToSchoolList(PageIndex, PageSize);
    }
}