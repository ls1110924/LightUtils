package com.cqu.lightutils.service;

import android.content.Intent;
import android.os.Bundle;

import com.cqu.lightutils.bean.AbsGeneralTaskParameter;
import com.cqu.lightutils.threadpool.ThreadExecutorWithKey;

import static com.cqu.lightutils.constants.LightUtilsConstants.KEY_REFRESH_GENERALDATA_TASKNAME;
import static com.cqu.lightutils.constants.LightUtilsConstants.KEY_REFRESH_GENERALDATA_PARAMETER;

/**
 * Created by A Shuai on 2015/5/3.
 * 配合{@link com.cqu.lightutils.bean.AbsGeneralTaskParameter}任务参数类，
 * {@link com.cqu.lightutils.task.AbsGeneralExecutorTask}带任务ID的线程池任务类使用
 */
public abstract class AbsServiceWithExecutor extends AbsService {

    private ThreadExecutorWithKey mExecutor;

    @Override
    public void onCreate() {
        super.onCreate();
        mExecutor = new ThreadExecutorWithKey();
    }

    @Override
    protected void onStartCommand(Intent intent, Bundle mBundle, int flags, int startId) {
        String mTaskName = mBundle.getString(KEY_REFRESH_GENERALDATA_TASKNAME);
        AbsGeneralTaskParameter mPara = mBundle.getParcelable(KEY_REFRESH_GENERALDATA_PARAMETER);
        dispatchTask(mTaskName, mPara);
    }

    protected abstract void dispatchTask(String mTaskName, AbsGeneralTaskParameter mPara);

    @Override
    public void onDestroy() {
        super.onDestroy();
        mExecutor.shutdown();
        mExecutor = null;
    }
}
