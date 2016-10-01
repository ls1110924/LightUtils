package com.cqu.lightutils.sample.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cqu.lightutils.fragment.BaseDynamicFragment;
import com.cqu.lightutils.sample.R;
import com.cqu.lightutils.sample.recycleadapter.MarerialSingleItemAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by A Shuai on 2016/1/6.
 */
public class MarerialMainListFragment extends BaseDynamicFragment{

    private List<String> mListDataSet;
    private MarerialSingleItemAdapter mAdapter;
    private RecyclerView mRecyclerView;

    @Override
    protected void onInitParameter() {
        super.onInitParameter();

        mListDataSet = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            mListDataSet.add("Item-->" + i);
        }
        mAdapter = new MarerialSingleItemAdapter(mContext, mListDataSet);
    }

    @Override
    @NonNull
    protected View onInflaterRootView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recycleview, container, false);
    }

    @Override
    protected void onFindViews(View mRootView) {
        mRecyclerView = findView(mRootView, R.id.fragment_recycleview_recycleview);
    }

    @Override
    protected void onBindContent() {
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setAdapter(mAdapter);
    }
}
