package com.commonui.webview;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.commonui.R;

/**
 * 添加Progress的Webview
 * @author Leo
 * Created at 2016/9/26
 */
public class ProgressBarWebView extends AppWebView
{
    private OnLoadCompleteListener onLoadCompleteListener;      //url加载完成监听
    private ProgressBar progressBar;                            //加载进度条

    public ProgressBarWebView(Context context) {
        this(context, null, 0);
        createProsserBar(context);
        addView(progressBar);
        setWebChromeClient(new WebClient());
    }

    public ProgressBarWebView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        createProsserBar(context);
        addView(progressBar);
        setWebChromeClient(new WebClient());
    }

    public ProgressBarWebView(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
        createProsserBar(context);
        addView(progressBar);
        setWebChromeClient(new WebClient());
    }

    /**
     * 创建水平进度条
     * @param context 上下文
     */
    private void createProsserBar(Context context)
    {
        progressBar = new ProgressBar(context, null, android.R.attr.progressBarStyleHorizontal);
        progressBar.setProgressDrawable(ContextCompat.getDrawable(getContext(), R.drawable.base_progress_horizonbar));
        progressBar.setMax(100);
        progressBar.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, 5, 0, 0));
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt)
    {
        LayoutParams lp = (LayoutParams) progressBar.getLayoutParams();
        lp.x = l;
        lp.y = t;
        progressBar.setLayoutParams(lp);
        super.onScrollChanged(l, t, oldl, oldt);
    }

    public OnLoadCompleteListener getOnLoadCompleteListener() {
        return onLoadCompleteListener;
    }

    public void setOnLoadCompleteListener(OnLoadCompleteListener onLoadCompleteListener) {
        this.onLoadCompleteListener = onLoadCompleteListener;
    }

    public class WebClient extends android.webkit.WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            if (newProgress == 100) {
                progressBar.setVisibility(GONE);

                if (onLoadCompleteListener != null) {
                    int contentHeight = getContentHeight();
                    int viewHeight = getHeight();
                    onLoadCompleteListener.onComplete(contentHeight, viewHeight);
                }

                if(!getSettings().getLoadsImagesAutomatically()) {
                    getSettings().setLoadsImagesAutomatically(true);
                }
            } else {
                if (progressBar.getVisibility() == GONE)
                    progressBar.setVisibility(VISIBLE);
                progressBar.setProgress(newProgress);
            }
            super.onProgressChanged(view, newProgress);
        }

    }

    public interface OnLoadCompleteListener {
        void onComplete(int contentHeight, int viewHeight);
    }

    private float downX, downY; // 按下时
    private float currX, currY; // 移动时
    private float moveX; // 移动长度-横向

    @Override
    public boolean onTouchEvent(MotionEvent ev)
    {
        switch (ev.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                downX = ev.getX();
                downY = ev.getY();

            case MotionEvent.ACTION_MOVE:
                currX = ev.getX();
                currY = ev.getY();
                moveX = Math.abs(currX - downX);

                if (Math.abs(currY - downY) > moveX)
                {
                }

                // 水平滚动,横向滑动长度大于20像素时再交出去，不然都当做是垂直滑动。
                else if(moveX > 20)
                {
                    // 横向滑动事不直接交出去，是因为可能页面出现水平滚动条，就是网页宽度比屏幕还宽的情况下就需要判断滑到左边和滑到右边的情况。
                    getParent().getParent().requestDisallowInterceptTouchEvent(false);
                    // 已在左边且继续右滑时，把事件交出去（currX - downX >0是右滑, <0则是左滑）
                    if (getScrollX() == 0 && currX - downX > 0)
                    {
                        getParent().getParent().requestDisallowInterceptTouchEvent(false);
                    }

                    // 已在右边且继续左滑时，把事件交出去
                    else if(getRight()*getScale() - (getWidth() + getScrollX()) <= 1 && currX - downX < 0)
                    {
                        getParent().getParent().requestDisallowInterceptTouchEvent(false);
                    }
                }
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                if (Math.abs(currY - downY) > moveX)
                {
                    Log.e("ACTION_UP - currY", currY + "");
                    Log.e("ACTION_UP - downY", downY + "");
                }
                break;
        }

        return super.onTouchEvent(ev);
    }

    private void setOnInterceptEvent(boolean isToTop, float currY, float downY)
    {
        // 处于顶部或者无法滚动，并且继续下滑，交出事件（currY-downY >0是下滑, <0则是上滑）
        if (currY - downY > 0)
        {
            if (getScrollY() == 0)
                getParent().getParent().requestDisallowInterceptTouchEvent(false);
            else
                getParent().getParent().requestDisallowInterceptTouchEvent(true);
        }

        if (currY - downY < 0)   //上滑
        {
            //上滑默认滑动父控件
            getParent().getParent().requestDisallowInterceptTouchEvent(false);

            setOnInterceptPoint(isToTop);
        }
    }

    private void setOnInterceptPoint(boolean isToTop)
    {
        if (isToTop) {       //滑动到父控件顶部
            getParent().getParent().requestDisallowInterceptTouchEvent(true);
        } else {
            getParent().getParent().requestDisallowInterceptTouchEvent(false);
        }
    }
}