package com.commonui.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatDialog;
import android.widget.Toast;

import java.util.List;

/**
 * ========================================
 * <p>
 * 版 权：dou361.com 版权所有 （C） 2015
 * <p>
 * 作 者：陈冠明
 * <p>
 * 个人网站：http://www.dou361.com
 * <p>
 * 版 本：1.0
 * <p>
 * 创建日期：2016/10/31 22:17
 * <p>
 * 描 述：一个对外UI管理容器工具类
 * <p>
 * <p>
 * 修订历史：
 * <p>
 * ========================================
 */
public class DialogUIUtils {

    public static Context appContext;

    public static void init(Context appContext) {
        DialogUIUtils.appContext = appContext;
    }

    /**
     * 只定义一个Toast
     */
    private static Toast mToastBottom;

    /**
     * 关闭弹出框
     */
    public static void dismiss(DialogInterface... dialogs) {
        if (dialogs != null && dialogs.length > 0) {
            for (DialogInterface dialog : dialogs) {
                if (dialog instanceof Dialog) {
                    Dialog dialog1 = (Dialog) dialog;
                    if (dialog1.isShowing()) {
                        dialog1.dismiss();
                    }
                } else if (dialog instanceof AppCompatDialog) {
                    AppCompatDialog dialog2 = (AppCompatDialog) dialog;
                    if (dialog2.isShowing()) {
                        dialog2.dismiss();
                    }
                }
            }

        }
    }

    /**
     * 提示弹出框 默认可取消可点击
     *
     * @param activity 所在activity
     * @param title    标题 不传则无标题
     * @param listener 事件监听
     */
    public static BuildBean showAlert(Activity activity, CharSequence title, CharSequence msg, DialogUIListener listener) {
        return showAlert(activity, title, msg, true, true, listener);
    }

    /**
     * 提示弹出框
     *
     * @param activity         所在activity
     * @param title            标题 不传则无标题
     * @param cancleable       true为可以取消false为不可取消(物理返回按键取消)
     * @param outsideTouchable true为可以点击空白区域false为不可点击
     * @param listener         事件监听
     */
    public static BuildBean showAlert(Activity activity, CharSequence title, CharSequence msg, boolean cancleable, boolean outsideTouchable, DialogUIListener listener) {
        return DialogAssigner.getInstance().assignAlert(activity, title, msg, cancleable, outsideTouchable, listener);
    }

    /***
     * md风格弹出框 默认可取消可点击
     *
     * @param activity 所在activity
     * @param title    标题 不传则无标题
     * @param msg      消息
     * @param listener 事件监听
     * @return
     */
    public static BuildBean showMdAlert(Activity activity, CharSequence title, CharSequence msg, DialogUIListener listener) {
        return showMdAlert(activity, title, msg, true, true, listener);
    }

    /***
     * md风格弹出框
     *
     * @param activity         所在activity
     * @param title            标题 不传则无标题
     * @param msg              消息
     * @param cancleable       true为可以取消false为不可取消(物理返回按键取消)
     * @param outsideTouchable true为可以点击空白区域false为不可点击
     * @param listener         事件监听
     * @return
     */
    public static BuildBean showMdAlert(Activity activity, CharSequence title, CharSequence msg, boolean cancleable, boolean outsideTouchable, DialogUIListener listener) {
        return DialogAssigner.getInstance().assignMdAlert(activity, title, msg, cancleable, outsideTouchable, listener);
    }

    /**
     * 带取消的底部弹出列表 默认可取消可点击
     *
     * @param context   上下文
     * @param words     素组集合
     * @param bottomTxt 底部按钮文本
     * @param listener  事件监听
     * @return
     */
    public static BuildBean showBottomSheetAndCancel(Context context, List<? extends CharSequence> words, CharSequence bottomTxt, DialogUIItemListener listener) {
        return showBottomSheetAndCancel(context, words, bottomTxt, true, true, listener);
    }

    /***
     * 带取消的底部弹出列表
     *
     * @param context          上下文
     * @param words            素组集合
     * @param bottomTxt        底部按钮文本
     * @param cancleable       true为可以取消false为不可取消
     * @param outsideTouchable true为可以点击空白区域false为不可点击
     * @param listener         事件监听
     * @return
     */
    public static BuildBean showBottomSheetAndCancel(Context context, List<? extends CharSequence> words, CharSequence bottomTxt, boolean cancleable, boolean outsideTouchable, DialogUIItemListener listener) {
        return DialogAssigner.getInstance().assignBottomSheetAndCancel(context, words, bottomTxt, cancleable, outsideTouchable, listener);
    }

    /**
     * 长时间中下位置显示。
     */
    public static void showToastLong(final String str) {
        showToast2Bottom(str, Toast.LENGTH_LONG);
    }

    /**
     * 对toast的简易封装。线程不安全，不可以在非UI线程调用。
     */
    private static void showToast2Bottom(String str, int showTime) {
        if (appContext == null) {
            throw new RuntimeException("DialogUIUtils not initialized!");
        }
        if (mToastBottom == null) {
            mToastBottom = Toast.makeText(appContext, str, showTime);
        } else {
            mToastBottom.setText(str);
        }
        mToastBottom.show();
    }
}
