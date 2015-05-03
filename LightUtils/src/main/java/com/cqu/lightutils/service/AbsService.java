package com.cqu.lightutils.service;

import android.app.Service;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;

/**
 * Created by A Shuai on 2015/5/3.
 */
public abstract class AbsService extends Service {

    protected LocalBroadcastManager mBroadcastManager;


    protected Cursor mCursor;

    @Override
    public void onCreate() {
        super.onCreate();
        mBroadcastManager = LocalBroadcastManager.getInstance(this);
    }

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
     * @param mAction
     * @param mBundle
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
     * @param mAction
     * @param mBundle
     */
    protected final void sendLocalBroadcast(String mAction, Bundle mBundle) {
        Intent mIntent = new Intent(mAction);
        if (mBundle != null)
            mIntent.putExtras(mBundle);
        mBroadcastManager.sendBroadcast(mIntent);
    }

    protected final void closeCursor() {
        if (mCursor != null) {
            mCursor.close();
            mCursor = null;
        }
    }

}
