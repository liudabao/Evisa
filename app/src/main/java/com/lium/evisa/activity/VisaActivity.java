package com.lium.evisa.activity;

import com.lium.evisa.R;
import com.lium.evisa.global.GlobalValue;
import com.lium.evisa.ui.MyProgressWebView;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.FrameLayout.LayoutParams;

public class VisaActivity extends Activity{
	
	private WebView webView;
	private LinearLayout linearLayout;
	private String url;
	private ImageButton backImage;
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_visa);
		initData();
		initView();
	}

	private void initData(){
		url=getIntent().getStringExtra(GlobalValue.URL_KEY);
	}

	
	private void initView(){
		backImage=(ImageButton)findViewById(R.id.iv_visa_back);
		backImage.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		webView=new MyProgressWebView(getApplicationContext());
		webView.getSettings().setJavaScriptEnabled(true);
		webView.loadUrl(url);
		//webView.loadUrl("https://www.baidu.com");
		linearLayout=(LinearLayout)findViewById(R.id.ll_visa_web);
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		linearLayout.addView(webView,params);
	}

}
