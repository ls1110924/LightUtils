package com.yunxian.lightutils.lib.utils;

/**
 * Created by A Shuai on 2015/5/3.
 * 通用未捕获异常Handler，为单例类，通过{@link UncaughtExceptionHandlerUtil#getInstance()}方法获取单例对象引用
 */
public class UncaughtExceptionHandlerUtil implements Thread.UncaughtExceptionHandler {

    private static UncaughtExceptionHandlerUtil mInstance;

    public static UncaughtExceptionHandlerUtil getInstance() {
        if (mInstance == null) {
            synchronized (UncaughtExceptionHandlerUtil.class) {
                if (mInstance == null) {
                    mInstance = new UncaughtExceptionHandlerUtil();
                }
            }
        }
        return mInstance;
    }

    private UncaughtExceptionHandlerUtil() {
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {

        ex.printStackTrace();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        android.os.Process.killProcess(android.os.Process.myPid());

    }
}
