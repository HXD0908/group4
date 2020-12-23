package com.example.android_demo_day7.mvp.engine.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.android_demo_day7.mvp.base.App;

import java.util.List;

public class HomeActVpAdapter extends FragmentPagerAdapter {
    private List<Fragment> mFragments;
    private int[] mTabs;

    public HomeActVpAdapter(FragmentManager fm, List<Fragment> mFragments, int[] mTabs) {
        super(fm);
        this.mFragments = mFragments;
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

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return App.getStr(mTabs[position]);
    }
}
