package com.example.jetpack.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jetpack.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;


public class SofaFragment extends Fragment {


    private View view;
    private TabLayout tabSofa;
    private ViewPager vpSofa;
    private ArrayList<Fragment> fragments;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_sofa, container, false);
        initView();
        return view;
    }
    private void initView() {
        tabSofa = view.findViewById(R.id.tab_sofa);
        vpSofa = view.findViewById(R.id.vp_sofa);

        fragments = new ArrayList<>();
        fragments.add(new PicFragment());
        fragments.add(new VedioFragment());
        fragments.add(new TextFragment());
        vpSofa.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });
        tabSofa.setupWithViewPager(vpSofa);
        tabSofa.getTabAt(0).setText("图片");
        tabSofa.getTabAt(1).setText("视频");
        tabSofa.getTabAt(2).setText("文本");
    }
}