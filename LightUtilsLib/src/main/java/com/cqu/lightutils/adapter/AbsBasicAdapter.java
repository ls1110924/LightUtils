package com.cqu.lightutils.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.cqu.lightutils.adapter.viewholder.AbsViewHolder;

/**
 * Created by A Shuai on 2015/5/2.
 * 此类为普通Adapter类的模板类，适合于单一布局类型的adapter
 */
public abstract class AbsBasicAdapter<T extends AbsViewHolder> extends BaseAdapter {

    protected final Context mContext;
    protected final LayoutInflater mInflater;

    protected final Resources mResources;

    public AbsBasicAdapter(Context mContext) {
        this.mContext = mContext;
        mInflater = LayoutInflater.from(mContext);
        mResources = mContext.getResources();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressWarnings("unchecked")
    @Override
    public final View getView(int position, View convertView, ViewGroup parent) {
        T mViewHolder;
        if (convertView == null) {
            convertView = inflaterView(parent);
            if (convertView == null) {
                throw new NullPointerException();
            }
            mViewHolder = buildViewHolder();
            if (mViewHolder == null) {
                throw new NullPointerException();
            }
            findViews(convertView, mViewHolder);

            convertView.setTag(mViewHolder);
        } else {
            mViewHolder = (T) convertView.getTag();
        }
        mViewHolder.index = position;
        bindContent(position, mViewHolder);
        return convertView;
    }

    /**
     * 返回Adapter提供的视图类，LayoutInflate对象使用{@link AbsBasicAdapter#mInflater}
     *
     * @param parent
     * @return 不可为空
     */
    protected abstract View inflaterView(ViewGroup parent);

    /**
     * 子类请务必返回一个BasicViewHolderc的子类对象{@link AbsViewHolder}
     *
     * @return 不可为空
     */
    protected abstract T buildViewHolder();

    /**
     * 各子类根据情况自行查找convertView中的内容视图控件并填充到ViewHolder中
     *
     * @param convertView
     * @param mViewHolder
     */
    protected abstract void findViews(View convertView, T mViewHolder);

    /**
     * 根据提供的当前item的索引位置，自行填充ViewHolder中的内容视图控件的内容
     *
     * @param position
     * @param mViewHolder
     */
    protected abstract void bindContent(int position, T mViewHolder);

    /**
     * 根据提供的根视图和ID，从根视图中找出ID对应的视图
     *
     * @param mView
     * @param id
     * @param <T>
     * @return
     */
    protected static final <T extends View> T findView(View mView, int id) {
        try {
            return (T) mView.findViewById(id);
        } catch (ClassCastException ex) {
            throw ex;
        }
    }

}
