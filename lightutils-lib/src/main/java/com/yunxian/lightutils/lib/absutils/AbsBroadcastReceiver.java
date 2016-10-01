package com.yunxian.lightutils.lib.absutils;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.lang.ref.SoftReference;

/**
 * Created by A Shuai on 2015/4/29.
 * 子类若为Activity的内部类，需作为静态内部类。如果不是静态内部类则记得取消广播注册器
 *
 * @param <T> 类型参数T需至少为Activity的子类
 */
public abstract class AbsBroadcastReceiver<T extends Activity> extends BroadcastReceiver {

    private final SoftReference<T> mActivityRef;

    public AbsBroadcastReceiver(T mActivity) {
        super();
        mActivityRef = new SoftReference<>(mActivity);
    }

    /**
     * 子类不可覆写这个方法
     *
     * @param context 上下文
     * @param intent  广播的Intent
     */
    @Override
    public final void onReceive(Context context, Intent intent) {
        T mActivity = mActivityRef.get();
        if (mActivity == null)
            return;
        onReceive(mActivity, intent, intent.getExtras());
    }

    /**
     * 子类需覆写这个方法对广播进行处理
     *
     * @param mActivity 类型参数T所指定的Activity
     * @param intent    广播的Intent
     * @param mBundle   可为null
     */
    public abstract void onReceive(T mActivity, Intent intent, Bundle mBundle);

}
