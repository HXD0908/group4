package com.jy.day04.view.activity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.jy.day04.R;
import com.jy.day04.view.fragment.RanExperFragment;
import com.jy.day04.view.fragment.RanLocalFragment;
import com.jy.day04.view.fragment.RanSignFragment;

import java.util.ArrayList;
import java.util.List;

public class RanKingActivity extends AppCompatActivity {

    private TabLayout mTablayoutRan;
    private ViewPager mViewpagerRan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_ran_king);
        initView ();
        initFragment();
    }

    private void initFragment() {
        List<Fragment> list = new ArrayList<> ();
        list.add (new RanExperFragment ());
        list.add (new RanLocalFragment ());
        list.add (new RanSignFragment ());

        mViewpagerRan.setAdapter (new FragmentPagerAdapter (getSupportFragmentManager ()) {
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

        mTablayoutRan.setupWithViewPager (mViewpagerRan);
        mTablayoutRan.getTabAt (0).setText ("经验榜");
        mTablayoutRan.getTabAt (1).setText ("土豪榜");
        mTablayoutRan.getTabAt (2).setText ("签到榜");
    }

    private void initView() {
        mTablayoutRan = (TabLayout) findViewById (R.id.ran_tablayout);
        mViewpagerRan = (ViewPager) findViewById (R.id.ran_viewpager);
    }
}
