package com.cqu.lightutils.adapter.multiitemtype;

/**
 * Created by A Shuai on 2015/5/2.
 * AdapterView中某一个item所属数据bean
 * 有多少item就有多少个IItemBean，但是同属一种布局类型的item集共享一个IViewProvider
 * 由此IViewProvider进行处理对应类型布局的实现
 */
public interface IItemBean {

    /**
     * 类似于工厂模式，返回用于填充当前item数据bean的工厂类的class对象
     *
     * @return 返回一个工厂类的class对象
     */
    Class<? extends IViewProvider> getViewProviderClass();

}
