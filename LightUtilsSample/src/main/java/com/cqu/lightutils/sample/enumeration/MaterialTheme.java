package com.cqu.lightutils.sample.enumeration;

import com.cqu.lightutils.custominterface.MaterialThemeInterface;
import com.cqu.lightutils.custominterface.StatusBarThemeInterface;

/**
 * Created by A Shuai on 2015/5/4.
 */
public enum MaterialTheme implements StatusBarThemeInterface, MaterialThemeInterface {


    THEME_1(0, 0xFFD32F2F, 0xFFF44336),

    THEME_2(1, 0xFF1976D2, 0xFF2196F3);

    private final int mType;
    /* 分别为状态栏颜色和ActionBar颜色 */
    private final int mPrimaryDarkColor;
    private final int mPrimaryColor;
    private final int mAccentColor;

    private MaterialTheme( int mType, int mPrimaryDarkColor, int mPrimaryColor ){
        this.mType = mType;
        this.mPrimaryDarkColor = mPrimaryDarkColor;
        this.mPrimaryColor = mPrimaryColor;
        this.mAccentColor = mPrimaryColor;
    }

    private MaterialTheme( int mType, int mPrimaryDarkColor, int mPrimaryColor, int mAccentColor ){
        this.mType = mType;
        this.mPrimaryDarkColor = mPrimaryDarkColor;
        this.mPrimaryColor = mPrimaryColor;
        this.mAccentColor = mAccentColor;
    }

    public int getType() {
        return mType;
    }


    @Override
    public int getStatusBarColor() {
        return mPrimaryDarkColor;
    }

    @Override
    public int getActionBarColor() {
        return mPrimaryColor;
    }

    @Override
    public int getToolBarColor() {
        return getActionBarColor();
    }

    @Override
    public int getPrimaryColor() {
        return mPrimaryColor;
    }

    @Override
    public int getPrimaryDarkColor() {
        return mPrimaryDarkColor;
    }

    @Override
    public int getAccentColor() {
        return mAccentColor;
    }

    public static MaterialTheme valueOf( int mType ){
        for( MaterialTheme mItem : values() ){
            if( mItem.getType() == mType ){
                return mItem;
            }
        }
        throw new IllegalArgumentException();
    }

}
