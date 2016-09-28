package com.yunxian.lightutils.lib.custominterface;

/**
 * Created by A Shuai on 2015/6/1.
 * 供执行Task任务的Service所调度任务使用
 */
public interface TaskInterface {

    /**
     * 返回一个Task的任务名
     *
     * @return 任务名字符串
     */
    String getName();

}
