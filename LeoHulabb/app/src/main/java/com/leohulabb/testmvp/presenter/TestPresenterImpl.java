package com.leohulabb.testmvp.presenter;
import com.leohulabb.data.DatasEntity;
import com.leohulabb.data.TestData;
import com.leohulabb.testmvp.contract.TestContract;

/**
* Created by MVPHelper on 2016/10/10
*/

public class TestPresenterImpl extends TestContract.Presenter{

    @Override
    public void getDetail()
    {
        TestData dataTest = DatasEntity.getDataTest();

        if (null != dataTest)
        {
            mView.setDetail(dataTest);
        } else
            mView.noData();
    }
}