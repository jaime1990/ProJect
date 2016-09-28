package com.commonui.button;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;


/**
 * @desc:   自定义动画Button
 * @author: Leo
 * @date:   2016/09/27
 */
public class Button extends android.widget.Button implements View.OnClickListener {
    public Button(Context context) {
        this(context, null, 0);
        setOnClickListener(this);
    }

    public Button(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        setOnClickListener(this);
    }

    public Button(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOnClickListener(this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        switch (event.getAction())
        {
//            case MotionEvent.ACTION_DOWN:
//            case MotionEvent.ACTION_MOVE:
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void onClick(View v) {
    }
}
