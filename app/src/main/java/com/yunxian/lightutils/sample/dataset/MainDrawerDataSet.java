package com.yunxian.lightutils.sample.dataset;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by A Shuai on 2015/5/4.
 */
public final class MainDrawerDataSet implements IDataSet {

    private List<String> mDataSet;
    private int selected;

    public MainDrawerDataSet() {
        mDataSet = new ArrayList<>();
        selected = -1;
    }

    public void addItem(String item) {
        mDataSet.add(item);
        if (mDataSet.size() == 1) {
            selected = 0;
        }
    }

    public String getIndexItem(int index) {
        if (index < mDataSet.size())
            return mDataSet.get(index);
        throw new IllegalArgumentException();
    }

    public int getSelected() {
        return selected;
    }

    public void setSelected(int selected) {
        this.selected = selected;
    }

    @Override
    public void clear() {
        selected = -1;
        mDataSet.clear();
    }

    @Override
    public int size() {
        return mDataSet.size();
    }
}
