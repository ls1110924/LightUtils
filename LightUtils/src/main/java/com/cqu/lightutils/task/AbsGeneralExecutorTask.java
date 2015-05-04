package com.cqu.lightutils.task;

import android.content.Context;
import android.os.Bundle;

import com.cqu.lightutils.enumeration.RefreshDataState;
import com.cqu.lightutils.threadpool.ThreadExecutorWithKey;

import static com.cqu.lightutils.constants.LightUtilsConstants.ACTION_BROADCAST_REFRESHGENERALDATA;
import static com.cqu.lightutils.constants.LightUtilsConstants.KEY_REFRESH_GENERALDATA_RESULTSTATE;
import static com.cqu.lightutils.constants.LightUtilsConstants.KEY_REFRESH_GENERALDATA_TASKNAME;

/**
 * Created by A Shuai on 2015/5/3.
 * 常规线程池API数据刷新任务
 */
public abstract class AbsGeneralExecutorTask extends AbsExecutorTask {

    public AbsGeneralExecutorTask(Context mContext, String mTaskName, ThreadExecutorWithKey mExecutor) {
        super(mContext, mTaskName, mExecutor);
    }

    @Override
    protected final void execute() {
        RefreshDataState mResultState = getDataByNet();
        switch (mResultState) {

            case SUCCESS:
                nofityState(mResultState);
                return;
            case NER_ERROR:
            case SERVER_ERROR:
            case UNKNOW_ERROR:
                nofityState(mResultState);
                break;
            case SUCCESS_OFFLINE:
            case FAIL_OFFLINE:
                throw new IllegalStateException("the result state is illegal!");
        }

        mResultState = getDataByOffline();
        switch (mResultState) {
            case SUCCESS:
            case NER_ERROR:
            case SERVER_ERROR:
            case UNKNOW_ERROR:
                throw new IllegalStateException("the result state is illegal!");
            case SUCCESS_OFFLINE:
            case FAIL_OFFLINE:
                nofityState(mResultState);
                break;
        }

    }

    /**
     * 子类完成覆写执行从网络获取数据的处理{}
     *
     * @return 返回结果只可以是在线加载的四个状态，分别在线加载成功，在线加载网络错误，
     * 在线加载主机错误，未知异常
     */
    protected abstract RefreshDataState getDataByNet();

    /**
     * 子类完成覆写，实现加载离线数据
     *
     * @return 返回值只能为离线加载成功，离线加载失败
     */
    protected RefreshDataState getDataByOffline() {
        return RefreshDataState.FAIL_OFFLINE;
    }

    /**
     * 发送本地广播，对此事件感兴趣的Activity可对此
     *
     * @param mState
     */
    private final void nofityState(RefreshDataState mState) {
        Bundle mBundle = new Bundle();
        mBundle.putString(KEY_REFRESH_GENERALDATA_TASKNAME, mTaskName);
        mBundle.putSerializable(KEY_REFRESH_GENERALDATA_RESULTSTATE, mState);
        sendLocalBroadcast(ACTION_BROADCAST_REFRESHGENERALDATA, mBundle);
    }

}
