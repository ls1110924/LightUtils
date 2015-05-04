package com.cqu.lightutils.sample;

import android.view.View;
import android.widget.Button;

import com.cqu.lightutils.activity.BaseActionBarFragmentActivity;
import com.cqu.lightutils.sample.data.ParameterConfig;
import com.cqu.lightutils.sample.enumeration.MaterialTheme;

/**
 * Created by A Shuai on 2015/5/4.
 */
public class SwitchThemeActivity extends BaseActionBarFragmentActivity {

    private Button mButton;

    private CommonCallbackListener mCommonListener;

    private ParameterConfig mParaConfig;

    @Override
    protected void onInitParameter() {
        mCommonListener = new CommonCallbackListener();

        mParaConfig = ParameterConfig.getInstance();

        setActionBarHomeEnable(true);
    }

    @Override
    protected void onSetContentView() {
        setContentView(R.layout.activity_switchtheme);
    }

    @Override
    protected void onFindViews() {
        mButton = findView(R.id.activity_switchtheme_button);
    }

    @Override
    protected void onBindContent() {
        mButton.setOnClickListener(mCommonListener);
    }

    @Override
    protected void onStart() {
        super.onStart();
        setStatusBarAndNavigationBarTheme(mParaConfig.getMaterialTheme());
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

    private class CommonCallbackListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            if (mParaConfig.getMaterialTheme() == MaterialTheme.THEME_1) {
                mParaConfig.setMaterialTheme(MaterialTheme.THEME_2);
            } else {
                mParaConfig.setMaterialTheme(MaterialTheme.THEME_1);
            }

            setStatusBarAndNavigationBarTheme(mParaConfig.getMaterialTheme());
        }

    }

}
