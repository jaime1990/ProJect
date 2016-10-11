package com.leohulabb.testmsp.presenter;
import com.leohulabb.data.DatasEntity;
import com.leohulabb.data.TestData;
import com.leohulabb.testmsp.contract.TestListContract;

import java.util.ArrayList;

/**
* Created by MVPHelper on 2016/10/11
*/

public class TestListPresenterImpl extends TestListContract.Presenter {

    @Override
    public void loadData(int pageSize, int pageIndex, boolean isLoadMore) {
        mView.showProgress();
        ArrayList<TestData> dataListTest = DatasEntity.getDataListTest();

        mView.hideProgress();
        if (null != dataListTest) {
            if (!isLoadMore)
                mView.loadData(dataListTest);
            else
                mView.addDatas(dataListTest);
        }
        else
            mView.showNoData();
    }
}