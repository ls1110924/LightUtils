package com.cqu.lightutils.sample;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.cqu.lightutils.sample.adapter.MainActivityViewPagerAdapter;
import com.cqu.lightutils.sample.fragment.MainFragment;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    private ViewPager mViewPager;
    private List<Fragment> mViewDataSet;
    private MainActivityViewPagerAdapter mViewAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewPager = (ViewPager)findViewById(R.id.activity_main_viewpager);
        mViewDataSet = new ArrayList<>();
        mViewDataSet.add(MainFragment.getInstance(0));
        mViewDataSet.add(MainFragment.getInstance(1));
        mViewDataSet.add(MainFragment.getInstance(2));
        mViewAdapter = new MainActivityViewPagerAdapter(getSupportFragmentManager(),mViewDataSet);
        mViewPager.setAdapter(mViewAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
