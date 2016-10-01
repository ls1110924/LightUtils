package com.yunxian.lightutils.sample.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yunxian.lightutils.lib.fragment.BaseStaticFragment;
import com.yunxian.lightutils.sample.R;

/**
 * Created by A Shuai on 2015/5/4.
 */
public class LazyTwoFragment extends BaseStaticFragment {

    @Override
    protected void onInitParameter() {
        super.onInitParameter();
    }

    @Override
    protected View onInflaterRootView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_lazy_two, container, false);
    }

    @Override
    protected void onBindContent() {

    }

    @Override
    protected void onFindViews(View mRootView) {

    }
}
