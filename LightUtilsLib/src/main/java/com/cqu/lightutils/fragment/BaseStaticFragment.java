package com.cqu.lightutils.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by A Shuai on 2015/4/30.
 * 此Fragment适用于视图内容为静态且占用内存不会特别多的情况，
 * 所以此Fragment只会初始化一遍内容视图，下一次调用{@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}方法
 * 就会完整复用上一次的内容视图，不再重新填充内容
 */
public abstract class BaseStaticFragment extends BaseFragment {

    //此Fragment提供的视图的根视图
    private View mRootView;

    @Override
    public final View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = onInflaterRootView(inflater, container, savedInstanceState);
            if (mRootView == null) {
                throw new NullPointerException("the root view should not be null");
            }

            onFindViews(mRootView);

            onBindContent();
        } else {
            ViewGroup mRootParent = (ViewGroup) mRootView.getParent();
            if (mRootParent != null) {
                mRootParent.removeView(mRootView);
            }
        }

        return mRootView;
    }

}
