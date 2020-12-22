package com.example.day06_homework;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.day06_homework.fragment.AFragment;
import com.example.day06_homework.fragment.BFragment;
import com.example.day06_homework.fragment.CFragment;
import com.example.day06_homework.fragment.DFragment;
import com.example.day06_homework.fragment.EFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ViewPager vp;
    private TabLayout tab;
    private ArrayList<Fragment> fragList;
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
        fragList = new ArrayList<>();
        fragList.add(new AFragment());
        fragList.add(new BFragment());
        fragList.add(new CFragment());
        fragList.add(new DFragment());
        fragList.add(new EFragment());
        fragAdapter = new FragAdapter(getSupportFragmentManager(), fragList);
        vp.setAdapter(fragAdapter);
        tab.setupWithViewPager(vp);
        tab.getTabAt(0).setText(R.string.one).setIcon(R.drawable.item1);
        tab.getTabAt(1).setText(R.string.two).setIcon(R.drawable.item2);
        tab.getTabAt(2).setIcon(R.drawable.item3);
        tab.getTabAt(3).setText(R.string.fore).setIcon(R.drawable.item4);
        tab.getTabAt(4).setText(R.string.five).setIcon(R.drawable.item5);
    }
}
