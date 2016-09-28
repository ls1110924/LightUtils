package com.yunxian.lightutils.lib.custominterface;

/**
 * Created by A Shuai on 2015/4/30.
 * 提供自定义状态栏和ActionBar颜色的实体类需实现的接口
 */
@Deprecated
public interface StatusBarThemeInterface {

    /**
     * 获取状态栏的自定义颜色
     *
     * @return 状态栏的配色
     */
    int getStatusBarColor();

    /**
     * 获取ActionBar栏的自定义颜色
     *
     * @return 动作栏的配色
     */
    int getActionBarColor();

    /**
     * 获取ToolBar栏的自定义颜色
     *
     * @return 工具栏的配色
     */
    int getToolBarColor();

}
