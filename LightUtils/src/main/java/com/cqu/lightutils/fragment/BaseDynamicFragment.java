package com.cqu.lightutils.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by A Shuai on 2015/4/30.
 */
public abstract class BaseDynamicFragment extends BaseFragment {

    //此Fragment提供的视图的根视图
    private View mRootView;

    @Override
    public final View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mRootView==null){
            mRootView = onInflaterRootView(inflater,container,savedInstanceState);
            if (mRootView==null){
                throw new NullPointerException("the root view should not be null");
            }

            onFindViews(mRootView);
        } else {
            ViewGroup mRootParent = (ViewGroup)mRootView.getParent();
            if (mRootParent!=null){
                mRootParent.removeView(mRootView);
            }
        }

        onBindContent();

        return mRootView;
    }
}
