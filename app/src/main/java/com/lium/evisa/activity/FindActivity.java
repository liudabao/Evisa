package com.lium.evisa.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

import com.lium.evisa.R;

public class FindActivity extends Activity{

	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_find);
	}

}
