package com.yunxian.lightutils.lib.absutils;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import java.lang.ref.SoftReference;

/**
 * Created by A Shuai on 2015/4/29.
 * 子类如果作为Activity的内部类，则需使用静态内部类，
 * 覆写onMessageExecute方法进行处理消息即可
 *
 * @param <T> 类型参数T需至少为Activity的子类
 */
public abstract class AbsHandler<T extends Activity> extends Handler {

    private final SoftReference<T> mActivityRef;

    public AbsHandler(T mActivity) {
        mActivityRef = new SoftReference<>(mActivity);
    }

    /**
     * 不许覆写，若对需对消息处理可对{@link #handleMessage(Activity, Message, Bundle)}进行覆写
     *
     * @param msg Message消息对象
     */
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
     * @param mActivity 类型参数T所指定的Activity对象
     * @param msg       Message消息对象
     * @param mBundle   可以为null，子类使用时需注意
     */
    protected abstract void handleMessage(T mActivity, Message msg, Bundle mBundle);

}
