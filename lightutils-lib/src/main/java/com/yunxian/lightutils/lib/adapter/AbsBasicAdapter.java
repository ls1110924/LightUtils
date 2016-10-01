package com.yunxian.lightutils.lib.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.yunxian.lightutils.lib.adapter.viewholder.AbsViewHolder;

/**
 * Created by A Shuai on 2015/5/2.
 * 此类为普通Adapter类的模板类，适合于单一布局类型的adapter
 */
public abstract class AbsBasicAdapter<T extends AbsViewHolder> extends BaseAdapter {

    /**
     * 上下文对象
     */
    protected final Context mContext;
    /**
     * 用于填充layout文件的LayoutInflater对象
     */
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
     * @param parent 父容器
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
     * @param convertView 由{@link #inflaterView(ViewGroup)}实例化的内容视图
     * @param mViewHolder 由{@link #buildViewHolder()} 构造的ViewHolder对象
     */
    protected abstract void findViews(View convertView, T mViewHolder);

    /**
     * 根据提供的当前item的索引位置，自行填充ViewHolder中的内容视图控件的内容
     *
     * @param position    当前正在填充的itemview的位置
     * @param mViewHolder 查找完必要的子视图后的ViewHolder对象
     */
    protected abstract void bindContent(int position, T mViewHolder);

    /**
     * 根据提供的根视图和ID，从根视图中找出ID对应的视图
     *
     * @param mView 待查找视图所在的容器视图
     * @param id    待查找的视图ID
     * @param <T>   待查找的视图类型
     * @return 待查找的视图对象
     */
    @SuppressWarnings("unchecked")
    protected static <T extends View> T findView(View mView, int id) {
        try {
            return (T) mView.findViewById(id);
        } catch (ClassCastException ex) {
            throw ex;
        }
    }

}
