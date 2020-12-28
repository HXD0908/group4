package com.example.android_demo_day12;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.android_demo_day12.fragment.Fragment1;
import com.example.android_demo_day12.fragment.Fragment2;
import com.example.android_demo_day12.fragment.Fragment3;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.vp)
    ViewPager vp;
    @BindView(R.id.tab)
    TabLayout tab;
    private ArrayList<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        fragments = new ArrayList<>();
        fragments.add(new Fragment1());
        fragments.add(new Fragment2());
        fragments.add(new Fragment3());
        vp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
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
        tab.setupWithViewPager(vp);
        tab.getTabAt(0).setText("首页").setIcon(R.drawable.home_select);
        tab.getTabAt(1).setText("攻略").setIcon(R.drawable.nav_select);
        tab.getTabAt(2).setText("我的").setIcon(R.drawable.me_select);
    }
}