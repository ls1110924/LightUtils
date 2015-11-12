package com.cqu.lightutils.custominterface;

/**
 * Created by A Shuai on 2015/11/12.<p/>
 * 新的MaterialTheme配色接口定义，用于替代{@link StatusBarThemeInterface}接口
 */
public interface MaterialThemeInterface {

    /**
     * <p>主调色彩</p>
     * <p>常用与ActionBar，ToolBar以及NavigationBar配色</p>
     *
     * @return 主色调的配色
     */
    int getPrimaryColor();

    /**
     * <p>主调暗色</p>
     * <p>常用于StatusBar配色</p>
     *
     * @return 主色调暗色的配色
     */
    int getPrimaryDarkColor();

    /**
     * <p>强调色彩</p>
     * <p>应用于框架的控制，如EditText，Switch等</p>
     *
     * @return 强调色彩的配色
     */
    int getAccentColor();

}
