package com.yunxian.lightutils.lib.constants;

/**
 * Created by A Shuai on 2015/5/3.
 * 常量类
 */
public final class LightUtilsConstants {

    private LightUtilsConstants() {
    }

    /**
     * 无效值
     */
    public static final int INVALID_VALUE = -1;

    /**
     * 默认线程池大小
     */
    public static final int DEFAULT_THREADPOOL_SIZE = 3;

    /**
     * 网络操作时的超时时间
     */
    public static final int DEFAULT_TIMEOUT = 20000;

    /**
     * 常规数据刷新任务后台广播Action
     */
    public static final String ACTION_BROADCAST_REFRESHGENERALDATA = "com.cqu.lightutils.refreshgeneraldata";

    /**
     * 启动刷新常规数据Service的任务类型
     */
    public static final String KEY_REFRESH_GENERALDATA_TASKNAME = "RefreshGeneralDataTaskType";
    /**
     * 启动刷新常规数据Service时携带的参数键值
     */
    public static final String KEY_REFRESH_GENERALDATA_PARAMETER = "RefreshGeneralDataParameter";
    /**
     * 刷新常规数据任务结束后的结果状态
     */
    public static final String KEY_REFRESH_GENERALDATA_RESULTSTATE = "RefreshGeneralDataResultState";

}
