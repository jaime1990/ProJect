package com.leohulabb.testmsp;

import com.commonui.activity.base.BaseListActivity;
import com.commonui.listview.BaseQuickAdapter;
import com.leohulabb.R;
import com.leohulabb.data.UniversityListDto;
import com.leohulabb.testmsp.model.TestListModelImpl;
import com.leohulabb.testmsp.presenter.TestListPresenterImpl;

/**
 * @desc:
 * @author: Leo
 * @date: 2016/10/12
 */
public class ListActivity extends BaseListActivity<TestListPresenterImpl, TestListModelImpl, UniversityListDto>
{
    @Override
    public int getLayoutId() {
        return R.layout.test_list_activity;
    }

    @Override
    protected void initBaseView() {
        loadingView = findView(R.id.progress);
        springView = findView(R.id.springview);
        recyclerView = findView(R.id.rv_list);
    }

    @Override
    protected void setNavigation() {
        getNavigationBar(true).setAppWidgeTitle("Test BaseList");
    }

    @Override
    protected BaseQuickAdapter getAdapter() {
        return new ListViewAdapter(R.layout.test_list_item_layout,null);
    }

    @Override
    protected void initLoadData() {
        PageIndex = 1;
        mPresenter.loadData(PageIndex, PageSize, false);
    }

    @Override
    protected void loadRefreshData() {
        PageIndex = 1;
        mPresenter.loadData(PageIndex, PageSize, true);
    }

    @Override
    protected void loadMoreData() {
        PageIndex++;
        mPresenter.loadData(PageIndex, PageSize, true);
    }
}
