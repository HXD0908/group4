package com.example.tongpao;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.example.tongpao.fragment.FoundFragment;
import com.example.tongpao.fragment.HomeFragment;
import com.example.tongpao.fragment.MineFragment;
import com.example.tongpao.fragment.ShopFragment;
import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    private FrameLayout fl;
    private TabLayout tab;
    private HomeFragment homeFragment;
    private FoundFragment foundFragment;
    private ShopFragment shopFragment;
    private MineFragment mineFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();


    }

    private void initView() {
        fl = (FrameLayout) findViewById(R.id.fl);
        tab = (TabLayout) findViewById(R.id.tab);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        homeFragment = new HomeFragment();
        foundFragment = new FoundFragment();
        shopFragment = new ShopFragment();
        mineFragment = new MineFragment();

        transaction.add(R.id.fl,homeFragment)
                .add(R.id.fl,foundFragment)
                .add(R.id.fl,shopFragment)
                .add(R.id.fl,mineFragment)
                .show(homeFragment)
                .hide(foundFragment)
                .hide(shopFragment)
                .hide(mineFragment)
                .commit();

        tab.addTab(tab.newTab().setText("首页").setIcon(R.drawable.tabbar_home_normal));
        tab.addTab(tab.newTab().setText("发现").setIcon(R.drawable.tabbar_find_selected));
        tab.addTab(tab.newTab().setIcon(R.drawable.add));
        tab.addTab(tab.newTab().setText("商城").setIcon(R.drawable.tabbar_activity_selected));
        tab.addTab(tab.newTab().setText("我的").setIcon(R.drawable.tabbar_me_selected));

        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                FragmentTransaction transaction1 = getSupportFragmentManager().beginTransaction();
                switch (tab.getPosition()){
                    case 0:
                        transaction1.show(homeFragment)
                                .hide(foundFragment)
                                .hide(shopFragment)
                                .hide(mineFragment)
                                .commit();
                        break;
                    case 1:
                        transaction1.show(foundFragment)
                                .hide(homeFragment)
                                .hide(shopFragment)
                                .hide(mineFragment)
                                .commit();
                        break;
                    case 2:
                        break;
                    case 3:
                        transaction1.show(shopFragment)
                                .hide(foundFragment)
                                .hide(homeFragment)
                                .hide(mineFragment)
                                .commit();
                        break;
                    case 4:
                        transaction1.show(mineFragment)
                                .hide(foundFragment)
                                .hide(shopFragment)
                                .hide(homeFragment)
                                .commit();
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
