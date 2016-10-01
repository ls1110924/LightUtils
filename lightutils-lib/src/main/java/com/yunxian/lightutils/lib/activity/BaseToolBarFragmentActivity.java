package com.yunxian.lightutils.lib.activity;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.yunxian.lightutils.lib.compat.ViewCompat;
import com.yunxian.lightutils.lib.custominterface.StatusBarThemeInterface;

/**
 * Created by A Shuai on 2015/4/30.
 * 需使用ToolBar而非ActionBar的FragmentActivity子类可继承本类
 * 切记当前类的子类Activity主题需使用NoActionBar样式
 */
public abstract class BaseToolBarFragmentActivity extends BaseFragmentActivity {

    /**
     * ToolBar工具栏对象
     */
    protected Toolbar mToolBar;

    protected ActionBar mActionBar;

    /**
     * 不提供覆写本方法，若需覆写请覆写{@link #onCreateImpl(Bundle)}
     *
     * @param savedInstanceState 用于状态恢复的Bundle数据集
     */
    @Override
    protected final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        onInitParameter();
        onSetContentView();
        mToolBar = onFindToolBarView();
        setSupportActionBar(mToolBar);
        mActionBar = getSupportActionBar();
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
     * 设置ActionBarHome键的可用性
     *
     * @param enable true为可用，否则为不可用
     */
    protected final void setActionBarHomeEnable(boolean enable) {
        mActionBar.setDisplayHomeAsUpEnabled(enable);
    }

    @Override
    protected void setStatusBarAndNavigationBarTheme(StatusBarThemeInterface mTheme) {
        super.setStatusBarAndNavigationBarTheme(mTheme);
        setToolBarColor(mTheme.getToolBarColor());
    }

    @Override
    protected void colourByMaterialThemePrimaryColor(int mColor) {
        super.colourByMaterialThemePrimaryColor(mColor);
        setToolBarColor(mColor);
    }

    /**
     * 设置ActionBar的颜色
     *
     * @param mColor 设置ToolBar工具栏的配色
     */
    protected final void setToolBarColor(int mColor) {
        ViewCompat.setViewBackground(mToolBar, new ColorDrawable(mColor));
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
