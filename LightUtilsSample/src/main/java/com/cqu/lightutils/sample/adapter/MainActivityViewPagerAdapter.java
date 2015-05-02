package com.cqu.lightutils.sample.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by A Shuai on 2015/4/30.
 */
public class MainActivityViewPagerAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> mDataSet;

    public MainActivityViewPagerAdapter(FragmentManager fm, List<Fragment> mDataSet) {
        super(fm);
        this.mDataSet = mDataSet;
    }

    @Override
    public Fragment getItem(int position) {
        return mDataSet.get(position);
    }

    @Override
    public int getCount() {
        return mDataSet.size();
    }
}
