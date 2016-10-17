package com.leohulabb.testmvp.presenter;
import com.leohulabb.data.TestListData;
import com.leohulabb.testmvp.contract.TestListContract;

import java.util.List;

import rx.Subscriber;

/**
* Created by MVPHelper on 2016/10/11
*/

public class TestListPresenterImpl extends TestListContract.Presenter
{
    @Override
    public void loadData(int pageIndex, int pageSize, final boolean isLoadMore)
    {
        if (!isLoadMore)
            mView.showProgress();

        mRxManage.add(mModel.loadData(pageIndex, pageSize, null).subscribe(new Subscriber<List<TestListData>>() {
            @Override
            public void onCompleted() {
                mView.hideProgress();
            }

            @Override
            public void onError(Throwable e) {
                mView.hideProgress();
                mView.showLoadFailMsg();
            }

            @Override
            public void onNext(List<TestListData> data) {
                if (isLoadMore) {
                    if (data.size() == 0)
                        mView.showLoadCompleteAllData();
                    else
                        mView.addData(data);
                } else {
                    if (data.size() == 0)
                        mView.showNoData();
                    else
                        mView.loadData(data);
                }
            }
        }));
    }
}