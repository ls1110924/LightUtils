package com.yunxian.lightutils.lib.adapter.multiitemtype;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yunxian.lightutils.lib.adapter.viewholder.AbsViewHolder;

/**
 * Created by A Shuai on 2015/5/2.
 * 对{@link IViewProvider}接口进行扩展的一个模板类，是对AdapterView中某个类型的ItemView的处理
 */
public abstract class AbsViewProvider<T extends AbsViewHolder, P extends IItemBean, Q extends OnGeneralListener>
        implements IViewProvider<P, Q> {

    @SuppressWarnings("unchecked")
    @Override
    public final View getItemView(int position, View convertView, ViewGroup parent, LayoutInflater mInflater, P mItemBean, Q mGeneralListener) {
        T mViewHolder;
        if (convertView == null) {
            convertView = inflaterView(mInflater, parent);
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
        bindContent(position, mViewHolder, mItemBean, mGeneralListener);
        return convertView;
    }

    /**
     * 返回Adapter提供的视图类
     *
     * @param mInflater 用于填充layout文件的LayoutInflater对象
     * @param parent    父容器对象
     * @return 返回当前item类型的内容视图，不可为空
     */
    protected abstract View inflaterView(LayoutInflater mInflater, ViewGroup parent);

    /**
     * 子类请务必返回一个AbsViewHolder的子类对象{@link AbsViewHolder}
     *
     * @return 不可为空
     */
    protected abstract T buildViewHolder();

    /**
     * 各子类根据情况自行查找convertView中的内容视图控件并填充到ViewHolder中
     *
     * @param convertView 由{@link #inflaterView(LayoutInflater, ViewGroup)}实例化的内容视图
     * @param mViewHolder 由{@link #buildViewHolder()} 构造的ViewHolder对象
     */
    protected abstract void findViews(View convertView, T mViewHolder);

    /**
     * 根据提供的当前item的索引位置，数据bean和回调监听器，自行填充ViewHolder中的内容视图控件的内容
     *
     * @param position         当前正在填充的itemview的位置
     * @param mViewHolder      查找完必要的子视图后的ViewHolder对象
     * @param mItemBean        用于填充当前内容的数据bean
     * @param mGeneralListener 通用回调监听器
     */
    protected abstract void bindContent(int position, T mViewHolder, P mItemBean, Q mGeneralListener);

}
