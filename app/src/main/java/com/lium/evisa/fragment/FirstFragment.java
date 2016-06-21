package com.lium.evisa.fragment;

import com.lium.evisa.R;
import com.lium.evisa.api.BitmapApi;
import com.lium.evisa.api.BitmapApiFactory;
import com.lium.evisa.api.DisplayImageApi;
import com.lium.evisa.api.DisplayImageApiFactory;
import com.lium.evisa.global.GlobalContext;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class FirstFragment  extends Fragment {

	
	private ImageView iv_first;
	private View view;
	//private BitmapApi bitmapApi;
	
	@Override	
	public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState){
		super.onCreateView(inflater, container, savedInstanceState);
		view=inflater.inflate(R.layout.fragment_first, container, false);
		initView();
		return view;
	}
	
	private void initView(){
		iv_first=(ImageView)view.findViewById(R.id.iv_first);
		//bitmapApi= BitmapApiFactory.getInstance();
		//ViewGroup.LayoutParams params=iv_first.getLayoutParams();
		//iv_first.setImageBitmap(bitmapApi.compressBitmap(GlobalContext.getContext().getResources(), R.drawable.guide1, 240, 320));
		DisplayImageApi displayImageApi= DisplayImageApiFactory.getInstance();;
		displayImageApi.display(iv_first, R.drawable.guide1 );
	}
}
