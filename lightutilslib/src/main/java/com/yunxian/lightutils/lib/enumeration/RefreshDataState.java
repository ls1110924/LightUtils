package com.yunxian.lightutils.lib.enumeration;

/**
 * Created by A Shuai on 2015/5/4.
 * 带缓存的API数据刷新状态枚举，用于配合{@link com.yunxian.lightutils.lib.task.AbsGeneralExecutorTask}常规API数据刷新任务
 */
public enum RefreshDataState {

    /**
     * 在线加载成功
     */
    SUCCESS,

    /**
     * 在线加载，网络错误，一般为无网络
     */
    NER_ERROR,

    /**
     * 在线加载，服务器错误，有网络
     */
    SERVER_ERROR,

    /**
     * 离线加载成功
     */
    SUCCESS_OFFLINE,

    /**
     * 离线加载失败
     */
    FAIL_OFFLINE,

    /**
     * 未知异常，一般为数据格式非法，解析错误
     */
    UNKNOW_ERROR


}
