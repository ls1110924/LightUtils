package com.yunxian.lightutils.lib.threadpool;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.yunxian.lightutils.lib.constants.LightUtilsConstants.DEFAULT_THREADPOOL_SIZE;

/**
 * Created by A Shuai on 2015/5/2.
 * 用于管理一组线程池的管理类
 * 可根据提供的ID获得指定的线程池对象
 * 此线程池对象使用完毕后请调用{@link #shutdown(String)}方法进行关闭
 */
public final class ThreadExecutorManager {

    private static ThreadExecutorManager mInstance;

    /**
     * 获取线程池管理的单例对象的引用
     *
     * @return 线程管理器的单例对应引用
     */
    public static ThreadExecutorManager getInstance() {
        if (mInstance == null) {
            synchronized (ThreadExecutorManager.class) {
                if (mInstance == null) {
                    mInstance = new ThreadExecutorManager();
                }
            }
        }
        return mInstance;
    }

    private int mThreadPoolSize;

    private final HashMap<String, ExecutorService> mExecutors;

    private ThreadExecutorManager() {
        mThreadPoolSize = DEFAULT_THREADPOOL_SIZE;
        mExecutors = new HashMap<>();
    }

    /**
     * 获得指定名字的线程池
     *
     * @param mExecutorName 要获取的线程池对象ID
     * @return 线程池对象
     */
    public ExecutorService getExecutor(String mExecutorName) {
        ExecutorService mService = mExecutors.get(mExecutorName);
        if (mService == null) {
            synchronized (this) {
                mService = mExecutors.get(mExecutorName);
                if (mService == null) {
                    mService = Executors.newFixedThreadPool(mThreadPoolSize);
                }
                mExecutors.put(mExecutorName, mService);
            }
        }
        return mService;
    }

    /**
     * 向指定名字的线程池中提交一个任务
     *
     * @param mExecutorName 线程池名
     * @param mTask         任务
     */
    public void execute(String mExecutorName, Runnable mTask) {
        ExecutorService mService = getExecutor(mExecutorName);
        mService.execute(mTask);
    }

    /**
     * 关闭指定名字的线程池
     *
     * @param mExecutorName 线程池名
     */
    public void shutdown(String mExecutorName) {
        ExecutorService mService = mExecutors.get(mExecutorName);
        if (mService == null)
            return;
        mService.shutdown();
        mExecutors.remove(mExecutorName);
    }

    /**
     * 关闭所有的线程池
     */
    public void shutdownAllExecutor() {

        synchronized (this) {
            Set<Map.Entry<String, ExecutorService>> mExeSet = mExecutors.entrySet();
            for (Map.Entry<String, ExecutorService> mSet : mExeSet) {
                ExecutorService mService = mSet.getValue();
                mService.shutdown();
            }
            mExecutors.clear();
        }
    }

    /**
     * 设置线程池默认大小
     * 推荐3-5之间即可
     *
     * @param mThreadPoolSize 待设置的线程池的大小
     */
    public void setThreadPoolSize(int mThreadPoolSize) {
        this.mThreadPoolSize = mThreadPoolSize;
    }
}
