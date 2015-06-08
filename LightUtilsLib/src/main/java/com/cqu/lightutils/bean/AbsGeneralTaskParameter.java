package com.cqu.lightutils.bean;

import android.os.Parcelable;

/**
 * Created by A Shuai on 2015/5/4.
 * 传递启动到Service中的任务参数，子类可自行覆写，添加任务特有的类型
 * 采用Parcelable进行序列化，可有效提升效率
 */
public abstract class AbsGeneralTaskParameter implements Parcelable {
}
