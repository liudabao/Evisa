package com.lium.evisa.adapter;

import java.util.ArrayList;
import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class GuideAdapter extends FragmentPagerAdapter{

	private List<Fragment> _fragList=new ArrayList<Fragment>();
	
	public GuideAdapter(FragmentManager fm) {
		super(fm);
		// TODO Auto-generated constructor stub
	}
	
	public GuideAdapter(FragmentManager fm,List<Fragment> fragList) {
		super(fm);
		// TODO Auto-generated constructor stub
		_fragList=fragList;
	}

	@Override
	public Fragment getItem(int position) {
		// TODO Auto-generated method stub
		return _fragList.get(position);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return _fragList.size();
	}

}
