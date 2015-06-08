package com.cqu.lightutils.sample.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.cqu.lightutils.fragment.BaseDynamicFragment;
import com.cqu.lightutils.sample.R;
import com.cqu.lightutils.sample.adapter.MainSingleItemFragListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by A Shuai on 2015/5/4.
 */
public class SingleItemListFragment extends BaseDynamicFragment {

    private ListView mListView;
    private List<String> mListDataSet;
    private MainSingleItemFragListAdapter mListAdapter;

    @Override
    protected void onBindContent() {
        mListView.setAdapter(mListAdapter);
    }

    @Override
    protected void onFindViews(View mRootView) {
        mListView = findView(mRootView, R.id.fragment_list_list);
    }

    @Override
    protected View onInflaterRootView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    protected void onInitParameter() {

        mListDataSet = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            mListDataSet.add("Item-->" + i);
        }
        mListAdapter = new MainSingleItemFragListAdapter(mContext, mListDataSet);

    }
}
