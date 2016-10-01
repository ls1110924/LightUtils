package com.yunxian.lightutils.lib.threadpool;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static com.yunxian.lightutils.lib.constants.LightUtilsConstants.DEFAULT_THREADPOOL_SIZE;

/**
 * Created by A Shuai on 2015/5/3.
 * 管理带有任务ID的线程池管理类
 */
public final class ThreadExecutorManagerWithKey {

    private static ThreadExecutorManagerWithKey mInstance;

    /**
     * 获取带任务键的线程池管理的单例对象的引用
     *
     * @return 带任务键的线程管理器的单例对应引用
     */
    public static ThreadExecutorManagerWithKey getInstance() {
        if (mInstance == null) {
            synchronized (ThreadExecutorManagerWithKey.class) {
                if (mInstance == null) {
                    mInstance = new ThreadExecutorManagerWithKey();
                }
            }
        }
        return mInstance;
    }

    private int mThreadPoolSize;

    private final HashMap<String, ThreadExecutorWithKey> mExecutors;

    private ThreadExecutorManagerWithKey() {
        mThreadPoolSize = DEFAULT_THREADPOOL_SIZE;
        mExecutors = new HashMap<>();
    }

    /**
     * 获得指定名字的线程池
     *
     * @param mExecutorName 要获取的线程池对象ID
     * @return 带任务键的线程池对象
     */
    public ThreadExecutorWithKey getExecutor(String mExecutorName) {
        ThreadExecutorWithKey mService = mExecutors.get(mExecutorName);
        if (mService == null) {
            synchronized (this) {
                mService = mExecutors.get(mExecutorName);
                if (mService == null) {
                    mService = new ThreadExecutorWithKey(mThreadPoolSize);
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
     * @param mTaskName     任务名
     * @param mTask         任务
     */
    public void execute(String mExecutorName, String mTaskName, Runnable mTask) {
        ThreadExecutorWithKey mService = getExecutor(mExecutorName);
        mService.execute(mTaskName, mTask);
    }

    /**
     * 关闭指定名字的线程池
     *
     * @param mExecutorName 线程池名
     */
    public void shutdown(String mExecutorName) {
        ThreadExecutorWithKey mService = mExecutors.get(mExecutorName);
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
            Set<Map.Entry<String, ThreadExecutorWithKey>> mExeSet = mExecutors.entrySet();
            for (Map.Entry<String, ThreadExecutorWithKey> mSet : mExeSet) {
                ThreadExecutorWithKey mService = mSet.getValue();
                mService.shutdown();
            }
            mExecutors.clear();
        }
    }

    /**
     * 获取线程池的大小
     *
     * @return 线程池打大小
     */
    public int getThreadPoolSize() {
        return mThreadPoolSize;
    }

    /**
     * '
     * 设置线程池的大小
     *
     * @param mThreadPoolSize 待设置的线程池的大小
     */
    public void setThreadPoolSize(int mThreadPoolSize) {
        this.mThreadPoolSize = mThreadPoolSize;
    }
}
