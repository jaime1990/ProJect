package com.leohulabb.testmsp;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.commonui.activity.base.BaseActivity;
import com.commonui.loadingview.LoadingView;
import com.commonui.recyclerview.other.IRecyclerView;
import com.commonui.recyclerview.other.LoadMoreFooterView;
import com.commonui.recyclerview.other.OnLoadMoreListener;
import com.commonui.recyclerview.other.OnRefreshListener;
import com.commonui.recyclerview.other.adapter.CommonRecycleViewAdapter;
import com.commonui.recyclerview.other.adapter.ViewHolderHelper;
import com.commonui.toast.ToastManager;
import com.leohulabb.R;
import com.leohulabb.data.Constant;
import com.leohulabb.data.UniversityListDto;
import com.leohulabb.testmsp.contract.TestListContract;
import com.leohulabb.testmsp.model.TestListModelImpl;
import com.leohulabb.testmsp.presenter.TestListPresenterImpl;

import java.util.List;

import static com.leohulabb.BaseApplication.getContext;

public class TestListActivityActivity extends BaseActivity<TestListPresenterImpl, TestListModelImpl> implements
        TestListContract.View, OnRefreshListener, OnLoadMoreListener {

    private LoadingView progress;
    private IRecyclerView rvList;
    private int PageIndex = 1;
    private int PageSize = 2;

    private CommonRecycleViewAdapter<UniversityListDto> adapter;

    @Override
    public int getLayoutId() {
        return R.layout.test_other_list_activity;
    }

    @Override
    public void initPresenter()
    {
        mPresenter.setVM(this, mModel);

        adapter = new CommonRecycleViewAdapter<UniversityListDto> (getContext(),R.layout.test_list_item_layout) {
            @Override
            public void convert(ViewHolderHelper helper, UniversityListDto item) {
                helper.setText(R.id.listview_tv_title,item.getCnName()).setText(R.id.listview_tv_content,"热度:"+item.getHits());
                Glide.with(context)
                        .load(item.getLogo().getPictureUrl())
                        .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                        .crossFade()
                        .centerCrop().override(1090, 1090*3/4)
                        .placeholder(R.mipmap.bg_square_ing)
                        .into((ImageView) helper.getView(R.id.listview_image_url));
            }

        };
        //设置RecyclerView的显示模式  当前List模式
        rvList.setLayoutManager(new LinearLayoutManager(this));
        //如果Item高度固定  增加该属性能够提高效率
        rvList.setHasFixedSize(true);
        //设置页面为加载中..
        progress.showLoading();
        //将适配器添加到RecyclerView
        rvList.setAdapter(adapter);

        rvList.setOnLoadMoreListener(this);
        rvList.setOnRefreshListener(this);
        //请求网络数据
        mPresenter.loadData(PageIndex, PageSize, false);
    }

    @Override
    public void initView() {
        progress = findView(R.id.progress);
        rvList = findView(R.id.rv_list);

        getNavigationBar(true).setAppWidgeTitle("列表Demo");
    }

    @Override
    public void setData() {
    }

    @Override
    public void setListener() {
        //设置自动加载监听
    }

    //下拉刷新
    @Override
    public void onRefresh() {
        PageIndex = 0;
        adapter.getPageBean().setRefresh(true);
        //发起请求
        rvList.setRefreshing(true);
        mPresenter.loadData(PageIndex, PageSize, false);
    }

    @Override
    public void showProgress() {
        progress.showLoading();
    }

    @Override
    public void hideProgress() {
        progress.showContent();
    }

    @Override
    public void showLoadFailMsg() {
        //设置加载错误页显示
        progress.showError(getResources().getDrawable(R.mipmap.bg_square_error), Constant.ERROR_TITLE, Constant.ERROR_CONTEXT, Constant.ERROR_BUTTON, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.loadData(PageIndex, PageSize, false);
            }
        });
    }

    @Override
    public void showLoadCompleteAllData() {

    }

    @Override
    public void showNoData() {
        progress.showEmpty(getResources().getDrawable(R.mipmap.bg_square_no),Constant.EMPTY_TITLE,Constant.EMPTY_CONTEXT);
    }

    @Override
    public void loadData(List list) {

        if (PageIndex >= 2) {
            rvList.setLoadMoreStatus(LoadMoreFooterView.Status.THE_END);
            return;
        }

        if (list != null) {
            if (adapter.getPageBean().isRefresh()) {
                rvList.setRefreshing(false);
                adapter.replaceAll(list);
            } else {
                if (list.size() > 0) {
                    rvList.setLoadMoreStatus(LoadMoreFooterView.Status.GONE);
                    adapter.addAll(list);
                } else {
                    rvList.setLoadMoreStatus(LoadMoreFooterView.Status.THE_END);
                }
            }
        }
    }

    @Override
    public void addData(List list) {

        if (PageIndex >= 2) {
            rvList.setLoadMoreStatus(LoadMoreFooterView.Status.THE_END);
            return;
        }

        if (list != null) {
            if (adapter.getPageBean().isRefresh()) {
                rvList.setRefreshing(false);
                adapter.replaceAll(list);
            } else {
                if (list.size() > 0) {
                    rvList.setLoadMoreStatus(LoadMoreFooterView.Status.GONE);
                    adapter.addAll(list);
                } else {
                    rvList.setLoadMoreStatus(LoadMoreFooterView.Status.THE_END);
                }
            }
        }
    }

    @Override
    public void onLoadMore(View loadMoreView) {

        if (rvList.isLoadEnd()) {
            ToastManager.show(context, "已滑到底部");
            return;
        }
        PageIndex++;
        adapter.getPageBean().setRefresh(false);
        //发起请求
        rvList.setLoadMoreStatus(LoadMoreFooterView.Status.LOADING);
        mPresenter.loadData(PageIndex, PageSize, false);
    }

//    @Override
//    public void onLoadMore(View loadMoreView) {
//        adapter.getPageBean().setRefresh(false);
//        //发起请求
//        rvList.setLoadMoreStatus(LoadMoreFooterView.Status.LOADING);
//        mPresenter.loadData(2, 12, true);
//    }
}