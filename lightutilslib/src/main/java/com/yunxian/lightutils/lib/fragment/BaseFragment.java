package com.yunxian.lightutils.lib.fragment;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yunxian.lightutils.lib.absutils.AbsFragmentHandler;
import com.yunxian.lightutils.lib.activity.BaseFragmentActivity;
import com.yunxian.lightutils.lib.listener.IFastClickDetect;

import java.lang.ref.WeakReference;
import java.lang.reflect.Field;

/**
 * Created by A Shuai on 2015/4/30.
 * Fragment的实现子类不可继承本类，实现子类可根据功能继承
 * {@link com.yunxian.lightutils.lib.fragment.BaseDynamicFragment},{@link com.yunxian.lightutils.lib.fragment.BaseStaticFragment},
 * {@link com.yunxian.lightutils.lib.fragment.BaseNonReuseFragment}这三个子类
 */
public abstract class BaseFragment extends Fragment {

    //可供子类使用的Context对象
    protected Context mContext;

    protected Cursor mCursor;

    protected FragmentManager mFragmentManager;

    //快速点击侦测接口
    protected IFastClickDetect mIFastClickDetect;

    /**
     * 子类和配合覆写{@link #handleMessage(Message, Bundle)}方法使用本Handler对象
     */
    protected final BaseUpdateHandler mBaseHandler = new BaseUpdateHandler(this);

    /**
     * 基类Fragment提供了点击和长按回调监听器，子类可直接使用，并配合覆写{@link #onViewClick(View)}
     * 和{@link #onViewLongClick(View)}方法即可使用。
     */
    protected final BaseCommonCallbackListener mBaseCommonListener = new BaseCommonCallbackListener(this);

    /**
     * 子类不可覆写此方法，以免破坏onCreate的初始化逻辑
     * 子类可覆写{@link #onCreateImpl(Bundle)}方法完成自定义的初始化逻辑
     *
     * @param savedInstanceState 用于恢复状态的Bundle数据集
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
     * @param savedInstanceState 用于恢复状态的Bundle数据集
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
     * @param inflater           用于实例化layout文件的Inflater
     * @param container          父容器
     * @param savedInstanceState 有可能为空，使用之前请先进行判断
     * @return 不可为空
     */
    @NonNull
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

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context != null && context instanceof IFastClickDetect) {
            mIFastClickDetect = (IFastClickDetect) context;
        } else {
            mIFastClickDetect = null;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        mIFastClickDetect = null;
    }

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
     * @param mContentId 被替换Fragment的布局ID
     * @param mFragment  替换的Fragment
     * @param mTag       标签
     * @param enterAnim  替换进入动画
     * @param exitAnim   替换退出动画
     */
    protected final void replaceFragments(int mContentId, Fragment mFragment, String mTag, int enterAnim, int exitAnim) {
        Fragment mOldFragment = mFragmentManager.findFragmentByTag(mTag);
        FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();
        if (mOldFragment != null) {
            mFragmentTransaction.remove(mOldFragment);
        }
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
     * @param requestCode 请求码
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
     * @param target  指定打开的Service的class对象
     * @param mBundle 按需传递的数据Bundle
     */
    protected final void startService(Class<? extends Service> target, Bundle mBundle) {
        ((BaseFragmentActivity) getActivity()).startService(target, mBundle);
    }

    /**
     * 关闭一个普通的后台服务
     *
     * @param target  指定关闭的Service的class对象
     * @param mBundle 按需传递的数据Bundle
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
     * @param mView 待查找视图所在的容器视图
     * @param id    待查找的视图ID
     * @param <T>   待查找的视图类型
     * @return 待查找的视图对象
     */
    @SuppressWarnings("unchecked")
    protected static <T extends View> T findView(View mView, int id) {
        try {
            return (T) mView.findViewById(id);
        } catch (ClassCastException ex) {
            throw new ClassCastException(ex.getMessage());
        }
    }

    /**
     * 点击事件回调方法，子类可自行覆写处理。
     *
     * @param v 被点击的按钮
     */
    public void onViewClick(View v) {

    }

    /**
     * 长按事件回调方法，子类可自行覆写处理。
     *
     * @param v 被长按的按钮
     * @return true表示事件被处理，false为未处理
     */
    public boolean onViewLongClick(View v) {
        return false;
    }

    /**
     * 基类提供的常用的公共点击事件回调监听器
     */
    private static final class BaseCommonCallbackListener implements View.OnClickListener, View.OnLongClickListener {

        private final WeakReference<BaseFragment> mFragmentRef;

        public BaseCommonCallbackListener(BaseFragment mFragment) {
            mFragmentRef = new WeakReference<>(mFragment);
        }

        @Override
        public void onClick(View v) {
            BaseFragment mFragment = mFragmentRef.get();
            if (mFragment == null) {
                return;
            }
            if (mFragment.mIFastClickDetect == null) {
                mFragment.onViewClick(v);
            } else if (mFragment.mIFastClickDetect.isLegalClick(v)) {
                //在这里对快速点击进行了处理
                mFragment.onViewClick(v);
            }
        }

        @Override
        public boolean onLongClick(View v) {
            BaseFragment mFragment = mFragmentRef.get();
            return mFragment != null && mFragment.onViewLongClick(v);
        }

    }

    /**
     * Handler对Message的回调处理方法，子类可自行覆写处理
     *
     * @param msg     Message对象
     * @param mBundle 可能为null，子类使用时需注意
     */
    public void handleMessage(Message msg, Bundle mBundle) {

    }

    /**
     * 基类提供的Handler实现类
     */
    public static final class BaseUpdateHandler extends AbsFragmentHandler<BaseFragment> {

        public BaseUpdateHandler(BaseFragment mFragment) {
            super(mFragment);
        }

        @Override
        protected void handleMessage(BaseFragment mFragment, Message msg, Bundle mBundle) {
            mFragment.handleMessage(msg, mBundle);
        }
    }

}
