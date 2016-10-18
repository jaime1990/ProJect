package com.leohulabb.listmvp.presenter;
import com.leohulabb.data.TestListData;
import com.leohulabb.listmvp.contract.ListContract;

import java.util.List;

import rx.Subscriber;

/**
 * @desc:         列表逻辑处理类
 * @author:       Leo
 * @date:         2016/10/17
 */
public class ListPresenterImpl extends ListContract.Presenter {

    public void loadResultData(int PageIndex, int PageSize) {
        loadResultData(PageIndex, PageSize, false);
    }

    @Override
    public void loadResultData(final int PageIndex, int PageSize, final boolean isFirstLoad)
    {
        //初次请求时Loading
        if (PageIndex == 1 && isFirstLoad)
            mView.showProgress();

        mRxManage.add(mModel.loadResultData(PageIndex, PageSize)
                .subscribe(new Subscriber<List<TestListData>>() {
                    @Override
                    public void onCompleted() {
                        mView.hideProgress();
                    }

                    @Override
                    public void onError(Throwable e) {
                        //由于出错情况不会执行onCompleted
                        mView.hideProgress();
                        mView.showLoadFailMsg();
                    }

                    @Override
                    public void onNext(List<TestListData> universityListDtos)
                    {
                        if (universityListDtos.size() <= 0)    //网络请求返回空
                        {
                            /**
                             * 空数据情况分类
                             * 1.初次加载数据为空
                             * 2.上拉加载数据为空
                             */
                            if (isFirstLoad) {
                                mView.showNoData();
                            } else {
                                mView.showLoadCompleteAllData();
                            }
                        } else {
                            mView.loadResultData(universityListDtos);
                        }
                    }
                }));
    }
}