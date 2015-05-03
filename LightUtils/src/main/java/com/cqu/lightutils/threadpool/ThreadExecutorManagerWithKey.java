package com.cqu.lightutils.threadpool;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static com.cqu.lightutils.constants.LightUtilsConstants.DEFAULT_THREADPOOL_SIZE;

/**
 * Created by A Shuai on 2015/5/3.
 * 管理带有任务ID的线程池管理类
 */
public final class ThreadExecutorManagerWithKey {

    private static ThreadExecutorManagerWithKey mInstance;

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
        mExecutors = new HashMap<String, ThreadExecutorWithKey>();
    }

    /**
     * 获得指定名字的线程池
     *
     * @param mExecutorName 要获取的线程池对象ID
     * @return
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
     * @param mExecutorName
     * @param mTask
     */
    public void execute(String mExecutorName, String mTaskName, Runnable mTask) {
        ThreadExecutorWithKey mService = getExecutor(mExecutorName);
        mService.execute(mTaskName, mTask);
    }

    /**
     * 关闭指定名字的线程池
     *
     * @param mExecutorName
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

    public int getThreadPoolSize() {
        return mThreadPoolSize;
    }

    public void setThreadPoolSize(int mThreadPoolSize) {
        this.mThreadPoolSize = mThreadPoolSize;
    }
}
