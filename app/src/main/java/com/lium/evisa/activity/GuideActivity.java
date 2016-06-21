package com.lium.evisa.activity;

import java.util.ArrayList;
import java.util.List;

import com.lium.evisa.R;
import com.lium.evisa.adapter.GuideAdapter;
import com.lium.evisa.fragment.EnterFragment;
import com.lium.evisa.fragment.FirstFragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Window;

public class GuideActivity extends FragmentActivity{
	private GuideAdapter guideAdapter;
	private List<Fragment> listFragments;
	private ViewPager vp_guide;
	private FirstFragment firstFragment;
	private EnterFragment enterFragment;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_guide);
		initData();
		initView();
	}
	
	private void initView(){
		vp_guide=(ViewPager)findViewById(R.id.vp_guide);
		guideAdapter=new GuideAdapter(getSupportFragmentManager(),listFragments);
		vp_guide.setAdapter(guideAdapter);
		
	}
	
	private void initData(){
		listFragments=new ArrayList<Fragment>();
		firstFragment=new FirstFragment();
		enterFragment=new EnterFragment();
		listFragments.add(firstFragment);
		listFragments.add(enterFragment);
		
	}
}
