package com.cqu.lightutils.bean;

import com.cqu.lightutils.enumeration.AccessNetState;

/**
 * Created by A Shuai on 2015/5/3.
 */
public final class AccessNetResultBean {

    private AccessNetState mState;
    private String mResult;

    public AccessNetResultBean() {
        mState = AccessNetState.Initialize;
    }

    public String getResult() {
        return mResult;
    }

    public AccessNetState getState() {
        return mState;
    }

    public void setResult(String mResult) {
        this.mResult = mResult;
    }

    public void setState(AccessNetState mState) {
        this.mState = mState;
    }
}
