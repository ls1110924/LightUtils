package com.cqu.lightutils.sample.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by A Shuai on 2015/5/4.
 */
public class LazyFragViewPagerAdapter extends FragmentStatePagerAdapter{

    private List<Fragment> mDataSet;

    public LazyFragViewPagerAdapter(FragmentManager fm, List<Fragment> mDataSet) {
        super(fm);
        this.mDataSet = mDataSet;
    }

    @Override
    public int getCount() {
        return mDataSet.size();
    }

    @Override
    public Fragment getItem(int position) {
        return mDataSet.get(position);
    }


}
