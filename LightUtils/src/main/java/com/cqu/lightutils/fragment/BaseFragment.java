package com.cqu.lightutils.fragment;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cqu.lightutils.activity.BaseFragmentActivity;

import java.lang.reflect.Field;

/**
 * Created by A Shuai on 2015/4/30.
 * Fragment的实现子类不可继承本类，实现子类可根据功能继承
 * {@link com.cqu.lightutils.fragment.BaseDynamicFragment},{@link com.cqu.lightutils.fragment.BaseStaticFragment},
 * {@link com.cqu.lightutils.fragment.BaseNonReuseFragment}这三个子类
 */
public abstract class BaseFragment extends Fragment {

    //可供子类使用的Context对象
    protected Context mContext;

    protected Cursor mCursor;

    protected FragmentManager mFragmentManager;

    /**
     * 子类不可覆写此方法，以免破坏onCreate的初始化逻辑
     * 子类可覆写{@link #onCreateImpl(Bundle)}方法完成自定义的初始化逻辑
     *
     * @param savedInstanceState
     */
    @Override
    public final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = getActivity();

        mFragmentManager = getChildFragmentManager();

        onCreateImpl(savedInstanceState);

        onInitParameter();
    }

    /**
     * 子类可覆写此方法完成自定义的初始化逻辑
     *
     * @param savedInstanceState
     */
    public void onCreateImpl(Bundle savedInstanceState) {
    }

    /**
     * 初始化一些必要的参数
     * 需要初始化参数的子类自行覆写
     */
    protected void onInitParameter() {
    }

    /**
     * 请务必在此方法中返回此Fragment提供的根视图，返回结果不可为空
     *
     * @param inflater
     * @param container
     * @param savedInstanceState 有可能为空，使用之前请先进行判断
     * @return 不可为空
     */
    protected abstract View onInflaterRootView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    /**
     * 根据提供的根视图，找到当前页面需要使用的一些视图控件
     *
     * @param mRootView 提供给子类查找视图控件所用
     */
    protected abstract void onFindViews(View mRootView);

    /**
     * 将数据与视图控件进行绑定，以显示内容
     */
    protected abstract void onBindContent();

    /**
     * 处理使用ChildFragmentManager时的一些BUG
     */
    @Override
    public void onDetach() {
        super.onDetach();
        try {
            Field childFragmentManager = Fragment.class.getDeclaredField("mChildFragmentManager");
            childFragmentManager.setAccessible(true);
            childFragmentManager.set(this, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 以指定动画执行Fragment的替换操作
     *
     * @param mContentId
     * @param mFragment
     * @param mTag
     * @param enterAnim
     * @param exitAnim
     */
    protected final void replaceFragments(int mContentId, Fragment mFragment, String mTag, int enterAnim, int exitAnim) {
        FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.setCustomAnimations(enterAnim, exitAnim);
        mFragmentTransaction.replace(mContentId, mFragment, mTag);
        mFragmentTransaction.commit();
    }

    /**
     * 简单的打开一个新的Activity，并传入必要的参数
     *
     * @param target    要打开的Activity的Class对象
     * @param enterAnim 使用0表示不指定动画
     * @param exitAnim  使用0表示不指定动画
     * @param isFinish  是否终止当前activity
     * @param mBundle   给新的Activity传递的参数
     */
    protected final void startNewActivity(Class<? extends Activity> target,
                                          int enterAnim, int exitAnim, boolean isFinish, Bundle mBundle) {
        ((BaseFragmentActivity) getActivity()).startNewActivity(target, enterAnim, exitAnim, isFinish, mBundle);
    }

    /**
     * 打开一个可以回调数据的Activity，并传入必要的参数
     *
     * @param target      要打开的Activity的Class对象
     * @param enterAnim   使用0表示不指定动画
     * @param exitAnim    使用0表示不指定动画
     * @param requestCode
     * @param mBundle     给新的Activity传递的参数
     */
    protected final void startNewActivityForResult(Class<? extends Activity> target,
                                                   int enterAnim, int exitAnim, int requestCode, Bundle mBundle) {
        ((BaseFragmentActivity) getActivity()).startNewActivityForResult(target, enterAnim, exitAnim, requestCode, mBundle);
    }

    /**
     * 以指定的动画结束当前Activity
     *
     * @param enterAnim 使用0表示不指定动画
     * @param exitAnim  使用0表示不指定动画
     */
    protected final void finish(int enterAnim, int exitAnim) {
        ((BaseFragmentActivity) getActivity()).finish(enterAnim, exitAnim);
    }

    /**
     * 开启一个普通的后台服务
     *
     * @param target
     * @param mBundle
     */
    protected final void startService(Class<? extends Service> target, Bundle mBundle) {
        ((BaseFragmentActivity) getActivity()).startService(target, mBundle);
    }

    /**
     * 关闭一个普通的后台服务
     *
     * @param target
     * @param mBundle
     */
    protected final void stopService(Class<? extends Service> target, Bundle mBundle) {
        ((BaseFragmentActivity) getActivity()).stopService(target, mBundle);
    }

    /**
     * 关闭数据集游标
     */
    protected final void closeCursor() {
        if (mCursor != null) {
            mCursor.close();
            mCursor = null;
        }
    }

    /**
     * 根据提供的根视图和ID，从根视图中找出ID对应的视图
     *
     * @param mView
     * @param id
     * @param <T>
     * @return
     */
    protected static final <T extends View> T findView(View mView, int id) {
        try {
            return (T) mView.findViewById(id);
        } catch (ClassCastException ex) {
            throw ex;
        }
    }

}
