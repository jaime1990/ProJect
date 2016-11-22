package com.leohulabb.listmvp;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.commonui.activity.base.BaseHYListActivity;
import com.commonui.loadingview.LoadingTip;
import com.commonui.recyclerview.other.IRecyclerView;
import com.commonui.recyclerview.other.LoadMoreFooterView;
import com.commonui.recyclerview.other.adapter.CommonRecycleViewAdapter;
import com.commonui.recyclerview.other.adapter.ViewHolderHelper;
import com.commonui.toast.ToastManager;
import com.leohulabb.R;
import com.leohulabb.data.TestListData;
import com.leohulabb.listmvp.contract.ListContract;
import com.leohulabb.listmvp.model.ListModelImpl;
import com.leohulabb.listmvp.presenter.ListPresenterImpl;
import com.leohulabb.testmvp.TestActivityActivity;

import java.util.List;

import static com.leohulabb.BaseApplication.getContext;

/**
 * @desc:
 * @author: Leo
 * @date: 2016/10/17
 */
public class ListMvpActivity extends BaseHYListActivity<ListPresenterImpl, ListModelImpl> implements ListContract.View
{
    private CommonRecycleViewAdapter<TestListData> adapter;

    @Override
    protected void initRecyclerView()
    {
        final TestListData testListData = new TestListData();
        testListData.setCnName("咖喱吨国际大学");
        testListData.setHits(666666);

        final TestListData testlistData = new TestListData();
        testlistData.setCnName("胡扯国际大学");
        testlistData.setHits(10000);

        adapter = new CommonRecycleViewAdapter<TestListData>(getContext(),R.layout.test_list_item_layout) {
            @Override
            public void convert(final ViewHolderHelper helper, final TestListData item) {
                helper.setText(R.id.listview_tv_title,item.getCnName()).setText(R.id.listview_tv_content,"热度:"+item.getHits());
                helper.setImageUrl(R.id.listview_image_url, item.getLogo().getPictureUrl());
                adapter.openLoadAnimation(CommonRecycleViewAdapter.SLIDEIN_BOTTOM);

                if (helper.getmPosition() % 4 == 1)
                helper.setOnClickListener(helper.getConvertView(), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, TestActivityActivity.class);
                        intent.putExtra("Picture", item.getLogo().getPictureUrl());
                        startActivity(intent);
                    }
                });

                if (helper.getmPosition() % 4 == 2)
                    helper.setOnClickListener(helper.getConvertView(), new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            removeAt(helper.getmPosition());
                            ToastManager.show("delete" + item.getCnName());
//                            Intent intent = new Intent(context, TestActivityActivity.class);
//                            intent.putExtra("Picture", item.getLogo().getPictureUrl());
//                            startActivity(intent);
                        }
                    });

                if (helper.getmPosition() % 4 == 3)
                    helper.setOnClickListener(helper.getConvertView(), new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            replaceAt(helper.getmPosition(), testListData);
                            ToastManager.longShow("replace" + item.getCnName() + "-To-" + testListData.getCnName());
//                            Intent intent = new Intent(context, TestActivityActivity.class);
//                            intent.putExtra("Picture", item.getLogo().getPictureUrl());
//                            startActivity(intent);
                        }
                    });

                if (helper.getmPosition() % 4 == 0)
                    helper.setOnClickListener(helper.getConvertView(), new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            addAt(helper.getmPosition(), testlistData);
                            removeAt(helper.getmPosition());
                            ToastManager.show("add" + testlistData.getCnName());
//                            Intent intent = new Intent(context, TestActivityActivity.class);
//                            intent.putExtra("Picture", item.getLogo().getPictureUrl());
//                            startActivity(intent);
                        }
                    });
            }
        };

        //设置RecyclerView的显示模式  当前List模式
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //如果Item高度固定  增加该属性能够提高效率
        recyclerView.setHasFixedSize(true);
        //将适配器添加到RecyclerView
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void initLoadData() {
        isFirstLoad = true;
        mPresenter.loadResultData(PageIndex, PageSize, isFirstLoad);
    }

    @Override
    protected void loadRefreshData() {
        adapter.getPageBean().setRefresh(true);
        mPresenter.loadResultData(PageIndex, PageSize, isFirstLoad);
    }

    @Override
    protected void loadMoreData() {
        adapter.getPageBean().setRefresh(false);
        mPresenter.loadResultData(PageIndex, PageSize);
    }

    @Override
    protected void initBaseView() {
        loadingView = (LoadingTip) findViewById(R.id.loadedTip);
        recyclerView = (IRecyclerView) findViewById(R.id.rv_list);
    }

    @Override
    protected void setNavigation() {
        getNavigationBar(true).setAppWidgeTitle("List Refresh Mvp");
    }

    @Override
    public int getLayoutId() {
        return R.layout.test_other_list_activity;
    }

    @Override
    public void loadResultData(List lists) {
        if (adapter.getPageBean().isRefresh()) {
            recyclerView.setRefreshing(false);
            adapter.replaceAll(lists);
        } else {
            recyclerView.setLoadMoreStatus(LoadMoreFooterView.Status.GONE);
            adapter.addAll(lists);
        }
    }
}
