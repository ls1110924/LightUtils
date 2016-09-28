package com.yunxian.lightutils.sample.recycleadapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yunxian.lightutils.sample.R;

import java.util.List;

/**
 * Created by A Shuai on 2016/1/6.
 */
public class MarerialSingleItemAdapter extends RecyclerView.Adapter<MarerialSingleItemAdapter.ViewHolder> {

    private final LayoutInflater mInflater;
    private final List<String> mDataSet;

    public MarerialSingleItemAdapter(Context mContext, @NonNull List<String> mDataSet) {
        mInflater = LayoutInflater.from(mContext);
        this.mDataSet = mDataSet;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mInflater.inflate(R.layout.item_activity_listview_general, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTextView.setText(mDataSet.get(position));
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mTextView;

        public ViewHolder(View itemView) {
            super(itemView);

            mTextView = (TextView) itemView.findViewById(R.id.item_activity_listview_general_text);
        }
    }

}
