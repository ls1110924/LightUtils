package com.yunxian.lightutils.sample.adapter.multiitem;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yunxian.lightutils.lib.adapter.multiitemtype.AbsViewProvider;
import com.yunxian.lightutils.sample.R;
import com.yunxian.lightutils.sample.adapter.viewholder.GeneralItemViewHolder;
import com.yunxian.lightutils.sample.listener.OnCommonListener;

/**
 * Created by A Shuai on 2015/5/4.
 */
public class GeneralViewProvider extends AbsViewProvider<GeneralItemViewHolder, GeneralItemBean, OnCommonListener> {

    @Override
    protected void bindContent(int position, GeneralItemViewHolder mViewHolder, GeneralItemBean mItemBean, OnCommonListener mGeneralListener) {
        mViewHolder.txt.setText(mItemBean.getOption());
    }

    @Override
    protected GeneralItemViewHolder buildViewHolder() {
        return new GeneralItemViewHolder();
    }

    @Override
    protected void findViews(View convertView, GeneralItemViewHolder mViewHolder) {
        mViewHolder.txt = (TextView) convertView.findViewById(R.id.item_activity_listview_general_text);
    }

    @Override
    protected View inflaterView(LayoutInflater mInflater, ViewGroup parent) {
        return mInflater.inflate(R.layout.item_activity_listview_general, parent, false);
    }


}
