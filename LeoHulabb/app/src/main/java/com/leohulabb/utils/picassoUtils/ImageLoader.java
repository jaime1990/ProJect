package com.leohulabb.utils.picassoUtils;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.leohulabb.R;
import com.leohulabb.utils.DataUtils;
import com.leohulabb.utils.ScreenUtils;
import com.leohulabb.utils.picassoUtils.transformation.BlurTransformation;
import com.leohulabb.utils.picassoUtils.transformation.CropCircleTransformation;
import com.leohulabb.utils.picassoUtils.transformation.RoundedCornersTransformation;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.io.File;

public class ImageLoader
{
    private final static ImageLoader PICASSO_IMAGE_LOADER = new ImageLoader();

    public static final int PHOTO_SIZE_PIC = 231;               //长形压缩图
    public static final int PHOTO_SIZE_HOTPIC = 543;            //个人主页模块默认图

    public static ImageLoader getInstance() {
        return PICASSO_IMAGE_LOADER;
    }

    private Bitmap.Config mConfig;

    public ImageLoader() {
        this(Bitmap.Config.RGB_565);
    }

    public ImageLoader(Bitmap.Config config) {
        this.mConfig = config;
    }

    /**
     * 加载文件图片
     * @param imageFile
     * @param imageView
     */
    public void displayImage(File imageFile, ImageView imageView)
    {
        if (null == imageFile) {
            displayEmptyImage(imageView);
            return;
        }

        Picasso.with(imageView.getContext())
                .load(imageFile)
                .config(Bitmap.Config.ARGB_8888)
                .placeholder(R.mipmap.bg_square_ing)
                .error(R.mipmap.bg_square_error)
                .into(imageView);
    }

    /**
     * 加载项目图片
     * @param imageRes
     * @param imageView
     */
    public void displayImage(int imageRes, ImageView imageView)
    {
        Picasso.with(imageView.getContext())
                .load(imageRes)
                .config(Bitmap.Config.ARGB_8888)
                .placeholder(R.mipmap.bg_square_ing)
                .error(R.mipmap.bg_square_error)
                .into(imageView);
    }

    /**
     * 加载网络图片
     * @param imageUrl
     * @param imageView
     * @param transformer
     */
    public void displayImage(String imageUrl, ImageView imageView, Transformation transformer)
    {
        if (!DataUtils.isRightData(imageUrl)) {
            displayEmptyImage(imageView);
            return;
        }

        Picasso.with(imageView.getContext())
                .load(imageUrl)
                .config(mConfig)
                .transform(transformer)
                .placeholder(R.mipmap.bg_square_ing)
                .error(R.mipmap.bg_square_error)
                .into(imageView);
    }

    /**
     * 加载网络图片
     * @param imageUrl
     * @param imageView
     */
    public void displayImage(String imageUrl, ImageView imageView)
    {
        if (!DataUtils.isRightData(imageUrl)) {
            displayEmptyImage(imageView);
            return;
        }

        Picasso.with(imageView.getContext())
                .load(imageUrl)
                .config(mConfig)
                .placeholder(R.mipmap.bg_square_ing)
                .error(R.mipmap.bg_square_error)
                .into(imageView);
    }

    /**
     * 加载网络图片   无默认加载图
     * @param imageUrl
     * @param imageView
     */
    public void displayNoLoadImage(String imageUrl, ImageView imageView)
    {
        if (!DataUtils.isRightData(imageUrl)) {
            displayEmptyImage(imageView);
            return;
        }

        Picasso.with(imageView.getContext())
                .load(imageUrl)
                .config(mConfig)
                .into(imageView);
    }

    /**
     * 加载圆形图片
     * @param imageUrl
     * @param imageView
     */
    public void displayCircleImage(String imageUrl, ImageView imageView)
    {
        if (!DataUtils.isRightData(imageUrl)) {
            displayEmptyImage(imageView);
            return;
        }

        Picasso.with(imageView.getContext())
                .load(imageUrl)
                .config(mConfig)
                .transform(new CropCircleTransformation())
                .placeholder(R.mipmap.bg_square_ing)
                .error(R.mipmap.bg_square_error)
                .into(imageView);
    }

    public void displayCircleImage(int imageRes, ImageView imageView)
    {
        Picasso.with(imageView.getContext())
                .load(imageRes)
                .config(Bitmap.Config.ARGB_8888)
                .transform(new CropCircleTransformation())
                .placeholder(R.mipmap.bg_square_ing)
                .error(R.mipmap.bg_square_error)
                .into(imageView);
    }

    /**
     * 加载圆角图片
     * @param imageUrl
     * @param imageView
     */
    public void displayRoundImage(String imageUrl, ImageView imageView)
    {
        if (!DataUtils.isRightData(imageUrl)) {
            displayEmptyImage(imageView);
            return;
        }

        Picasso.with(imageView.getContext())
                .load(imageUrl)
                .config(mConfig)
                .transform(new RoundedCornersTransformation(ScreenUtils.dp2px(imageView.getContext(), 5), 0))
                .placeholder(R.mipmap.bg_square_ing)
                .error(R.mipmap.bg_square_error)
                .into(imageView);
    }

    /**
     * 加载圆角图片
     * @param imageRes
     * @param imageView
     */
    public void displayRoundImage(int imageRes, ImageView imageView)
    {
        Picasso.with(imageView.getContext())
                .load(imageRes)
                .config(Bitmap.Config.ARGB_8888)
                .transform(new RoundedCornersTransformation(ScreenUtils.dp2px(imageView.getContext(), 10), 0))
                .placeholder(R.mipmap.bg_square_ing)
                .error(R.mipmap.bg_square_error)
                .into(imageView);
    }

    /**
     * 自定义圆角加载
     * @param imageUrl
     * @param imageView
     * @param radio
     * @param margin
     */
    public void displayRoundImage(String imageUrl, ImageView imageView, int radio, int margin)
    {
        if (!DataUtils.isRightData(imageUrl)) {
            displayEmptyImage(imageView);
            return;
        }

        Picasso.with(imageView.getContext())
                .load(imageUrl)
                .config(mConfig)
                .transform(new RoundedCornersTransformation(radio, margin))
                .placeholder(R.mipmap.bg_square_ing)
                .error(R.mipmap.bg_square_error)
                .into(imageView);
    }

    /**
     * 自定义圆角加载
     * @param imageUrl
     * @param imageView
     * @param radio
     */
    public void displayRoundImage(String imageUrl, ImageView imageView, int radio)
    {
        if (!DataUtils.isRightData(imageUrl)) {
            displayEmptyImage(imageView);
            return;
        }

        Picasso.with(imageView.getContext())
                .load(imageUrl)
                .config(mConfig)
                .transform(new RoundedCornersTransformation(radio, 0))
                .placeholder(R.mipmap.bg_square_ing)
                .error(R.mipmap.bg_square_error)
                .into(imageView);
    }

    public void displayRoundImage(int imageRes, ImageView imageView, int radio)
    {
        Picasso.with(imageView.getContext())
                .load(imageRes)
                .config(mConfig)
                .transform(new RoundedCornersTransformation(radio, 0))
                .placeholder(R.mipmap.bg_square_ing)
                .error(R.mipmap.bg_square_error)
                .into(imageView);
    }

    /**
     * 图片模糊效果
     * @param imageUrl
     * @param imageView
     */
    public void picassoBlurLoader(String imageUrl, ImageView imageView)
    {
        if (!DataUtils.isRightData(imageUrl)) {
            displayEmptyImage(imageView);
            return;
        }

        Picasso.with(imageView.getContext())
                .load(imageUrl)
                .config(mConfig)
                .transform(new BlurTransformation(imageView.getContext()))
                .placeholder(R.mipmap.bg_square_ing)
                .error(R.mipmap.bg_square_error)
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
                .transform(new BlurTransformation(imageView.getContext()))
                .placeholder(R.mipmap.bg_square_ing)
                .error(R.mipmap.bg_square_error)
                .into(imageView);
    }

    public void displayEmptyImage(ImageView imageView)
    {
        if (null == imageView)
            return;

        Picasso.with(imageView.getContext())
                .load(R.mipmap.bg_square_no)
                .config(mConfig)
                .into(imageView);
    }

    public void clearMemoryCache()
    {

    }

    public void clearDiskCache()
    {

    }
}