package com.example.asus.recycleviewtest.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.asus.recycleviewtest.fragment.fragmentTest.ContentFragment;
import com.example.asus.recycleviewtest.utils.LogUtil;

import java.util.List;

/**
 * Created by asus on 2017/1/1.
 */

public class TestFragmentAdapter extends FragmentStatePagerAdapter {
    public static final String TAB_TAG = "@dream@";
    private List<String> mTitles;

    public TestFragmentAdapter(FragmentManager fm, List<String> titles) {
        super(fm);
        mTitles = titles;

    }

    @Override
    public Fragment getItem(int position) {
        ContentFragment fragment = new ContentFragment();

        String[] title = mTitles.get(position).split(TAB_TAG);
        //LogUtil.i("打印中文111","sdddddddd");
        LogUtil.i("打印中文","类型为"+title[1]+"__title为"+title[0]);
        fragment.setType(Integer.parseInt(title[1]));
        fragment.setTitle(title[0]);
        return fragment;
    }

    @Override
    public int getCount() {
        return mTitles.size();

    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position).split(TAB_TAG)[0];
    }
}
