package com.leohulabb.utils.picassoUtils;

import android.content.Context;
import android.widget.AbsListView;

import com.leohulabb.BaseApplication;

public abstract class PauseOnScrollListener implements AbsListView.OnScrollListener {

    public Context getContext(){
        return BaseApplication.getContext();
    }

    private final boolean pauseOnScroll;
    private final boolean pauseOnFling;
    private final AbsListView.OnScrollListener externalListener;

    public PauseOnScrollListener(boolean pauseOnScroll, boolean pauseOnFling) {
        this(pauseOnScroll, pauseOnFling, null);
    }

    protected PauseOnScrollListener(boolean pauseOnScroll, boolean pauseOnFling, AbsListView.OnScrollListener customListener) {
        this.pauseOnScroll = pauseOnScroll;
        this.pauseOnFling = pauseOnFling;
        externalListener = customListener;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        switch (scrollState) {
            case PauseOnScrollListener.SCROLL_STATE_IDLE:
                resume();
                break;
            case PauseOnScrollListener.SCROLL_STATE_TOUCH_SCROLL:
                if (pauseOnScroll) {
                    pause();
                }
                break;
            case PauseOnScrollListener.SCROLL_STATE_FLING:
                if (pauseOnFling) {
                    pause();
                }
                break;
        }
        if (externalListener != null) {
            externalListener.onScrollStateChanged(view, scrollState);
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (externalListener != null) {
            externalListener.onScroll(view, firstVisibleItem, visibleItemCount, totalItemCount);
        }
    }

    public abstract void resume();
    public abstract void pause();
}
