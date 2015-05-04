package com.cqu.lightutils.activity;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.cqu.lightutils.compat.ViewCompat;
import com.cqu.lightutils.custominterface.StatusBarThemeInterface;

/**
 * Created by A Shuai on 2015/4/30.
 * 需使用ToolBar而非ActionBar的FragmentActivity子类可继承本类
 * 切记当前类的子类Activity主题需使用NoActionBar样式
 */
public abstract class BaseToolBarFragmentActivity extends BaseFragmentActivity {

    protected Toolbar mToolBar;

    /**
     * 不提供覆写{@link #onCreate(Bundle)}方法，若需覆写请覆写
     * {@link #onCreateImpl(Bundle)}
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        onInitParameter();
        onSetContentView();
        mToolBar = onFindToolBarView();
        if (mToolBar == null) {
            throw new NullPointerException("ToolBar should not be null");
        }
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
     * @param savedInstanceState
     */
    protected void onCreateImpl(Bundle savedInstanceState) {
    }

    @Override
    protected void setStatusBarAndNavigationBarTheme(StatusBarThemeInterface mTheme) {
        super.setStatusBarAndNavigationBarTheme(mTheme);
        setToolBarColor(mTheme.getToolBarColor());
    }

    /**
     * 设置ActionBar的颜色
     *
     * @param mColor
     */
    protected final void setToolBarColor(int mColor) {
        ViewCompat.setViewBackground(mToolBar, new ColorDrawable(mColor));
    }

}
