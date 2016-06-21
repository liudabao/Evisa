package com.lium.evisa.util;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.RemoteViews;

import com.lium.evisa.R;
import com.lium.evisa.global.GlobalContext;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Administrator on 2016/6/2.
 */
public class DownAnsycTask extends AsyncTask<Void, Integer, Boolean> {

    private String downloadUrl;// 下载链接地址
    private int threadNum;// 开启的线程数
    private File filePath;// 保存文件路径地址
    private int blockSize;// 每一个线程的下载量
    private ProgressDialog dialog;
    private int fileSize;
    private int temp=0;
   // private Notification notification;
    //private NotificationManager mNotificationManager;;

    public DownAnsycTask(String downloadUrl, int threadNum, File fileptah, ProgressDialog dialog) {
        this.downloadUrl = downloadUrl;
        this.threadNum = threadNum;
        this.filePath = fileptah;
        this.dialog=dialog;
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        DownThread[] threads = new DownThread[threadNum];
        try{
            URL url = new URL(downloadUrl);
            Log.e("URL", "download file http path:" + downloadUrl);
            HttpURLConnection conn =(HttpURLConnection) url.openConnection();
            // 读取下载文件总大小
            fileSize = conn.getContentLength();
            if (fileSize <= 0) {
                return false;
            }
            blockSize = (fileSize % threadNum) == 0 ? fileSize / threadNum
                    : fileSize / threadNum + 1;

            Log.e("URL", "fileSize:" + fileSize + "  blockSize:"+blockSize);

            //File file = new File(filePath);
            for (int i = 0; i < threads.length; i++) {
                // 启动线程，分别下载每个线程需要下载的部分
                threads[i] = new DownThread(url, filePath, blockSize,
                        (i + 1));
                threads[i].setName("Thread:" + i);
                threads[i].start();
            }

            boolean isfinished = false;
            int downloadedAllSize = 0;
            while (!isfinished) {
                isfinished = true;
                // 当前所有线程下载总量
                downloadedAllSize = 0;
                for (int i = 0; i < threads.length; i++) {
                    downloadedAllSize += threads[i].getDownloadLength();

                    if (!threads[i].isCompleted()) {
                        isfinished = false;
                    }

                }
                publishProgress(downloadedAllSize);
            }
        }catch (IOException e){

        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    protected void onProgressUpdate(Integer... values){

        int percent=values[0]*100/fileSize;

        if(percent!=0&&percent>temp){
            Log.e("percent", values[0]+"   *"+percent);
            dialog.setProgress(percent);
            temp=percent;

        }


    }

    @Override
    protected void onPostExecute(Boolean result){
        Log.e("percent", "finish");
        dialog.dismiss();
        install(filePath);
        send();
    }

    private void install(File file){
        if(!file.exists()){
            Log.e("apk","not exist");
            return;
        }
        Intent intent=new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setDataAndType(Uri.parse("file://"+file.toString()),"application/vnd.android.package-archive");
        GlobalContext.getContext().startActivity(intent);
    }

    private void send(){
        Intent intent=new Intent("com.service.stop");
        GlobalContext.getContext().sendBroadcast(intent);
    }

}