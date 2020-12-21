package com.example.day4_1.activity;

import android.os.Bundle;
import android.widget.TableLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.day4_1.R;
import com.example.day4_1.adapter.MyHomeVpAdapter;
import com.example.day4_1.fragment.JYFragment;
import com.example.day4_1.fragment.THFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class PaiHangBangActivity extends AppCompatActivity {

    private TabLayout tab;
    private ViewPager vp;
    private ArrayList<Fragment> list;
    private MyHomeVpAdapter myHomeVpAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pai_hang_bang);
        initView();
    }

    private void initView() {
        tab = (TabLayout) findViewById(R.id.tab);
        vp = (ViewPager) findViewById(R.id.vp);

        list = new ArrayList<>();
        list.add(new JYFragment());
        list.add(new THFragment());
        list.add(new THFragment());

        myHomeVpAdapter = new MyHomeVpAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT,list);

        vp.setAdapter(myHomeVpAdapter);

        tab.setupWithViewPager(vp);

        tab.getTabAt(0).setText("经验榜");
        tab.getTabAt(1).setText("土豪榜");
        tab.getTabAt(2).setText("签到榜");

    }
}