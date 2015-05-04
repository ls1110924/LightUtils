package com.cqu.lightutils.activity;

import android.os.Bundle;
import android.view.MenuItem;

/**
 * Created by A Shuai on 2015/4/30.
 * 不需要NavigationBar(包括ActionBar和ToolBar)的FragmentActivity的子类可继承本类
 * 切记当前类的子类Activity主题需使用NoActionBar样式
 */
public abstract class BaseNoActionBarFragmentActivity extends BaseFragmentActivity {

    /**
     * 不提供覆写{@link #onCreate(Bundle)}方法，若需覆写请覆写
     * {@link #onCreateImpl(Bundle)}
     *
     * @param savedInstanceState
     */
    @Override
    protected final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
     * @param savedInstanceState
     */
    protected void onCreateImpl(Bundle savedInstanceState) {
    }

    /**
     * 不可覆写
     * 是为保证各个Activity子类的菜单事件回调方法的覆写一致性
     * 需监听另外的菜单项点击事件，请覆写{@link #onOptionsItemSelected(MenuItem, int)}方法
     */
    @Override
    public final boolean onOptionsItemSelected(MenuItem item) {
        return onOptionsItemSelected(item, 1);
    }

    /**
     * 用户可覆写此方法进行监听菜单项点击事件
     * 用于替代{@link #onOptionsItemSelected(MenuItem)}方法
     *
     * @param item
     * @param flag 无意义
     * @return
     */
    public boolean onOptionsItemSelected(MenuItem item, int flag) {
        return super.onOptionsItemSelected(item);
    }

}
