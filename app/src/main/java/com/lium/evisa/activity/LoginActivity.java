package com.lium.evisa.activity;

import com.lium.evisa.R;
import com.lium.evisa.global.GlobalContext;
import com.lium.evisa.global.GlobalValue;
import com.lium.evisa.util.DbHelper;
import com.lium.evisa.util.DialogUtil;

import android.app.Activity;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

public class LoginActivity extends Activity{

	private TextView tv_register;
	private TextView tv_find;
	private EditText name;
	private EditText psd;
	private Button bt_login;
	private Button bt_code;
	private ImageButton backImage;
	private Dialog dialog;
	private EventHandler eh;
	private TimeCount time;


	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);
        initView();
		//SMSSDK.initSDK(this, "142ffc51bc7be", "ca1b80c5a359dc66dbfa0629108048e2");
		SMSSDK.initSDK(this, "1488ed0f38d80", "b07247c27cd9fe697c4c56a659b0804a");
		eh=new EventHandler(){

			@Override
			public void afterEvent(int event, int result, Object data) {

				if (result == SMSSDK.RESULT_COMPLETE) {
					//回调完成
					if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
						//提交验证码成功
						Log.e("sms","提交验证码成功");
						login();

					}else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE){
						//获取验证码成功
						Log.e("sms","获取验证码成功 ");



					}else if (event ==SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES){
						//返回支持发送验证码的国家列表
						Log.e("sms","获取国家列表成功 "+data.toString());
					}
				}else{
					Log.e("code", "error");
					Toast.makeText(LoginActivity.this, "code error", Toast.LENGTH_SHORT).show();
					((Throwable)data).printStackTrace();
				}
			}
		};
		SMSSDK.registerEventHandler(eh);
    }
	
	private void initView(){
		time = new TimeCount(60000, 1000);
		name=(EditText)findViewById(R.id.et_login_name);
		psd=(EditText)findViewById(R.id.et_login_psd);
		backImage=(ImageButton)findViewById(R.id.iv_login_back);
		tv_register=(TextView)findViewById(R.id.tv_fast_register);
		tv_find=(TextView)findViewById(R.id.tv_forget_psd);
		bt_login=(Button)findViewById(R.id.bt_login);
		bt_code=(Button)findViewById(R.id.login_code);
		bt_code.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				SMSSDK.getVerificationCode("86",name.getText().toString());
				time.start();
			}
		});
		bt_login.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				SMSSDK.submitVerificationCode("86", name.getText().toString(), psd.getText().toString());
				dialog= DialogUtil.createLoadingDialog(LoginActivity.this,"登录中");
				dialog.show();
				/*new Handler().postDelayed(new Runnable() {
					@Override
					public void run() {
						login();
					}
				},3000);*/
				;
			}
		});

		tv_register.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
				startActivity(intent);
			}
		});
		

		tv_find.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(LoginActivity.this,FindActivity.class);
				startActivity(intent);
			}
		});
		backImage.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
	}

	@Override
	public void onDestroy(){
		super.onDestroy();
		SMSSDK.unregisterEventHandler(eh);
	}

	private void login(){

		Intent intent = new Intent();
		Bundle bundle=new Bundle();;
		bundle.putString("name", name.getText().toString());
		bundle.putString("imageUrl", GlobalValue.ADURL);
		intent.putExtras(bundle);
		DbHelper helper=new DbHelper(GlobalContext.getContext(), GlobalValue.DB, 1);
		ContentValues values=new ContentValues();
		values.put("id", name.getText().toString());
		values.put("status", true);
		values.put("name", name.getText().toString());
		values.put("token", "123456");
		values.put("imageUrl", GlobalValue.ADURL);
		helper.save(values, GlobalValue.TABLE);
		dialog.dismiss();
		setResult(RESULT_OK, intent);
		finish();
	}
	class TimeCount extends CountDownTimer {
		public TimeCount(long millisInFuture, long countDownInterval) {
			super(millisInFuture, countDownInterval);
		}

		@Override
		public void onFinish() {// 计时完毕
			bt_code.setText("获取验证码");
			bt_code.setClickable(true);
		}

		@Override
		public void onTick(long millisUntilFinished) {// 计时过程
			bt_code.setClickable(false);//防止重复点击
			bt_code.setText(millisUntilFinished / 1000 + "s后重新发送");
		}
	}
}
