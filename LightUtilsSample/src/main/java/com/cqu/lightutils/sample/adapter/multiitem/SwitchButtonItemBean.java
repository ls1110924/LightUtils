package com.cqu.lightutils.sample.adapter.multiitem;

import com.cqu.lightutils.adapter.multiitemtype.IItemBean;
import com.cqu.lightutils.adapter.multiitemtype.IViewProvider;

/**
 * Created by A Shuai on 2015/5/4.
 */
public class SwitchButtonItemBean implements IItemBean{

    private String mOption;
    private boolean mSelected;

    public SwitchButtonItemBean( String mOString, boolean mSelected ){
        this.mOption = mOString;
        this.mSelected = mSelected;
    }

    public boolean isSelected() {
        return mSelected;
    }

    public void setSelected(boolean mSelected) {
        this.mSelected = mSelected;
    }

    public String getOption() {
        return mOption;
    }

    @Override
    public Class<? extends IViewProvider> getViewProviderClass() {
        return SwitchButtonViewProvider.class;
    }
}
