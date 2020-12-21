package com.example.xun_day04.fragment.fragmenta;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.xun_day04.R;
import com.example.xun_day04.adapter.FragAdapter;
import com.example.xun_day04.fragment.fragmentb.HomeAFragment;
import com.example.xun_day04.fragment.fragmentb.HomeBFragment;
import com.example.xun_day04.fragment.fragmentb.HomeCFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class AcFragment extends Fragment {

    private ViewPager vp;
    private TabLayout tab;
    private ArrayList<Fragment> fragList;
    private FragAdapter fragAdapter;

    public AcFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ac, container, false);
        initView(view);
        initVpf();
        return view;
    }

    private void initView(View view) {
        tab = view.findViewById(R.id.tab);
        vp = view.findViewById(R.id.vp);
    }

    private void initVpf() {
        fragList = new ArrayList<>();
        fragList.add(new HomeAFragment());
        fragList.add(new HomeBFragment());
        fragList.add(new HomeCFragment());
        fragAdapter = new FragAdapter(getChildFragmentManager(), fragList);
        vp.setAdapter(fragAdapter);
        tab.setupWithViewPager(vp);
        tab.getTabAt(0).setText("经验榜");
        tab.getTabAt(1).setText("土豪榜");
        tab.getTabAt(2).setText("签到榜");
    }
}
