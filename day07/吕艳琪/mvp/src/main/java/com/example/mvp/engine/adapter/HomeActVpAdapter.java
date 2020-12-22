package com.example.mvp.engine.adapter;

import com.example.mvp.app.MyApp;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class HomeActVpAdapter extends FragmentPagerAdapter {
    private List<Fragment> mFragments;
    private int[] mTabs;

    public HomeActVpAdapter(FragmentManager mManager, List<Fragment> fragments, int[] mTabs) {
        super(mManager);
        this.mFragments = fragments;
        this.mTabs = mTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return MyApp.getStr(mTabs[position]);
    }
}
