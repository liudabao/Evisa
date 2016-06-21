package com.lium.evisa.fragment;

import com.lium.evisa.R;
import com.lium.evisa.activity.LoginActivity;
import com.lium.evisa.activity.SetActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


public class MyFragment extends Fragment {
    private TextView tv;
    private View view;
    private LinearLayout ll_set;
     
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	super.onCreateView(inflater, container, savedInstanceState);
        view=inflater.inflate(R.layout.fragment_my,container,false);
        initView();
        return view;
    }
    
    private void  initView(){
    	tv=(TextView)view.findViewById(R.id.tv_login);
        ll_set=(LinearLayout)view.findViewById(R.id.ll_set);
    	tv.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(getActivity(),LoginActivity.class);
				startActivityForResult(intent, 0);
			}
		});

        ll_set.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), SetActivity.class);
                startActivity(intent);
            }
        });
    }

}
