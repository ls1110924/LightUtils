package com.cqu.lightutils.absutils;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;

import java.lang.ref.SoftReference;

/**
 * Created by A Shuai on 2015/5/2.
 */
public abstract class AbsFragmentHandler<T extends Fragment> extends Handler{

    private final SoftReference<T> mFragmentRef;

    public AbsFragmentHandler(T mFragment){
        mFragmentRef = new SoftReference<T>(mFragment);
    }

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
     * @param mFragment
     * @param msg
     * @param mBundle   可以为null
     */
    protected abstract void handleMessage(T mFragment, Message msg, Bundle mBundle);


}
