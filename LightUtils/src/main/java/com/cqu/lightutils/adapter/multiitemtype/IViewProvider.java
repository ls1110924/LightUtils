package com.cqu.lightutils.adapter.multiitemtype;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by A Shuai on 2015/5/2.
 */
public interface IViewProvider<T extends IItemBean, P extends OnGeneralListener> {

    public abstract View getItemView(int position, View convertView, ViewGroup parent,
                                     LayoutInflater mInflater, T mData, P mGeneralListener);

}
