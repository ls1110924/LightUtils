package com.cqu.lightutils.sample.enumeration;

import com.cqu.lightutils.custominterface.StatusBarThemeInterface;

/**
 * Created by A Shuai on 2015/5/4.
 */
public enum MaterialTheme implements StatusBarThemeInterface {


    THEME_1(0, 0xFFD32F2F, 0xFFF44336),

    THEME_2(1, 0xFF1976D2, 0xFF2196F3),;

    private final int mType;
    /* 分别为状态栏颜色和ActionBar颜色 */
    private final int mStatusBarColor;
    private final int mActionBarColor;

    private MaterialTheme( int mType, int mStatusBarColor, int mActionBarColor ){
        this.mType = mType;
        this.mStatusBarColor = mStatusBarColor;
        this.mActionBarColor = mActionBarColor;
    }

    public int getType() {
        return mType;
    }


    @Override
    public int getStatusBarColor() {
        return mStatusBarColor;
    }

    @Override
    public int getActionBarColor() {
        return mActionBarColor;
    }

    @Override
    public int getToolBarColor() {
        return getActionBarColor();
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
