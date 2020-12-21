package com.example.day4_1.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ScrollingTabContainerView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.day4_1.R;
import com.example.day4_1.adapter.MyHomeVpAdapter;
import com.example.day4_1.fragment.FaXianFragment;
import com.example.day4_1.fragment.JiaHaoFragment;
import com.example.day4_1.fragment.ShangChengFragment;
import com.example.day4_1.fragment.ShouYeFragment;
import com.example.day4_1.fragment.WoDeFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ViewPager vp;
    private TabLayout tab;
    private ArrayList<Fragment> list;
    private MyHomeVpAdapter myHomeVpAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        vp = (ViewPager) findViewById(R.id.vp);
        tab = (TabLayout) findViewById(R.id.tab);

        list = new ArrayList<>();

        list.add(new ShouYeFragment());
        list.add(new FaXianFragment());
        list.add(new JiaHaoFragment());
        list.add(new ShangChengFragment());
        list.add(new WoDeFragment());

        myHomeVpAdapter = new MyHomeVpAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT,list);

        vp.setAdapter(myHomeVpAdapter);

        tab.setupWithViewPager(vp);

        tab.getTabAt(0).setText("首页").setIcon(R.drawable.tabbar_home_selected);
        tab.getTabAt(1).setText("发现").setIcon(R.drawable.tabbar_find_selected);
        tab.getTabAt(2).setText("").setIcon(R.drawable.jiahao);
        tab.getTabAt(3).setText("商城").setIcon(R.drawable.tabbar_activity_selected);
        tab.getTabAt(4).setText("我的").setIcon(R.drawable.tabbar_me_selected);

    }
}