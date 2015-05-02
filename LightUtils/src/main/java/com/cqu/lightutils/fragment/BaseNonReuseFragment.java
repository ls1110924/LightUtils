package com.cqu.lightutils.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by A Shuai on 2015/4/30.
 */
public abstract class BaseNonReuseFragment extends BaseFragment {

    @Override
    public final View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View mRootView = onInflaterRootView(inflater, container, savedInstanceState);
        if (mRootView == null) {
            throw new NullPointerException("the root view should not be null");
        }

        onFindViews(mRootView);

        onBindContent();

        return mRootView;
    }
}
