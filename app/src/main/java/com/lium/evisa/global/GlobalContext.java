package com.lium.evisa.global;

import android.app.Application;
import android.content.Context;

public class GlobalContext extends Application{


	public static Context context;
	
	@Override
	public void onCreate(){
		super.onCreate();
		context=getApplicationContext();
	}
	
	public static Context getContext(){

		return context;
	}
}
