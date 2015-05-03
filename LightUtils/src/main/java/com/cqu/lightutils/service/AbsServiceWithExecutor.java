package com.cqu.lightutils.service;

import android.content.Intent;

import com.cqu.lightutils.threadpool.ThreadExecutorWithKey;

/**
 * Created by A Shuai on 2015/5/3.
 */
public class AbsServiceWithExecutor extends AbsService {

    private ThreadExecutorWithKey mExecutor;

    @Override
    public void onCreate() {
        super.onCreate();
        mExecutor = new ThreadExecutorWithKey();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mExecutor.shutdown();
        mExecutor = null;
    }
}
