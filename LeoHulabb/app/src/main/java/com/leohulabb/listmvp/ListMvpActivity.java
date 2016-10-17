package com.leohulabb.listmvp;

import android.support.v7.widget.LinearLayoutManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.commonui.activity.base.BaseHYListActivity;
import com.commonui.recyclerview.other.LoadMoreFooterView;
import com.commonui.recyclerview.other.adapter.CommonRecycleViewAdapter;
import com.commonui.recyclerview.other.adapter.ViewHolderHelper;
import com.leohulabb.R;
import com.leohulabb.data.TestListData;
import com.leohulabb.listmvp.contract.ListContract;
import com.leohulabb.listmvp.model.ListModelImpl;
import com.leohulabb.listmvp.presenter.ListPresenterImpl;

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
        adapter = new CommonRecycleViewAdapter<TestListData>(getContext(),R.layout.test_list_item_layout) {
            @Override
            public void convert(ViewHolderHelper helper, TestListData item) {
                helper.setText(R.id.listview_tv_title,item.getCnName()).setText(R.id.listview_tv_content,"热度:"+item.getHits());
                Glide.with(context)
                        .load(item.getLogo().getPictureUrl())
                        .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                        .crossFade()
                        .centerCrop().override(1090, 1090*3/4)
                        .placeholder(R.mipmap.bg_square_ing)
                        .into((ImageView) helper.getView(R.id.listview_image_url));

                adapter.openLoadAnimation(CommonRecycleViewAdapter.SLIDEIN_BOTTOM);
                adapter.addAnimation(helper);
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
        loadingView = findView(R.id.loadedTip);
        recyclerView = findView(R.id.rv_list);
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

    @Override
    public void showLoadFailMsg() {
        if (adapter.getPageBean().isRefresh())
            super.showLoadFailMsg();
        else {
            recyclerView.setLoadMoreStatus(LoadMoreFooterView.Status.ERROR);
            recyclerView.setFootViewRetry(new LoadMoreFooterView.OnRetryListener() {
                @Override
                public void onRetry(LoadMoreFooterView view) {
                    mPresenter.loadResultData(PageIndex, PageSize);
                }
            });
        }
    }
}
