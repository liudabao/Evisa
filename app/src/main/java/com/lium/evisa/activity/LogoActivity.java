package com.lium.evisa.activity;

import com.lium.evisa.R;
import com.lium.evisa.api.DisplayImageApi;
import com.lium.evisa.api.DisplayImageApiFactory;
import com.lium.evisa.global.GlobalContext;
import com.lium.evisa.global.GlobalValue;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import com.igexin.sdk.PushManager;
/**
 * 
 * @author liumin
 * 
 */

public class LogoActivity extends Activity{

	private ImageView iv_ad;
	private ImageView iv_logo;
	private DisplayImageApi displayImageApi;
	private SharedPreferences preferences;
	private Button bt_ad;
	private int times;
	//private TimeCount timeCount;
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_logo);
		initView();
		PushManager.getInstance().initialize(this.getApplicationContext());
	}
	

	private void initView(){
		iv_ad=(ImageView)findViewById(R.id.iv_ad);
		//bt_ad=(Button)findViewById(R.id.bt_ad);
		//iv_logo=(ImageView)findViewById(R.id.iv_logo);
		//timeCount = new TimeCount(GlobalValue.DELAYTIME, 1000);


		preferences=getSharedPreferences("visit", MODE_PRIVATE);
		times=preferences.getInt("count", 0);

		displayImageApi=DisplayImageApiFactory.getInstance();
		//displayImageApi.display(iv_logo, GlobalValue.LOGOURL,R.drawable.ic_launcher);
		displayImageApi.display(iv_ad, GlobalValue.ADURL,R.drawable.ic_launcher);

		onScaleWidth(iv_ad);
		new Handler().postDelayed(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub

				if(times==0){
					enter();
				}
				else{
					Intent intent=new Intent(LogoActivity.this,HomeActivity.class);
					startActivity(intent);
				}
				finish();
			}
		}, GlobalValue.DELAYTIME);


	}

	private void onScaleWidth(final View view) {
		Animator  animator=AnimatorInflater.loadAnimator(GlobalContext.getContext(), R.animator.ad_scale);
		view.setPivotX(0);
		view.setPivotY(0);
		view.invalidate();
		animator.setTarget(view);
		animator.start();
	}
	private void  enter(){
		Intent intent=new Intent(LogoActivity.this,GuideActivity.class);
		startActivity(intent);
		SharedPreferences.Editor editor=getSharedPreferences("visit", MODE_PRIVATE).edit();
		editor.putInt("count", 1);
		editor.commit();
	}

		
}
