package com.example.xun_day10.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

public class FragAdapter extends FragmentStatePagerAdapter {
    private ArrayList<Fragment> fragList;

    public FragAdapter(@NonNull FragmentManager fm, ArrayList<Fragment> fragList) {
        super(fm);
        this.fragList = fragList;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragList.get(position);
    }

    @Override
    public int getCount() {
        return fragList.size();
    }
}
