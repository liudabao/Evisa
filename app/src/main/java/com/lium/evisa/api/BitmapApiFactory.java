package com.lium.evisa.api;

/**
 * Created by Administrator on 2016/5/10.
 */
public class BitmapApiFactory {

    private static volatile BitmapApi bitmapApi;

    private BitmapApiFactory(){

    }

    public static BitmapApi getInstance(){
        if(bitmapApi==null){
            synchronized (BitmapApiFactory.class){
                if(bitmapApi==null){
                    bitmapApi=new BitmapApiImpl();

                }
            }
        }
        return bitmapApi;
    }


}
