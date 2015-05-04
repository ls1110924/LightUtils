package com.cqu.lightutils.sample.fragment;

import android.os.Bundle;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.cqu.lightutils.absutils.AbsFragmentHandler;
import com.cqu.lightutils.fragment.BaseDynamicFragment;
import com.cqu.lightutils.sample.LazyFragmentActivity;
import com.cqu.lightutils.sample.MainActivity;
import com.cqu.lightutils.sample.R;
import com.cqu.lightutils.sample.SwitchThemeActivity;
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

    @Override
    public void onCreateImpl(Bundle savedInstanceState) {

    }

    @Override
    protected void onInitParameter() {
        mCommonListener = new CommonCallbackListener();

        mListDataSet = new MainDrawerDataSet();
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

    public String getTitle() {
        if (mListDataSet == null || mListDataSet.size() == 0) {
            return null;
        }
        return mListDataSet.getIndexItem(mListDataSet.getSelected());
    }


    private class CommonCallbackListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
            switch (position) {
                case 0:
                case 1:
                    mListAdapter.notifyDataSetChanged();
                    mListDataSet.setSelected(position);
                    ((MainActivity) getActivity()).onDrawerItemSelected(position);
                    break;
                case 2:
                    startNewActivity(SwitchThemeActivity.class, R.anim.activity_slide_right_in, R.anim.activity_slide_left_out_part, false, null);
                    break;
                case 3:
                    startNewActivity(LazyFragmentActivity.class, R.anim.activity_slide_right_in, R.anim.activity_slide_left_out_part, false, null);
                default:
                    break;
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
