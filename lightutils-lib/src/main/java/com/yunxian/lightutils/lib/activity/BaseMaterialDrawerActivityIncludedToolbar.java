package com.yunxian.lightutils.lib.activity;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;

import com.yunxian.lightutils.lib.listener.OnNavigationItemSelectedListener;

import com.yunxian.lightutils.lib.R;

import static com.yunxian.lightutils.lib.constants.LightUtilsConstants.INVALID_VALUE;

/**
 * Created by A Shuai on 2015/12/30.
 * <p>已封装Toolbar，侧滑菜单面板和内容面板的Activity类。
 * 用户只需自定义侧滑菜单面板和内容面板对应的Fragment类即可。</p>
 */
public abstract class BaseMaterialDrawerActivityIncludedToolbar extends BaseMaterialDrawerActivity
        implements OnNavigationItemSelectedListener {

    private static final String TAG_CONTENT = "CONTENT";
    private static final String TAG_DRAWER = "DRAWER";

    protected CoordinatorLayout mRootLayout;

    private Fragment mContentFragment;

    private Fragment mDrawerFragment;

    protected FloatingActionButton mFloatingButton;

    private int mNavigationItemLastSelected = INVALID_VALUE;
    private int mNavigationItemSelected = INVALID_VALUE;

    @Override
    protected final void onSetContentView() {
        super.setContentView(R.layout.lightutils_activity_materialdrawerincludedtoolbar);

        mRootLayout = findView(R.id.lightutils_activity_matedrawertoolbar_rootlayout);
        mFloatingButton = findView(R.id.lightutils_activity_matedrawertoolbar_fab);
    }

    @Override
    protected final void onFindViews() {
        mContentFragment = onSetInitContentPanel();
        mDrawerFragment = onSetDrawerPanel();
    }

    @Override
    protected final void onBindContent() {
        replaceFragments(R.id.lightutils_activity_matedrawertoolbar_content, mContentFragment, TAG_CONTENT, 0, 0);
        replaceFragments(R.id.lightutils_activity_matedrawertoolbar_drawer, mDrawerFragment, TAG_DRAWER, 0, 0);
    }

    /**
     * 填充内容面板的Fragment
     *
     * @return 返回值不可为空
     */
    @NonNull
    protected abstract Fragment onSetInitContentPanel();

    /**
     * 填充侧滑抽屉面板的Fragment
     *
     * @return 返回值不可为空
     */
    @NonNull
    protected abstract Fragment onSetDrawerPanel();

    @NonNull
    @Override
    protected Toolbar onFindToolBarView() {
        return findView(R.id.lightutils_activity_matedrawertoolbar_toolbar);
    }

    @NonNull
    @Override
    protected DrawerLayout onFindDrawerLayoutView() {
        return findView(R.id.lightutils_activity_matedrawertoolbar_drawerlayout);
    }

    @Override
    protected void onCreateImpl(Bundle savedInstanceState) {

    }

    @Override
    protected void colourByMaterialThemePrimaryColor(int mColor) {
        super.colourByMaterialThemePrimaryColor(mColor);
    }

    /**
     * 覆写Android5.0以上系统对状态栏的着色逻辑，不使用Window对象对状态栏进行作色。
     * 因为使用了透明状态栏主题，实际上的状态栏是透明的，被着色的是一个逻辑上的状态栏，
     * 逻辑状态栏的着色交由根布局来处理。
     *
     * @param mColor 待使用着色的颜色
     */
    @Override
    protected void setStatusBarColorOnGreaterEqualLollipop(int mColor) {
        mDrawerLayout.setStatusBarBackgroundColor(mColor);
    }

    @Override
    protected void colourByMaterialThemeAccentColor(int mColor) {
        super.colourByMaterialThemeAccentColor(mColor);

        mFloatingButton.setBackgroundDrawable(new ColorDrawable(mColor));
    }

    /**
     * 本DrawerActivity模板类已经设置了内容布局，无需再次设置
     *
     * @param layoutResID 布局资源ID
     */
    @Override
    public final void setContentView(@LayoutRes int layoutResID) {
        throw new IllegalStateException("shouldn't call this method.");
    }

    /**
     * 本DrawerActivity模板类已经设置了内容布局，无需再次设置
     *
     * @param view 布局视图
     */
    @Override
    public final void setContentView(View view) {
        throw new IllegalStateException("shouldn't call this method.");
    }

    /**
     * 本DrawerActivity模板类已经设置了内容布局，无需再次设置
     *
     * @param view   布局视图
     * @param params 布局参数
     */
    @Override
    public final void setContentView(View view, ViewGroup.LayoutParams params) {
        throw new IllegalStateException("shouldn't call this method.");
    }

    @Override
    public void onDrawerClosed(View drawerView) {
        super.onDrawerClosed(drawerView);

        if (mNavigationItemSelected != INVALID_VALUE) {
            if (onNavigationItemSelectedExtend(mNavigationItemSelected, mNavigationItemLastSelected)) {
                mNavigationItemLastSelected = mNavigationItemSelected;
            }
            mNavigationItemSelected = INVALID_VALUE;
        }
    }

    /**
     * 侧滑抽屉导航的菜单项选中回调事件处理。子类不可需覆写，子类若对菜单选中项
     * 的回调事件感兴趣，可覆写{@link #onNavigationItemSelectedExtend(int, int)}方法
     *
     * @param mItemID 菜单项ID
     * @return true表示事件被消费
     */
    @Override
    public final boolean onNavigationItemSelected(int mItemID) {
        mNavigationItemSelected = mItemID;
        closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * 侧滑抽屉导航的菜单选中项的回调事件处理，子类可自行覆写。
     *
     * @param mSelectedItemID     此次选中的菜单项ID
     * @param mLastSelectedItemID 上次选中的菜单项ID
     * @return true表示需要记录此次选中项索引，false表示无需记录此次选中项索引
     */
    public boolean onNavigationItemSelectedExtend(int mSelectedItemID, int mLastSelectedItemID) {
        return false;
    }

}
