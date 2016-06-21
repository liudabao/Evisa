package com.lium.evisa.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.lium.evisa.service.DownloadService;

/**
 * Created by Administrator on 2016/6/2.
 */
public class DownloadReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        Log.e("receiver","i get message");
    }
}
