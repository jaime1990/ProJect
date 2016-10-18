package com.commonui.activity.base;


import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;

import com.commonui.R;
import com.commonui.navigation.NavigationBar;
import com.commonui.navigation.WidgeButton;
import com.commonutils.ActivityManager;
import com.commonutils.ScreenUtils;
import com.commonutils.TranslateUtil;
import com.commonutils.baserx.RxManager;

/**
 * @desc:         基类
 * @author:       Leo
 * @date:         2016/10/9
 */
/***************使用例子*********************/
//1.mvp模式
//public class SampleActivity extends BaseActivity<NewsChanelPresenter, NewsChannelModel>implements NewsChannelContract.View {
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
//public class SampleActivity extends BaseActivity {
//    @Override
//    public int getLayoutId() {
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
public abstract class BaseActivity<T extends BasePresenter, E extends BaseModel> extends AppCompatActivity
{
    public T mPresenter;
    public E mModel;
    public Context context;
    public RxManager mRxManager;
    public NavigationBar navigationBar;
    private WidgeButton btnBack;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRxManager=new RxManager();
        doBeforeSetcontentView();
        setContentView(getLayoutId());
        initActivity();
        this.initView();
        this.initPresenter();
        this.initFragment(savedInstanceState);
        this.setData();
        this.setListener();
    }

    private void initActivity()
    {
        context = this;
        mPresenter = TranslateUtil.getT(this, 0);
        mModel = TranslateUtil.getT(this,1);

        if(mPresenter != null) {
            mPresenter.context = this;
        }

        navigationBar = (NavigationBar) findViewById(R.id.navigationBar);
    }

    public NavigationBar getNavigationBar()
    {
        return getNavigationBar(false);
    }

    public NavigationBar getNavigationBar(boolean isCanBack) {

        if (null != navigationBar) {
            if (isCanBack) {
                navigationBar.setLeftMenu(getBtnBack());
            }
            return navigationBar;
        }
        return null;
    }

    public WidgeButton getBtnBack()
    {
        btnBack = new WidgeButton(context);
        btnBack.setBackgroundResource(R.drawable.icon_fb);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        return btnBack;
    }

    /**
     * 初始化碎片
     */
    public void initFragment(Bundle savedInstanceState) { }

    /**
     * 设置layout前配置
     */
    private void doBeforeSetcontentView() {
        // 无标题
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        // 设置竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //设置透明状态栏
        ScreenUtils.translateStatusBar(this);
        setTheme(R.style.TranslucentTheme);
        ActivityManager.getActivityManager().addActivity(this);
    }

    /*********************子类实现*****************************/
    //获取布局文件
    public abstract int getLayoutId();
    //简单页面无需mvp就不用管此方法即可,完美兼容各种实际场景的变通
    public abstract void initPresenter();
    //初始化view
    public abstract void initView();
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
        intent.setClass(this, cls);
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
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * 开启浮动加载进度条
     */
    public void startProgressDialog() {
    }

    /**
     * 开启浮动加载进度条
     *
     * @param msg
     */
    public void startProgressDialog(String msg) {
    }

    /**
     * 停止浮动加载进度条
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

    /**
     * 带图片的toast
     * @param text
     * @param res
     */
    public void showToastWithImg(String text,int res) {
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null)
            mPresenter.onDestroy();
        mRxManager.clear();
        ActivityManager.getActivityManager().finishActivity(this);
    }
}
