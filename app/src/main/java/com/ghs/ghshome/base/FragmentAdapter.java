package com.ghs.ghshome.base;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.SparseArray;

/**
 * Author:wang_sir
 * Time:2018/6/20 21:35
 * Description:This is FragmentAdapter
 */
public class FragmentAdapter extends FragmentPagerAdapter {
    private SparseArray<Fragment> fragments;


    public FragmentAdapter(FragmentManager fm, SparseArray<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments==null?0:fragments.size();
    }




}
