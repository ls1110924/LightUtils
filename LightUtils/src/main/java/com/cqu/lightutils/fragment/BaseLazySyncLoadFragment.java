package com.cqu.lightutils.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/**
 * Created by A Shuai on 2015/4/30.
 */
public abstract class BaseLazySyncLoadFragment extends BaseFragment {

    //由子类Fragment所提供的视图的根视图
    private View mRootView;
    //当前Fragment为了实现懒加载，即当此Fragment完全可见的时候才会加载内容
    private FrameLayout mRootContainer;

    //分别表示当前Fragment是否可见和是否已准备
    private boolean isVisible;
    private boolean isPrepared;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        isVisible = isVisibleToUser;
        if (isVisible) {

        } else {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mRootView==null){

        } else {

        }

        return mRootView;
    }
}
