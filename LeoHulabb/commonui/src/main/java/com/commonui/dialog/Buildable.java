package com.commonui.dialog;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.Window;

import com.commonui.R;

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
 * 创建日期：2016/11/1 16:15
 * <p/>
 * 描 述：
 * <p/>
 * <p/>
 * 修订历史：
 * <p/>
 * ========================================
 */
public class Buildable {

    protected static int singleChosen;

    protected BuildBean buildByType(BuildBean bean) {
        ToolUtils.fixContext(bean);
        switch (bean.type) {
            case CommonConfig.TYPE_ALERT:
                buildAlert(bean);
                break;
            case CommonConfig.TYPE_MD_ALERT:
                buildMdAlert(bean);
                break;
            case CommonConfig.TYPE_BOTTOM_SHEET_CANCEL:
                ToolUtils.newCustomDialog(bean);
                buildBottomSheetCancel(bean);
                break;
            default:
                break;
        }
        return bean;
    }

    protected BuildBean buildAlert(BuildBean bean) {
        bean.isVertical = true;
        bean.hint1 = "";
        buildCommon(bean);
        return bean;
    }

    protected BuildBean buildMdAlert(final BuildBean bean) {
        AlertDialog.Builder builder = new AlertDialog.Builder(bean.context);
        builder.setTitle(bean.title)
                .setMessage(bean.msg)
                .setPositiveButton(bean.text1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        bean.listener.onPositive();
                        dialog.dismiss();
                    }
                })
                .setNegativeButton(bean.text2, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        bean.listener.onNegative();
                        dialog.dismiss();
                    }
                }).setNeutralButton(bean.text3, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                bean.listener.onNeutral();
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                bean.listener.onCancle();
            }
        });
        bean.alertDialog = dialog;
        bean.alertDialog.setCancelable(bean.cancelable);
        bean.alertDialog.setCanceledOnTouchOutside(bean.outsideTouchable);
        return bean;
    }

    protected BuildBean buildBottomSheetCancel(BuildBean bean) {
        BottomSheetCancelHolder holder = new BottomSheetCancelHolder(bean.context);
        bean.dialog.setContentView(holder.rootView);
        bean.dialog.setCancelable(bean.cancelable);
        bean.dialog.setCanceledOnTouchOutside(bean.outsideTouchable);
        holder.assingDatasAndEvents(bean.context, bean);
        bean.viewHeight = ToolUtils.mesureHeight(holder.rootView, holder.lv);
        Window window = bean.dialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setWindowAnimations(R.style.dialogui_bottom_style);
        return bean;
    }

    private BuildBean buildCommon(BuildBean bean) {
        AlertDialog.Builder builder = new AlertDialog.Builder(bean.context);
        AlertDialogHolder holder = new AlertDialogHolder(bean.context);
        builder.setView(holder.rootView);
        AlertDialog dialog = builder.create();
        bean.alertDialog = dialog;
        bean.alertDialog.setCancelable(bean.cancelable);
        bean.alertDialog.setCanceledOnTouchOutside(bean.outsideTouchable);
        holder.assingDatasAndEvents(bean.context, bean);
        return bean;
    }
}
