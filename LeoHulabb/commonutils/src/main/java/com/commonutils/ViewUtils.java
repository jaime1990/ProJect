package com.commonutils;

/**
 * @desc:   view工具类
 * @author: Leo
 * @date:   2016/09/28
 */
public class ViewUtils {

    //记录上次点击的时间点
    private static long lastClickTime;

    /**
     * 防止用户重复点击
     * @return bool
     *         true 未重复
     *         false 重复
     */
    public synchronized static boolean isFastRepeatClick()
    {
        long time = System.currentTimeMillis();
        if (time - lastClickTime < 500) {
            return true;
        }
        lastClickTime = time;
        return false;
    }
}
