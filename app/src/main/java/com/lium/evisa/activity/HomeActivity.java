package com.lium.evisa.activity;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.lium.evisa.R;
import com.lium.evisa.adapter.HomeFragmentAdapter;
import com.lium.evisa.api.InternetApi;
import com.lium.evisa.api.InternetApiFactory;
import com.lium.evisa.api.ParserApi;
import com.lium.evisa.api.ParserApiFactory;
import com.lium.evisa.entity.EventMessage;
import com.lium.evisa.entity.Version;
import com.lium.evisa.entity.VersionEvent;
import com.lium.evisa.fragment.HallFragment;
import com.lium.evisa.fragment.InternetFragment;
import com.lium.evisa.fragment.MyFragment;
import com.lium.evisa.global.GlobalContext;
import com.lium.evisa.service.DownloadService;
import com.lium.evisa.ui.HomeBottomLayout;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Window;
import android.widget.Toast;

import de.greenrobot.event.EventBus;

public class HomeActivity  extends FragmentActivity{

	private HomeFragmentAdapter mAdapter;
    private ViewPager mPager;
    private HomeBottomLayout mTabLayout;
    private List<Fragment> list=new ArrayList<Fragment>();
	private InternetApi mInternetApi;
	private Version mVersion;
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_home);
		initView();
		EventBus.getDefault().register(this);
	}
	
	private void  initView(){
		mInternetApi=InternetApiFactory.getInstance();
		//Toast.makeText(this,""+mInternetApi.isNetConnectted(),Toast.LENGTH_SHORT).show();
    	HallFragment hallFragment=new HallFragment();
    	MyFragment myFragment=new MyFragment();
		InternetFragment internetFragment=new InternetFragment();
		mInternetApi=InternetApiFactory.getInstance();
		if(mInternetApi.isNetConnectted()){
			list.add(hallFragment);
			list.add(myFragment);
		}
		else{
			list.add(internetFragment);
			list.add(myFragment);
		}
        mAdapter = new HomeFragmentAdapter(getSupportFragmentManager(),list);
        mPager = (ViewPager) findViewById(R.id.vp_home);
        mPager.setAdapter(mAdapter);
        mTabLayout = (HomeBottomLayout) findViewById(R.id.home_bottom_tablayout);
        mTabLayout.setViewPager(mPager);
    }

	public void onEventMainThread(VersionEvent event) {
		EventMessage msg=event.getMessage();
		if(msg.isResult()){
			ParserApi parserApi= ParserApiFactory.getInstance();
			Gson gson=new Gson();
			mVersion=gson.fromJson(msg.getContent(),Version.class);
			Log.e("version",mVersion.getValue()+"*"+mVersion.getInfo()+"*"+mVersion.getUrl()+"*"+getVersionName());
			if(mVersion.getValue().compareTo(getVersionName())!=0){
				Log.e("update","show");
				showUpdateDialog();
			}
		}


	}

	@Override
	public void onDestroy(){
		super.onDestroy();
		EventBus.getDefault().unregister(this);//反注册EventBus
	}

	private void showUpdateDialog() {
		Log.e("update","show start");
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setIcon(android.R.drawable.ic_dialog_info);
		builder.setTitle("版本更新" );
		builder.setMessage(mVersion.getInfo());
		builder.setCancelable(false);
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				if (Environment.getExternalStorageState().equals(
						Environment.MEDIA_MOUNTED)) {
					Intent intent = new Intent(HomeActivity.this, DownloadService.class);
					//intent.putExtra("url", mVersion.getUrl());
					startService(intent);
				} else {
					Toast.makeText(GlobalContext.getContext(), "SD卡不可用，请插入SD卡",
							Toast.LENGTH_SHORT).show();
				}
			}
		});
		builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {

			}

		});
		builder.create().show();
		Log.e("update","show end");
	}

	private String getVersionName(){
		//获取packagemanager的实例
		PackageManager packageManager = getPackageManager();
		//getPackageName()是你当前类的包名，0代表是获取版本信息
		PackageInfo packInfo = null;
		try {
			packInfo = packageManager.getPackageInfo(getPackageName(), 0);
		} catch (PackageManager.NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Log.e("Version", packInfo.versionName);
		return packInfo.versionName;
	}
}
