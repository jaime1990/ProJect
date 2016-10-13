package com.commonui.activity.base;

import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.commonui.listview.BaseQuickAdapter;
import com.commonui.loadingview.LoadingView;
import com.commonui.pulltorefresh.SpringView;
import com.commonui.pulltorefresh.container.RotationFooter;
import com.commonui.pulltorefresh.container.RotationHeader;
import com.commonui.toast.ToastManager;

import java.util.List;

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
public abstract class BaseListActivity<T extends BasePresenter, E extends BaseModel, K> extends BaseActivity<T, E>
        implements BaseQuickAdapter.RequestLoadMoreListener, SpringView.OnFreshListener, BaseListView<K>
{
    //预加载view
    protected LoadingView        loadingView;
    
    //刷新加载控件
    protected SpringView         springView;
    protected RecyclerView       recyclerView;
    protected RecyclerView.LayoutManager layoutManager;

    protected BaseQuickAdapter   mQuickAdapter;

    public int PageSize = 8;
    public int PageIndex = 1;

    @Override
    public void initView() {
        setNavigation();
        initBaseView();
    }

    @Override
    public void initPresenter()
    {
        if (!checkView()) {
            return;
        }
        mPresenter.setVM(this, mModel);
        initRefreshView();
        initRecyclerView(layoutManager);
        initAdapter();

        //请求网络数据
        initLoadData();
    }

    @Override
    public void setData() {
    }

    //检测基础view是否被初始化
    public boolean checkView()
    {
        if (null == loadingView || null == springView || null == recyclerView) {
            return false;
        }

        return true;
    }

    //初始化适配器
    private void initAdapter()
    {
        //设置适配器
        mQuickAdapter = getAdapter();
        //设置加载动画
        mQuickAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        //设置是否自动加载以及加载个数
        mQuickAdapter.openLoadMore(6, true);
        //将适配器添加到RecyclerView
        recyclerView.setAdapter(mQuickAdapter);
        //设置自动加载监听
        mQuickAdapter.setOnLoadMoreListener(this);
    }

    protected abstract BaseQuickAdapter getAdapter();
    //第一次初始化数据
    protected abstract void initLoadData();
    protected abstract void loadRefreshData();
    //加载更多数据
    protected abstract void loadMoreData();
    //初始化基础控件
    protected abstract void initBaseView();
    //设置Navigation
    protected abstract void setNavigation();

    //初始化刷新控件样式
    private void initRefreshView()
    {
        //设置下拉刷新监听
        springView.setListener(this);
        //设置下拉刷新样式
        springView.setHeader(new RotationHeader(this));
        //设置上拉加载样式
        springView.setHeader(new RotationFooter(this));
    }

    /**
     * 设置刷新控件头部样式
     * @param refreshHander 刷新控件头部样式
     */
    public void setRefreshHander(SpringView.DragHander refreshHander)
    {
        if (null != refreshHander) {
            springView.setHeader(refreshHander);
        }
    }

    /**
     * 设置刷新控件头部样式
     * @param refreshFooter 刷新控件底部样式
     */
    public void setRefreshFooter(SpringView.DragHander refreshFooter)
    {
        if (null != refreshFooter) {
            springView.setHeader(refreshFooter);
        }
    }

    //初始化列表
    private void initRecyclerView(RecyclerView.LayoutManager manager)
    {
        if (null == manager) {
            manager = new LinearLayoutManager(this);
        }

        //设置RecyclerView的显示模式  当前List模式
        recyclerView.setLayoutManager(manager);
        //如果Item高度固定  增加该属性能够提高效率
        recyclerView.setHasFixedSize(true);
    }

    @Override
    public void onLoadMoreRequested()
    {
        loadMoreData();
    }

    @Override
    public void onRefresh() {
        loadRefreshData();
    }

    @Override
    public void onLoadmore() {
        loadMoreData();
    }

    @Override
    public void showProgress() {
        loadingView.showLoading();
    }

    @Override
    public void hideProgress() {
        loadingView.showContent();
    }

    @Override
    public void loadData(List<K> list) {
        //进入显示的初始数据或者下拉刷新显示的数据
        mQuickAdapter.setNewData(list);//新增数据
        mQuickAdapter.openLoadMore(6, true);//设置是否可以上拉加载  以及加载条数
        springView.onFinishFreshAndLoad();//刷新完成
    }

    @Override
    public void addData(final List<K> list) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mQuickAdapter.notifyDataChangedAfterLoadMore(list, true);
            }
        }, 1000);
    }

    @Override
    public void showLoadFailMsg() {
        //设置加载错误页显示
        ToastManager.show(context, "数据错误");
//        loadingView.showError(getResources().getDrawable(R.mipmap.bg_square_error), Constant.ERROR_TITLE, Constant.ERROR_CONTEXT, Constant.ERROR_BUTTON, new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                initLoadData();
//            }
//        });
    }

    @Override
    public void showLoadCompleteAllData() {
        //所有数据加载完成后显示
        mQuickAdapter.notifyDataChangedAfterLoadMore(false);
        ToastManager.show(context, "暂无数据");
//        View view = getLayoutInflater().inflate(R.layout.not_loading, (ViewGroup) recyclerView.getParent(), false);
//        mQuickAdapter.addFooterView(view);
    }

    @Override
    public void showNoData() {
        ToastManager.show(context, "暂无数据");
//        loadingView.showEmpty(getResources().getDrawable(R.mipmap.bg_square_no), Constant.EMPTY_TITLE, Constant.EMPTY_CONTEXT);
    }
}
