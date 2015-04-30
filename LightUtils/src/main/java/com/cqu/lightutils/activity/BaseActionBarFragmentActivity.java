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

    protected ActionBar mActionBar;

    @Override
    protected final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mActionBar = getSupportActionBar();

        onInitParameter();
        onSetContentView();
        onFindViews();
        onBindContent();

    }

    /**
     * 设置ActionBarHome键的可用性
     *
     * @param enable
     */
    protected void setActionBarHomeEnable(boolean enable) {
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
     * @param mColor
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
     * @param item
     * @return
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
     * @param item
     * @param flag
     * @return
     */
    public boolean onOptionsItemSelected(MenuItem item, int flag) {
        return super.onOptionsItemSelected(item);
    }

}
