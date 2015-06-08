package com.cqu.lightutils.enumeration;

/**
 * Created by A Shuai on 2015/5/3.
 * 访问网络后的状态
 */
public enum AccessNetState {

    /**
     * 初始化
     */
    Initialize,

    /**
     * 访问网络并得到正确结果
     */
    Success,

    /**
     * 主机异常
     */
    ServerException,

    /**
     * 客户端异常
     */
    ClientException,

    /**
     * 网络异常，包括连接超时和DNS解析异常等，即无网络
     */
    NetException,

    /**
     * 本地IO异常
     */
    IOException,

    /**
     * 其他未知异常
     */
    Exception,

}
