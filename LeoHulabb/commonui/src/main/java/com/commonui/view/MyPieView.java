package com.commonui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * @desc:         饼状比例图
 * @author:       Leo
 * @date:         2016/12/15
 */
public class MyPieView extends View
{
    // 画笔
    private Paint paint = new Paint();
    // 控件尺寸
    private int width, height;
    // 画饼起始角度
    private float startAngle;
    // 显示数值
    private List<DataBean> dataBeanList;
    // 动画
    private int anim = 0;

    public MyPieView(Context context, List<DataBean> list) {
        super(context);
        this.dataBeanList = list;
    }

    public MyPieView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initData();
        initPaint();
    }

    private void initPaint() {
        // 设置画笔填充
        paint.setStyle(Paint.Style.FILL);
        // 抗锯齿
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

        drawPie(canvas);

        if (anim < 100) {
            anim ++;
            invalidate();
        }
    }

    private void drawPie(Canvas canvas)
    {
        float currentStartAngle = startAngle;
        // 重置画布中心点
        canvas.translate(width / 2, height / 2);

        // 设置饼状图半径
        float radio = (float) (Math.min(width, height) / 2);
        RectF rectF = new RectF(-radio, -radio, radio, radio);

        if (null == dataBeanList || dataBeanList.size() == 0) {
            return;
        }

        caculateAngle(currentStartAngle);

        Log.e("TAG currentStartAngle", currentStartAngle + "");

        for (DataBean dataBean : dataBeanList) {
            paint.setColor(dataBean.getColor());
            canvas.drawArc(rectF, anim * 3.6f * (dataBean.getStart() / dataBean.getTotal()), anim * 3.6f * (dataBean.getValue()), true, paint);
        }
    }

    // 按比例计算角度
    private void caculateAngle(float startAngle)
    {
        float sumValue = 0;
        for (DataBean dataBean : dataBeanList) {
            sumValue += dataBean.getValue();
        }

        float value;
        for (DataBean dataBean : dataBeanList) {
            value = dataBean.getValue() / sumValue;
            dataBean.setStart(startAngle);
            startAngle += dataBean.getValue();
            dataBean.setTotal(sumValue);
            dataBean.setValue(value);
        }
    }

    public void setStartAngle(int startAngle) {
        this.startAngle = startAngle;
        invalidate();
    }

    public void setData(List<DataBean> dataBeanList) {
        this.dataBeanList = dataBeanList;
        invalidate();
    }

    private int[] mColors = {Color.RED, Color.BLACK, Color.YELLOW, Color.WHITE, Color.GREEN};
    private float[] mValue = {10, 80, 20, 60, 35};
    private String[] mName = {"北京", "上海", "深圳", "成都", "武汉", "天津", "厦门", "首尔", "哥伦比亚", "哈"};

    private void initData()
    {
        if (null == dataBeanList) {
            dataBeanList = new ArrayList<>();
        }

        int length = Math.min(mColors.length, Math.min(mValue.length, mName.length));

        for (int i = 0; i < length; i++) {
            DataBean dataBean = new DataBean(mValue[i], mName[i], mColors[i]);
            dataBeanList.add(dataBean);
        }
    }

    public class DataBean
    {
        public float value;
        public String name;
        public int color;
        public float start;
        public float total;

        public DataBean(float value, String name, int color) {
            this.value = value;
            this.name = name;
            this.color = color;
        }

        public float getTotal() {
            return total;
        }

        public void setTotal(float total) {
            this.total = total;
        }

        public float getStart() {
            return start;
        }

        public void setStart(float start) {
            this.start = start;
        }

        public float getValue() {
            return value;
        }

        public void setValue(float value) {
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getColor() {
            return color;
        }

        public void setColor(int color) {
            this.color = color;
        }
    }
}
