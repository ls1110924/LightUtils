package com.yunxian.lightutils.sample.adapter;

import android.content.Context;

import com.yunxian.lightutils.lib.adapter.AbsMultiItemBasicAdapter;
import com.yunxian.lightutils.lib.adapter.multiitemtype.IItemBean;
import com.yunxian.lightutils.lib.adapter.multiitemtype.IViewProvider;
import com.yunxian.lightutils.sample.listener.OnCommonListener;

import java.util.List;

/**
 * Created by A Shuai on 2015/5/4.
 */
public class MainMultiItemFragListAdapter extends AbsMultiItemBasicAdapter<OnCommonListener> {

    public MainMultiItemFragListAdapter(Context mContext, OnCommonListener mOnGeneralListener, List<? extends IItemBean> mItemBeanList, List<Class<? extends IViewProvider>> mProviderList) {
        super(mContext, mOnGeneralListener, mItemBeanList, mProviderList);
    }
}

