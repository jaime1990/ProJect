package com.commonutils;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;

import java.util.Iterator;
import java.util.Stack;

/**
 * @desc:   应用程序Activity管理类：用于Activity管理和应用程序退出
 * @author: Leo
 * @date:   2016/09/26
 */
public class ActivityManager {

    private static Stack<Activity> activityStack;
    private volatile static ActivityManager instance;

    private ActivityManager(){}

    /**
     * 单一实例
     */
    public static ActivityManager getActivityManager(){
        if (instance == null) {
            synchronized (ActivityManager.class){
                if(instance==null){
                    instance = new ActivityManager();
                    instance.activityStack = new Stack();
                }
            }

        }
        return instance;
    }

    /**
     * 添加Activity到堆栈
     */
    public void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<Activity>();
        }
        activityStack.add(activity);
    }

    /**
     * 获取当前Activity（堆栈中最后一个压入的）
     */
    public Activity currentActivity() {
        try {
            Activity activity = activityStack.lastElement();
            return activity;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取当前Activity的前一个Activity
     */
    public Activity preActivity() {
        int index = activityStack.size() - 2;
        if (index < 0) {
            return null;
        }
        Activity activity = activityStack.get(index);
        return activity;
    }

    /**
     * 结束当前Activity（堆栈中最后一个压入的）
     */
    public void finishActivity(){
        if( activityStack == null ) {
            return;
        }
        Activity activity=activityStack.lastElement();
        finishActivity(activity);
    }

    /**
     * 结束指定的Activity
     */
    public void finishActivity(Activity activity){
        if( activityStack == null ) {
            return;
        }
        if(activity!=null){
            activityStack.remove(activity);
            activity.finish();
            activity=null;
        }
    }

    /**
     * 结束指定类名的Activity
     */
    public void finishActivity(Class<?> cls){
        if( activityStack == null ) {
            return;
        }
        Iterator<Activity> iterator = activityStack.iterator();
        while (iterator.hasNext()) {
            Activity activity = iterator.next();
            if(activity != null && activity.getClass().equals(cls) ){
                activity.finish();
                iterator.remove();
            }
        }
    }

    /**
     * 结束所有Activity
     */
    public void finishAllActivity(){
        if( activityStack == null ) {
            return;
        }
        Iterator<Activity> iterator = activityStack.iterator();
        while (iterator.hasNext()) {
            Activity activity = iterator.next();
            if(activity != null && !activity.isFinishing()){
                activity.finish();
            }
        }
        activityStack.clear();
    }

    /**
     * 根据ActivityName获取堆中Activity实例
     * @param activityName
     * @return
     */
    public Activity getActivity(String activityName) {
        Iterator<Activity> iterator = activityStack.iterator();
        while (iterator.hasNext()) {
            Activity activity = iterator.next();
            if(activity != null && TextUtils.equals(activity.getClass().getName(), activityName)){
                return activity;
            }
        }
        return null;
    }

    /**
     * 返回到指定的activity
     *
     * @param cls
     */
    public void returnToActivity(Class<?> cls) {
        while (activityStack.size() != 0)
            if (activityStack.peek().getClass() == cls) {
                break;
            } else {
                finishActivity(activityStack.peek());
            }
    }

    /**
     * 是否已经打开指定的activity
     * @param cls
     * @return
     */
    public boolean isOpenActivity(Class<?> cls) {
        if (activityStack!=null){
            for (int i = 0, size = activityStack.size(); i < size; i++) {
                if (cls == activityStack.peek().getClass()) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 退出应用程序
     */
    public void appExit(Context context) {
        try {
            finishAllActivity();
            android.os.Process.killProcess(android.os.Process.myPid());
        } catch (Exception ignored) { }
    }

    /**
     * 退出应用程序
     *
     * @param context      上下文
     * @param isBackground 是否开开启后台运行
     */
    public void appExit(Context context, Boolean isBackground) {
        try {
            finishAllActivity();
            android.app.ActivityManager activityMgr = (android.app.ActivityManager) context
                    .getSystemService(Context.ACTIVITY_SERVICE);
            activityMgr.restartPackage(context.getPackageName());
        } catch (Exception e) {

        } finally {
            // 注意，如果您有后台程序运行，请不要支持此句子
            if (!isBackground) {
                System.exit(0);
            }
        }
    }
}
