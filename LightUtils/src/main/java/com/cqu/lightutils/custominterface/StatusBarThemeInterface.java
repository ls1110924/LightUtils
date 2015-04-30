package com.cqu.lightutils.custominterface;

/**
 * Created by A Shuai on 2015/4/30.
 * 提供自定义状态栏和ActionBar颜色的实体类需实现的接口
 */
public interface StatusBarThemeInterface {

    /**
     * 获取状态栏的自定义颜色
     *
     * @return
     */
    int getStatusBarColor();

    /**
     * 获取ActionBar栏的自定义颜色
     *
     * @return
     */
    int getActionBarColor();

    /**
     * 获取ToolBar栏的自定义颜色
     *
     * @return
     */
    int getToolBarColor();

}
