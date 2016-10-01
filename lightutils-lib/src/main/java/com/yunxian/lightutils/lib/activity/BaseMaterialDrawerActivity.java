package com.yunxian.lightutils.lib.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.yunxian.lightutils.lib.R;

/**
 * Created by A Shuai on 2015/12/30.
 * <p>基础的使用Toolbar的Material风格的DrawerActivity。
 * 此类型Activity务必使用LightUtilsTheme.BaseMaterial.BaseDrawer主题。
 * 由于该主题会使用透明状态栏，为了保证侧滑菜单能够满足Material设计规范，
 * 需让侧滑菜单的内容填充从屏幕顶端开始，</p>
 */
public abstract class BaseMaterialDrawerActivity extends BaseFragmentActivity implements DrawerLayout.DrawerListener {

    protected Toolbar mToolbar;
    protected ActionBar mActionBar;

    protected DrawerLayout mDrawerLayout;

    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        onInitParameter();
        onSetContentView();

        mToolbar = onFindToolBarView();
        if (mToolbar == null) {
            throw new NullPointerException("Toolbar shouldn't be null!");
        }
        setSupportActionBar(mToolbar);
        mActionBar = getSupportActionBar();

        mDrawerLayout = onFindDrawerLayoutView();
        if (mDrawerLayout == null) {
            throw new NullPointerException("the DrawerLayout view shouldn't be null!");
        }

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar,
                R.string.lightutils_drawer_open, R.string.lightutils_drawer_close);
        mDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(this);

        onFindViews();
        onBindContent();
        onCreateImpl(savedInstanceState);
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
     * 子类需覆写此方法返回ToolBar的引用
     *
     * @return 不可为空
     */
    @NonNull
    protected abstract Toolbar onFindToolBarView();

    /**
     * 子类需覆写此方法返回子类的DrawerLayout
     *
     * @return 返回不可为空
     */
    @NonNull
    protected abstract DrawerLayout onFindDrawerLayoutView();

    /**
     * 查找必要的子视图控件
     * 子类无需在这里继续查找Toolbar
     */
    protected abstract void onFindViews();

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
     * 不可覆写，此处包含了抽屉对返回按钮事件的优先响应
     *
     * @return true表示此事件被消费
     */
    @Override
    protected final boolean onBackKeyDown() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
            return true;
        } else if (mDrawerLayout.isDrawerOpen(GravityCompat.END)) {
            mDrawerLayout.closeDrawer(GravityCompat.END);
            return true;
        }
        return onBackKeyDownExtend();
    }

    /**
     * Back键按下回调事件的扩展回调方法
     *
     * @return true 表示事件被消费
     */
    protected abstract boolean onBackKeyDownExtend();

    @Override
    protected void colourByMaterialThemePrimaryColor(int mColor) {
        super.colourByMaterialThemePrimaryColor(mColor);
        mToolbar.setBackgroundColor(mColor);
    }

    @Override
    public void onDrawerSlide(View drawerView, float slideOffset) {
        mDrawerToggle.onDrawerSlide(drawerView, slideOffset);
    }

    @Override
    public void onDrawerOpened(View drawerView) {
        mDrawerToggle.onDrawerOpened(drawerView);
    }

    @Override
    public void onDrawerClosed(View drawerView) {
        mDrawerToggle.onDrawerClosed(drawerView);
    }

    @Override
    public void onDrawerStateChanged(int newState) {
        mDrawerToggle.onDrawerStateChanged(newState);
    }

    /**
     * 关闭指定方向的抽屉
     *
     * @param mGravity 带关闭抽屉的方向
     */
    protected final void closeDrawer(int mGravity) {
        mDrawerLayout.closeDrawer(mGravity);
    }

}
