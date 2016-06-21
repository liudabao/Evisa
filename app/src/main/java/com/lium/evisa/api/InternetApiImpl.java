package com.lium.evisa.api;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.lium.evisa.entity.EventMessage;
import com.lium.evisa.global.GlobalContext;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;


import java.io.IOException;
import java.util.Map;

import de.greenrobot.event.EventBus;

/**
 * Created by lenovo on 2016/4/15.
 */
public class InternetApiImpl implements  InternetApi {

    private final OkHttpClient client = new OkHttpClient();

    @Override
    public EventMessage getHandle(String url) {

        EventMessage msg=new EventMessage();
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
            if(response.isSuccessful()) {
                msg.setResult(true);
                msg.setContent(response.body().string());
               // msg.setIn(response.body().byteStream());
               // Log.e("TAG",msg.getContent());
            }
            else{
                Log.e("TAG","false");
                msg.setResult(false);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return msg;

    }



    @Override
    public  void  getXmlHandle(String url){

      /*  RequestQueue mQueue= Volley.newRequestQueue(GlobalContext.getContext());
        StringRequest mRequest=new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Log.e("Volley Success",s.length()+""+s.toString());
                EventMessage msg=new EventMessage();
                msg.setResult(true);
                msg.setContent(s);

                ParserApi parserApi=ParserApiFactory.getInstance();
                parserApi.parserXml(s);
               // EventBus.getDefault().post(new VersionEvent(msg));

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

                Log.e("VOLLEY ERROR",volleyError.getMessage());
            }
        });
        mRequest.setTag(GlobalValue.INTERNET_TAG);
        mQueue.add(mRequest);*/
    }


    @Override
    public EventMessage postHandle(String url, Map<String, String> params) {

        return  null;
    }

    @Override
    public boolean isNetConnectted() {

        ConnectivityManager connectivityManager=(ConnectivityManager)GlobalContext.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mNetwortInfo=connectivityManager.getActiveNetworkInfo();
        if(mNetwortInfo!=null){
            return  mNetwortInfo.isAvailable();
        }
        return false;
    }
}
