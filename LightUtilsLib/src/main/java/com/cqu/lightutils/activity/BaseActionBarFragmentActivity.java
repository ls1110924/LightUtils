package com.cqu.lightutils.activity;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;

import com.cqu.lightutils.custominterface.StatusBarThemeInterface;

/**
 * Created by A Shuai on 2015/4/30.
 * 需实现带有ActionBar的FragmentActivity的子类，可继承本类
 */
public abstract class BaseActionBarFragmentActivity extends BaseFragmentActivity {

    /**
     * ActionBar动作栏对象
     */
    protected ActionBar mActionBar;

    /**
     * 不提供覆写本方法，若需覆写请覆写{@link #onCreateImpl(Bundle)}
     *
     * @param savedInstanceState 用于状态恢复的Bundle数据集
     */
    @Override
    protected final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mActionBar = getSupportActionBar();

        onInitParameter();
        onSetContentView();
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
     * 查找必要的子视图控件
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
     * 设置ActionBarHome键的可用性
     *
     * @param enable true为可用，否则为不可用
     */
    protected final void setActionBarHomeEnable(boolean enable) {
        mActionBar.setDisplayHomeAsUpEnabled(enable);
    }

    @Override
    protected final void setStatusBarAndNavigationBarTheme(StatusBarThemeInterface mTheme) {
        super.setStatusBarAndNavigationBarTheme(mTheme);
        setActionBarColor(mTheme.getActionBarColor());
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
     * ActionBar上的Home键被按下的回调
     *
     * @return true 表示事件被消费
     */
    protected boolean onHomeKeyDown() {
        return false;
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
                if (onHomeKeyDown()) {
                    return true;
                }
                break;
        }
        return onOptionsItemSelected(item, 1);
    }

    /**
     * 用户可覆写此方法进行监听菜单项其他item的点击事件，
     * 用于替代{@link #onOptionsItemSelected(MenuItem)}方法
     *
     * @param item 发生选择的菜单项
     * @param flag 标志位，无意义，仅作区分使用
     * @return true表示事件被消费
     */
    public boolean onOptionsItemSelected(MenuItem item, int flag) {
        return super.onOptionsItemSelected(item);
    }

}
