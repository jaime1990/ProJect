package com.commonui.dialog;

import android.app.Activity;
import android.content.Context;

import java.util.List;

/**
 * Created by Administrator on 2016/10/9 0009.
 */
public class DialogAssigner implements Assignable {

    private static DialogAssigner instance;

    private DialogAssigner() {

    }

    public static DialogAssigner getInstance() {
        if (instance == null) {
            instance = new DialogAssigner();
        }
        return instance;
    }

    @Override
    public BuildBean assignAlert(Context activity, CharSequence title, CharSequence msg, boolean cancleable, boolean outsideTouchable, DialogUIListener listener) {
        BuildBean bean = new BuildBean();
        bean.context = activity;
        bean.msg = msg;
        bean.title = title;
        bean.text2 = "";
        bean.cancelable = cancleable;
        bean.outsideTouchable = outsideTouchable;
        bean.listener = listener;
        bean.type = CommonConfig.TYPE_ALERT;
        return bean;
    }

    @Override
    public BuildBean assignMdAlert(Activity activity, CharSequence title, CharSequence msg, boolean cancleable, boolean outsideTouchable, DialogUIListener listener) {
        BuildBean bean = new BuildBean();
        bean.context = activity;
        bean.msg = msg;
        bean.title = title;
        bean.cancelable = cancleable;
        bean.outsideTouchable = outsideTouchable;
        bean.listener = listener;
        bean.type = CommonConfig.TYPE_MD_ALERT;
        bean.btn1Color = CommonConfig.mdBtnColor;
        bean.btn2Color = CommonConfig.mdBtnColor;
        bean.btn3Color = CommonConfig.mdBtnColor;
        return bean;
    }

    @Override
    public BuildBean assignBottomSheetAndCancel(Context context, List<? extends CharSequence> words, CharSequence bottomTxt, boolean cancleable, boolean outsideTouchable, DialogUIItemListener listener) {
        BuildBean bean = new BuildBean();
        bean.context = context;
        bean.cancelable = cancleable;
        bean.outsideTouchable = outsideTouchable;
        bean.itemListener = listener;
        bean.wordsIos = words;
        bean.type = CommonConfig.TYPE_BOTTOM_SHEET_CANCEL;
        return bean;
    }
}
