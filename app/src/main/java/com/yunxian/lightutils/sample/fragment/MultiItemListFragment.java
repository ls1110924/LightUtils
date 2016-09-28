package com.yunxian.lightutils.sample.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ListView;

import com.yunxian.lightutils.lib.adapter.multiitemtype.IItemBean;
import com.yunxian.lightutils.lib.adapter.multiitemtype.IViewProvider;
import com.yunxian.lightutils.lib.fragment.BaseDynamicFragment;
import com.yunxian.lightutils.sample.R;
import com.yunxian.lightutils.sample.adapter.MainMultiItemFragListAdapter;
import com.yunxian.lightutils.sample.adapter.multiitem.GeneralItemBean;
import com.yunxian.lightutils.sample.adapter.multiitem.GeneralViewProvider;
import com.yunxian.lightutils.sample.adapter.multiitem.SwitchButtonItemBean;
import com.yunxian.lightutils.sample.adapter.multiitem.SwitchButtonViewProvider;
import com.yunxian.lightutils.sample.listener.OnCommonListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by A Shuai on 2015/5/4.
 */
public class MultiItemListFragment extends BaseDynamicFragment {

    private ListView mListView;
    private MainMultiItemFragListAdapter mListAdapter;

    private List<IItemBean> mOptionDataSet = null;
    private List<Class<? extends IViewProvider>> mProvidersList = null;

    private CommonCallbackListener mCommonListener;

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
        mCommonListener = new CommonCallbackListener();

        mOptionDataSet = new ArrayList<IItemBean>();
        mProvidersList = new ArrayList<Class<? extends IViewProvider>>();

        mOptionDataSet.add(new SwitchButtonItemBean("Message 1", true));
        mOptionDataSet.add(new SwitchButtonItemBean("Message 2", false));
        mOptionDataSet.add(new GeneralItemBean("Message 3"));
        mOptionDataSet.add(new SwitchButtonItemBean("Message 4", true));
        mOptionDataSet.add(new GeneralItemBean("Message 5"));
        mOptionDataSet.add(new GeneralItemBean("Message 6"));
        mOptionDataSet.add(new GeneralItemBean("Message 7"));
        mOptionDataSet.add(new SwitchButtonItemBean("Message 8", true));
        mOptionDataSet.add(new SwitchButtonItemBean("Message 9", false));
        mOptionDataSet.add(new GeneralItemBean("Message 10"));
        mOptionDataSet.add(new GeneralItemBean("Message 11"));
        mOptionDataSet.add(new GeneralItemBean("Message 12"));
        mOptionDataSet.add(new SwitchButtonItemBean("Message 13", true));
        mOptionDataSet.add(new SwitchButtonItemBean("Message 14", false));
        mOptionDataSet.add(new GeneralItemBean("Message 15"));
        mOptionDataSet.add(new GeneralItemBean("Message 16"));
        mOptionDataSet.add(new GeneralItemBean("Message 17"));
        mOptionDataSet.add(new SwitchButtonItemBean("Message 18", true));
        mOptionDataSet.add(new SwitchButtonItemBean("Message 19", false));
        mOptionDataSet.add(new GeneralItemBean("Message 20"));

        mProvidersList.add(SwitchButtonViewProvider.class);
        mProvidersList.add(GeneralViewProvider.class);

        mListAdapter = new MainMultiItemFragListAdapter(mContext, mCommonListener, mOptionDataSet, mProvidersList);
    }

    private class CommonCallbackListener implements OnCommonListener {

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        }

        @Override
        public void onClick(View v) {

        }
    }

}
