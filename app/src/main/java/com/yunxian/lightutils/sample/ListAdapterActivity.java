package com.yunxian.lightutils.sample;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import com.yunxian.lightutils.lib.activity.BaseMarerialActivityIncludedActionbar;
import com.yunxian.lightutils.sample.data.ParameterConfig;
import com.yunxian.lightutils.sample.fragment.MultiItemListFragment;
import com.yunxian.lightutils.sample.fragment.SingleItemListFragment;

/**
 * Created by A Shuai on 2016/1/8.
 */
public class ListAdapterActivity extends BaseMarerialActivityIncludedActionbar {

    public static final String KEY_FRAGMENT_FLAG = "ContentFlag";

    private CharSequence mTitle;
    private Fragment mContentFragment;

    private ParameterConfig mParaConfig = ParameterConfig.getInstance();

    @Override
    protected void onInitParameter() {
        super.onInitParameter();

        Bundle mBundle = getIntent().getExtras();
        int mFragmentFlag = mBundle.getInt(KEY_FRAGMENT_FLAG, 0);

        switch (mFragmentFlag){
            case 0:
                mTitle = "SingleItemListTest";
                mContentFragment = new SingleItemListFragment();
                break;
            case 1:
                mTitle = "MultiItemListTest";
                mContentFragment = new MultiItemListFragment();
                break;
            default:
                throw new IllegalArgumentException();
        }

    }

    @NonNull
    @Override
    protected Fragment onSetInitContentFragment() {
        return mContentFragment;
    }

    @Override
    protected void onCreateImpl(Bundle savedInstanceState) {
        super.onCreateImpl(savedInstanceState);

        setActionBarHomeEnable(true);
        setActionBarTitle(mTitle);
    }

    @Override
    protected void onStart() {
        super.onStart();
        setMaterialThemeColor(mParaConfig.getMaterialTheme());
    }

    @Override
    protected boolean onHomeKeyDown() {
        finish(R.anim.activity_slide_left_in_part, R.anim.activity_slide_right_out);
        return true;
    }

    @Override
    protected boolean onBackKeyDown() {
        finish(R.anim.activity_slide_left_in_part, R.anim.activity_slide_right_out);
        return true;
    }
}
