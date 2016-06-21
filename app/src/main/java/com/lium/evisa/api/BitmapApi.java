package com.lium.evisa.api;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.bumptech.glide.load.engine.Resource;

/**
 * Created by Administrator on 2016/5/10.
 */
public interface BitmapApi {
    public int caculateSize( BitmapFactory.Options options, int reqWidth, int reqHeight);

    public Bitmap compressBitmap(Resources res, int resId, int reqWidth, int reqHeight);
}
