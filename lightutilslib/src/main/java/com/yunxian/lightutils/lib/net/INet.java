package com.yunxian.lightutils.lib.net;

import com.yunxian.lightutils.lib.bean.AccessNetResultBean;

/**
 * Created by A Shuai on 2015/5/3.
 * 访问网络的接口，有两个实现子类分别为，{@link com.yunxian.lightutils.lib.net.HttpClientNet} 和 {@link com.yunxian.lightutils.lib.net.URLConnectionNet}
 */
public interface INet {

    /**
     * 获取访问网络的结果
     *
     * @return 访问结果
     */
    AccessNetResultBean getResult();

    /**
     * 以Get方式访问网络获取结果
     *
     * @return 访问结果
     */
    AccessNetResultBean getResultByGet();

    /**
     * 以Post方式访问网络获取结果
     *
     * @return 访问结果
     */
    AccessNetResultBean getResultByPost();

}
