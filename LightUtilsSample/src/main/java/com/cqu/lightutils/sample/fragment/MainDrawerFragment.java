package com.cqu.lightutils.sample.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.cqu.lightutils.absutils.AbsFragmentHandler;
import com.cqu.lightutils.fragment.BaseDynamicFragment;
import com.cqu.lightutils.listener.OnNavigationItemSelectedListener;
import com.cqu.lightutils.sample.R;
import com.cqu.lightutils.sample.adapter.MainDrawerFragmentListAdapter;
import com.cqu.lightutils.sample.dataset.MainDrawerDataSet;

/**
 * Created by A Shuai on 2015/5/4.
 */
public class MainDrawerFragment extends BaseDynamicFragment {

    private ListView mListView;
    private MainDrawerFragmentListAdapter mListAdapter;
    private MainDrawerDataSet mListDataSet;

    private CommonCallbackListener mCommonListener;

    private DrawerFragHandler mHandler;

    private OnNavigationItemSelectedListener mNavigationListener;

    @Override
    public void onCreateImpl(Bundle savedInstanceState) {

    }

    @Override
    protected void onInitParameter() {
        mCommonListener = new CommonCallbackListener();

        mListDataSet = new MainDrawerDataSet();
        mListDataSet.addItem("MaterialDrawerTest");
        mListDataSet.addItem("SingleItemListTest");
        mListDataSet.addItem("MultiItemListTest");
        mListDataSet.addItem("SwitchThemeTest");
        mListDataSet.addItem("LazyFragmentTest");
        mListAdapter = new MainDrawerFragmentListAdapter(mContext, mListDataSet);

        mHandler = new DrawerFragHandler(this);
    }

    @Override
    protected View onInflaterRootView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main_drawer, container, false);
    }

    @Override
    protected void onFindViews(View mRootView) {
        mListView = findView(mRootView, R.id.fragment_main_drawer_list);
    }

    @Override
    protected void onBindContent() {
        mListView.setAdapter(mListAdapter);
        mListView.setOnItemClickListener(mCommonListener);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context != null && context instanceof OnNavigationItemSelectedListener) {
            mNavigationListener = (OnNavigationItemSelectedListener) context;
        } else {
            mNavigationListener = null;
        }

    }

    @Override
    public void onDetach() {
        super.onDetach();

        mNavigationListener = null;
    }

    public String getTitle() {
        if (mListDataSet == null || mListDataSet.size() == 0) {
            return null;
        }
        return mListDataSet.getIndexItem(mListDataSet.getSelected());
    }


    private class CommonCallbackListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
            if (mNavigationListener != null) {
                mNavigationListener.onNavigationItemSelected(position);
            }
        }
    }

    private static class DrawerFragHandler extends AbsFragmentHandler<MainDrawerFragment> {

        public DrawerFragHandler(MainDrawerFragment mFragment) {
            super(mFragment);
        }

        @Override
        protected void handleMessage(MainDrawerFragment mFragment, Message msg, Bundle mBundle) {

        }
    }


}
