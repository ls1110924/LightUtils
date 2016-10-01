package com.yunxian.lightutils.lib.activity;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.yunxian.lightutils.lib.R;

/**
 * Created by A Shuai on 2016/1/9.
 * <p>Marerial风格带有Actionbar的Activity。其中已经包含了布局，
 * 用户可自行填充内容面板对应的Fragment即可</p>
 */
public abstract class BaseMarerialActivityIncludedActionbar extends BaseFragmentActivity {

    private static final String TAG_CONTENT = "CONTENT";
    private static final int ID_CONTENT_PANEL = R.id.lightutils_activity_actionbar_content;

    protected ActionBar mActionBar;

    private Fragment mContentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mActionBar = getSupportActionBar();
        if (mActionBar == null) {
            throw new IllegalStateException("Please select LightUtilsTheme.BaseMaterial.BaseActionBar theme or its child theme。");
        }

        onInitParameter();
        super.setContentView(R.layout.lightutils_activity_includedactionbar);

        mContentFragment = onSetInitContentFragment();
        replaceFragments(ID_CONTENT_PANEL, mContentFragment, TAG_CONTENT, 0, 0);

        onCreateImpl(savedInstanceState);
    }

    /**
     * 初始化一些必要的参数值
     * 子类可按需自行覆写
     */
    protected void onInitParameter() {
    }


    /**
     * 提供初始化的内容面板Fragment
     *
     * @return 返回待填充的内容面板，不可为空
     */
    @NonNull
    protected abstract Fragment onSetInitContentFragment();

    /**
     * 用于替代{@link #onCreate(Bundle)}方法
     *
     * @param savedInstanceState 用于状态恢复的Bundle数据集
     */
    protected void onCreateImpl(Bundle savedInstanceState) {
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

    /**
     * 设置ActionBarHome键的可用性
     *
     * @param enable true为可用，否则为不可用
     */
    protected final void setActionBarHomeEnable(boolean enable) {
        mActionBar.setDisplayHomeAsUpEnabled(enable);
    }

    /**
     * 设置Actionbar的Title
     *
     * @param title Title字符串
     */
    protected final void setActionBarTitle(CharSequence title) {
        mActionBar.setTitle(title);
    }

    /**
     * 设置ActionBar的Title
     *
     * @param resID Title字符串资源ID
     */
    protected final void setActionBarTitle(@StringRes int resID) {
        mActionBar.setTitle(resID);
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
