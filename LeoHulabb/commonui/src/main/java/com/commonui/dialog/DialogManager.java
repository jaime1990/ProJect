package com.commonui.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * @desc:   弹框管理类
 * @author: Leo
 * @date:   2016/09/28
 */
public class DialogManager
{
    public Context context;
    private DialogPositiveButtonListener listener;

    public DialogManager(Context context) {
        this.context = context;
    }

    public void showDialog(String message) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setMessage(message);
        dialog.setCancelable(false);//点击框外取消
        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (listener != null) {
                    listener.setDialogPositiveButtonListener();
                }
            }

        });
        dialog.setNegativeButton("取消", null);
        dialog.show();
    }

    public void setDialogPositiveButtonListener(DialogPositiveButtonListener listener) {
        this.listener = listener;
    }

    public interface DialogPositiveButtonListener {
        void setDialogPositiveButtonListener();
    }
}
