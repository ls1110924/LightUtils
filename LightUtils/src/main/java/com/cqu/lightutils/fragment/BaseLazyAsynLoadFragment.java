package com.cqu.lightutils.fragment;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.cqu.lightutils.absutils.AbsFragmentHandler;
import com.cqu.lightutils.threadpool.ThreadExecutorManager;

import java.lang.ref.SoftReference;
import java.util.concurrent.ExecutorService;

/**
 * Created by A Shuai on 2015/4/30.
 * 异步数据延迟加载的Fragment，使用此Fragment最好提供两套布局，一套为正式的内容布局，
 * 一套为Loading。此Fragment适用于数据量较大以至于造成UI线程拥塞的情况。
 */
public abstract class BaseLazyAsynLoadFragment extends BaseFragment {

    /**
     * 异步数据加载完成的Message信号
     */
    private static final int ASYN_DATALOAD_COMPLETE = 10;


    private static final String EXECUTOR_NAME = "LIGHT_UTILS";


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

    private boolean isCreateView = false;

    private AsynTaskState mAsynState = AsynTaskState.INIT;

    private LayoutInflater mInflater;

    private BaseUpdateHandler mHandler;
    private ExecutorService mExecutor;

    @Override
    public void onCreateImpl(Bundle savedInstanceState) {
        mHandler = new BaseUpdateHandler(this);
        mExecutor = ThreadExecutorManager.getInstance().getExecutor(EXECUTOR_NAME);
    }

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

                mRootLoadingView = onInflaterRootLoadingView(inflater, mRootContainer, savedInstanceState);
                if (mRootLoadingView != null) {
                    mRootContainer.addView(mRootLoadingView, -1, mContainerLayoutParams);
                    onFindLoadingViews(mRootLoadingView);
                    onBindLoadingContent();
                }

                mAsynState = AsynTaskState.PROCESSING;
                mExecutor.execute(new AsynTask(this));

            } else {

                mRootLoadingView = onInflaterRootLoadingView(inflater, mRootContainer, savedInstanceState);
                if (mRootLoadingView != null) {
                    mRootContainer.addView(mRootLoadingView, -1, mContainerLayoutParams);
                    onFindLoadingViews(mRootLoadingView);
                    onBindLoadingContent();
                }

            }
            isPrepared = true;
            isLoaded = false;

        } else {

            ViewGroup mRootParent = (ViewGroup) mRootContainer.getParent();
            if (mRootParent != null) {
                mRootParent.removeView(mRootContainer);
            }

            if (isLoaded) {
                onBindContent();
            } else {
                if (isVisible) {

                    switch (mAsynState) {
                        case INIT:
                            mAsynState = AsynTaskState.PROCESSING;
                            mExecutor.execute(new AsynTask(this));
                            onBindLoadingContent();
                            break;
                        case PROCESSING:
                            onBindLoadingContent();
                            break;
                        case RPOCESSED:
                            initContentViewAfterProcess();
                            break;
                        case COMPLETE:
                            onBindContent();
                            break;
                    }

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

            mAsynState = AsynTaskState.INIT;
        }
    }

    /**
     * 子类需对此方法进行覆写，进行数据处理
     * 注意:此方法的执行回调位于子线程中，请务必不要更新UI线程中视图控件
     * 子类不可最好不要手动调用本方法，子类可在此方法中进行耗时过程的处理，
     * 如果进行了耗时处理请务必将返回值置为true，表示数据初始化完毕，可以进行UI加载
     * 如果子类在此进行了异步处理，请将返回值置为false，表示此方法的回调并没有真正完成数据初始化，
     * 不可进行UI加载，此时子类应手动完成Handler事件发送，{@link #handlerDataLoadComplete()}方法
     *
     * @return
     */
    protected abstract boolean onLoadedDataByAsyn();

    /**
     * 用于发送异步数据处理完成的回调
     */
    protected final void handlerDataLoadComplete() {
        mHandler.sendEmptyMessage(ASYN_DATALOAD_COMPLETE);
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
            switch (mAsynState) {
                case INIT:
                    mAsynState = AsynTaskState.PROCESSING;
                    mExecutor.execute(new AsynTask(this));
                    onBindLoadingContent();
                    break;
                case PROCESSING:
                    onBindLoadingContent();
                    break;
                case RPOCESSED:
                    initContentViewAfterProcess();
                    break;
                case COMPLETE:
                    onBindContent();
                    break;
            }
        }
    }

    private final void initContentViewAfterProcess() {
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

    /**
     * 子Fragment对此方法覆写用于清理过度占用内存的数据和视图
     * 对视图控件的引用务必清空，以便GC能正确回收。本类的回调方法会在必要的情况下进行重构视图的
     */
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

    /**
     * 当前Fragment进行更新UI所用的Handler
     */
    private static class BaseUpdateHandler extends AbsFragmentHandler<BaseLazyAsynLoadFragment> {

        public BaseUpdateHandler(BaseLazyAsynLoadFragment mFragment) {
            super(mFragment);
        }

        @Override
        protected void handleMessage(BaseLazyAsynLoadFragment mFragment, Message msg, Bundle mBundle) {
            switch (msg.what) {
                case ASYN_DATALOAD_COMPLETE:
                    mFragment.mAsynState = AsynTaskState.RPOCESSED;
                    if (mFragment.getUserVisibleHint()) {
                        mFragment.initContentViewAfterProcess();
                        mFragment.mAsynState = AsynTaskState.COMPLETE;
                    }
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * 异步任务
     */
    private static class AsynTask implements Runnable {

        private final SoftReference<BaseLazyAsynLoadFragment> mFragmentRef;

        public AsynTask(BaseLazyAsynLoadFragment mFragment) {
            mFragmentRef = new SoftReference<BaseLazyAsynLoadFragment>(mFragment);
        }

        @Override
        public void run() {
            BaseLazyAsynLoadFragment mFragment = getFragment();
            if (mFragment == null)
                return;
            if (mFragment.onLoadedDataByAsyn()) {
                mFragment.handlerDataLoadComplete();
            }
        }

        private final BaseLazyAsynLoadFragment getFragment() {
            return mFragmentRef.get();
        }
    }

    /**
     * 异步数据处理的状态枚举
     */
    private enum AsynTaskState {

        /**
         * 初始化，应该进行异步数据加载，应继续使用Loading布局
         */
        INIT,

        /**
         * 处理中，继续使用Loading布局
         */
        PROCESSING,

        /**
         * 处理完成，可以加载UI，执行加载内容布局
         */
        RPOCESSED,

        /**
         * 加载完成，继续使用内容布局
         */
        COMPLETE

    }

}
