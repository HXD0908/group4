package com.example.myday9_2;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TabLayout tab;
    private ArrayList<Fragment> list;
    private ViewPager vp;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        tab = (TabLayout) findViewById(R.id.tab);
        vp = (ViewPager) findViewById(R.id.vp);
        list = new ArrayList<>();

        list.add(new AFragment());
        list.add(new BFragment());
        list.add(new CFragment());

        myAdapter = new MyAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT,list);
        vp.setAdapter(myAdapter);

        tab.setupWithViewPager(vp);

        tab.getTabAt(0).setText("首页");
        tab.getTabAt(1).setText("攻略");
        tab.getTabAt(2).setText("我的");
    }
}