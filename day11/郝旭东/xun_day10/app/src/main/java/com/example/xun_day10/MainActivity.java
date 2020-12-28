package com.example.xun_day10;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.xun_day10.adapter.FragAdapter;
import com.example.xun_day10.fragment.AFragment;
import com.example.xun_day10.fragment.BFragment;
import com.example.xun_day10.fragment.CFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.vp)
    ViewPager vp;
    @BindView(R.id.tab)
    TabLayout tab;
    private ArrayList<Fragment> fragList;
    private FragAdapter fragAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initVpf();
    }

    private void initVpf() {
        fragList = new ArrayList<>();
        fragList.add(new AFragment());
        fragList.add(new BFragment());
        fragList.add(new CFragment());
        fragAdapter = new FragAdapter(getSupportFragmentManager(), fragList);
        vp.setAdapter(fragAdapter);
        tab.setupWithViewPager(vp);
        tab.getTabAt(0).setText("首页").setIcon(R.mipmap.a);
        tab.getTabAt(1).setText("攻略").setIcon(R.mipmap.a);
        tab.getTabAt(2).setText("我的").setIcon(R.mipmap.a);
    }
}
