package com.cqu.lightutils.task;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;

import com.cqu.lightutils.threadpool.ThreadExecutorWithKey;

import java.lang.ref.SoftReference;

/**
 * Created by A Shuai on 2015/5/2.
 * 用于配合{@link com.cqu.lightutils.service.AbsServiceWithExecutor}使用，所实现的任务继承本类进行自定义实现即可
 * 本类适用于无需网络访问，且耗时的任务
 */
public abstract class AbsExecutorTask implements Runnable {

    private final SoftReference<Context> mContextRef;
    private final String mTaskName;
    protected final ThreadExecutorWithKey mExecutor;

    protected final LocalBroadcastManager mBroadcastManager;

    protected AbsExecutorTask(Context mContext, String mTaskName, ThreadExecutorWithKey mExecutor) {
        mContextRef = new SoftReference<Context>(mContext.getApplicationContext());
        this.mTaskName = mTaskName;
        this.mExecutor = mExecutor;

        mBroadcastManager = LocalBroadcastManager.getInstance(mContext);
    }

    /**
     * 不允许子类覆写run方法，保证任务结束后一定能从线程池中移除当前任务
     */
    @Override
    public final void run() {
        try{
            execute();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            removeTask();
        }
    }

    /**
     * 子类对此覆写，实现自定义任务
     */
    protected abstract void execute();

    /**
     * 从线程池中移除当前任务
     */
    private final void removeTask() {
        mExecutor.removeTask(mTaskName);
    }

    /**
     * 用于子类获取一个Context对象
     *
     * @return 有可能返回空，使用前需进行判断
     */
    protected final Context getContext(){
        return mContextRef.get();
    }

    /**
     * 发送普通广播
     *
     * @param mAction
     * @param mBundle
     */
    protected final void sendBroadcast(String mAction, Bundle mBundle) {
        Context mContext = mContextRef.get();
        if (mContext == null)
            return;
        Intent mIntent = new Intent(mAction);
        if (mBundle != null) {
            mIntent.putExtras(mBundle);
        }
        mContext.sendBroadcast(mIntent);
    }

    /**
     * 发送本地广播
     *
     * @param mAction
     * @param mBundle
     */
    protected final void sendLocalBroadcast(String mAction, Bundle mBundle) {
        Intent mIntent = new Intent(mAction);
        if (mBundle != null) {
            mIntent.putExtras(mBundle);
        }
        mBroadcastManager.sendBroadcast(mIntent);
    }

}
