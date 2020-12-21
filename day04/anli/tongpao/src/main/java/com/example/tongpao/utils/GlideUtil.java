package com.example.tongpao.utils;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

public class GlideUtil {
    public static void loadImg(Context context, String url, ImageView img){
        if (!TextUtils.isEmpty(url)&&img!=null){
            Glide.with(context).load(url).into(img);
        }else {
            Toast.makeText(context,"图片加载错误",Toast.LENGTH_LONG).show();
        }
    }

    /**
     * 加载圆形图片
     * @param context
     * @param url
     * @param img
     */
    public static void loadCircleImg(Context context, String url, ImageView img){
        if (!TextUtils.isEmpty(url)&&img!=null){
            Glide.with(context).load(url).apply(RequestOptions.circleCropTransform()).into(img);
        }else {
            Toast.makeText(context,"图片加载错误",Toast.LENGTH_LONG).show();
        }
    }
    public static void loadRoundImg(Context context, String url, ImageView img,int round){
        if (!TextUtils.isEmpty(url)&&img!=null){
            Glide.with(context).load(url).apply(RequestOptions.bitmapTransform(new RoundedCorners(round))).into(img);
        }else {
            Toast.makeText(context,"图片加载错误",Toast.LENGTH_LONG).show();
        }
    }

}
