package com.cqu.lightutils.service;

import android.app.Service;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;

/**
 * Created by A Shuai on 2015/5/3.
 * 抽象服务基类
 */
public abstract class AbsService extends Service {

    /**
     * 本地广播管理器
     */
    protected LocalBroadcastManager mBroadcastManager;

    /**
     * 数据库Cursor游标对象
     */
    protected Cursor mCursor;

    @Override
    public void onCreate() {
        super.onCreate();
        mBroadcastManager = LocalBroadcastManager.getInstance(this);
    }

    @Override
    public final int onStartCommand(Intent intent, int flags, int startId) {
        onStartCommand(intent, intent.getExtras(), flags, startId);
        return START_REDELIVER_INTENT;
    }

    protected abstract void onStartCommand(Intent intent, Bundle mBundle, int flags, int startId);

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    /**
     * 发送普通广播
     *
     * @param mAction 广播动作
     * @param mBundle 待传递的Bundle数据
     */
    protected final void sendBroadcast(String mAction, Bundle mBundle) {
        Intent mIntent = new Intent(mAction);
        if (mBundle != null)
            mIntent.putExtras(mBundle);
        sendBroadcast(mIntent);
    }

    /**
     * 发送本地广播，仅用于应用程序内通信，不可以夸应用发送本地广播
     *
     * @param mAction 广播动作
     * @param mBundle 待传递的Bundle数据
     */
    protected final void sendLocalBroadcast(String mAction, Bundle mBundle) {
        Intent mIntent = new Intent(mAction);
        if (mBundle != null)
            mIntent.putExtras(mBundle);
        mBroadcastManager.sendBroadcast(mIntent);
    }

    /**
     * 关闭数据库游标
     * 子类使用完毕游标集后，请务必及时关闭，以免造成不必要的OOM问题
     */
    protected final void closeCursor() {
        if (mCursor != null) {
            mCursor.close();
            mCursor = null;
        }
    }

}
