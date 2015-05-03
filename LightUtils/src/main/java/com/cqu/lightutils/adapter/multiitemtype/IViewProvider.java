package com.cqu.lightutils.adapter.multiitemtype;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by A Shuai on 2015/5/2.
 * 用于一种布局类型的实现，有几种类型的item，就实现几个类型的本接口
 */
public interface IViewProvider<T extends IItemBean, P extends OnGeneralListener> {

    /**
     * 专用于一种布局类型的处理
     *
     * @param position
     * @param convertView
     * @param parent
     * @param mInflater
     * @param mItemBean
     * @param mGeneralListener 用户可根据自己需求自定义一个超级接口，
     *                         该超级接口务必也继承{@link com.cqu.lightutils.adapter.multiitemtype.OnGeneralListener}接口
     * @return 不可返回空
     */
    public abstract View getItemView(int position, View convertView, ViewGroup parent,
                                     LayoutInflater mInflater, T mItemBean, P mGeneralListener);

}
