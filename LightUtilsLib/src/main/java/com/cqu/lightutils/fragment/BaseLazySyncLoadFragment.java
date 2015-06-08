package com.cqu.lightutils.fragment;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/**
 * Created by A Shuai on 2015/4/30.
 * 同步数据延迟加载的Fragment，使用此Fragment最好提供两套布局，一套为正式的内容布局，一套为
 * Loading
 * <p/>
 * 此Fragment适用于数据量较大但还不至于造成UI线程拥塞的情况，如果初始化数据量非常大且耗时，推荐使用
 * {@link BaseLazyAsynLoadFragment}异步式数据延迟加载型Fragment
 */
public abstract class BaseLazySyncLoadFragment extends BaseFragment {

    //当前Fragment为了实现懒加载，即当此Fragment完全可见的时候才会加载内容
    private FrameLayout mRootContainer;
    //为mRootContainer添加子视图所用的布局填充参数
    private ViewGroup.LayoutParams mContainerLayoutParams;

    //由子类Fragment所提供的视图的内容视图
    private View mRootView;
    //子类Fragment提供的内容视图前载入视图
    private View mRootLoadingView;

    //分别表示当前Fragment是否可见,是否已准备(表示已经走过onCreateView方法)以及是否数据已加载
    private boolean isVisible = false;
    private boolean isPrepared = false;
    private boolean isLoaded = false;

    private LayoutInflater mInflater;

    /**
     * 不提供覆写，需监听可见性的子类可覆写{@link #onFragmentVisible()}和
     * {@link #onFragmentInvisible()}方法
     *
     * @param isVisibleToUser
     */
    @Override
    public final void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        isVisible = isVisibleToUser;
        if (getUserVisibleHint()) {
            onLoadedData();
            onFragmentVisible();
        } else {
            onFragmentInvisible();
        }
    }

    @Override
    public final View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mRootContainer == null) {
            mInflater = inflater;
            mRootContainer = createContainerLayout();
            mContainerLayoutParams = new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            //如果可见说明这个Fragment初次加载就是可见，应立即初始化布局
            if (isVisible) {
                mRootView = onInflaterRootView(inflater, mRootContainer, savedInstanceState);
                if (mRootView == null) {
                    throw new NullPointerException("the root view should not be null");
                }
                mRootContainer.addView(mRootView, -1, mContainerLayoutParams);
                onFindViews(mRootView);
                onBindContent();
                isPrepared = true;
                isLoaded = true;
            } else {
                mRootLoadingView = onInflaterRootLoadingView(inflater, mRootContainer, savedInstanceState);
                if (mRootLoadingView != null) {
                    mRootContainer.addView(mRootLoadingView, -1, mContainerLayoutParams);
                    onFindLoadingViews(mRootLoadingView);
                    onBindLoadingContent();
                }
                isPrepared = true;
                isLoaded = false;
            }
        } else {

            ViewGroup mRootParent = (ViewGroup) mRootContainer.getParent();
            if (mRootParent != null) {
                mRootParent.removeView(mRootContainer);
            }

            if (isLoaded) {
                onBindContent();
            } else {
                if (isVisible) {
                    mRootView = onInflaterRootView(inflater, mRootContainer, savedInstanceState);
                    if (mRootView == null) {
                        throw new NullPointerException("the root view should not be null");
                    }
                    mRootContainer.removeAllViews();
                    mRootContainer.addView(mRootView, -1, mContainerLayoutParams);
                    onFindViews(mRootView);
                    onBindContent();
                    isLoaded = true;
                } else {
                    onBindLoadingContent();
                }
            }

        }

        return mRootContainer;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (isNeedDestroy()) {
            onClearDataset();

            mRootView = null;
            mRootLoadingView = null;
            mRootContainer = null;
            mContainerLayoutParams = null;
            isVisible = false;
            isPrepared = false;
            isLoaded = false;
        }
    }

    /**
     * 初始化未载入数据前的loading视图，子类若提供Loading视图可自行覆写并务必返回
     * 若子类提供了Loading视图，最好一并覆写了{@link #onFindLoadingViews(View)}方法和
     * {@link #onBindLoadingContent()}方法
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return 若子类覆写此方法，请务必不要返回空
     */
    protected View onInflaterRootLoadingView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return null;
    }

    /**
     * 根据提供的Loading视图，查找必要的内容视图控件
     *
     * @param mRootLoadingView
     */
    protected void onFindLoadingViews(View mRootLoadingView) {
    }

    /**
     * 绑定Loading视图的内容视图控件的内容
     */
    protected void onBindLoadingContent() {
    }

    /**
     * Fragment可见时进行的回调，子类可根据需要自行覆写
     */
    protected void onFragmentVisible() {
    }

    /**
     * Fragment不可见时进行的回调，子类可根据需要自行覆写
     */
    protected void onFragmentInvisible() {
    }

    /**
     * 在Fragment可见时进行判断是否载入数据
     */
    private final void onLoadedData() {
        if (!isPrepared)
            return;
        if (isLoaded) {
            onBindContent();
        } else {
            mRootView = onInflaterRootView(mInflater, mRootContainer, null);
            if (mRootView == null) {
                throw new NullPointerException("the root view should not be null");
            }
            mRootContainer.removeAllViews();
            mRootContainer.addView(mRootView, -1, mContainerLayoutParams);
            onFindViews(mRootView);
            onBindContent();
            isLoaded = true;
        }
    }

    /**
     * 如果此Fragment占用的数据量过大，可覆写此方法返回true，
     * 表示需要当Fragment无效时进行数据清理，然后覆写{@link #onClearDataset()}方法
     * 对占用内存的数据进行清理和对视图控件引用的释放(否则内存不会释放)，
     * 这样此Fragment再次回到台前时会重新加载所有的数据
     *
     * @return
     */
    protected boolean isNeedDestroy() {
        return false;
    }

    protected void onClearDataset() {
    }

    /**
     * 构造一个当然Fragment提供视图的容器，以便可以进行LazyLoad形式的内容替换
     *
     * @return
     */
    private FrameLayout createContainerLayout() {
        FrameLayout mLayout = new FrameLayout(mContext);
        ViewGroup.LayoutParams mParams = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mLayout.setLayoutParams(mParams);
        TypedArray array = mContext.getTheme().obtainStyledAttributes(new int[]{android.R.attr.colorBackground});
        mLayout.setBackgroundColor(array.getInt(0, android.R.color.white));
        array.recycle();
        return mLayout;
    }

}
