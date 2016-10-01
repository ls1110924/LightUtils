package com.yunxian.lightutils.lib.utils;

import android.util.SparseArray;
import android.view.View;

/**
 * Created by A Shuai on 2015/9/12.
 * <p>快速点击侦测工具类，避免因快速点击按钮导致事件重复响应</p>
 */
public final class FastClickDetectionUtil {

    //默认设置的快速点击间隔为700毫秒，当连续点击处于700毫秒内时，判定为无效点击
    private static final int TIME_THRESHOLD_GAP = 700;

    private static FastClickDetectionUtil mInstance;

    public static FastClickDetectionUtil getInstance() {
        if (mInstance == null) {
            synchronized (FastClickDetectionUtil.class) {
                if (mInstance == null) {
                    mInstance = new FastClickDetectionUtil();
                }
            }
        }
        return mInstance;
    }

    private final SparseArray<Long> mTimeArray;

    private FastClickDetectionUtil() {
        mTimeArray = new SparseArray<>();
    }

    /**
     * 检查参数控件的此次点击事件是否合法
     *
     * @param mView 待检查的控件
     * @return true表示此次点击合法，false为非法，即处于快速点击状态
     */
    public final boolean isLegalClick(View mView) {
        return isLegalClick(mView.getId());
    }

    /**
     * 检查参数ID所对应的控件此次点击事件是否合法
     *
     * @param mID 待检查控件的ID
     * @return true表示此次点击合法，false为非法，即处于快速点击状态
     */
    public final boolean isLegalClick(int mID) {
        Long mLastTime = mTimeArray.get(mID);
        long mCurrentTime = System.currentTimeMillis();
        mTimeArray.put(mID, mCurrentTime);
        if (mLastTime == null) {
            return true;
        } else {
            if (mCurrentTime - mLastTime >= TIME_THRESHOLD_GAP) {
                return true;
            } else {
                return false;
            }
        }
    }

    /**
     * 因为一个页面只有为数不多的控件需要处理，而如果稀疏数组
     * 一直保持所有页面的待检查控件ID会过多占用空间也会降低效率，不是很合理。
     * 故提供此方法，便于用户在合适的时间清空一次数据。推荐合适调用此方法的声明周期为
     * Activity的onResume()或者onPause()。只需在其中一个中进行处理即可。
     */
    public final void clear() {
        mTimeArray.clear();
    }

}
