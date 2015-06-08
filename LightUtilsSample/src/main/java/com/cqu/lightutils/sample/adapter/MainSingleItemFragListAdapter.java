package com.cqu.lightutils.sample.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.cqu.lightutils.adapter.AbsBasicAdapter;
import com.cqu.lightutils.sample.R;
import com.cqu.lightutils.sample.adapter.viewholder.GeneralItemViewHolder;

import java.util.List;

/**
 * Created by A Shuai on 2015/5/4.
 */
public class MainSingleItemFragListAdapter extends AbsBasicAdapter<GeneralItemViewHolder> {

    private List<String> mDataSet;

    public MainSingleItemFragListAdapter(Context mContext, List<String> mDataSet) {
        super(mContext);
        this.mDataSet = mDataSet;
    }

    @Override
    protected void bindContent(int position, GeneralItemViewHolder mViewHolder) {
        mViewHolder.txt.setText(mDataSet.get(position));
    }

    @Override
    protected GeneralItemViewHolder buildViewHolder() {
        return new GeneralItemViewHolder();
    }

    @Override
    protected void findViews(View convertView, GeneralItemViewHolder mViewHolder) {
        mViewHolder.txt = findView(convertView, R.id.item_activity_listview_general_text);
    }

    @Override
    protected View inflaterView(ViewGroup parent) {
        return mInflater.inflate(R.layout.item_activity_listview_general, parent, false);
    }

    @Override
    public int getCount() {
        return mDataSet.size();
    }

    @Override
    public Object getItem(int position) {
        return mDataSet.get(position);
    }


}
