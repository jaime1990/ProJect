package com.commonui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * @desc:         自定义优惠券
 * @author:       Leo
 * @date:         2016/12/20
 */
public class MyCouponView extends View
{
    private int width;
    private int height;
    private int circleRadius = 30;
    private int circleGap = 12;
    float centerX;
    float centerY;

    // 锯齿画笔
    private Paint paint;
    // 状态字背景画笔
    private Paint textBGPaint;
    // 状态字画笔
    private Paint textPaint;

    public MyCouponView(Context context) {
        super(context, null);
    }

    public MyCouponView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    private void initPaint()
    {
        paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);

        textBGPaint = new Paint();
        textBGPaint.setColor(Color.GRAY);
        textBGPaint.setStyle(Paint.Style.FILL);
        textBGPaint.setAntiAlias(true);

        textPaint = new Paint();
        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(50);
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setAntiAlias(true);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;

        centerX = width / 2;
        centerY = height / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.translate(centerX, centerY);

        drawAngle(canvas);
        drawCircle(canvas);
        drawText(canvas);
    }

    private void drawText(Canvas canvas)
    {
        canvas.save();
        String mSlantedText = "不可用";
        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
        float textHeight = fontMetrics.descent - fontMetrics.ascent + fontMetrics.leading;
        float textLength = textPaint.measureText(mSlantedText);//文本长度

        float offset = (centerY - textLength) / 2;
        Rect rect = new Rect((int)offset, 0, (int) (centerY / 2 + offset), (int) centerY / 2);
        RectF rectF = new RectF(rect);
        rectF.right = textPaint.measureText(mSlantedText, 0, mSlantedText.length());
        rectF.bottom = textPaint.descent() - textPaint.ascent();
        rectF.left += (rect.width() - rectF.right) / 2.0f;
        rectF.top += (rect.height() - rectF.bottom) / 2.0f;


        float x = centerX - centerY + offset;
        canvas.translate(width - height / 4, height / 4);
//        canvas.rotate(45);
        canvas.drawText(mSlantedText, 0, 0, textPaint);
        Log.e("AnGLE", rectF.left + "===" + (rectF.top - textPaint.ascent()));
        Log.e("AnGLE", centerX + "===" + centerY);
//        canvas.rotate(45, textLength / 2f, textHeight / 2);
//        canvas.drawText(text, centerX * 3 / 8, -centerY * 3 / 4, textPaint);
        canvas.restore();
    }

    private void drawCircle(Canvas canvas)
    {
        float gap = circleGap + circleRadius * 2;

        canvas.drawCircle(centerX, 0, circleRadius, paint);
        canvas.drawCircle(centerX, gap * 1, circleRadius, paint);
        canvas.drawCircle(centerX, gap * 2, circleRadius, paint);
        canvas.drawCircle(centerX, gap * 3, circleRadius, paint);
        canvas.drawCircle(centerX, gap * (-1), circleRadius, paint);
        canvas.drawCircle(centerX, gap * (-2), circleRadius, paint);
        canvas.drawCircle(centerX, gap * (-3), circleRadius, paint);
    }

    private void drawAngle(Canvas canvas) {
        Path path = new Path();

        path.moveTo(centerX, -centerY);
        path.lineTo(centerX, 0);
        path.lineTo(centerX - centerY, -centerY);
        path.close();

        canvas.drawPath(path, textBGPaint);
    }
}
