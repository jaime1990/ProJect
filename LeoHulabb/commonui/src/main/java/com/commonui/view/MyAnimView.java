package com.commonui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;



/**
 * @desc:         自定义View动画
 * @author:       Leo
 * @date:         2016/12/15
 */
public class MyAnimView extends View
{
    private Paint paint;
    private int width;
    private int height;

    public MyAnimView(Context context) {
        super(context);
    }

    public MyAnimView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    private void initPaint() {
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        paint.setColor(Color.RED);
//        canvas.translate(250, 250);
//        canvas.drawCircle(0, 0, 100, paint);
//
//        paint.setColor(Color.BLUE);
//        canvas.translate(250, 250);
//        canvas.drawCircle(0, 0, 100, paint);

        canvas.translate(width / 2, height / 2);

        paint.setColor(Color.BLUE);
        float w = (float) (width / 2 * 0.5);
        float h = (float) (height / 2 * 0.5);
        Log.i("W", w + "");
        Log.i("H", h + "");
        RectF rectF = new RectF(w, h, w, h);

        for (int i = 0; i < 20; i++) {
            canvas.scale(0.9f, 0.9f);
            canvas.drawRect(rectF, paint);
        }
    }
}
