package com.commonui.dialog;

import android.app.Activity;
import android.content.Context;

import java.util.List;

/**
 * ========================================
 * <p/>
 * 版 权：dou361.com 版权所有 （C） 2015
 * <p/>
 * 作 者：陈冠明
 * <p/>
 * 个人网站：http://www.dou361.com
 * <p/>
 * 版 本：1.0
 * <p/>
 * 创建日期：2016/11/1 15:08
 * <p/>
 * 描 述：构建弹出框样式方法
 * <p/>
 * <p/>
 * 修订历史：
 * <p/>
 * ========================================
 */
public interface Assignable {

    /**
     * 提示弹出框
     */
    BuildBean assignAlert(Context activity, CharSequence title, CharSequence msg, boolean cancleable, boolean outsideTouchable, final DialogUIListener listener);

    /**
     * md风格弹出框
     */
    BuildBean assignMdAlert(Activity activity, CharSequence title, CharSequence msg, boolean cancleable, boolean outsideTouchable, final DialogUIListener listener);

    /**
     * 带取消的底部弹出列表
     */
    BuildBean assignBottomSheetAndCancel(Context context, List<? extends CharSequence> words, CharSequence bottomTxt, boolean cancleable, boolean outsideTouchable, final DialogUIItemListener listener);
}
