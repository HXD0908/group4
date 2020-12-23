package com.example.mymvp;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.main_vp)
    ViewPager viewPager;
    @BindView(R.id.main_tab)
    TabLayout tabLayout;
    @BindView(R.id.root_ll)
    LinearLayout rootLl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initTab();
    }

    private void initTab() {
        tabLayout.addTab(tabLayout.newTab().setText("首页").setIcon(R.drawable.selector_home));
        tabLayout.addTab(tabLayout.newTab().setText("发现").setIcon(R.drawable.selector_discover));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.add));
        tabLayout.addTab(tabLayout.newTab().setText("商城").setIcon(R.drawable.selector_shop));
        tabLayout.addTab(tabLayout.newTab().setText("我的").setIcon(R.drawable.selector_my));
    }

}