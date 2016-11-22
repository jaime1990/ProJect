package com.commonui.toast;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.commonui.R;
import com.commonutils.ContextUtils;
import com.commonutils.SizeUtils;
public class SuperToast extends Toast {

    private Context context;
    private TextView message;

    private SuperToast(Context context) {
        super(context);
        this.context = context;
        setDuration(Toast.LENGTH_SHORT);
    }

    volatile static SuperToast toast;

    public synchronized static SuperToast init() {
        if (toast == null) {
            synchronized (SuperToast.class) {
                if (toast == null) {
                    toast = new SuperToast(ContextUtils.getContext());
                }
            }
        }
        return toast;
    }

    public SuperToast duration(int duration) {
        super.setDuration(duration);
        return this;
    }

    public void textNormal(CharSequence s){
        text(s,0);
    }

    public void textNormal(int msgId){
        text(msgId,0);
    }

    public void textSuccess(CharSequence s){
        text(s,1);
    }

    public void textError(CharSequence s){
        text(s,2);
    }

    private void text(CharSequence s, int type)
    {
        if(getView() == null) {
            int marginBottom = SizeUtils.dp2px(context, 70);
            View v = View.inflate(context, R.layout.base_view_toast, null);
            message = (TextView) v.findViewById(R.id.message);
            setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, marginBottom);
            setDuration(Toast.LENGTH_SHORT);
            setView(v);
        }

        //设置图片
//           if(type == 0 && image.getVisibility() != View.GONE){
//               image.setVisibility(View.GONE);
//           }else if(type == 1){
//               if(image.getVisibility() != View.VISIBLE){
//                  image.setVisibility(View.VISIBLE);
//               }
//               image.setImageResource(android.R.drawable.stat_notify_chat);
//           }else if(type == 2){
//               if(image.getVisibility() != View.VISIBLE){
//                  image.setVisibility(View.VISIBLE);
//               }
//               image.setImageResource(android.R.drawable.stat_notify_error);
//           }
        message.setText(s);
        show();
    }

    private void text(int msgId, int type)
    {
        if(getView() == null) {
            int marginBottom = SizeUtils.dp2px(context, 70);
            View v = View.inflate(context, R.layout.base_view_toast, null);
            message = (TextView) v.findViewById(R.id.message);
            setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, marginBottom);
            setDuration(Toast.LENGTH_SHORT);
            setView(v);
        }

        //设置图片
//           if(type == 0 && image.getVisibility() != View.GONE){
//               image.setVisibility(View.GONE);
//           }else if(type == 1){
//               if(image.getVisibility() != View.VISIBLE){
//                  image.setVisibility(View.VISIBLE);
//               }
//               image.setImageResource(android.R.drawable.stat_notify_chat);
//           }else if(type == 2){
//               if(image.getVisibility() != View.VISIBLE){
//                  image.setVisibility(View.VISIBLE);
//               }
//               image.setImageResource(android.R.drawable.stat_notify_error);
//           }
        message.setText(msgId);
        show();
    }
}