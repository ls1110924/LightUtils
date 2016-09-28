package com.yunxian.lightutils.lib.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.View;

/**
 * Created by A Shuai on 2015/5/2.
 * 有关View的工具类
 */
public final class ViewUtils {

    private ViewUtils() {
    }

    /**
     * 根据指定的View，绘制一个同样的位图图像
     *
     * @param view 视图控件
     * @return Bitmap对象
     */
    public static Bitmap buildBitmapFromView(View view) {
        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);
        return bitmap;
    }

}
