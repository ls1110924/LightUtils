package com.cqu.lightutils.compat;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;

/**
 * Created by A Shuai on 2015/4/30.
 * 提供操作View兼容各版本系统的方法
 */
public final class ViewCompat {

    private ViewCompat() {
    }

    /**
     * 设置指定View的Background
     *
     * @param mView     待操作的View
     * @param mDrawable 待设置背景的Drawable
     */
    @SuppressWarnings("deprecation")
    public static void setViewBackground(View mView, Drawable mDrawable) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            mView.setBackgroundDrawable(mDrawable);
        } else {
            mView.setBackground(mDrawable);
        }
    }

}
