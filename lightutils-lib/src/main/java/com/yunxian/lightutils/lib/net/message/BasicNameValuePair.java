package com.yunxian.lightutils.lib.net.message;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by A Shuai on 2015/9/12.
 * <p>手工实现Android6.0剔除了的Http类，Http请求中的键值对接口的基本实现类。</p>
 */
public final class BasicNameValuePair implements NameValuePair, Parcelable {

    private final String mName;
    private final String mValue;

    public BasicNameValuePair(String mName, String mValue) {
        if (mName == null || mValue == null) {
            throw new NullPointerException();
        }
        this.mName = mName;
        this.mValue = mValue;
    }

    @Override
    public String getName() {
        return mName;
    }

    @Override
    public String getValue() {
        return mValue;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mName);
        dest.writeString(mValue);
    }

    private BasicNameValuePair(Parcel in) {
        mName = in.readString();
        mValue = in.readString();
    }

    public static final Creator<BasicNameValuePair> CREATOR = new Creator<BasicNameValuePair>() {

        @Override
        public BasicNameValuePair createFromParcel(Parcel source) {
            return new BasicNameValuePair(source);
        }

        @Override
        public BasicNameValuePair[] newArray(int size) {
            return new BasicNameValuePair[0];
        }

    };

}
