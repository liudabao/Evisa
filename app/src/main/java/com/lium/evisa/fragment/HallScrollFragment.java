package com.lium.evisa.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lium.evisa.R;
import com.lium.evisa.adapter.RecyclerViewAdapter;
import com.lium.evisa.entity.Country;
import com.lium.evisa.global.GlobalContext;
import com.lium.evisa.ui.ScrollRecyclerView;

import java.util.ArrayList;
import java.util.List;


public class HallScrollFragment extends Fragment {
    private TextView tv;
    private View view;

    private RecyclerView recyclerView;
    private List<Country> listCountry;
    private RecyclerViewAdapter recyclerViewAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ScrollRecyclerView scrollRecyclerView;
    private LinearLayoutManager linearLayoutManager;

    private Handler handler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	super.onCreateView(inflater, container, savedInstanceState);
        view=inflater.inflate(R.layout.fragment_hall,container,false);
        initData();
        initView();
        handler=new Handler() {
            @Override
            public void handleMessage(Message msg){

                if(msg.obj==1){
                    recyclerViewAdapter.notifyDataSetChanged();
                    recyclerView.setAdapter(recyclerViewAdapter);
                    swipeRefreshLayout.setRefreshing(false);
                }
            }

        };
        return view;
    }

    private void  initData(){
        listCountry=new ArrayList<Country>();
        Country country=new Country();
        country.setName("柬埔寨");
        country.setIntroduce("柬埔寨是东南亚的一个国家，人口。。。。。");
        country.setPeriod(90);
        country.setPrice(180);
        country.setImageId(R.drawable.ic_launcher);
        listCountry.add(country);
        listCountry.add(country);
        listCountry.add(country);
        listCountry.add(country);
        listCountry.add(country);
        listCountry.add(country);
        listCountry.add(country);

    }

    private void initView(){
    	tv=(TextView) view.findViewById(R.id.tv_hall_top);
        swipeRefreshLayout=(SwipeRefreshLayout)view.findViewById(R.id.srl_home);
        scrollRecyclerView=(ScrollRecyclerView)view.findViewById(R.id.srv_country);
        linearLayoutManager=new LinearLayoutManager(GlobalContext.getContext());
        recyclerView=scrollRecyclerView.getRecycleView();
        recyclerViewAdapter=new RecyclerViewAdapter(GlobalContext.getContext(), listCountry);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(recyclerViewAdapter);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        refreashData();
                        Message msg = new Message();
                        msg.obj = 1;
                        handler.sendMessage(msg);
                    }
                }).start();
            }
        });

    }

    private void loadMore(){
        for (int i = 0; i <5; i++) {
            Country country=new Country();
            country.setName("泰国");
            country.setIntroduce("泰国是东南亚的一个国家，人口。。。。。");
            country.setPeriod(250);
            country.setPrice(50);
            country.setImageId(R.drawable.ic_launcher);
            //recyclerViewAdapter.addItem(country,recyclerViewAdapter.getItemCount());
        }
    }

    private void  refreashData(){
        listCountry.clear();
        Country country=new Country();
        country.setName("缅甸");
        country.setIntroduce("缅甸是东南亚的一个国家，人口。。。。。");
        country.setPeriod(180);
        country.setPrice(90);
        country.setImageId(R.drawable.ic_launcher);
        listCountry.add(country);
        listCountry.add(country);
        listCountry.add(country);
        listCountry.add(country);
        listCountry.add(country);
        listCountry.add(country);
        listCountry.add(country);

    }
}
