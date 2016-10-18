package com.leohulabb.splash;

import com.commonui.activity.base.WebViewActivity;
import com.commonui.navigation.WidgeButton;
import com.commonui.webview.ProgressBarWebView;
import com.commonutils.StringUtils;
import com.leohulabb.R;

/**
 * @desc:   轮播图详情H5跳转
 * @author: Leo
 * @date:   2016/10/18
 */
public class BannerWebActivity extends WebViewActivity
{
    @Override
    public int getLayoutId() {
        return R.layout.base_webview_layout;
    }

    @Override
    public void initPresenter()
    {
        backBtn = getBtnBack();
        closeBtn = new WidgeButton(context);
        closeBtn.setBackgroundResource(com.commonui.R.drawable.icon_google_plus);

        widgeButtons = new WidgeButton[2];
        widgeButtons[0] = backBtn;
        widgeButtons[1] = closeBtn;

        navigationBar.setAppWidgeTitle("详情");
        navigationBar.setLeftMenus(widgeButtons);
    }

    @Override
    public void initView() {
        webView = (ProgressBarWebView) findViewById(com.commonui.R.id.webviewId);
    }

    @Override
    public void setData() {
        super.setData();

        url = StringUtils.null2Length0(getIntent().getStringExtra(WEBURL));
        webView.loadUrl(url);
    }
}
