package com.commonui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

 /**
  * @desc:         绘制文字的textview
  * @author:       Leo
  * @date:         2016/12/14
  */
public class MyTextView extends View
{
    private Paint paint;
    private String str = "你好";

    public MyTextView(Context context) {
        super(context);
        init();
    }

    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        // 实例化一个画笔工具
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);

        // 设置字体大小
        paint.setTextSize(50);

        // 设置画笔颜色
        paint.setColor(Color.RED);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        /**
         * getWidth() / 2 - paint.measureText(str) / 2
         * 让文字在水平方向居中
         */
        canvas.drawText(str, getWidth() / 2 - paint.measureText(str) / 2, getHeight() / 2, paint);
    }
}
