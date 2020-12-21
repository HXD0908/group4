package com.example.demo_day5_zuoye.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.demo_day5_zuoye.R;
import com.example.demo_day5_zuoye.fragment.FragmentFaXian;
import com.example.demo_day5_zuoye.fragment.FragmentMe;
import com.example.demo_day5_zuoye.fragment.FragmentStop;
import com.example.demo_day5_zuoye.fragment.FragmentTianjia;
import com.example.demo_day5_zuoye.fragment.Fragmentshow;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ViewPager mVp;
    private TabLayout mTab;
    private ArrayList<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mVp = (ViewPager) findViewById(R.id.vp);
        mTab = (TabLayout) findViewById(R.id.tab);
        fragments = new ArrayList<>();
        fragments.add(new Fragmentshow());
        fragments.add(new FragmentFaXian());
        fragments.add(new FragmentTianjia());
        fragments.add(new FragmentStop());
        fragments.add(new FragmentMe());
        mVp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
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
        mTab.getTabAt(0).setText("首页").setIcon(R.drawable.home_select);
        mTab.getTabAt(1).setText("发现").setIcon(R.drawable.nav_select);
        mTab.getTabAt(2).setIcon(R.drawable.a9);
        mTab.getTabAt(3).setText("商场").setIcon(R.drawable.stop_select);
        mTab.getTabAt(4).setText("我的").setIcon(R.drawable.me_select);
    }
}
