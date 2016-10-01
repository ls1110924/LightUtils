package com.yunxian.lightutils.sample.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.yunxian.lightutils.lib.adapter.AbsBasicAdapter;
import com.yunxian.lightutils.sample.R;
import com.yunxian.lightutils.sample.adapter.viewholder.MainDrawerListViewHolder;
import com.yunxian.lightutils.sample.dataset.MainDrawerDataSet;

/**
 * Created by A Shuai on 2015/5/4.
 */
public class MainDrawerFragmentListAdapter extends AbsBasicAdapter<MainDrawerListViewHolder> {

    private final MainDrawerDataSet mDataSet;

    public MainDrawerFragmentListAdapter(Context mContext, MainDrawerDataSet mDataSet) {
        super(mContext);
        this.mDataSet = mDataSet;
    }

    @Override
    public int getCount() {
        return mDataSet.size();
    }

    @Override
    public Object getItem(int position) {
        return mDataSet.getIndexItem(position);
    }

    @Override
    protected View inflaterView(ViewGroup parent) {
        return mInflater.inflate(R.layout.item_fragment_drawer_list, parent, false);
    }

    @Override
    protected MainDrawerListViewHolder buildViewHolder() {
        return new MainDrawerListViewHolder();
    }

    @Override
    protected void findViews(View convertView, MainDrawerListViewHolder mViewHolder) {
        mViewHolder.txt = findView(convertView, R.id.item_fragment_drawer_list_txt);
        mViewHolder.img = findView(convertView, R.id.item_fragment_drawer_list_selected);
    }

    @Override
    protected void bindContent(int position, MainDrawerListViewHolder mViewHolder) {
        mViewHolder.txt.setText(mDataSet.getIndexItem(position));
        if (position == mDataSet.getSelected()) {
            mViewHolder.img.setVisibility(View.VISIBLE);
        } else {
            mViewHolder.img.setVisibility(View.INVISIBLE);
        }
    }

}
