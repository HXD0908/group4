package com.example.jetpack.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jetpack.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;


public class FoundFragment extends Fragment {


    private View view;
    private TabLayout mTab;
    private ViewPager mVp;
    private ArrayList<Fragment> fragments;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_found, container, false);
        initView();
        return view;
    }

    private void initView() {
        mTab = (TabLayout) view.findViewById(R.id.tab);
        mVp = (ViewPager) view.findViewById(R.id.vp);
        fragments = new ArrayList<>();
        fragments.add(new guanFragment());
        fragments.add(new tuiFragment());
        mVp.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });
        mTab.setupWithViewPager(mVp);
        mTab.getTabAt(0).setText("关注");
        mTab.getTabAt(1).setText("推荐");
    }

}