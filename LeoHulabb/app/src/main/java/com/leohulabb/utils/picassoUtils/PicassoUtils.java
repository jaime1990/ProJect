package com.leohulabb.utils.picassoUtils;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.leohulabb.BaseApplication;
import com.leohulabb.R;
import com.leohulabb.utils.ScreenUtils;
import com.leohulabb.utils.picassoUtils.transformation.BlurTransformation;
import com.leohulabb.utils.picassoUtils.transformation.CropCircleTransformation;
import com.leohulabb.utils.picassoUtils.transformation.RoundedCornersTransformation;
import com.squareup.picasso.LruCache;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

/**
 * Created by Leo on 2016/7/22.
 */
public class PicassoUtils
{
    private final static PicassoUtils REQUEST_IMPL = new PicassoUtils();
    public static Picasso mPicasso;

    private PicassoUtils()
    {
        Picasso.Builder picasso = new Picasso.Builder(BaseApplication.getContext());
        picasso.memoryCache(new LruCache(2 * 1024 * 1024));
//        picasso.defaultBitmapConfig(Bitmap.Config.RGB_565);

        Picasso.setSingletonInstance(picasso.build());

        setmPicasso(picasso.build());
    }

    public static PicassoUtils get() {
        return REQUEST_IMPL;
    }

    public static Picasso getmPicasso() {
        return mPicasso;
    }

    public static void setmPicasso(Picasso picasso) {
        mPicasso = picasso;
    }

    /**
     * 加载项目图片
     * @param imageRes
     * @param imageView
     */
    public void picassoLoader(int imageRes, ImageView imageView)
    {
        mPicasso.with(imageView.getContext())
                .load(imageRes)
                .placeholder(R.mipmap.bg_square_ing)
                .error(R.mipmap.bg_square_no)
                .into(imageView);
    }

    /**
     * 加载网络图片
     * @param imageUrl
     * @param imageView
     * @param transformer
     */
    public void picassoLoader(String imageUrl, ImageView imageView, Transformation transformer)
    {
        mPicasso.with(imageView.getContext())
                .load(imageUrl)
                .transform(transformer)
                .placeholder(R.mipmap.bg_square_ing)
                .error(R.mipmap.bg_square_no)
                .into(imageView);
    }

    /**
     * 加载网络图片
     * @param imageUrl
     * @param imageView
     */
    public void picassoLoader(String imageUrl, ImageView imageView)
    {
        mPicasso.with(imageView.getContext())
                .load(imageUrl)
                .placeholder(R.mipmap.bg_square_ing)
                .error(R.mipmap.bg_square_no)
                .into(imageView);
    }

    /**
     * 加载圆形图片
     * @param imageUrl
     * @param imageView
     */
    public void picassoCircleLoader(String imageUrl, ImageView imageView)
    {
        mPicasso.with(imageView.getContext())
                .load(imageUrl)
                .transform(new CropCircleTransformation())
                .placeholder(R.mipmap.bg_square_ing)
                .error(R.mipmap.bg_square_no)
                .into(imageView);
    }

    public void picassoCircleLoader(int imageRes, ImageView imageView)
    {
        mPicasso.with(imageView.getContext())
                .load(imageRes)
                .transform(new CropCircleTransformation())
                .placeholder(R.mipmap.bg_square_ing)
                .error(R.mipmap.bg_square_no)
                .into(imageView);
    }

    /**
     * 加载圆角图片
     * @param imageUrl
     * @param imageView
     */
    public void picassoRoundedLoader(String imageUrl, ImageView imageView)
    {
        mPicasso.with(imageView.getContext())
                .load(imageUrl)
                .transform(new RoundedCornersTransformation(ScreenUtils.dp2px(imageView.getContext(), 10), 0))
                .placeholder(R.mipmap.bg_square_ing)
                .error(R.mipmap.bg_square_no)
                .into(imageView);
    }

    /**
     * 加载圆角图片
     * @param imageRes
     * @param imageView
     */
    public void picassoRoundedLoader(int imageRes, ImageView imageView)
    {
        mPicasso.with(imageView.getContext())
                .load(imageRes)
                .transform(new RoundedCornersTransformation(ScreenUtils.dp2px(imageView.getContext(), 10), 0))
                .placeholder(R.mipmap.bg_square_ing)
                .error(R.mipmap.bg_square_no)
                .into(imageView);
    }

    /**
     * 自定义圆角加载
     * @param imageUrl
     * @param imageView
     * @param radio
     * @param margin
     */
    public void picassoRoundedLoader(String imageUrl, ImageView imageView, int radio, int margin)
    {
        mPicasso.with(imageView.getContext())
                .load(imageUrl)
                .transform(new RoundedCornersTransformation(radio, margin))
                .placeholder(R.mipmap.bg_square_ing)
                .error(R.mipmap.bg_square_no)
                .into(imageView);
    }

    /**
     * 图片模糊效果
     * @param imageUrl
     * @param imageView
     */
    public void picassoBlurLoader(String imageUrl, ImageView imageView)
    {
        mPicasso.with(imageView.getContext())
                .load(imageUrl)
                .transform(new BlurTransformation(imageView.getContext()))
                .placeholder(R.mipmap.bg_square_ing)
                .error(R.mipmap.bg_square_no)
                .into(imageView);
    }

    /**
     * 图片模糊效果
     * @param imageRes
     * @param imageView
     */
    public void picassoBlurLoader(int imageRes, ImageView imageView)
    {
        mPicasso.with(imageView.getContext())
                .load(imageRes)
                .transform(new BlurTransformation(imageView.getContext()))
                .placeholder(R.mipmap.bg_square_ing)
                .error(R.mipmap.bg_square_no)
                .into(imageView);
    }

    public static Picasso.Builder setPicassoConfig(Context context)
    {
        Picasso.Builder picasso = new Picasso.Builder(context);
        picasso.memoryCache(new LruCache(2 * 1024 * 1024));
        picasso.defaultBitmapConfig(Bitmap.Config.RGB_565);
        return picasso;
    }
}
