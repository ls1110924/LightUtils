package com.yunxian.lightutils.lib.adapter.viewholder;

/**
 * Created by A Shuai on 2015/5/2.
 * 此ViewHolder为抽象标识类，需子类化后才可以使用
 * 是Adapter类使用的ViewHolder类的最根类，各子类可根据情况自行定义各自特有的内容视图控件
 */
public abstract class AbsViewHolder {

    /**
     * AdapterView中单个ItemView的索引
     */
    public int index;

}
