package com.commonui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

import com.pulltolargerlistview.R;

/**
 * @desc:         优惠卡券
 * @author:       Leo
 * @date:         2016/12/21
 */
public class MySlantedTextView extends View
{
    // 文字旋转角度45度
    public static final int ROTATE_ANGLE = 45;

    // 控件类型 1 空白， 2 标签， 3 选中
    public static final int COUPON_NONE = 0;
    public static final int COUPON_TAG = 1;
    public static final int COUPON_SELECT = 2;

    // 控件宽度
    private float mWidth;
    // 控件长度
    private float mHeight;

    // 标签背景画笔
    private Paint mPaint;
    // 标签文字画笔
    private TextPaint mTextPaint;

    // 标签背景边长
    private float mSlantedLength = 100;
    // 标签背景颜色
    private int mSlantedBackgroundColor = Color.GRAY;
    // 标签字体大小
    private float mTextSize = 16;
    // 标签字体颜色
    private int mTextColor = Color.WHITE;
    // 标签文本
    private String mSlantedText = "";
    // 文字偏移量
    private float mSlantedOffset;

    // 锯齿半径
    private float mCouponRadius = 22;
    // 锯齿间距
    private float mCouponGap;
    // 锯齿颜色
    private int mCouponColor = Color.WHITE;

    // 选中状态图
    private int mCouponPic;
    // 选中状态图与上边缘间距
    private float mCouponPicMarginTop = 20;
    // 选中状态图与下边缘间距
    private float mCouponPicMarginRight = 30;

    // 控件类型，默认空白
    private int couponMode = COUPON_NONE;

    public MySlantedTextView(Context context) {
        this(context, null);
    }

    public MySlantedTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
        initPaint();
    }

    private void initPaint()
    {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER));
        mPaint.setAntiAlias(true);
        mPaint.setColor(mSlantedBackgroundColor);

        mTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setAntiAlias(true);
        mTextPaint.setTextSize(mTextSize);
        mTextPaint.setColor(mTextColor);
    }

    public void init(AttributeSet attrs)
    {
        TypedArray array = getContext().obtainStyledAttributes(attrs, R.styleable.MyCouponView);

        mTextSize = array.getDimension(R.styleable.MyCouponView_couponTextSize, mTextSize);
        mTextColor = array.getColor(R.styleable.MyCouponView_couponTextColor, mTextColor);
        mSlantedText = array.getString(R.styleable.MyCouponView_couponTextStr);
        mSlantedLength = array.getDimension(R.styleable.MyCouponView_couponLength, mSlantedLength);
        mSlantedOffset = array.getDimension(R.styleable.MyCouponView_couponTextOffset, mSlantedLength / 7);
        mSlantedBackgroundColor = array.getColor(R.styleable.MyCouponView_couponTextBackground, mSlantedBackgroundColor);

        mCouponRadius = array.getDimension(R.styleable.MyCouponView_couponRadius, mCouponRadius);
        mCouponGap = array.getDimension(R.styleable.MyCouponView_couponGap, mCouponRadius * 5 / 4);
        mCouponColor = array.getColor(R.styleable.MyCouponView_couponColor, mCouponColor);

        mCouponPicMarginTop = array.getDimension(R.styleable.MyCouponView_couponPicMarginTop, mCouponPicMarginTop);
        mCouponPicMarginRight = array.getDimension(R.styleable.MyCouponView_couponPicMarginRight, mCouponPicMarginRight);
        mCouponPic = array.getResourceId(R.styleable.MyCouponView_couponPic, mCouponPic);

        if (array.hasValue(R.styleable.MyCouponView_couponTextStr)) {
            mSlantedText = array.getString(R.styleable.MyCouponView_couponTextStr);
        }

        if (array.hasValue(R.styleable.MyCouponView_couponMode)) {
            couponMode = array.getInt(R.styleable.MyCouponView_couponMode, couponMode);
        }

        array.recycle();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;

        if (mSlantedLength == 100) {
            mSlantedLength = h / 2;
        }
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        if (couponMode == COUPON_TAG) {
            drawBackground(canvas);
            drawText(canvas);
        } else if (couponMode == COUPON_SELECT) {
            drwaPic(canvas);
        }

        drawCircle(canvas);
    }

    // 绘制右上角选中图标
    private void drwaPic(Canvas canvas)
    {
        Bitmap mBitmap = BitmapFactory.decodeResource(getResources(), mCouponPic);

        if (null == mBitmap) {
            return;
        }

        Rect rect = new Rect(0, 0, mBitmap.getWidth(), mBitmap.getWidth());
        RectF rectf = new RectF(mWidth - mHeight / 5 - mCouponPicMarginRight, mCouponPicMarginTop, mWidth - mCouponPicMarginRight, mCouponPicMarginTop + mHeight / 5);

        canvas.drawBitmap(mBitmap, rect, rectf, null);
    }

    /**
     * 绘制锯齿
     *     设定控件两边锯齿留白范围为 h/8 ~ h/6
     *     计算得锯齿数范围为 （2h + 3g）/ 3(2r + g) ~ (3h + 3g) / 4(2r + g)
     */
    private void drawCircle(Canvas canvas)
    {
        float min = (2 * mHeight + 3 * mCouponGap) / (3 * (2 * mCouponRadius + mCouponGap));
        float max = (7 * mHeight + 9 * mCouponGap) / (9 * (2 * mCouponRadius + mCouponGap));

        int n = (int) max;

        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(mCouponColor);
        paint.setAntiAlias(true);

        // 判断为偶数个锯齿
        if (n / 2 == 0) {
            for (int i = 0; i < n / 2; i++) {
                canvas.drawCircle(mWidth, mHeight / 2 - mCouponGap / 2 - mCouponRadius - (mCouponGap + 2 * mCouponRadius) * i, mCouponRadius, paint);
                canvas.drawCircle(mWidth, mHeight / 2 - mCouponGap / 2 - mCouponRadius - (mCouponGap + 2 * mCouponRadius) * (-i), mCouponRadius, paint);

                canvas.drawCircle(0, mHeight / 2 - mCouponGap / 2 - mCouponRadius - (mCouponGap + 2 * mCouponRadius) * i, mCouponRadius, paint);
                canvas.drawCircle(0, mHeight / 2 - mCouponGap / 2 - mCouponRadius - (mCouponGap + 2 * mCouponRadius) * (-i), mCouponRadius, paint);
            }
        }

        if (n / 2 != 0) {
            for (int i = 0; i <= n / 2; i++) {
                canvas.drawCircle(mWidth, mHeight / 2 + (mCouponGap + 2 * mCouponRadius) * (i), mCouponRadius, paint);
                canvas.drawCircle(mWidth, mHeight / 2 + (mCouponGap + 2 * mCouponRadius) * (-i), mCouponRadius, paint);

                canvas.drawCircle(0, mHeight / 2 + (mCouponGap + 2 * mCouponRadius) * (i), mCouponRadius, paint);
                canvas.drawCircle(0, mHeight / 2 + (mCouponGap + 2 * mCouponRadius) * (-i), mCouponRadius, paint);
            }
        }
    }

    /**
     * 绘制标签三角形背景
     *     在控件右上角绘制三角形标签背景
     *     边长为mSlantedLength, 默认为高度的一半
     *     此时，坐标轴原点在控件左上角
     */
    private void drawBackground(Canvas canvas)
    {
        Path path = new Path();
        path.moveTo(mWidth, 0);
        path.lineTo(mWidth - mSlantedLength, 0);
        path.lineTo(mWidth, mSlantedLength);

        path.close();
        canvas.drawPath(path, mPaint);

        // 保存画布状态
        canvas.save();
    }

    /**
     * 绘制倾斜45度的文本
     *     旋转圆心为右上角三角形斜边的中点
     *
     *     防止文字超出边界
     */
    private void drawText(Canvas canvas)
    {
        if (null == mSlantedText) {
            return;
        }

        // 获取文本的宽高
        float textWidth = mTextPaint.measureText(mSlantedText, 0, mSlantedText.length());
        float textHeight = mTextPaint.descent() - mTextPaint.ascent();

        float eachTextWidth = textWidth / mSlantedText.length();

        float maxLength = (float) ((mSlantedLength * Math.cos(ROTATE_ANGLE) - (mSlantedOffset + textHeight)) * 2);

        if (textWidth > maxLength) {
            int length = (int) (maxLength / eachTextWidth);
            mSlantedText = mSlantedText.substring(0, length + 1);
        }

        /**
         * 计算文本标签的基点
         *     横坐标：控件总长度 - 标签边长的一半 - 文本长度的一半
         *     纵坐标：标签长度的一半 - 偏移量（旋转后文字到斜边边缘的距离）
         **/
        float toX = mWidth - mSlantedLength / 2 - textWidth / 2;
        float toY = mSlantedLength / 2 - mSlantedOffset;

        // 计算右上角三角形标签斜边的中点坐标
        float centerX = mWidth - mSlantedLength / 2;
        float centerY = mSlantedLength / 2;

        // 围绕标签斜边中点旋转
        canvas.rotate(ROTATE_ANGLE, centerX, centerY);
        canvas.drawText(mSlantedText, toX, toY, mTextPaint);
        canvas.restore();
    }

    public MySlantedTextView setText(String str) {
        mSlantedText = str;
        postInvalidate();
        return this;
    }

    public MySlantedTextView setText(int res) {
        String str = getResources().getString(res);
        if (!TextUtils.isEmpty(str)) {
            setText(str);
        }
        return this;
    }

    public String getText() {
        return mSlantedText;
    }

    public MySlantedTextView setSlantedBackgroundColor(int color) {
        mSlantedBackgroundColor = color;
        mPaint.setColor(mSlantedBackgroundColor);
        postInvalidate();
        return this;
    }

    public MySlantedTextView setTextColor(int color) {
        mTextColor = color;
        mTextPaint.setColor(mTextColor);
        postInvalidate();
        return this;
    }

    public MySlantedTextView setTextSize(int size) {
        this.mTextSize = size;
        mPaint.setTextSize(mTextSize);
        postInvalidate();
        return this;
    }

    /**
     * set slanted space length
     *
     * @param length
     * @return this
     */
    public MySlantedTextView setSlantedLength(int length) {
        mSlantedLength = length;
        postInvalidate();
        return this;
    }

    public void setCouponMode(int mode) {
        this.couponMode = mode;
        postInvalidate();
    }
}