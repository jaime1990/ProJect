package com.commonui.listener;

import android.view.View;


/**
 * @desc:         拦截快速点击重复事件
 * @author:       Leo
 * @date:         2016/10/8
 */
public abstract class OnClickFastListener extends BaseClickListener {

    // 防止快速点击默认等待时长为900ms
    private long DELAY_TIME = 900;
    private static long lastClickTime;

    private boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (0 < timeD && timeD < DELAY_TIME) {
            return true;
        }
        lastClickTime = time;
        return false;
    }

    @Override
    public void onClick(View v) {
        // 判断当前点击事件与前一次点击事件时间间隔是否小于阙值
        if (isFastDoubleClick()) {
            return;
        }

        onFastClick(v);
    }

    /**
     * 设置默认快速点击事件时间间隔
     * @param delay_time 间隔时间
     * @return 点击事件
     */
    public OnClickFastListener setLastClickTime(long delay_time) {

        this.DELAY_TIME = delay_time;

        return this;
    }

    /**
     * 快速点击事件回调方法
     * @param v 事件控件
     */
    public abstract void onFastClick(View v);
}