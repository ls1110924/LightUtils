package com.cqu.lightutils;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import java.lang.ref.WeakReference;

/**
 * Created by A Shuai on 2015/4/29.
 * 子类如果作为Activity的内部类，则需使用静态内部类，
 * 覆写onMessageExecute方法进行处理消息即可
 */
public abstract class AbsHandler<T extends Activity> extends Handler {

    private final WeakReference<T> mActivityRef;

    public AbsHandler(T mActivity) {
        mActivityRef = new WeakReference<T>(mActivity);
    }

    @Override
    public final void handleMessage(Message msg) {
        T mActivity = mActivityRef.get();
        if (mActivity == null) {
            return;
        }
        handleMessage(mActivity, msg, msg.getData());
    }

    /**
     * 主要的消息处理逻辑
     *
     * @param mActivity
     * @param msg
     * @param mBundle   可以为null
     */
    protected abstract void handleMessage(T mActivity, Message msg, Bundle mBundle);

}
