package com.example.jetpack;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.example.jetpack.fragment.FoundFragment;
import com.example.jetpack.fragment.HomeFragment;
import com.example.jetpack.fragment.MyFragment;
import com.example.jetpack.fragment.SofaFragment;
import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    private FrameLayout fl;
    private TabLayout tab;
    private HomeFragment homeFragment;
    private SofaFragment sofaFragment;
    private FoundFragment foundFragment;
    private MyFragment myFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        fl = (FrameLayout) findViewById(R.id.fl);
        tab = (TabLayout) findViewById(R.id.tab);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        homeFragment = new HomeFragment();
        sofaFragment = new SofaFragment();
        foundFragment = new FoundFragment();
        myFragment = new MyFragment();

        fragmentTransaction.add(R.id.fl,homeFragment)
                .add(R.id.fl,sofaFragment)
                .add(R.id.fl,foundFragment)
                .add(R.id.fl,myFragment)
                .show(homeFragment)
                .hide(sofaFragment)
                .hide(foundFragment)
                .hide(myFragment)
                .commit();
        tab.addTab(tab.newTab().setText("首页").setIcon(R.drawable.icon_tab_home));
        tab.addTab(tab.newTab().setText("沙发").setIcon(R.drawable.icon_tab_sofa));
        tab.addTab(tab.newTab().setIcon(R.drawable.icon_tab_publish));
        tab.addTab(tab.newTab().setText("发现").setIcon(R.drawable.icon_tab_find));
        tab.addTab(tab.newTab().setText("我的").setIcon(R.drawable.icon_tab_mine));

        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                switch (tab.getPosition()){
                    case 0:
                        transaction.show(homeFragment)
                            .hide(sofaFragment)
                            .hide(foundFragment)
                            .hide(myFragment)
                            .commit();
                        break;
                    case 1:
                        transaction.show(sofaFragment)
                                .hide(homeFragment)
                                .hide(foundFragment)
                                .hide(myFragment)
                                .commit();
                        break;
                    case 2:
//                        startActivity();
                        break;
                    case 3:
                        transaction.show(foundFragment)
                                .hide(homeFragment)
                                .hide(sofaFragment)
                                .hide(myFragment)
                                .commit();
                        break;
                    case 4:
                        transaction.show(myFragment)
                            .hide(homeFragment)
                            .hide(foundFragment)
                            .hide(sofaFragment)
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
