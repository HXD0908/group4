package com.example.xun_day04.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

public class FragAdapter extends FragmentStatePagerAdapter {
    private ArrayList<Fragment> fargList;

    public FragAdapter(@NonNull FragmentManager fm, ArrayList<Fragment> fargList) {
        super(fm);
        this.fargList = fargList;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fargList.get(position);
    }

    @Override
    public int getCount() {
        return fargList.size();
    }
}
