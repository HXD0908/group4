package com.jy.day04.view.activity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.jy.day04.R;
import com.jy.day04.view.fragment.FindFragment;
import com.jy.day04.view.fragment.HomeFragment;
import com.jy.day04.view.fragment.MyFragment;
import com.jy.day04.view.fragment.PlusFragment;
import com.jy.day04.view.fragment.StoreFragment;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private ViewPager mViewpagerHome;
    private TabLayout mTablayoutHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);
        initView ();
        initFragment();
    }

    private void initFragment() {
        List<Fragment> list = new ArrayList<> ();
        list.add (new HomeFragment ());
        list.add (new FindFragment ());
        list.add (new PlusFragment ());
        list.add (new StoreFragment ());
        list.add (new MyFragment ());

        mViewpagerHome.setAdapter (new FragmentPagerAdapter (getSupportFragmentManager ()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return list.get (position);
            }

            @Override
            public int getCount() {
                return list.size ();
            }
        });
        mTablayoutHome.setupWithViewPager (mViewpagerHome);
        mTablayoutHome.getTabAt (0).setText ("首页").setIcon (R.drawable.home_home);
        mTablayoutHome.getTabAt (1).setText ("发现").setIcon (R.drawable.home_find);
        mTablayoutHome.getTabAt (2).setText ("").setIcon (R.drawable.icon_home_plus);
        mTablayoutHome.getTabAt (3).setText ("商城").setIcon (R.drawable.home_store);
        mTablayoutHome.getTabAt (4).setText ("我的").setIcon (R.drawable.home_my);
    }

    private void initView() {
        mViewpagerHome = (ViewPager) findViewById (R.id.home_viewpager);
        mTablayoutHome = (TabLayout) findViewById (R.id.home_tablayout);
    }
}
