package com.commonui.button;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;


/**
 * @desc:   自定义动画Button
 * @author: Leo
 * @date:   2016/09/27
 */
public class Button extends ScalableTextView {

    public Button(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
    }
}
