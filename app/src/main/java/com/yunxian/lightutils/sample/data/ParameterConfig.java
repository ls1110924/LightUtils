package com.yunxian.lightutils.sample.data;

import com.yunxian.lightutils.sample.enumeration.MaterialTheme;

/**
 * Created by A Shuai on 2015/5/4.
 */
public final class ParameterConfig {

    private static ParameterConfig mInstance;

    public static ParameterConfig getInstance(){
        if (mInstance==null){
            synchronized (ParameterConfig.class){
                if (mInstance==null){
                    mInstance = new ParameterConfig();
                }
            }
        }
        return mInstance;
    }

    private MaterialTheme mMaterialTheme;

    private ParameterConfig(){
        mMaterialTheme = MaterialTheme.THEME_1;
    }

    public MaterialTheme getMaterialTheme() {
        return mMaterialTheme;
    }

    public void setMaterialTheme(MaterialTheme mMaterialTheme) {
        this.mMaterialTheme = mMaterialTheme;
    }
}
