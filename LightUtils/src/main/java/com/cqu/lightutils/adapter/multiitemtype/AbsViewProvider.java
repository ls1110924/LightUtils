package com.cqu.lightutils.adapter.multiitemtype;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cqu.lightutils.adapter.viewholder.AbsViewHolder;

/**
 * Created by A Shuai on 2015/5/2.
 * 对{@link com.cqu.lightutils.adapter.multiitemtype.IViewProvider}接口进行扩展的一个模板类
 * 是对AdapterView中某个类型的ItemView的处理
 */
public abstract class AbsViewProvider<T extends AbsViewHolder, P extends IItemBean, Q extends OnGeneralListener>
        implements IViewProvider<P, Q> {

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
     * @param mInflater
     * @param parent
     * @return 不可为空
     */
    protected abstract View inflaterView(LayoutInflater mInflater, ViewGroup parent);

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
     * 根据提供的当前item的索引位置，数据bean和回调监听器，自行填充ViewHolder中的内容视图控件的内容
     *
     * @param position
     * @param mViewHolder
     * @param mItemBean
     * @param mGeneralListener
     */
    protected abstract void bindContent(int position, T mViewHolder, P mItemBean, Q mGeneralListener);

}
