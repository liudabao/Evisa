package com.lium.evisa.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.WindowManager;
import android.widget.RemoteViews;

import com.lium.evisa.R;
import com.lium.evisa.global.GlobalContext;
import com.lium.evisa.global.GlobalValue;
import com.lium.evisa.util.DownAnsycTask;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Administrator on 2016/4/21.
 */
public class DownloadService extends Service {

    private NotificationManager mNotificationManager;
    private Notification.Builder mBuilder;
    private Notification mNotification;
    private int percent=0;
    private int max=0;
    private  String url;
    ProgressDialog progress;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate(){
        super.onCreate();
        Log.e("MyService", "onCreate executed");

       // showNotification();
    }

    @Override
    public  int onStartCommand(Intent intent,int flag,int startId){
        File file=new File(Environment.getExternalStorageDirectory().getAbsolutePath(),"test.apk");
        showProgressDialog();
        //showNotification();
        new DownAnsycTask(GlobalValue.url, 2, file, progress).execute();
        return  super.onStartCommand(intent, flag, startId);
    }

    private void showProgressDialog(){

        progress=new ProgressDialog(GlobalContext.getContext());
        progress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);

        progress.setIcon(R.drawable.ic_launcher);
        progress.setTitle("下载");
        progress.setMessage("版本更新");
        progress.setCancelable(true);
        progress.setMax(100);
        progress.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        progress.show();


    }

    private void showNotification(){
        mNotificationManager=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        mNotification=new Notification(R.drawable.ic_launcher,"test",System.currentTimeMillis());
        RemoteViews mRemoteViews=new RemoteViews(getPackageName(),R.layout.ui_notification);
        mRemoteViews.setImageViewResource(R.id.icon, R.drawable.ic_launcher);
        mRemoteViews.setProgressBar(R.id.notification_progress,100, 0, false);
        mRemoteViews.setTextViewText(R.id.notification_text,""+percent+"%" );
        //notification=builder.build();
        mNotification.contentView=mRemoteViews;
        mNotificationManager.notify(1, mNotification);
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
    }

    private void httpUtil(final String str){

        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection=null;
                FileOutputStream out;
                try {
                    URL url=new URL(str);
                    connection=(HttpURLConnection)url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(3000);
                    connection.setDoInput(true);
                    connection.setDoOutput(true);
                    max=connection.getContentLength();
                    if(connection.getResponseCode()==200){
                        Log.e("service","connect");
                        InputStream in=connection.getInputStream();
                        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
                            File file=new File(Environment.getExternalStorageDirectory().getAbsolutePath(),"test.apk");
                            if(!file.exists()){
                                file.createNewFile();
                                Log.e("FILE",""+file);
                            }
                            out=new FileOutputStream(file);
                            Log.e("service","sdcard exist");
                        }
                        else {
                            out=openFileOutput("test.apk",Context.MODE_PRIVATE);
                        }

                        // BufferedReader reader=new BufferedReader(new InputStreamReader(in));
                        byte[] bytes=new byte[1024];
                        int len=0;
                        int count=0;
                        while ((len=in.read(bytes))!=-1){
                            out.write(bytes, 0, len);
                            count+=len;
                            int process=(count*100)/max;
                            Log.e("FILE",""+process+"%: "+count);

                        }
                        in.close();
                        out.close();
                    }
                    else {
                        Log.e("service","connet failed");
                    }

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {

                    connection.disconnect();
                }

            }
        }).start();


    }

}
