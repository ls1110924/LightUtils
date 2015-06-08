package com.cqu.lightutils.sample.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cqu.lightutils.fragment.BaseDynamicFragment;
import com.cqu.lightutils.sample.R;

/**
 * Created by A Shuai on 2015/5/4.
 */
public class LazyOneFragment extends BaseDynamicFragment {

    private TextView mCountTxt;

    private int mCount = 0;

    @Override
    protected void onInitParameter() {

    }

    @Override
    protected View onInflaterRootView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_lazy_one, container, false);
    }

    @Override
    protected void onFindViews(View mRootView) {
        mCountTxt = findView(mRootView, R.id.fragment_lazy_one_count);
    }

    @Override
    protected void onBindContent() {
        mCount++;
        mCountTxt.setText("Count-->" + mCount);
    }
}
