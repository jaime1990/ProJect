package com.commonui.imageloader;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.commonui.R;
import com.commonutils.StringUtils;

/**
 * Description : 图片加载工具类 使用glide框架封装
 */
public class ImageLoaderUtils
{
    public static final String ANDROID_RESOURCE = "android.resource://";
    public static final int IMAGE_LOADING = R.drawable.bg_square_ing;   //loading占位图
    public static final int IMAGE_ERROR = R.drawable.bg_square_error;  //error图

    // 将资源ID转为Uri
    public static Uri resourceIdToUri(Context mContext, int resourceId) {
        return Uri.parse(ANDROID_RESOURCE + mContext.getPackageName() + "/" + resourceId);
    }

    // 不使用占位图
    public static void display(String url, ImageView imageView) {

        if (!checkImage(url, imageView))
            return;

        Glide.with(imageView.getContext())
                .load(url)
                .crossFade()
                .into(imageView);
    }

    // 加载网络图片
    public static void displayByUrlOnlyWifi(String url, ImageView imageView) {

        if (!checkImage(url, imageView))
            return;

        Glide.with(imageView.getContext())
                .load(url)
                .placeholder(IMAGE_LOADING)
                .error(IMAGE_ERROR)
                .crossFade()   //淡入淡出
                .into(imageView);
    }

    // 加载网络图片
    public static void displayByUrl(String url, ImageView imageView) {

        if (!checkImage(url, imageView))
            return;

        Glide.with(imageView.getContext())
                .load(url)
                .placeholder(IMAGE_LOADING)
                .error(IMAGE_ERROR)
                .crossFade()   //淡入淡出
                .into(imageView);
    }

    // 加载drawable图片
    public static void displayByRes(int resId, ImageView imageView) {

        if (imageView == null)
            return;

        Glide.with(imageView.getContext())
                .load(resourceIdToUri(imageView.getContext(), resId))
                .placeholder(IMAGE_LOADING)
                .error(IMAGE_ERROR)
                .crossFade()
                .into(imageView);
    }

    // 加载本地图片
    public static void displayByLocal(String url, ImageView imageView) {

        if (!checkImage(url, imageView))
            return;

        Glide.with(imageView.getContext())
                .load("file://" + url)
                .placeholder(IMAGE_LOADING)
                .error(IMAGE_ERROR)
                .crossFade()
                .into(imageView);
    }

    public static void displaySmallPhoto(String url, ImageView imageView) {

        if (!checkImage(url, imageView))
            return;

        Glide.with(imageView.getContext()).load(url).asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.bg_square_ing)
                .error(R.drawable.bg_square_error)
                .thumbnail(0.5f)
                .into(imageView);
    }

    public static void displayBigPhoto(String url, ImageView imageView) {

        if (!checkImage(url, imageView))
            return;

        Glide.with(imageView.getContext()).load(url).asBitmap()
                .format(DecodeFormat.PREFER_ARGB_8888)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.bg_square_ing)
                .error(R.drawable.bg_square_error)
                .into(imageView);
    }

    // 检查图片加载  true条件符合，false条件不符合
    private static boolean checkImage(String url, ImageView imageView)
    {
        if (imageView == null || StringUtils.isSpace(url)) {
            return false;
        }

        return true;
    }
}
