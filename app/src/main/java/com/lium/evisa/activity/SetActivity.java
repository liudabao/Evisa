package com.lium.evisa.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;

import com.lium.evisa.R;
import com.lium.evisa.global.GlobalContext;
import com.lium.evisa.global.GlobalValue;
import com.lium.evisa.util.DbHelper;

public class SetActivity extends Activity{

	private ImageButton backImage;
	private Button logout;
	private DbHelper helper;
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_set);
		initView();
	}

	private void initView(){
		backImage=(ImageButton)findViewById(R.id.iv_set_back);
		logout=(Button)findViewById(R.id.set_logout);
		backImage.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});

		logout.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				showUpdateDialog(SetActivity.this);
			}
		});
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		finish();
	}

	private void showUpdateDialog(Context context) {
		Log.e("update","show start");
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setIcon(android.R.drawable.ic_dialog_info);
		builder.setTitle("退出登录");
		builder.setMessage("确定退出登录");
		builder.setCancelable(false);
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				exit();
			}
		});
		builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {

			}

		});
		builder.create().show();

	}

	private void exit(){
		helper=new DbHelper(GlobalContext.getContext(), GlobalValue.DB, 1);
		helper.deleteAll(GlobalValue.TABLE);
		Intent intent = new Intent();
		setResult(RESULT_OK, intent);
		finish();
	}
}
