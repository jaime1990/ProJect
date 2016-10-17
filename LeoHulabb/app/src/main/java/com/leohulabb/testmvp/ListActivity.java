package com.leohulabb.testmvp;

import android.content.Intent;
import android.os.Handler;
import android.view.View;

import com.commonui.activity.base.BaseListActivity;
import com.commonui.listview.BaseQuickAdapter;
import com.leohulabb.R;
import com.leohulabb.data.TestListData;
import com.leohulabb.testmvp.contract.TestListContract;
import com.leohulabb.testmvp.model.TestListModelImpl;
import com.leohulabb.testmvp.presenter.TestListPresenterImpl;

import java.util.List;

/**
 * @desc:
 * @author: Leo
 * @date: 2016/10/12
 */
public class ListActivity extends BaseListActivity<TestListPresenterImpl, TestListModelImpl> implements TestListContract.View
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
        return new ListViewAdapter(R.layout.test_list_item_layout, null);
    }

    @Override
    protected void initLoadData() {
        PageIndex = 1;
        isLoadMore = false;
        mPresenter.loadData(PageIndex, PageSize, isLoadMore);
    }

    @Override
    protected void loadRefreshData() {
        PageIndex = 1;
        isLoadMore = true;
        mPresenter.loadData(PageIndex, PageSize, isLoadMore);
    }

    @Override
    protected void loadMoreData() {
        PageIndex++;
        isLoadMore = true;
        mPresenter.loadData(PageIndex, PageSize, isLoadMore);
    }

    @Override
    public void setListener() {
        mQuickAdapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                TestListData item = (TestListData) mQuickAdapter.getItem(position);
                Intent intent = new Intent(context, TestActivityActivity.class);
                intent.putExtra("Picture", item.getLogo().getPictureUrl());

                startActivity(intent);
            }
        });
    }

    @Override
    public void loadData(List list) {
        //进入显示的初始数据或者下拉刷新显示的数据
        mQuickAdapter.setNewData(list);//新增数据
        mQuickAdapter.openLoadMore(PageSize, true);//设置是否可以上拉加载  以及加载条数
        springView.onFinishFreshAndLoad();//刷新完成
    }

    @Override
    public void addData(final List list) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mQuickAdapter.notifyDataChangedAfterLoadMore(list, true);
            }
        }, 1000);
    }
}
