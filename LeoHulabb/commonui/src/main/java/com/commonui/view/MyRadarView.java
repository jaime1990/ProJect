package com.commonui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * @desc:         雷达图
 * @author:       Leo
 * @date:         2016/12/15
 */
public class MyRadarView extends View
{
    // 设置维度
    private int dataCount = 6;
    // 每个角的弧度
    private float radian = (float) (Math.PI * 2 / dataCount);
    // 雷达图半径
    private float radius;
    // 中心X坐标
    private int centerX;
    // 中心Y坐标
    private int centerY;
    // 各维度标题
    private String[] titleTexts = {"履约能力", "信用历史", "人脉关系",
            "行为偏好", "身份特质", "马云干崽"};

    // 各维度分值
    private float[] data = {170, 110, 160, 10, 180, 130};
    // 数据最大值
    private float maxValue = 190;
    // 动画
    private int anim = 0;

    // 雷达区画笔
    private Paint radPaint;
    // 数据区画笔
    private Paint valuePaint;
    // 文本区画笔
    private Paint textPaint;

    public MyRadarView(Context context) {
        super(context, null);
    }

    public MyRadarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    private void initPaint()
    {
        radPaint = new Paint();
        radPaint.setColor(Color.GRAY);
        radPaint.setStyle(Paint.Style.STROKE);
        radPaint.setAntiAlias(true);

        textPaint = new Paint();
        textPaint.setColor(Color.RED);
        textPaint.setTextSize(32);
        textPaint.setAntiAlias(true);

        valuePaint = new Paint();
        valuePaint.setColor(Color.BLUE);
        valuePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        valuePaint.setAntiAlias(true);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        radius = Math.min(w, h) / 2 * 0.7f;
        centerX = w / 2;
        centerY = h / 2;
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        dataCount = Math.min(titleTexts.length, data.length);
        drawPolygon(canvas);
        drawLine(canvas);
        drawValue(canvas);

        if (anim < 100) {
            anim++;
            invalidate();
        } else {
            drawText(canvas);
        }
    }

    // 绘制正多边形
    private void drawPolygon(Canvas canvas)
    {
        float minRadius = radius / (dataCount - 1);
        Path path = new Path();

        // 绘制圆环
        for (int i = 1; i < dataCount; i++) {
            float r = minRadius * (i);
            path.reset();

            // 绘制圆环上的点
            for (int j = 0; j < dataCount; j++)
            {
                if (j == 0) {
                    path.moveTo(centerX + r, centerY);
                } else {
                    float x = (float) (centerX + r * Math.cos(radian * j));
                    float y = (float) (centerY + r * Math.sin(radian * j));

                    path.lineTo(x, y);
                }
            }

            path.close();
            canvas.drawPath(path, radPaint);
        }
    }

    // 绘制斜线
    private void drawLine(Canvas canvas)
    {
        Path path = new Path();
        for (int i = 0; i < dataCount; i++) {
            path.reset();
            path.moveTo(centerX, centerY);
            float x = (float) (centerX + radius * Math.cos(radian * i));
            float y = (float) (centerY + radius * Math.sin(radian * i));
            path.lineTo(x, y);
            canvas.drawPath(path, radPaint);
        }
    }

    // 绘制文本
    private void drawText(Canvas canvas)
    {
        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
        float fontHeight = fontMetrics.descent - fontMetrics.ascent;
        float textHeight = fontMetrics.descent + fontMetrics.ascent + fontMetrics.leading;

        for (int j = 0; j < dataCount; j++)
        {
            double angle = radian * j;

            float x = (float) (centerX + (radius + fontHeight / 2) * Math.cos(angle));
            float y = (float) (centerY + (radius + fontHeight / 2) * Math.sin(angle));
            float textLength = textPaint.measureText(titleTexts[j]);//文本长度

            switch (caculateArea(angle)) {
                case 0:
                    canvas.drawText(titleTexts[j], x, centerY - textHeight / 2, textPaint);
                    break;
                case -1:
                    canvas.drawText(titleTexts[j], centerX - textLength / 2, centerY + radius + textHeight / 2, textPaint);
                    break;
                case -2:
                    canvas.drawText(titleTexts[j], centerX - radius - fontHeight / 2, centerY - textHeight / 2, textPaint);
                    break;
                case -3:
                    canvas.drawText(titleTexts[j], centerX - textLength / 2, centerY - radius - textHeight / 2, textPaint);
                    break;
                case 1:
                    canvas.drawText(titleTexts[j], x, y, textPaint);
                    break;
                case 2:
                    canvas.drawText(titleTexts[j], x - textLength, y, textPaint);
                    break;
                case 3:
                    canvas.drawText(titleTexts[j], x - textLength, y + fontHeight / 2, textPaint);
                    break;
                case 4:
                    canvas.drawText(titleTexts[j], x, y + fontHeight / 2, textPaint);
                    break;
            }
            Log.e("TEXT", titleTexts[j] + caculateArea(angle));
        }
    }

    // 绘制区域
    private void drawValue(Canvas canvas)
    {
        Path path = new Path();

        for (int i = 0; i < dataCount; i++) {
            float percent = data[i] / maxValue;
            float x = (float) (centerX + radius * anim / 100 * Math.cos(radian * i) * percent);
            float y = (float) (centerY + radius * anim / 100 * Math.sin(radian * i) * percent);

            if (i == 0) {
                path.moveTo(x, centerY);
            } else {
                path.lineTo(x, y);
            }
            valuePaint.setAlpha(255);
            canvas.drawCircle(x, y, 2, valuePaint);
        }
        valuePaint.setAlpha(127);
        canvas.drawPath(path, valuePaint);
    }

    // 计算顶点所在象限
    private int caculateArea(double angle) {

        if (angle == 0 || angle == Math.PI * 2) {
            return 0;   // x正半轴
        } else if (angle == Math.PI / 2) {
            return -1;  // y正半轴
        } else if (angle == Math.PI) {
            return -2;  // x负半轴
        } else if (angle == Math.PI * 3 / 2) {
            return -3;  // y负半轴
        } else if (angle > 0 && angle < Math.PI / 2) {
            return 4;   // 第四象限
        } else if (angle < Math.PI) {
            return 3;   // 第三象限
        } else if (angle < Math.PI * 3 / 2) {
            return 2;   // 第二象限
        } else if (angle < 2 * Math.PI) {
            return 1;   // 第一象限
        }

        return 0;
    }

    // 获取坐标点
    private void getPoint(int position, int radianMargin, float percent) {
        int x = 0;
        int y = 0;

        if (position == 0) {
            x = (int) (centerX + (radius + radianMargin) * Math.sin(radian) * percent);
        }
    }
}