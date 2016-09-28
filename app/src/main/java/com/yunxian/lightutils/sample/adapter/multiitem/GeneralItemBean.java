package com.yunxian.lightutils.sample.adapter.multiitem;


import com.yunxian.lightutils.lib.adapter.multiitemtype.IItemBean;
import com.yunxian.lightutils.lib.adapter.multiitemtype.IViewProvider;

/**
 * Created by A Shuai on 2015/5/4.
 */
public class GeneralItemBean implements IItemBean {

    private String mOption;

    public GeneralItemBean( String mOption ){
        this.mOption = mOption;
    }

    public String getOption() {
        return mOption;
    }

    public void setOption( String mOption ){
        this.mOption = mOption;
    }

    @Override
    public Class<? extends IViewProvider> getViewProviderClass() {
        return GeneralViewProvider.class;
    }
}
