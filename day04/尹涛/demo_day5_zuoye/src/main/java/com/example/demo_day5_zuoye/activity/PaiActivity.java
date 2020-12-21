package com.example.demo_day5_zuoye.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.demo_day5_zuoye.R;
import com.example.demo_day5_zuoye.fragment.JingFragment;
import com.example.demo_day5_zuoye.fragment.QianFragment;
import com.example.demo_day5_zuoye.fragment.TuFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class PaiActivity extends AppCompatActivity {

    private TabLayout mTab;
    private ViewPager mVp;
    private ArrayList<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pai);
        initView();
    }

    private void initView() {
        mTab = (TabLayout) findViewById(R.id.tab);
        mVp = (ViewPager) findViewById(R.id.vp);
        fragments = new ArrayList<>();
        fragments.add(new JingFragment());
        fragments.add(new TuFragment());
        fragments.add(new QianFragment());
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
        mTab.getTabAt(0).setText("经验榜");
        mTab.getTabAt(1).setText("土豪榜");
        mTab.getTabAt(2).setText("签到榜");
    }
}
