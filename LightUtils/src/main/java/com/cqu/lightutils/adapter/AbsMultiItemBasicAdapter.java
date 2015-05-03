package com.cqu.lightutils.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.cqu.lightutils.adapter.multiitemtype.IItemBean;
import com.cqu.lightutils.adapter.multiitemtype.IViewProvider;
import com.cqu.lightutils.adapter.multiitemtype.OnGeneralListener;

import java.util.HashMap;
import java.util.List;

/**
 * Created by A Shuai on 2015/5/2.
 * 此类为多布局Adapter类的模板类，适合于多个布局类型的adapter
 */
public abstract class AbsMultiItemBasicAdapter<Q extends OnGeneralListener> extends BaseAdapter {

    protected final Context mContext;
    protected final LayoutInflater mInflater;

    protected final Resources mResources;


    protected final Q mOnGeneralListener;

    private final HashMap<String, IViewProvider> mProviderMap;
    private final List<? extends IItemBean> mItemBeanList;
    private final List<Class<? extends IViewProvider>> mProviderList;

    public AbsMultiItemBasicAdapter(Context mContext, Q mOnGeneralListener, List<? extends IItemBean> mItemBeanList, List<Class<? extends IViewProvider>> mProviderList) {
        this.mContext = mContext;
        mInflater = LayoutInflater.from(mContext);
        mResources = mContext.getResources();

        this.mOnGeneralListener = mOnGeneralListener;
        mProviderMap = new HashMap<String, IViewProvider>();
        this.mItemBeanList = mItemBeanList;
        this.mProviderList = mProviderList;

        if (mItemBeanList == null || mProviderList == null) {
            throw new NullPointerException("the itembeanlist and providerlist should not be null");
        }
    }

    @Override
    public final int getCount() {
        return mItemBeanList.size();
    }

    @Override
    public final Object getItem(int position) {
        return mItemBeanList.get(position);
    }

    @Override
    public final long getItemId(int position) {
        return position;
    }

    @Override
    public final int getItemViewType(int position) {
        String mClassName = mItemBeanList.get(position).getViewProviderClass().getName();
        for (int i = 0, size = mProviderList.size(); i < size; i++) {
            Class<? extends IViewProvider> mClass = mProviderList.get(i);
            if (mClassName.equals(mClass.getName()))
                return i;
        }
        throw new IllegalArgumentException("there is no provider class name conform to");
    }

    @Override
    public final int getViewTypeCount() {
        return mProviderList.size();
    }

    @Override
    public final View getView(int position, View convertView, ViewGroup parent) {
        IItemBean mItemBean = mItemBeanList.get(position);
        String mClassName = mItemBean.getViewProviderClass().getName();

        IViewProvider mProvider = mProviderMap.get(mClassName);
        if (mProvider == null) {
            try {
                mProvider = mItemBean.getViewProviderClass().newInstance();
                mProviderMap.put(mClassName, mProvider);
            } catch (Exception e) {
                e.printStackTrace();
                throw new IllegalStateException("");
            }

        }
        convertView = mProvider.getItemView(position, convertView, parent, mInflater, mItemBean, mOnGeneralListener);

        return convertView;
    }

}
