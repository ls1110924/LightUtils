package com.yunxian.lightutils.sample.fragment;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pnikosis.materialishprogress.ProgressWheel;
import com.yunxian.lightutils.lib.fragment.BaseLazyAsynLoadFragment;
import com.yunxian.lightutils.sample.R;

/**
 * Created by A Shuai on 2015/5/4.
 */
public class LazyFourFragment extends BaseLazyAsynLoadFragment {

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
        return inflater.inflate(R.layout.fragment_lazy_four_content, container, false);
    }

    @Override
    protected void onFindViews(View mRootView) {
        mContentTxt = findView(mRootView, R.id.fragment_lazy_four_count);
    }

    @Override
    protected void onBindContent() {
        mContentTxt.setText("延迟异步加载完成");
    }

    @Override
    protected boolean onLoadedDataByAsyn() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return true;
    }
}
