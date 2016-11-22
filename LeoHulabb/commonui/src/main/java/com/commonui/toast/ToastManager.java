package com.commonui.toast;

import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

import com.commonutils.StringUtils;

/**
  * @desc:         Toast管理类 防止重复多次弹出toast
  * @author:       Leo
  * @date:         2016/10/26
  */
public class ToastManager
{
    private static SuperToast toast = null;
    private static final Object sysnObject = new Object();
    private static Handler handler = null;
    private static Context context;

    /**
     * @param context application的上下文
     */
    public static void init(Context context) {
        ToastManager.context = context.getApplicationContext();
        handler = new Handler(context.getMainLooper());
    }

    private static void tip(final String message, int duration) {
        if (StringUtils.isEmpty(message))
            return;

        new ToastThread(new StringToastRunnable(context, message, duration)).start();
    }

    private static void tip(final int msgId, int duration) {
        new ToastThread(new StringIdToastRunnable(context, msgId, duration)).start();
    }

    public static void show(final String message) {
        tip(message, Toast.LENGTH_SHORT);
    }

    public static void show(final int msgId) {
        tip(msgId, Toast.LENGTH_SHORT);
    }

    public static void longShow(String message) {
        tip(message, Toast.LENGTH_LONG);
    }

    public static void longShow(int msgId) {
        tip(msgId, Toast.LENGTH_LONG);
    }

    public static void shortShow(String message) {
        tip(message, Toast.LENGTH_SHORT);
    }

    public static void shortShow(int msgId) {
        tip(msgId, Toast.LENGTH_SHORT);
    }

    static class ToastThread extends Thread {
        public ToastThread(Runnable runnable) {
            super(runnable);
        }
    }

    static class StringToastRunnable implements Runnable {

        Context context;
        String msg;
        int duration;

        public StringToastRunnable(Context context, String msg, int duration) {
            this.context = context;
            this.msg = msg;
            this.duration = duration;
        }

        @Override
        public void run() {

            handler.post(new Runnable() {

                @Override
                public void run() {
                    synchronized (sysnObject) {
                        if (toast != null) {
                            toast.cancel();
                        }

                        SuperToast.init().textNormal(msg);
                    }
                }
            });
        }
    }

    static class StringIdToastRunnable implements Runnable {

        Context context;
        int msgId;
        int duration;

        public StringIdToastRunnable(Context context, int msgId, int duration) {
            this.context = context;
            this.msgId = msgId;
            this.duration = duration;
        }

        @Override
        public void run() {
            synchronized (sysnObject) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (toast != null) {
                            toast.cancel();
                        }
                        SuperToast.init().textNormal(msgId);
                    }
                });
            }
        }
    }
}
