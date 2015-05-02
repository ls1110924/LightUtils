package com.cqu.lightutils.sample.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cqu.lightutils.sample.R;

/**
 * Created by A Shuai on 2015/4/30.
 */
public class MainFragment extends Fragment{

    private static final String KEY_ID = "id";

    public static Fragment getInstance( int id ){
        Bundle mBundle = new Bundle();
        mBundle.putInt(KEY_ID, id);

        Fragment mFragment = new MainFragment();
        mFragment.setArguments(mBundle);
        return mFragment;
    }

    private int id;

    private TextView mTxt;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle mBundle = getArguments();
        id = mBundle.getInt(KEY_ID);

        System.out.println(id + "--->onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        System.out.println(id + "--->onCreateView");
        View mView = inflater.inflate(R.layout.fragment_main,container,false);
        mTxt = (TextView)mView.findViewById(R.id.fragment_main_text);
        mTxt.setText("Fragment " + id);
        return mView;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        System.out.println(id + "--->setUserVisibleHint--->" + isVisibleToUser);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        System.out.println(id + "--->onDestroyView");
    }
}
