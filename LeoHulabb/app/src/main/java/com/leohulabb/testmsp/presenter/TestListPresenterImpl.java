package com.leohulabb.testmsp.presenter;
import com.leohulabb.data.UniversityListDto;
import com.leohulabb.testmsp.contract.TestListContract;

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

        mModel.loadData(pageIndex, pageSize, null).subscribe(new Subscriber<List<UniversityListDto>>() {
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
            public void onNext(List<UniversityListDto> data) {
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
        });
    }
}