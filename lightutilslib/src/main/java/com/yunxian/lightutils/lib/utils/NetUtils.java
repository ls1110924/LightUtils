package com.yunxian.lightutils.lib.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by A Shuai on 2015/5/3.
 * 网络工具类
 */
public final class NetUtils {

    private NetUtils() {
    }

    /**
     * 检查网络连接状态
     *
     * @param mContext 上下文
     * @return 返回是否网络可用
     */
    public static boolean getNetworkState(Context mContext) {

        ConnectivityManager conManager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = conManager.getActiveNetworkInfo();

        if (networkInfo != null) {
            return networkInfo.isAvailable();
        }
        return false;
    }

}
