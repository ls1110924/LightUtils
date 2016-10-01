package com.yunxian.lightutils.lib.adapter.multiitemtype;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by A Shuai on 2015/5/2.
 * 用于一种布局类型的实现，有几种类型的item，就实现几个类型的本接口
 */
public interface IViewProvider<P extends IItemBean, Q extends OnGeneralListener> {

    /**
     * 专用于一种布局类型的处理
     *
     * @param position         当前正在填充的item的view的坐标
     * @param convertView      可被复用的内容视图
     * @param parent           父容器
     * @param mInflater        初始化layout文件的Inflater
     * @param mItemBean        填充当前类型item的数据bean
     * @param mGeneralListener 用户可根据自己需求自定义一个超级接口，
     *                         该超级接口务必也继承{@link com.cqu.lightutils.adapter.multiitemtype.OnGeneralListener}接口
     * @return 不可返回空
     */
    View getItemView(int position, View convertView, ViewGroup parent,
                     LayoutInflater mInflater, P mItemBean, Q mGeneralListener);

}
