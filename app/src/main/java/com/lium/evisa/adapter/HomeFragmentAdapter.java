package com.lium.evisa.adapter;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by wuyexiong on 4/25/15.
 */
public class HomeFragmentAdapter extends FragmentPagerAdapter {

    private List<Fragment> list;

    public HomeFragmentAdapter(FragmentManager fm) {
        super(fm);
    }
    
    public HomeFragmentAdapter(FragmentManager fm,List<Fragment> list) {
        super(fm);
        this.list=list;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

}
