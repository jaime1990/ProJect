package com.commonui.activity.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.commonui.R;
import com.commonui.navigation.NavigationBar;
import com.commonui.navigation.WidgeButton;
import com.commonutils.TranslateUtil;
import com.commonutils.ViewUtils;
import com.commonutils.baserx.RxBusManager;

/**
 * des:基类fragment
 * Created by xsf
 * on 2016.07.12:38
 */

/***************使用例子*********************/
//1.mvp模式
//public class SampleFragment extends BaseFragment<NewsChanelPresenter, NewsChannelModel>implements NewsChannelContract.View {
//    @Override
//    public int getLayoutId() {
//        return R.layout.activity_news_channel;
//    }
//
//    @Override
//    public void initPresenter() {
//        mPresenter.setVM(this, mModel);
//    }
//
//    @Override
//    public void initView() {
//    }
//}
//2.普通模式
//public class SampleFragment extends BaseFragment {
//    @Override
//    public int getLayoutResource() {
//        return R.layout.activity_news_channel;
//    }
//
//    @Override
//    public void initPresenter() {
//    }
//
//    @Override
//    public void initView() {
//    }
//}
public abstract  class BaseFragment<T extends BasePresenter, E extends BaseModel> extends Fragment
{
    protected View rootView;
    protected Context context;
    public T mPresenter;
    public E mModel;
    public RxBusManager mRxManager;
    private NavigationBar navigationBar;
    private WidgeButton btnBack;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mRxManager = new RxBusManager();

        if (rootView == null) {
            rootView = inflater.inflate(getLayoutResource(), container, false);
            initFragmet();
            this.initPresenter();
            this.initView();
            this.setData();
            this.setListener();
        }

        // 缓存的mView需要判断是否已经被加过parent，如果有parent需要从parent删除，要不然会发生这个mView已经有parent的错误。
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null)
        {
            parent.removeView(rootView);
        }

        return rootView;
    }

    private void initFragmet()
    {
        context = this.getActivity();
        mPresenter = TranslateUtil.getT(this, 0);
        mModel = TranslateUtil.getT(this,1);
        if(mPresenter != null){
            mPresenter.context = this.getActivity();
        }

        navigationBar = findView(R.id.navigationBar);
    }

    public NavigationBar getNavigationBar() {

        if (null != navigationBar) {
            return navigationBar;
        }
        return null;
    }

    //获取布局文件
    protected abstract int getLayoutResource();
    //简单页面无需mvp就不用管此方法即可,完美兼容各种实际场景的变通
    public abstract void initPresenter();
    //初始化view
    protected abstract void initView();
    //设置监听
    public abstract void setData();
    //设置监听
    public abstract void setListener();

    /**
     * 通过Class跳转界面
     **/
    public void startActivity(Class<?> cls) {
        startActivity(cls, null);
    }

    /**
     * 通过Class跳转界面
     **/
    public void startActivityForResult(Class<?> cls, int requestCode) {
        startActivityForResult(cls, null, requestCode);
    }

    /**
     * 含有Bundle通过Class跳转界面
     **/
    public void startActivityForResult(Class<?> cls, Bundle bundle,
                                       int requestCode) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    /**
     * 含有Bundle通过Class跳转界面
     **/
    public void startActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    public <E extends View> E findView(int resId) {
        return ViewUtils.findViewById(rootView, resId);
    }

    /**
     * 开启加载进度条
     */
    public void startProgressDialog() {
    }

    /**
     * 开启加载进度条
     *
     * @param msg
     */
    public void startProgressDialog(String msg) {
    }

    /**
     * 停止加载进度条
     */
    public void stopProgressDialog() {
    }


    /**
     * 短暂显示Toast提示(来自String)
     **/
    public void showShortToast(String text) {
    }

    /**
     * 短暂显示Toast提示(id)
     **/
    public void showShortToast(int resId) {
    }

    /**
     * 长时间显示Toast提示(来自res)
     **/
    public void showLongToast(int resId) {
    }

    /**
     * 长时间显示Toast提示(来自String)
     **/
    public void showLongToast(String text) {
    }


    public void showToastWithImg(String text,int res) {
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mPresenter != null)
            mPresenter.onDestroy();
        mRxManager.clear();
    }
}
