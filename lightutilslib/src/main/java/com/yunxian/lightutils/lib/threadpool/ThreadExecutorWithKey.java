package com.yunxian.lightutils.lib.threadpool;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.yunxian.lightutils.lib.constants.LightUtilsConstants.DEFAULT_THREADPOOL_SIZE;

/**
 * Created by A Shuai on 2015/5/2.
 * 带有任务ID的线程池，相同ID的任务只会在线程池中存在一份
 */
public final class ThreadExecutorWithKey {

    private final Object lock;

    /* 线程池 */
    private final ExecutorService taskDistributor;
    /* 当前在线程池中的任务 */
    private final Map<String, Object> currentTastInThreadPool;

    public ThreadExecutorWithKey() {
        this(DEFAULT_THREADPOOL_SIZE);
    }

    public ThreadExecutorWithKey(int mSize) {
        lock = new Object();

        taskDistributor = Executors.newFixedThreadPool(mSize);
        currentTastInThreadPool = new HashMap<>();
    }

    /**
     * 向线程池中提交一个任务
     *
     * @param mTaskName 任务名需唯一，此唯一性需要由客户端保证
     * @param mRunnable 任务
     * @return true 表示接受此任务;false 表示线程池中已有相同的任务
     */
    public boolean execute(String mTaskName, Runnable mRunnable) {
        if (currentTastInThreadPool.containsKey(mTaskName)) {
            return false;
        }
        synchronized (lock) {
            if (currentTastInThreadPool.containsKey(mTaskName)) {
                return false;
            }
            currentTastInThreadPool.put(mTaskName, new Object());
        }
        taskDistributor.execute(mRunnable);
        return true;
    }

    /**
     * 因客户端维持的Runnable任务已完成，从线程池的任务池中移除指定的任务名
     * 以便客户端可以继续添加类似的任务
     *
     * @param mTaskName 任务名
     */
    public void removeTask(String mTaskName) {
        synchronized (lock) {
            if (taskDistributor.isShutdown()) {
                return;
            }
            currentTastInThreadPool.remove(mTaskName);
        }
    }

    /**
     * 关闭线程池
     */
    public void shutdown() {
        taskDistributor.shutdown();
        currentTastInThreadPool.clear();
    }

}
