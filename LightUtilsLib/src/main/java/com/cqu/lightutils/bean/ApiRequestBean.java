package com.cqu.lightutils.bean;

import com.cqu.lightutils.enumeration.NetOperation;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by A Shuai on 2015/5/3.
 * 由{@link com.cqu.lightutils.net.NetFactory#getNetResult(ApiRequestBean)}方法访问网络时，所需的URL内容
 */
public final class ApiRequestBean {

    private final NetOperation mOpeType;
    private final String mApiUrl;
    private final List<NameValuePair> mKeyValuePair;

    public ApiRequestBean(NetOperation mOpeType, String mApiUrl) {
        this.mOpeType = mOpeType;
        this.mApiUrl = mApiUrl;
        mKeyValuePair = new LinkedList<>();
    }

    /**
     * 获取当前所指定的访问网络的类型
     *
     * @return 返回值为Post和Get中的一个
     */
    public NetOperation getNetOpeType() {
        return mOpeType;
    }

    /**
     * 获取基类URL，一般有Post方式进行调用
     *
     * @return 访问网路的基类URL字符串
     */
    public String getApiUrl() {
        return mApiUrl;
    }

    /**
     * 向键值对集中添加一个新的键值对
     *
     * @param mKey   键
     * @param mValue 值
     */
    public void addKeyValuePair(String mKey, String mValue) {
        mKeyValuePair.add(new BasicNameValuePair(mKey, mValue));
    }

    /**
     * 获取当前URL所填充的键值对列表
     *
     * @return 键值对列表
     */
    public List<NameValuePair> getKeyValuePair() {
        return mKeyValuePair;
    }

    /**
     * 获取以HttpGet方法访问Api时的URL
     *
     * @return 以HttpGet方式访问网络所需的完整URL字符串
     */
    public final String getGetUrl() {
        StringBuilder mSB = new StringBuilder(mApiUrl);
        if (mKeyValuePair.size() > 0)
            mSB.append('?');
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
     * @return 以HttpPost方式访问网络所需的写到HTTP内容段的字符串
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
