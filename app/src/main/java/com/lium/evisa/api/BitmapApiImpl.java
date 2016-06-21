package com.lium.evisa.api;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by Administrator on 2016/5/10.
 */
public class BitmapApiImpl implements BitmapApi {
    @Override
    public int caculateSize( BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int height=options.outHeight;
        final int width=options.outWidth;
        int sampleSize=1;
        if(height>reqHeight || width> reqWidth){

            while((height/sampleSize)>reqHeight && (width/sampleSize)> reqWidth){
                sampleSize*=2;
            }

        }
        return sampleSize;
    }

    @Override
    public Bitmap compressBitmap(Resources res, int resId, int reqWidth, int reqHeight) {
        BitmapFactory.Options options=new BitmapFactory.Options();
        options.inJustDecodeBounds=true;
        BitmapFactory.decodeResource(res,resId,options);
        options.inSampleSize=caculateSize(options,reqWidth,reqHeight);
        options.inJustDecodeBounds=false;
        return BitmapFactory.decodeResource(res,resId,options);
    }
}
