package com.cqu.lightutils.sample.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.cqu.lightutils.fragment.BaseDynamicFragment;
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

    @Override
    public void onCreateImpl(Bundle savedInstanceState) {

    }

    @Override
    protected void onInitParameter() {
        mCommonListener = new CommonCallbackListener();

        mListDataSet = new MainDrawerDataSet();
        mListDataSet.addItem("SingleItemListTest");
        mListDataSet.addItem("MultiItemListTest");
        mListAdapter = new MainDrawerFragmentListAdapter(mContext, mListDataSet);
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

    public String getTitle(){
        if (mListDataSet==null||mListDataSet.size()==0){
            return null;
        }
        return mListDataSet.getIndexItem(mListDataSet.getSelected());
    }


    private class CommonCallbackListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            switch (position) {
                case 0:
                    break;
                case 1:
                    break;
                default:
                    break;
            }
        }
    }


}
