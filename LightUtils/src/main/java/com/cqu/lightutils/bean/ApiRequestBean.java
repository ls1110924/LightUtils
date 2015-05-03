package com.cqu.lightutils.bean;

import com.cqu.lightutils.enumeration.NetOperation;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by A Shuai on 2015/5/3.
 */
public final class ApiRequestBean {

    private final NetOperation mOpeType;
    private final String mApiUrl;
    private final List<NameValuePair> mKeyValuePair;

    public ApiRequestBean(NetOperation mOpeType, String mApiUrl) {
        this.mOpeType = mOpeType;
        this.mApiUrl = mApiUrl;
        mKeyValuePair = new LinkedList<NameValuePair>();
    }

    public NetOperation getNetOpeType() {
        return mOpeType;
    }

    public String getApiUrl() {
        return mApiUrl;
    }

    /**
     * 向键值对集中添加一个新的键值对
     *
     * @param mKey
     * @param mValue
     */
    public void addKeyValuePair(String mKey, String mValue) {
        mKeyValuePair.add(new BasicNameValuePair(mKey, mValue));
    }

    public List<NameValuePair> getKeyValuePair() {
        return mKeyValuePair;
    }

    /**
     * 获取以HttpGet方法访问Api时的URL
     *
     * @return
     */
    public final String getGetUrl() {
        StringBuilder mSB = new StringBuilder(mApiUrl).append('?');
        boolean flag = false;
        for (NameValuePair m : mKeyValuePair) {
            if (flag)
                mSB.append('&');
            else
                flag = true;
            mSB.append(m.getName()).append('=').append(m.getValue());

        }
        return mSB.toString();
    }

    /**
     * 获取以HttpPost方式访问网络时的内容段
     *
     * @return
     */
    public final String getPostContent() {
        StringBuilder mSB = new StringBuilder();
        boolean flag = false;
        for (NameValuePair m : mKeyValuePair) {
            if (flag)
                mSB.append('&');
            else
                flag = true;
            mSB.append(m.getName()).append('=').append(m.getValue());
        }
        return mSB.toString();
    }

}
