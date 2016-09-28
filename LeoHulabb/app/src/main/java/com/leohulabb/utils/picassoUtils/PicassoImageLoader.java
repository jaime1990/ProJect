package com.leohulabb.utils.picassoUtils;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.leohulabb.R;
import com.leohulabb.utils.ScreenUtils;
import com.leohulabb.utils.picassoUtils.transformation.BlurTransformation;
import com.leohulabb.utils.picassoUtils.transformation.CropCircleTransformation;
import com.leohulabb.utils.picassoUtils.transformation.RoundedCornersTransformation;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

public class PicassoImageLoader
{
    private static PicassoImageLoader instance ;

    public static PicassoImageLoader getImageLoader() {
        if (instance == null) {
            synchronized (PicassoUtils.class) {
                if (instance == null) {
                    instance = new PicassoImageLoader();
                }
            }
        }
        return instance;
    }

    private Bitmap.Config mConfig;

    public PicassoImageLoader() {
        this(Bitmap.Config.RGB_565);
    }

    public PicassoImageLoader(Bitmap.Config config) {
        this.mConfig = config;
    }

    /**
     * 加载项目图片
     * @param imageRes
     * @param imageView
     */
    public void picassoLoader(int imageRes, ImageView imageView)
    {
        Picasso.with(imageView.getContext())
                .load(imageRes)
                .config(Bitmap.Config.ARGB_8888)
                .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
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
        Picasso.with(imageView.getContext())
                .load(imageUrl)
                .config(mConfig)
                .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
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
        Picasso.with(imageView.getContext())
                .load(imageUrl)
                .config(mConfig)
                .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
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
        Picasso.with(imageView.getContext())
                .load(imageUrl)
                .config(mConfig)
                .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                .transform(new CropCircleTransformation())
                .placeholder(R.mipmap.bg_square_ing)
                .error(R.mipmap.bg_square_no)
                .into(imageView);
    }

    public void picassoCircleLoader(int imageRes, ImageView imageView)
    {
        Picasso.with(imageView.getContext())
                .load(imageRes)
                .config(Bitmap.Config.ARGB_8888)
                .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
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
        Picasso.with(imageView.getContext())
                .load(imageUrl)
                .config(mConfig)
                .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
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
        Picasso.with(imageView.getContext())
                .load(imageRes)
                .config(Bitmap.Config.ARGB_8888)
                .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
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
        Picasso.with(imageView.getContext())
                .load(imageUrl)
                .config(mConfig)
                .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
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
        Picasso.with(imageView.getContext())
                .load(imageUrl)
                .config(mConfig)
                .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
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
        Picasso.with(imageView.getContext())
                .load(imageRes)
                .config(Bitmap.Config.ARGB_8888)
                .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                .transform(new BlurTransformation(imageView.getContext()))
                .placeholder(R.mipmap.bg_square_ing)
                .error(R.mipmap.bg_square_no)
                .into(imageView);
    }
}