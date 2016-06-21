package com.lium.evisa.fragment;

import com.lium.evisa.R;
import com.lium.evisa.activity.HomeActivity;
import com.lium.evisa.api.BitmapApi;
import com.lium.evisa.api.BitmapApiFactory;
import com.lium.evisa.api.DisplayImageApi;
import com.lium.evisa.api.DisplayImageApiFactory;
import com.lium.evisa.global.GlobalContext;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

public class EnterFragment extends Fragment {

	private ImageView iv_enter;
	private View view;
	private Button bt_enter;
	//private BitmapApi bitmapApi;
	@Override	
	public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState){
		super.onCreateView(inflater, container, savedInstanceState);
		view=inflater.inflate(R.layout.fragment_enter, container, false);
		initView();
		return view;
	}
	
	private void initView(){
		iv_enter=(ImageView)view.findViewById(R.id.iv_enter);
		bt_enter=(Button)view.findViewById(R.id.bt_enter);
		//iv_enter.setBackgroundResource(R.drawable.guide2);
		//bitmapApi= BitmapApiFactory.getInstance();
		//ViewGroup.LayoutParams params=iv_first.getLayoutParams();
		//iv_enter.setImageBitmap(bitmapApi.compressBitmap(GlobalContext.getContext().getResources(), R.drawable.guide2, 240, 320));
		DisplayImageApi displayImageApi= DisplayImageApiFactory.getInstance();;
		displayImageApi.display(iv_enter, R.drawable.guide2 );
		bt_enter.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(getActivity(),HomeActivity.class);
				startActivity(intent);
				getActivity().finish();
			}
		});
	}

}
