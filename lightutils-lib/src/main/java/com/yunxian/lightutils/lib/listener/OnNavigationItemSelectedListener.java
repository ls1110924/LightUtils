package com.yunxian.lightutils.lib.listener;

import com.yunxian.lightutils.lib.activity.BaseMaterialDrawerActivityIncludedToolbar;

/**
 * Created by A Shuai on 2016/1/7.
 * <p>适用于带侧滑抽屉Activity中侧滑Fragment回调通知主Activity用。
 * 适用于{@link BaseMaterialDrawerActivityIncludedToolbar}</p>
 */
public interface OnNavigationItemSelectedListener {

    /**
     * 回调通知主Activity第几个菜单项被选中。具体菜单项ID如何映射由各子类自行负责
     *
     * @param mItemID 菜单项ID
     * @return true表示此事件被消费
     */
    boolean onNavigationItemSelected(int mItemID);

}
