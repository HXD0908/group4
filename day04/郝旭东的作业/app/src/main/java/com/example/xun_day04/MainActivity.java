package com.example.xun_day04;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.xun_day04.adapter.FragAdapter;
import com.example.xun_day04.fragment.AFragment;
import com.example.xun_day04.fragment.BFragment;
import com.example.xun_day04.fragment.CFragment;
import com.example.xun_day04.fragment.DFragment;
import com.example.xun_day04.fragment.EFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ViewPager vp;
    private TabLayout tab;
    private ArrayList<Fragment> fargList;
    private FragAdapter fragAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initVpf();
    }

    private void initView() {
        vp = (ViewPager) findViewById(R.id.vp);
        tab = (TabLayout) findViewById(R.id.tab);
    }

    private void initVpf() {
        fargList = new ArrayList<>();
        fargList.add(new AFragment());
        fargList.add(new BFragment());
        fargList.add(new CFragment());
        fargList.add(new DFragment());
        fargList.add(new EFragment());
        fragAdapter = new FragAdapter(getSupportFragmentManager(), fargList);
        vp.setAdapter(fragAdapter);
        tab.setupWithViewPager(vp);
        tab.getTabAt(0).setIcon(R.drawable.item1_selector).setText("首页");
        tab.getTabAt(1).setIcon(R.drawable.item2_selector).setText("发现");
        tab.getTabAt(2).setIcon(R.drawable.e);
        tab.getTabAt(3).setIcon(R.drawable.item4_selector).setText("收藏");
        tab.getTabAt(4).setIcon(R.drawable.item5_selector).setText("我的");
    }
}
