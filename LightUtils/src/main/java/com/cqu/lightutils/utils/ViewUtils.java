package com.cqu.lightutils.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.View;

/**
 * Created by A Shuai on 2015/5/2.
 * 有关View的工具类
 */
public class ViewUtils {

    private ViewUtils() {
    }

    /**
     * 根据指定的View，绘制一个同样的位图图像
     *
     * @param view
     * @return
     */
    public static Bitmap buildBitmapFromView(View view) {
        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);
        return bitmap;
    }

}
