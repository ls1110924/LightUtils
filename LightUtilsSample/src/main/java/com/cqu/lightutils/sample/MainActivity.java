package com.cqu.lightutils.sample;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.cqu.lightutils.activity.BaseActionBarFragmentActivityWithDrawer;
import com.cqu.lightutils.sample.data.ParameterConfig;
import com.cqu.lightutils.sample.fragment.MainDrawerFragment;
import com.cqu.lightutils.sample.fragment.MultiItemListFragment;
import com.cqu.lightutils.sample.fragment.SingleItemListFragment;
import com.cqu.lightutils.utils.DialogUtils;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends BaseActionBarFragmentActivityWithDrawer {

    private static final int DIALOG_EXIT = 10;

    private FrameLayout mContentFrame;
    private FrameLayout mDrawerFrame;

    private MainDrawerFragment mDrawerFragment;

    private SingleItemListFragment mSingleItemFragment;
    private MultiItemListFragment mMultiItemFragment;

    private ParameterConfig mParaConfig;

    private int mFragmentIndex = -1;

    private boolean isExit = false;

    @Override
    protected void onInitParameter() {
        mParaConfig = ParameterConfig.getInstance();

        mDrawerFragment = new MainDrawerFragment();

        mSingleItemFragment = new SingleItemListFragment();
        mMultiItemFragment = new MultiItemListFragment();
    }

    @Override
    protected void onSetContentView() {
        setContentView(R.layout.activity_main);
    }

    @Override
    protected DrawerLayout onFindDrawerLayoutView() {
        return findView(R.id.activity_main_drawerlayout);
    }

    @Override
    protected void onFindViews() {
        mContentFrame = findView(R.id.activity_main_content);
        mDrawerFrame = findView(R.id.activity_main_drawer);
    }

    @Override
    protected void onBindContent() {
        replaceFragments(R.id.activity_main_drawer, mDrawerFragment, "Drawer", 0, 0);
        replaceFragments(R.id.activity_main_content, mSingleItemFragment, "Content", 0, 0);

        mActionBar.setTitle("SingleItemListTest");
    }

    @Override
    protected void onCreateImpl(Bundle savedInstanceState) {
    }

    @Override
    protected void onStart() {
        super.onStart();
        setMaterialThemeColor(mParaConfig.getMaterialTheme());
    }

    @Override
    protected void onDrawerClosed(ActionBar mActionBar) {
        String mTitle = mDrawerFragment.getTitle();
        mActionBar.setTitle(mTitle == null ? "SingleItemListTest" : mTitle);

        if (mFragmentIndex != -1) {
            switch (mFragmentIndex) {
                case 0:
                    replaceFragments(R.id.activity_main_content, mSingleItemFragment, "Content", 0, 0);
                    break;
                case 1:
                    replaceFragments(R.id.activity_main_content, mMultiItemFragment, "Content", 0, 0);
                    break;
            }
            mFragmentIndex = -1;
        }

        supportInvalidateOptionsMenu();
    }

    @Override
    protected void onDrawerOpened(ActionBar mActionBar) {
        mActionBar.setTitle(R.string.application_name);
        supportInvalidateOptionsMenu();
    }

    @Override
    protected boolean onBackKeyDown() {
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
        if (mDrawerLayout.isDrawerOpen(mDrawerFrame)) {
            getMenuInflater().inflate(R.menu.main_menu_draweropen, menu);
        } else {
            getMenuInflater().inflate(R.menu.main_menu_drawerclose, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item, int flag) {

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

    public void onDrawerItemSelected(int id) {
        mDrawerLayout.closeDrawer(mDrawerFrame);
        mFragmentIndex = id;
    }

}
