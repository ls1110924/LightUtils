package com.cqu.lightutils.adapter.multiitemtype;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by A Shuai on 2015/5/2.
 * 声明式接口，用于{@link IViewProvider#getItemView(int, View, ViewGroup, LayoutInflater, IItemBean, OnGeneralListener)}
 * 方法中填充回调监听器，子类可实现一个超接口，即继承或者实现多个接口类，或者任意一个类只要实现了本接口即可，不仅可以
 * 作为回调监听器，还可以传递必要的成员变量对象
 */
public interface OnGeneralListener {
}
