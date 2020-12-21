package com.example.day04;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.os.Bundle;

import com.example.day04.homes.fragment.fragment.AddFragment;
import com.example.day04.homes.fragment.fragment.FindFragment;
import com.example.day04.homes.fragment.fragment.HomeFragment;
import com.example.day04.homes.fragment.fragment.MineFragment;
import com.example.day04.homes.fragment.fragment.StoreFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tab)
    TabLayout tab;

    private ArrayList<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        inteView();
    }

    private void inteView() {
        fragments = new ArrayList<>();

        ArrayList<String> titles = new ArrayList<>();
        titles.add("首页");
        titles.add("发现");
        titles.add("加号");
        titles.add("商城");
        titles.add("我的");

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        HomeFragment homeFragment = new HomeFragment();
        FindFragment findFragment = new FindFragment();
        AddFragment addFragment = new AddFragment();
        StoreFragment storeFragment = new StoreFragment();
        MineFragment mineFragment = new MineFragment();

        transaction.add(R.id.vp_fragment,homeFragment).commit();
        tab.addTab(tab.newTab().setText("首页"));
        tab.addTab(tab.newTab().setText("发现"));
        tab.addTab(tab.newTab().setIcon(R.drawable.a));
        tab.addTab(tab.newTab().setText("商城"));
        tab.addTab(tab.newTab().setText("我的"));

        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab pTab) {
                switch (pTab.getPosition()){
                    case 0:
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.vp_fragment,homeFragment)
                                .commit();
                        break;
                    case 1:
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.vp_fragment,findFragment)
                                .commit();
                        break;
                    case 2:
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.vp_fragment,addFragment)
                                .commit();
                        break;
                    case 3:
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.vp_fragment,storeFragment)
                                .commit();
                        break;
                    case 4:
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.vp_fragment,mineFragment)
                                .commit();
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab pTab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab pTab) {

            }
        });
    }
}