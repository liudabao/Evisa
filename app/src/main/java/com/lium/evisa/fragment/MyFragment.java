package com.lium.evisa.fragment;

import com.lium.evisa.R;
import com.lium.evisa.activity.LoginActivity;
import com.lium.evisa.activity.SetActivity;
import com.lium.evisa.api.DisplayImageApi;
import com.lium.evisa.api.DisplayImageApiFactory;
import com.lium.evisa.entity.User;
import com.lium.evisa.global.GlobalContext;
import com.lium.evisa.global.GlobalValue;
import com.lium.evisa.ui.CircleView;
import com.lium.evisa.util.DbHelper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
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
    private CircleView image;
    private  DisplayImageApi displayImageApi;
    private DbHelper helper;
    Boolean isLogin=false;
    private LinearLayout ll_login;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	super.onCreateView(inflater, container, savedInstanceState);
        view=inflater.inflate(R.layout.fragment_my,container,false);
        initView();
        return view;
    }
    
    private void  initView(){
        displayImageApi= DisplayImageApiFactory.getInstance();
    	tv=(TextView)view.findViewById(R.id.tv_login);;
        image=(CircleView)view.findViewById(R.id.iv_login) ;
        ll_set=(LinearLayout)view.findViewById(R.id.ll_set);
        ll_login=(LinearLayout)view.findViewById(R.id.ll_login);
    	ll_login.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
                if(!isLogin){
                    Intent intent=new Intent(getActivity(),LoginActivity.class);
                    startActivityForResult(intent, 1);
                }

			}
		});

        ll_set.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), SetActivity.class);
                startActivityForResult(intent, 2);
            }
        });
        if(isLogin()){
            Log.e("login","true");
            isLogin=true;
            User user=helper.query(GlobalValue.TABLE);
            tv.setText(user.getId());
            displayImageApi.display(image, user.getImageUrl());
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                if (resultCode == Activity.RESULT_OK) {
                    Bundle bundle=data.getExtras();
                    tv.setText(bundle.getString("name"));
                    displayImageApi.display(image, bundle.getString("imageUrl"));
                    isLogin=true;
                    return;
                }
                break;
            case 2:
                if (resultCode == Activity.RESULT_OK) {
                    Bundle bundle=data.getExtras();
                    displayImageApi.display(image, R.drawable.bg_image_login);
                    tv.setText("立即登录");
                    isLogin=false;
                    return;
                }
                break;
            default:
                break;
        }

    }

    private boolean isLogin(){
        helper=new DbHelper(GlobalContext.getContext(), GlobalValue.DB, 1);
        return helper.isExist(GlobalValue.TABLE);
    }




}
