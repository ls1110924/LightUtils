package com.cqu.lightutils.sample.fragment;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cqu.lightutils.fragment.BaseLazySyncLoadFragment;
import com.cqu.lightutils.sample.R;
import com.pnikosis.materialishprogress.ProgressWheel;

/**
 * Created by A Shuai on 2015/5/4.
 */
public class LazyThreeFragment extends BaseLazySyncLoadFragment {

    private ProgressWheel mLoadProgressBar;

    private TextView mContentTxt;

    int mColorArray[] = new int[5];

    int count = 0;

    @Override
    protected void onInitParameter() {
        super.onInitParameter();
        Resources mResources = mContext.getResources();
        mColorArray[0] = mResources.getColor(R.color.holo_blue_dark);
        mColorArray[1] = mResources.getColor(R.color.holo_yellow_dark);
        mColorArray[2] = mResources.getColor(R.color.holo_green_dark);
        mColorArray[3] = mResources.getColor(R.color.holo_purple_dark);
        mColorArray[4] = mResources.getColor(R.color.holo_red_dark);

    }

    @Override
    protected View onInflaterRootLoadingView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_lazy_three_loading, container, false);
    }

    @Override
    protected void onFindLoadingViews(View mRootLoadingView) {
        mLoadProgressBar = findView(mRootLoadingView, R.id.fragment_lazy_three_loading_progressbar);
    }

    @Override
    protected void onBindLoadingContent() {
        count = count % 5;
        mLoadProgressBar.setBarColor(mColorArray[count++]);
    }

    @Override
    protected View onInflaterRootView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_lazy_three_content, container, false);
    }

    @Override
    protected void onFindViews(View mRootView) {
        mContentTxt = findView(mRootView, R.id.fragment_lazy_three_count);
    }

    @Override
    protected void onBindContent() {
        mContentTxt.setText("延迟同步加载完成");
    }




}
