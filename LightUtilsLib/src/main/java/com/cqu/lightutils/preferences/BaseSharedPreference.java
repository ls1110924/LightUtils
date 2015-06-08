package com.cqu.lightutils.preferences;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by A Shuai on 2015/5/3.
 * 可进行子类扩展，实现自定义的存储重要键值对的工厂方法
 */
public abstract class BaseSharedPreference {

    /**
     * 没有隐藏构造方法，是可以让子类继承本类，可以直接使用本类中的静态方法实现自定义存储参数的方法
     */
    protected BaseSharedPreference() {
        throw new IllegalStateException("shouldn't create instance");
    }

    /**
     * 读取一个boolean值
     *
     * @param mContext
     * @param mName        文件名
     * @param mKey
     * @param defalutValue
     * @return
     */
    public static boolean getBooleanValue(Context mContext, String mName, String mKey, boolean defalutValue) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(mName, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(mKey, defalutValue);
    }

    /**
     * 存储一个boolean值
     *
     * @param mContext
     * @param mName    文件名
     * @param mKey
     * @param mValue
     */
    public static void setBooleanValue(Context mContext, String mName, String mKey, boolean mValue) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(mName, Context.MODE_PRIVATE);
        SharedPreferences.Editor mEditor = sharedPreferences.edit();
        mEditor.putBoolean(mKey, mValue);
        mEditor.commit();
    }

    /**
     * 读取一个int整型值
     *
     * @param mContext
     * @param mName        文件名
     * @param mKey
     * @param defalutValue
     * @return
     */
    public static int getIntegerVaule(Context mContext, String mName, String mKey, int defalutValue) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(mName, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(mKey, defalutValue);
    }

    /**
     * 存储一个int整型值
     *
     * @param mContext
     * @param mName    文件名
     * @param mKey
     * @param mValue
     */
    public static void setIntegerValue(Context mContext, String mName, String mKey, int mValue) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(mName, Context.MODE_PRIVATE);
        SharedPreferences.Editor mEditor = sharedPreferences.edit();
        mEditor.putInt(mKey, mValue);
        mEditor.commit();
    }

    /**
     * 读取一个String字符串
     *
     * @param mContext
     * @param mName        文件名
     * @param mKey
     * @param defalutValue
     * @return
     */
    public static String getStringVaule(Context mContext, String mName, String mKey, String defalutValue) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(mName, Context.MODE_PRIVATE);
        return sharedPreferences.getString(mKey, defalutValue);
    }

    /**
     * 存储一个String字符串
     *
     * @param mContext
     * @param mName    文件名
     * @param mKey
     * @param mValue
     */
    public static void setStringValue(Context mContext, String mName, String mKey, String mValue) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(mName, Context.MODE_PRIVATE);
        SharedPreferences.Editor mEditor = sharedPreferences.edit();
        mEditor.putString(mKey, mValue);
        mEditor.commit();
    }

}
