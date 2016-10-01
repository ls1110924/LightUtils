package com.cqu.lightutils.bean;

import com.cqu.lightutils.enumeration.AccessNetState;

/**
 * Created by A Shuai on 2015/5/3.
 * 调用{@link com.cqu.lightutils.net.NetFactory#getNetResult(ApiRequestBean)}方法访问网络后返回的数据
 */
public final class AccessNetResultBean {

    private AccessNetState mState;
    private String mResult;

    public AccessNetResultBean() {
        mState = AccessNetState.Initialize;
    }

    /**
     * 获取访问网络返回的结果字符串
     *
     * @return 结果
     */
    public String getResult() {
        return mResult;
    }

    /**
     * 获取访问网络的访问结果状态
     *
     * @return 访问网络的结果状态
     */
    public AccessNetState getState() {
        return mState;
    }

    /**
     * 设置访问网络的结果字符串
     *
     * @param mResult 结果
     */
    public void setResult(String mResult) {
        this.mResult = mResult;
    }

    /**
     * 设置访问网络的结果状态
     *
     * @param mState 状态
     */
    public void setState(AccessNetState mState) {
        this.mState = mState;
    }
}
