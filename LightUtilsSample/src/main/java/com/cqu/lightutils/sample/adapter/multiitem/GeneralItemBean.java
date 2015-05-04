package com.cqu.lightutils.sample.adapter.multiitem;

import com.cqu.lightutils.adapter.multiitemtype.IItemBean;
import com.cqu.lightutils.adapter.multiitemtype.IViewProvider;

/**
 * Created by A Shuai on 2015/5/4.
 */
public class GeneralItemBean implements IItemBean{

    @Override
    public Class<? extends IViewProvider> getViewProviderClass() {
        return null;
    }
}
