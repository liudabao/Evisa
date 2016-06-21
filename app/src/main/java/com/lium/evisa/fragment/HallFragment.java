package com.lium.evisa.fragment;


import java.util.ArrayList;
import java.util.List;

import com.lium.evisa.R;
import com.lium.evisa.activity.CountryActivity;
import com.lium.evisa.adapter.MyAdapter;
import com.lium.evisa.api.InternetApi;
import com.lium.evisa.api.InternetApiFactory;
import com.lium.evisa.api.ParserApi;
import com.lium.evisa.api.ParserApiFactory;
import com.lium.evisa.entity.Country;
import com.lium.evisa.entity.EventMessage;
import com.lium.evisa.entity.Version;
import com.lium.evisa.entity.VersionEvent;
import com.lium.evisa.global.GlobalContext;
import com.lium.evisa.global.GlobalValue;
import com.lium.evisa.service.DownloadService;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import de.greenrobot.event.EventBus;


public class HallFragment extends Fragment {

    private TextView tv;
    private View view;

    private RecyclerView recyclerView;
    private List<Country> listCountry;
    //private RecyclerViewAdapter recyclerViewAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;
   // private ScrollRecyclerView scrollRecyclerView;
    private LinearLayoutManager linearLayoutManager;
    private MyAdapter adapter;
    private int lastVisibleItem;

    private Handler handler;
    private  Version mVersion;
    private InternetApi mInternetApi;

    private EventMessage eventMessage;
    private ProgressBar mProgressBar;
    private FrameLayout mFrameLayout;
    private LinearLayout mLinearLayout;
    private  boolean flag=true;
    private  boolean checkUpdate=true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	super.onCreateView(inflater, container, savedInstanceState);
        view=inflater.inflate(R.layout.fragment_hall,container,false);
        mInternetApi= InternetApiFactory.getInstance();

        showProgressBar();
        initData();
        //EventBus.getDefault().register(this);
        handler=new Handler() {
            @Override
            public void handleMessage(Message msg){

                int type=(int)msg.obj;
                switch (type){
                    case 1:

                        adapter.notifyDataSetChanged();
                        swipeRefreshLayout.setRefreshing(false);
                        break;
                    case 2:
                        adapter.footer_type=GlobalValue.END;
                        adapter.notifyDataSetChanged();
                        flag=true;
                        break;
                    case 3:
                        hideProgressBar();
                        initView();
                        if (checkUpdate){
                            checkVersion();
                            checkUpdate=false;
                        }
                        adapter.notifyDataSetChanged();
                        break;

                }
            }

        };

        return view;
    }

    private void  initData(){
        listCountry=new ArrayList<Country>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                eventMessage=mInternetApi.getHandle(GlobalValue.COUNTRY_URL);
                ParserApi parserApi= ParserApiFactory.getInstance();
                if(eventMessage.isResult()){
                    Log.e("xml",eventMessage.getContent());
                    listCountry=parserApi.parserXml(eventMessage.getContent());
                  //  Log.e("TAG",listCountry.size()+"");
                }
                Message msg = new Message();
                msg.obj =3;
                handler.sendMessage(msg);
            }
        }).start();

       //

    }

    private void loadMore(){
        for (int i = 0; i <5; i++) {
            Country country=new Country();
            country.setName("泰国"+i);
            country.setIntroduce("泰国是东南亚的一个国家，人口。。。。。");
            country.setPeriod(250);
            country.setPrice(50);
            country.setImaUrl("http://101.200.164.87:8080/visa/image/country/turky.jpg");
           // country.setImageId(R.drawable.ic_launcher);
            listCountry.add(country);
            //recyclerViewAdapter.addItem(country,recyclerViewAdapter.getItemCount());
        }
    }

    private void  refreashData(){
        listCountry.clear();
        eventMessage=mInternetApi.getHandle(GlobalValue.COUNTRY_URL);
        ParserApi parserApi= ParserApiFactory.getInstance();
       // Log.e("xml",eventMessage.isResult()+"");
        if(eventMessage.isResult()){
           // Log.e("xml",eventMessage.getContent());
            List<Country>temp =parserApi.parserXml(eventMessage.getContent());
            for(Country c:temp){
                listCountry.add(c);
            }

        }

    }

    private void initView(){
    	tv=(TextView) view.findViewById(R.id.tv_hall_top);
        swipeRefreshLayout=(SwipeRefreshLayout)view.findViewById(R.id.srl_home);
        recyclerView=(RecyclerView)view.findViewById(R.id.rv_country);
        linearLayoutManager=new LinearLayoutManager(GlobalContext.getContext());
        adapter=new MyAdapter(GlobalContext.getContext(), listCountry);


        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        refreashData();
                       // initData();
                        Message msg = new Message();
                        msg.obj = 1;
                        handler.sendMessage(msg);
                    }
                }).start();
            }
        });

        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView,
                                             int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                //Log.e("FLAG",""+flag+"*"+adapter.getItemCount()+"*"+lastVisibleItem);
                if (newState == RecyclerView.SCROLL_STATE_IDLE
                        && lastVisibleItem + 1 == adapter.getItemCount()&&flag==true) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            flag=false;
                            //Message msg = new Message();
                            //msg.obj = 2;
                           // handler.sendMessage(msg);
                            try {
                                Thread.sleep(3000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            //loadMore();
                            Message msg= new Message();
                            msg.obj= 2;
                            handler.sendMessage(msg);
                            //Log.e("View","load more");
                        }
                    }).start();
                }
                else if(flag==true){
                    adapter.footer_type=GlobalValue.SUCCESS;
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();

            }
        });

        adapter.setOnItemClickLitener(new MyAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //Toast.makeText(getActivity(), "Click: " + position, Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getActivity(), CountryActivity.class);
                Bundle bundle=new Bundle();
                bundle.putSerializable(GlobalValue.COUNTRY_KEY,listCountry.get(position-1));
                intent.putExtras(bundle);
                startActivity(intent);

            }

            @Override
            public void onItemLongClick(View view, int position) {
               // Toast.makeText(getActivity(), "Click: " + position, Toast.LENGTH_SHORT).show();
            }
        });

    }



    private void checkVersion(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                eventMessage=mInternetApi.getHandle(GlobalValue.VERSION_URL);
                EventBus.getDefault().post(new VersionEvent(eventMessage));
               /* Log.e("version",eventMessage.getContent());
                ParserApi parserApi= ParserApiFactory.getInstance();
                if(eventMessage.isResult()){
                    mVersion=parserApi.parserJson(eventMessage.getContent());
                    Log.e("version",mVersion.getValue()+"*"+mVersion.getInfo()+"*"+mVersion.getUrl()+"*"+getVersionName());
                    if(mVersion.getValue().compareTo(getVersionName())!=0){
                        Log.e("update","show");
                        showUpdateDialog();
                    }
                }*/

            }
        }).start();
    }

    private void showProgressBar(){
        mFrameLayout=(FrameLayout) view.findViewById(R.id.frame_home);
        FrameLayout.LayoutParams lParams=new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        mLinearLayout=(LinearLayout)view.findViewById(R.id.ly_hall);
        mLinearLayout.setVisibility(View.GONE);
        lParams.gravity= Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL;
        mProgressBar=new ProgressBar(GlobalContext.getContext());
        mProgressBar.setVisibility(View.VISIBLE);
        mProgressBar.setLayoutParams(lParams);
        mFrameLayout.addView(mProgressBar);
    }

    private void hideProgressBar(){
        mProgressBar.setVisibility(View.INVISIBLE);
        mLinearLayout.setVisibility(View.VISIBLE);
    }




   /* public void onEventMainThread(VersionEvent event) {
        EventMessage msg=event.getMessage();
        Gson gson=new Gson();
        Version version=gson.fromJson(msg.getContent(),Version.class);
        Log.e("version",version.getUrl());
        Log.d("harvic", msg.getContent());

    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        EventBus.getDefault().unregister(this);//反注册EventBus
    }*/
}
