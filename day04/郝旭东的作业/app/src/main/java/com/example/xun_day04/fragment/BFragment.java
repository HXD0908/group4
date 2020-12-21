package com.example.xun_day04.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.xun_day04.R;
import com.example.xun_day04.adapter.FragAdapter;
import com.example.xun_day04.fragment.fragmenta.AaFragment;
import com.example.xun_day04.fragment.fragmenta.AbFragment;
import com.example.xun_day04.fragment.fragmenta.AcFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class BFragment extends Fragment {

    private TabLayout tab;
    private ViewPager vp;
    private ArrayList<Fragment> fargList;
    private FragAdapter fragAdapter;

    public BFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_b, container, false);
        initView(view);
        initVpf();
        return view;
    }

    private void initView(View view) {
        tab = view.findViewById(R.id.tab);
        vp = view.findViewById(R.id.vp);
    }

    private void initVpf() {
        fargList = new ArrayList<>();
        fargList.add(new AaFragment());
        fargList.add(new AbFragment());
        fargList.add(new AcFragment());
        fragAdapter = new FragAdapter(getChildFragmentManager(), fargList);
        vp.setAdapter(fragAdapter);
        tab.setupWithViewPager(vp);
        tab.getTabAt(0).setText("袍子").setIcon(R.drawable.i);
        tab.getTabAt(1).setText("社团").setIcon(R.drawable.a);
        tab.getTabAt(2).setText("排行榜").setIcon(R.drawable.c);
    }
}
