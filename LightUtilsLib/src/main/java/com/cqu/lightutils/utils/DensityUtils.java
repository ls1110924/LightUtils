package com.cqu.lightutils.utils;

import android.content.Context;

/**
 * Created by A Shuai on 2015/5/2.
 * 有关屏幕密度工具类
 */
public final class DensityUtils {

    private DensityUtils() {
    }

    /**
     * 获取屏幕高度，以像素为单位
     *
     * @param context 上下文
     * @return 屏幕高度
     */
    public static float getHeightInPx(Context context) {
        final float height = context.getResources().getDisplayMetrics().heightPixels;
        return height;
    }

    /**
     * 获取屏幕宽度，以像素为单位
     *
     * @param context 上下文
     * @return 屏幕宽度
     */
    public static float getWidthInPx(Context context) {
        final float width = context.getResources().getDisplayMetrics().widthPixels;
        return width;
    }

    /**
     * 获取屏幕高度，以DP为单位
     *
     * @param context 上下文
     * @return 屏幕高度，以DP为单位
     */
    public static int getHeightInDp(Context context) {
        final float height = context.getResources().getDisplayMetrics().heightPixels;
        int heightInDp = px2dip(context, height);
        return heightInDp;
    }

    /**
     * 获取屏幕宽度，以DP为单位
     *
     * @param context 上下文
     * @return 屏幕宽度，以DP为单位
     */
    public static int getWidthInDp(Context context) {
        final float height = context.getResources().getDisplayMetrics().heightPixels;
        int widthInDp = px2dip(context, height);
        return widthInDp;
    }

    /**
     * 将DP单位转换为PX
     *
     * @param context 上下文
     * @param dpValue 待转换的DP值
     * @return 转换后的PX值
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 将PX单位转换为DP单位
     *
     * @param context 上下文
     * @param pxValue 待转换的PX值
     * @return 转换后的DP值
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 将SP单位转换为PX
     *
     * @param context 上下文
     * @param spValue 待转换的SP值
     * @return 转换后的PX值
     */
    public static int sp2px(Context context, float spValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (spValue * scale + 0.5f);
    }

    /**
     * 将PX单位转换为SP
     *
     * @param context 上下文
     * @param pxValue 待转换的PX值
     * @return 转换后的SP值
     */
    public static int px2sp(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

}
