package com.commonutils;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @desc:   view工具类
 * @author: Leo
 * @date:   2016/09/28
 */
public class ViewUtils
{
    public static <E extends View> E findViewById(Activity activity, int resId) {
        return (E) activity.findViewById(resId);
    }

    public static <E extends View> E findViewById(View view, int resId) {
        return (E) view.findViewById(resId);
    }

    public static void switchViewVisibility(View... targetViews) {
        for (View v : targetViews) {
            if (v != null) {
                v.setVisibility(v.getVisibility() == View.VISIBLE ? View.GONE
                        : View.VISIBLE);
            }
        }
    }

    public static void setViewShow(View... targetViews) {
        for (View v : targetViews) {
            if (v != null) {
                v.setVisibility(View.VISIBLE);
            }
        }
    }

    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    public static void setViewHide(View... targetViews) {
        for (View v : targetViews) {
            if (v != null) {
                v.setVisibility(View.GONE);
            }
        }
    }

    public static void setViewEnabled(boolean enable, View... targetViews) {
        for (View v : targetViews) {
            if (v != null) {
                v.setEnabled(enable);
            }
        }
    }

    public static void setViewVisibility(boolean show, View... targetViews) {
        for (View v : targetViews) {
            if (v != null) {
                v.setVisibility(show ? View.VISIBLE : View.GONE);
            }
        }
    }

    public static void setBackgroundResource(int resid, View... targetViews) {
        for (View v : targetViews) {
            if (v != null) {
                v.setBackgroundResource(resid);
            }
        }
    }

    public static void setImageDrawable(final ImageView imageView,
                                        int drawableInt, String url, final int dimenSize) {
        if (drawableInt > 0) {
            Bitmap bitmapOrg = BitmapFactory
                    .decodeResource(imageView.getResources(), drawableInt);
            imageView.setImageBitmap(Bitmap.createScaledBitmap(bitmapOrg,
                    dimenSize, dimenSize, false));
        } else if (StringUtils.isEmpty(url)) {
            // supplement volley add internet image

        }
    }

    private static int checkVilidity(float f) {
        if (f < 0) {
            return 0;
        }
        return (int) f;
    }

    private static int getVilidity(int actual, int expect) {
        if (actual < expect) {
            return actual;
        }
        return expect;
    }

    /**
     * 注册一个双击事件
     * 改自网友的，增加  Handler  处理，如果不加这个，会引起线程安全之类错误。<br>
     */
    public static void registerDoubleClickListener(View view, final OnDoubleClickListener tlistener) {
        if (tlistener == null) return;
        view.setOnClickListener(new View.OnClickListener() {
            private static final int DOUBLE_CLICK_TIME = 350;        //双击间隔时间350毫秒
            private boolean waitDouble = true;

            private Handler handler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    tlistener.OnSingleClick((View) msg.obj);
                }

            };

            //等待双击
            public void onClick(final View v) {
                if (waitDouble) {
                    waitDouble = false;//与执行双击事件
                    new Thread() {

                        public void run() {
                            try {
                                Thread.sleep(DOUBLE_CLICK_TIME);
                            } catch (InterruptedException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }    //等待双击时间，否则执行单击事件
                            if (!waitDouble) {
                                //如果过了等待事件还是预执行双击状态，则视为单击
                                waitDouble = true;
                                Message msg = handler.obtainMessage();
                                msg.obj = v;
                                handler.sendMessage(msg);
                            }
                        }

                    }.start();
                } else {
                    waitDouble = true;
                    tlistener.OnDoubleClick(v);    //执行双击
                }
            }
        });
    }

    public interface OnDoubleClickListener {
        void OnSingleClick(View v);

        void OnDoubleClick(View v);
    }
}
