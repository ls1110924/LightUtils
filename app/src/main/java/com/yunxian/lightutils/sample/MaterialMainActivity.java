package com.yunxian.lightutils.sample;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.yunxian.lightutils.lib.activity.BaseMaterialDrawerActivityIncludedToolbar;
import com.yunxian.lightutils.lib.utils.DialogUtils;
import com.yunxian.lightutils.sample.data.ParameterConfig;
import com.yunxian.lightutils.sample.fragment.MainDrawerFragment;
import com.yunxian.lightutils.sample.fragment.MarerialMainListFragment;
import com.yunxian.lightutils.sample.fragment.MultiItemListFragment;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by A Shuai on 2016/1/6.
 * <p>重写MainActivity，使其符合Material规范</p>
 */
public class MaterialMainActivity extends BaseMaterialDrawerActivityIncludedToolbar {

    private static final int DIALOG_EXIT = 10;

    private MainDrawerFragment mDrawerFragment;

    private Fragment mSingleItemFragment;
    private Fragment mMultiItemFragment;

    private ParameterConfig mParaConfig;

    private int mFragmentIndex = -1;

    private boolean isExit = false;

    @Override
    protected void onInitParameter() {
        super.onInitParameter();

        mParaConfig = ParameterConfig.getInstance();

        mDrawerFragment = new MainDrawerFragment();

        mSingleItemFragment = new MarerialMainListFragment();
        mMultiItemFragment = new MultiItemListFragment();
    }

    @NonNull
    @Override
    protected Fragment onSetInitContentPanel() {
        return mSingleItemFragment;
    }

    @NonNull
    @Override
    protected Fragment onSetDrawerPanel() {
        return mDrawerFragment;
    }

    @Override
    protected void onStart() {
        super.onStart();
        setMaterialThemeColor(mParaConfig.getMaterialTheme());
    }

    @Override
    protected boolean onBackKeyDownExtend() {
        if (!isExit) {
            isExit = true;
            Toast.makeText(this, R.string.activity_main_exit_tip, Toast.LENGTH_LONG).show();
            new Timer().schedule(new TimerTask() {
                public void run() {
                    isExit = false;
                }
            }, 2000);
        } else {
            finish(R.anim.activity_default_in, R.anim.activity_default_out);
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu_drawerclose, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.main_menu_switchtheme:
                startNewActivity(SwitchThemeActivity.class, R.anim.activity_slide_right_in, R.anim.activity_slide_left_out_part, false, null);
                break;
            case R.id.main_menu_exit:
                DialogUtils.buildSimpleDialog(mContext, mFragmentManager, R.string.activity_main_dialog_exit_title,
                        R.string.activity_main_dialog_exit_message, DIALOG_EXIT, true).show();
                break;
        }

        return true;
    }

    @Override
    public void onPositiveButtonClicked(int code) {
        switch (code) {
            case DIALOG_EXIT:
                finish(R.anim.activity_default_in, R.anim.activity_default_out);
                return;
            default:
                break;
        }
    }

    @Override
    public boolean onNavigationItemSelectedExtend(int mSelectedItemID, int mLastSelectedItemID) {
        if (mSelectedItemID == mLastSelectedItemID) {
            return false;
        }

        boolean result = false;
        Bundle mBundle = new Bundle();
        switch (mSelectedItemID) {
            case 0:
                result = true;
                break;
            case 1:
                mBundle.putInt(ListAdapterActivity.KEY_FRAGMENT_FLAG, 0);
                startNewActivity(ListAdapterActivity.class, R.anim.activity_slide_right_in, R.anim.activity_slide_left_out_part, false, mBundle);
                break;
            case 2:
                mBundle.putInt(ListAdapterActivity.KEY_FRAGMENT_FLAG, 1);
                startNewActivity(ListAdapterActivity.class, R.anim.activity_slide_right_in, R.anim.activity_slide_left_out_part, false, mBundle);
                break;
            case 3:
                startNewActivity(SwitchThemeActivity.class, R.anim.activity_slide_right_in, R.anim.activity_slide_left_out_part, false, null);
                break;
            case 4:
                startNewActivity(LazyFragmentActivity.class, R.anim.activity_slide_right_in, R.anim.activity_slide_left_out_part, false, null);
                break;
            default:
                throw new IllegalArgumentException();
        }
        return result;
    }
}
