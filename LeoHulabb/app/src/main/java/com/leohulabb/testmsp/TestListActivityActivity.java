package com.leohulabb.testmsp;

import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.commonui.activity.base.BaseActivity;
import com.commonui.listview.BaseQuickAdapter;
import com.commonui.loadingview.LoadingView;
import com.commonui.pulltorefresh.SpringView;
import com.commonui.pulltorefresh.container.RotationHeader;
import com.leohulabb.R;
import com.leohulabb.data.Constant;
import com.leohulabb.data.UniversityListDto;
import com.leohulabb.testmsp.contract.TestListContract;
import com.leohulabb.testmsp.model.TestListModelImpl;
import com.leohulabb.testmsp.presenter.TestListPresenterImpl;

import java.util.List;

public class TestListActivityActivity extends BaseActivity<TestListPresenterImpl, TestListModelImpl> implements
        BaseQuickAdapter.RequestLoadMoreListener,SpringView.OnFreshListener, TestListContract.View {

    private LoadingView progress;
    private SpringView springview;
    private RecyclerView rvList;

    private BaseQuickAdapter mQuickAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.test_list_activity;
    }

    @Override
    public void initPresenter()
    {
//        mPresenter.setVM(this, mModel);
        //设置下拉刷新监听
        springview.setListener(this);
        //设置下拉刷新样式
        springview.setHeader(new RotationHeader(this));
        //设置RecyclerView的显示模式  当前List模式
        rvList.setLayoutManager(new LinearLayoutManager(this));
        //如果Item高度固定  增加该属性能够提高效率
        rvList.setHasFixedSize(true);
        //设置页面为加载中..
        progress.showLoading();
        //设置适配器
        mQuickAdapter = new ListViewAdapter(R.layout.test_list_item_layout,null);
        //设置加载动画
        mQuickAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        //设置是否自动加载以及加载个数
        mQuickAdapter.openLoadMore(6, true);
        //将适配器添加到RecyclerView
        rvList.setAdapter(mQuickAdapter);

        //请求网络数据
        mPresenter.loadData(1, 12, false);
    }

    @Override
    public void initView() {
        progress = findView(R.id.progress);
        springview = findView(R.id.springview);
        rvList = findView(R.id.rv_list);

        getNavigationBar(true).setAppWidgeTitle("列表Demo");
    }

    @Override
    public void setData() {
    }

    @Override
    public void setListener() {
        //设置自动加载监听
        mQuickAdapter.setOnLoadMoreListener(this);

        mQuickAdapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(context, "点击了"+position, Toast.LENGTH_SHORT).show();
            }
        });
        mQuickAdapter.setOnRecyclerViewItemLongClickListener(new BaseQuickAdapter.OnRecyclerViewItemLongClickListener() {
            @Override
            public boolean onItemLongClick(View view, int position) {
                Toast.makeText(context, "长按了"+position, Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }

    //自动加载
    @Override
    public void onLoadMoreRequested() {
        mPresenter.loadData(2, 12, true);
    }

    //下拉刷新
    @Override
    public void onRefresh() {
        mPresenter.loadData(1, 12, false);
    }

    @Override
    public void onLoadmore() {

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
    public void loadData(List<UniversityListDto> list) {
        //进入显示的初始数据或者下拉刷新显示的数据
        mQuickAdapter.setNewData(list);//新增数据
        mQuickAdapter.openLoadMore(10, true);//设置是否可以上拉加载  以及加载条数
        springview.onFinishFreshAndLoad();//刷新完成
    }

    @Override
    public void addDatas(final List<UniversityListDto> list) {
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
        progress.showError(getResources().getDrawable(R.mipmap.bg_square_error), Constant.ERROR_TITLE, Constant.ERROR_CONTEXT, Constant.ERROR_BUTTON, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.loadData(1, 12, false);
            }
        });
    }

    @Override
    public void showLoadCompleteAllData() {
        //所有数据加载完成后显示
        mQuickAdapter.notifyDataChangedAfterLoadMore(false);
        View view = getLayoutInflater().inflate(R.layout.not_loading, (ViewGroup) rvList.getParent(), false);
        mQuickAdapter.addFooterView(view);
    }

    @Override
    public void showNoData() {
        progress.showEmpty(getResources().getDrawable(R.mipmap.bg_square_no),Constant.EMPTY_TITLE,Constant.EMPTY_CONTEXT);
    }
}