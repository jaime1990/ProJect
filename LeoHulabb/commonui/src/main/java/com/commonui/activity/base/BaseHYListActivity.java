package com.commonui.activity.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.commonui.activity.basebean.Constant;
import com.commonui.loadingview.LoadingTip;
import com.commonui.recyclerview.other.IRecyclerView;
import com.commonui.recyclerview.other.LoadMoreFooterView;
import com.commonui.recyclerview.other.OnLoadMoreListener;
import com.commonui.recyclerview.other.OnRefreshListener;
import com.commonui.toast.ToastManager;
import com.commonutils.NetworkUtils;

/**
 * 1.设置列表的列数
 * 2.设置适配器
 * 3.传入数据解析实体类
 * 4.加载数据（初始加载和加载更多）
 *
 * @desc:   基础ListActivity封装
 * @author: Leo
 * @date:   2016/10/12
 */
public abstract class BaseHYListActivity<T extends BasePresenter, E extends BaseModel> extends BaseActivity<T, E>
        implements BaseListView, OnRefreshListener, OnLoadMoreListener {
    //预加载view
    protected LoadingTip                 loadingView;

    protected IRecyclerView              recyclerView;
    protected RecyclerView.LayoutManager layoutManager;

    public int PageSize = 8;
    public int PageIndex = 1;
    public boolean isFirstLoad;

    @Override
    public void initView() {
        setNavigation();
        initBaseView();
        initRecyclerView();
    }

    protected abstract void initRecyclerView();

    @Override
    public void initPresenter()
    {
        if (!checkView()) {
            return;
        }
        mPresenter.setVM(this, mModel);

        showProgress();
        //请求网络数据
        initLoadData();
    }

    @Override
    public void setData() {
    }

    //检测基础view是否被初始化
    public boolean checkView()
    {
        if (null == loadingView || null == recyclerView) {
            return false;
        }
        return true;
    }

    //第一次初始化数据
    protected abstract void initLoadData();
    //刷新加载数据
    protected abstract void loadRefreshData();
    //加载更多数据
    protected abstract void loadMoreData();
    //初始化基础控件
    protected abstract void initBaseView();
    //设置Navigation
    protected abstract void setNavigation();

    @Override
    public void setListener() {
        if (null != recyclerView)
        {
            recyclerView.setOnRefreshListener(this);
            recyclerView.setOnLoadMoreListener(this);
        }
    }

    @Override
    public void showProgress() {
        if (null != loadingView)
            loadingView.setLoadingTip(LoadingTip.LoadStatus.loading);
    }

    @Override
    public void hideProgress() {
        if (null != loadingView)
            loadingView.setLoadingTip(LoadingTip.LoadStatus.finish);
    }

    @Override
    public void showLoadFailMsg() {
        //设置加载错误页显示
        if (PageIndex == 1 && isFirstLoad) {
            loadingView.setLoadingTip(LoadingTip.LoadStatus.errorTry);
            loadingView.setTips(Constant.ERROR_CONTEXT);
            loadingView.setOnReloadListener(new LoadingTip.onReloadListener() {
                @Override
                public void reload() {
                    if (!NetworkUtils.isWorked(context)) {
                        ToastManager.show(Constant.ERROR_TITLE);
                        return;
                    }
                    loadRefreshData();
                }
            });
        } else {
            recyclerView.setLoadMoreStatus(LoadMoreFooterView.Status.ERROR);
            recyclerView.setFootViewRetry(new LoadMoreFooterView.OnRetryListener() {
                @Override
                public void onRetry(LoadMoreFooterView view) {
                    if (!NetworkUtils.isWorked(context)) {
                        ToastManager.show(Constant.ERROR_TITLE);
                        return;
                    }
                    loadMoreData();
                }
            });
        }

        recyclerView.setRefreshing(false);
    }

    @Override
    public void showLoadCompleteAllData() {
        recyclerView.setLoadMoreStatus(LoadMoreFooterView.Status.THE_END);
    }

    @Override
    public void showNoData() {
        loadingView.setLoadingTip(LoadingTip.LoadStatus.error);
        recyclerView.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        //发起请求
        PageIndex = 1;
        isFirstLoad = false;
        recyclerView.setRefreshing(true);
        recyclerView.setLoadMoreStatus(LoadMoreFooterView.Status.LOADING);
        loadRefreshData();
    }

    @Override
    public void onLoadMore(View loadMoreView) {
        if (!NetworkUtils.isWorked(context)) {
            recyclerView.setLoadMoreStatus(LoadMoreFooterView.Status.ERROR);
            return;
        }

        if (recyclerView.isLoadEnd()) {
            return;
        }

        PageIndex++;
        isFirstLoad = false;
        recyclerView.setLoadMoreStatus(LoadMoreFooterView.Status.LOADING);
        loadMoreData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        recyclerView = null;
    }
}