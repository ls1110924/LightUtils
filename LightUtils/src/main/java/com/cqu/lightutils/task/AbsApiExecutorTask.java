package com.cqu.lightutils.task;

import android.content.Context;

import com.cqu.lightutils.threadpool.ThreadExecutorWithKey;

/**
 * Created by A Shuai on 2015/5/3.
 */
public abstract class AbsApiExecutorTask extends AbsExecutorTask{

    protected AbsApiExecutorTask(Context mContext, String mTaskName, ThreadExecutorWithKey mExecutor) {
        super(mContext, mTaskName, mExecutor);
    }

    @Override
    protected void execute() {

    }
}
