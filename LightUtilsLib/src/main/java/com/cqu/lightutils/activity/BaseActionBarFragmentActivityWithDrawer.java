package com.cqu.lightutils.activity;

import android.content.res.Configuration;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.view.View;

import com.cqu.lightutils.R;
import com.cqu.lightutils.custominterface.StatusBarThemeInterface;

/**
 * Created by A Shuai on 2015/4/30.
 * 本类为带有Drawer和ActionBar的页面的抽象基类
 */
public abstract class BaseActionBarFragmentActivityWithDrawer extends BaseFragmentActivity {

    /**
     * ActionBar动作栏对象
     */
    protected ActionBar mActionBar;
    /**
     * 配合ActionBar对象实现的动画对象
     */
    protected ActionBarDrawerToggle mActionBarToggle;

    /**
     * 子类需对抽屉控件赋值，不可为空
     */
    protected DrawerLayout mDrawerLayout;

    private BaseActionbarDrawerActivityCommonCallbackListener mBaseCommonListener = new BaseActionbarDrawerActivityCommonCallbackListener();

    /**
     * 不提供覆写本方法，若需覆写请覆写{@link #onCreateImpl(Bundle)}
     *
     * @param savedInstanceState 用于状态恢复的Bundle数据集
     */
    @Override
    protected final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mActionBar = getSupportActionBar();
        if(mActionBar==null){
            throw new NullPointerException("the ActionBar shouldn't be null");
        }
        mActionBar.setDisplayHomeAsUpEnabled(true);
        mActionBar.setDisplayShowHomeEnabled(false);

        onInitParameter();
        onSetContentView();
        mDrawerLayout = onFindDrawerLayoutView();
        onFindViews();

        mDrawerLayout.setDrawerListener(mBaseCommonListener);
        mDrawerLayout.setDrawerTitle(GravityCompat.START, getString(R.string.lightutils_drawer_title));

        mActionBarToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.string.lightutils_drawer_open, R.string.lightutils_drawer_close);

        onBindContent();
        onCreateImpl(savedInstanceState);

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Sync the toggle state after onRestoreInstanceState has occurred.
        mActionBarToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mActionBarToggle.onConfigurationChanged(newConfig);
    }

    /**
     * 初始化一些必要的参数值
     * 子类可按需自行覆写
     */
    protected void onInitParameter() {
    }

    /**
     * 设置布局
     */
    protected abstract void onSetContentView();

    /**
     * 查找必要的子视图控件
     */
    protected abstract void onFindViews();

    /**
     * 子类需覆写此方法返回子类的DrawerLayout
     *
     * @return 返回不可为空
     */
    @NonNull
    protected abstract DrawerLayout onFindDrawerLayoutView();

    /**
     * 将数据与视图进行绑定，以展示数据
     */
    protected abstract void onBindContent();

    /**
     * 用于替代{@link #onCreate(Bundle)}方法
     *
     * @param savedInstanceState 用于状态恢复的Bundle数据集
     */
    protected void onCreateImpl(Bundle savedInstanceState) {
    }

    /**
     * 子类可根据需要自行覆写此方法，表示对抽屉控件打开事件的监听
     *
     * @param mActionBar ActionBar对象
     */
    protected void onDrawerOpened(ActionBar mActionBar) {
    }

    /**
     * 子类可根据需要自行覆写此方法，表示对抽屉控件关闭事件的监听
     *
     * @param mActionBar ActionBar对象
     */
    protected void onDrawerClosed(ActionBar mActionBar) {
    }

    @Override
    protected void setStatusBarAndNavigationBarTheme(StatusBarThemeInterface mTheme) {
        super.setStatusBarAndNavigationBarTheme(mTheme);
        setActionBarColor(mTheme.getActionBarColor());
    }

    @Override
    protected void colourByMaterialThemePrimaryColor(int mColor) {
        super.colourByMaterialThemePrimaryColor(mColor);
        setActionBarColor(mColor);
    }

    /**
     * 设置ActionBar的颜色
     *
     * @param mColor 设置ActionBar动作栏的配色
     */
    protected final void setActionBarColor(int mColor) {
        mActionBar.setBackgroundDrawable(new ColorDrawable(mColor));
    }

    /**
     * 不可覆写
     * 需监听另外的菜单项点击事件，请覆写{@link #onOptionsItemSelected(MenuItem, int)}方法
     *
     * @param item 发生选择的菜单项
     * @return true表示事件被消费
     */
    @Override
    public final boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                /*
                 *	ActionBar的Home/Up事件应当响应打开或关闭抽屉，
		         *	mActionBarDrawerToggle会处理这一事件逻辑
		         */
                if (mActionBarToggle.onOptionsItemSelected(item)) {
                    return true;
                }
                break;
        }
        return onOptionsItemSelected(item, 1);
    }

    /**
     * 用户可覆写此方法进行监听菜单项点击事件
     *
     * @param item 发生选择的菜单项
     * @param flag 无意义，仅作区分的标志位
     * @return true表示事件被消费
     */
    public boolean onOptionsItemSelected(MenuItem item, int flag) {
        return super.onOptionsItemSelected(item);
    }

    /**
     * 抽屉状态事件回调监听器
     */
    private class BaseActionbarDrawerActivityCommonCallbackListener implements DrawerLayout.DrawerListener {

        @Override
        public void onDrawerOpened(View drawerView) {
            mActionBarToggle.onDrawerOpened(drawerView);
            BaseActionBarFragmentActivityWithDrawer.this.onDrawerOpened(mActionBar);
        }

        @Override
        public void onDrawerClosed(View drawerView) {
            mActionBarToggle.onDrawerClosed(drawerView);
            BaseActionBarFragmentActivityWithDrawer.this.onDrawerClosed(mActionBar);
        }

        @Override
        public void onDrawerSlide(View drawerView, float slideOffset) {
            mActionBarToggle.onDrawerSlide(drawerView, slideOffset);
        }

        @Override
        public void onDrawerStateChanged(int newState) {
            mActionBarToggle.onDrawerStateChanged(newState);
        }
    }

}
