package com.lium.evisa.activity;

import com.lium.evisa.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class LoginActivity extends Activity{

	private TextView tv_register;
	private TextView tv_find;
	private Button bt_login;
	private ImageButton backImage;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);
        initView();
    }
	
	private void initView(){
		backImage=(ImageButton)findViewById(R.id.iv_login_back);
		tv_register=(TextView)findViewById(R.id.tv_fast_register);
		tv_find=(TextView)findViewById(R.id.tv_forget_psd);
		bt_login=(Button)findViewById(R.id.bt_login);
		
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
}
