package com.cqu.lightutils.net;

import com.cqu.lightutils.bean.AccessNetResultBean;

/**
 * Created by A Shuai on 2015/5/3.
 */
public interface INet {

    public abstract AccessNetResultBean getResult();

    public abstract AccessNetResultBean getResultByGet();

    public abstract AccessNetResultBean getResultByPost();

}
