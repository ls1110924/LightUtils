package com.yunxian.lightutils.lib.absutils;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;

import java.lang.ref.SoftReference;

/**
 * Created by A Shuai on 2015/5/2.
 * 适用于Fragment的Handler的抽象模板类
 */
public abstract class AbsFragmentHandler<T extends Fragment> extends Handler {

    private final SoftReference<T> mFragmentRef;

    public AbsFragmentHandler(T mFragment) {
        mFragmentRef = new SoftReference<T>(mFragment);
    }

    /**
     * 不许覆写，若对需对消息处理可对{@link #handleMessage(Fragment, Message, Bundle)}进行覆写
     *
     * @param msg Message消息对象
     */
    @Override
    public final void handleMessage(Message msg) {
        T mFragment = mFragmentRef.get();
        if (mFragment == null) {
            return;
        }
        handleMessage(mFragment, msg, msg.getData());
    }

    /**
     * 主要的消息处理逻辑
     *
     * @param mFragment 类型参数T所指定的Fragment对象
     * @param msg       Message消息对象
     * @param mBundle   可以为null
     */
    protected abstract void handleMessage(T mFragment, Message msg, Bundle mBundle);


}
