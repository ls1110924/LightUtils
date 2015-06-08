package com.cqu.lightutils.compat;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;

/**
 * Created by A Shuai on 2015/4/30.
 */
@SuppressWarnings("deprecation")
public final class ViewCompat {

    private ViewCompat() {
    }

    /**
     * 设置指定View的Background
     *
     * @param mView
     * @param mDrawable
     */
    public static void setViewBackground(View mView, Drawable mDrawable) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            mView.setBackgroundDrawable(mDrawable);
        } else {
            mView.setBackground(mDrawable);
        }
    }

}
