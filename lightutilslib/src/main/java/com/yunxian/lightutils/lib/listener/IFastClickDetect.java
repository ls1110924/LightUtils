package com.yunxian.lightutils.lib.listener;

import android.view.View;

/**
 * Created by A Shuai on 2015/9/12.
 * <p>检查View的此次点击事件是否为合法点击的接口类</p>
 */
public interface IFastClickDetect {

    /**
     * 检查View的此次点击是否为合法点击
     *
     * @param v 待检查的View
     * @return true表示合法，false为非法
     */
    boolean isLegalClick(View v);

}
