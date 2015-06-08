package com.cqu.lightutils.utils;

import android.content.Context;
import android.support.v4.app.FragmentManager;

import com.avast.android.dialogs.fragment.SimpleDialogFragment;
import com.avast.android.dialogs.fragment.SimpleDialogFragment.SimpleDialogBuilder;
import com.cqu.lightutils.R;

/**
 * Created by A Shuai on 2015/5/4.
 * 对话框工具类
 */
public final class DialogUtils {

    /**
     *	无效的资源ID
     */
    public static final int INVALID_RES_ID = 0;
    /**
     *	无效的请求码
     */
    public static final int INVALID_REQUEST_CODE = -1;

    private DialogUtils(){
        throw new IllegalStateException();
    }

    public static SimpleDialogBuilder buildSimpleDialog( Context context, FragmentManager fragmentManager,
                                                         int mTitleRes, int mMessage, int mRequestCode, boolean cancelable ){
        SimpleDialogBuilder mBuilder = SimpleDialogFragment.createBuilder(context, fragmentManager).setCancelable(cancelable)
                .setPositiveButtonText(R.string.lightutils_dialog_sure).setNegativeButtonText(R.string.lightutils_dialog_cancel);
        if( mTitleRes != INVALID_RES_ID ){
            mBuilder.setTitle(mTitleRes);
        }
        if( mMessage != INVALID_RES_ID ){
            mBuilder.setMessage(mMessage);
        }
        if( mRequestCode != INVALID_REQUEST_CODE ){
            mBuilder.setRequestCode(mRequestCode);
        }
        return mBuilder;
    }

}
