package com.cqu.lightutils.sample;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.cqu.lightutils.activity.BaseActionBarFragmentActivity;
import com.cqu.lightutils.sample.adapter.LazyFragViewPagerAdapter;
import com.cqu.lightutils.sample.data.ParameterConfig;
import com.cqu.lightutils.sample.fragment.LazyFourFragment;
import com.cqu.lightutils.sample.fragment.LazyOneFragment;
import com.cqu.lightutils.sample.fragment.LazyThreeFragment;
import com.cqu.lightutils.sample.fragment.LazyTwoFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by A Shuai on 2015/5/4.
 */
public class LazyFragmentActivity extends BaseActionBarFragmentActivity {

    private ViewPager mViewPager;
    private List<Fragment> mViewDataSet;
    private LazyFragViewPagerAdapter mViewPagerAdapter;

    @Override
    protected void onInitParameter() {

        setActionBarHomeEnable(true);

        mViewDataSet = new ArrayList<>();
        mViewDataSet.add(new LazyOneFragment());
        mViewDataSet.add(new LazyTwoFragment());
        mViewDataSet.add(new LazyThreeFragment());
        mViewDataSet.add(new LazyFourFragment());

        mViewPagerAdapter = new LazyFragViewPagerAdapter(mFragmentManager, mViewDataSet);
    }

    @Override
    protected void onSetContentView() {
        setContentView(R.layout.activity_lazyfragment);
    }

    @Override
    protected void onFindViews() {
        mViewPager = findView(R.id.activity_lazyfragment_viewpager);
    }

    @Override
    protected void onBindContent() {
        mViewPager.setAdapter(mViewPagerAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        setMaterialThemeColor(ParameterConfig.getInstance().getMaterialTheme());
    }

    @Override
    protected boolean onBackKeyDown() {
        finish(R.anim.activity_slide_left_in_part, R.anim.activity_slide_right_out);
        return true;
    }

    @Override
    protected boolean onHomeKeyDown() {
        finish(R.anim.activity_slide_left_in_part, R.anim.activity_slide_right_out);
        return true;
    }
}
