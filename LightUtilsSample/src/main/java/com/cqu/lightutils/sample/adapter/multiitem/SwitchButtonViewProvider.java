package com.cqu.lightutils.sample.adapter.multiitem;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cqu.lightutils.adapter.multiitemtype.AbsViewProvider;
import com.cqu.lightutils.sample.R;
import com.cqu.lightutils.sample.adapter.viewholder.SwitchButtonItemViewHolder;
import com.cqu.lightutils.sample.listener.OnCommonListener;

import me.imid.view.SwitchButton;

/**
 * Created by A Shuai on 2015/5/4.
 */
public class SwitchButtonViewProvider extends AbsViewProvider<SwitchButtonItemViewHolder, SwitchButtonItemBean, OnCommonListener> {

    @Override
    protected void bindContent(int position, SwitchButtonItemViewHolder mViewHolder, SwitchButtonItemBean mItemBean, OnCommonListener mGeneralListener) {
        mViewHolder.txt.setText(mItemBean.getOption());
        mViewHolder.button.setChecked(mItemBean.isSelected());
        mViewHolder.button.setOnCheckedChangeListener(mGeneralListener);
    }

    @Override
    protected SwitchButtonItemViewHolder buildViewHolder() {
        return new SwitchButtonItemViewHolder();
    }

    @Override
    protected void findViews(View convertView, SwitchButtonItemViewHolder mViewHolder) {
        mViewHolder.txt = (TextView) convertView.findViewById(R.id.item_activity_listview_with_switchbutton_text);
        mViewHolder.button = (SwitchButton) convertView.findViewById(R.id.item_activity_listview_with_switchbutton_button);
        mViewHolder.button.setTag(mViewHolder);
    }

    @Override
    protected View inflaterView(LayoutInflater mInflater, ViewGroup parent) {
        return mInflater.inflate(R.layout.item_activity_listview_with_switchbutton, parent, false);
    }
}
