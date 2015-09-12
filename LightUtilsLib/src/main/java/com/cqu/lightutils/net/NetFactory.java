package com.cqu.lightutils.net;

import com.cqu.lightutils.bean.AccessNetResultBean;
import com.cqu.lightutils.bean.ApiRequestBean;

/**
 * Created by A Shuai on 2015/5/3.
 * 访问网络的工厂类，提供了访问网络的工厂方法
 */
public final class NetFactory {

    private NetFactory() {
    }

    /**
     * 调用此工厂方法进行网络数据的获取
     *
     * @param mRequestBean URL数据bean
     * @return 访问网络的结果
     */
    public static AccessNetResultBean getNetResult(ApiRequestBean mRequestBean) {
        INet mINet = new URLConnectionNet(mRequestBean);
        return mINet.getResult();
    }

}
