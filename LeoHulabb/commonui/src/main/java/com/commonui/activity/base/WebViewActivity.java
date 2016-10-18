package com.commonui.activity.base;

import android.view.View;

import com.commonui.navigation.WidgeButton;
import com.commonui.webview.ProgressBarWebView;

/**
 * @desc:   H5显示类
 * @author: Leo
 * @date:   2016/10/18
 */
public abstract class WebViewActivity extends BaseActivity
{
    protected ProgressBarWebView      webView;
    protected String                  url;            //网页地址
    public static String WEBURL =     "WEBURL";       //网页传值的key
    protected WidgeButton             closeBtn;       //关闭按钮
    protected WidgeButton             backBtn;       //返回按钮
    protected WidgeButton[]           widgeButtons;   //导航栏左边的两个按钮（返回和关闭）

    @Override
    public void setData() {

    }

    @Override
    public void setListener()
    {
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //添加网页的返回事件
                if (webView.canGoBack()) {
                    webView.goBack();//返回上一页面
                } else {
                    onBackPressed();
                }
            }
        });

        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //添加按钮的关闭网页事件
                onBackPressed();
            }
        });
    }
}
